package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.lesson.Lesson;

//@@author Gernene
/**
 * Controller for a attendance popup.
 */
public class AttendanceWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "AttendanceWindow.fxml";

    @FXML
    private ListView<Lesson> lessonListView;

    /**
     * Creates a new AttendanceWindow.
     *
     * @param lessonList A list of lessons to display.
     */
    public AttendanceWindow(ObservableList<Lesson> lessonList) {
        super(FXML, new Stage());
        lessonListView.setItems(lessonList);
        lessonListView.setCellFactory(listView -> new LessonListViewCell());
    }

    /**
     * Shows the attendance window.
     * @throws IllegalStateException
     */
    public void show() {
        logger.fine("Showing attendance page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the attendance window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the attendance window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the attendance window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Lesson} using a {@code LessonCard}.
     */
    class LessonListViewCell extends ListCell<Lesson> {
        @Override
        protected void updateItem(Lesson lesson, boolean empty) {
            super.updateItem(lesson, empty);

            if (empty || lesson == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LessonCard(lesson, getIndex() + 1).getRoot());
            }
        }
    }
}
