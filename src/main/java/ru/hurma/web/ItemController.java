package ru.hurma.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.hurma.service.CategoryService;
import ru.hurma.service.ItemService;
import ru.hurma.data.Category;
import ru.hurma.data.Item;

@RestController
@RequestMapping("/ajax/items")
public class ItemController {

    private final CategoryService categoryService;
    private final ItemService itemService;

    public ItemController(CategoryService categoryService, ItemService itemService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestBody Item item) {
        Category category = categoryService.findByName(item.getCategory().getName());
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        item.setCategory(category);
        return itemService.save(item);
    }

    @PatchMapping(value = "/{id}")
    public Item editItem(@PathVariable long id, @RequestBody ItemDTO editedItemDTO) {
        Item item = itemService.find(id);
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }

        if (editedItemDTO.getBought() != null) item.setBought(editedItemDTO.getBought());
        if (editedItemDTO.getCategory() != null) {
            Category category = categoryService.getByName(editedItemDTO.getCategory());
            if (category == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
            }
            item.setCategory(category);
        }
        if (editedItemDTO.getComment() != null) item.setComment(editedItemDTO.getComment());
        if (editedItemDTO.getImportant() != null) item.setImportant(editedItemDTO.getImportant());
        if (editedItemDTO.getName() != null) item.setName(editedItemDTO.getName());
        if (editedItemDTO.getNeeded() != null) item.setNeeded(editedItemDTO.getNeeded());

        return itemService.save(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable long id) {
        itemService.delete(id);
    }

    @PatchMapping("/all-not-bought")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setAllNotBought() {
        itemService.setAllNotBought();
    }
}
