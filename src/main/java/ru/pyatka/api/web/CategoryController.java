package ru.pyatka.api.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pyatka.api.CategoryService;
import ru.pyatka.api.data.CategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getCategories() {
        return categoryService.getCategories().stream()
                .map(CategoryMapper.INSTANCE::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }
}
