package ru.pyatka.api.data;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.pyatka.api.web.ItemDTO;

@Mapper
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper( ItemMapper.class );

    Item itemDTOToItem(ItemDTO itemDTO);

    default Item itemDTOToItem(ItemDTO itemDTO, Category category) {
        Item item = itemDTOToItem(itemDTO);
        item.setCategory(category);
        return item;
    }

    default Category map(String category) {
        return null;
    }
}
