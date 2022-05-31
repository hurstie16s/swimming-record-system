package com.hurst.ui;

import com.hurst.App;
import com.hurst.scene.BaseScene;
import com.hurst.scene.MenuScene;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppWindow {

    private static final Logger logger = LogManager.getLogger(AppWindow.class);

    private final int width;
    private final int height;
    private final Stage stage;
    private BaseScene currentScene;
    private Scene scene;

    public AppWindow(Stage stage, int width, int height) {
        this.width = width;
        this.height = height;

        this.stage = stage;

        setUpStage();

        setUpResources();

        setUpDefaultScene();

        startMenu();
    }

    public Scene getScene() {
        return scene;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void setUpResources() {
        Font.loadFont(getClass().getResourceAsStream("/style/Orbitron-Regular.ttf"), 32);
        Font.loadFont(getClass().getResourceAsStream("/style/Orbitron-Bold.ttf"), 32);
        Font.loadFont(getClass().getResourceAsStream("/style/Orbitron-ExtraBold.ttf"), 32);
    }

    public void setUpStage() {
        stage.setTitle("Swim Record System");
        stage.setMinWidth(width);
        stage.setMinHeight(height + 20);
        stage.setOnCloseRequest(event -> App.getInstance().shutdown());
    }

    public void loadScene(BaseScene newScene) {

        // Create and set up new scene
        newScene.build();
        currentScene = newScene;
        scene = newScene.setScene();
        stage.setScene(scene);

        // Initialise the scene when it is ready
        Platform.runLater(() -> currentScene.initialise());
    }

    public void setUpDefaultScene() {
        this.scene = new Scene(new Pane(), width, height, Color.BLACK);
        stage.setScene(this.scene);
    }

    public void startMenu() {
        loadScene(new MenuScene(this));
    }
}
