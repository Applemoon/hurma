package ru.pyatka.api.data;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.pyatka.api.web.ItemDTO;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "itemDTO.id", target = "id")
    @Mapping(source = "itemDTO.name", target = "name")
    @Mapping(source = "category", target = "category")
    Item itemDTOToItem(ItemDTO itemDTO, Category category);
}
