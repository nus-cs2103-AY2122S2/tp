package unibook.logic.parser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

import unibook.commons.core.Messages;
import unibook.logic.commands.AddCommand;
import unibook.logic.parser.exceptions.ParseException;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleKeyEvent;
import unibook.model.module.ModuleName;
import unibook.model.module.group.Group;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Office;
import unibook.model.person.Phone;
import unibook.model.person.Professor;
import unibook.model.person.Student;
import unibook.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {
    public static final String MESSAGE_CONSTRAINTS_OPTION =
        "Options can take only 6 values, module/student/professor/group/meeting/event.";

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_OPTION, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE,
                CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_OFFICE, CliSyntax.PREFIX_TAG,
                CliSyntax.PREFIX_MODULE, CliSyntax.PREFIX_KEYEVENT);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_OPTION)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        String option = argMultimap.getValue(CliSyntax.PREFIX_OPTION).get().trim();
        ModuleName moduleName;
        ModuleCode moduleCode;
        Module module;
        ArrayList<ModuleKeyEvent> keyEventsList = new ArrayList<>();
        ModuleKeyEvent.KeyEventType keyEvent;
        LocalDateTime dateTime;
        ArrayList<LocalDateTime> dateTimeList;
        Name name;
        Phone phone = new Phone();
        Email email = new Email();
        Office office = new Office();
        Set<Tag> tagList;
        Set<Module> moduleList = new LinkedHashSet<>();
        Set<Group> groups = new LinkedHashSet<>();
        Set<ModuleCode> moduleCodes;
        ArrayList<LinkedHashSet<String>> groupNamesList;
        Set<String> groupNamesSet;
        String groupName;

        switch (option) {
        case "group":
            argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_OPTION, CliSyntax.PREFIX_NAME,
                CliSyntax.PREFIX_MODULE, CliSyntax.PREFIX_DATETIME);
            if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_MODULE)) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE_GROUP));
            }
            groupName = argMultimap.getValue(CliSyntax.PREFIX_NAME).get().trim();
            if (groupName.length() == 0) {
                throw new ParseException(Group.NAME_CONSTRAINT_MESSAGE);
            }
            moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(CliSyntax.PREFIX_MODULE).get());
            if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_DATETIME)) {
                dateTimeList = ParserUtil.parseAllDateTimes(argMultimap.getAllValues(CliSyntax.PREFIX_DATETIME));
                return new AddCommand(groupName, moduleCode, dateTimeList);
            }
            return new AddCommand(groupName, moduleCode);
        case "module":
            if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_MODULE)) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE_MODULE));
            }
            moduleName = ParserUtil.parseModuleName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
            moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(CliSyntax.PREFIX_MODULE).get());
            module = new Module(moduleName, moduleCode);
            if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_KEYEVENT)) {
                keyEventsList = ParserUtil.parseModuleKeyEvent(argMultimap.getAllValues(CliSyntax.PREFIX_KEYEVENT),
                    module);
                module.addKeyEventList(keyEventsList);
                return new AddCommand(module);
            } else {
                return new AddCommand(module);
            }
        case "meeting":
            argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_OPTION, CliSyntax.PREFIX_MODULE,
                CliSyntax.PREFIX_GROUP, CliSyntax.PREFIX_DATETIME);
            if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE,
                CliSyntax.PREFIX_GROUP, CliSyntax.PREFIX_DATETIME)) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE_MEETING));
            }
            moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(CliSyntax.PREFIX_MODULE).get());
            groupName = argMultimap.getValue(CliSyntax.PREFIX_GROUP).get().trim();
            if (groupName.length() == 0) {
                throw new ParseException(Group.NAME_CONSTRAINT_MESSAGE);
            }
            dateTimeList = ParserUtil.parseAllDateTimes(argMultimap.getAllValues(CliSyntax.PREFIX_DATETIME));
            return new AddCommand(moduleCode, groupName, dateTimeList);
        case "event":
            argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_OPTION, CliSyntax.PREFIX_MODULE,
                CliSyntax.PREFIX_KEYEVENT, CliSyntax.PREFIX_DATETIME);
            if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE,
                CliSyntax.PREFIX_KEYEVENT, CliSyntax.PREFIX_DATETIME)) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE_EVENT));
            }

            moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(CliSyntax.PREFIX_MODULE).get());
            keyEvent = ParserUtil.parseKeyEventType(argMultimap.getValue(CliSyntax.PREFIX_KEYEVENT).get());
            dateTime = ParserUtil.parseDateTime(argMultimap.getValue(CliSyntax.PREFIX_DATETIME).get());
            return new AddCommand(moduleCode, keyEvent, dateTime);
        case "student":
            if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME)) {
                throw new ParseException(String.format("Missing n/NAME field!\n%s",
                    AddCommand.MESSAGE_USAGE_STUDENT));
            }
            if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_OFFICE)) {
                throw new ParseException(String.format("Student should not have an of/OFFICE field!\n%s",
                        AddCommand.MESSAGE_USAGE_STUDENT));
            }
            if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_GROUP)
                && !arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE)) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE_STUDENT));
            }
            name = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
            if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_PHONE)) {
                phone = ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get());
            }
            if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_EMAIL)) {
                email = ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get());
            }
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));
            moduleCodes = ParserUtil.parseMultipleModulesAndGroups(argMultimap.getAllValues(CliSyntax.PREFIX_MODULE));
            groupNamesList = ParserUtil.parseMultipleGroups(argMultimap.getAllValues(CliSyntax.PREFIX_MODULE));
            Student student = new Student(name, phone, email, tagList, moduleList, groups);
            boolean hasGroups = false;
            for (LinkedHashSet<String> set : groupNamesList) {
                if (!set.isEmpty()) {
                    hasGroups = true;
                    break;
                }
            }
            if (moduleCodes.isEmpty() && !hasGroups) {
                return new AddCommand(student);
            } else if (moduleCodes.isEmpty()) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_MISSING_MODULES));
            } else if (!hasGroups) {
                return new AddCommand(student, moduleCodes);
            } else {
                return new AddCommand(student, moduleCodes, groupNamesList);
            }
        case "professor":
            if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_GROUP)) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE_PROFESSOR));
            }
            if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME)) {
                throw new ParseException(String.format("Missing n/NAME field!\n%s",
                        AddCommand.MESSAGE_USAGE_PROFESSOR));
            }
            name = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
            if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_PHONE)) {
                phone = ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get());
            }
            if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_EMAIL)) {
                email = ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get());
            }
            if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_OFFICE)) {
                office = ParserUtil.parseOffice(argMultimap.getValue(CliSyntax.PREFIX_OFFICE).get());
            }
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));
            moduleCodes = ParserUtil.parseMultipleModules(argMultimap.getAllValues(CliSyntax.PREFIX_MODULE));
            Professor professor = new Professor(name, phone, email, tagList, office, moduleList);
            if (moduleCodes.isEmpty()) {
                return new AddCommand(professor);
            } else {
                return new AddCommand(professor, moduleCodes);
            }
        default:
            throw new ParseException(MESSAGE_CONSTRAINTS_OPTION);
        }
    }
}
