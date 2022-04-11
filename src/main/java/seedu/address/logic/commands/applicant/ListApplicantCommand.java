package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_ARGUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ARGUMENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;

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
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantGenderPredicate;
import seedu.address.model.applicant.ApplicantNameComparator;
import seedu.address.model.applicant.ApplicantNamePredicate;
import seedu.address.model.applicant.ApplicantStatusPredicate;
import seedu.address.model.applicant.ApplicantTagPredicate;

/**
 * Lists applicants in HireLah to the user.
 */
public class ListApplicantCommand extends ListCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " -a: List applicants with optional parameters."
            + "\nOptional parameters: "
            + PREFIX_FILTER_TYPE + "FILTER_TYPE "
            + PREFIX_FILTER_ARGUMENT + "FILTER_TYPE "
            + PREFIX_SORT_ARGUMENT + "[asc/dsc] "
            + "\nExample: " + COMMAND_WORD + " -a "
            + PREFIX_FILTER_TYPE + "gender "
            + PREFIX_FILTER_ARGUMENT + "M "
            + PREFIX_SORT_ARGUMENT + "dsc ";

    public static final String MESSAGE_SUCCESS = "Listed %1$d applicants";

    private FilterType filterType;
    private FilterArgument filterArgument;
    private SortArgument sortArgument;

    /**
     * Creates an ListApplicantCommand to display all {@code Applicant}
     */
    public ListApplicantCommand() {
        filterType = null;
        filterArgument = null;
        sortArgument = null;
    }

    /**
     * Creates an ListApplicantCommand to filter and display {@code Applicant}
     */
    public ListApplicantCommand(FilterType filterType, FilterArgument filterArgument) {
        this.filterType = filterType;
        this.filterArgument = filterArgument;
    }

    public ListApplicantCommand(SortArgument sortArgument) {
        this.sortArgument = sortArgument;
    }

    /**
     * Creates an ListApplicantCommand to filter and sort then display {@code Applicant}
     */
    public ListApplicantCommand(FilterType filterType, FilterArgument filterArgument, SortArgument sortArgument) {
        this.filterArgument = filterArgument;
        this.filterType = filterType;
        this.sortArgument = sortArgument;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (filterType != null && filterArgument != null && sortArgument != null) {
            Predicate<Applicant> predicate = getFilterPredicate(filterType, filterArgument);
            Comparator<Applicant> comparator = new ApplicantNameComparator(sortArgument.toString());
            model.updateFilterAndSortApplicantList(predicate, comparator);
        } else if (filterType != null && filterArgument != null) {
            Predicate<Applicant> predicate = getFilterPredicate(filterType, filterArgument);
            model.updateFilteredApplicantList(predicate);
        } else if (sortArgument != null) {
            Comparator<Applicant> comparator = new ApplicantNameComparator(sortArgument.toString());
            model.updateSortApplicantList(comparator);
        } else {
            model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        }

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredApplicantList().size()), getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.APPLICANT;
    }

    /**
     * Returns the suitable {@code Predicate} based on the given {@code filterType} and {@code filterArgument}
     */
    public Predicate<Applicant> getFilterPredicate(FilterType filterType, FilterArgument filterArgument) {
        if (filterType.type.equals("name")) {
            String[] nameKeywords = filterArgument.toString().split("\\s+");
            return new ApplicantNamePredicate(Arrays.asList(nameKeywords));
        } else if (filterType.type.equals("gender")) {
            return new ApplicantGenderPredicate(filterArgument.toString());
        } else if (filterType.type.equals("status")) {
            return new ApplicantStatusPredicate(filterArgument.toString());
        } else if (filterType.type.equals("tag")) {
            String[] tagKeywords = filterArgument.toString().split("\\s+");
            return new ApplicantTagPredicate(Arrays.asList(tagKeywords));
        }

        assert true : "Filter type should be valid";
        return null;
    }
}
