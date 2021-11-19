package ru.hurma.api.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hurma.api.CategoryService;
import ru.hurma.api.data.CategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ajax/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping()
    public List<CategoryDTO> getCategories() {
        return categoryService.getCategories().stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }
}
