package unibook.logic.parser;

import java.util.stream.Stream;

import unibook.commons.core.Messages;
import unibook.logic.commands.ListCommand;
import unibook.logic.parser.exceptions.ParseException;
import unibook.model.module.ModuleCode;


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
        try {
            ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_OPTION, CliSyntax.PREFIX_TYPE,
                    CliSyntax.PREFIX_MODULE, CliSyntax.PREFIX_VIEW, CliSyntax.PREFIX_GROUP,
                    CliSyntax.PREFIX_MEETINGTIME, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_KEYEVENT,
                    CliSyntax.PREFIX_DATETIME);

            //List all (empty list command)
            if (args.strip().equals("")) {
                return new ListCommand();
            }

            if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_OPTION)) {
                //Commands that have no option field
                //Very specific command chain with 2-3 arguments
                if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_DATETIME, CliSyntax.PREFIX_KEYEVENT,
                    CliSyntax.PREFIX_NAME)) {
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

                    return new ListCommand(name, dateString, keyEvent.toUpperCase(),
                        ListCommand.ListCommandType.MODULESWITHDATEANDKEYEVENTANDNAME);
                }
                if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_DATETIME, CliSyntax.PREFIX_KEYEVENT)) {
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
                if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DATETIME)) {
                    //On module page, checking for modules with specific name match and date time
                    String dateString = argMultimap.getValue(CliSyntax.PREFIX_DATETIME).get();
                    String name = argMultimap.getValue(CliSyntax.PREFIX_NAME).get();

                    if (dateString.strip().isEmpty()) {
                        throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Date"));
                    }

                    if (name.strip().isEmpty()) {
                        throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Name"));
                    }

                    return new ListCommand(dateString, name,
                        ListCommand.ListCommandType.MODULESWITHDATEANDNAME);
                }
                if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_KEYEVENT)) {
                    //On module page, checking for modules with specific name match and key event
                    String name = argMultimap.getValue(CliSyntax.PREFIX_NAME).get();
                    String keyEvent = argMultimap.getValue(CliSyntax.PREFIX_KEYEVENT).get();

                    if (name.strip().isEmpty()) {
                        throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Name"));
                    }

                    if (keyEvent.strip().isEmpty()) {
                        throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Key Event"));
                    }


                    return new ListCommand(name, keyEvent,
                        ListCommand.ListCommandType.MODULESWITHNAMEANDKEYEVENT);

                }
                if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TYPE)) {
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
                } else if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_GROUP)) {
                    //Group prefix present without option field (Listing groups from group page)
                    String group = argMultimap.getValue(CliSyntax.PREFIX_GROUP).get().toLowerCase();

                    if (group.strip().isEmpty()) {
                        throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Group"));
                    }

                    if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE)) {
                        //list g/W16-1 m/module
                        String module = argMultimap.getValue(CliSyntax.PREFIX_MODULE).get().toLowerCase();

                        if (!ModuleCode.isValidModuleCode(module)) {
                            throw new ParseException("Module Codes should only contain alphanumeric characters.");
                        }

                        if (module.strip().isEmpty()) {
                            throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Module"));
                        }


                        return new ListCommand(group, module, ListCommand.ListCommandType.SPECIFICGROUPFROMGROUPVIEW);
                    }

                    //e.g. list g/W16-1
                    return new ListCommand(group, ListCommand.ListCommandType.GROUPFROMGROUPVIEW);
                } else if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MEETINGTIME)) {
                    //Find all groups with given meeting time on group page
                    //e.g. list mt/<YYYY-MM-DD>

                    String dateString = argMultimap.getValue(CliSyntax.PREFIX_MEETINGTIME).get();

                    if (dateString.strip().isEmpty()) {
                        throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Meeting"));
                    }

                    return new ListCommand(dateString, ListCommand.ListCommandType.GROUPWITHMEETINGDATE);
                } else if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_DATETIME)) {
                    String dateString = argMultimap.getValue(CliSyntax.PREFIX_DATETIME).get();

                    if (dateString.strip().isEmpty()) {
                        throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Date"));
                    }

                    return new ListCommand(dateString, ListCommand.ListCommandType.MODULESWITHEVENTDATE);
                } else if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_KEYEVENT)) {
                    //Listing modules that contain the given key event
                    //e.g. list ke/exam, list ke/quiz
                    String keyEvent = argMultimap.getValue(CliSyntax.PREFIX_KEYEVENT).get();

                    if (keyEvent.strip().isEmpty()) {
                        throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Key Event"));
                    }

                    return new ListCommand(keyEvent, ListCommand.ListCommandType.MODULESWITHKEYEVENT);
                } else if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME)) {
                    //Listing modules that contain the given name on module page
                    // e.g. list n/software, list n/operating systems
                    // Name only
                    String name = argMultimap.getValue(CliSyntax.PREFIX_NAME).get();

                    if (name.strip().isEmpty()) {
                        throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Name"));
                    }

                    return new ListCommand(name, ListCommand.ListCommandType.MODULEWITHNAMEMATCH);
                } else if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE)) {
                    //listing module code in module view
                    String moduleCode = argMultimap.getValue(CliSyntax.PREFIX_MODULE).get().toUpperCase();

                    if (moduleCode.strip().isEmpty()) {
                        throw new ParseException(String.format(Messages.MESSAGE_FIELD_EMPTY, "Module"));
                    }

                    if (!ModuleCode.isValidModuleCode(moduleCode)) {
                        throw new ParseException("Module Codes should only contain alphanumeric characters.");
                    }

                    return new ListCommand(moduleCode, ListCommand.ListCommandType.MODULE);
                } else {
                    throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        ListCommand.MESSAGE_USAGE));
                }
            }
            String option = argMultimap.getValue(CliSyntax.PREFIX_OPTION).get();
            //Commands which have o/<OPTION> parameter.
            switch (option) {
            case "view":
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
            case "module":
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
                    throw new ParseException("Module Codes should only contain alphanumeric characters.");
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
                        throw new ParseException("Module Codes should only contain alphanumeric characters.");
                    }

                    return new ListCommand(moduleCode, ListCommand.ListCommandType.MODULE);
                }
            case "group":

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

            default:
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_INVALID_LIST_OPTION));

            }
        } catch (ParseException pe) {
            throw pe;
        }

    }

}
