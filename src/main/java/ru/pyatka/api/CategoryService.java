package ru.pyatka.api;

import org.springframework.stereotype.Service;
import ru.pyatka.api.data.Category;
import ru.pyatka.api.data.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category getByName(String name) {
        return categoryRepository.getByName(name);
    }
}
