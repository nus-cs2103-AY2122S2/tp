package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number(s) used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1\n"
            + "OR\n"
            + "Parameters: INDEX... (all indexes must be unique and positive integers)\n"
            + "Example: " + COMMAND_WORD + " 1 3 5";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person:%n%1$s";

    public static final String MESSAGE_DELETE_MULTIPLE_PERSON_SUCCESS = "Deleted Persons:%n%1$s";

    private final Index[] targetIndexArr;
    private final Index targetIndex;

    /**
     * Constructor for DeleteCommand, for singular deletion
     *
     * @param targetIndex the person to be deleted
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.targetIndexArr = new Index[]{targetIndex};
    }

    /**
     * Constructor for DeleteCommand, for multiple deletions
     *
     * @param targetIndexArr the persons to be deleted
     */
    public DeleteCommand(Index[] targetIndexArr) {
        this.targetIndexArr = targetIndexArr;
        this.targetIndex = targetIndexArr[0];
    }

    /**
     * In the multiple deletion case, ensures all user input indexes are within range of the list.
     *
     * @param listSize the size of the last displayed list.
     * @return true if every index is within the range of the list.
     */
    private boolean checkIndexRange(int listSize) {
        boolean result = true;
        for (Index target : targetIndexArr) {
            result = result && (target.getZeroBased() < listSize);
        }
        return result;
    }

    /**
     * Extracts the information before deletion for the success message.
     *
     * @param lastShownList the last displayed list.
     * @return a string containing all the information of the person(s) to be deleted.
     */
    private String extractDeletedInfo(List<Person> lastShownList) {
        final StringBuilder deletedPersonOrPersons = new StringBuilder();
        for (int i = 0; i < targetIndexArr.length; i++) {
            Index target = targetIndexArr[i];
            Person personToDelete = lastShownList.get(target.getZeroBased());
            if (i > 0) {
                deletedPersonOrPersons.append(System.lineSeparator());
            }
            deletedPersonOrPersons.append(personToDelete.displayPersonForDelete());
        }
        return deletedPersonOrPersons.toString();
    }

    /**
     * Deletes the persons specified in the targetIndexArr.
     * A copy of targetIndexArr is created for defensive programming.
     * targetIndexArrClone is sorted in descending order so that the deletion process will not delete the wrong person.
     * Example: If index 1 is deleted first, the original index 2 becomes index 1.
     *
     * @param model the addressbook model
     * @param lastShownList the last displayed person list
     * @param lastEventList the last display event list
     */
    private void deleteFromList(Model model, List<Person> lastShownList, List<Event> lastEventList) {
        Index[] targetIndexArrClone = targetIndexArr.clone();
        Arrays.sort(targetIndexArrClone);
        for (Index target : targetIndexArrClone) {
            Person personToDelete = lastShownList.get(target.getZeroBased());
            updateEvents(model, personToDelete, lastEventList);
            model.deletePerson(personToDelete);
        }
    }

    private void updateEvents(Model model, Person person, List<Event> lastEventList) {
        Name name = person.getName();

        for (int i = 0; i < lastEventList.size(); i++) {
            Event currEvent = lastEventList.get(i);
            List<Name> editedParticipants = currEvent.getParticipants();
            if (editedParticipants.contains(name) && editedParticipants.size() == 1) {
                model.deleteEvent(currEvent);
            } else if (editedParticipants.contains(name) && editedParticipants.size() > 1) {
                editedParticipants.remove(name);
                Event editedEvent = new Event(currEvent.getEventName(), currEvent.getEventInfo(), editedParticipants,
                        currEvent.getDateTime());
                model.setEvent(currEvent, editedEvent);
            }
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Event> lastEventList = model.getFilteredEventList();
        int lastShownListSize = lastShownList.size();

        if (!checkIndexRange(lastShownListSize)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        String deletedPersonOrPersons = extractDeletedInfo(lastShownList);
        deleteFromList(model, lastShownList, lastEventList);

        return targetIndexArr.length == 1
                ? new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, deletedPersonOrPersons))
                : new CommandResult(String.format(MESSAGE_DELETE_MULTIPLE_PERSON_SUCCESS, deletedPersonOrPersons));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
