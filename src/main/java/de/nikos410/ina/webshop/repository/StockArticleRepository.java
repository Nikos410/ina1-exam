package de.nikos410.ina.webshop.repository;

import de.nikos410.ina.webshop.model.entity.StockArticle;

import java.util.Collection;
import java.util.Optional;

public interface StockArticleRepository extends Repository<StockArticle> {

    /**
     * Returns all articles that have a stock quantity larger than zero.
     *
     * @return All articles that have a stock quantity larger than zero.
     */
    Collection<StockArticle> findAllAvailable();

    /**
     * Returns the article with the given name, if one exists.
     *
     * @param name The name of the article to return
     * @return An optional containing the article with the given name if it exists. Empty otherwise.
     */
    Optional<StockArticle> findOneByName(String name);
}
