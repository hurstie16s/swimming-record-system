package com.hurst.scene;

import com.hurst.ui.AppPane;
import com.hurst.ui.AppWindow;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public abstract class BaseScene {

    protected final AppWindow appWindow;

    protected AppPane root;

    protected Scene scene;

    public BaseScene(AppWindow appWindow) {
        this.appWindow = appWindow;
    }

    public abstract void initialise();
    public abstract void build();

    public Scene setScene() {
        var previous = appWindow.getScene();
        Scene scene = new Scene(root, previous.getWidth(), previous.getHeight(), Color.BLACK);
        scene.getStylesheets().add(getClass().getResource("/style/app.css").toExternalForm());
        this.scene = scene;
        return scene;
    }

    public Scene getScene() {
        return this.scene;
    }
}
