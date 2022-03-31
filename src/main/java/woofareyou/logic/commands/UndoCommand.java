package woofareyou.logic.commands;

import static java.util.Objects.requireNonNull;

import woofareyou.logic.commands.exceptions.CommandException;
import woofareyou.model.AddressBook;
import woofareyou.model.Model;
import woofareyou.model.ReadOnlyAddressBook;


public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undid previous command!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undoes the previous command that the user executed.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_NO_UNDO = "No commands to undo!";

    /**
     * Constructor for the UndoCommand
     */
    public UndoCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            ReadOnlyAddressBook currentAddressBook = new AddressBook(model.undo());
            model.setAddressBook(currentAddressBook);
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (Exception e) {
            throw new CommandException(MESSAGE_NO_UNDO);
        }
    }
}
