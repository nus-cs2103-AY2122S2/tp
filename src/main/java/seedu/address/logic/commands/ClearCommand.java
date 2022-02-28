package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.ui.UiAlert;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_ALERT_TITLE = "You are about to clear Tracey's data";
    public static final String MESSAGE_ALERT_CONFIRM = "Are you sure you want to clear all of Tracey's content?";
    public static final String MESSAGE_SUCCESS = "Tracey has been cleared!";
    public static final String MESSAGE_UNSUCCESS = "Clear command cancelled";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Alert alert = UiAlert.makeConfirmationAlert(MESSAGE_ALERT_TITLE, MESSAGE_ALERT_CONFIRM);
        if (alert.getResult() == ButtonType.OK) {
            model.setAddressBook(new AddressBook());
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            throw new CommandException(MESSAGE_UNSUCCESS);
        }
    }
}
