//package ru.pyatka.api.web;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//import ru.pyatka.api.CategoryService;
//import ru.pyatka.api.ItemService;
//import ru.pyatka.api.data.Category;
//import ru.pyatka.api.data.Item;
//
//@RestController()
//@RequestMapping("/ajax")
//public class ItemController {
//
//    private final CategoryService categoryService;
//    private final ItemService itemService;
//    private final ModelMapper modelMapper;
//
//    public ItemController(CategoryService categoryService, ItemService itemService, ModelMapper modelMapper) {
//        this.categoryService = categoryService;
//        this.itemService = itemService;
//        this.modelMapper = modelMapper;
//    }
//
//    @PostMapping("/items")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createItem(@RequestBody ItemDTO itemDTO) {
//        Category category = categoryService.findByName(itemDTO.getCategory());
//        if (category == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
//        }
//        Item item = new Item(
//                itemDTO.getBought(),
//                itemDTO.getComment(),
//                itemDTO.isImportant(),
//                itemDTO.getName(),
//                itemDTO.isNeeded(),
//                category);
//        itemService.save(item);
//    }
//
//    // PATCH /items/478 {needed: true, ...}
//    @PatchMapping("/items/{id}")
//    public void editItem(@PathVariable long id, @RequestBody ItemDTO editedItemDTO) {
//        Item item = itemService.find(id);
////        if (editedItemDTO.getBought() != null) item.setBought(editedItemDTO.getBought());
////        if (editedItemDTO.getCategory() != null) item.setCategory(editedItemDTO.getCategory());
////        if (editedItemDTO.getComment() != null) item.setComment(editedItemDTO.getComment());
////        if (editedItemDTO.getBought() != null) item.setBought(editedItemDTO.getBought());
////        if (editedItemDTO.getBought() != null) item.setBought(editedItemDTO.getBought());
////        if (editedItemDTO.getBought() != null) item.setBought(editedItemDTO.getBought());
//        // TODO
//    }
//
//    @DeleteMapping("/items/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteItem(@PathVariable long id) {
//        itemService.delete(id);
//    }
//
//    // PATCH /items/all-not-bought
//    @PatchMapping("/items/all-not-bought")
//    public void setAllNotBought() {
//        itemService.setAllNotBought();
//    }
//
////    private ItemDTO convertToDto(Item item) {
////        ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
////        itemDTO.setSubmissionDate(item.getSubmissionDate(),
////                userService.getCurrentUser().getPreference().getTimezone());
////        return itemDTO;
////    }
////
////    private Item convertToEntity(ItemDTO postDto) {
////        Item item = modelMapper.map(postDto, Item.class);
////        item.setSubmissionDate(postDto.getSubmissionDateConverted(
////                userService.getCurrentUser().getPreference().getTimezone()));
////
////        if (postDto.getId() != null) {
////            Item oldPost = itemService.getPostById(postDto.getId());
////            item.setRedditID(oldPost.getRedditID());
////            item.setSent(oldPost.isSent());
////        }
////        return item;
////    }
//}
