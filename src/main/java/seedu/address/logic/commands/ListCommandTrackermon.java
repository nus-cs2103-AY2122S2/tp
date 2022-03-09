package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.ModelTrackermon.PREDICATE_SHOW_ALL_SHOWS;

import seedu.address.model.ModelTrackermon;

/**
 * Lists all shows in Trackermon to the user.
 */
public class ListCommandTrackermon extends CommandTrackermon {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all shows";


    @Override
    public CommandResultTrackermon execute(ModelTrackermon model) {
        requireNonNull(model);
        model.updateFilteredShowList(PREDICATE_SHOW_ALL_SHOWS);
        return new CommandResultTrackermon(MESSAGE_SUCCESS);
    }
}
