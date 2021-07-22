package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.repository.InMemoryStockArticleRepository;
import de.nikos410.ina.webshop.repository.StockArticleRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class ShopControllerHelper extends ControllerHelper {

    public static final String AVAILABLE_ARTICLES_ATTRIBUTE_NAME = "availableArticles";

    private final StockArticleRepository stockArticleRepository = InMemoryStockArticleRepository.getInstance();

    public ShopControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doGet() throws ServletException, IOException {
        requireSessionAuthenticated();

        setSessionAttribute(AVAILABLE_ARTICLES_ATTRIBUTE_NAME, stockArticleRepository.findAllAvailable());
        forward("/de/nikos410/ina/webshop/shop.jsp");
    }
}
