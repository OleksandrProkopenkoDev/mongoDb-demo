package ua.spro.mongodemo.product;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class ProductDao {
  private final MongoCollection<Document> collection;

  public ProductDao() {
    // MongoDB connection URI with username and password
    String connectionString = "mongodb://admin:password@localhost:27027/?authSource=admin";

    // Create MongoClientSettings to configure the connection
    MongoClientSettings settings =
        MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(connectionString))
            .build();

    // Connect to MongoDB server
    MongoClient mongoClient = MongoClients.create(settings);

    // Access the database
    MongoDatabase database = mongoClient.getDatabase("aliboucoding");

    // Access the collection
    collection = database.getCollection("products");
  }

  public void save(Product product) {
    Document doc =
        new Document("name", product.getName())
            .append("description", product.getDescription());
    collection.insertOne(doc);
  }

  public Product findById(String id) {
    try {
      ObjectId objectId = new ObjectId(id);
      Document query = new Document("_id", objectId);
      Document result = collection.find(query).first();
      if (result != null) {
        return documentToProduct(result);
      }
    } catch (IllegalArgumentException e) {
      System.err.println("Invalid ObjectId: " + id);
    }
    return null;
  }

  public List<Product> findAll() {
    List<Product> products = new ArrayList<>();
    for (Document doc : collection.find()) {
      products.add(documentToProduct(doc));
    }
    return products;
  }

  public void update(Product product) {
    Document query = new Document("_id", new ObjectId(product.getId()));
    Document update =
        new Document(
            "$set",
            new Document("name", product.getName())
                .append("description", product.getDescription()));
    collection.updateOne(query, update);
  }

  public void delete(Product product) {
    Document query = new Document("_id", new ObjectId(product.getId()));
    collection.deleteOne(query);
  }

  private Product documentToProduct(Document doc) {
    String id = doc.getObjectId("_id").toString();
    String name = doc.getString("name");
    String description = doc.getString("description");
    return new Product(id, name, description);
  }
}
