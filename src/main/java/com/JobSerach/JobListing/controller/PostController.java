package com.JobSerach.JobListing.controller;

import com.JobSerach.JobListing.repository.PostRepo;
import com.JobSerach.JobListing.model.Post;
import com.JobSerach.JobListing.repository.SearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostRepo repo;

    @Autowired
    SearchRepo srepo;

    @GetMapping("/posts")
    public List<Post> getAllPosts()
    {
        return repo.findAll();
    }

    @GetMapping("/posts/{txt}")
    public List<Post> search(@PathVariable String txt)
    {
        return srepo.findByText(txt);
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post)
    {
        return repo.save(post);
    }
}
