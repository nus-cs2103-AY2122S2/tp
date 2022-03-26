package unibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import unibook.logic.commands.exceptions.CommandException;
import unibook.logic.parser.CliSyntax;
import unibook.model.Model;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleKeyEvent;
import unibook.model.module.group.Group;
import unibook.model.person.Person;
import unibook.model.person.Student;

/**
 * Adds a person or module to the UniBook.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Adds a student/professor/module/group/event to the UniBook, depending on the option specified.\n"
        + "Parameters: "
        + CliSyntax.PREFIX_OPTION + "OPTION "
        + CliSyntax.PREFIX_NAME + "NAME "
        + "[" + CliSyntax.PREFIX_PHONE + "PHONE] "
        + "[" + CliSyntax.PREFIX_EMAIL + "EMAIL] "
        + "[" + CliSyntax.PREFIX_OFFICE + "OFFICE] "
        + "[" + CliSyntax.PREFIX_TAG + "TAG]... "
        + "[" + CliSyntax.PREFIX_MODULE + "MODULE]... "
        + "[" + CliSyntax.PREFIX_GROUP + "GROUP]... "
        + "[" + CliSyntax.PREFIX_KEYEVENT + "TYPE] "
        + "[" + CliSyntax.PREFIX_DATETIME + "DATETIME]\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "student "
        + CliSyntax.PREFIX_NAME + "John Doe "
        + CliSyntax.PREFIX_PHONE + "98765432 "
        + CliSyntax.PREFIX_EMAIL + "johnd@example.com "
        + CliSyntax.PREFIX_TAG + "friends "
        + CliSyntax.PREFIX_TAG + "owesMoney "
        + CliSyntax.PREFIX_MODULE + "CS2103 "
        + CliSyntax.PREFIX_MODULE + "CS2105\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "professor "
        + CliSyntax.PREFIX_NAME + "Mary Jane "
        + CliSyntax.PREFIX_PHONE + "98765412 "
        + CliSyntax.PREFIX_EMAIL + "maryj@example.com "
        + CliSyntax.PREFIX_OFFICE + "COM2 01-02 "
        + CliSyntax.PREFIX_TAG + "strict "
        + CliSyntax.PREFIX_TAG + "dean "
        + CliSyntax.PREFIX_MODULE + "CS2103 "
        + CliSyntax.PREFIX_MODULE + "CS2105\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "module "
        + CliSyntax.PREFIX_NAME + "Computer Organisation "
        + CliSyntax.PREFIX_MODULE + "CS2100\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "group "
        + CliSyntax.PREFIX_NAME + "Study Group "
        + CliSyntax.PREFIX_MODULE + "CS2100\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "event "
        + CliSyntax.PREFIX_MODULE + "CS2100 "
        + CliSyntax.PREFIX_KEYEVENT + "1 "
        + CliSyntax.PREFIX_DATETIME + "2022-05-04 13:00\n";
    public static final String MESSAGE_USAGE_MODULE = COMMAND_WORD
        + ": To add a module to the UniBook, use the following format.\n"
        + "Parameters: "
        + CliSyntax.PREFIX_OPTION + "module "
        + CliSyntax.PREFIX_NAME + "NAME "
        + CliSyntax.PREFIX_MODULE + "MODULE "
        + CliSyntax.PREFIX_KEYEVENT + "TYPE "
        + CliSyntax.PREFIX_DATETIME + "DATETIME\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "module "
        + CliSyntax.PREFIX_NAME + "Computer Organisation "
        + CliSyntax.PREFIX_MODULE + "CS2100 "
        + CliSyntax.PREFIX_KEYEVENT + "1 "
        + CliSyntax.PREFIX_DATETIME + "2022-05-04 13:00\n";
    public static final String MESSAGE_USAGE_GROUP = COMMAND_WORD
        + ": To add a group to the UniBook, use the following format.\n"
        + "Parameters: "
        + CliSyntax.PREFIX_OPTION + "group "
        + CliSyntax.PREFIX_NAME + "NAME "
        + CliSyntax.PREFIX_MODULE + "MODULE\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "group "
        + CliSyntax.PREFIX_NAME + "Study Group "
        + CliSyntax.PREFIX_MODULE + "CS2100\n";
    public static final String MESSAGE_USAGE_STUDENT = COMMAND_WORD
        + ": To add a student to the UniBook, use the following format.\n"
        + "Parameters: "
        + CliSyntax.PREFIX_OPTION + "student "
        + CliSyntax.PREFIX_NAME + "NAME "
        + CliSyntax.PREFIX_PHONE + "PHONE "
        + CliSyntax.PREFIX_EMAIL + "EMAIL "
        + "[" + CliSyntax.PREFIX_TAG + "TAG]... "
        + "[" + CliSyntax.PREFIX_MODULE + "MODULE]...\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "student "
        + CliSyntax.PREFIX_NAME + "John Doe "
        + CliSyntax.PREFIX_PHONE + "98765432 "
        + CliSyntax.PREFIX_EMAIL + "johnd@example.com "
        + CliSyntax.PREFIX_TAG + "friends "
        + CliSyntax.PREFIX_TAG + "owesMoney "
        + CliSyntax.PREFIX_MODULE + "CS2103 "
        + CliSyntax.PREFIX_MODULE + "CS2105\n";
    public static final String MESSAGE_USAGE_PROFESSOR = COMMAND_WORD
        + ": To add a professor to the UniBook, use the following format.\n"
        + "Parameters: "
        + CliSyntax.PREFIX_OPTION + "professor "
        + CliSyntax.PREFIX_NAME + "NAME "
        + CliSyntax.PREFIX_PHONE + "PHONE "
        + CliSyntax.PREFIX_EMAIL + "EMAIL "
        + CliSyntax.PREFIX_OFFICE + "OFFICE "
        + "[" + CliSyntax.PREFIX_TAG + "TAG]... "
        + "[" + CliSyntax.PREFIX_MODULE + "MODULE]...\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "professor "
        + CliSyntax.PREFIX_NAME + "Mary Jane "
        + CliSyntax.PREFIX_PHONE + "98765412 "
        + CliSyntax.PREFIX_EMAIL + "maryj@example.com "
        + CliSyntax.PREFIX_OFFICE + "COM2 01-02 "
        + CliSyntax.PREFIX_TAG + "strict "
        + CliSyntax.PREFIX_TAG + "dean "
        + CliSyntax.PREFIX_MODULE + "CS2103 "
        + CliSyntax.PREFIX_MODULE + "CS2105\n";
    public static final String MESSAGE_MISSING_MODULES = "You cannot add a student to a group"
        + "without the student being in any modules first.\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "student "
        + CliSyntax.PREFIX_NAME + "John Doe "
        + CliSyntax.PREFIX_PHONE + "98765432 "
        + CliSyntax.PREFIX_EMAIL + "johnd@example.com "
        + CliSyntax.PREFIX_TAG + "friends "
        + CliSyntax.PREFIX_TAG + "owesMoney "
        + CliSyntax.PREFIX_MODULE + "CS2103 "
        + CliSyntax.PREFIX_MODULE + "CS2105 "
        + CliSyntax.PREFIX_GROUP + "CS2103 G16\n";
    public static final String MESSAGE_USAGE_EVENT = COMMAND_WORD
        + ": To add a key event to a module, use the following format.\n"
        + "Parameters: "
        + CliSyntax.PREFIX_OPTION + "event "
        + CliSyntax.PREFIX_MODULE + "MODULE "
        + CliSyntax.PREFIX_KEYEVENT + "TYPE "
        + CliSyntax.PREFIX_DATETIME + "DATETIME\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "event "
        + CliSyntax.PREFIX_MODULE + "CS2100 "
        + CliSyntax.PREFIX_KEYEVENT + "1 "
        + CliSyntax.PREFIX_DATETIME + "2022-05-04 13:00\n";
    public static final String MESSAGE_USAGE_MEETING = COMMAND_WORD
        + ": To add a meeting to a group, use the following format.\n"
        + "Parameters: "
        + CliSyntax.PREFIX_OPTION + "meeting "
        + CliSyntax.PREFIX_MODULE + "MODULE "
        + CliSyntax.PREFIX_GROUP + "GROUPNAME "
        + CliSyntax.PREFIX_DATETIME + "DATETIME\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "meeting "
        + CliSyntax.PREFIX_MODULE + "CS2100 "
        + CliSyntax.PREFIX_GROUP + "Project Work "
        + CliSyntax.PREFIX_DATETIME + "2022-05-04 13:00\n";

    public static final String MESSAGE_SUCCESS_PERSON = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the UniBook";

    public static final String MESSAGE_SUCCESS_MODULE = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the UniBook";

    public static final String MESSAGE_SUCCESS_GROUP = "New group added: %1$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the specified "
        + "module in the UniBook";

    public static final String MESSAGE_SUCCESS_MEETING = "New meeting time added.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting time already exists in the specified "
            + "group in the UniBook";

    public static final String MESSAGE_SUCCESS_EVENT = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the module specified.";

    public static final String MESSAGE_MODULE_DOES_NOT_EXIST = "One or more of the modules entered"
        + " does not exist in the UniBook";
    public static final String MESSAGE_GROUP_DOES_NOT_EXIST = "One or more of the groups entered"
        + " does not exist in the UniBook";

    private Person personToAdd;
    private Module moduleToAdd;
    private Group groupToAdd;
    private String groupName;
    private ModuleCode moduleCode;
    private Set<ModuleCode> moduleCodeSet = new HashSet<>();
    private ArrayList<LinkedHashSet<String>> groupNamesList;
    private ModuleKeyEvent.KeyEventType keyEvent;
    private LocalDateTime dateTime;
    private ArrayList<LocalDateTime> dateTimeList;
    private Boolean isAddMeetingTime = false;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        personToAdd = person;
    }

    /**
     * Creates an AddCommand to add the specified {@code Person} with {@code moduleCodes}
     */
    public AddCommand(Person person, Set<ModuleCode> moduleCodes) {
        requireNonNull(person);
        personToAdd = person;
        moduleCodeSet = moduleCodes;
    }

    /**
     * Creates an AddCommand to add the specified {@code Person} with {@code moduleCodes} into {@code groups}
     */
    public AddCommand(Person person, Set<ModuleCode> moduleCodes, ArrayList<LinkedHashSet<String>> groups) {
        requireNonNull(person);
        personToAdd = person;
        moduleCodeSet = moduleCodes;
        groupNamesList = groups;
    }

    /**
     * Creates an AddCommand to add the specified {@code Module}
     */
    public AddCommand(Module module) {
        requireNonNull(module);
        moduleToAdd = module;
    }

    /**
     * Creates an AddCommand to add the {@code ModuleKeyEvent} into the specified {@code Module}
     */
    public AddCommand(ModuleCode moduleCode, ModuleKeyEvent.KeyEventType keyEvent, LocalDateTime dateTime) {
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode;
        this.keyEvent = keyEvent;
        this.dateTime = dateTime;
    }

    /**
     * Creates an AddCommand to add {@code LocalDateTime meetings} into the specified {@code Group}
     */
    public AddCommand(ModuleCode moduleCode, String groupName, ArrayList<LocalDateTime> dateTimes) {
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode;
        this.groupName = groupName;
        dateTimeList = dateTimes;
        isAddMeetingTime = true;
    }

    /**
     * Creates an AddCommand to add a Group to UniBook with {@code groupName} to {@code moduleCode}
     */
    public AddCommand(String groupNameToAdd, ModuleCode moduleCodeOfGroup) {
        requireNonNull(groupNameToAdd);
        requireNonNull(moduleCodeOfGroup);
        groupName = groupNameToAdd;
        moduleCode = moduleCodeOfGroup;
    }

    /**
     * Creates an AddCommand to add a Group to UniBook with {@code String groupName}
     * and meetings {@code ArrayList<LocalDateTime> dateTimes} to {@code moduleCode}
     */
    public AddCommand(String groupNameToAdd, ModuleCode moduleCodeOfGroup, ArrayList<LocalDateTime> dateTimes) {
        requireNonNull(groupNameToAdd);
        requireNonNull(moduleCodeOfGroup);
        groupName = groupNameToAdd;
        moduleCode = moduleCodeOfGroup;
        dateTimeList = dateTimes;
    }

    @Override
    public CommandResult execute(Model model, Boolean isPersonListShowing,
                                 Boolean isModuleListShowing) throws CommandException {
        requireNonNull(model);

        if (moduleToAdd != null) {
            if (model.hasModule(moduleToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_MODULE);
            }
            model.addModule(moduleToAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS_MODULE, moduleToAdd));
        } else if (personToAdd != null) {
            if (model.hasPerson(personToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            } else if (!model.isModuleExist(moduleCodeSet)) {
                throw new CommandException(MESSAGE_MODULE_DOES_NOT_EXIST);
            }
            Set<Module> personModules = personToAdd.getModulesModifiable();
            if (groupNamesList != null) {
                Set<Group> studentGroups = ((Student) personToAdd).getGroups();
                Iterator<LinkedHashSet<String>> groupNamesListItr = groupNamesList.iterator();
                for (ModuleCode moduleCode : moduleCodeSet) {
                    Module moduleToAdd = model.getModuleByCode(moduleCode);
                    personModules.add(moduleToAdd);
                    LinkedHashSet<String> groupSet = groupNamesListItr.next();
                    if (groupSet.isEmpty()) {
                        continue;
                    }
                    for (String groupName : groupSet) {
                        if (!moduleToAdd.hasGroupName(groupName)) {
                            throw new CommandException(MESSAGE_GROUP_DOES_NOT_EXIST);
                        } else {
                            Group currGroup = moduleToAdd.getGroupByName(groupName);
                            studentGroups.add(currGroup);
                        }
                    }
                }
            } else {
                for (ModuleCode moduleCode : moduleCodeSet) {
                    Module moduleToAdd = model.getModuleByCode(moduleCode);
                    personModules.add(moduleToAdd);
                }
            }
            model.addPersonToTheirModules(personToAdd);
            if (personToAdd instanceof Student) {
                model.addPersonToTheirGroups(personToAdd);
            }
            model.addPerson(personToAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS_PERSON, personToAdd));
        } else if (keyEvent != null) {
            if (!model.hasModule(moduleCode)) {
                throw new CommandException(MESSAGE_MODULE_DOES_NOT_EXIST);
            }
            Module moduleToAddEventTo = model.getModuleByCode(moduleCode);
            if (moduleToAddEventTo.hasEvent(keyEvent, dateTime)) {
                throw new CommandException(MESSAGE_DUPLICATE_EVENT);
            }
            ModuleKeyEvent moduleKeyEvent = new ModuleKeyEvent(keyEvent, dateTime, moduleToAddEventTo);
            moduleToAddEventTo.addKeyEvent(moduleKeyEvent);
            return new CommandResult(String.format(MESSAGE_SUCCESS_EVENT, moduleKeyEvent));
        } else if (isAddMeetingTime) {
            if (!model.hasModule(moduleCode)) {
                throw new CommandException(MESSAGE_MODULE_DOES_NOT_EXIST);
            }
            Module moduleGroupIsIn = model.getModuleByCode(moduleCode);
            Group groupToUpdate = moduleGroupIsIn.getGroupByName(groupName);
            for (LocalDateTime dateTime : dateTimeList) {
                try {
                    groupToUpdate.addMeetingTime(dateTime);
                } catch (Exception e) {
                    throw new CommandException(MESSAGE_DUPLICATE_MEETING);
                }
            }
            return new CommandResult(MESSAGE_SUCCESS_MEETING);
        } else {
            if (!model.hasModule(moduleCode)) {
                throw new CommandException(MESSAGE_MODULE_DOES_NOT_EXIST);
            }
            Module moduleToAddGroupTo = model.getModuleByCode(moduleCode);
            if (moduleToAddGroupTo.hasGroupName(groupName)) {
                throw new CommandException(MESSAGE_DUPLICATE_GROUP);
            }
            if (dateTimeList != null) {
                groupToAdd = new Group(groupName, moduleToAddGroupTo,
                    FXCollections.observableArrayList(dateTimeList));
            } else {
                groupToAdd = new Group(groupName, moduleToAddGroupTo);
            }
            model.addGroup(groupToAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS_GROUP, groupToAdd));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && personToAdd.equals(((AddCommand) other).personToAdd));
    }
}

