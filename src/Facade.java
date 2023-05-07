import com.mongodb.client.*;
import org.bson.Document;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;

public class Facade {

    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    MongoCollection<Document> mongoCollection;
    String connectionString;
    String collectionName;
    String databaseName;

    public Facade(String connectionString, String databaseName, String collectionName) {
        setConnectionString(connectionString);
        setDatabaseName(databaseName);
        setCollectionName(collectionName);
        connectToMongoServer();
    }
    public Facade() {
        connectToMongoServer();
    }
    public String getConnectionString() {
        return connectionString;
    }
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
    public String getCollectionName() {
        return collectionName;
    }
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
    public String getDatabaseName() {
        return databaseName;
    }
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    public void connectToMongoServer() {
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        try {
            mongoClient = MongoClients.create(settings);
            mongoDatabase = mongoClient.getDatabase(databaseName);
            mongoCollection = mongoDatabase.getCollection(collectionName);
            System.out.println("Connected to DB: " + databaseName);
        } catch (Exception mongo) {
            System.out.println("Failed to connect");
            System.out.println(mongo.getMessage());
        }
    }
    public void insertOne(Person person) {
        Document doc = person.toDoc();
        doc.remove("_id");

        var find = mongoCollection.find(doc);
        if (find.first() == null) {
            mongoCollection.insertOne(doc);
        }
    }
    public void readPersons() {
        FindIterable<Document> result = mongoCollection.find();
        for(Document res : result) {
            System.out.println(res.toJson());
        }
    }
    public ArrayList<Person> findByName(String name) {
        Document doc = new Document("name", name);
        FindIterable<Document> result = mongoCollection.find(doc);
        ArrayList<Person> persons = new ArrayList<>();

        result.forEach(person -> persons.add(Person.fromDoc(person)));
        return persons;
    }
    public Person findById(String id) {
        Document doc = new Document("id", id);
        Document search = mongoCollection.find(doc).first();
        return Person.fromDoc(search);
    }

    public void updatePerson(String nameToUpdate, String nameToAdd) {
        Document doc = new Document("name", nameToUpdate);
        Document update = new Document("name", nameToAdd);
        mongoCollection.findOneAndReplace(doc, update);
    }

    public void deletePerson(String id) {
        Document doc = new Document("id", id);
        mongoCollection.deleteOne(doc);
    }
}