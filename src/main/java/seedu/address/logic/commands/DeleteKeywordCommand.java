package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using keywords from the address book.
 */
public class DeleteKeywordCommand extends Command {

    public static final String COMMAND_WORD = "deleteKeyword";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all filtered people.\n"
            + "Parameters: none\n"
            + "Example: " + COMMAND_WORD + "";

    public static final String MESSAGE_PERSONS_DELETED_OVERVIEW = "%1$d persons deleted!";


    public DeleteKeywordCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = List.copyOf(model.getFilteredPersonList());
        for (Person person : lastShownList) {
            model.deletePerson(person);
        }
        return new CommandResult(String.format(MESSAGE_PERSONS_DELETED_OVERVIEW,  lastShownList.size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this; // short circuit if same object
    }
}
