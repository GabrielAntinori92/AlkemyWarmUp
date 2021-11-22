package com.project.warmup.Repositories;

import com.project.warmup.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByOrderByDateDesc();
    List<Post> findByCategory(String category);
    List<Post> findByTitle(String title);
    List<Post> findByTitleAndCategoryName(String title,String category);

}
