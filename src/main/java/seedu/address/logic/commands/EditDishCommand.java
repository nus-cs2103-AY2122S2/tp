package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DISHES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.NameDish;
import seedu.address.model.dish.PriceDish;

/**
 * Edits the details of an existing dish in FoodOnWheels.
 */
public class EditDishCommand extends Command {

    public static final String COMMAND_WORD = "editdish";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the dish identified "
            + "by the index number used in the displayed dish list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PRICE + "PRICE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Crab Fried Rice "
            + PREFIX_PRICE + "19.90";

    public static final String MESSAGE_EDIT_DISH_SUCCESS = "Edited Dish: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit "
            + "must be provided and field to edit should not be the same as existing field.";
    public static final String MESSAGE_DUPLICATE_DISH = "This dish already exists in FoodOnWheels.";

    private final Index index;
    private final EditDishDescriptor editDishDescriptor;

    /**
     * @param index of the dish in the filtered dish list to edit
     * @param editDishDescriptor details to edit the dish with
     */
    public EditDishCommand(Index index, EditDishDescriptor editDishDescriptor) {
        requireNonNull(index);
        requireNonNull(editDishDescriptor);

        this.index = index;
        this.editDishDescriptor = new EditDishDescriptor(editDishDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Dish> lastShownList = model.getFilteredDishList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISH_DISPLAYED_INDEX);
        }

        Dish dishToEdit = lastShownList.get(index.getZeroBased());
        Dish editedDish = createEditedDish(dishToEdit, editDishDescriptor);

        if (dishToEdit.equals(editedDish)) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        if (!dishToEdit.isSameDish(editedDish) && model.hasDish(editedDish)) {
            throw new CommandException(MESSAGE_DUPLICATE_DISH);
        }

        model.setDish(dishToEdit, editedDish);
        model.updateFilteredDishList(PREDICATE_SHOW_ALL_DISHES);
        return new CommandResult(String.format(MESSAGE_EDIT_DISH_SUCCESS, editedDish),
                false, false, true, false, false);
    }

    /**
     * Creates and returns a {@code Dish} with the details of {@code dishToEdit}
     * edited with {@code editDishDescriptor}.
     */
    private static Dish createEditedDish(Dish dishToEdit,
            EditDishDescriptor editDishDescriptor) {
        assert dishToEdit != null;

        NameDish updatedNameDish = editDishDescriptor.getName().orElse(dishToEdit.getName());
        PriceDish updatedPriceDish = editDishDescriptor.getPrice().orElse(dishToEdit.getPrice());

        return new Dish(updatedNameDish, updatedPriceDish);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditDishCommand)) {
            return false;
        }

        // state check
        EditDishCommand e = (EditDishCommand) other;
        return index.equals(e.index)
                && editDishDescriptor.equals(e.editDishDescriptor);
    }

    /**
     * Stores the details to edit the dish with. Each non-empty field value will replace the
     * corresponding field value of the dish.
     */
    public static class EditDishDescriptor {
        private NameDish nameDish;
        private PriceDish priceDish;

        public EditDishDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditDishDescriptor(EditDishDescriptor toCopy) {
            setName(toCopy.nameDish);
            setPrice(toCopy.priceDish);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(nameDish, priceDish);
        }

        public void setName(NameDish nameDish) {
            this.nameDish = nameDish;
        }

        public Optional<NameDish> getName() {
            return Optional.ofNullable(nameDish);
        }

        public void setPrice(PriceDish priceDish) {
            this.priceDish = priceDish;
        }

        public Optional<PriceDish> getPrice() {
            return Optional.ofNullable(priceDish);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditDishDescriptor)) {
                return false;
            }

            // state check
            EditDishDescriptor e = (EditDishDescriptor) other;

            return getName().equals(e.getName())
                    && getPrice().equals(e.getPrice());
        }
    }
}

