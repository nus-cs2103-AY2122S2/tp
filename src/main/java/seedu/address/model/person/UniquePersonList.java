package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.DuplicateTaskException;
import seedu.address.model.person.exceptions.InvalidTaskIndexException;
import seedu.address.model.person.exceptions.ModuleCodeNotFoundException;
import seedu.address.model.person.exceptions.PartialDuplicateTaskException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.exceptions.TaskAlreadyCompleteException;
import seedu.address.model.person.exceptions.TaskAlreadyNotCompleteException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList implements Iterable<Person> {

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPerson);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(persons);
    }

    /**
     * Assigns {@code task} to a person with {@code studentId}.
     *
     * @param studentId the student id of the person to be assigned.
     * @param task the task to be assigned.
     */
    public void assignTaskToPerson(StudentId studentId, Task task) {
        requireAllNonNull(studentId, task);
        boolean isPersonFound = false;

        for (Person currPerson: internalList) {
            if (currPerson.getStudentId().equals(studentId)) {
                isPersonFound = true;
                if (!currPerson.isTaskAlreadyPresent(task)) {
                    Person updatedPerson = currPerson.getCopy();
                    updatedPerson.addTask(task);
                    setPerson(currPerson, updatedPerson);
                } else {
                    throw new DuplicateTaskException();
                }
            }
        }

        if (!isPersonFound) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Assigns {@code task} to a person taking a module with {@code moduleId}.
     *
     * @param moduleCode the module code of the module of which all students are to be assigned a task.
     * @param task the task to be assigned.
     */

    public void assignTaskToAllInModule(ModuleCode moduleCode, Task task) {
        requireAllNonNull(moduleCode, task);
        boolean anyPersonFound = false;
        int totalPersonWithNoDuplicateTask = 0;
        int totalPersonTakingThisModule = 0;

        for (Person currPerson: internalList) {
            if (currPerson.getModuleCode().equals(moduleCode)) {
                anyPersonFound = true;
                totalPersonTakingThisModule++;
                if (!currPerson.isTaskAlreadyPresent(task)) {
                    Person updatedPerson = currPerson.getCopy();
                    updatedPerson.addTask(task);
                    setPerson(currPerson, updatedPerson);
                    totalPersonWithNoDuplicateTask++;
                }
            }
        }
        if (!anyPersonFound) {
            throw new ModuleCodeNotFoundException();
        }
        if (totalPersonWithNoDuplicateTask == 0) {
            throw new DuplicateTaskException();
        }

        if (totalPersonWithNoDuplicateTask != totalPersonTakingThisModule) {
            throw new PartialDuplicateTaskException();
        }
    }

    /**
     * Mark {@code task} task belonging to a person {@code studentId} as done.
     *
     * @param studentId the student id of the person's whose task is to be marked as done.
     * @param index the index of he task to be marked as complete.
     */

    public void markTaskOfPerson(StudentId studentId, Index index) {
        requireAllNonNull(studentId, index);
        boolean isPersonFound = false;

        for (Person currPerson: internalList) {
            if (currPerson.getStudentId().equals(studentId)) {
                isPersonFound = true;
                Person updatedPerson = currPerson.getCopy();
                TaskList updatedPersonTaskList = updatedPerson.getTaskList();
                int numberOfTasks = updatedPersonTaskList.getNumberOfTasks();
                if (index.getZeroBased() < numberOfTasks && index.getOneBased() > 0) {
                    if (!updatedPersonTaskList.getTaskList().get(index.getZeroBased()).isTaskComplete()) {
                        updatedPersonTaskList.markTaskAsComplete(index.getZeroBased());
                        setPerson(currPerson, updatedPerson);
                    } else {
                        throw new TaskAlreadyCompleteException();
                    }
                } else {
                    throw new InvalidTaskIndexException();
                }
            }
        }

        if (!isPersonFound) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Mark {@code task} task belonging to a person {@code studentId} as undone.
     *
     * @param studentId the student id of the person's whose task is to be marked as undone.
     * @param index the index of he task to be marked as incomplete.
     */
    public void unmarkTaskOfPerson(StudentId studentId, Index index) {
        requireAllNonNull(studentId, index);
        boolean isPersonFound = false;

        for (Person currPerson: internalList) {
            if (currPerson.getStudentId().equals(studentId)) {
                isPersonFound = true;
                Person updatedPerson = currPerson.getCopy();
                TaskList updatedPersonTaskList = updatedPerson.getTaskList();
                int numberOfTasks = updatedPersonTaskList.getNumberOfTasks();
                if (index.getZeroBased() < numberOfTasks && index.getOneBased() > 0) {
                    if (updatedPersonTaskList.getTaskList().get(index.getZeroBased()).isTaskComplete()) {
                        updatedPersonTaskList.markTaskAsNotComplete(index.getZeroBased());
                        setPerson(currPerson, updatedPerson);
                    } else {
                        throw new TaskAlreadyNotCompleteException();
                    }
                } else {
                    throw new InvalidTaskIndexException();
                }
            }
        }

        if (!isPersonFound) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Iterates through each {@code Person}, and checks if the {@code Person} who is taking {@code ModuleCode}
     * has the specified {@code Task} in his/her {@code TaskList}. If the specified {@Code Task} is present,
     * the completion status will be extracted out into a resulting HashMap.
     *
     *
     * @param moduleCode target moduleCode to be compared with.
     * @param task target task to be compared with.
     * @return LinkedHashMap containing valid person/completion status pair.
     */
    public LinkedHashMap<Person, Boolean> checkProgress(ModuleCode moduleCode, Task task) {
        requireNonNull(moduleCode);
        requireNonNull(task);
        LinkedHashMap<Person, Boolean> result = new LinkedHashMap<Person, Boolean>();

        // iterate through each student, and check if their respective TaskList contain the specified task
        for (Person currPerson: internalList) {
            TaskList currTaskList = currPerson.getTaskList();
            if (currPerson.getModuleCode().equals(moduleCode)
                    && currTaskList != null && currTaskList.isTaskAlreadyPresent(task)) {
                result.put(currPerson, currTaskList.isTaskPresentAndCompleted(task));
            }
        }
        return result;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Person> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSamePerson(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sorts the list of persons in ascending order of their names.
     */
    public void sortList() {
        internalList.sort(new SortAlphabetically());
    }

    /**
     * SortAlphabetically implements a comparator class, to sort the list of persons in alphabetical order.
     */
    class SortAlphabetically implements Comparator<Person> {

        /**
         * Sorts in ascending order of the names of persons.
         *
         * @param a the first person.
         * @param b the second person.
         * @return an int value after comparing the names of the two persons.
         */
        @Override
        public int compare(Person a, Person b) {
            String personAFullName = a.getName().fullName;
            String personBFullName = b.getName().fullName;
            return personAFullName.compareTo(personBFullName);
        }
    }
}
