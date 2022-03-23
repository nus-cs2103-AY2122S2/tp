package unibook.logic.parser;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import unibook.commons.core.Messages;
import unibook.logic.commands.AddCommand;
import unibook.logic.parser.exceptions.ParseException;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleName;
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
            "Options can take only 3 values, module/student/professor.";

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
                    CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_OFFICE, CliSyntax.PREFIX_TAG, CliSyntax.PREFIX_MODULE);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_OPTION, CliSyntax.PREFIX_NAME)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        String option = argMultimap.getValue(CliSyntax.PREFIX_OPTION).get().trim();
        ModuleName moduleName;
        ModuleCode moduleCode;
        Name name;
        Phone phone;
        Email email;
        Office office;
        Set<Tag> tagList;
        Set<Module> moduleList = new HashSet<>();
        Set<ModuleCode> moduleCodes;

        switch (option) {
        case "module":
            if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE)) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        AddCommand.MESSAGE_USAGE_MODULE));
            }
            moduleName = ParserUtil.parseModuleName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
            moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(CliSyntax.PREFIX_MODULE).get());
            return new AddCommand(moduleName, moduleCode);
        case "student":
            if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL)) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        AddCommand.MESSAGE_USAGE_STUDENT));
            }
            name = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
            phone = ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get());
            email = ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get());
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));
            moduleCodes = ParserUtil.parseMultipleModules(argMultimap.getAllValues(CliSyntax.PREFIX_MODULE));
            //TODO instantiate student with actual modules, not codes
            Student student = new Student(name, phone, email, tagList, moduleList, null);
            return new AddCommand(student);
        case "professor":
            if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_PHONE,
                    CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_OFFICE)) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        AddCommand.MESSAGE_USAGE_PROFESSOR));
            }
            name = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
            phone = ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get());
            email = ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get());
            office = ParserUtil.parseOffice(argMultimap.getValue(CliSyntax.PREFIX_OFFICE).get());
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));
            moduleCodes = ParserUtil.parseMultipleModules(argMultimap.getAllValues(CliSyntax.PREFIX_MODULE));
            //TODO instantiate prof with actual modules, not codes
            Professor professor = new Professor(name, phone, email, tagList, office, moduleList);
            return new AddCommand(professor);
        default:
            throw new ParseException(MESSAGE_CONSTRAINTS_OPTION);
        }
    }
}
