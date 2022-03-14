package unibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import unibook.model.Model;
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
    public static final String MESSAGE_SUCCESS_MODULE = "Listed all persons with specified module.";
    public static final String MESSAGE_SUCCESS_TYPE = "Listed all persons with specified type.";
    public static final String MESSAGE_SUCCESS_MODULEANDTYPE = "Listed all persons with specified type "
            + "in specified module.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists according to the given criteria\n "
            + "Parameters: option (either module or type) \n"
            + "Example: list o/module m/cs2103\n";

    private enum ListCommandType {
        ALL, MODULE, TYPE, MODULEANDTYPE
    };

    private ModuleCode moduleCode;
    private String type;
    private ListCommandType commandType;

    /**
     * Constructor for a ListCommand to list everything.
     */
    public ListCommand() {
        this.commandType = ListCommandType.ALL;
    }

    /**
     * Constructor for a ListCommand to list people with specific module.
     * @param moduleCode
     */
    public ListCommand(ModuleCode moduleCode) {
        this.commandType = ListCommandType.MODULE;
        this.moduleCode = moduleCode;
    }

    /**
     * Constructor for a ListCommand to list people with specific type.
     * @param type
     */
    public ListCommand(String type) {
        this.commandType = ListCommandType.TYPE;
        this.type = type;
    }

    /**
     * Constructor for a ListCommand to list people with specific type in a
     * specific module.
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
     * @param model
     */
    private void showAll(Model model) {
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredModuleList(Model.PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public CommandResult execute(Model model, Boolean isPersonListShowing,
                                 Boolean isModuleListShowing) {
        requireNonNull(model);
        showAll(model);
        switch (commandType) {
        case ALL:
            return new CommandResult(MESSAGE_SUCCESS);
        case MODULE:
            Predicate<Person> showSpecificPeoplePredicate = p -> p.hasModule(this.moduleCode);
            model.updateFilteredPersonList(showSpecificPeoplePredicate);
            return new CommandResult(MESSAGE_SUCCESS_MODULE);
        case TYPE:
            if (type.equals("professors")) {
                model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PROFESSORS);
            } else {
                model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_STUDENTS);
            }
            return new CommandResult(MESSAGE_SUCCESS_TYPE);
        case MODULEANDTYPE:
            if (type.equals("professors")) {
                Predicate<Person> showSpecificProfessorPredicate = p -> p.hasModule(this.moduleCode)
                        && (p instanceof Professor);
                model.updateFilteredPersonList(showSpecificProfessorPredicate);
            } else {
                Predicate<Person> showSpecificStudentPredicate = p -> p.hasModule(this.moduleCode)
                        && (p instanceof Student);
                model.updateFilteredPersonList(showSpecificStudentPredicate);
            }
            return new CommandResult(MESSAGE_SUCCESS_MODULEANDTYPE);
        default:
            return new CommandResult(MESSAGE_USAGE);
        }
    }
}
