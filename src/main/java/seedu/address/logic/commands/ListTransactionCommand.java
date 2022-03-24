package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


public class ListTransactionCommand extends Command {

    public static final String COMMAND_WORD = "listTransaction";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all transactions ";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
