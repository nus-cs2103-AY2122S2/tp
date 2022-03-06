package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Comparator;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts entries in the AddressBook by the specified field(s).\n"
            + "Order parameters are mandatory but specifying order is optional.\n"
            + "All other parameters are optional.\n"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_TAG + "TAG] "
            + "[" + PREFIX_ORDER + "ORDER]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TAG + " " + PREFIX_NAME
            + " " + PREFIX_ORDER + "desc" + "\n";

    public static final String MESSAGE_SUCCESS = "Sorted Modules successfully";

    private final PersonComparator personComparator;
    private final List<Prefix> fields;
    /**
     * @param fields modules to be deleted
     */
    public SortCommand(List<Prefix> fields, String order) {
        this.fields = fields;
        this.personComparator = new PersonComparator(fields, order);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortPerson(personComparator);
        return new CommandResult(MESSAGE_SUCCESS + ": " + fields.toString());
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        return (other instanceof SortCommand)
                && this.fields.equals(((SortCommand) other).fields)
                && this.personComparator.equals(((SortCommand) other).personComparator); // instanceof handles null
    }

    public static class PersonComparator implements Comparator<Person> {
        private final List<Prefix> fields;
        private final String order;
        /**
         * Create a comparator using the specified fields,
         * using the ordering implied by its iterator.
         * @param fields a list of field names
         */
        public PersonComparator(List<Prefix> fields, String order) {
            this.fields = fields;
            this.order = order;
        }

        @Override
        public int compare(Person o1, Person o2) {
            for (Prefix field : fields) {
                int result = 0;
                if (PREFIX_NAME.equals(field)) {
                    result = o1.getName().compareTo(o2.getName());

                } else if (PREFIX_PHONE.equals(field)) {
                    result = o1.getPhone().compareTo(o2.getPhone());

                } else if (PREFIX_EMAIL.equals(field)) {
                    result = o1.getEmail().compareTo(o2.getEmail());

                } else if (PREFIX_ADDRESS.equals(field)) {
                    result = o1.getAddress().compareTo(o2.getAddress());

                } else if (PREFIX_TAG.equals(field)) {
                    result = Integer.compare(o1.getTags().size(), o2.getTags().size());

                } else if (PREFIX_STATUS.equals(field)) {
                    result = o1.getStatus().compareTo(o2.getStatus());

                }

                if (order.equals("desc")) {
                    result *= -1;
                }

                if (result != 0) {
                    return result;
                }
            }
            return 0;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            return (other instanceof PersonComparator)
                    && this.fields.equals(((PersonComparator) other).fields)
                    && this.order.equals(((PersonComparator) other).order); // instanceof handles null
        }
    }
}
