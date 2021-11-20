package ru.hurma.api.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private Boolean bought;
    private String category;
    private String comment;
    private Boolean important;
    private String name;
    private Boolean needed;
}
