package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.model.entity.StockArticle;
import de.nikos410.ina.webshop.repository.InMemoryStockArticleRepository;
import de.nikos410.ina.webshop.repository.StockArticleRepository;
import de.nikos410.ina.webshop.util.BigDecimalUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;


public class ManageStockArticlesChangeQuantityControllerHelper extends ControllerHelper {

    public static final String ARTICLE_PARAMETER_NAME = "article";
    public static final String QUANTITY_PARAMETER_NAME = "quantity";

    private final StockArticleRepository stockArticleRepository = InMemoryStockArticleRepository.getInstance();

    public ManageStockArticlesChangeQuantityControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doPost() throws ServletException, IOException {
        requireSessionAuthenticated("admin");

        final StockArticle stockArticle = getStockArticle();
        final BigDecimal quantity = getQuantity();
        if (BigDecimalUtils.isLessThan(quantity, ZERO)) {
            redirect("/manage-stock-articles?error=Quantity%20must%20not%20be%20negative.");
        } else {
            stockArticle.setStockQuantity(quantity);
            stockArticleRepository.update(stockArticle);
            redirect("/manage-stock-articles");
        }

    }

    private StockArticle getStockArticle() {
        final long stockArticleID = getStockArticleId();
        return stockArticleRepository.findOneById(stockArticleID)
                .orElseThrow(() -> new IllegalArgumentException("Unknown stock article."));
    }

    private long getStockArticleId() {
        final String articleParameter = getRequest().getParameter(ARTICLE_PARAMETER_NAME);
        return Long.parseLong(articleParameter);
    }

    private BigDecimal getQuantity() {
        final String quantity = getRequest().getParameter(QUANTITY_PARAMETER_NAME);
        return new BigDecimal(quantity);
    }
}
