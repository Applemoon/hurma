package ru.hurma.api.web;

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
import ru.hurma.api.CategoryService;
import ru.hurma.api.ItemService;
import ru.hurma.api.data.Category;
import ru.hurma.api.data.Item;
import ru.hurma.api.data.ItemMapper;

@RestController
@RequestMapping("/ajax/items")
public class ItemController {

    private final CategoryService categoryService;
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    public ItemController(CategoryService categoryService, ItemService itemService, ItemMapper itemMapper) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO createItem(@RequestBody ItemDTO itemDTO) {
        Category category = categoryService.findByName(itemDTO.getCategory());
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        if (itemDTO.getName() == null) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Name can't be empty");
        }
        Item item = itemMapper.itemDTOToItem(itemDTO, category);
        item = itemService.save(item);
        return itemMapper.itemToItemDTO(item);
    }

    @PatchMapping(value = "/{id}")
    public ItemDTO editItem(@PathVariable long id, @RequestBody ItemDTO editedItemDTO) {
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

        item = itemService.save(item);
        return itemMapper.itemToItemDTO(item);
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
