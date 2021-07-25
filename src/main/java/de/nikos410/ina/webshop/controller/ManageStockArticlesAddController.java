package de.nikos410.ina.webshop.controller;

import de.nikos410.ina.webshop.controller.helper.ControllerHelper;
import de.nikos410.ina.webshop.controller.helper.ManageStockArticlesAddControllerHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ManageStockArticlesAddController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final ControllerHelper helper = new ManageStockArticlesAddControllerHelper(request, response);
        helper.doPost();
    }
}
