package ru.hurma.api.data;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hurma.api.web.ItemDTO;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "itemDTO.id", target = "id")
    @Mapping(source = "itemDTO.name", target = "name")
    @Mapping(source = "category", target = "category")
    Item itemDTOToItem(ItemDTO itemDTO, Category category);

    @Mapping(source = "item.category.name", target = "category")
    ItemDTO itemToItemDTO(Item item);
}