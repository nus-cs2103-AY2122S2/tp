package unibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import unibook.commons.core.Messages;
import unibook.commons.core.index.Index;
import unibook.logic.commands.exceptions.CommandException;
import unibook.model.Model;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.group.Group;
import unibook.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the unibook.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ":\n"
        + "Possible options:\n"
        + "delete [index] (Delete person or module at that index)"
        + "delete m/modulecode (Delete module that matches the code)"
        + "delete m/modulecode o/cascade (Delete module and anyone that is only associated with this module"
        + "delete m/modulecode g/groupname (Delete the group that is associated with this module code";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";
    public static final String MESSAGE_DELETE_MODULE_AND_PERSON_SUCCESS = "Deleted Module: %1$s, and all Persons";
    public static final String MESSAGE_DELETE_UNSUCCESSFUL = "Delete Unsuccessful";
    public static final String MESSAGE_DELETE_GROUP_SUCCESS = "Deleted Group: %1$s";
    public static final String MESSAGE_INVALID_OPTION = "Invalid Option provided!\nOption should be either " +
            "o/all, o/mod or o/prof";

    private Index targetIndex;
    private ModuleCode moduleCode;
    private String option;
    private Group group;

    /**
     * Creates a Delete Command Object that will delete a person at targetIndex.
     *
     * @param targetIndex Index object that describes the person at that index to delete
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Creates a Delete Command Object that will delete a module that has moduleCode
     *
     * @param moduleCode
     */
    public DeleteCommand(ModuleCode moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Creates a Delete Command Object that will delete a module
     * that has moduleCode based on provided option
     *
     * @param moduleCode
     */
    public DeleteCommand(ModuleCode moduleCode, String option) {
        this.moduleCode = moduleCode;
        this.option = option;
    }

    /**
     * Creates a Delete Command Object that will delete a group in the module that
     * matches module code provided
     *
     * @param moduleCode
     */
    public DeleteCommand(ModuleCode moduleCode, Group group) {
        this.moduleCode = moduleCode;
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model,
                                 Boolean isPersonListShowing,
                                 Boolean isModuleListShowing) throws CommandException {
        requireNonNull(model);

        // delete person by index case
        if (targetIndex != null) {

            // if not on person page, throw exception telling user to change pages
            if (isPersonListShowing) {

                List<Person> lastShownPersonList = model.getFilteredPersonList();

                if (targetIndex.getZeroBased() >= lastShownPersonList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
                }

                Person personToDelete = lastShownPersonList.get(targetIndex.getZeroBased());

                // Bi-directionality
                model.deletePerson(personToDelete); // delete person from UniquePersonList

                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));

            } else if (isModuleListShowing) {

                List<Module> lastShownModuleList = model.getFilteredModuleList();

                if (targetIndex.getZeroBased() >= lastShownModuleList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
                }

                Module moduleToDelete = lastShownModuleList.get(targetIndex.getZeroBased());

                // Bi-directionality
                model.deleteModule(moduleToDelete); // delete person from UniquePersonList

                return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete.getModuleCode()));

            }



            // delete module by code case
        } else if ((targetIndex == null && moduleCode != null && option == null && group == null) ||
                (targetIndex == null && moduleCode != null
                        && option != null && option.equals("mod") && group == null)) {

            // if not on module page, throw exception telling users to change page
            if (!isModuleListShowing) {
                throw new CommandException(Messages.MESSAGE_CHANGE_TO_MODULE_PAGE);
            }

            // check if module code is valid
            if (!model.hasModule(moduleCode)) {
                throw new CommandException(String.format(Messages.MESSAGE_MODULE_CODE_NOT_EXIST, moduleCode));
            }

            // Bi-directionality
            model.deleteByModuleCode(moduleCode); // delete module from ModuleList and remove module from all persons

            return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleCode));

            // delete with options case
        } else if (targetIndex == null && moduleCode != null && option != null && group == null) {

            // if not on module page, throw exception telling users to change page
            if (!isModuleListShowing) {
                throw new CommandException(Messages.MESSAGE_CHANGE_TO_MODULE_PAGE);
            }

            // check if module code is valid
            if (!model.hasModule(moduleCode)) {
                throw new CommandException(String.format(Messages.MESSAGE_MODULE_CODE_NOT_EXIST, moduleCode));
            }

            if (option.equals("cascade")) {
                model.deleteModuleAndPersons(moduleCode);
                return new CommandResult(String.format(MESSAGE_DELETE_MODULE_AND_PERSON_SUCCESS, moduleCode));
            } else {
                throw new CommandException(MESSAGE_INVALID_OPTION);
            }

            // delete group case
        } else if (targetIndex == null && moduleCode != null && option == null && group != null) {

            // if not on module page, throw exception telling users to change page
            if (!isModuleListShowing) {
                throw new CommandException(Messages.MESSAGE_CHANGE_TO_MODULE_PAGE);
            }

            // check if module code is valid
            if (!model.hasModule(moduleCode)) {
                throw new CommandException(String.format(Messages.MESSAGE_MODULE_CODE_NOT_EXIST, moduleCode));
            }

            // check if group exist
            if (!model.hasModuleAndGroup(moduleCode, group)) {
                throw new CommandException(String.format(Messages.MESSAGE_GROUP_NOT_EXIST, group.getGroupName()));
            }

            // module and group exists
            Group removedGroup = model.removeGroup(moduleCode, group);
            return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS, removedGroup));

        }

        return new CommandResult(String.format(MESSAGE_DELETE_UNSUCCESSFUL, moduleCode));


    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
