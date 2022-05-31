package com.hurst;

import com.hurst.sql.SqlCommandRunner;
import com.hurst.ui.AppWindow;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static final Logger logger = LogManager.getLogger(App.class);
    private static App instance;
    private final int width = 1600;
    private final int height = 1200;
    private Stage stage;

    public static void main(String[] args) {
        logger.info("starting Application");
        launch();
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) {
        instance = this;
        this.stage = stage;

        try {
            SqlCommandRunner.initialiseSqlConnection("jdbc:sqlite:identifier.sqlite");
            logger.info("Connection to Database Successful");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            System.exit(1);
        }

        openApp();
    }

    public void openApp() {
        logger.info("Opening App Window");

        var appWindow = new AppWindow(stage, width, height);

        stage.show();
    }

    public void shutdown() {
        logger.info("Shutting Down");
        System.exit(0);
    }
}