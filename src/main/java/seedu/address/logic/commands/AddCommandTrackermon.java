package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntaxTrackermon.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntaxTrackermon.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntaxTrackermon.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelTrackermon;
import seedu.address.model.show.Show;

/**
 * Adds a show to Trackermon.
 */
public class AddCommandTrackermon extends CommandTrackermon {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a show to Trackermon. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_STATUS + "STATUS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Boku no Hero "
            + PREFIX_STATUS + "completed "
            + PREFIX_TAG + "Anime "
            + PREFIX_TAG + "Action";

    public static final String MESSAGE_SUCCESS = "New show added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This show already exists in Trackermon";

    private final Show toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Show}
     */
    public AddCommandTrackermon(Show show) {
        requireNonNull(show);
        toAdd = show;
    }

    @Override
    public CommandResultTrackermon execute(ModelTrackermon model) throws CommandException {
        requireNonNull(model);

        if (model.hasShow(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addShow(toAdd);
        return new CommandResultTrackermon(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommandTrackermon // instanceof handles nulls
                && toAdd.equals(((AddCommandTrackermon) other).toAdd));
    }
}

