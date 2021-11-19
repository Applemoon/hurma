package ru.hurma.api.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Table(nam = "api_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NonNull String fullName;
    @NonNull String name;
    int position;
    @OneToMany(mappedBy = "category") List<Item> items = new ArrayList<>();
}
