package unibook.logic.parser;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;

import unibook.commons.core.Messages;
import unibook.commons.core.index.Index;
import unibook.logic.commands.DeleteCommand;
import unibook.logic.parser.exceptions.ParseException;
import unibook.model.module.ModuleCode;
import unibook.model.module.group.Group;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static final String WRONG_OPTION = "Only o/module or o/group is allowed";
    private static final String MODULE_MISSING = "Module code is missing! Example: m/modulecode";
    private static final String GROUP_MISSING = "Group name is missing! Example: g/groupname";
    private static final String MODULE_AND_GROUP_MISSING = "Group name and Module code is missing! " +
            "Example: m/modulecode g/groupname";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {

        try {

            // if no params provided case
            if (args.trim().length() == 0) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteCommand.MESSAGE_USAGE));
            }

            boolean indexPresent = false;
            boolean moduleCodePresent;
            boolean optionPresent;
            boolean groupPresent;
            boolean meetingTimePresent;
            boolean phonePresent;
            boolean emailPresent;
            boolean tagPresent;
            boolean officePresent;
            boolean profIndexPresent;
            boolean stuIndexPresent;
            boolean onlyIndexPresent;
            boolean keyEventPresent;

            if (Character.isDigit(args.trim().charAt(0))) {
                indexPresent = true;

            }

            // delete m/MODULECODE case
            ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_MODULE,
                        CliSyntax.PREFIX_OPTION, CliSyntax.PREFIX_GROUP,
                        CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_TAG,
                        CliSyntax.PREFIX_OFFICE, CliSyntax.PREFIX_MEETING_TIME, CliSyntax.PREFIX_PROF_INDEX,
                        CliSyntax.PREFIX_STU_INDEX, CliSyntax.PREFIX_KE_INDEX);

            moduleCodePresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE);
            optionPresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_OPTION);
            groupPresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_GROUP);
            meetingTimePresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MEETING_TIME);
            phonePresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_PHONE);
            emailPresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_EMAIL);
            tagPresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TAG);
            officePresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_OFFICE);
            profIndexPresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_PROF_INDEX);
            stuIndexPresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_STU_INDEX);
            keyEventPresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_KE_INDEX);
            onlyIndexPresent = !moduleCodePresent && !optionPresent && !groupPresent && !meetingTimePresent &&
                    !phonePresent && !emailPresent && !tagPresent && !officePresent && !profIndexPresent
                    && !stuIndexPresent && !keyEventPresent && indexPresent;

            // delete index case
            if (onlyIndexPresent) {
                Index index = ParserUtil.parseIndex(args.trim().split(" ")[0]);
                return new DeleteCommand(index);

            } else if (optionPresent) { // delete module code or group
                String option = argMultimap.getValue(CliSyntax.PREFIX_OPTION).get().toLowerCase();
                System.out.println(option);
                System.out.println(option.equals("group"));
                System.out.println(!moduleCodePresent && !groupPresent);
                System.out.println(!moduleCodePresent);
                System.out.println(!groupPresent);
                if (option.equals("module")) {
                    if (!moduleCodePresent) {
                        throw new ParseException(MODULE_MISSING);
                    }
                    ModuleCode moduleCode = ParserUtil.parseModuleCode(
                            argMultimap.getValue(CliSyntax.PREFIX_MODULE).get());
                    return new DeleteCommand(moduleCode);
                } else if (option.equals("group")) {
                    if (!moduleCodePresent && !groupPresent) {
                        throw new ParseException(MODULE_AND_GROUP_MISSING);
                    }
                    if (!moduleCodePresent) {
                        throw new ParseException(MODULE_MISSING);
                    }
                    if (!groupPresent) {
                        throw new ParseException(GROUP_MISSING);
                    }
                    ModuleCode moduleCode = ParserUtil.parseModuleCode(
                            argMultimap.getValue(CliSyntax.PREFIX_MODULE).get());
                    Group group = ParserUtil.parseGroup(argMultimap.getValue(CliSyntax.PREFIX_GROUP).get());
                    return new DeleteCommand(moduleCode, group);
                } else {
                    throw new ParseException(WRONG_OPTION);
                }

                // delete person traits case
            } else if ((phonePresent || emailPresent || tagPresent || officePresent) && indexPresent) {
                Index index = ParserUtil.parseIndex(args.trim().split(" ")[0]);
                String tag = tagPresent ? argMultimap.getValue(CliSyntax.PREFIX_TAG).get() : null;
                return new DeleteCommand(index, phonePresent, emailPresent, tag, officePresent);

            } else if (indexPresent && profIndexPresent) { // delete student from module on module page

                Index index = ParserUtil.parseIndex(args.trim().split(" ")[0]);
                String profIndexString = argMultimap.getValue(CliSyntax.PREFIX_PROF_INDEX).get();
                Index profIndex = ParserUtil.parseIndex(profIndexString);
                return new DeleteCommand(index, null, profIndex, null, null);

            } else if (indexPresent && stuIndexPresent) { // delete prof from module on module page

                Index index = ParserUtil.parseIndex(args.trim().split(" ")[0]);
                String stuIndexString = argMultimap.getValue(CliSyntax.PREFIX_STU_INDEX).get();
                Index stuIndex = ParserUtil.parseIndex(stuIndexString);
                return new DeleteCommand(index, stuIndex, null, null, null);

            } else if (indexPresent && groupPresent) {

                Index index = ParserUtil.parseIndex(args.trim().split(" ")[0]);
                String groupName = argMultimap.getValue(CliSyntax.PREFIX_GROUP).get();
                System.out.println(groupName);
                Group group = ParserUtil.parseGroup(groupName);
                return new DeleteCommand(index, group);

            } else if (indexPresent && meetingTimePresent) {

                Index index = ParserUtil.parseIndex(args.trim().split(" ")[0]);
                String meetingIndexString = argMultimap.getValue(CliSyntax.PREFIX_MEETING_TIME).get();
                Index meetingIndex = ParserUtil.parseIndex(meetingIndexString);
                return new DeleteCommand(index, null, null, meetingIndex, null);

            } else if (indexPresent && keyEventPresent) {

                Index index = ParserUtil.parseIndex(args.trim().split(" ")[0]);
                String KeyEventIndexString = argMultimap.getValue(CliSyntax.PREFIX_KE_INDEX).get();
                Index keyEventIndex = ParserUtil.parseIndex(KeyEventIndexString);
                return new DeleteCommand(index, null, null, null, keyEventIndex);

            }

            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommand.MESSAGE_USAGE));

        } catch (ParseException pe) {
            throw pe;
        }
    }

}
