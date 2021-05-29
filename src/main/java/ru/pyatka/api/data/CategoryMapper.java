package ru.pyatka.api.data;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.pyatka.api.web.CategoryDTO;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper( CategoryMapper.class );

//    @Mapping(source = "numberOfSeats", target = "seatCount")
    CategoryDTO categoryToCategoryDTO(Category car);
}
