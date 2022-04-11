package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.person.Address;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;

public class DeleteModulesCommand extends RedoableCommand {
    public static final String COMMAND_WORD = "deletemodules";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clear specified modules of the person identified by the index "
            + "number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer and less than 2,147,483,647) "
            + "[" + PREFIX_MODULE + "MODULE]...\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_MODULE + "CS3230 " + PREFIX_MODULE + "CS1231S\n";

    public static final String MESSAGE_SUCCESS = "Deleted Modules for %s: %s";
    public static final String MESSAGE_FAILURE = "These modules were not found: %s";

    private final Index targetIndex;

    private final List<Module> modules;

    /**
     * @param targetIndex of the person in the filtered person list
     * @param modules     modules to be deleted
     */
    public DeleteModulesCommand(Index targetIndex, List<Module> modules) {
        this.targetIndex = targetIndex;
        this.modules = modules;
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, List<Module> modules) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Status updatedStatus = personToEdit.getStatus();
        Comment updatedComment = personToEdit.getComment();

        Set<Module> oldModules = personToEdit.getModules();
        Set<Module> updatedModules = new HashSet<>(oldModules);
        modules.removeIf(updatedModules::remove);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedStatus, updatedModules, updatedComment);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModulesCommand // instanceof handles nulls
                        && targetIndex.equals(((DeleteModulesCommand) other).targetIndex)) // state check
                        && modules.equals(((DeleteModulesCommand) other).modules);
    }

    @Override
    public CommandResult executeUndoableCommand(Model model) throws CommandException {

        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        List<Module> modulesToDelete = new ArrayList<>(modules);
        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, modulesToDelete);

        if (modulesToDelete.size() != 0) {
            throw new CommandException(String.format(MESSAGE_FAILURE, modulesToDelete));
        }

        model.setPerson(personToEdit, editedPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedPerson.getName(), modules));
    }
}
