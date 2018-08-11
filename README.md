

> http://mongodb.github.io/mongo-java-driver/3.8/driver/getting-started/

> The preferred artifact for new applications is mongodb-driver-sync
> Typically you only create one MongoClient instance for a given MongoDB deployment 
> A MongoClient instance represents a pool of connections to the database; you will only need one instance of class MongoClient even with multiple threads.
> To dispose of an instance, call MongoClient.close() to clean up resources.