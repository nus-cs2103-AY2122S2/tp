package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

public class ShowEventsCommand extends Command {

    public static final String COMMAND_WORD = "showevents";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters the events list to show either upcoming, past or all events. \n"
            + "By defualt shows all events, unless one of the parameter flags is specified.\n"
            + "Parameter flags: "
            + "[-upcoming] [-past]\n"
            + "Example: " + COMMAND_WORD + "-upcoming";

    private final Predicate<Event> predicate;

    public ShowEventsCommand(Predicate<Event> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredEventList(predicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ShowEventsCommand)) {
            return false;
        }

        ShowEventsCommand e = (ShowEventsCommand) other;
        return predicate.equals(e.predicate);
    }

}
