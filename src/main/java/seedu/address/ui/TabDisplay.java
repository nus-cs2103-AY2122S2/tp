package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class TabDisplay extends UiPart<Region> {

    private static final String FXML = "TabDisplay.fxml";
    private static final int ORDER_INDEX = 0;
    private static final int DRIVER_INDEX = 1;
    private static final int DISH_INDEX = 2;
    private static final int CUSTOMER_INDEX = 3;

    @FXML
    private HBox tabDisplay;
    @FXML
    private Label customer;
    @FXML
    private Label order;
    @FXML
    private Label driver;
    @FXML
    private Label dish;

    public TabDisplay() {
        super(FXML);
    }

    public void setFocus(int focusItem) {
        requireNonNull(focusItem);
        customer.setStyle("-fx-text-fill: grey");
        order.setStyle("-fx-text-fill: grey");
        driver.setStyle("-fx-text-fill: grey");
        dish.setStyle("-fx-text-fill: grey");
        switch (focusItem) {
        case ORDER_INDEX:
            order.setStyle("-fx-text-fill: white");
            break;
        case DRIVER_INDEX:
            driver.setStyle("-fx-text-fill: white");
            break;
        case DISH_INDEX:
            dish.setStyle("-fx-text-fill: white");
            break;
        case CUSTOMER_INDEX:
            customer.setStyle("-fx-text-fill: white");
            break;
        default:
            break;
        }
    }


}
