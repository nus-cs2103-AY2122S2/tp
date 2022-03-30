package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COVID_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;

import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Block;
import seedu.address.model.person.CovidStatus;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Person;

/**
 * Lists all persons with a specified covid status and/or faculty in the address book.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who are of the specified health "
            + "status, faculty, and/or block (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: " + PREFIX_COVID_STATUS + "[COVID STATUS] " + PREFIX_FACULTY + "[FACULTY] "
            + PREFIX_BLOCK + "[BLOCK]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_COVID_STATUS + "positive " + PREFIX_FACULTY + "soc "
            + PREFIX_BLOCK + "e";

    public static final String MESSAGE_SUCCESS = "Listed all persons.";
    public static final String MESSAGE_NO_VALID_FILTERS = "No valid filters added.";

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
    public CommandResult execute(Model model) throws CommandException {
        if (filterDescriptor.isEmpty()) {
            throw new CommandException(MESSAGE_NO_VALID_FILTERS);
        }

        requireNonNull(model);
        Predicate<Person> filters = filterDescriptor.getFilters();
        model.updateFilteredPersonList(filters);

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
        private static final int FACULTY_INDEX = 0;
        private static final int COVID_STATUS_INDEX = 1;
        private static final int BLOCK_INDEX = 2;

        private Faculty faculty;
        private CovidStatus status;
        private Block block;
        private ArrayList<Predicate<Person>> filters = new ArrayList<>();

        private Predicate<Person> defaultFilter = person -> true;

        private Predicate<Person> facultyFilter = person -> person.isFaculty(faculty);
        private Predicate<Person> covidStatusFilter = person -> person.isStatus(status);
        private Predicate<Person> blockFilter = person -> person.isBlock(block);

        /**
         * Constructor to create a FilterDescriptor object.
         */
        public FilterDescriptor() {
            filters.add(defaultFilter);
            filters.add(defaultFilter);
            filters.add(defaultFilter);
        }

        public Predicate<Person> getFilters() {
            return filters.get(FACULTY_INDEX).and(filters.get(COVID_STATUS_INDEX)).and(filters.get(BLOCK_INDEX));
        }

        public void setFaculty(Faculty faculty) {
            this.faculty = faculty;
            filters.set(FACULTY_INDEX, facultyFilter);
        }

        public void setCovidStatus(CovidStatus status) {
            this.status = status;
            filters.set(COVID_STATUS_INDEX, covidStatusFilter);
        }

        public void setBlock(Block block) {
            this.block = block;
            filters.set(BLOCK_INDEX, blockFilter);
        }

        public boolean isEmpty() {
            return filters.get(FACULTY_INDEX) == defaultFilter
                    && filters.get(COVID_STATUS_INDEX) == defaultFilter
                    && filters.get(BLOCK_INDEX) == defaultFilter;
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
            boolean sameFaculty;
            boolean sameCovidStatus;
            boolean sameBlock;

            if (faculty == null) {
                sameFaculty = (f.faculty == null);
            } else {
                sameFaculty = faculty.equals(f.faculty);
            }

            if (status == null) {
                sameCovidStatus = (f.status == null);
            } else {
                sameCovidStatus = status.equals(f.status);
            }

            if (block == null) {
                sameBlock = (f.block == null);
            } else {
                sameBlock = block.equals(f.block);
            }

            return sameFaculty && sameCovidStatus && sameBlock;
        }
    }
}
