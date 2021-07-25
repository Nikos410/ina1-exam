package de.nikos410.ina.webshop.model.entity;

import java.math.BigDecimal;

public class StockArticle extends BaseEntity {

    private String name;
    private String description;
    // quantity allows decimal values, since it is possible for a shop to deal in goods that are not countable.
    // For example, a shop might have 2.5 liters of some liquid in stock.
    private BigDecimal stockQuantity;

    public StockArticle() {
    }

    public StockArticle(String name, String description, BigDecimal stockQuantity) {
        this.name = name;
        this.description = description;
        this.stockQuantity = stockQuantity;
    }

    @Override
    protected boolean canEquals(BaseEntity other) {
        return other instanceof StockArticle;
    }

    @Override
    public String toString() {
        // auto-generated
        return "Article{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stockQuantity=" + stockQuantity +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(BigDecimal stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
