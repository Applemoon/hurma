package ru.pyatka.api.data;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NonNull String name;
    @NonNull Boolean bought = true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_category") // TODO delete?
    @NonNull Category category;
    @NonNull String comment = "";
    @NonNull boolean important = false;
    @NonNull boolean needed = false;
}
