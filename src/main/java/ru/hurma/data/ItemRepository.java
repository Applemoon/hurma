package ru.hurma.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByBought(boolean bought);

    @Query("UPDATE Item SET bought = false")
    void setAllNotBought();
}
