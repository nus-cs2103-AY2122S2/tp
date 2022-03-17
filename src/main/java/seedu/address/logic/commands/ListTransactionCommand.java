package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;


public class ListTransactionCommand extends Command {

    public static final String COMMAND_WORD = "listTransaction";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all Transactions "
            + "associated with the person specified by the index "
            + "used in the last person listing\n"
            + "Parameters: INDEX \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Listed all transactions from person: "
            + "%s";

    private final Index index;

    /**
     * @param index of the person whose the transactions are to be listed
     */
    public ListTransactionCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    private static String generateSuccessMessage(Person selectedPerson) {
        return String.format(MESSAGE_SUCCESS, selectedPerson);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person selectedPerson = lastShownList.get(index.getZeroBased());
        model.updateTransactionList(selectedPerson.getTransactions());

        return new CommandResult(generateSuccessMessage(selectedPerson));
    }
}
