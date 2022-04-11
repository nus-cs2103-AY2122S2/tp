package seedu.address.ui;


import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Contains utility methods used for dynamically changing the style of the FXML components in UI
 */
public class StyleUtil {
    /**
     * Displays the job status and set style of the status label based on the position value.
     *
     * @param status status value to display
     */
    public static void setJobStatusStyle(Label label, String status) {
        label.setText(status);
        if (status.equals("vacant")) {
            label.setStyle("-fx-background-color: rgb(66, 128, 83); -fx-background-radius: 10; "
                    + "-fx-padding: 3; -fx-text-fill: white");
        } else if (status.equals("filled")) {
            label.setStyle("-fx-background-color: rgb(61, 61, 61); -fx-background-radius: 10; "
                    + "-fx-padding: 3; -fx-text-fill: white;");
        }
    }

    /**
     * Displays the position and set style of the job position label based on the position value.
     *
     * @param position status value to display
     */
    public static void setJobPositionStyle(Label label, String position) {
        label.setText(position);
        if (position.equals("part-time")) {
            label.setStyle("-fx-background-color:rgb(237, 196, 114); -fx-background-radius: 10; "
                    + "-fx-padding: 3; -fx-text-fill: #707070;");
        } else if (position.equals("full-time")) {
            label.setStyle("-fx-background-color:rgb(78, 96, 145); -fx-background-radius: 10; "
                    + "-fx-padding: 3; -fx-text-fill: white;");
        }
    }

    /**
     * Displays the status and set style of an application status label based on the status value.
     *
     * @param label to change style
     * @param status status value to display
     */
    public static void setApplicantStatusStyle(Label label, String status) {
        label.setText(status);
        if (status.equals("REJECTED")) {
            label.setStyle("-fx-background-color:rgb(189, 94, 94); -fx-background-radius: 10; "
                    + "-fx-padding: 3; -fx-text-fill: white;");
        } else if (status.equals("ACCEPTED")) {
            label.setStyle("-fx-background-color:rgb(66, 128, 83); -fx-background-radius: 10; "
                    + "-fx-padding: 3; -fx-text-fill: white;");
        } else if (status.equals("INTERVIEWED")) {
            label.setStyle("-fx-background-color:rgb(237, 196, 114); -fx-background-radius: 10; "
                    + "-fx-padding: 3; -fx-text-fill: #707070;");
        } else {
            label.setStyle("-fx-background-color:rgb(61, 61, 61);-fx-background-radius: 10; "
                    + "-fx-padding: 3; -fx-text-fill: white;");
        }
    }

    /**
     * Displays the status and set style of an application status circle based on the status value.
     *
     * @param circle to change style
     * @param status status value to display
     */
    public static void setApplicantStatusStyle(Circle circle, String status) {
        if (status.equals("REJECTED")) {
            circle.setFill(Color.rgb(189, 94, 94));
        } else if (status.equals("ACCEPTED")) {
            circle.setFill(Color.rgb(66, 128, 83));
        } else if (status.equals("INTERVIEWED")) {
            circle.setFill(Color.rgb(237, 196, 114));
        } else {
            circle.setFill(Color.GREY);
        }
    }

    /**
     * Resets the style of the label to default.
     *
     * @param label to reset style
     */
    public static void resetLabelStyle(Label label) {
        label.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
    }
}
