package seedu.address.logic.commands.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_ARGUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ARGUMENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_POSITIONS;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import seedu.address.commons.core.DataType;
import seedu.address.logic.FilterArgument;
import seedu.address.logic.FilterType;
import seedu.address.logic.SortArgument;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.Model;
import seedu.address.model.position.Position;
import seedu.address.model.position.PositionNameComparator;
import seedu.address.model.position.PositionNamePredicate;
import seedu.address.model.position.PositionRequirementPredicate;

/**
 * Lists positions in HireLah to the user.
 */
public class ListPositionCommand extends ListCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " -p: List positions with optional parameters."
            + "\nOptional parameters: "
            + PREFIX_FILTER_TYPE + "FILTER_TYPE "
            + PREFIX_FILTER_ARGUMENT + "FILTER_TYPE "
            + PREFIX_SORT_ARGUMENT + "[asc/dsc] "
            + "\nExample: " + COMMAND_WORD + " -p "
            + PREFIX_FILTER_TYPE + "req "
            + PREFIX_FILTER_ARGUMENT + "Java "
            + PREFIX_SORT_ARGUMENT + "asc ";

    public static final String MESSAGE_SUCCESS = "Listed %1$d positions";

    private FilterType filterType;
    private FilterArgument filterArgument;
    private SortArgument sortArgument;

    /**
     * Creates an ListPositionCommand to display all {@code Position}
     */
    public ListPositionCommand() {
        filterType = null;
        filterArgument = null;
        sortArgument = null;
    }

    /**
     * Creates an ListPositionCommand to filter and display {@code Position}
     */
    public ListPositionCommand(FilterType filterType, FilterArgument filterArgument) {
        this.filterType = filterType;
        this.filterArgument = filterArgument;
    }

    public ListPositionCommand(SortArgument sortArgument) {
        this.sortArgument = sortArgument;
    }

    /**
     * Creates an ListApplicantCommand to filter and sort then display {@code Position}
     */
    public ListPositionCommand(FilterType filterType, FilterArgument filterArgument, SortArgument sortArgument) {
        this.filterType = filterType;
        this.filterArgument = filterArgument;
        this.sortArgument = sortArgument;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (filterType != null && filterArgument != null && sortArgument != null) {
            Predicate<Position> predicate = getFilterPredicate(filterType, filterArgument);
            Comparator<Position> comparator = new PositionNameComparator(sortArgument.toString());
            model.updateFilterAndSortPositionList(predicate, comparator);
        } else if (filterType != null && filterArgument != null) {
            Predicate<Position> predicate = getFilterPredicate(filterType, filterArgument);
            model.updateFilteredPositionList(predicate);
        } else if (sortArgument != null) {
            Comparator<Position> comparator = new PositionNameComparator(sortArgument.toString());
            model.updateSortPositionList(comparator);
        } else {
            model.updateFilteredPositionList(PREDICATE_SHOW_ALL_POSITIONS);
        }

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredPositionList().size()), getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.POSITION;
    }

    /**
     * Returns the suitable {@code Predicate} based on the given {@code filterType} and {@code filterArgument}
     */
    public Predicate<Position> getFilterPredicate(FilterType filterType, FilterArgument filterArgument) {
        if (filterType.type.equals("name")) {
            String[] nameKeywords = filterArgument.toString().split("\\s+");
            return new PositionNamePredicate(Arrays.asList(nameKeywords));
        } else if ((filterType.type.equals("req"))) {
            String[] reqKeywords = filterArgument.toString().split("\\s+");
            return new PositionRequirementPredicate(Arrays.asList(reqKeywords));
        }

        assert true : "Filter type should be valid";
        return null;
    }
}
