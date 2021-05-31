package ru.pyatka.api.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import ru.pyatka.api.CategoryService;
import ru.pyatka.api.data.Category;
import ru.pyatka.api.data.CategoryMapper;
import ru.pyatka.api.data.CategoryMapperImpl;
import ru.pyatka.api.data.CategoryRepository;
import ru.pyatka.api.data.Item;
import ru.pyatka.api.data.ItemRepository;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryRepository categoryRepo;

    @MockBean
    private ItemRepository itemRepo;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public CategoryMapper categoryMapper() {
            return new CategoryMapperImpl();
        }

        @Bean
        public CategoryService categoryService(CategoryRepository categoryRepository, ItemRepository itemRepository) {
            return new CategoryService(categoryRepository, itemRepository);
        }
    }

    @Test
    void getCategories() throws Exception {
        Category fruitCategory = new Category("Fruits", "fruits");
        Category vegCategory = new Category("Vegetables", "vegetables");

        Item apple = new Item("Apple");
        apple.setCategory(fruitCategory);
        apple.setId(1L);
        Item lemon = new Item("Lemon");
        lemon.setCategory(fruitCategory);
        lemon.setId(2L);
        Item tomato = new Item("Tomato");
        tomato.setCategory(vegCategory);
        tomato.setId(3L);

        fruitCategory.setItems(List.of(apple, lemon));
        fruitCategory.setId(4L);
        vegCategory.setItems(List.of(tomato));
        vegCategory.setId(5L);

        given(categoryRepo.findAll()).willReturn(List.of(fruitCategory, vegCategory));

        mockMvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id", is(fruitCategory.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(fruitCategory.getName())))
                .andExpect(jsonPath("$[0].fullName", is(fruitCategory.getFullName())))
                .andExpect(jsonPath("$[0].position", is(fruitCategory.getPosition())))
                .andExpect(jsonPath("$[0].items").isArray())

                .andExpect(jsonPath("$[0].items[0].id", is(apple.getId().intValue())))
                .andExpect(jsonPath("$[0].items[0].bought", is(apple.getBought())))
                .andExpect(jsonPath("$[0].items[0].category", is(apple.getCategory().getName())))
                .andExpect(jsonPath("$[0].items[0].comment", is(apple.getComment())))
                .andExpect(jsonPath("$[0].items[0].important", is(apple.isImportant())))
                .andExpect(jsonPath("$[0].items[0].name", is(apple.getName())))
                .andExpect(jsonPath("$[0].items[0].needed", is(apple.isNeeded())))
//
                .andExpect(jsonPath("$[0].items[1].id", is(lemon.getId().intValue())))
                .andExpect(jsonPath("$[0].items[1].bought", is(lemon.getBought())))
                .andExpect(jsonPath("$[0].items[1].category", is(lemon.getCategory().getName())))
                .andExpect(jsonPath("$[0].items[1].comment", is(lemon.getComment())))
                .andExpect(jsonPath("$[0].items[1].important", is(lemon.isImportant())))
                .andExpect(jsonPath("$[0].items[1].name", is(lemon.getName())))
                .andExpect(jsonPath("$[0].items[1].needed", is(lemon.isNeeded())))

                .andExpect(jsonPath("$[1].id", is(vegCategory.getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(vegCategory.getName())))
                .andExpect(jsonPath("$[1].fullName", is(vegCategory.getFullName())))
                .andExpect(jsonPath("$[1].position", is(vegCategory.getPosition())))
                .andExpect(jsonPath("$[1].items").isArray())

                .andExpect(jsonPath("$[1].items[0].id", is(tomato.getId().intValue())))
                .andExpect(jsonPath("$[1].items[0].bought", is(tomato.getBought())))
                .andExpect(jsonPath("$[1].items[0].category", is(tomato.getCategory().getName())))
                .andExpect(jsonPath("$[1].items[0].comment", is(tomato.getComment())))
                .andExpect(jsonPath("$[1].items[0].important", is(tomato.isImportant())))
                .andExpect(jsonPath("$[1].items[0].name", is(tomato.getName())))
                .andExpect(jsonPath("$[1].items[0].needed", is(tomato.isNeeded())));
    }
}