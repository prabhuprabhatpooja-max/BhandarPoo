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
    void homeShowsOnlyTheTwoProducts() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.allOf(
                        Matchers.containsString("Our Products"),
                        Matchers.containsString("Prabhu Prabhat 100% Pure Camphor (50 gm Box)"),
                        Matchers.containsString("Prabhu Prabhat Dia Baati (100 pcs)"))));
    }

    @Test
    void showsBusinessContactDetails() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.allOf(
                        Matchers.containsString("mailto:prabhuprabhatpooja@gmail.com"),
                        Matchers.containsString("tel:+919310321014"),
                        Matchers.containsString("S206, Kapil Vihar, Sector-21C"),
                        Matchers.containsString("Faridabad, Haryana – 121001"),
                        Matchers.containsString("https://www.instagram.com/prabhuprabhatpooja/"),
                        Matchers.containsString("https://www.facebook.com/prabhuprabhatpooja"))));
    }

    @Test
    void removedSectionsAreGone() throws Exception {
        for (String path : new String[] {"/", "/products"}) {
            mockMvc.perform(get(path))
                    .andExpect(status().isOk())
                    .andExpect(content().string(Matchers.not(Matchers.anyOf(
                            Matchers.containsStringIgnoringCase("cart"),
                            Matchers.containsStringIgnoringCase("online puja"),
                            Matchers.containsStringIgnoringCase("knowledge hub"),
                            Matchers.containsString("About Us"),
                            Matchers.containsStringIgnoringCase("youtube"),
                            Matchers.containsString("Privacy Policy"),
                            Matchers.containsString("Shop by Category")))));
        }
    }

    @Test
    void productsPageShowsDiscountedPrices() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.allOf(
                        Matchers.containsString("₹150"),
                        Matchers.containsString("25% OFF"),
                        Matchers.containsString("₹200"),
                        Matchers.containsString("50% OFF"),
                        Matchers.containsString("/images/products/prabhu-prabhat-camphor.svg"))));
    }

    @Test
    void healthReturnsUp() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }
}
