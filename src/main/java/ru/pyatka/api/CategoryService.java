package ru.pyatka.api;

import org.springframework.stereotype.Service;
import ru.pyatka.api.data.Category;
import ru.pyatka.api.data.CategoryRepository;
import ru.pyatka.api.data.Item;
import ru.pyatka.api.data.ItemRepository;

import java.util.List;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;
    ItemRepository itemRepository;

    public CategoryService(CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;

        initDB(); // TODO delete after migrating to PSQL
    }

    public void initDB() {
        Category fruitCategory = new Category("Фрукты", "fruits");
        categoryRepository.save(fruitCategory);
        Item apple = new Item("Apple");
        apple.setCategory(fruitCategory);
        itemRepository.save(apple);
        Item lemon = new Item("Lemon");
        lemon.setCategory(fruitCategory);
        itemRepository.save(lemon);

        Category vegCategory = new Category("Овощи", "vegetables");
        categoryRepository.save(vegCategory);
        Item tomato = new Item("Tomato");
        tomato.setCategory(vegCategory);
        itemRepository.save(tomato);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category getByName(String name) {
        return categoryRepository.getByName(name);
    }
}
