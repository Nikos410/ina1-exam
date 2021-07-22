package de.nikos410.ina.webshop.model;

import java.math.BigDecimal;

public record ShoppingCartArticle(long articleId, BigDecimal quantity) {
}
