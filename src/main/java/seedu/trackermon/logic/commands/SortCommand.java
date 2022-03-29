package seedu.trackermon.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.trackermon.model.Model;
import seedu.trackermon.model.show.Show;

/**
 * Sort the list of shows in Trackermon.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "sort success!!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort shows based on the input prefix. If there are "
            + "no prefix, it will sort by name in ascending order.\n"
            + "if the value for ascending and descending is incorrect, \n"
            + "it will only sort by ascending.\n"
            + "if all prefix are shown, by default it sort by name then status then rating then tags,\n"
            + "use so/ to sort in different priority by stating the name of your priority in order.\n"
            + "Parameters: [sn/] [ss/] [sr] [st] [so/]...\n"
            + "Example: " + COMMAND_WORD + " sn/ ss/dsc";

    private final Comparator<Show> comparator;

    public SortCommand(Comparator<Show> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedShowList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}

