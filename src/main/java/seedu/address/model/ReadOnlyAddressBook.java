package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.tasks.Task;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    ObservableList<Person> getPersonList();

    ObservableList<Interview> getInterviewList();

    ObservableList<Task> getTaskList();
}
