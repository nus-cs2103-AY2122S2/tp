package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INSURANCES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.insurance.Price;
import seedu.address.model.insurance.Title;


/**
 * Edits the details of an existing insurance in the insurance book.
 */
public class EditInsuranceCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " -i : Edits the details of the insurance identified "
            + "by the index number used in the displayed insurance list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_PRICE + "PRICE]\n"
            + "Example: " + COMMAND_WORD + " -i 1 "
            + PREFIX_TITLE + "House 1 ";

    public static final String MESSAGE_EDIT_INSURANCE_SUCCESS = "Edited Insurance: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INSURANCE = "This insurance already exists in the insurance book.";

    private final Index index;
    private final EditInsuranceDescriptor editInsuranceDescriptor;
    private Insurance editedInsurance;
    private Insurance insuranceToEdit;

    /**
     * @param index                   of the insurance in the filtered insurance list to edit
     * @param editInsuranceDescriptor details to edit the insurance with
     */
    public EditInsuranceCommand(Index index, EditInsuranceDescriptor editInsuranceDescriptor) {
        requireNonNull(index);
        requireNonNull(editInsuranceDescriptor);

        this.index = index;
        this.editInsuranceDescriptor = new EditInsuranceDescriptor(editInsuranceDescriptor);
    }

    /**
     * Creates and returns a {@code Insurance} with the details of {@code insuranceToEdit}
     * edited with {@code editInsuranceDescriptor}.
     */
    private static Insurance createEditedInsurance(Insurance insuranceToEdit,
                                                   EditInsuranceDescriptor editInsuranceDescriptor) {
        assert insuranceToEdit != null;

        Title updatedTitle = editInsuranceDescriptor.getTitle().orElse(insuranceToEdit.getTitle());
        Price updatedPrice = editInsuranceDescriptor.getPrice().orElse(insuranceToEdit.getPrice());

        return new Insurance(updatedTitle, updatedPrice);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Insurance> lastShownList = model.getFilteredInsuranceList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INSURANCE_DISPLAYED_INDEX);
        }

        insuranceToEdit = lastShownList.get(index.getZeroBased());
        editedInsurance = createEditedInsurance(insuranceToEdit, editInsuranceDescriptor);

        if (!insuranceToEdit.isSameInsurance(editedInsurance) && model.hasInsurance(editedInsurance)) {
            throw new CommandException(MESSAGE_DUPLICATE_INSURANCE);
        }

        model.setInsurance(insuranceToEdit, editedInsurance);
        model.updateFilteredInsuranceList(PREDICATE_SHOW_ALL_INSURANCES);
        return new CommandResult(String.format(MESSAGE_EDIT_INSURANCE_SUCCESS, editedInsurance));
    }

    /**
     * Returns the edited Insurance.
     */
    public Insurance getEditedInsurance() {
        return editedInsurance;
    }

    /**
     * Returns the insuranceToEdit.
     */
    public Insurance getInsuranceToEdit() {
        return insuranceToEdit;
    }

    /**
     * Returns the edited Insurance index.
     */
    public int getEditedInsuranceIndex() {
        return index.getOneBased();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditInsuranceCommand)) {
            return false;
        }

        // state check
        EditInsuranceCommand e = (EditInsuranceCommand) other;
        return index.equals(e.index)
                && editInsuranceDescriptor.equals(e.editInsuranceDescriptor);
    }

    /**
     * Stores the details to edit the insurance with. Each non-empty field value will replace the
     * corresponding field value of the insurance.
     */
    public static class EditInsuranceDescriptor {
        private Title title;
        private Price price;


        public EditInsuranceDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code toCopy} is used internally.
         */
        public EditInsuranceDescriptor(EditInsuranceDescriptor toCopy) {
            setTitle(toCopy.title);
            setPrice(toCopy.price);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, price);
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
        }

        public void setPrice(Price price) {
            this.price = price;
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditInsuranceDescriptor)) {
                return false;
            }

            // state check
            EditInsuranceDescriptor e = (EditInsuranceDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getPrice().equals(e.getPrice());
        }
    }
}
