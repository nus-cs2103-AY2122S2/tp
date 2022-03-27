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
import unibook.model.person.Professor;
import unibook.model.person.Student;

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
    public static final String MESSAGE_DELETE_TRAITS_SUCCESS = "Successfully deleted the following traits:\n";
    public static final String PHONE = "Phone, ";
    public static final String EMAIL = "Email, ";
    public static final String TAG = "Tag, ";
    public static final String OFFICE = "Office, ";

    private Index targetIndex;
    private boolean indexOnly = false;
    private ModuleCode moduleCode;
    private Group group;
    private boolean phone;
    private boolean email;
    private String tag;
    private boolean office;
    private Index profIndex;
    private Index stuIndex;

    /**
     * Creates a Delete Command Object that will delete a person at targetIndex.
     *
     * @param targetIndex Index object that describes the person at that index to delete
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.indexOnly = true;
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
     * Creates a Delete Command Object that will delete a group in the module that
     * matches module code provided
     *
     * @param moduleCode
     */
    public DeleteCommand(ModuleCode moduleCode, Group group) {
        this.moduleCode = moduleCode;
        this.group = group;
    }

    public DeleteCommand(boolean phone, boolean email, String tag, boolean office) {
        this.phone = phone;
        this.email = email;
        this.tag = tag;
        this.office = office;
    }

    @Override
    public CommandResult execute(Model model,
                                 Boolean isPersonListShowing,
                                 Boolean isModuleListShowing) throws CommandException {
        requireNonNull(model);

        // delete person by index case
        if (indexOnly) {

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
        } else if (moduleCode != null) {

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


            // delete group case
        } else if (moduleCode != null && group != null) {

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

        } else if (phone || email || tag != null || office) { // delete person attributes

            if (!isPersonListShowing) {
                throw new CommandException(Messages.MESSAGE_CHANGE_TO_PERSON_PAGE);
            }

            List<Person> lastShownPersonList = model.getFilteredPersonList();

            if (targetIndex.getZeroBased() >= lastShownPersonList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDeleteTrait = lastShownPersonList.get(targetIndex.getZeroBased());
            Person newPerson = null;

            if (phone) {
                newPerson = personToDeleteTrait.deletePhone();
            }
            if (email) {
                newPerson = personToDeleteTrait.deleteEmail();
            }
            if (tag != null) {
                newPerson = personToDeleteTrait.deleteTag(tag);
            }
            if (personToDeleteTrait instanceof Professor && office) {
                newPerson = ((Professor)personToDeleteTrait).deleteOffice();
            }

            personToDeleteTrait.removePersonFromAllTheirModules();
            newPerson.addPersonToAllTheirModules();
            if (personToDeleteTrait instanceof Student) {
                ((Student) personToDeleteTrait).removeStudentFromAllTheirGroups();
                ((Student) newPerson).addStudentToAllTheirGroups();
            }

            // message can be improved if have time
            String phoneString = phone ? PHONE : "";
            String emailString = email ? EMAIL : "";
            String tagString = tag != null ? TAG : "";
            String officeString = personToDeleteTrait instanceof Professor && office ? OFFICE : "";
            return new CommandResult(MESSAGE_DELETE_TRAITS_SUCCESS + phoneString +
                    emailString + tagString + officeString + "from " + personToDeleteTrait.getName());

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
