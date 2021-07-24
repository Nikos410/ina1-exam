package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.model.ShoppingCartArticle;
import de.nikos410.ina.webshop.model.entity.StockArticle;
import de.nikos410.ina.webshop.repository.InMemoryStockArticleRepository;
import de.nikos410.ina.webshop.repository.StockArticleRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

import static de.nikos410.ina.webshop.controller.helper.ShoppingCartControllerHelper.SHOPPING_CART_ATTRIBUTE_NAME;
import static java.util.Objects.isNull;

public class OrderControllerHelper extends ControllerHelper {

    private final StockArticleRepository stockArticleRepository = InMemoryStockArticleRepository.getInstance();

    public OrderControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doPost() throws IOException {
        requireSessionAuthenticated();

        final Collection<ShoppingCartArticle> shoppingCart = getShoppingCart();
        if (isNull(shoppingCart) || shoppingCart.isEmpty()) {
            redirect("/shop?error=Shopping%20cart%20is%20empty.");
            return;
        }

        shoppingCart.clear();

        redirect("/shop?ordered");
    }

    private Collection<ShoppingCartArticle> getShoppingCart() {
        return getSessionAttribute(SHOPPING_CART_ATTRIBUTE_NAME, Collection.class);
    }
}