package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.activity.Activity;
import seedu.address.model.person.Address;
import seedu.address.model.person.ClassCode;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " Command: Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    private static final Logger logger = LogsCenter.getLogger(DeleteCommand.class);

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Person> studentList = model.getAddressBook().getPersonList();
        List<Person> lastShownList = model.getFilteredPersonList();

        try {

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX
                        + " Only " + lastShownList.size() + " person(s) shown in the list.");
            }

            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);

            try {
                batchUpdateDeletedPerson(personToDelete, studentList, model);
            } catch (Exception ex) {
                logger.severe("Batch update failed: " + StringUtil.getDetails(ex));
            }

            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
        } catch (Exception ex) {
            logger.severe("Delete Command failed: " + StringUtil.getDetails(ex));
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX
                    + " Only " + lastShownList.size() + " person(s) shown in the list.");
        }
    }

    /**
     * Batch updates the list when a person's status changes
     * from positive to negative.
     */
    private static void batchUpdateDeletedPerson(Person deletedPerson,
                                                      ObservableList<Person> studentList,
                                                      Model model) {
        if (deletedPerson.isPositive()) {

            List<Person> filteredByClassCodeAndActivityList = studentList.stream()
                    .filter(student -> (student.hasSameClassCode(deletedPerson)
                            || student.hasSameActivity(deletedPerson))
                            && !student.isSamePerson(deletedPerson))
                    .collect(Collectors.toList());

            for (int i = 0; i < filteredByClassCodeAndActivityList.size(); i++) {
                Person currentPerson = filteredByClassCodeAndActivityList.get(i);

                List<Person> positiveRelatedToPerson = studentList.stream()
                        .filter(student -> (student.hasSameClassCode(currentPerson)
                                || student.hasSameActivity(currentPerson))
                                && !student.isSamePerson(deletedPerson)
                                && student.isPositive())
                        .collect(Collectors.toList());

                if (positiveRelatedToPerson.size() == 0) {
                    EditCommand.EditPersonDescriptor tempDescriptor = new EditCommand.EditPersonDescriptor();
                    tempDescriptor.setStatus(new Status(Status.NEGATIVE));
                    Person editedPersonStatus = createEditedPerson(currentPerson, tempDescriptor);
                    model.setPerson(currentPerson, editedPersonStatus);
                }
            }
        }
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit,
                                             EditCommand.EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(personToEdit);

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Status updatedStatus = editPersonDescriptor.getStatus().orElse(personToEdit.getStatus());
        ClassCode updatedClassCode = editPersonDescriptor.getClassCode().orElse(personToEdit.getClassCode());
        Set<Activity> updatedActivity = editPersonDescriptor.getActivities().orElse(personToEdit.getActivities());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedStatus,
                updatedClassCode, updatedActivity);
    }



    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
