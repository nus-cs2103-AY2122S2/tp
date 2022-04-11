package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.person.Person;
import seedu.address.model.tutorial.Tutorial;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the tutorials list.
     * This list will not contain any duplicate tutorials.
     */
    ObservableList<Tutorial> getTutorialList();

    /**
     * Returns an unmodifiable view of the assessments list.
     * This list will not contain any duplicate assessments.
     */
    ObservableList<Assessment> getAssessmentList();

    /**
     * Returns an unmodifiable view of the filtered persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getFilteredPersonsList();

    /**
     * Returns an unmodifiable view of the last shown list.
     */
    ObservableList<Displayable> getLastShownList();
}
