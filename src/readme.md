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


+ ^5. Receives the id(String) that is wished to be searched for. Creates a new document and adds 
the id to it. Then compares it with the documents in the collections and, if found, sends it 
to "fromDoc" which creates a new object from the document.


+ *^6. Receives the name(String) that you want to update and the name(String) that you want to change*
*it to. Then adds the "nameToUpdate" to a new document and the "nameToAdd" to another. Next step*
*it uses the MongoCollection's method "findOneAndReplace" to change the old name with the new one.*


+ *^7. Receives the id(String) that is wished to remove. Then adds the id to a new document which is*
*put into MongoCollection's method "deleteOne" that deletes the name from the collection.*


``` Java
-------------------------------------------------------------------------------------------------------------------------------------------------
```

***Java***

***Licence MIT***

  