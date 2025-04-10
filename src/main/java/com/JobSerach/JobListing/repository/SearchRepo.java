package com.JobSerach.JobListing.repository;

import com.JobSerach.JobListing.model.Post;

import java.util.List;

public interface SearchRepo {
    List<Post> findByText(String txt);
}
