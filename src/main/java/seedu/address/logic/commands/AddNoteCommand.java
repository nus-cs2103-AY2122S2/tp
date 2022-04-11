package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import seedu.address.commons.core.index.Index;

public class AddNoteCommand extends EditCommand {
    public static final String COMMAND_WORD = "addNote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to client identified "
            + "by the index number used in the displayed client list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NOTE + "NOTE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NOTE + "Likes to gym";

    public AddNoteCommand(Index index, EditClientDescriptor editClientDescriptor) {
        super(index, editClientDescriptor);
    }
}
