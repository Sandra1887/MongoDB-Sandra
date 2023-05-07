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

    MongoClient mongoCl;
    MongoDatabase mongoDb;
    MongoCollection<Document> mongoColl;
    String connStr;
    String collName;
    String dBName;

    public Facade(String connStr, String dBName, String collName) {
        setConnStr(connStr);
        setdBName(dBName);
        setCollName(collName);
        connectToMongoServer();
    }
    public Facade() {
        connectToMongoServer();
    }
    public String getConnStr() {
        return connStr;
    }
    public void setConnStr(String connStr) {
        this.connStr = connStr;
    }
    public String getCollName() {
        return collName;
    }
    public void setCollName(String collName) {
        this.collName = collName;
    }
    public String getdBName() {
        return dBName;
    }
    public void setdBName(String dBName) {
        this.dBName = dBName;
    }
    public void connectToMongoServer() {
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connStr))
                .serverApi(serverApi)
                .build();

        try {
            mongoCl = MongoClients.create(settings);
            mongoDb = mongoCl.getDatabase(dBName);
            mongoColl = mongoDb.getCollection(collName);
            System.out.println("Connected to DB: " + dBName);
        } catch (Exception mongo) {
            System.out.println("Failed to connect");
            System.out.println(mongo.getMessage());
        }
    }
    public void insertOne(Person person) {
        Document doc = person.toDoc();
        doc.remove("_id");

        var find = mongoColl.find(doc);
        if (find.first() == null) {
            mongoColl.insertOne(doc);
        }
    }
    public void readPersons() {
        FindIterable<Document> result = mongoColl.find();
        for(Document res : result) {
            System.out.println(res.toJson());
        }
    }
    public ArrayList<Person> findByName(String name) {
        Document doc = new Document("name", name);
        FindIterable<Document> result = mongoColl.find(doc);
        ArrayList<Person> persons = new ArrayList<>();

        result.forEach(person -> persons.add(Person.fromDoc(person)));
        return persons;
    }
    public Person findById(String id) {
        Document doc = new Document("id", id);
        Document search = mongoColl.find(doc).first();
        return Person.fromDoc(search);
    }

    public void updatePerson(String nameToRemove, String nameToAdd) {
        Document doc = new Document("name", nameToRemove);
        Document update = new Document("name", nameToAdd);
        mongoColl.findOneAndReplace(doc, update);
    }

    public void deletePerson(String id) {
        Document doc = new Document("id", id);
        mongoColl.deleteOne(doc);
    }
}