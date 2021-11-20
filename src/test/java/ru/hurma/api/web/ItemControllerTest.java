package ru.hurma.api.web;

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
import ru.hurma.api.CategoryService;
import ru.hurma.api.ItemService;
import ru.hurma.api.data.Category;
import ru.hurma.api.data.CategoryRepository;
import ru.hurma.api.data.Item;
import ru.hurma.api.data.ItemRepository;

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

    @Test
    void shouldCreateItem() throws Exception {
        Category category = new Category("Fruits", "fruits");
        given(categoryRepository.findByName("fruit")).willReturn(category);

        Item item = new Item();
        item.setName("name");
        item.setCategory(category);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/ajax/items")
                                .content(asJsonString(item))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        // TODO check response

        then(itemRepository).should(times(1)).save(item);
    }

    @Test
    void shouldReturnErrorWhenCreateItemNoName() throws Exception {
        Category category = new Category("Fruits", "fruits");
        given(categoryRepository.findByName("fruit")).willReturn(category);

        Item item = new Item();
        item.setCategory(category);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/ajax/items")
                                .content(asJsonString(item))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isExpectationFailed());
    }

    @Test
    void shouldReturnErrorWhenCreateItemWrongCategory() throws Exception {
        Item item = new Item();
        item.setCategory(null);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/ajax/items")
                                .content(asJsonString(item))
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

        Item repoItem = new Item();
        repoItem.setName("some name");
        repoItem.setId(1L);
        repoItem.setCategory(categoryOld);
        given(itemRepository.findById(1L)).willReturn(java.util.Optional.of(repoItem));

        Item item = new Item(1L, categoryNew.getName(), "new comment", true,
                categoryNew, true,true);

        mockMvc.perform(
                        patch("/ajax/items/1")
                                .content(asJsonString(item))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // TODO check response

        then(itemRepository).should(times(1)).save(item);
    }

    @Test
    void shouldErrorWhenEditNonexistentItem() throws Exception {
        mockMvc.perform(
                        patch("/ajax/items/1")
                                .content(asJsonString(new Item()))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteItem() throws Exception {
        mockMvc.perform(delete("/ajax/items/1"))
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

        mockMvc.perform(patch("/ajax/items/all-not-bought"))
                .andDo(print())
                .andExpect(status().isNoContent());

        then(itemRepository).should(times(1)).saveAll(items);
    }

    @SneakyThrows
    private String asJsonString(final Item item) {
        return new ObjectMapper().writeValueAsString(item);
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
    }
}
