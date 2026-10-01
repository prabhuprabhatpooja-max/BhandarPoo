package com.example.bhandarpoo.catalog;

/**
 * A product for sale. Prices are in whole rupees; {@code mrp} is the original price shown struck through.
 * {@code image} is a path under {@code static/} (e.g. "/images/products/x.svg"), or null to show the category icon.
 */
public record Product(int id, String name, Category category, int price, int mrp, String image) {

    public Product(int id, String name, Category category, int price, int mrp) {
        this(id, name, category, price, mrp, null);
    }

    public boolean onSale() {
        return mrp > price;
    }

    public int discountPercent() {
        return onSale() ? Math.round((mrp - price) * 100f / mrp) : 0;
    }
}
