

> http://mongodb.github.io/mongo-java-driver/3.8/driver/getting-started/

### Notes
- The preferred artifact for new applications is mongodb-driver-sync
- Typically you only create one MongoClient instance for a given MongoDB deployment 
- A MongoClient instance represents a pool of connections to the database; you will only need one instance of class MongoClient even with multiple threads.
- To dispose of an instance, call MongoClient.close() to clean up resources.


### Scenario covered
-  #### Update
-  Remove a document from the array
-  Add a new document in the array
-  Remove a element from the array of document of an array
-  Add a new element from the array of document of an array 
-  Add or remove multiple element from an array
-  #### Read 
-  Find an embedded document in array
-  Projection of fields from embedded document of an array
 
