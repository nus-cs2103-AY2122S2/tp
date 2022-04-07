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

    /**
     * Enum type that determines which variant/permutation of a command is executed.
     */
    public enum ListCommandType {
        ALL, MODULE, TYPE, MODULEANDTYPE, VIEW, GROUPFROMMODULEVIEW, PEOPLEINGROUP, GROUPFROMGROUPVIEW,
        GROUPWITHMEETINGDATE, MODULEWITHNAMEMATCH, MODULESWITHKEYEVENT, MODULESWITHEVENTDATE,
        MODULESWITHDATEANDKEYEVENT, MODULESWITHDATEANDNAME, MODULESWITHNAMEANDKEYEVENT,
        MODULESWITHDATEANDKEYEVENTANDNAME, SPECIFICGROUPFROMGROUPVIEW
    }

    /**
     * Enum type that determines which view to switch to.
     */
    public enum ListView {
        PEOPLE, MODULES, GROUPS
    }

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed everything.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Lists entries based on specified conditions. \n"
        + "Please refer to user guide in docs folder for command syntax.";


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
     * Constructor for a ListCommand to change the view.
     */
    public ListCommand(ListView viewType, ListCommandType commandType) {
        assert commandType == ListCommandType.VIEW;
        this.commandType = ListCommandType.VIEW;
        this.viewType = viewType;
    }


    /**
     * Constructor for a ListCommand with 1 field.
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
     * Constructor for a ListCommand with 2 fields.
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
        case SPECIFICGROUPFROMGROUPVIEW:
            this.commandType = ListCommandType.SPECIFICGROUPFROMGROUPVIEW;
            this.group = field1;
            this.moduleCode = new ModuleCode(field2);
            break;
        default:
            break;
        }
    }

    /**
     * Constructor for a ListCommand with 3 fields.
     */
    public ListCommand(String field1, String field2, String field3, ListCommandType commandType) {
        //Currently only 1 possible type that takes in 3 fields. Add switch case upon expansion
        this.commandType = commandType;
        this.moduleNameFragment = field1;
        this.date = field2;
        this.keyEvent = field3;
    }


    /**
     * Utility method used to show every entry in a given page when the relevant command is executed.
     */
    private void showAll(Model model) {
        ModelManager mm = (ModelManager) model;

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredModuleList(Model.PREDICATE_SHOW_ALL_MODULES);

        if (!(mm.getUi() == null) && mm.getUi().isGroupListShowing()) {
            ObservableList<Group> allGroups = FXCollections.observableArrayList();
            for (Module m : model.getUniBook().getModuleList()) {
                ObservableList<Group> moduleGroups = m.getGroups();
                allGroups.addAll(moduleGroups);
            }
            mm.getUi().setGroupListPanel(allGroups);
        }
    }

    /**
     * Utility method to check if the instance module code exists in a given list of modules.
     * @return true if module code was found, false otherwise
     */
    private boolean moduleCodeExists(ObservableList<Module> modules) {
        for (Module m : modules) {
            if (m.hasModuleCode(this.moduleCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Utility method to check if the instance key date exists in a given list of modules.
     * @return true if key date was found, false otherwise
     */
    private boolean keyDateExists(ObservableList<Module> modules, LocalDate date) {
        for (Module m : modules) {
            ObservableList<LocalDate> dates = m.getKeyEventDates();
            if (dates.contains(date)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a module name has the instance keyword contained within it in a given model.
     * If it does not exist, a CommandException is thrown.
     */
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

    @Override
    public CommandResult execute(Model model, Boolean isPersonListShowing,
                                 Boolean isModuleListShowing, Boolean isGroupListShowing) throws CommandException {
        requireNonNull(model);
        switch (this.commandType) {
        case ALL:
            return allCommand(model);
        case MODULE:
            return moduleCommand(model, isPersonListShowing, isModuleListShowing, isGroupListShowing);
        case TYPE:
            return typeCommand(model, isPersonListShowing);
        case MODULEANDTYPE:
            return moduleAndTypeCommand(model, isPersonListShowing);
        case VIEW:
            return viewCommand(model, isPersonListShowing, isModuleListShowing, isGroupListShowing);
        case PEOPLEINGROUP:
            return peopleInGroupCommand(model, isPersonListShowing);
        case GROUPFROMMODULEVIEW:
            return groupFromModulesCommand(model, isPersonListShowing, isGroupListShowing);
        case GROUPFROMGROUPVIEW:
            return groupFromGroupsCommand(model, isGroupListShowing);
        case GROUPWITHMEETINGDATE:
            return groupMeetingDateCommand(model, isGroupListShowing);
        case MODULEWITHNAMEMATCH:
            return moduleWithNameCommand(model, isModuleListShowing);
        case MODULESWITHKEYEVENT:
            return moduleWithKeyEventCommand(model, isModuleListShowing);
        case MODULESWITHEVENTDATE:
            return moduleWithDateCommand(model, isModuleListShowing);
        case MODULESWITHDATEANDKEYEVENT:
            return moduleWithDateAndEventCommand(model, isModuleListShowing);
        case MODULESWITHDATEANDNAME:
            return moduleWithDateAndNameCommand(model, isModuleListShowing);
        case MODULESWITHNAMEANDKEYEVENT:
            return moduleWithNameAndEventCommand(model, isModuleListShowing);
        case MODULESWITHDATEANDKEYEVENTANDNAME:
            return moduleWithNameAndEventAndDateCommand(model, isModuleListShowing);
        case SPECIFICGROUPFROMGROUPVIEW:
            return specificGroupCommand(model, isGroupListShowing);
        default:
            return new CommandResult("");
        }
    }

    //Utiity method for equals method
    private static <T> boolean equalsIfNotNull(T attr1, T attr2) {
        return attr1 == null || attr2 == null || attr1.equals(attr2);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ListCommand)) {
            return false;
        }

        ListCommand lc = (ListCommand) o;

        return equalsIfNotNull(lc.moduleCode, moduleCode)
                && equalsIfNotNull(lc.type, type)
                && equalsIfNotNull(lc.commandType.toString(), commandType.toString())
                && equalsIfNotNull(lc.viewType, viewType)
                && equalsIfNotNull(lc.group, group)
                && equalsIfNotNull(lc.date, date)
                && equalsIfNotNull(lc.moduleNameFragment, moduleNameFragment)
                && equalsIfNotNull(lc.keyEvent, keyEvent);
    }


    @Override
    public String toString() {
        return this.type;
    }

    private CommandResult allCommand(Model model) {
        //List everything. Example: `list`
        showAll(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private CommandResult moduleCommand(Model model, Boolean isPersonListShowing, Boolean isModuleListShowing,
                                        Boolean isGroupListShowing) throws CommandException {
        //Module command given in People or Module page.
        if (!moduleCodeExists(model.getUniBook().getModuleList())) {
            //The module code given does not exist in the UniBook.
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_CODE_NOT_EXIST, moduleCode));
        }

        if (isGroupListShowing) {
            //Module command given in groups page (Invalid)
            return new CommandResult(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules or People"));
        }

        if (isPersonListShowing) {
            //Module command given in person page. Displays all people with the given module.
            Predicate<Person> showSpecificPeoplePredicate = p -> p.hasModule(this.moduleCode);
            model.updateFilteredPersonList(showSpecificPeoplePredicate);
            return new
                    CommandResult(String.format(Messages.MESSAGE_LISTED_PEOPLE_WITH_MODULE, moduleCode.toString()));
        }

        if (isModuleListShowing) {
            //Module command given in modules page. Displays the specified module.
            Predicate<Module> showSpecificModulePredicate = m -> m.hasModuleCode(this.moduleCode);
            model.updateFilteredModuleList(showSpecificModulePredicate);
            return new CommandResult(String.format(Messages.MESSAGE_LISTED_MODULE, moduleCode.toString()));
        }

        return new CommandResult("");
    }

    private CommandResult typeCommand(Model model, Boolean isPersonListShowing) throws CommandException {
        if (!isPersonListShowing) {
            throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "People"));
        }

        //Type command given in People page.
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
    }

    private CommandResult moduleAndTypeCommand(Model model, Boolean isPersonListShowing) throws CommandException {
        if (!isPersonListShowing) {
            throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "People"));
        }

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
    }

    private CommandResult viewCommand(Model model, Boolean isPersonListShowing, Boolean isModuleListShowing,
                                      Boolean isGroupListShowing) {
        ModelManager modelManager = (ModelManager) model;
        showAll(model);
        if (this.viewType == ListView.MODULES) {
            //Switch view to modules
            if (isModuleListShowing) {
                return new CommandResult(Messages.MESSAGE_ALREADY_ON_MODULE_VIEW);
            } else {
                modelManager.getUi().setModuleListPanel();
                return new CommandResult(Messages.MESSAGE_CHANGED_TO_MODULE_VIEW);
            }
        } else if (this.viewType == ListView.PEOPLE) {
            //Switch view to people
            if (isPersonListShowing) {
                return new CommandResult(Messages.MESSAGE_ALREADY_ON_PEOPLE_VIEW);
            } else {
                modelManager.getUi().setPersonListPanel();
                return new CommandResult(Messages.MESSAGE_CHANGED_TO_PERSON_VIEW);
            }
        } else {
            //Switch view to groups
            assert this.viewType == ListView.GROUPS;
            if (isGroupListShowing) {
                return new CommandResult(Messages.MESSAGE_ALREADY_ON_GROUP_VIEW);
            } else {
                ObservableList<Module> modules = modelManager.getUniBook()
                        .getModuleList();
                ObservableList<Group> groups = FXCollections.observableArrayList();
                for (Module m : modules) {
                    ObservableList<Group> moduleGroups = m.getGroups();
                    groups.addAll(moduleGroups);
                }
                modelManager.getUi().setGroupListPanel(groups);
                return new CommandResult(Messages.MESSAGE_CHANGED_TO_GROUP_VIEW);
            }
        }
    }

    private CommandResult peopleInGroupCommand(Model model, Boolean isPersonListShowing) throws CommandException {
        if (!isPersonListShowing) {
            throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "People"));
        }

        if (!model.hasModule(this.moduleCode)) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_CODE_NOT_EXIST, this.moduleCode));
        }

        Module m = model.getModuleByCode(this.moduleCode);

        if (!m.hasGroupName(this.group)) {
            throw new CommandException(String.format(Messages.MESSAGE_GROUP_NOT_IN_MODULE, this.group));
        }

        Group g = m.getGroupByName(this.group);
        ObservableList<Student> peopleInGroup = g.getMembers();
        Predicate<Person> pred = p -> peopleInGroup.contains(p);
        model.updateFilteredPersonList(pred);
        return new CommandResult(String.format(Messages.MESSAGE_DISPLAYED_PEOPLE_IN_GROUP,
                this.moduleCode.toString(), g.getGroupName()));

    }

    private CommandResult groupFromModulesCommand(Model model, Boolean isPersonListShowing,
                                                  Boolean isGroupListShowing) throws CommandException {
        ModelManager modelManager = (ModelManager) model;

        if (isPersonListShowing) {
            throw new CommandException(Messages.MESSAGE_MODULE_FIELD_MISSING);
        }

        if (isGroupListShowing) {
            throw new CommandException(Messages.MESSAGE_NO_GROUP_FIELD_REQUIRED);
        }

        if (modelManager.getFilteredModuleList().size() == 1) {
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
            throw new CommandException(String.format(Messages.MESSAGE_GROUP_NOT_IN_MODULE, this.group));
        } else {
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
        }
    }

    private CommandResult groupFromGroupsCommand(Model model,
                                                 Boolean isGroupListShowing) throws CommandException {
        ModelManager modelManager = (ModelManager) model;

        if (!isGroupListShowing) {
            throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Groups"));
        }

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

        modelManager.getUi().setGroupListPanel(groups);
        return new CommandResult(String.format(Messages.MESSAGE_DISPLAYED_GROUPS_WITH_NAME,
                this.group.toUpperCase()));
    }

    private CommandResult groupMeetingDateCommand(Model model,
                                                  Boolean isGroupListShowing) throws CommandException {
        ModelManager modelManager = (ModelManager) model;

        if (!isGroupListShowing) {
            throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Groups"));

        }

        try {
            LocalDate localDate = ParserUtil.parseDate(this.date);
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
                        String.format(Messages.MESSAGE_NO_GROUP_WITH_MEETING_DATE, this.date));
            }

            modelManager.getUi().setGroupListPanel(groups);
            return new CommandResult(String.format(Messages.MESSAGE_DISPLAYED_GROUPS_WITH_MEETING_DATE, this.date));

        } catch (ParseException e) {
            throw new CommandException(e.getMessage());
        }
    }

    private CommandResult moduleWithNameCommand(Model model, Boolean isModuleListShowing) throws CommandException {
        ModelManager modelManager = (ModelManager) model;

        if (!isModuleListShowing) {
            throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules"));
        }

        //Module command given in modules page. Displays specified module.
        checkIfKeywordExists(modelManager);
        Predicate<Module> showSpecificModulePredicate = m ->
                m.getModuleName().toString().toLowerCase().contains(this.moduleNameFragment.toLowerCase());
        model.updateFilteredModuleList(showSpecificModulePredicate);
        return new CommandResult(String.format(Messages.MESSAGE_DISPLAYED_MODULES_WITH_NAME,
                this.moduleNameFragment));
    }

    private CommandResult moduleWithKeyEventCommand(Model model, Boolean isModuleListShowing) throws CommandException {
        if (!isModuleListShowing) {
            throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules"));
        }

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

    }

    private CommandResult moduleWithDateCommand(Model model, Boolean isModuleListShowing) throws CommandException {
        if (!isModuleListShowing) {
            throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules"));
        }

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
    }

    private CommandResult moduleWithDateAndEventCommand(Model model, Boolean isModuleListShowing)
            throws CommandException {
        if (!isModuleListShowing) {
            throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules"));
        }

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

    }

    private CommandResult moduleWithDateAndNameCommand(Model model, Boolean isModuleListShowing)
            throws CommandException {
        ModelManager modelManager = (ModelManager) model;

        if (!isModuleListShowing) {
            throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules"));
        }

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
    }

    private CommandResult moduleWithNameAndEventCommand(Model model, Boolean isModuleListShowing)
            throws CommandException {
        ModelManager modelManager = (ModelManager) model;

        if (!isModuleListShowing) {
            throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules"));
        }

        checkIfKeywordExists(modelManager);

        try {
            ModuleKeyEvent.KeyEventType.valueOf(this.keyEvent.toUpperCase());
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

    }

    private CommandResult moduleWithNameAndEventAndDateCommand(Model model, Boolean isModuleListShowing)
            throws CommandException {
        ModelManager modelManager = (ModelManager) model;

        if (!isModuleListShowing) {
            throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Modules"));
        }

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
    }

    private CommandResult specificGroupCommand(Model model,
                                               Boolean isGroupListShowing) throws CommandException {
        ModelManager modelManager = (ModelManager) model;

        if (!isGroupListShowing) {
            throw new CommandException(String.format(Messages.MESSAGE_WRONG_VIEW, "Groups"));
        }

        ObservableList<Group> groups = FXCollections.observableArrayList();
        if (!model.hasModule(moduleCode)) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_CODE_NOT_EXIST, moduleCode));
        }
        Module module = model.getModuleByCode(this.moduleCode);

        if (!module.hasGroupName(this.group)) {
            throw new CommandException(String.format(Messages.MESSAGE_GROUP_NOT_IN_MODULE, this.group));
        }

        groups.add(module.getGroupByName(this.group));
        modelManager.getUi().setGroupListPanel(groups);
        return new CommandResult(String.format(Messages.MESSAGE_DISPLAYED_GROUPS_WITH_NAME,
                this.group.toUpperCase()));
    }
}
