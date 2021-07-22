package de.nikos410.ina.webshop.repository;

import de.nikos410.ina.webshop.model.entity.StockArticle;

import java.math.BigDecimal;
import java.util.Set;

import static de.nikos410.ina.webshop.util.BigDecimalUtils.isGreaterThan;
import static java.util.stream.Collectors.toUnmodifiableSet;

public class InMemoryStockArticleRepository extends InMemoryRepository<StockArticle> implements StockArticleRepository{

    private static final InMemoryStockArticleRepository INSTANCE = new InMemoryStockArticleRepository();

    public static StockArticleRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Set<StockArticle> findAllAvailable() {
        return content.stream()
                .filter(this::isAvailable)
                .collect(toUnmodifiableSet());
    }

    private boolean isAvailable(StockArticle stockArticle) {
        final BigDecimal stockQuantity = stockArticle.getStockQuantity();
        return isGreaterThan(stockQuantity, BigDecimal.ZERO);
    }

}
