package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.DeleteModuleCommand.MESSAGE_FAILURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
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


public class AddModuleCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "addmodule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add specified modules to the person identified "
            + "by the index number used in the displayed person list. "
            + "Input values will be added on to existing modules.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MODULE + "MODULE]...\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_MODULE + "CS3230 " + PREFIX_MODULE + "CS1231S\n";

    /**
     * Arg1 is Person's Name
     * Arg2 is List of Modules added
     */
    public static final String MESSAGE_SUCCESS = "Added Modules for %s: %s";
    public static final String MESSAGE_DUPLICATE_MODULES_EXIST = "Some Module(s) already exists, ";
    public static final String MESSAGE_NO_NEW_MODULES_ADDED = "no new Modules added, current Modules that %s has: %s";

    private final Index targetIndex;
    private final List<Module> modulesToAdd;


    /**
     * @param targetIndex of the person in the filtered person list
     * @param modulesToAdd modules to be added
     */
    public AddModuleCommand(Index targetIndex, List<Module> modulesToAdd) {
        this.targetIndex = targetIndex;
        this.modulesToAdd = modulesToAdd;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult executeUndoableCommand(Model model, CommandHistory commandHistory,
                                                StackUndoRedo undoRedoStack) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (modulesToAdd.size() == 0) {
            throw new CommandException(String.format(MESSAGE_FAILURE, modulesToAdd));
        }

        List<Module> modulesToAddActual = new ArrayList<>(modulesToAdd);
        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, modulesToAdd);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return getCommandResult(personToEdit, editedPerson, modulesToAddActual);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, List<Module> modulesToAdd) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Status updatedStatus = personToEdit.getStatus();
        Comment updatedComment = personToEdit.getComment();

        Set<Module> oldModules = personToEdit.getModules();
        Set<Module> updatedModules = new HashSet<>(oldModules);
        updatedModules.addAll(modulesToAdd);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedStatus, updatedModules, updatedComment);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleCommand // instanceof handles nulls
                && targetIndex.equals(((AddModuleCommand) other).targetIndex)) // state check
                && modulesToAdd.equals(((AddModuleCommand) other).modulesToAdd);
    }

    /**
     * @return true if personToEdit contains any modules in proposedModules
     */
    private boolean hasModulesInCommon(Person personToEdit, List<Module> proposedModules) {
        Set<Module> existingModules = personToEdit.getModules();
        return !Collections.disjoint(existingModules, proposedModules);
    }

    /**
     * Function to get a List of non-duplicated modules added to the Person
     * @return List of non-duplicated modules, which may be empty if no unique modules exist
     */
    private List<Module> getNewModules(Person personToEdit, List<Module> proposedModules) {
        Set<Module> existingModules = personToEdit.getModules();
        proposedModules.removeAll(existingModules);

        return proposedModules;
    }

    private CommandResult getCommandResult(Person personToEdit, Person editedPerson, List<Module> modulesToAddActual) {
        if (hasModulesInCommon(personToEdit, modulesToAddActual)) {
            List<Module> newModules = getNewModules(personToEdit, modulesToAddActual);

            if (newModules.isEmpty()) {
                return new CommandResult(String.format(MESSAGE_DUPLICATE_MODULES_EXIST + MESSAGE_NO_NEW_MODULES_ADDED,
                        editedPerson.getName(),
                        editedPerson.getModules()));
            } else {
                return new CommandResult(String.format(MESSAGE_DUPLICATE_MODULES_EXIST + MESSAGE_SUCCESS,
                        editedPerson.getName(),
                        newModules));
            }
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedPerson.getName(), modulesToAddActual));
    }
}
