package ru.pyatka.api.data;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.pyatka.api.web.CategoryDTO;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper( CategoryMapper.class );

    CategoryDTO categoryToCategoryDTO(Category category);

    default String map(Category category) {
        return category.getName();
    }
}
