package com.stdbank.assessment.repository;

import com.mongodb.client.MongoCollection;
import com.stdbank.assessment.model.User;
import jakarta.inject.Inject;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

@Component
public class UserRepositoryImpl implements UserRepository{

    @Inject
    private MongoCollection<Document> collection;

    @Override
    public void saveUser(User user) {
        Document document = convertUserToDocument(user);
        collection.insertOne(document);
    }

    @Override
    public User updateUser(User user) {
        Document updatedDocument = new Document("name", user.getName())
                .append("email", user.getEmail());
        collection.updateOne(eq("_id", new org.bson.types.ObjectId(String.valueOf(user.getId()))),
                new Document("$set", updatedDocument)).getUpsertedId();
        return convertDocumentToUser(updatedDocument);
    }

    @Override
    public User findUserById(String id) {
        Document doc = collection.find(eq("_id", new org.bson.types.ObjectId(id))).first();
        if (doc != null) {
            return convertDocumentToUser(doc);
        }
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();

        for (Document doc : collection.find()) {
            User user = new User();
            user.setId(Integer.parseInt(doc.getObjectId("_id").toString()));
            user.setName(doc.getString("name"));
            user.setEmail(doc.getString("email"));
            users.add(user);
        }
        return users;

    }

    @Override
    public void deleteUser(String id) {
        collection.deleteOne(eq("_id", new org.bson.types.ObjectId(id)));

    }

    private User convertDocumentToUser(Document document){
        return new User(Integer.valueOf(String.valueOf(UUID.randomUUID())),document.getString("name"),
                document.getString("email"));
    }
    private Document convertUserToDocument(User user){
        return new Document("name", user.getName())
                .append("email", user.getEmail()).append("_id",UUID.randomUUID());
    }
}
