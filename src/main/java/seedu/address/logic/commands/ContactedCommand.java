package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_CONTACTED;

import seedu.address.commons.core.index.Index;

public class ContactedCommand extends EditCommand {
    public static final String COMMAND_WORD = "contacted";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the last contacted datetime of the client "
            + "identified by the index number used in the displayed client list.\n"
            + "Parameters: INDEX (must be a positive integer) " + PREFIX_LAST_CONTACTED + "DATETIME\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_LAST_CONTACTED + "21-03-2022 21:03";

    public ContactedCommand(Index index, EditClientDescriptor editClientDescriptor) {
        super(index, editClientDescriptor);
    }
}
