package de.nikos410.ina.webshop.repository;

import de.nikos410.ina.webshop.model.entity.StockArticle;

import java.util.Collection;

public interface StockArticleRepository extends Repository<StockArticle> {

    /**
     * Returns all articles that have a stock quantity larger than zero.
     *
     * @return All articles that have a stock quantity larger than zero.
     */
    Collection<StockArticle> findAllAvailable();
}
