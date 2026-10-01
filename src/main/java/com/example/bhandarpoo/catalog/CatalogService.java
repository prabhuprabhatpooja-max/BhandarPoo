package com.example.bhandarpoo.catalog;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * In-memory sample catalog. Replace with a database-backed implementation when real inventory is available.
 */
@Service
public class CatalogService {

    private static final Category KADA = new Category("kada", "Bracelets (Kada)", "📿");
    private static final Category PUJA_ITEMS = new Category("puja-items", "Puja Items", "🪔");
    private static final Category CLOTHING = new Category("clothing", "Clothing", "🧣");
    private static final Category MALAS = new Category("malas", "Malas", "📿");
    private static final Category INCENSE = new Category("incense", "Incense & Dhoop", "🕯️");
    private static final Category ATTAR = new Category("attar", "Attar", "🌸");
    private static final Category BOOKS = new Category("books", "Books", "📖");
    private static final Category MUSIC = new Category("music", "Music", "🎵");

    private final List<Category> categories =
            List.of(KADA, PUJA_ITEMS, CLOTHING, MALAS, INCENSE, ATTAR, BOOKS, MUSIC);

    // Own-brand products are listed first so they lead every category page.
    private final List<Product> products = List.of(
            new Product(17, "Prabhu Prabhat 100% Pure Camphor (50 gm Box)", PUJA_ITEMS, 150, 200,
                    "/images/products/prabhu-prabhat-camphor.svg"),
            new Product(18, "Prabhu Prabhat Dia Baati (100 pcs)", PUJA_ITEMS, 200, 400,
                    "/images/products/prabhu-prabhat-dia-baati.svg"),
            new Product(1, "Brass Diya Set (Pack of 5)", PUJA_ITEMS, 349, 449),
            new Product(2, "Copper Kalash with Lid", PUJA_ITEMS, 699, 699),
            new Product(3, "Brass Ganesh Murti (6 inch)", PUJA_ITEMS, 1575, 1899),
            new Product(4, "Rudraksha Mala (108 Beads)", MALAS, 499, 650),
            new Product(5, "Tulsi Japa Mala", MALAS, 210, 210),
            new Product(6, "Silver-Plated Kada", KADA, 899, 1100),
            new Product(7, "Pure Copper Kada", KADA, 399, 399),
            new Product(8, "Woollen Ram Naam Shawl", CLOTHING, 1250, 1500),
            new Product(9, "Cotton Dhoti Kurta Set", CLOTHING, 1099, 1299),
            new Product(10, "Sandalwood Agarbatti (100 sticks)", INCENSE, 220, 250),
            new Product(11, "Guggal Dhoop Cones", INCENSE, 180, 180),
            new Product(12, "Rose Attar (10 ml)", ATTAR, 450, 520),
            new Product(13, "Kewda Attar (10 ml)", ATTAR, 480, 480),
            new Product(14, "Shrimad Bhagavad Gita (Hindi)", BOOKS, 299, 350),
            new Product(15, "Sundar Kand Path Book", BOOKS, 120, 120),
            new Product(16, "Bhajan Sangrah (USB)", MUSIC, 399, 499));

    private final List<PujaService> pujaServices = List.of(
            new PujaService("Hawan", "Sacred fire ritual for purification and blessings.", 2100, "🔥"),
            new PujaService("Parthiv Shivling Puja", "Worship of a clay Shivling crafted for the ritual.", 3100, "🔱"),
            new PujaService("Sundar Kand Path", "Recitation of Sundar Kand for strength and courage.", 2500, "🙏"),
            new PujaService("Ramayan Path", "Complete recitation by learned pandits.", 5100, "📜"),
            new PujaService("Lakshmi Puja", "Invoke prosperity and abundance for your home.", 2100, "🪷"),
            new PujaService("Satyanarayan Katha", "Traditional katha for gratitude and well-being.", 1800, "🌼"),
            new PujaService("Navgraha Shanti", "Puja to balance the influence of the nine planets.", 4100, "🪐"),
            new PujaService("Griha Pravesh Puja", "Blessings for a new home before moving in.", 3500, "🏠"));

    private final List<BlogPost> blogPosts = List.of(
            new BlogPost("Why We Light a Diya at Dusk",
                    "The meaning behind the evening lamp and how to set up your home mandir.",
                    LocalDate.of(2026, 9, 18), "🪔"),
            new BlogPost("A Beginner's Guide to Japa with a Mala",
                    "Choosing a mala, counting 108 beads, and building a daily practice.",
                    LocalDate.of(2026, 9, 5), "📿"),
            new BlogPost("Preparing for Navratri at Home",
                    "Essential puja samagri and a simple day-by-day checklist.",
                    LocalDate.of(2026, 8, 27), "🌺"));

    public List<Category> categories() {
        return categories;
    }

    public Optional<Category> findCategory(String slug) {
        return categories.stream().filter(c -> c.slug().equals(slug)).findFirst();
    }

    public List<Product> allProducts() {
        return products;
    }

    public List<Product> latestProducts(int limit) {
        return products.stream().sorted((a, b) -> b.id() - a.id()).limit(limit).toList();
    }

    public List<Product> productsIn(Category category) {
        return products.stream().filter(p -> p.category().equals(category)).toList();
    }

    public List<PujaService> pujaServices() {
        return pujaServices;
    }

    public List<BlogPost> blogPosts() {
        return blogPosts;
    }
}
