package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.model.entity.StockArticle;
import de.nikos410.ina.webshop.repository.InMemoryStockArticleRepository;
import de.nikos410.ina.webshop.repository.StockArticleRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;


public class ManageStockArticlesAddControllerHelper extends ControllerHelper {

    public static final String NAME_PARAMETER_NAME = "name";
    public static final String DESCRIPTION_PARAMETER_NAME = "description";
    public static final String QUANTITY_PARAMETER_NAME = "quantity";

    private final StockArticleRepository stockArticleRepository = InMemoryStockArticleRepository.getInstance();

    public ManageStockArticlesAddControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doPost() throws ServletException, IOException {
        requireSessionAuthenticated("admin");

        final StockArticle newStockArticle = getStockArticleFromRequest();
        if (isNewArticle(newStockArticle)) {
            stockArticleRepository.add(newStockArticle);
            redirect("/manage-stock-articles");
        } else {
            redirect("/manage-stock-articles?error=An%20article%20with%20that%20name%20already%20exists.");
        }
    }

    private StockArticle getStockArticleFromRequest() {
        final HttpServletRequest request = getRequest();
        final String name = request.getParameter(NAME_PARAMETER_NAME);
        final String description = request.getParameter(DESCRIPTION_PARAMETER_NAME);
        final BigDecimal quantity = new BigDecimal(request.getParameter(QUANTITY_PARAMETER_NAME));

        return new StockArticle(name, description, quantity);
    }

    private boolean isNewArticle(StockArticle stockArticle) {
        final String name = stockArticle.getName();
        return stockArticleRepository.findOneByName(name)
                .isEmpty();
    }
}
