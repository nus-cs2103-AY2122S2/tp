package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.lesson.Lesson;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyLessonBook {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Lesson> getLessonList();
}
