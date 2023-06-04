# MongoDB Application
by Sandra Jeppsson Kristiansson

## Modules
+ org.mongodb:mongodb-jdbc:2.0.2
+ org.mongodb:mongodb-driver-sync:4.9.1

## Code:

+ Modell: Person
    + id (get/set)
    + name (get/set)
    + age (get/set)
    + address(get/set)
    + Person(variables)
    + fromDoc(Document doc)
    + toDoc()
    + fromJson(String json)
    + toJson()
    + toString()


+ Modell: Client extends Person
    + _customerId (get/set)
    + Client(variables)
    + toString()
    + toDoc()


+ Modell: Employee extends Person
    + _employeeId (get/set)
    + Employee(variables)
    + toString()
    + toDoc()


+ Facade: MongoDB
  + mongoClient
  + mongoDatabase
  + mongoCollection
  + connectionString
  + collectionName
  + databaseName
  + Facade(variables)
  + ^1 connectToMongoServer()
  + ^2 insertOne(Person person)
  + ^3 readPersons()
  + ^4 findByName(String name)
  + ^5 findById(String id)
  + ^6 updatePerson(String nameToRemove, String nameToadd)
  + ^7 deletePerson(String id)

## *Facade method explainations*

+ *^1. A "MongoClient" object is instanciated and used to connect to the Mongo-database through* 
*MongoDB Java Driver. It then uses "getDatabase()" and "getCollection" to get a reference* 
*to the MongoDB database and the MongoDB Collection.*


+ *^2. Uses "toDoc" to make a document of the person-object and removes the "_id". Then uses*
*MongoCollection's method "insertOne" to add the 'object-document' to the collection.*


+ *^3. Uses MongoCollection's method "find" to search for all the 'object-documents' in the collection* 
*and adds them to the ArrayList. Then prints out all the 'object-documents'.*


+ *^4. Receives the name(String) that is wished to be searched for. Creates a new document and adds*
*the name to it. Compares the document with the collection and, if found, adds it to the ArrayList.*
*Then the forEach-adds each Person-object to the ArrayList.*


+ *^5. Receives the id(String) that is wished to be searched for. Creates a new document and adds*
*the id to it. Then compares it with the documents in the collections and, if found, sends it* 
*to "fromDoc" which creates a new object from the document.*


+ *^6. Receives the name(String) that you want to update and a is replaced with a new Person in which*
*you can change whatever you want. Then adds the "nameToUpdate" to a new document and the "nameToAdd"*
*to another. Next step it uses the MongoCollection's method "findOneAndReplace" to replace the old name*
*with the new one.*


+ *^7. Receives the id(String) that is wished to remove. Then adds the id to a new document which is*
*put into MongoCollection's method "deleteOne" that deletes the name from the collection.*


``` Java
System.out.println("REFLECTIONS: My personal favourite is SQL since it feels much more organized and easier to read due
to its tables. I also feel that it's an easier and more structured "language" to understand and use. I'd also say that 
I'm under the impression that SQL facilitates searching for something specific in the database. Although MongoDB also 
has it's  upsides while in some occations you'd want to save and organize more information about something or someone.
For an example, journal data for students, patients etc. where a documentation database definitely is the better option
since it's more flexible when there's a larger amount of information about something. To make it more clear: 
We earlier made a movie database where we used SQLite. The movie attributes were "title, year, genre, actors and plot"
where the last one obviously includes (mostly) a bit more data - which, according to me, would fit better with
MongoDB. BUT, if we'd exclude the "plot" - I'd say that SQLite is a better match.");
```
***Java***

***Licence MIT***

  