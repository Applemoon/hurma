package ru.pyatka.api.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.pyatka.api.CategoryService;
import ru.pyatka.api.ItemService;
import ru.pyatka.api.data.Category;
import ru.pyatka.api.data.Item;
import ru.pyatka.api.data.ItemMapper;

@RestController()
public class ItemController {

    private final CategoryService categoryService;
    private final ItemService itemService;

    public ItemController(CategoryService categoryService, ItemService itemService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public void createItem(@RequestBody ItemDTO itemDTO) {
        Category category = categoryService.getByName(itemDTO.getCategory());
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        Item item = ItemMapper.INSTANCE.INSTANCE.itemDTOToItem(itemDTO, category);
        itemService.save(item);
    }

    @PatchMapping("/items/{id}")
    public void editItem(@PathVariable long id, @RequestBody ItemDTO editedItemDTO) {
        Item item = itemService.find(id);
        if (editedItemDTO.getBought() != null) item.setBought(editedItemDTO.getBought());
        if (editedItemDTO.getCategory() != null) {
            Category category = categoryService.getByName(editedItemDTO.getCategory());
            item.setCategory(category);
        }
        if (editedItemDTO.getComment() != null) item.setComment(editedItemDTO.getComment());
        if (editedItemDTO.getImportant() != null) item.setImportant(editedItemDTO.getImportant());
        if (editedItemDTO.getName() != null) item.setName(editedItemDTO.getName());
        if (editedItemDTO.getNeeded() != null) item.setNeeded(editedItemDTO.getNeeded());

        itemService.save(item);
    }

    @DeleteMapping("/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable long id) {
        itemService.delete(id);
    }

    @PatchMapping("/items/all-not-bought")
    public void setAllNotBought() {
        itemService.setAllNotBought();
    }
}
