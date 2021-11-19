package ru.hurma.api;

import org.springframework.stereotype.Service;
import ru.hurma.api.data.Item;
import ru.hurma.api.data.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item find(long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public void delete(long id) {
        itemRepository.deleteById(id);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void setAllNotBought() {
        List<Item> boughtItems = itemRepository.findByBought(true);
        for (Item item : boughtItems) {
            item.setBought(false);
        }
        itemRepository.saveAll(boughtItems);
    }
}
