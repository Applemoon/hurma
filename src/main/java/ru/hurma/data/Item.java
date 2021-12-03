package ru.hurma.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String comment = "";
    private Boolean bought = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(using = ItemCategorySerializer.class)
    @JsonDeserialize(using = ItemCategoryDeserializer.class)
    private Category category;
    private Boolean important = false;
    private Boolean needed = false;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", bought=" + bought +
                ", category=" + category.getName() +
                ", important=" + important +
                ", needed=" + needed +
                '}';
    }
}
