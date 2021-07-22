package de.nikos410.ina.webshop.init;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * This data initializer automatically runs after the servlet context is initialized.
 */
public class DeferredDataInitializer extends DataInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent ignored) {
        initializeData();
    }
}
