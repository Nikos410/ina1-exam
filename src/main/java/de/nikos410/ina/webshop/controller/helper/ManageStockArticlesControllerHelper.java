package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.repository.InMemoryStockArticleRepository;
import de.nikos410.ina.webshop.repository.StockArticleRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class ManageStockArticlesControllerHelper extends ControllerHelper {

    public static final String ALL_ARTICLES_ATTRIBUTE_NAME = "allArticles";

    private final StockArticleRepository stockArticleRepository = InMemoryStockArticleRepository.getInstance();

    public ManageStockArticlesControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doGet() throws ServletException, IOException {
        requireSessionAuthenticated("admin");

        setSessionAttribute(ALL_ARTICLES_ATTRIBUTE_NAME, stockArticleRepository.findAll());

        forward("/de/nikos410/ina/webshop/manage-stock-articles.jsp");
    }
}
