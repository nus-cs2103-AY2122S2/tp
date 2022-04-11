package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;

public class DeleteNoteCommand extends EditCommand {
    public static final String COMMAND_WORD = "deleteNote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the note of the client specified"
            + "by the index number used in the displayed client list. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public DeleteNoteCommand(Index index, EditClientDescriptor editClientDescriptor) {
        super(index, editClientDescriptor);
    }
}
