package com.example.bhandarpoo.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.bhandarpoo.catalog.Category;
import com.example.bhandarpoo.catalog.CatalogService;

@Controller
public class HomeController {

    private final CatalogService catalog;

    public HomeController(CatalogService catalog) {
        this.catalog = catalog;
    }

    /** Categories are needed by the shared header menu on every page. */
    @ModelAttribute("categories")
    public Object categories() {
        return catalog.categories();
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("latestProducts", catalog.latestProducts(8));
        model.addAttribute("pujaServices", catalog.pujaServices());
        model.addAttribute("blogPosts", catalog.blogPosts());
        return "index";
    }

    @GetMapping("/products")
    public String products(@RequestParam(required = false) String category, Model model) {
        if (category == null) {
            model.addAttribute("title", "All Products");
            model.addAttribute("products", catalog.allProducts());
        } else {
            Category selected = catalog.findCategory(category)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown category"));
            model.addAttribute("title", selected.name());
            model.addAttribute("selected", selected);
            model.addAttribute("products", catalog.productsIn(selected));
        }
        return "products";
    }
}
