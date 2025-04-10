package com.JobSerach.JobListing.repository;

import com.JobSerach.JobListing.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepo extends MongoRepository<Post, String> {
}
