package unibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import unibook.commons.core.Messages;
import unibook.logic.commands.exceptions.CommandException;
import unibook.model.Model;
import unibook.model.ModelManager;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.person.Person;
import unibook.model.person.Professor;
import unibook.model.person.Student;

/**
 * Lists all persons in the UniBook according to the user specified criteria.
 */
public class ListCommand extends Command {


    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed everything.";
    public static final String MESSAGE_SUCCESS_PEOPLE_MODULE = "Listed all persons with specified module.";
    public static final String MESSAGE_SUCCESS_MODULE = "Listed all modules with specified code.";
    public static final String MESSAGE_SUCCESS_TYPE = "Listed all persons with specified type.";
    public static final String MESSAGE_SUCCESS_MODULEANDTYPE = "Listed all persons with specified type "
        + "in specified module.";
    public static final String MESSAGE_SUCCESS_VIEW = "Switched view successfully.";
    public static final String MESSAGE_USAGE_TYPE = "The acceptable arguments for type are students/professors.";
    public static final String MESSAGE_WRONG_VIEW = "The command requires you to switch views.";
    public static final String MESSAGE_USAGE_OPTION = "The acceptable arguments for option are module/type.";
    public static final String MESSAGE_TYPE_MISSING = "You did not enter a type argument. The acceptable"
        + " arguments for type are students/professors.";
    public static final String MESSAGE_MODULE_MISSING = "You did not enter a Module argument.";
    public static final String MESSAGE_USAGE_VIEW = "The acceptable arguments for view are modules/people.";
    private ModuleCode moduleCode;

    private String type;

    private ListCommandType commandType;
    private ListView viewType;
    /**
     * Constructor for a ListCommand to list everything.
     */
    public ListCommand() {
        this.commandType = ListCommandType.ALL;
    }
    /**
     * Constructor for a ListCommand to change the current view.
     *
     * @param viewType
     */
    public ListCommand(ListView viewType) {
        this.commandType = ListCommandType.VIEW;
        this.viewType = viewType;
    }

    /**
     * Constructor for a ListCommand to list people with specific module.
     *
     * @param moduleCode
     */
    public ListCommand(ModuleCode moduleCode) {
        this.commandType = ListCommandType.MODULE;
        this.moduleCode = moduleCode;
    }

    /**
     * Constructor for a ListCommand to list people with specific type.
     *
     * @param type
     */
    public ListCommand(String type) {
        this.commandType = ListCommandType.TYPE;
        this.type = type;
    }

    /**
     * Constructor for a ListCommand to list people with specific type in a
     * specific module.
     *
     * @param moduleCode
     * @param type
     */
    public ListCommand(ModuleCode moduleCode, String type) {
        this.commandType = ListCommandType.MODULEANDTYPE;
        this.moduleCode = moduleCode;
        this.type = type;
    }

    /**
     * Utility method to quickly show everything. Used to reset before narrowing
     * to specific criteria.
     *
     * @param model
     */
    private void showAll(Model model) {
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredModuleList(Model.PREDICATE_SHOW_ALL_MODULES);
    }

    private boolean moduleCodeExists(ObservableList<Module> modules) {
        for (Module m : modules) {
            if (m.hasModuleCode(this.moduleCode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CommandResult execute(Model model, Boolean isPersonListShowing,
                                 Boolean isModuleListShowing) throws CommandException {
        requireNonNull(model);
        showAll(model);
        ModelManager modelManager = (ModelManager) model;
        switch (commandType) {
        case ALL:
            return new CommandResult(MESSAGE_SUCCESS);
        case MODULE:
            if (!moduleCodeExists(model.getUniBook().getModuleList())) {
                throw new CommandException(String.format(Messages.MESSAGE_MODULE_CODE_NOT_EXIST, moduleCode));
            }
            if (modelManager.getUi().isPersonListShowing()) {
                Predicate<Person> showSpecificPeoplePredicate = p -> p.hasModule(this.moduleCode);
                model.updateFilteredPersonList(showSpecificPeoplePredicate);
                return new CommandResult(MESSAGE_SUCCESS_PEOPLE_MODULE);
            } else {
                Predicate<Module> showSpecificModulePredicate = m -> m.hasModuleCode(this.moduleCode);
                model.updateFilteredModuleList(showSpecificModulePredicate);
                return new CommandResult(MESSAGE_SUCCESS_MODULE);
            }

        case TYPE:
            if (modelManager.getUi().isPersonListShowing()) {
                if (type.equals("professors")) {
                    model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PROFESSORS);
                } else if (type.equals("students")) {
                    model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_STUDENTS);
                } else {
                    return new CommandResult(MESSAGE_USAGE_TYPE);
                }
            } else {
                return new CommandResult(MESSAGE_WRONG_VIEW);
            }

            return new CommandResult(MESSAGE_SUCCESS_TYPE);
        case MODULEANDTYPE:
            if (modelManager.getUi().isPersonListShowing()) {
                if (!moduleCodeExists(model.getUniBook().getModuleList())) {
                    throw new CommandException(String.format(Messages.MESSAGE_MODULE_CODE_NOT_EXIST, moduleCode));
                }
                if (type.equals("professors")) {
                    Predicate<Person> showSpecificProfessorPredicate = p -> p.hasModule(this.moduleCode)
                        && (p instanceof Professor);
                    model.updateFilteredPersonList(showSpecificProfessorPredicate);
                } else if (type.equals("students")) {
                    Predicate<Person> showSpecificStudentPredicate = p -> p.hasModule(this.moduleCode)
                        && (p instanceof Student);
                    model.updateFilteredPersonList(showSpecificStudentPredicate);
                } else {
                    return new CommandResult(MESSAGE_USAGE_TYPE);
                }
                return new CommandResult(MESSAGE_SUCCESS_MODULEANDTYPE);
            } else {
                return new CommandResult(MESSAGE_WRONG_VIEW);
            }
        case VIEW:
            if (this.viewType == ListView.MODULES) {
                modelManager.getUi().setModuleListPanel();
            } else {
                modelManager.getUi().setPersonListPanel();
            }
            return new CommandResult(MESSAGE_SUCCESS_VIEW);
        default:
            return new CommandResult("");
        }
    }

    private enum ListCommandType {
        ALL, MODULE, TYPE, MODULEANDTYPE, VIEW
    }

    public enum ListView {
        PEOPLE, MODULES
    }
}
