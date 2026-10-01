package com.example.bhandarpoo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bhandarpoo.catalog.CatalogService;

@Controller
public class HomeController {

    private final CatalogService catalog;

    public HomeController(CatalogService catalog) {
        this.catalog = catalog;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", catalog.products());
        return "index";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", catalog.products());
        return "products";
    }
}
