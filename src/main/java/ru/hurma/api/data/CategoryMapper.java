package ru.hurma.api.data;

import org.mapstruct.Mapper;
import ru.hurma.api.web.CategoryDTO;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

//    @Mapping(source = "category.name", target = "name")
    CategoryDTO categoryToCategoryDTO(Category category);

    default String map(Category category) {
        return category.getName();
    }
}
