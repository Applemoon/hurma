package ru.hurma.service;

import org.springframework.stereotype.Service;
import ru.hurma.data.Item;
import ru.hurma.data.ItemRepository;

import java.util.Optional;

@Service
public class ItemService {

    ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Optional<Item> find(long id) {
        return itemRepository.findById(id);
    }

    public void delete(long id) {
        itemRepository.deleteById(id);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void setAllNotBought() {
        itemRepository.setAllNotBought();
    }
}
