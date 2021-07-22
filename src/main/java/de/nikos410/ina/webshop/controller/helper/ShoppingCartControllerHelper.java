package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.model.ShoppingCartArticle;
import de.nikos410.ina.webshop.model.entity.StockArticle;
import de.nikos410.ina.webshop.repository.InMemoryStockArticleRepository;
import de.nikos410.ina.webshop.repository.StockArticleRepository;
import de.nikos410.ina.webshop.util.BigDecimalUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import static java.util.Objects.isNull;

public class ShoppingCartControllerHelper extends ControllerHelper {

    public static final String SHOPPING_CART_ATTRIBUTE_NAME = "shoppingCart";
    public static final String SELECTED_ARTICLE_PARAMETER_NAME = "article";
    public static final String SELECTED_QUANTITY_PARAMETER_NAME = "quantity";

    private final StockArticleRepository stockArticleRepository = InMemoryStockArticleRepository.getInstance();

    public ShoppingCartControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doPost() throws IOException {
        requireSessionAuthenticated();

        final Optional<StockArticle> selectedArticle = getSelectedArticle();
        if (selectedArticle.isEmpty()) {
            redirect("/shop?error=Unknown%20article.");
            return;
        }

        final Optional<BigDecimal> selectedQuantity = getSelectedQuantity();
        if (selectedQuantity.isEmpty()) {
            redirect("/shop?error=Quantity%20parameter%20missing.");
            return;
        }

        doPost(selectedArticle.get(), selectedQuantity.get());
    }

    private void doPost(StockArticle selectedArticle, BigDecimal selectedQuantity) throws IOException {

        if (BigDecimalUtils.isLessThan(selectedArticle.getStockQuantity(), selectedQuantity)) {
            redirect("/shop?error=Insufficient%20stock%20quantity.");
            return;
        }

        final ShoppingCartArticle shoppingCartArticle = new ShoppingCartArticle(selectedArticle, selectedQuantity);
        getOrCreateShoppingCart().add(shoppingCartArticle);

        redirect("/shop");
    }

    private Optional<StockArticle> getSelectedArticle() {
        return Optional.ofNullable(getRequest().getParameter(SELECTED_ARTICLE_PARAMETER_NAME))
                .map(Long::parseLong)
                .flatMap(stockArticleRepository::findOneById);
    }

    private Optional<BigDecimal> getSelectedQuantity() {
        return Optional.ofNullable(getRequest().getParameter(SELECTED_QUANTITY_PARAMETER_NAME))
                .map(BigDecimal::new);
    }

    private Collection<ShoppingCartArticle> getOrCreateShoppingCart() {
        Collection<ShoppingCartArticle> shoppingCart = getSessionAttribute(SHOPPING_CART_ATTRIBUTE_NAME, Collection.class);
        if (isNull(shoppingCart)) {
            shoppingCart = new LinkedList<>();
            setSessionAttribute(SHOPPING_CART_ATTRIBUTE_NAME, shoppingCart);
        }

        return shoppingCart;
    }
}