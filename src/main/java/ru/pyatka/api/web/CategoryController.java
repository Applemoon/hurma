package ru.pyatka.api.web;

import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pyatka.api.CategoryService;
import ru.pyatka.api.data.Category;
import ru.pyatka.api.data.CategoryMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/ajax")
public class CategoryController {

    private final CategoryService categoryService;
    private final ConversionService conversionService;

    public CategoryController(CategoryService categoryService, ConversionService conversionService) {
        this.categoryService = categoryService;
        this.conversionService = conversionService;
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getCategories() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : categoryService.getCategories()) {
//            CategoryDTO categoryDTO = conversionService.convert(category, CategoryDTO.class);
            CategoryDTO categoryDTO = CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
            categoryDTOS.add(categoryDTO);
        }
//        return categoryDTOS;

        List<Category> categories = categoryService.getCategories();
        return categories.stream()
//                .map(this::convertToDto)
//                .map(category -> conversionService.convert(category, CategoryDTO.class))
                .map(CategoryMapper.INSTANCE::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

//    private CategoryDTO convertToDto(Category category) {
//        return modelMapper.map(category, CategoryDTO.class);
//    }
}
