package ru.pyatka.api.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBLoader implements CommandLineRunner {

    CategoryRepository categoryRepository;
    ItemRepository itemRepository;

    public DBLoader(CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... strings) {
        Category fruitCategory = new Category("Fruits", "fruits");
        categoryRepository.save(fruitCategory);
        Item apple = new Item("Apple");
        apple.setCategory(fruitCategory);
        itemRepository.save(apple);
        Item lemon = new Item("Lemon");
        lemon.setCategory(fruitCategory);
        itemRepository.save(lemon);

        Category vegCategory = new Category("Vegetables", "vegetables");
        categoryRepository.save(vegCategory);
        Item tomato = new Item("Tomato");
        tomato.setCategory(vegCategory);
        itemRepository.save(tomato);
    }
}
