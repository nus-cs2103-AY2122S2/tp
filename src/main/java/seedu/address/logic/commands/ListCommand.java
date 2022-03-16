package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_NO_COMPANIES;
import static seedu.address.model.Model.PREDICATE_SHOW_NO_EVENTS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        //Show all the persons and empty out all the temporary list for events and companies
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredEventList(PREDICATE_SHOW_NO_EVENTS);
        model.updateFilteredCompanyList(PREDICATE_SHOW_NO_COMPANIES);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false, false);
    }
}
