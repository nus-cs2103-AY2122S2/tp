package seedu.address.ui.listpanel;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.lesson.Lesson;
import seedu.address.ui.card.RecurringLessonCard;
import seedu.address.ui.card.TemporaryLessonCard;

/**
 * Panel containing the list of lessons.
 */
public class LessonListPanel extends ListPanel {
    private static final String FXML = "LessonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(LessonListPanel.class);

    @FXML
    private ListView<Lesson> lessonListView;

    /**
     * Creates a {@code LessonListPanel} with the given {@code ObservableList}.
     */
    public LessonListPanel(ObservableList<Lesson> lessonList) {
        super(FXML);
        lessonListView.setItems(lessonList);
        lessonListView.setCellFactory(listView -> new LessonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Lesson} using a {@code TemporaryLessonCard}.
     */
    class LessonListViewCell extends ListCell<Lesson> {
        @Override
        protected void updateItem(Lesson lesson, boolean empty) {
            super.updateItem(lesson, empty);

            if (empty || lesson == null) {
                setGraphic(null);
                setText(null);

                return;
            }

            if (lesson.isRecurring()) {
                setGraphic(new RecurringLessonCard(lesson, getIndex() + 1).getRoot());
            } else {
                setGraphic(new TemporaryLessonCard(lesson, getIndex() + 1).getRoot());
            }
        }
    }

}
