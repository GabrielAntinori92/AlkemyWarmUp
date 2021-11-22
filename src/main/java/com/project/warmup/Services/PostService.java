package com.project.warmup.Services;

import com.project.warmup.Models.Post;
import com.project.warmup.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void save(Post post){
        postRepository.save(post);
    }

    public void update(Long id, Post updated) throws Exception{
        Optional<Post> post = postRepository.findById(id);

        if(post.isEmpty()) throw new Exception("Post id doesn't exist");

        post.get().setTitle(updated.getTitle());
        post.get().setImage(updated.getImage());
        post.get().setCategory(updated.getCategory());
        post.get().setContent(updated.getContent());
        post.get().setDate(updated.getDate());
    }

    public Optional<Post> getById(Long id) throws Exception{
        Optional<Post> post = postRepository.findById(id);

        if(post.isEmpty()) throw new Exception("Post with " + id + "doesn't exist");

        return post;
    }

    public void delete(Long id){
        postRepository.deleteById(id);
    }

    public List<Post> getAllorParam(Map<String,String> params){

        List<Post> posts = null;

        if(params.isEmpty()){
            posts = postRepository.findAllByOrderByDateDesc();
        }else if(params.containsKey("title") && params.containsKey("category")){
            posts = postRepository.findByTitleAndCategoryName(params.get("title"), params.get("category"));
        }else if(params.containsKey("title")){
            posts = postRepository.findByTitle("title");
        }else if(params.containsKey("category")){
            posts = postRepository.findByCategory("category");
        }

        return posts;
    }
}
