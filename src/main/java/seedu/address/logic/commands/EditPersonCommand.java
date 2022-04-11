package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonAmount;

public class EditPersonCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the amount of the person identified "
            + "by the index number used in the displayed person list. \n"
            + "Existing amount will be overwritten by the input amount.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_AMOUNT + "AMOUNT \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_AMOUNT + "120\n\n";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "One field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person with the specified amount already exists in "
            + "Expense Expert.";
    private final Index index;
    private final EditPersonCommand.EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditPersonCommand(Index index, EditPersonCommand.EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonCommand.EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.equals(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Expense} with the details of {@code expenseToEdit}
     * edited with {@code editExpenseDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonCommand.EditPersonDescriptor
            editPersonDescriptor) {
        assert personToEdit != null;
        PersonAmount updatedAmount = editPersonDescriptor.getPersonAmount().orElse(personToEdit.getPersonAmount());
        return new Person(personToEdit.getPersonName(), updatedAmount);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPersonCommand)) {
            return false;
        }

        // state check
        EditPersonCommand e = (EditPersonCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the expense with. Each non-empty field value will replace the
     * corresponding field value of the expense.
     */
    public static class EditPersonDescriptor {
        private PersonAmount amount;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonCommand.EditPersonDescriptor toCopy) {
            setPersonAmount(toCopy.amount);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(amount);
        }

        public void setPersonAmount(PersonAmount amount) {
            this.amount = amount;
        }

        public Optional<PersonAmount> getPersonAmount() {
            return Optional.ofNullable(amount);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonCommand.EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonCommand.EditPersonDescriptor e = (EditPersonCommand.EditPersonDescriptor) other;

            return getPersonAmount().equals(e.getPersonAmount());
        }
    }
}


