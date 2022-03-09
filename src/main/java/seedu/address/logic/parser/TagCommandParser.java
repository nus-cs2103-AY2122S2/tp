package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Cca;
import seedu.address.model.person.Education;
import seedu.address.model.person.Internship;
import seedu.address.model.person.Module;
import seedu.address.model.tag.Tag;


/**
 * Parses input arguments and creates a new {@Code TagCommand} object
 */
public class TagCommandParser implements Parser<TagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagCommand
     * and returns an TagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CCA, PREFIX_EDUCATION,
                PREFIX_MODULE, PREFIX_INTERNSHIP);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE), ive);
        }

        String education = argMultimap.getValue(PREFIX_EDUCATION).orElse("");
        String cca = argMultimap.getValue(PREFIX_CCA).orElse("");
        String internship = argMultimap.getValue(PREFIX_INTERNSHIP).orElse("");
        String module = argMultimap.getValue(PREFIX_MODULE).orElse("");

        return new TagCommand(index, convertToList(education, "education"), convertToList(internship, "internship"),
                convertToList(module, "module"), convertToList(cca, "cca"));
    }

    /**
     * Converts a string of tag args into a list of tags.
     * @param args that contains all the tags
     * @param tag the instance type of the tag
     * @return list of tags
     */
    public static List<Tag> convertToList(String args, String tag) {
        List<Tag> output = new ArrayList<>();

        if (args.isEmpty()) {
            return output;
        }

        String[] temp = args.split(",");
        for (String curr : temp) {
            curr = curr.trim().toLowerCase();
            switch (tag) {
            case "education":
                output.add(new Education(curr));
                break;
            case "internship":
                output.add(new Internship(curr));
                break;
            case "module":
                output.add(new Module(curr));
                break;
            default:
                output.add(new Cca(curr));
                break;
            }
        }

        Set<Tag> set = new HashSet<>(output);
        output.clear();
        output.addAll(set);
        return output;
    }
}
