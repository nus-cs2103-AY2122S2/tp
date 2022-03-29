package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_ARGUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_TYPE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
import seedu.address.model.applicant.ApplicantNameComparator;
import seedu.address.model.applicant.ApplicantNamePredicate;

/**
 * Lists applicants in HireLah to the user.
 */
public class ListApplicantCommand extends ListCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " -a: List applicants with optional parameters."
            + "\nOptional parameters: "
            + PREFIX_FILTER_TYPE + "FILTER_TYPE "
            + PREFIX_FILTER_ARGUMENT + "FILTER_TYPE "
            + "\nExample: " + COMMAND_WORD + " -a "
            + PREFIX_FILTER_TYPE + "name "
            + PREFIX_FILTER_ARGUMENT + "John Doe ";

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
            if (filterType.type.equals("name")) {
                String[] nameKeywords = filterArgument.toString().split("\\s+");
                Predicate<Applicant> predicate = new ApplicantNamePredicate(Arrays.asList(nameKeywords));
                Comparator<Applicant> comparator = new ApplicantNameComparator(sortArgument.toString());
                model.updateFilterAndSortApplicantList(predicate, comparator);
            }
        } else if (filterType != null && filterArgument != null) {
            if (filterType.type.equals("name")) {
                String[] nameKeywords = filterArgument.toString().split("\\s+");
                Predicate<Applicant> predicate = new ApplicantNamePredicate(Arrays.asList(nameKeywords));
                model.updateFilteredApplicantList(predicate);
            }
        } else if (sortArgument != null) {
            Comparator<Applicant> comparator = new ApplicantNameComparator(sortArgument.toString());
            model.updateSortApplicantList(comparator);
        } else {
            model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_PERSONS);
        }

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredApplicantList().size()), getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.APPLICANT;
    }
}
