package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.DeadlineList;
import seedu.address.model.person.Person;

public class DeadlineCommand extends Command implements DetailedViewExecutable {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Gives a deadline with a description to person identified\n"
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)"
            + PREFIX_DEADLINE + "DESCRIPTION DATE (DATE should be in dd/mm/yyyy)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DEADLINE + "windows 01/01/2022";

    public static final String MESSAGE_ADD_DEADLINE_SUCCESS = "Added deadline for: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_NO_DEADLINES_ADDED = "No deadlines given.";

    private final Index targetIndex;
    private final DeadlineList deadlines;

    /**
     * Creates a DeadlineCommand to add to specified {@code Person}.
     *
     * @param targetIndex the index of the person specified.
     * @param deadlines   the date of the deadline.
     */
    public DeadlineCommand(Index targetIndex, DeadlineList deadlines) {
        requireNonNull(targetIndex);
        requireNonNull(deadlines);
        this.targetIndex = targetIndex;
        this.deadlines = deadlines;
    }

    /**
     * Creates a DeadlineCommand to add to {@code Person} in detailed view.
     *
     * @param deadlines the date of the deadline.
     */
    public DeadlineCommand(DeadlineList deadlines) {
        requireNonNull(deadlines);
        this.targetIndex = null;
        this.deadlines = deadlines;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getSortedPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddDeadline = lastShownList.get(targetIndex.getZeroBased());
        DeadlineList newDeadlineList = personToAddDeadline.getDeadlines().appendDeadlines(deadlines);
        Person editedPerson = new Person(
                personToAddDeadline.getName(), personToAddDeadline.getPhone(), personToAddDeadline.getEmail(),
                personToAddDeadline.getAddress(), newDeadlineList, personToAddDeadline.getNotes(),
                personToAddDeadline.getTags(), personToAddDeadline.getFavouriteStatus(),
                personToAddDeadline.getHighImportanceStatus(), personToAddDeadline.getImageDetailsList());

        if (!personToAddDeadline.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToAddDeadline, editedPerson);
        return new CommandResult(String.format(MESSAGE_ADD_DEADLINE_SUCCESS, personToAddDeadline));
    }

    @Override
    public CommandResult executeInDetailedView(Model model) {
        requireNonNull(model);

        Person personToAddDeadline = model.getDetailedContactViewPerson();
        DeadlineList newDeadlineList = personToAddDeadline.getDeadlines().appendDeadlines(deadlines);
        Person editedPerson = new Person(
                personToAddDeadline.getName(), personToAddDeadline.getPhone(), personToAddDeadline.getEmail(),
                personToAddDeadline.getAddress(), newDeadlineList, personToAddDeadline.getNotes(),
                personToAddDeadline.getTags(), personToAddDeadline.getFavouriteStatus(),
                personToAddDeadline.getHighImportanceStatus(), personToAddDeadline.getImageDetailsList());

        model.setPerson(personToAddDeadline, editedPerson);
        model.setDetailedContactView(editedPerson);
        return new CommandResult(String.format(MESSAGE_ADD_DEADLINE_SUCCESS, personToAddDeadline),
                CommandResult.SpecialCommandResult.DETAILED_VIEW);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeadlineCommand)) {
            return false;
        }

        // state check
        DeadlineCommand e = (DeadlineCommand) other;
        return Objects.equals(this.targetIndex, e.targetIndex)
                && deadlines.equals(e.deadlines);
    }

}
