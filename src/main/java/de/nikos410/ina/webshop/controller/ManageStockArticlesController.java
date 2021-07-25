package de.nikos410.ina.webshop.controller;

import de.nikos410.ina.webshop.controller.helper.ManageStockArticlesControllerHelper;
import de.nikos410.ina.webshop.controller.helper.ControllerHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ManageStockArticlesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final ControllerHelper helper = new ManageStockArticlesControllerHelper(request, response);
        helper.doGet();
    }
}
