package ru.hurma.api.data;

import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NonNull String name;
    boolean bought = false;
    @ManyToOne(fetch = FetchType.LAZY)
//    @NonNull TODO delete?
    Category category;
    String comment = "";
    boolean important = false;
    boolean needed = false;
}
