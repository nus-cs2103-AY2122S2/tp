package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Field;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Sorts the person list in address book based on fields given.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_SUCCESS = "List is sorted accordingly!";
    public static final String DESCENDING_KEYWORD = "desc";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": sorts the person list in the field order specified.\n"
            + "Parameters: Any field prefix "
            + "[" + Name.PREFIX + "] "
            + "[" + Phone.PREFIX + "] "
            + "[" + Email.PREFIX + "] "
            + "[" + Address.PREFIX + "] "
            + "[" + Remark.PREFIX + "] "
            + "[" + Tag.PREFIX + "]...\n"
            + "Add keyword" + DESCENDING_KEYWORD + " after a field if it's to be sorted in descending order.\n"
            + "Example: " + COMMAND_WORD + " n/ e/ desc a/ n/";

    private final List<FieldSortOrder> fieldSortOrderList;

    /**
     *  Creates a SortCommand to sort the person list in address book based on fields given.
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
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Comparator<Person> comparator = null;
        for (FieldSortOrder fieldSortOrder : fieldSortOrderList) {
            Comparator<Person> currComperator = getComparator(fieldSortOrder.getFieldPrefix());
            if (fieldSortOrder.getIsDescendingOrder()) {
                currComperator = currComperator.reversed();
            }

            if (comparator == null) {
                comparator = currComperator;
            } else {
                comparator = comparator.thenComparing(currComperator);
            }
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
    private Comparator<Person> getComparator(Prefix fieldPrefix) {
        return (p1, p2) -> {
            Optional<Field> p1Field = p1.getField(fieldPrefix);
            Optional<Field> p2Field = p2.getField(fieldPrefix);

            //null values would be the last in list in ascending order
            if (p1Field.isEmpty() && p2Field.isEmpty()) {
                return 0;
            } else if (p1Field.isEmpty() && p2Field.isPresent()) {
                return 1;
            } else if (p1Field.isPresent() && p2Field.isEmpty()) {
                return -1;
            }

            String p1Value = p1Field.get().getValue().toLowerCase(Locale.ROOT);
            String p2Value = p2Field.get().getValue().toLowerCase(Locale.ROOT);

            return p1Value.compareTo(p2Value);
        };
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
    }
}

