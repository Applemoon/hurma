package ru.hurma.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hurma.service.CategoryService;
import ru.hurma.data.Category;

import java.util.List;

@RestController
@RequestMapping("/ajax/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }
}