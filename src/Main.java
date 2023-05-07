public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //Skapa en main-metod där du skapar ett antal Person-objekt, Kund-objekt och Anställd-objekt
        // och sedan sparar dem i MongoDB-fasad. Du kan sedan uppdatera och ta bort dem från databasen.

        //Tänk på att om programmet inte kan hitta en nyckel till servern, ska den utgå ifrån
        // att connectionstring är mongodb://localhost:27017/{dinDatabas}

        //I Readmefilen ska det finnas förklaringar till alla MongoDB-anrop i MongoDB-fasaden.

        Facade mongoFacade = new Facade("mixedPersons", "Persons");
        Person sandra = new Person("Sandra", "30", "Göteborgsgatan 1");
        Client mongo1 = new Client("Mongo1", "10", "Stockholmsgatan 2", "1");
        Employee mongo2 = new Employee("Mongo2", "11", "Skånegatan 3", "3");
        mongoFacade.insertOnePerson(sandra);
        mongoFacade.insertOnePerson(mongo1);
        mongoFacade.insertOnePerson(mongo2);




    }
}