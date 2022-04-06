package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " Command: Adds a person to the address book. "
            + "\nParameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_STATUS + "STATUS "
            + PREFIX_CLASSCODE + "CLASSCODE "
            + "[" + PREFIX_ACTIVITY + "ACTIVITIES]..."
            + "\n\n" + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Candice N Utz "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "candicenuts@example.com "
            + PREFIX_ADDRESS + "123, Sunrise Road, #01-25 "
            + PREFIX_STATUS + "Negative "
            + PREFIX_CLASSCODE + "4A "
            + PREFIX_ACTIVITY + "Basketball "
            + PREFIX_ACTIVITY + "Dance";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private static final Logger logger = LogsCenter.getLogger(AddCommand.class);
    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        ObservableList<Person> studentList = model.getAddressBook().getPersonList();

        assert toAdd != null : "A person should not be null";

        model.addPerson(toAdd);

        try {
            batchUpdateNegativeToPositive(toAdd, studentList, model);
        } catch (Exception ex) {
            logger.severe("Batch update failed: " + StringUtil.getDetails(ex));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    /**
     * Batch updates the list when a new person's with positive status joins the class and/or activity
     */
    private static void batchUpdateNegativeToPositive(Person addedPerson,
                                                      ObservableList<Person> studentList,
                                                      Model model) {
        assert addedPerson != null : "A person should not be null";
        assert studentList != null : "The student list should not be null";
        assert model != null : "A model should not be null";

        if (addedPerson.isPositive()) {

            List<Person> filteredByClassCodeList = studentList.stream()
                    .filter(student -> (student.hasSameClassCode(addedPerson)
                            || student.hasSameActivity(addedPerson))
                            && !student.isSamePerson(addedPerson)
                            && !student.isPositive())
                    .collect(Collectors.toList());

            for (int i = 0; i < filteredByClassCodeList.size(); i++) {
                Person currentPerson = filteredByClassCodeList.get(i);
                assert currentPerson != null : "A person should not be null";
                ModelManager.editPersonStatus(currentPerson, new Status(Status.CLOSE_CONTACT), model);
            }
        } else {
            List<Person> filteredByClassCodeAndActivityList = studentList.stream()
                    .filter(student -> (student.hasSameClassCode(addedPerson)
                            || student.hasSameActivity(addedPerson))
                            && !student.isSamePerson(addedPerson))
                    .collect(Collectors.toList());

            for (int i = 0; i < filteredByClassCodeAndActivityList.size(); i++) {
                Person currentPerson = filteredByClassCodeAndActivityList.get(i);
                assert currentPerson != null : "A person should not be null";
                List<Person> positiveRelatedToPerson = studentList.stream()
                        .filter(student -> (student.hasSameClassCode(currentPerson)
                                || student.hasSameActivity(currentPerson))
                                && !student.isSamePerson(addedPerson)
                                && student.isPositive())
                        .collect(Collectors.toList());

                if (positiveRelatedToPerson.size() == 0) {
                    ModelManager.editPersonStatus(currentPerson, new Status(Status.NEGATIVE), model);
                } else {
                    ModelManager.editPersonStatus(addedPerson, new Status(Status.CLOSE_CONTACT), model);
                }
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
