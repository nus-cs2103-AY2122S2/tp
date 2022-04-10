package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.record.Record;

/**
 * Sorts all record in record book ascending/descending order in terms of start/end date
 */
public class SortRecordCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String SORT_START_DATE_ASC = "sa";
    public static final String SORT_START_DATE_DES = "sd";
    public static final String SORT_END_DATE_ASC = "ea";
    public static final String SORT_END_DATE_DES = "ed";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all record in record book in ascending or descending order in terms of start/end date.\n"
            + "Example: " + COMMAND_WORD + " -r sa " + "('sa' for sort start date in ascending order). \n"
            + COMMAND_WORD + " -r ed " + "('ed' for sort end date in descending order). \n";

    private final String sortOption;

    /**
     * @param sortOption sort in ascending/descending order
     */
    public SortRecordCommand(String sortOption) {
        this.sortOption = sortOption;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Comparator<Record> comparator;
        String message = "";
        switch (sortOption) {
        case SORT_START_DATE_ASC:
            comparator = Comparator.comparing(Record::getStartLocalDate);
            message = String.format(Messages.MESSAGE_RECORD_SORTED_START_TIME_ASCENDING,
                    model.getFilteredRecordList().size());
            break;
        case SORT_START_DATE_DES:
            comparator = (Record a1, Record a2) -> a2.getStartLocalDate().compareTo(a1.getStartLocalDate());
            message = String.format(Messages.MESSAGE_RECORD_SORTED_START_TIME_DESCENDING,
                    model.getFilteredRecordList().size());
            break;
        case SORT_END_DATE_ASC:
            comparator = Comparator.comparing(Record::getEndLocalDate);
            message = String.format(Messages.MESSAGE_RECORD_SORTED_END_TIME_ASCENDING,
                    model.getFilteredRecordList().size());
            break;
        case SORT_END_DATE_DES:
            comparator = (Record a1, Record a2) -> a2.getEndLocalDate().compareTo(a1.getEndLocalDate());
            message = String.format(Messages.MESSAGE_RECORD_SORTED_END_TIME_DESCENDING,
                    model.getFilteredRecordList().size());
            break;
        default:
            // A comparator that does nothing
            comparator = (Record a1, Record a2) -> 0;
        }
        model.sortRecordBook(comparator);
        return new CommandResult(message, false, false, false,
                true, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortRecordCommand // instanceof handles nulls
                && sortOption.equals(((SortRecordCommand) other).sortOption)); // state check
    }
}
