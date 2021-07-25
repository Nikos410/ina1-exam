package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.repository.InMemoryStockArticleRepository;
import de.nikos410.ina.webshop.repository.StockArticleRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ManageStockArticlesDeleteControllerHelper extends ControllerHelper {

    public static final String ARTICLE_PARAMETER_NAME = "article";

    private final StockArticleRepository stockArticleRepository = InMemoryStockArticleRepository.getInstance();

    public ManageStockArticlesDeleteControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doPost() throws ServletException, IOException {
        requireSessionAuthenticated("admin");

        final long stockArticleId = getStockArticleId();
        stockArticleRepository.remove(stockArticleId);

        redirect("/manage-stock-articles");
    }

    private long getStockArticleId() {
        final String articleParameter = getRequest().getParameter(ARTICLE_PARAMETER_NAME);
        return Long.parseLong(articleParameter);
    }
}
