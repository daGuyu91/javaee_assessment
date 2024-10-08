package com.stdbank.assessment.repository;

import com.mongodb.client.MongoCollection;
import com.stdbank.assessment.model.Product;
import com.stdbank.assessment.model.User;
import jakarta.inject.Inject;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

@Component
public class ProductRepositoryImpl implements ProductRepository{

    @Inject
    private MongoCollection<Document> collection;

    @Override
    public void saveProduct(Product Product) {
        Document document = convertProductToDocument(Product);
        collection.insertOne(document);
    }

    @Override
    public Product updateProduct(Product product) {
        Document updatedDocument = new Document("name", product.getName())
                .append("price", product.getPrice());
        collection.updateOne(eq("_id",
                        new org.bson.types.ObjectId(String.valueOf(product.getId()))),
                new Document("$set", updatedDocument)).getUpsertedId();
        return convertDocumentToProduct(updatedDocument);
    }

    @Override
    public Product findProductById(String id) {
        Document doc = collection.find(eq("_id", new org.bson.types.ObjectId(id))).first();
        if (doc != null) {
            return convertDocumentToProduct(doc);
        }
        return null;
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();

        for (Document doc : collection.find()) {
            products.add(convertDocumentToProduct(doc));
        }
        return products;

    }

    @Override
    public void deleteProduct(String id) {
        collection.deleteOne(eq("_id", new org.bson.types.ObjectId(id)));

    }

    private Product convertDocumentToProduct(Document document){
        return new Product(Integer.valueOf(String.valueOf(UUID.randomUUID())),document.getString("name"),
                document.getString("email"));
    }
    private Document convertProductToDocument(Product Product){
        return new Document("name", Product.getName())
                .append("price", Product.getPrice()).append("_id",UUID.randomUUID());
    }
}
