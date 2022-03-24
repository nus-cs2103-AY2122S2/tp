package unibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import unibook.commons.core.Messages;
import unibook.commons.core.index.Index;
import unibook.logic.commands.exceptions.CommandException;
import unibook.model.Model;
import unibook.model.module.ModuleCode;
import unibook.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the unibook.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the person identified by the index number used in the displayed person list or "
        + "deletes a module that matches the given module code\n"
        + "Parameters: INDEX (must be a positive integer) or m/MODULECODE\n"
        + "Example: " + COMMAND_WORD + " 1 or m/CS2103\n";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";
    public static final String MESSAGE_DELETE_UNSUCCESSFUL = "Delete Unsuccessful";

    private final Index targetIndex;
    private final ModuleCode moduleCode;

    /**
     * Creates a Delete Command Object that will delete a person at targetIndex.
     *
     * @param targetIndex Index object that describes the person at that index to delete
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.moduleCode = null;
    }

    /**
     * Creates a Delete Command Object that will delete a module that has moduleCode
     *
     * @param moduleCode
     */
    public DeleteCommand(ModuleCode moduleCode) {
        this.moduleCode = moduleCode;
        this.targetIndex = null;
    }

    // execute will delete the specified index of person or module depending on what is currently showing
    //TODO remove person from
    @Override
    public CommandResult execute(Model model,
                                 Boolean isPersonListShowing,
                                 Boolean isModuleListShowing) throws CommandException {
        requireNonNull(model);

        // delete person by index case
        if (targetIndex != null && moduleCode == null) {

            // if not on person page, throw exception telling user to change pages
            if (!isPersonListShowing) {
                throw new CommandException(Messages.MESSAGE_CHANGE_TO_PERSON_PAGE);
            }

            List<Person> lastShownList = model.getFilteredPersonList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());

            // Bi-directionality
            model.deletePerson(personToDelete); // delete person from UniquePersonList
            //model.removePersonFromAllModules(personToDelete); // delete person from each module in ModuleList
            //removed this as this shld be implemented on the UniBook side, not here, for consistency (else testing
            //quite tough)

            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));

            // delete module by code case
        } else if (targetIndex == null && moduleCode != null) {

            // if not on module page, throw exception telling users to change page
            if (!isModuleListShowing) {
                throw new CommandException(Messages.MESSAGE_CHANGE_TO_MODULE_PAGE);
            }

            // check if module code is valid
            if (!model.hasModule(moduleCode)) {
                throw new CommandException(String.format(Messages.MESSAGE_MODULE_CODE_NOT_EXIST, moduleCode));
            }

            // Bi-directionality
            model.deleteByModuleCode(moduleCode); // delete module from ModuleList
            model.removeModuleFromAllPersons(moduleCode); // delete module from each person in UniquePersonList

            return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleCode));

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
