package seedu.address.logic.commands;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Field;
import seedu.address.model.person.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    private final List<FieldSortOrder> fieldSortOrderList;

    public static final String MESSAGE_SUCCESS = "List is sorted accordingly!";

    public static class FieldSortOrder {
        private final Prefix fieldPrefix;
        private final boolean isDescendingOrder;

        public FieldSortOrder(Prefix fieldPrefix, boolean isDescendingOrder) {
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


    public SortCommand(List<FieldSortOrder> fieldSortOrderList) {
        requireNonNull(fieldSortOrderList);

        this.fieldSortOrderList = fieldSortOrderList;
    }

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
}
