package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_HAS_INTERVIEW;
import static seedu.address.logic.parser.Type.PERSON;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;

public class DeletePersonCommand extends DeleteCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " [p] : Deletes the applicant identified by the index number used in the displayed applicant list,\n"
            + "Parameters: INDEX (must be a positive integer),\n"
            + "Example: " + COMMAND_WORD + " [p] 1\n";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Applicant: %1$s";

    private final Index targetIndex;

    /**
     * Constructor for DeletePersonCommand.
     * @param targetIndex
     */
    public DeletePersonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownPersonList.get(targetIndex.getZeroBased());

        if (personHasInterview(personToDelete, model)) {
            throw new CommandException(MESSAGE_PERSON_HAS_INTERVIEW);
        }

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete), getType());
    }

    /**
     * Check if applicant that we wish to delete has interview
     *
     * @param person applicant
     * @param model model
     * @return true if applicant has interview
     */
    public boolean personHasInterview(Person person, Model model) {
        List<Interview> lastShownInterviewList = model.getFilteredInterviewList();
        for (int i = 0; i < lastShownInterviewList.size(); i++) {
            if (lastShownInterviewList.get(i).getPerson().equals(person)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePersonCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePersonCommand) other).targetIndex)); // state check
    }

    @Override
    public Type getType() {
        return PERSON;
    }
}


