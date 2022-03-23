package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Information;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Tags an event to the person identified by the index "
            + "number."
            + "\nParameters: INDEX (must be a positive integer) " + PREFIX_EVENT_NAME + "EVENT NAME " + PREFIX_INFO
            + "EVENT DETAILS " + PREFIX_DATE + "yyyy-MM-dd " + PREFIX_TIME + "HH:mm";
    public static final String MESSAGE_ARGUMENTS = "Tagged following event to %1$s:" + "\n%2$s";

    private final Index[] indexes;
    private final EventName name;
    private final Information information;
    private final DateTime dateTime;
    private final Set<String> names;

    /**
     * Constructor for EventCommand.
     * @param indexes the indexes of the persons tagged to this event
     * @param name the name of the event
     * @param information the details of the event
     * @param dateTime the date and time of the event
     */
    public EventCommand(Index[] indexes, EventName name, Information information, DateTime dateTime) {
        requireAllNonNull(indexes, name, information, dateTime);

        this.indexes = indexes;
        this.name = name;
        this.information = information;
        this.dateTime = dateTime;
        this.names = new HashSet<>();
    }

    /**
     * In the multiple deletion case, ensures all user input indexes are within range of the list.
     *
     * @param listSize the size of the last displayed list.
     * @return true if every index is within the range of the list.
     */
    private boolean checkIndexRange(int listSize) {
        boolean result = true;
        for (Index target : indexes) {
            result = result && (target.getZeroBased() < listSize);
        }
        return result;
    }

    private Event createEvent(List<Person> lst, Index[] indexes) {
        Set<Name> tempList = new HashSet<>();
        for (Index currIndex : indexes) {
            tempList.add(lst.get(currIndex.getZeroBased()).getName());
        }
        return new Event(this.name, this.information, new ArrayList<>(tempList), this.dateTime);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        int listSize = lastShownList.size();

        if (!checkIndexRange(listSize)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        // Creating the event with the list of participants provided
        Event currEvent = createEvent(lastShownList, indexes);

        // Tagging the event to each participant
        for (Index currIndex : indexes) {
            Person currPerson = lastShownList.get(currIndex.getZeroBased());
            names.add(currPerson.getName().toString());
        }

        model.addEvent(currEvent);
        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_ARGUMENTS, names, currEvent));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventCommand)) {
            return false;
        }

        EventCommand e = (EventCommand) other;
        return name.equals(e.name) && information.equals(e.information)
                && Arrays.equals(indexes, e.indexes) && dateTime.equals(e.dateTime);
    }
}
