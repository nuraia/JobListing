package com.JobSerach.JobListing.repository;

import com.JobSerach.JobListing.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.json.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@Component
public class SearchRepositoryImplement implements SearchRepo {

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter mongoConverter;

    @Override
    public List<Post> findByText(String txt) {
        List<Post> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("JobListing");
        MongoCollection<Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                            new Document("$search", new Document("text",
                                new Document("query", txt)
                                        .append("path", Arrays.asList("desc", "techs", "profile")))),
                new Document("$sort",
                        new Document("exp", 1L)),
                new Document("$limit", 5L)));

        result.forEach(doc-> posts.add(mongoConverter.read(Post.class, doc)));

        return posts;
    }
}
