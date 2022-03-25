package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

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
            + "[" + PREFIX_MODULE + "MODULE] "
            + "[" + PREFIX_COMMENT + "COMMENT] "
            + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_MODULE + "asc " + PREFIX_NAME + "desc " + "\n";

    public static final String MESSAGE_SUCCESS = "Sorted successfully: %s";

    private final PersonComparator personComparator;
    private final List<Prefix> fields;
    private final List<String> orders;
    private final String successField;
    /**
     * @param fields modules to be deleted
     */
    public SortCommand(List<Prefix> fields, List<String> orders, String successField) {
        this.successField = successField;
        this.orders = orders;
        this.fields = fields;
        this.personComparator = new PersonComparator(fields, orders);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortPerson(personComparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS, successField));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        return (other instanceof SortCommand)
                && this.fields.equals(((SortCommand) other).fields)
                && this.personComparator.equals(((SortCommand) other).personComparator)
                && this.successField.equals(((SortCommand) other).successField)
                && this.orders.equals(((SortCommand) other).orders); // instanceof handles null
    }

    public static class PersonComparator implements Comparator<Person> {
        private final List<Prefix> fields;
        private final List<String> orders;
        /**
         * Create a comparator using the specified fields,
         * using the ordering implied by its iterator.
         * @param fields a list of field names
         */
        public PersonComparator(List<Prefix> fields, List<String> orders) {
            assert fields.size() == orders.size();
            this.fields = fields;
            this.orders = orders;
        }

        @Override
        public int compare(Person o1, Person o2) {
            for (int i = 0; i < fields.size(); i++) {
                int result = 0;
                Prefix field = fields.get(i);
                String order = orders.get(i);

                if (PREFIX_NAME.equals(field)) {
                    result = o1.getName().compareTo(o2.getName());

                } else if (PREFIX_PHONE.equals(field)) {
                    result = o1.getPhone().compareTo(o2.getPhone());

                } else if (PREFIX_EMAIL.equals(field)) {
                    result = o1.getEmail().compareTo(o2.getEmail());

                } else if (PREFIX_ADDRESS.equals(field)) {
                    result = o1.getAddress().compareTo(o2.getAddress());

                } else if (PREFIX_MODULE.equals(field)) {
                    result = Integer.compare(o1.getModules().size(), o2.getModules().size());

                } else if (PREFIX_STATUS.equals(field)) {
                    result = o1.getStatus().compareTo(o2.getStatus());

                } else if (PREFIX_COMMENT.equals(field)) {
                    result = o1.getComment().compareTo(o2.getComment());

                } else {
                    assert false;
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
                    && this.orders.equals(((PersonComparator) other).orders); // instanceof handles null
        }
    }
}
