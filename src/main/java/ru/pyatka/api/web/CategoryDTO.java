package ru.pyatka.api.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
