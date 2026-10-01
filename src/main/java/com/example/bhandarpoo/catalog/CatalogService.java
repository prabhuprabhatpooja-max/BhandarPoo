package com.example.bhandarpoo.catalog;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * In-memory product catalog. Replace with a database-backed implementation when inventory grows.
 */
@Service
public class CatalogService {

    private static final Category PUJA_ITEMS = new Category("puja-items", "Puja Items", "🪔");

    private final List<Product> products = List.of(
            new Product(1, "Prabhu Prabhat 100% Pure Camphor (50 gm Box)", PUJA_ITEMS, 150, 200,
                    "/images/products/prabhu-prabhat-camphor.svg"),
            new Product(2, "Prabhu Prabhat Dia Baati (100 pcs)", PUJA_ITEMS, 200, 400,
                    "/images/products/prabhu-prabhat-dia-baati.svg"));

    public List<Product> products() {
        return products;
    }
}
