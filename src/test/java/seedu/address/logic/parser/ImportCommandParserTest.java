package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ImportCommandParser.FILE_DOES_NOT_EXIST;
import static seedu.address.logic.parser.ImportCommandParser.WRONG_FILE_TYPE;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Job;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Stage;

public class ImportCommandParserTest {

    private ImportCommandParser parser = new ImportCommandParser();
    private String folderPath = "./src/test/data/CsvTest";

    @Test
    public void parse_filepath() {

        // Empty path
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        // File does not exist
        assertParseFailure(parser, "xxx.csv", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FILE_DOES_NOT_EXIST));

        // Wrong file format
        assertParseFailure(parser, folderPath + "/wrong_file_format.txt",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, WRONG_FILE_TYPE));
        assertParseFailure(parser, folderPath + "/no_file_format",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, WRONG_FILE_TYPE));

    }

    @Test
    public void parse_data() {

        List<Person> personList = new ArrayList<>();
        personList.add(new Person(new Name("Alejandro Sharma"), new Phone("56564367"),
            new Email("alejandros@gmail.com"), new Address("41 High Point Ave. Holly Springs, NC 27540"),
            new Job("Computer Hardware Engineer"), new Stage("ACCEPTED")));
        personList.add(new Person(new Name("Elly Beattie"), new Phone("62888566"),
                new Email("ellyb@yahoo.com"), new Address("94 Littleton Lane Rocky Mount, NC 27804"),
                new Job("Computer Hardware Engineer"), new Stage("REJECTED")));

        ImportCommand importList = new ImportCommand(personList);

        assertParseSuccess(parser, folderPath + "/overlapping_file_format.json.csv", importList);

        // check for invalid values
        assertParseFailure(parser, folderPath + "/wrong_stage.csv",
            "Line 1: Stage should be only INPROGRESS or ACCEPTED or REJECTED (case-sensitive)");

        // check for missing fields
        assertParseFailure(parser, folderPath + "/too_little_fields.csv",
            "Line 1: length of fields is not correct");

        // check for additional fields
        assertParseFailure(parser, folderPath + "/excess_fields.csv",
            "Line 1: length of fields is not correct");

        // check if excess whitespace is trimmed
        assertParseSuccess(parser, folderPath + "/excess_whitespace.csv", importList);
    }
}
