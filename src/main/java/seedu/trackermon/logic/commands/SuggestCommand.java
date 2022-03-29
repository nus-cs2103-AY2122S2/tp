package seedu.trackermon.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.trackermon.model.Model.PREDICATE_SHOW_ALL_SHOWS;

import seedu.trackermon.model.Model;
import seedu.trackermon.model.show.Show;



import java.util.function.Predicate;

public class SuggestCommand extends Command {

    public static final String COMMAND_WORD = "suggest";

    public static final String MESSAGE_SUCCESS = "Here is my suggestion";

    public static final String TAG_ERROR = "Tag parameter must only be a single word.\n"
            + "Example: " + COMMAND_WORD + " t/Action";

    private final Predicate<Show> predicate;

    public SuggestCommand(Predicate<Show> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredShowList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
