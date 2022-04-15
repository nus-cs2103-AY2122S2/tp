package seedu.address.logic.commands.strategy;

import static seedu.address.commons.core.Tabs.DEFAULT;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.UUID;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exports current Strategy Panel as a .png image into user selected directory. "
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_EXPORT_ACKNOWLEDGEMENT = "Strategy Panel is being exported.";

    private String fileName;

    /**
     * Constructor for ExportCommand class.
     */

    @Override
    public CommandResult execute(Model model) {
        requireAllNonNull(model);
        return new CommandResult(MESSAGE_EXPORT_ACKNOWLEDGEMENT, false, false, DEFAULT, false, null, true);
    }


    /**
     * Randomized file names
     */
    private static String imageRandomFileString() {
        return UUID.randomUUID().toString();
    }
}
