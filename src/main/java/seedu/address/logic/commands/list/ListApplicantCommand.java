package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.DataType;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListApplicantCommand extends ListCommand {
    public static final String MESSAGE_SUCCESS = "Listed all applicants";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.APPLICANT;
    }
}
