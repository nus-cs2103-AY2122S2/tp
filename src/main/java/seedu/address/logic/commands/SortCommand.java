package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Field;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;

/**
 * Sorts the person list in address book based on fields given.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_SUCCESS = "Client list sorted accordingly.";
    public static final String MESSAGE_EMPTY_ERROR =
        "No fields specified. Client list remains unchanged.";
    public static final String DESCENDING_KEYWORD = "desc";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort the client list according to the order of the fields specified.\n"
            + "Parameters: "
            + "[" + Name.PREFIX + "] "
            + "[" + Phone.PREFIX + "] "
            + "[" + Email.PREFIX + "] "
            + "[" + Address.PREFIX + "] "
            + "[" + Remark.PREFIX + "] "
            + "[" + Birthday.PREFIX + "]\n"
            + "Add keyword" + DESCENDING_KEYWORD + " after a prefix to sort in descending order.\n"
            + "Example: " + COMMAND_WORD + " n/ e/ desc a/ n/";

    private final List<FieldSortOrder> fieldSortOrderList;

    /**
     * Creates a SortCommand to sort the person list in address book based on fields given.
     * @param fieldSortOrderList a list of information about the fields to be sorted in the order of the list.
     */
    public SortCommand(List<FieldSortOrder> fieldSortOrderList) {
        requireNonNull(fieldSortOrderList);
        this.fieldSortOrderList = fieldSortOrderList;
    }

    /**
     * Sorts the person list based on fields and information provided.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult stating whether it has been successful.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Comparator<Person> comparator = null;
        for (FieldSortOrder fieldSortOrder : fieldSortOrderList) {
            Comparator<Person> currComperator;
            if (fieldSortOrder.getIsDescendingOrder()) {
                currComperator = getComparatorDescending(fieldSortOrder.getFieldPrefix());
            } else {
                currComperator = getComparatorDefault(fieldSortOrder.getFieldPrefix());
            }

            if (comparator == null) {
                comparator = currComperator;
            } else {
                comparator = comparator.thenComparing(currComperator);
            }
        }

        if (comparator == null) {
            throw new CommandException(MESSAGE_EMPTY_ERROR);
        }

        model.sortPersonList(comparator);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Get Comparator lambda function on how the people are to be sorted based on certain fields.
     *
     * @param fieldPrefix the field to be sorted by
     * @return Comparator lambda function.
     */
    private Comparator<Person> getComparatorDefault(Prefix fieldPrefix) {
        return (p1, p2) -> {
            Optional<Field> p1Field = p1.getField(fieldPrefix);
            Optional<Field> p2Field = p2.getField(fieldPrefix);

            //null values are given lower priority
            if (p1Field.isEmpty() || p2Field.isEmpty()) {
                return compareNullField(p1Field, p2Field);
            }

            return p1Field.get().compareTo(p2Field.get());
        };
    }

    private Comparator<Person> getComparatorDescending(Prefix fieldPrefix) {
        return (p1, p2) -> {
            Optional<Field> p1Field = p1.getField(fieldPrefix);
            Optional<Field> p2Field = p2.getField(fieldPrefix);

            //null values are given lower priority
            if (p1Field.isEmpty() || p2Field.isEmpty()) {
                return compareNullField(p1Field, p2Field);
            }

            return p2Field.get().compareTo(p1Field.get());
        };
    }

    private int compareNullField(Optional<Field> p1Field, Optional<Field> p2Field) {
        //null values would be the last in list in ascending order
        if (p1Field.isEmpty() && p2Field.isPresent()) {
            return 1;
        } else if (p1Field.isPresent() && p2Field.isEmpty()) {
            return -1;
        }

        return 0; //both empty
    }

    /**
     * Stores the details on how a field is to be sorted.
     */
    public static class FieldSortOrder {
        private final Prefix fieldPrefix;
        private final boolean isDescendingOrder;

        /**
         * Constructor of FieldSortOrder.
         *
         * @param fieldPrefix the field.
         * @param isDescendingOrder whether the field should be sorted in descending order.
         */
        public FieldSortOrder(Prefix fieldPrefix, boolean isDescendingOrder) {
            requireAllNonNull(fieldPrefix, isDescendingOrder);

            this.fieldPrefix = fieldPrefix;
            this.isDescendingOrder = isDescendingOrder;
        }

        public Prefix getFieldPrefix() {
            return fieldPrefix;
        }

        public boolean getIsDescendingOrder() {
            return isDescendingOrder;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FieldSortOrder)) {
                return false;
            }

            // state check
            FieldSortOrder f = (FieldSortOrder) other;
            return fieldPrefix.equals(f.fieldPrefix) && isDescendingOrder == f.isDescendingOrder;
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        // state check
        SortCommand s = (SortCommand) other;
        return fieldSortOrderList.equals(s.fieldSortOrderList);
    }
}
