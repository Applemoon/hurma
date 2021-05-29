package ru.pyatka.api.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pyatka.api.data.Category;
import ru.pyatka.api.data.Item;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor // TODO delete?
public class CategoryDTO {
    private long id;
    private String fullName; // TODO full_name
    private String name;
    private int position;
    private List<ItemDTO> items; // TODO item_set

//    public CategoryDTO(Category category) {
//        this.id = category.getId();
//        this.fullName = category.getFullName();
//        this.name = category.getName();
//        this.position = category.getPosition();
//        this.items = new ArrayList<>();
//        for (Item item : category.getItemSet()) {
//            this.items.add(new ItemDTO(item));
//        }
//    }
}
