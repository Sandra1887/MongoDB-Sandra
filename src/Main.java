import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to MongoDB!");
        Logger.getLogger("org.mongodb.driver")
                .setLevel(Level.SEVERE);

        Facade mongoFacade = new Facade("mongodb://localhost:27017", "Football",
                "footballPeople");

        Person person;
        Client client;
        Employee employee;

        person = new Person("Zlatan Ibrahimovic", "40", "Milanogatan 2");
        mongoFacade.insertOne(person);
        mongoFacade.updatePerson("Zlatan Ibrahimovic", new Person("Marcus Allbäck", "37", "Örgryte 1887"));

        client = new Client("Olof Mellberg", "45", "Londonstreet 3", "7");
        mongoFacade.insertOne(client);

        employee = new Employee("Bollkalle", "10", "Linjegatan 4", "11");
        mongoFacade.insertOne(employee);

        mongoFacade.deletePerson("6457a0139b333d7f18da264c");
    }
}