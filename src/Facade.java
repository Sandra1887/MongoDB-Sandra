import com.mongodb.client.*;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;

//Skapa en MongoDBFasad som kan hantera CRUD (Create, Read, Update, Delete)
public class Facade {
    MongoClient mongoCli;
    MongoDatabase mongoDb;
    MongoCollection<Document> mongoColl;

    String connStr;
    String collName;
    String dBName;

    public Facade(String collName, String dBName) {
        setConnStr("mongodb://localhost:27017");
        setCollName(collName);
        setdBName(dBName);
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

        MongoClientSettings set = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connStr))
                .serverApi(serverApi)
                .build();
        try {
            mongoCli = MongoClients.create(set);
            mongoDb = mongoCli.getDatabase(dBName);
            mongoColl = mongoDb.getCollection(collName);
        } catch (Exception mongo) {
            System.out.println("Failed to connect");
            System.out.println(mongo.getMessage());
        }
    }
    public void insertOnePerson(Person person) { //create Person
        Document doc = person.toDoc();
        doc.remove("_id");

        var findDoc = mongoColl.find(doc);
        if(findDoc.first() != null) mongoColl.insertOne(doc);
    }

    public void insertOneClient(Client client) { //create Client
        Document doc = client.toDoc();
        doc.remove("_customerId");

        var findDoc = mongoColl.find(doc);
        if(findDoc.first() != null) mongoColl.insertOne(doc);
    }

    public void insertOneEmployee(Employee employee) { //create Employee
        Document doc = employee.toDoc();
        doc.remove("_employeeId");

        var findDoc = mongoColl.find(doc);
        if(findDoc.first() != null) mongoColl.insertOne(doc);
    }

    public ArrayList<Person> findPersonByName(String name) {
        Document doc = new Document("name", name);
        FindIterable<Document> found = mongoColl.find(doc);
        ArrayList<Person> persons = new ArrayList<>();
        found.forEach(person -> persons.add(Person.fromDoc(person)));
    return persons;
    }

    public ArrayList<Client> findClientByName(String name) {
        Document doc = new Document("name", name);
        FindIterable<Document> found = mongoColl.find(doc);
        ArrayList<Client> clients = new ArrayList<>();
        found.forEach(client -> clients.add((Client) Client.fromDoc(client)));

        return clients;
    }
    public ArrayList<Employee> findEmployeeByName(String name) {
        Document doc = new Document("name", name);
        FindIterable<Document> found = mongoColl.find(doc);
        ArrayList<Employee> employees = new ArrayList<>();
        found.forEach(employee -> employees.add((Employee) Employee.fromDoc(employee)));

        return employees;
    }
    public Person findAnyOneById(String id) { //få fram instans av objekt?
        if (id.equalsIgnoreCase("_id")) {
            Document doc = new Document("_id", id);
            Document found = mongoColl.find(doc).first();
            return Person.fromDoc(found);
        } else if (id.equalsIgnoreCase("_customerId")) {
            Document doc = new Document("_custumerId", id);
            Document found = mongoColl.find(doc).first();
            return Client.fromDoc(found);
        } else {
            Document doc = new Document("_employeeId", id);
            Document found = mongoColl.find(doc).first();
            return Employee.fromDoc(found);

        }
    }


    public void deleteAnyone(String id) {
        if(id.equalsIgnoreCase("_id")) {
            Document doc = new Document("_id", id);
            mongoColl.deleteOne(doc);
        } else if (id.equalsIgnoreCase("_customerId")) {
            Document doc = new Document("_customerId", id);
            mongoColl.deleteOne(doc);
        } else {
            Document doc = new Document("_employeeId", id);
            mongoColl.deleteOne(doc);
        }

    }
}
