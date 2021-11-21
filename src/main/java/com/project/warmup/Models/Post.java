package com.project.warmup.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity @NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String image;
    private Date created;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
}
