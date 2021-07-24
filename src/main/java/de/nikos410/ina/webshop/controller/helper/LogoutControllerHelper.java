package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.model.ShoppingCartArticle;
import de.nikos410.ina.webshop.model.entity.StockArticle;
import de.nikos410.ina.webshop.repository.InMemoryStockArticleRepository;
import de.nikos410.ina.webshop.repository.StockArticleRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

import static de.nikos410.ina.webshop.controller.helper.ShoppingCartControllerHelper.SHOPPING_CART_ATTRIBUTE_NAME;
import static de.nikos410.ina.webshop.util.AuthenticationUtils.AUTHENTICATED_USER_ATTRIBUTE_NAME;
import static java.util.Objects.nonNull;

public class LogoutControllerHelper extends ControllerHelper {

    private final StockArticleRepository stockArticleRepository = InMemoryStockArticleRepository.getInstance();

    public LogoutControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doGet() throws IOException {
        clearShoppingCart();
        clearSession();

        redirect("/login");
    }

    private void clearSession() {
        setSessionAttribute(AUTHENTICATED_USER_ATTRIBUTE_NAME, null);
    }

    private void clearShoppingCart() {
        final Collection<ShoppingCartArticle> shoppingCart = getShoppingCart();
        if (nonNull(shoppingCart)) {
            shoppingCart.forEach(this::increaseStockQuantity);
            shoppingCart.clear();
        }
    }

    private Collection<ShoppingCartArticle> getShoppingCart() {
        return getSessionAttribute(SHOPPING_CART_ATTRIBUTE_NAME, Collection.class);
    }

    private void increaseStockQuantity(ShoppingCartArticle shoppingCartArticle) {
        final StockArticle stockArticle = stockArticleRepository.findOneById(shoppingCartArticle.article().getId())
                .orElseThrow(() -> new IllegalStateException("Unknown article in shopping cart."));
        increaseStockQuantity(stockArticle, shoppingCartArticle.quantity());
    }

    private void increaseStockQuantity(StockArticle stockArticle, BigDecimal by) {
        final BigDecimal increasedStockQuantity = stockArticle.getStockQuantity().add(by);
        stockArticle.setStockQuantity(increasedStockQuantity);
        stockArticleRepository.update(stockArticle);
    }
}
