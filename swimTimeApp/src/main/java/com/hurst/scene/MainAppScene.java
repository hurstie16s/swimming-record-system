package com.hurst.scene;

import com.hurst.ui.AppPane;
import com.hurst.ui.AppWindow;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Main app scene.
 */
public class MainAppScene extends BaseScene {

    private static final Logger logger = LogManager.getLogger(MainAppScene.class);

    private String username;

    /**
     * Instantiates a new Main app scene.
     *
     * @param appWindow the app window
     * @param username  the username
     */
    public MainAppScene(AppWindow appWindow, String username) {
        super(appWindow);
        logger.info("Creating Main App Scene");
        this.username = username;
    }

    @Override
    public void initialise() {}

    @Override
    public void build() {

        logger.info("Building "+this.getClass().getName());

        root = new AppPane(appWindow.getWidth(), appWindow.getHeight());

        var mainAppPane = rootSetUp("app-background");
        root.getChildren().add(mainAppPane);
    }

}
