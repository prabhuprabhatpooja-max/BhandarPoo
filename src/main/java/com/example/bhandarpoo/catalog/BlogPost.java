package com.example.bhandarpoo.catalog;

import java.time.LocalDate;

/** A short article for the knowledge hub section. */
public record BlogPost(String title, String excerpt, LocalDate date, String icon) {
}
