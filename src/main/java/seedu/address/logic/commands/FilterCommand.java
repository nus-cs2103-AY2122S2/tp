package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COVID_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;

import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.person.CovidStatus;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Person;

/**
 * Lists all persons with a specified covid status and/or faculty in the address book.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who are of the specified health "
            + "status (case-insensitive) and/or faculty (case-insensitive) and displays "
            + "them as a list with index numbers.\n"
            + "Parameters: " + PREFIX_COVID_STATUS + "[COVID STATUS] " + PREFIX_FACULTY + "[FACULTY]"
            + "Example: " + COMMAND_WORD + " " + PREFIX_COVID_STATUS + " positive " + PREFIX_FACULTY + "soc";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    private FilterDescriptor filterDescriptor;

    /**
     * Constructor for this class to create a FilterCommand object.
     *
     * @param filterDescriptor containing the predicates require to output the filtered list.
     */
    public FilterCommand(FilterDescriptor filterDescriptor) {
        this.filterDescriptor = filterDescriptor;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ArrayList<Predicate<Person>> filters = filterDescriptor.getFilters();
        for (Predicate<Person> filter : filters) {
            model.updateFilteredPersonList(filter);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        // state check
        FilterCommand f = (FilterCommand) other;
        return filterDescriptor.equals(f.filterDescriptor);
    }

    /**
     * Stores the filters requested by the user.
     */
    public static class FilterDescriptor {
        private Faculty faculty;
        private CovidStatus status;
        private ArrayList<Predicate<Person>> filters = new ArrayList<>();

        private Predicate<Person> covidStatusFilter = person -> person.isStatus(status);
        private Predicate<Person> facultyFilter = person -> person.isFaculty(faculty);

        public ArrayList<Predicate<Person>> getFilters() {
            return filters;
        }

        public void setFaculty(Faculty faculty) {
            this.faculty = faculty;
            filters.add(facultyFilter);
        }

        public void setCovidStatus(CovidStatus status) {
            this.status = status;
            filters.add(covidStatusFilter);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls and other types
            if (!(other instanceof FilterDescriptor)) {
                return false;
            }

            // state check
            FilterDescriptor f = (FilterDescriptor) other;
            if (faculty == null) {
                if (f.faculty != null) {
                    return false;
                } else if (status == null) {
                    return f.status == null;
                } else {
                    return status.equals(f.status);
                }
            }

            if (status == null) {
                return f.status == null;
            }

            return faculty.equals(f.faculty)
                    && status.equals(f.status);
        }
    }
}
