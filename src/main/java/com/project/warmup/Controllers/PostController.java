package com.project.warmup.Controllers;

import com.project.warmup.Models.Post;
import com.project.warmup.Services.PostService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("")
    public ResponseEntity<?> getAllOrWithParams(@RequestParam(required = false) Map<String,String> params){
        List<Post> posts = postService.getAllorParam(params);

        if(posts.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(posts);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Post post){
        postService.save(post);

        return ResponseEntity.ok("post saved");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id){
        Optional<Post> post = postService.getById(id);

        if(post.isEmpty()) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(post.get());
    }
}
