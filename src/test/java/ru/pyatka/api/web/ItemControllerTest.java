package ru.pyatka.api.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.pyatka.api.CategoryService;
import ru.pyatka.api.ItemService;
import ru.pyatka.api.data.Category;
import ru.pyatka.api.data.CategoryRepository;
import ru.pyatka.api.data.Item;
import ru.pyatka.api.data.ItemMapper;
import ru.pyatka.api.data.ItemMapperImpl;
import ru.pyatka.api.data.ItemRepository;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    @Test
    void shouldCreateItem() throws Exception {
        Category category = new Category("Fruits", "fruits");
        given(categoryRepository.findByName("fruit")).willReturn(category);

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("name");
        itemDTO.setCategory("fruit");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/items")
                        .content(asJsonString(itemDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        Item item = itemMapper.itemDTOToItem(itemDTO, category);
        then(itemRepository).should(times(1)).save(item);
    }

    @Test
    void shouldReturnErrorWhenCreateItemNoName() throws Exception {
        given(categoryRepository.findByName("fruit")).willReturn(new Category("Fruits", "fruits"));

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setCategory("fruit");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/items")
                        .content(asJsonString(itemDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isExpectationFailed());
    }

    @Test
    void shouldReturnErrorWhenCreateItemWrongCategory() throws Exception {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setCategory("wrong");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/items")
                        .content(asJsonString(itemDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void editItem() throws Exception {
        Category categoryOld = new Category("Fruits", "fruits");
        Category categoryNew = new Category("Vegetables", "vegetables");
        given(categoryRepository.getByName(categoryOld.getName())).willReturn(categoryOld);
        given(categoryRepository.getByName(categoryNew.getName())).willReturn(categoryNew);

        Item item = new Item();
        item.setName("some name");
        item.setId(1L);
        item.setCategory(categoryOld);
        given(itemRepository.findById(1L)).willReturn(java.util.Optional.of(item));

        ItemDTO itemDTO = new ItemDTO(1L, true, categoryNew.getName(), "new comment", true,
                "new name", true);

        mockMvc.perform(
                patch("/items/1")
                        .content(asJsonString(itemDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Item resultItem = itemMapper.itemDTOToItem(itemDTO, categoryNew);
        then(itemRepository).should(times(1)).save(resultItem);
    }

    @Test
    void shouldErrorWhenEditNonexistentItem() throws Exception {
        mockMvc.perform(
                patch("/items/1")
                        .content(asJsonString(new ItemDTO()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteItem() throws Exception {
        mockMvc.perform(delete("/items/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

        then(itemRepository).should(times(1)).deleteById(1L);
    }

    @Test
    void setAllNotBought() throws Exception {
        Item item1 = new Item();
        item1.setBought(true);
        Item item2 = new Item();
        item1.setBought(false);
        List<Item> items = List.of(item1, item2);
        given(itemRepository.findByBought(true)).willReturn(items);

        mockMvc.perform(patch("/items/all-not-bought"))
                .andDo(print())
                .andExpect(status().isNoContent());

        then(itemRepository).should(times(1)).saveAll(items);
    }

    @SneakyThrows
    private String asJsonString(final ItemDTO itemDTO) {
        return new ObjectMapper().writeValueAsString(itemDTO);
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public CategoryService categoryService(CategoryRepository categoryRepository) {
            return new CategoryService(categoryRepository);
        }

        @Bean
        public ItemService itemService(ItemRepository itemRepository) {
            return new ItemService(itemRepository);
        }

        @Bean
        public ItemMapper itemMapper() {
            return new ItemMapperImpl();
        }
    }
}