package unibook.logic.parser;

import java.util.stream.Stream;

import unibook.commons.core.Messages;
import unibook.logic.commands.ListCommand;
import unibook.logic.parser.exceptions.ParseException;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleName;


/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_OPTION, CliSyntax.PREFIX_TYPE,
                CliSyntax.PREFIX_MODULE, CliSyntax.PREFIX_VIEW, CliSyntax.PREFIX_GROUP,
                CliSyntax.PREFIX_MEETINGTIME, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_KEYEVENT,
                CliSyntax.PREFIX_DATETIME);

        //List all (empty list command)
        if (args.strip().equals("")) {
            return new ListCommand();
        }

        //List command that does not have o/OPTION
        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_OPTION)) {
            return parseNoOption(argMultimap);
        }

        String option = argMultimap.getValue(CliSyntax.PREFIX_OPTION).get();

        if (option.strip().isEmpty()) {
            //The o/OPTION field cannot be blank.
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Option"));
        }

        //List command that has o/OPTION
        switch (option) {
        case "view":
            return viewOption(argMultimap);
        case "module":
            return moduleOption(argMultimap);
        case "group":
            return groupOption(argMultimap);
        default:
            //The o/OPTION field is invalid.
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_INVALID_LIST_OPTION));
        }
    }

    //Helper methods for commands without o/OPTION
    private static ListCommand dateTimeAndEventAndNamePresent(ArgumentMultimap argMultimap) throws ParseException {
        //On module page, checking for modules that contain a specific name with specific date, key event
        //e.g. list n/software dt/2022-05-04 ke/exam
        String dateString = argMultimap.getValue(CliSyntax.PREFIX_DATETIME).get();
        String keyEvent = argMultimap.getValue(CliSyntax.PREFIX_KEYEVENT).get();
        String name = argMultimap.getValue(CliSyntax.PREFIX_NAME).get();

        if (dateString.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Date"));
        }

        if (keyEvent.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Key Event"));
        }

        if (name.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Name"));
        }

        if (!ModuleName.isValidModuleName(name)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_MODULE_NAME));
        }

        return new ListCommand(name, dateString, keyEvent.toUpperCase(),
                ListCommand.ListCommandType.MODULESWITHDATEANDKEYEVENTANDNAME);
    }

    private static ListCommand dateTimeAndEventPresent(ArgumentMultimap argMultimap) throws ParseException {
        //On module page, checking for modules with specific date and key event
        //e.g. list dt/2022-05-04 ke/exam
        String dateString = argMultimap.getValue(CliSyntax.PREFIX_DATETIME).get();
        String keyEvent = argMultimap.getValue(CliSyntax.PREFIX_KEYEVENT).get();

        if (dateString.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Date"));
        }

        if (keyEvent.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Key Event"));
        }

        return new ListCommand(dateString, keyEvent.toUpperCase(),
                ListCommand.ListCommandType.MODULESWITHDATEANDKEYEVENT);
    }

    private static ListCommand nameAndDateTimePresent(ArgumentMultimap argMultimap) throws ParseException {
        //On module page, checking for modules with specific name match and date time
        String dateString = argMultimap.getValue(CliSyntax.PREFIX_DATETIME).get();
        String name = argMultimap.getValue(CliSyntax.PREFIX_NAME).get();

        if (dateString.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Date"));
        }

        if (name.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Name"));
        }

        if (!ModuleName.isValidModuleName(name)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_MODULE_NAME));
        }

        return new ListCommand(dateString, name,
                ListCommand.ListCommandType.MODULESWITHDATEANDNAME);
    }

    private static ListCommand nameAndEventPresent(ArgumentMultimap argMultimap) throws ParseException {
        //On module page, checking for modules with specific name match and key event
        String name = argMultimap.getValue(CliSyntax.PREFIX_NAME).get();
        String keyEvent = argMultimap.getValue(CliSyntax.PREFIX_KEYEVENT).get();

        if (name.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Name"));
        }

        if (keyEvent.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Key Event"));
        }

        if (!ModuleName.isValidModuleName(name)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_MODULE_NAME));
        }

        return new ListCommand(name, keyEvent,
                ListCommand.ListCommandType.MODULESWITHNAMEANDKEYEVENT);
    }

    private static ListCommand typePresent(ArgumentMultimap argMultimap) throws ParseException {
        //Listing types of people on people page
        //e.g. list type/professors
        String type = argMultimap.getValue(CliSyntax.PREFIX_TYPE).get().toLowerCase();

        if (type.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Type"));
        }


        if (!(type.equals("professors") || type.equals("students"))) {
            //invalid type argument
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, Messages.MESSAGE_WRONG_TYPE));
        }

        return new ListCommand(type, ListCommand.ListCommandType.TYPE);
    }

    private static ListCommand groupPresent(ArgumentMultimap argMultimap) throws ParseException {
        //Group prefix present without option field (Listing groups from group page)
        String group = argMultimap.getValue(CliSyntax.PREFIX_GROUP).get().toLowerCase();

        if (group.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Group"));
        }

        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE)) {
            //list g/W16-1 m/module
            String module = argMultimap.getValue(CliSyntax.PREFIX_MODULE).get().toLowerCase();

            if (!ModuleCode.isValidModuleCode(module)) {
                throw new ParseException(Messages.MESSAGE_INVALID_MODULE_CODE);
            }

            if (module.strip().isEmpty()) {
                throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Module"));
            }


            return new ListCommand(group, module, ListCommand.ListCommandType.SPECIFICGROUPFROMGROUPVIEW);
        }

        //e.g. list g/W16-1
        return new ListCommand(group, ListCommand.ListCommandType.GROUPFROMGROUPVIEW);
    }

    private static ListCommand meetingPresent(ArgumentMultimap argMultimap) throws ParseException {
        //Find all groups with given meeting time on group page
        //e.g. list mt/<YYYY-MM-DD>

        String dateString = argMultimap.getValue(CliSyntax.PREFIX_MEETINGTIME).get();

        if (dateString.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Meeting"));
        }

        return new ListCommand(dateString, ListCommand.ListCommandType.GROUPWITHMEETINGDATE);
    }

    private static ListCommand datePresent(ArgumentMultimap argMultimap) throws ParseException {
        String dateString = argMultimap.getValue(CliSyntax.PREFIX_DATETIME).get();

        if (dateString.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Date"));
        }

        return new ListCommand(dateString, ListCommand.ListCommandType.MODULESWITHEVENTDATE);
    }

    private static ListCommand eventPresent(ArgumentMultimap argMultimap) throws ParseException {
        //Listing modules that contain the given key event
        //e.g. list ke/exam, list ke/quiz
        String keyEvent = argMultimap.getValue(CliSyntax.PREFIX_KEYEVENT).get();

        if (keyEvent.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Key Event"));
        }

        return new ListCommand(keyEvent, ListCommand.ListCommandType.MODULESWITHKEYEVENT);
    }

    private static ListCommand namePresent(ArgumentMultimap argMultimap) throws ParseException {
        //Listing modules that contain the given name on module page
        // e.g. list n/software, list n/operating systems
        // Name only
        String name = argMultimap.getValue(CliSyntax.PREFIX_NAME).get();

        if (name.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Name"));
        }

        if (!ModuleName.isValidModuleName(name)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_MODULE_NAME));
        }

        return new ListCommand(name, ListCommand.ListCommandType.MODULEWITHNAMEMATCH);
    }

    private static ListCommand modulePresent(ArgumentMultimap argMultimap) throws ParseException {
        //listing module code in module view
        String moduleCode = argMultimap.getValue(CliSyntax.PREFIX_MODULE).get().toUpperCase();

        if (moduleCode.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Module"));
        }

        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new ParseException(Messages.MESSAGE_INVALID_MODULE_CODE);
        }

        return new ListCommand(moduleCode, ListCommand.ListCommandType.MODULE);
    }



    private static ListCommand parseNoOption(ArgumentMultimap argMultimap) throws ParseException {
        //Commands that have no option field
        //Very specific command chain with 2-3 arguments
        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_DATETIME, CliSyntax.PREFIX_KEYEVENT,
                CliSyntax.PREFIX_NAME)) {
            return dateTimeAndEventAndNamePresent(argMultimap);
        }

        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_DATETIME, CliSyntax.PREFIX_KEYEVENT)) {
            return dateTimeAndEventPresent(argMultimap);
        }

        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DATETIME)) {
            return nameAndDateTimePresent(argMultimap);
        }

        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_KEYEVENT)) {
            return nameAndEventPresent(argMultimap);
        }

        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TYPE)) {
            return typePresent(argMultimap);
        }

        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_GROUP)) {
            return groupPresent(argMultimap);
        }

        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MEETINGTIME)) {
            return meetingPresent(argMultimap);
        }

        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_DATETIME)) {
            return datePresent(argMultimap);
        }

        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_KEYEVENT)) {
            return eventPresent(argMultimap);
        }

        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME)) {
            return namePresent(argMultimap);
        }

        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE)) {
            return modulePresent(argMultimap);
        }

        throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                ListCommand.MESSAGE_USAGE));
    }

    //Helper methods for commands with o/OPTION field
    private static ListCommand viewOption(ArgumentMultimap argMultimap) throws ParseException {
        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_VIEW)) {
            String view = argMultimap.getValue(CliSyntax.PREFIX_VIEW).get().toLowerCase();

            if (view.strip().isEmpty()) {
                throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "View"));
            }

            if (view.equals("modules")) {
                //Change to module view
                return new ListCommand(ListCommand.ListView.MODULES, ListCommand.ListCommandType.VIEW);
            } else if (view.equals("people")) {
                //Change to people view
                return new ListCommand(ListCommand.ListView.PEOPLE, ListCommand.ListCommandType.VIEW);
            } else if (view.equals("groups")) {
                //Change to group view
                return new ListCommand(ListCommand.ListView.GROUPS, ListCommand.ListCommandType.VIEW);
            } else {
                //Invalid view argument given
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        Messages.MESSAGE_INVALID_VIEW));
            }
        } else {
            //View type argument not present
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                            Messages.MESSAGE_VIEW_FIELD_MISSING));
        }
    }

    private static ListCommand moduleOption(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE)) {
            //module argument not present
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_MODULE_FIELD_MISSING));
        }
        String moduleCode = argMultimap.getValue(CliSyntax.PREFIX_MODULE).get().toUpperCase();

        if (moduleCode.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Module"));
        }

        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new ParseException(Messages.MESSAGE_INVALID_MODULE_CODE);
        }

        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TYPE)) {
            //list module with specific type, e.g. list o/module m/cs2103 ty/professors
            String type = argMultimap.getValue(CliSyntax.PREFIX_TYPE).get().toLowerCase();

            if (type.strip().isEmpty()) {
                throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Type"));
            }

            if (!(type.equals("professors") || type.equals("students"))) {
                throw new ParseException(
                        String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                                Messages.MESSAGE_WRONG_TYPE));
            }
            return new ListCommand(moduleCode, type, ListCommand.ListCommandType.MODULEANDTYPE);
        } else {
            //list module with no specific type e.g. list o/module m/cs2103 (in people page)

            if (!ModuleCode.isValidModuleCode(moduleCode)) {
                throw new ParseException(Messages.MESSAGE_INVALID_MODULE_CODE);
            }

            return new ListCommand(moduleCode, ListCommand.ListCommandType.MODULE);
        }
    }

    private static ListCommand groupOption(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_GROUP)) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                            Messages.MESSAGE_GROUP_FIELD_MISSING));
        }
        String group = argMultimap.getValue(CliSyntax.PREFIX_GROUP).get().toLowerCase();

        if (group.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Group"));
        }

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE)) {
            //Module page listing group
            return new ListCommand(group, ListCommand.ListCommandType.GROUPFROMMODULEVIEW);
        }
        //People page listing group in a specific module
        //e.g. list o/group m/cs2103 g/w16-1
        String module = argMultimap.getValue(CliSyntax.PREFIX_MODULE).get().toUpperCase();

        if (module.strip().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Module"));
        }

        return new ListCommand(group, module, ListCommand.ListCommandType.PEOPLEINGROUP);
    }

}
