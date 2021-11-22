package com.project.warmup.Services;

import com.project.warmup.Models.Category;
import com.project.warmup.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void save(String name){
        Category category = new Category();
        category.setName(name);

        categoryRepository.save(category);
    }
}
