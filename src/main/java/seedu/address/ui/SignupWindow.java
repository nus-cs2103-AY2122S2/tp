package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seedu.address.authentication.Authentication;
import seedu.address.authentication.AuthenticationException;
import seedu.address.commons.core.LogsCenter;


/**
 * The SignUp Window. Provides the basic application layout containing
 * a login form for user to log in with their password.
 */
public class SignupWindow extends UiPart<Stage> {
    private static final String FXML = "SignupWindow.fxml";
    private static final String SCREEN_TITLE = "Welcome to MedBook\nPlease type in a new password to get started";
    private static final String PASSWORD_DOES_NOT_MEET_REQUIREMENTS_MESSAGE = "Oops. Your password format does not meet"
            + "the following requirements. Please double check again.";
    private static final String PASSWORD_EMPTY_MESSAGE = "Oops. Your password is empty";

    private static final Image APPROVE_IMAGE = new Image("/images/approve.png");
    private static final Image REJECT_IMAGE = new Image("/images/reject.png");

    private static final String MATCH_UPPER_CASE = ".*[A-Z].*";
    private static final String MATCH_LOWER_CASE = ".*[a-z].*";
    private static final String MATCH_DIGITS = ".*[0-9+].*";
    private static final String SPECIAL_CHARACTER = ".*[#?!@$%^&*-].*";

    /**
     * Password should adhere to the following constraints:
     * - At least 8 characters
     * - Consists at least one lowercase character
     * - Consists at least one uppercase character
     * - Consists at least one digit character
     * - Consists at least one special character in this list [#?!@$%^&*-]
     */
    private static final String VALIDATION_REGEX =
            "^(?=[^A-Z\\n]*[A-Z])(?=[^a-z\\n]*[a-z])(?=[^0-9\\n]*[0-9])(?=[^#?!@$%^&*\\n-]*[#?!@$%^&*-]).{8,}$";


    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private PasswordField userPassword;

    @FXML
    private PasswordField userRepeatPassword;

    @FXML
    private Label responseDisplay;

    @FXML
    private Label screenTitle;

    @FXML
    private ImageView conditionImage1;

    @FXML
    private Label conditionText1;

    @FXML
    private ImageView conditionImage2;

    @FXML
    private ImageView conditionImage3;

    @FXML
    private ImageView conditionImage4;

    @FXML
    private ImageView conditionImage5;

    @FXML
    private ImageView conditionImage6;


    private final Authentication auth;

    /**
     * Creates a sign up window instance.
     *
     * @param auth The authentication manager to handle the authentication flow.
     */
    public SignupWindow(Authentication auth) {
        super(FXML);
        this.auth = auth;
    }

    /**
     * Creates a sign up window instance.
     *
     * @param auth The authentication manager to handle the authentication flow.
     * @param primaryStage JavaFX primary stage to render display.
     */
    public SignupWindow(Authentication auth, Stage primaryStage) {
        super(FXML, primaryStage);
        requireNonNull(auth);
        primaryStage.setResizable(true);
        screenTitle.setText(SCREEN_TITLE);
        this.auth = auth;
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleSignup() {
        handleUserPassword();
    }

    @FXML
    private void handleExit() {
        logger.info("Signup Screen: Exiting...");
    }

    @FXML
    private void handleOnKeyPressed() {
        isValidPassword(userPassword.getText(), userRepeatPassword.getText());
    }

    private void handleUserPassword() {
        // Guard clause to prevent unnecessary checks
        if (userPassword.getText().isEmpty()) {
            responseDisplay.setText(PASSWORD_EMPTY_MESSAGE);
            responseDisplay.setTextFill(Color.web("#D06651"));
            return;
        }

        if (isValidPassword(userPassword.getText(), userRepeatPassword.getText())) {
            handleNewPassword();
        } else {
            responseDisplay.setText(PASSWORD_DOES_NOT_MEET_REQUIREMENTS_MESSAGE);
            responseDisplay.setTextFill(Color.web("#D06651"));
        }
    }

    private void handleNewPassword() {
        try {
            auth.signup(userPassword.getText());
        } catch (AuthenticationException e) {
            responseDisplay.setText(e.getMessage());
            responseDisplay.setTextFill(Color.web("#D06651"));
        }
    }

    /**
     * Shows the sign up screen.
     */
    public void show() {
        logger.fine("Showing sign up page");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    private boolean isValidPassword(String password, String repeatPassword) {
        if (password.equals(repeatPassword) && !repeatPassword.isEmpty()) {
            conditionText1.setText("Passwords match");
            conditionImage1.setImage(APPROVE_IMAGE);
        } else {
            conditionImage1.setImage(REJECT_IMAGE);
            conditionText1.setText("Passwords do not match");
        }

        if (password.length() >= 8) {
            conditionImage2.setImage(APPROVE_IMAGE);
        } else {
            conditionImage2.setImage(REJECT_IMAGE);
        }

        if (password.matches(MATCH_LOWER_CASE)) {
            conditionImage3.setImage(APPROVE_IMAGE);
        } else {
            conditionImage3.setImage(REJECT_IMAGE);
        }

        if (password.matches(MATCH_UPPER_CASE)) {
            conditionImage4.setImage(APPROVE_IMAGE);
        } else {
            conditionImage4.setImage(REJECT_IMAGE);
        }

        if (password.matches(MATCH_DIGITS)) {
            conditionImage5.setImage(APPROVE_IMAGE);
        } else {
            conditionImage5.setImage(REJECT_IMAGE);
        }

        if (password.matches(SPECIAL_CHARACTER)) {
            conditionImage6.setImage(APPROVE_IMAGE);
        } else {
            conditionImage6.setImage(REJECT_IMAGE);
        }

        // Final validation is required to match all rules
        return password.equals(repeatPassword) && password.matches(VALIDATION_REGEX);
    }

}
