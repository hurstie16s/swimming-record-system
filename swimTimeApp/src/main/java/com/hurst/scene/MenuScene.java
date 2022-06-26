package com.hurst.scene;

import com.hurst.account.PrimaryAccountFunctions;
import com.hurst.ui.AppPane;
import com.hurst.ui.AppWindow;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Menu scene.
 */
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
    private boolean menuOptionsState = true;
    private TextField firstName;
    private TextField surname;

    /**
     * Instantiates a new Menu scene.
     *
     * @param appWindow the app window
     */
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
        signIn.setOnAction(this::toggleMenuOptions);
        signUp.setOnAction(this::toggleMenuOptions);

        menuOptions.setTop(menuToggleButtons);

        // Current User
        currentUserOptions();
        // New User

        // New User Heading
        newUserOptions();

        menuOptions.setCenter(currentUser);

        mainPane.setCenter(menuOptions);
    }

    /**
     * Toggle menu options.
     *
     * @param actionEvent the action event
     */
    public void toggleMenuOptions(ActionEvent actionEvent) {
        if (menuOptionsState) {
            menuOptions.setCenter(newUser);
        } else {
            menuOptions.setCenter(currentUser);
        }
        menuOptionsState = !menuOptionsState;
    }

    /**
     * Current user options.
     */
    public void currentUserOptions() {

        currentUser = new VBox();

        // Current Username heading
        currentUsernameHeading = new Text("Current Users");
        currentUsernameHeading.setTextAlignment(TextAlignment.CENTER);
        currentUsernameHeading.getStyleClass().add("menuHeading");

        // Username input field
        currentUsernameInput = new TextField();
        currentUsernameInput.setPromptText("username");
        currentUsernameInput.getStyleClass().add("text-field");
        currentUsernameInput.setAlignment(Pos.CENTER);
        currentUsernameInput.setMaxWidth(200);

        getTimes = new Button("Sign In");
        getTimes.getStyleClass().add("Button");
        getTimes.setAlignment(Pos.CENTER);
        getTimes.setPadding(new Insets(5.0f, 20.0f, 5.0f, 20.0f));
        // TODO : Add method to sign in
        getTimes.setOnAction(this::signIn);

        currentUser.getChildren().addAll(currentUsernameHeading, currentUsernameInput, getTimes);
        currentUser.setAlignment(Pos.CENTER);
    }

    /**
     * New user options.
     */
    public void newUserOptions() {
        newUser = new VBox();

        // New user heading
        newUserHeading = new Text("New Users");
        newUserHeading.setTextAlignment(TextAlignment.CENTER);
        newUserHeading.getStyleClass().add("menuHeading");

        // First Name
        firstName = new TextField();
        firstName.setPromptText("First Name");
        firstName.getStyleClass().add("text-field");
        firstName.setAlignment(Pos.CENTER);
        firstName.setPadding(new Insets(5.0f, 5.0f, 5.0f, 5.0f));
        firstName.setMaxWidth(200);

        // Surname
        surname = new TextField();
        surname.setPromptText("Surname");
        surname.getStyleClass().add("text-field");
        surname.setAlignment(Pos.CENTER);
        surname.setPadding(new Insets(5.0f, 5.0f, 5.0f, 5.0f));
        surname.setMaxWidth(200);

        createUser = new Button("Sign Up");
        createUser.getStyleClass().add("Button");
        createUser.setAlignment(Pos.CENTER);
        createUser.setPadding(new Insets(5.0f, 5.0f, 5.0f, 5.0f));
        createUser.setOnAction(this::signUp);

        newUser.getChildren().addAll(newUserHeading, firstName, surname, createUser);
    }

    private void userNotFound() {
        Text userNotFoundHeading = new Text("User does not exist");
        userNotFoundHeading.setTextAlignment(TextAlignment.CENTER);
        userNotFoundHeading.getStyleClass().add("menuWarning");

        if (currentUser.getChildren().size() == 3) {
            currentUser.getChildren().add(userNotFoundHeading);
        }
    }

    private void signIn(ActionEvent event) {
        boolean accountExists = PrimaryAccountFunctions.signIn(currentUsernameInput.getText());
        if(accountExists) {
            logger.info("User "+currentUsernameInput.getText()+" found");
            appWindow.loadScene(new MainAppScene(appWindow, currentUsernameInput.getText()));
        } else {
            logger.info("User does not exist");
            userNotFound();
        }
    }

    private void signUp(ActionEvent event) {
        String username = PrimaryAccountFunctions.signUp(firstName.getText(), surname.getText());
        appWindow.loadScene(new MainAppScene(appWindow, username));
    }
}