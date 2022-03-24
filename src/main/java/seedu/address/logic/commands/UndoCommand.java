package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;


public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undid previous command!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undoes the previous command that the user executed.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_NO_UNDO = "No commands to undo!";

    public UndoCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            ReadOnlyAddressBook currentAddressBook = model.undo();
            model.setAddressBook(new AddressBook(currentAddressBook));
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (Exception e) {
            throw new CommandException(MESSAGE_NO_UNDO);
        }
    }
}
