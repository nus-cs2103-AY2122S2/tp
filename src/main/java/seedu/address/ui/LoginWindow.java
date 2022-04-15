package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seedu.address.authentication.Authentication;
import seedu.address.authentication.AuthenticationException;
import seedu.address.commons.core.LogsCenter;

/**
 * The Login Window. Provides the basic application layout containing
 * a login form for user to log in with their password.
 */
public class LoginWindow extends UiPart<Stage> {
    private static final String SCREEN_TITLE = "Welcome back to MedBook\nPlease type in your password to login";
    private static final String FXML = "LoginWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private PasswordField userPassword;

    @FXML
    private Label responseDisplay;

    @FXML
    private Label screenTitle;

    private final Authentication auth;

    /**
     * Creates a login window instance.
     *
     * @param auth The authentication manager to handle the authentication flow.
     */
    public LoginWindow(Authentication auth) {
        super(FXML);
        this.auth = auth;
    }

    /**
     * Creates a login window instance.
     *
     * @param auth The Authentication Manager to handle the authentication flow.
     * @param primaryStage JavaFX primary stage to render display.
     */
    public LoginWindow(Authentication auth, Stage primaryStage) {
        super(FXML, primaryStage);
        primaryStage.setResizable(true);
        requireNonNull(auth);
        screenTitle.setText(SCREEN_TITLE);
        this.auth = auth;
    }

    @FXML
    private void handleLogin() {
        handleInputPassword();
    }

    @FXML
    private void handleExit() {
        logger.info("Login Screen: Exiting...");
    }

    private void handleInputPassword() {
        handlePassword();
    }

    private void handlePassword() {
        try {
            auth.login(userPassword.getText());
        } catch (AuthenticationException e) {
            responseDisplay.setText(e.getMessage());
            responseDisplay.setTextFill(Color.web("#D06651"));
            userPassword.clear();
        }
    }

    /**
     * Shows the login window.
     */
    public void show() {
        logger.fine("Showing login screen");
        getRoot().show();
        getRoot().centerOnScreen();
    }

}
