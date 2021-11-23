package com.project.warmup.Repositories;

import com.project.warmup.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByOrderByDateDesc();
    List<Post> findByCategory(String category);
    List<Post> findByTitle(String title);
    @Query(value ="select * from post p" +
            " join category cat on cat.id = p.category_id" +
            " where p.title like ?1 and cat.name like ?2",nativeQuery = true)
    List<Post> findByTitleAndCategoryName(String title,String category);

}
