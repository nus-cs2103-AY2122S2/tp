package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Deletes an event identified using it's displayed index from the address book
 */
public class CancelEventCommand extends Command {

    public static final String COMMAND_WORD = "cancelevent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the event identified by the index number(s) used in the displayed event\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + "1\n"
            + "OR\n"
            + "Parameters: INDEX... (all indexes must be unique and positive integers)"
            + "Example: " + COMMAND_WORD + "1 3 5";

    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Cancelled Event:%n%1$s";
    public static final String MESSAGE_DELETE_EVENTS_SUCCESS = "Cancelled Events:%n%1$s";

    private final Index[] targetIndexArr;
    private final Index targetIndex;

    /**
     * Constructor for CancelEventCommand, for singular deletion
     *
     * @param targetIndex the event to be deleted
     */
    public CancelEventCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.targetIndexArr = new Index[]{targetIndex};
    }

    /**
     * Constructor for CancelEventCommand, for multiple deletions
     *
     * @param targetIndexArr the events to be deleted
     */
    public CancelEventCommand(Index[] targetIndexArr) {
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
     * @return a string containing all the information of the event(s) to be deleted.
     */
    private String extractDeletedInfo(List<Event> lastShownList) {
        final StringBuilder deletedEventOrEvents = new StringBuilder();
        for (int i = 0; i < targetIndexArr.length; i++) {
            Index target = targetIndexArr[i];
            Event eventToDelete = lastShownList.get(target.getZeroBased());
            if (i > 0) {
                deletedEventOrEvents.append(System.lineSeparator());
            }
            deletedEventOrEvents.append(eventToDelete.displayForCancelEvent());
        }
        return deletedEventOrEvents.toString();
    }

    /**
     * Deletes the events specified in the targetIndexArr.
     * A copy of targetIndexArr is created for defensive programming.
     * targetIndexArrClone is sorted in descending order so that the deletion process will not delete the wrong event.
     * Example: If index 1 is deleted first, the original index 2 becomes index 1.
     *
     * @param model the addressbook model
     * @param lastShownList the last displayed person list
     */
    private void deleteFromList(Model model, List<Event> lastShownList) {
        Index[] targetIndexArrClone = targetIndexArr.clone();
        Arrays.sort(targetIndexArrClone);
        for (Index target : targetIndexArrClone) {
            Event eventToDelete = lastShownList.get(target.getZeroBased());
            model.deleteEvent(eventToDelete);
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();
        int lastShownListSize = lastShownList.size();

        if (!checkIndexRange(lastShownListSize)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        String deletedEventOrEvents = extractDeletedInfo(lastShownList);
        deleteFromList(model, lastShownList);

        return targetIndexArr.length == 1
                ? new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, deletedEventOrEvents))
                : new CommandResult(String.format(MESSAGE_DELETE_EVENTS_SUCCESS, deletedEventOrEvents));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CancelEventCommand // instanceof handles nulls
                && targetIndex.equals(((CancelEventCommand) other).targetIndex)); // state check
    }
}
