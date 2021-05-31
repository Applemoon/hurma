package ru.pyatka.api.data;

import org.mapstruct.Mapper;
import ru.pyatka.api.web.CategoryDTO;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO categoryToCategoryDTO(Category category);

    default String map(Category category) {
        return category.getName();
    }
}
