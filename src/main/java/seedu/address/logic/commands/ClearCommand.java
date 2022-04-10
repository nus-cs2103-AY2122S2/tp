package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentBook;
import seedu.address.model.InsuranceBook;
import seedu.address.model.Model;
import seedu.address.model.RecordBook;

/**
 * Clears the all books.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All books has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        model.setInsuranceBook(new InsuranceBook());
        model.setRecordBook(new RecordBook());
        model.setAppointmentBook(new AppointmentBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
