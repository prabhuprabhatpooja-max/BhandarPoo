package com.example.bhandarpoo.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.example.bhandarpoo.catalog.CatalogService;

@WebMvcTest({HomeController.class, ApiController.class})
@Import(CatalogService.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homeShowsAllSections() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.allOf(
                        Matchers.containsString("Shop by Category"),
                        Matchers.containsString("Latest Products"),
                        Matchers.containsString("Online Puja Services"),
                        Matchers.containsString("Knowledge Hub"))));
    }

    @Test
    void productsFilteredByCategory() throws Exception {
        mockMvc.perform(get("/products").param("category", "attar"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Rose Attar")))
                .andExpect(content().string(Matchers.not(Matchers.containsString("Tulsi Japa Mala"))));
    }

    @Test
    void prabhuPrabhatProductsShowDiscountedPrices() throws Exception {
        mockMvc.perform(get("/products").param("category", "puja-items"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.allOf(
                        Matchers.containsString("Prabhu Prabhat 100% Pure Camphor (50 gm Box)"),
                        Matchers.containsString("₹150"),
                        Matchers.containsString("25% OFF"),
                        Matchers.containsString("Prabhu Prabhat Dia Baati (100 pcs)"),
                        Matchers.containsString("50% OFF"),
                        Matchers.containsString("/images/products/prabhu-prabhat-camphor.svg"))));
    }

    @Test
    void unknownCategoryIsNotFound() throws Exception {
        mockMvc.perform(get("/products").param("category", "nope"))
                .andExpect(status().isNotFound());
    }

    @Test
    void healthReturnsUp() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }
}
