package unibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unibook.commons.core.Messages;
import unibook.logic.commands.exceptions.CommandException;
import unibook.model.Model;
import unibook.model.ModelManager;
import unibook.model.UniBook;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.exceptions.GroupNotFoundException;
import unibook.model.module.group.Group;
import unibook.model.person.Person;
import unibook.model.person.Professor;
import unibook.model.person.Student;

/**
 * Lists all persons in the UniBook according to the user specified criteria.
 */
public class ListCommand extends Command {


    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed everything.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists entries based on specified conditions. \n"
            + "Format examples: \n"
            + "list (displays all entries)\n"
            + "list o/type ty/<TYPE> (displays all people with the specified type (students/professors))\n"
            + "list o/module m/<MODULENAME> (displays all entries associated with the given module)\n"
            + "list o/module m/<MODULENAME> ty/<TYPE> "
            + "(displays all entries of a specific type associated with the module)\n"
            + "list o/view v/<VIEWTYPE> (Switches the UniBook to the specified view (people/modules))";


    private ModuleCode moduleCode;

    private String type;

    private ListCommandType commandType;
    private ListView viewType;

    private String group;

    /**
     * Constructor for a ListCommand to list everything.
     */
    public ListCommand() {
        this.commandType = ListCommandType.ALL;
    }

    /**
     * Constructor for a ListCommand to change the current view.
     * @param viewType
     */
    public ListCommand(ListView viewType, ListCommandType commandType) {
        assert commandType == ListCommandType.VIEW;
        this.commandType = ListCommandType.VIEW;
        this.viewType = viewType;
    }

    /**
     * Constructor for a ListCommand with 1 option and 1 field
     * eg list o/module m/cs2103 list o/type ty/professors list o/group g/groupname
     */
    public ListCommand(String field, ListCommandType commandType) {
        switch (commandType) {
        case MODULE:
            this.commandType = ListCommandType.MODULE;
            this.moduleCode = new ModuleCode(field);
            break;
        case TYPE:
            this.commandType = ListCommandType.TYPE;
            this.type = field;
            break;
        case GROUP:
            this.commandType = ListCommandType.GROUP;
            this.group = field;
            break;
        default:
            break;
        }
    }

    /**
     * Constructor for a ListCommand with 1 option and 2 fields
     * eg list o/module m/cs2103 ty/professors
     */
    public ListCommand(String field1, String field2, ListCommandType commandType) {
        switch (commandType) {
        case MODULEANDTYPE:
            this.commandType = ListCommandType.MODULEANDTYPE;
            this.moduleCode = new ModuleCode(field1);
            this.type = field2;
            break;
        default:
            break;
        }

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
                                 Boolean isModuleListShowing, Boolean isGroupListShowing) throws CommandException {
        requireNonNull(model);
        ModelManager modelManager = (ModelManager) model;

        switch (this.commandType) {
        case ALL:
            //List everything.
            showAll(model);
            return new CommandResult(MESSAGE_SUCCESS);
        case MODULE:
            if (!moduleCodeExists(model.getUniBook().getModuleList())) {
                //The given module does not exist in UniBook.
                throw new CommandException(String.format(Messages.MESSAGE_MODULE_CODE_NOT_EXIST, moduleCode));
            }
            if (modelManager.getUi().isPersonListShowing()) {
                //Module command given in person page. Displays all people with given module
                Predicate<Person> showSpecificPeoplePredicate = p -> p.hasModule(this.moduleCode);
                model.updateFilteredPersonList(showSpecificPeoplePredicate);
                return new
                        CommandResult(String.format(Messages.MESSAGE_LISTED_PEOPLE_WITH_MODULE, moduleCode.toString()));

            } else if (modelManager.getUi().isModuleListShowing()) {
                //Module command given in modules page. Displays specified module.
                Predicate<Module> showSpecificModulePredicate = m -> m.hasModuleCode(this.moduleCode);
                model.updateFilteredModuleList(showSpecificModulePredicate);
                return new CommandResult(String.format(Messages.MESSAGE_LISTED_MODULE, moduleCode.toString()));
            } else {
                return new CommandResult(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules or People"));
            }

        case TYPE:
            if (modelManager.getUi().isPersonListShowing()) {
                if (type.equals("professors")) {
                    //Displays all professors.
                    model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PROFESSORS);
                    return new CommandResult(Messages.MESSAGE_LISTED_ALL_PROFESSORS);
                } else if (type.equals("students")) {
                    //Displays all students.
                    model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_STUDENTS);
                    return new CommandResult(Messages.MESSAGE_LISTED_ALL_STUDENTS);
                } else {
                    //Invalid type argument.
                    throw new CommandException(Messages.MESSAGE_WRONG_TYPE);
                }
            } else {
                //Type command given in (wrong) modules view or groups view.
                throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "People"));
            }
        case MODULEANDTYPE:
            if (modelManager.getUi().isPersonListShowing()) {
                if (!moduleCodeExists(model.getUniBook().getModuleList())) {
                    //The given module does not exist in UniBook.
                    throw new CommandException(String.format(Messages.MESSAGE_MODULE_CODE_NOT_EXIST, moduleCode));
                }
                if (type.equals("professors")) {
                    //Displays all professors in the given module.
                    Predicate<Person> showSpecificProfessorPredicate = p -> p.hasModule(this.moduleCode)
                        && (p instanceof Professor);
                    model.updateFilteredPersonList(showSpecificProfessorPredicate);
                    return new CommandResult(
                            String.format(Messages.MESSAGE_LISTED_ALL_TYPE_IN_MODULE,
                                    "professors", moduleCode.toString()));
                } else if (type.equals("students")) {
                    //Displays all students in the given module.
                    Predicate<Person> showSpecificStudentPredicate = p -> p.hasModule(this.moduleCode)
                        && (p instanceof Student);
                    model.updateFilteredPersonList(showSpecificStudentPredicate);
                    return new CommandResult(
                            String.format(Messages.MESSAGE_LISTED_ALL_TYPE_IN_MODULE,
                                    "students", moduleCode.toString()));
                } else {
                    //Invalid type argument.
                    throw new CommandException(Messages.MESSAGE_WRONG_TYPE);
                }
            } else {
                //Type command given in (wrong) modules or group view.
                throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "People"));
            }
        case VIEW:
            if (this.viewType == ListView.MODULES) {
                //Switch view to modules
                if (isModuleListShowing) {
                    return new CommandResult(Messages.MESSAGE_ALREADY_ON_MODULE_PAGE);
                } else {
                    modelManager.getUi().setModuleListPanel();
                    return new CommandResult(Messages.MESSAGE_CHANGE_TO_MODULE_PAGE);
                }
            } else if (this.viewType == ListView.PEOPLE) {
                //Switch view to people
                if (isPersonListShowing) {
                    return new CommandResult(Messages.MESSAGE_ALREADY_ON_PEOPLE_PAGE);
                } else {
                    modelManager.getUi().setPersonListPanel();
                    return new CommandResult(Messages.MESSAGE_CHANGE_TO_PERSON_PAGE);
                }
            } else {
                //Switch view to groups
                assert this.viewType == ListView.GROUPS;
                if (isGroupListShowing) {
                    return new CommandResult(Messages.MESSAGE_ALREADY_ON_GROUP_PAGE);
                } else {
                    ObservableList<Module> modules = ((UniBook) modelManager.getUniBook())
                            .getModuleList();
                    ObservableList<Group> groups = FXCollections.observableArrayList();
                    for (Module m : modules) {
                        ObservableList<Group> moduleGroups = m.getGroups();
                        for (Group mg : moduleGroups) {
                            groups.add(mg);
                        }
                    }
                    modelManager.getUi().setGroupListPanel(groups);
                    return new CommandResult(Messages.MESSAGE_CHANGE_TO_GROUP_PAGE);
                }

            }

        case GROUP:
            if (modelManager.getUi().isModuleListShowing() && modelManager.getFilteredModuleList().size() == 1) {
                //The case where 1 module is showing, allow to focus into 1 group
                //Shows all groups that match given name in aforementioned module.
                ObservableList<Group> groups = modelManager.getFilteredModuleList().get(0).getGroups();
                Group group = groups.get(0);
                if (group.getGroupName().toLowerCase().equals(this.group)) {
                    modelManager.getUi().setGroupListPanel(groups);
                    return new CommandResult(String.format(Messages.MESSAGE_LISTED_MODULE_GROUP,
                            group.getGroupName(), group.getModule().getModuleName().toString()));
                } else {
                    throw new CommandException("The group does not exist in the displayed module!");
                }
            } else {
                try {
                    //The case where the user is in people list, or modules list with >1 module showing
                    //Shows all groups that match given name.
                    ObservableList<Group> groups = ((UniBook) modelManager.getUniBook())
                            .getGroupsWithGroupName(this.group);
                    modelManager.getUi().setGroupListPanel(groups);
                    return new CommandResult(String.format(Messages.MESSAGE_LISTED_GROUP_WITH_NAME,
                            groups.get(0).getGroupName()));
                } catch (GroupNotFoundException g) {
                    throw new CommandException("The group entered does not exist in the UniBook!");
                }

            }

        default:
            return new CommandResult("");
        }
    }

    public enum ListCommandType {
        ALL, MODULE, TYPE, MODULEANDTYPE, VIEW, GROUP
    }

    public enum ListView {
        PEOPLE, MODULES, GROUPS
    }
}
