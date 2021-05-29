package ru.pyatka.api.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pyatka.api.data.Item;

@Data
@AllArgsConstructor
@NoArgsConstructor // TODO delete?
@Builder // TODO for mapper, delete?
public class ItemDTO {
    private Long id;
    private Boolean bought;
    private String category;
    private String comment;
    private boolean important;
    private String name;
    private boolean needed;

//    public ItemDTO(Item item) {
//        this.id = item.getId();
//        this.bought = item.getBought();
//        this.category = item.getCategory().getName();
//        this.comment = item.getComment();
//        this.important = item.isImportant();
//        this.name = item.getName();
//        this.needed = item.isNeeded();
//    }
}
