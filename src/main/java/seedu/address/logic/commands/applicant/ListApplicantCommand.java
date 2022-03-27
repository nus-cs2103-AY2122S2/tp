package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.DataType;
import seedu.address.logic.FilterArgument;
import seedu.address.logic.FilterType;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.NameContainsKeywordsPredicate;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListApplicantCommand extends ListCommand {
    public static final String MESSAGE_SUCCESS_ALL = "Listed all applicants";
    public static final String MESSAGE_SUCCESS_FILTER = "Filtered applicants, %1$d applicants listed";

    private FilterType filterType;
    private FilterArgument filterArgument;

    /**
     * Creates an ListApplicantCommand to display all {@code Applicant}
     */
    public ListApplicantCommand() {
        filterType = null;
        filterArgument = null;
    }

    /**
     * Creates an ListApplicantCommand to filter and display {@code Applicant}
     */
    public ListApplicantCommand(FilterType filterType, FilterArgument filterArgument) {
        this.filterType = filterType;
        this.filterArgument = filterArgument;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (filterType != null && filterArgument != null) {
            String[] nameKeywords = filterArgument.toString().split("\\s+");
            Predicate<Applicant> predicate = new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));
            model.updateFilteredApplicantList(predicate);
            return new CommandResult(
                    String.format(MESSAGE_SUCCESS_FILTER, model.getFilteredApplicantList().size()),
                    getCommandDataType());
        } else {
            model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS_ALL, getCommandDataType());
        }
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.APPLICANT;
    }
}
