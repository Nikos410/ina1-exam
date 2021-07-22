package de.nikos410.ina.webshop.model;

import de.nikos410.ina.webshop.model.entity.StockArticle;

import java.math.BigDecimal;

public record ShoppingCartArticle(StockArticle article, BigDecimal quantity) {
}
