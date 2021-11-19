package ru.hurma.api.web;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDTO {
    Long id;
    Boolean bought;
    String category;
    String comment;
    Boolean important;
    String name;
    Boolean needed;
}
