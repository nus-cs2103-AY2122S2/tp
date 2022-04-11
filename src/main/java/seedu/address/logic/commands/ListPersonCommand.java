package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.PERSON;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.parser.Type;
import seedu.address.model.Model;

public class ListPersonCommand extends ListCommand {
    public static final String MESSAGE_SUCCESS = "Listed all applicants!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, getType());
    }

    @Override
    public Type getType() {
        return PERSON;
    }
}
