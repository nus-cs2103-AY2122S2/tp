package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabList;
import seedu.address.model.student.Student;

/**
 * Window to show the details of a student
 */
public class ViewWindow extends UiPart<Stage> {

    private static final String FXML = "ViewWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(ViewWindow.class);

    public final Stage viewStage;
    public final Student student;

    @FXML
    private Label name;

    @FXML
    private Label id;

    @FXML
    private Label email;

    @FXML
    private Label github;

    @FXML
    private Label telegram;

    @FXML
    private Label studentId;

    @FXML
    private FlowPane tags;

    @FXML
    private ListView<Lab> labs;

    /**
     * Creates a new ViewWindow.
     *
     * @param root Stage to use as the root of the ViewWindow.
     */
    public ViewWindow(Stage root, Student student) {
        super(FXML, root);
        this.viewStage = root;
        this.student = student;
        name.setText(student.getName().fullName);
        email.setText("email: " + student.getEmail().value);
        github.setText("github: " + student.getGithubUsername().username);
        telegram.setText("telegram: " + student.getTelegram().handle);
        studentId.setText("studentID: " + student.getStudentId().id);
        student.getTags().stream()
                        .sorted(Comparator.comparing(tag -> tag.tagName))
                        .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        setLabDetails(student.getLabs());
    }

    /**
     * Creates a new ViewWindow.
     */
    public ViewWindow(Student student) {
        this(new Stage(), student);
        setStageTitle(student.getName().fullName);
    }

    /**
     * Shows the ViewWindow
     */
    public void show() {
        logger.fine(String.format("Showing details of %s", this.student.getName().fullName));
        getRoot().show();
        getRoot().centerOnScreen();
    }

    public void setStageTitle(String stageTitle) {
        requireNonNull(stageTitle);
        getRoot().setTitle(stageTitle);
    }

    public void setLabDetails(LabList labList) {
        labs.setItems(labList.asUnmodifiableObservableList());
        labs.setCellFactory(listView -> new LabListViewCell());
        labs.setOrientation(Orientation.VERTICAL);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    private static class LabListViewCell extends ListCell<Lab> {
        @Override
        protected void updateItem(Lab lab, boolean empty) {
            super.updateItem(lab, empty);

            if (empty || lab == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LabDetails(lab));
                setAlignment(Pos.CENTER);
            }
        }
    }

}
