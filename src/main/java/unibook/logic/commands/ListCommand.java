package unibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unibook.commons.core.Messages;
import unibook.logic.commands.exceptions.CommandException;
import unibook.logic.parser.ParserUtil;
import unibook.logic.parser.exceptions.ParseException;
import unibook.model.Model;
import unibook.model.ModelManager;
import unibook.model.UniBook;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleKeyEvent;
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
            + "list o/view v/<VIEWTYPE> (Switches the UniBook to the specified view (people/modules))"
            + "list o/group g/<GROUPNAME> (Dsiplays groups with the given groupname)";


    private ModuleCode moduleCode;

    private String type;

    private ListCommandType commandType;
    private ListView viewType;

    private String group;

    private String date;

    private String moduleNameFragment;

    private String keyEvent;

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
        case GROUPFROMMODULEVIEW:
            this.commandType = ListCommandType.GROUPFROMMODULEVIEW;
            this.group = field;
            break;
        case GROUPFROMGROUPVIEW:
            this.commandType = ListCommandType.GROUPFROMGROUPVIEW;
            this.group = field;
            break;
        case GROUPWITHMEETINGDATE:
            this.commandType = ListCommandType.GROUPWITHMEETINGDATE;
            this.date = field;
            break;
        case MODULEWITHNAMEMATCH:
            this.commandType = ListCommandType.MODULEWITHNAMEMATCH;
            this.moduleNameFragment = field;
            break;
        case MODULESWITHKEYEVENT:
            this.commandType = ListCommandType.MODULESWITHKEYEVENT;
            this.keyEvent = field;
            break;
        case MODULESWITHEVENTDATE:
            this.commandType = ListCommandType.MODULESWITHEVENTDATE;
            this.date = field;
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
        case PEOPLEINGROUP:
            this.commandType = ListCommandType.PEOPLEINGROUP;
            this.moduleCode = new ModuleCode(field2);
            this.group = field1;
            break;
        case MODULESWITHDATEANDKEYEVENT:
            this.commandType = ListCommandType.MODULESWITHDATEANDKEYEVENT;
            this.date = field1;
            this.keyEvent = field2;
            break;
        case MODULESWITHDATEANDNAME:
            this.commandType = ListCommandType.MODULESWITHDATEANDNAME;
            this.date = field1;
            this.moduleNameFragment = field2;
            break;
        case MODULESWITHNAMEANDKEYEVENT:
            this.commandType = ListCommandType.MODULESWITHNAMEANDKEYEVENT;
            this.moduleNameFragment = field1;
            this.keyEvent = field2;
            break;
        default:
            break;
        }
    }

    /**
     * Constructor for a ListCommand with 3 fields
     * eg list n/software dt/2022-05-04 ke/exam
     */
    public ListCommand(String field1, String field2, String field3, ListCommandType commandType) {
        switch (commandType) {
        case MODULESWITHDATEANDKEYEVENTANDNAME:
            this.commandType = ListCommandType.MODULESWITHDATEANDKEYEVENTANDNAME;
            this.moduleNameFragment = field1;
            this.date = field2;
            this.keyEvent = field3;
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
        ModelManager mm = (ModelManager) model;

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredModuleList(Model.PREDICATE_SHOW_ALL_MODULES);

        if (!(mm.getUi() == null) && mm.getUi().isGroupListShowing()) {
            ObservableList<Group> allGroups = FXCollections.observableArrayList();
            for (Module m : model.getUniBook().getModuleList()) {
                ObservableList<Group> moduleGroups = m.getGroups();
                for (Group mg : moduleGroups) {
                    allGroups.add(mg);
                }
            }
            mm.getUi().setGroupListPanel(allGroups);
        }


    }

    private boolean moduleCodeExists(ObservableList<Module> modules) {
        for (Module m : modules) {
            if (m.hasModuleCode(this.moduleCode)) {
                return true;
            }
        }
        return false;
    }

    private boolean keyDateExists(ObservableList<Module> modules, LocalDate date) {
        for (Module m : modules) {
            ObservableList<LocalDate> dates = m.getKeyEventDates();
            if (dates.contains(date)) {
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
            showAll(model);
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
        case PEOPLEINGROUP:
            if (modelManager.getUi().isPersonListShowing()) {

                if (!model.hasModule(this.moduleCode)) {
                    throw new CommandException(String.format(Messages.MESSAGE_MODULE_CODE_NOT_EXIST, this.moduleCode));
                }

                Module m = model.getModuleByCode(this.moduleCode);

                if (!m.hasGroupName(this.group)) {
                    throw new CommandException(String.format(Messages.MESSAGE_GROUP_NOT_IN_MODULE,
                            this.group, this.moduleCode));
                }

                Group g = m.getGroupByName(this.group);
                ObservableList<Student> peopleInGroup = g.getMembers();


                Predicate<Person> pred = p -> peopleInGroup.contains(p);
                model.updateFilteredPersonList(pred);
                return new CommandResult(String.format(Messages.MESSAGE_DISPLAYED_PEOPLE_IN_GROUP,
                        this.moduleCode.toString(), g.getGroupName()));
            } else {
                throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "People"));
            }

        case GROUPFROMMODULEVIEW:
            if (modelManager.getUi().isModuleListShowing() && modelManager.getFilteredModuleList().size() == 1) {
                //The case where 1 module is showing, allow to focus into 1 group
                //Shows all groups that match given name in aforementioned module.
                ObservableList<Group> groups = modelManager.getFilteredModuleList().get(0).getGroups();
                for (Group g : groups) {
                    if (g.getGroupName().toLowerCase().equals(this.group)) {
                        if (groups.size() == 0) {
                            throw new CommandException(
                                    String.format(Messages.MESSAGE_GROUP_NOT_IN_MODULE, this.group.toUpperCase()));
                        }
                        modelManager.getUi().setGroupListPanel(FXCollections.observableArrayList(g));
                        return new CommandResult(String.format(Messages.MESSAGE_LISTED_MODULE_GROUP,
                                g.getGroupName(), g.getModule().getModuleCode().toString()));
                    }
                }
                throw new CommandException(String.format(
                        Messages.MESSAGE_GROUP_NOT_IN_MODULE, this.group));
            } else if (modelManager.getUi().isModuleListShowing()) {
                try {
                    //The case where the user is in modules list with >1 module showing
                    //Shows all groups that match given name.
                    ObservableList<Group> groups = ((UniBook) modelManager.getUniBook())
                            .getGroupsWithGroupName(this.group);
                    if (groups.size() == 0) {
                        throw new CommandException(
                                String.format(Messages.MESSAGE_GROUP_NOT_IN_UNIBOOK, this.group.toUpperCase()));
                    }
                    modelManager.getUi().setGroupListPanel(groups);
                    return new CommandResult(String.format(Messages.MESSAGE_LISTED_GROUP_WITH_NAME,
                            groups.get(0).getGroupName()));
                } catch (GroupNotFoundException g) {
                    throw new CommandException(Messages.MESSAGE_GROUP_NOT_IN_UNIBOOK);
                }
            } else {
                if (modelManager.getUi().isPersonListShowing()) {
                    throw new CommandException(String.format(Messages.MESSAGE_MODULE_FIELD_MISSING));
                } else {
                    throw new CommandException("You are on group page! No need to specify o/group");
                }
            }
        case GROUPFROMGROUPVIEW:
            if (modelManager.getUi().isGroupListShowing()) {
                ObservableList<Group> groups = FXCollections.observableArrayList();
                for (Module m : model.getUniBook().getModuleList()) {
                    ObservableList<Group> moduleGroups = m.getGroups();
                    for (Group mg : moduleGroups) {
                        if (mg.getGroupName().toLowerCase().equals(this.group)) {
                            groups.add(mg);
                        }
                    }
                }
                if (groups.size() == 0) {
                    throw new CommandException(
                            String.format(Messages.MESSAGE_GROUP_NOT_IN_UNIBOOK, this.group.toUpperCase()));
                }
                ModelManager mm = (ModelManager) model;
                mm.getUi().setGroupListPanel(groups);
                return new CommandResult(String.format(Messages.MESSAGE_DISPLAYED_GROUPS_WITH_NAME,
                        this.group.toUpperCase()));
            } else {
                throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Groups"));
            }
        case GROUPWITHMEETINGDATE:
            try {
                LocalDate localDate = ParserUtil.parseDate(this.date);
                if (modelManager.getUi().isGroupListShowing()) {
                    ObservableList<Group> groups = FXCollections.observableArrayList();

                    for (Module m : model.getUniBook().getModuleList()) {
                        ObservableList<Group> moduleGroups = m.getGroups();
                        for (Group mg : moduleGroups) {
                            ObservableList<LocalDate> dates = mg.getMeetingDates();
                            if (dates.contains(localDate)) {
                                groups.add(mg);
                            }
                        }
                    }

                    if (groups.size() == 0) {
                        throw new CommandException(
                                String.format(Messages.MESSAGE_NO_GROUP_WITH_MEETING_DATE, this.date.toString()));
                    }
                    ModelManager mm = (ModelManager) model;
                    mm.getUi().setGroupListPanel(groups);
                    return new CommandResult(String.format(Messages.MESSAGE_DISPLAYED_GROUPS_WITH_MEETING_DATE,
                            this.date.toString()));

                } else {
                    throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Groups"));
                }
            } catch (ParseException e) {
                throw new CommandException(e.getMessage());
            }
        case MODULEWITHNAMEMATCH:
            //Module command given in modules page. Displays specified module.
            if (modelManager.getUi().isModuleListShowing()) {
                checkIfKeywordExists(modelManager);

                Predicate<Module> showSpecificModulePredicate = m ->
                        m.getModuleName().toString().toLowerCase().contains(this.moduleNameFragment.toLowerCase());
                model.updateFilteredModuleList(showSpecificModulePredicate);
                return new CommandResult(String.format(Messages.MESSAGE_DISPLAYED_MODULES_WITH_NAME,
                        this.moduleNameFragment));
            } else {
                throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules"));
            }
        case MODULESWITHKEYEVENT:
            if (modelManager.getUi().isModuleListShowing()) {
                try {
                    ModuleKeyEvent.KeyEventType ke = ModuleKeyEvent.KeyEventType.valueOf(this.keyEvent.toUpperCase());
                    Predicate<Module> showSpecificModulePredicate = m -> {
                        ObservableList<ModuleKeyEvent> allKeyEvents = m.getKeyEvents();
                        for (ModuleKeyEvent k : allKeyEvents) {
                            if (k.getKeyEventType() == ke) {
                                return true;
                            }
                        }
                        return false;
                    };
                    model.updateFilteredModuleList(showSpecificModulePredicate);
                    return new CommandResult(String.format(Messages.MESSAGE_DISPLAYED_MODULES_WITH_KEY_EVENT,
                            this.keyEvent));
                } catch (IllegalArgumentException e) {
                    throw new CommandException(String.format(Messages.MESSAGE_INVALID_KEY_EVENT, this.keyEvent));
                }

            } else {
                throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules"));
            }
        case MODULESWITHEVENTDATE:
            if (modelManager.getUi().isModuleListShowing()) {
                try {
                    LocalDate localDate = ParserUtil.parseDate(this.date);

                    if (!keyDateExists(model.getUniBook().getModuleList(), localDate)) {
                        throw new CommandException(String.format(Messages.MESSAGE_NO_MODULES_WITH_DATE, localDate));
                    }

                    Predicate<Module> showSpecificModulePredicate = m -> m.getKeyEventDates().contains(localDate);
                    model.updateFilteredModuleList(showSpecificModulePredicate);
                    return new CommandResult(String.format(Messages.MESSAGE_DISPLAYED_MODULES_WITH_DATE,
                            localDate.toString()));

                } catch (ParseException e) {
                    throw new CommandException(e.getMessage());
                }
            } else {
                throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules"));
            }
        case MODULESWITHDATEANDKEYEVENT:
            if (modelManager.getUi().isModuleListShowing()) {
                try {
                    LocalDate localDate = ParserUtil.parseDate(this.date);

                    if (!keyDateExists(model.getUniBook().getModuleList(), localDate)) {
                        throw new CommandException(String.format(Messages.MESSAGE_NO_MODULES_WITH_DATE, localDate));
                    }

                    try {
                        ModuleKeyEvent.KeyEventType ke =
                                ModuleKeyEvent.KeyEventType.valueOf(this.keyEvent.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        throw new CommandException(String.format(Messages.MESSAGE_INVALID_KEY_EVENT, this.keyEvent));
                    }


                    Predicate<Module> showSpecificModulePredicate = m -> {
                        ObservableList<ModuleKeyEvent> keyEvents = m.getKeyEvents();
                        for (ModuleKeyEvent k : keyEvents) {
                            if (k.getKeyEventType().toString().equals(this.keyEvent.toUpperCase())
                                    && k.getKeyEventDate().equals(localDate)) {
                                return true;
                            }
                        }
                        return false;
                    };
                    model.updateFilteredModuleList(showSpecificModulePredicate);

                    if (model.getFilteredModuleList().isEmpty()) {
                        model.updateFilteredModuleList(Model.PREDICATE_SHOW_ALL_MODULES);
                        throw new CommandException(String.format(Messages.MESSAGE_NO_MODULE_WITH_EVENT_AND_DATE,
                                this.keyEvent, localDate));
                    }

                    return new CommandResult(String.format(Messages.MESSAGE_DISPLAYED_MODULES_WITH_EVENT_AND_DATE,
                            this.keyEvent, localDate.toString()));

                } catch (ParseException e) {
                    throw new CommandException(e.getMessage());
                }
            } else {
                throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules"));
            }
        case MODULESWITHDATEANDNAME:
            if (modelManager.getUi().isModuleListShowing()) {
                try {
                    checkIfKeywordExists(modelManager);

                    LocalDate localDate = ParserUtil.parseDate(this.date);

                    if (!keyDateExists(model.getUniBook().getModuleList(), localDate)) {
                        throw new CommandException(String.format(Messages.MESSAGE_NO_MODULES_WITH_DATE, localDate));
                    }

                    Predicate<Module> showSpecificModulePredicate = m -> {
                        return m.getModuleName().toString().toLowerCase().contains(this.moduleNameFragment
                                .toLowerCase())
                                && m.getKeyEvents().stream().map(ke -> ke.getKeyEventDate().equals(localDate))
                                        .collect(Collectors.toList()).contains(true);
                    };
                    model.updateFilteredModuleList(showSpecificModulePredicate);

                    if (model.getFilteredModuleList().isEmpty()) {
                        model.updateFilteredModuleList(Model.PREDICATE_SHOW_ALL_MODULES);
                        throw new CommandException(String.format(Messages.MESSAGE_NO_MODULE_WITH_NAME_AND_DATE,
                                this.moduleNameFragment, localDate));
                    }

                    return new CommandResult(String.format(Messages.MESSAGE_DISPLAYED_MODULES_WITH_NAME_AND_DATE,
                            this.moduleNameFragment, localDate.toString()));

                } catch (ParseException e) {
                    throw new CommandException(e.getMessage());
                }
            } else {
                throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules"));
            }
        case MODULESWITHNAMEANDKEYEVENT:
            if (modelManager.getUi().isModuleListShowing()) {
                checkIfKeywordExists(modelManager);

                try {
                    ModuleKeyEvent.KeyEventType ke = ModuleKeyEvent.KeyEventType.valueOf(this.keyEvent.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new CommandException(String.format(Messages.MESSAGE_INVALID_KEY_EVENT, this.keyEvent));
                }


                Predicate<Module> showSpecificModulePredicate = m -> {
                    if (!m.getModuleName().toString().toLowerCase()
                            .contains(this.moduleNameFragment.toLowerCase())) {
                        return false;
                    }
                    ObservableList<ModuleKeyEvent> keyEvents = m.getKeyEvents();
                    for (ModuleKeyEvent k : keyEvents) {
                        if (k.getKeyEventType().toString().equals(this.keyEvent.toUpperCase())) {
                            return true;
                        }
                    }
                    return false;
                };

                model.updateFilteredModuleList(showSpecificModulePredicate);

                if (model.getFilteredModuleList().isEmpty()) {
                    model.updateFilteredModuleList(Model.PREDICATE_SHOW_ALL_MODULES);
                    throw new CommandException(String.format(Messages.MESSAGE_NO_MODULE_WITH_EVENT_AND_NAME,
                            this.keyEvent, this.moduleNameFragment));
                }

                return new CommandResult(String.format(Messages.MESSAGES_DISPLAYED_MODULES_WITH_EVENT_AND_NAME,
                        this.keyEvent, this.moduleNameFragment));

            } else {
                throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules"));
            }
        case MODULESWITHDATEANDKEYEVENTANDNAME:
            if (modelManager.getUi().isModuleListShowing()) {
                try {
                    LocalDate localDate = ParserUtil.parseDate(this.date);

                    if (!keyDateExists(model.getUniBook().getModuleList(), localDate)) {
                        throw new CommandException(String.format(Messages.MESSAGE_NO_MODULES_WITH_DATE, "Modules"));
                    }

                    checkIfKeywordExists(modelManager);

                    try {
                        ModuleKeyEvent.KeyEventType ke = ModuleKeyEvent.KeyEventType
                                .valueOf(this.keyEvent.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        throw new CommandException(String.format(Messages.MESSAGE_INVALID_KEY_EVENT, this.keyEvent));
                    }

                    Predicate<Module> showSpecificModulePredicate = m -> {
                        if (!m.getModuleName().toString().toLowerCase().contains(moduleNameFragment.toLowerCase())) {
                            return false;
                        }
                        ObservableList<ModuleKeyEvent> keyEvents = m.getKeyEvents();
                        for (ModuleKeyEvent k : keyEvents) {
                            if (k.getKeyEventType().toString().equals(this.keyEvent.toUpperCase())
                                    && k.getKeyEventDate().equals(localDate)) {
                                return true;
                            }
                        }
                        return false;
                    };
                    model.updateFilteredModuleList(showSpecificModulePredicate);
                    if (model.getFilteredModuleList().isEmpty()) {
                        model.updateFilteredModuleList(Model.PREDICATE_SHOW_ALL_MODULES);
                        throw new CommandException(String.format(
                                Messages.MESSAGE_NO_MODULES_WITH_EVENT_AND_NAME_AND_DATE,
                                moduleNameFragment, this.keyEvent, localDate));
                    }
                    return new CommandResult(String.format(
                            Messages.MESSAGES_DISPLAYED_MODULES_WITH_EVENT_AND_NAME_AND_DATE,
                            this.moduleNameFragment, this.keyEvent, localDate.toString()));

                } catch (ParseException e) {
                    throw new CommandException(e.getMessage());
                }
            } else {
                throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules"));
            }
        default:
            return new CommandResult("");
        }
    }

    private void checkIfKeywordExists(ModelManager modelManager) throws CommandException {
        ObservableList<Module> allModules = modelManager.getUniBook().getModuleList();
        boolean found = false;
        for (Module k : allModules) {
            if (k.getModuleName().toString().toLowerCase().contains(this.moduleNameFragment.toLowerCase())) {
                found = true;
                break;
            }
        }

        if (!found) {
            throw new CommandException(String.format(Messages.MESSAGE_NO_MODULES_WITH_NAME,
                    moduleNameFragment));
        }
    }

    public enum ListCommandType {
        ALL, MODULE, TYPE, MODULEANDTYPE, VIEW, GROUPFROMMODULEVIEW, PEOPLEINGROUP, GROUPFROMGROUPVIEW,
        GROUPWITHMEETINGDATE, MODULEWITHNAMEMATCH, MODULESWITHKEYEVENT, MODULESWITHEVENTDATE,
        MODULESWITHDATEANDKEYEVENT, MODULESWITHDATEANDNAME, MODULESWITHNAMEANDKEYEVENT,
        MODULESWITHDATEANDKEYEVENTANDNAME
    }

    public enum ListView {
        PEOPLE, MODULES, GROUPS
    }
}
