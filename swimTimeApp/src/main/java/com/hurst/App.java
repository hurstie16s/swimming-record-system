package com.hurst;

import com.hurst.sql.SqlCommandRunner;
import com.hurst.ui.AppWindow;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 * The type App.
 */
public class App extends Application {

    private static final Logger logger = LogManager.getLogger(App.class);
    private static App instance;
    private final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    private final int width = (int) screenBounds.getWidth() - 10;
    private final int height = (int) (screenBounds.getWidth() * 0.5);
    private Stage stage;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        logger.info("starting Application");
        launch();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
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

    /**
     * Open app.
     */
    public void openApp() {
        logger.info("Opening App Window");

        var appWindow = new AppWindow(stage, width, height);

        stage.show();
    }

    /**
     * Shutdown.
     */
    public void shutdown() {
        logger.info("Shutting Down");
        System.exit(0);
    }
}