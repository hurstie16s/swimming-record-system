package com.hurst.scene;

import com.hurst.ui.AppPane;
import com.hurst.ui.AppWindow;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MenuScene extends BaseScene {

    private static final Logger logger = LogManager.getLogger(MenuScene.class);

    // GUI components
    private Text title;
    private Text currentUsernameHeading;
    private TextField currentUsernameInput;
    private Button getTimes;
    private Text newUserHeading;
    private TextField newFirstNameInput;
    private TextField newSurnameInput;
    private Button createUser;
    private VBox currentUser;
    private VBox newUser;
    private BorderPane menuOptions;
    private Button signIn;
    private Button signUp;

    public MenuScene(AppWindow appWindow) {
        super(appWindow);
        logger.info("Creating Menu Scene");
    }

    @Override
    public void initialise() {

    }

    @Override
    public void build() {
        logger.info("Building " + this.getClass().getName());

        root = new AppPane(appWindow.getWidth(), appWindow.getHeight());

        var menuPane = new StackPane();
        menuPane.setMaxWidth(appWindow.getWidth());
        menuPane.setMaxHeight(appWindow.getHeight());
        menuPane.getStyleClass().add("menu-background");
        root.getChildren().add(menuPane);

        var mainPane = new BorderPane();
        menuPane.getChildren().add(mainPane);

        // Title
        title = new Text("Swimming Results");
        title.getStyleClass().add("menuTitle");
        mainPane.setTop(title);

        menuOptions = new BorderPane();

        var menuToggleButtons = new HBox();

        signIn = new Button("Sign In");
        signIn.getStyleClass().add("Button");
        signIn.setAlignment(Pos.CENTER);

        signUp = new Button("Sign Up");
        signUp.getStyleClass().add("Button");
        signUp.setAlignment(Pos.CENTER);

        menuToggleButtons.getChildren().addAll(signIn, signUp);

        menuOptions.setTop(menuToggleButtons);

        // Current User
        currentUserOptions();
        // New User
        var newUser = new VBox();

        // New User Heading
        newUserHeading = new Text("New User:");

        mainPane.setCenter(menuOptions);
    }

    public void toggleMenuOptions() {}

    public void currentUserOptions() {

        currentUser = new VBox();
        currentUser.getStyleClass().add("menuOptions");

        // Current Username heading
        currentUsernameHeading = new Text("Sign In");
        currentUsernameHeading.setTextAlignment(TextAlignment.CENTER);

        // Username input field
        currentUsernameInput = new TextField();
        currentUsernameInput.getStyleClass().add("text-field");
        currentUsernameInput.setAlignment(Pos.CENTER);

        getTimes = new Button("Get Swim Times");
        getTimes.getStyleClass().add("Button");
        getTimes.setAlignment(Pos.CENTER);

        currentUser.getChildren().addAll(currentUsernameHeading, currentUsernameInput, getTimes);
        currentUser.setAlignment(Pos.CENTER);
    }

    public void newUserOptions() {
    }
}
