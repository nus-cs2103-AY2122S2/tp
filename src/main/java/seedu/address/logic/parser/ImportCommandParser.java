package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Job;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Stage;


public class ImportCommandParser implements Parser<ImportCommand> {

    public static final String FILE_DOES_NOT_EXIST = "The file does not exists or it is not an actual file.\n"
            + "If using relative path, your current working directory is "
            + System.getProperty("user.dir");

    public static final String INVALID_FILE_PATH = "That is not a valid file path\n"
            + "Please check for any illegal characters.";

    public static final String PARSE_ERROR = "File cannot be parsed due to IO error.";
    public static final String WRONG_FILE_TYPE = "No csv file extension found";
    private static final int NUM_OF_FIELDS = 6;

    @Override
    public ImportCommand parse(String args) throws ParseException {
        String filepath = args.strip();
        checkFilePath(filepath);

        try {
            return new ImportCommand(readCsv(Paths.get(filepath)));
        } catch (IOException e) {
            throw new ParseException(PARSE_ERROR);
        }
    }

    private List<Person> readCsv(Path path) throws IOException, ParseException {
        requireNonNull(path);
        List<Person> persons = new ArrayList<>();
        List<String> entries = FileUtil.readFromFileLines(path);

        for (int i = 0; i < entries.size(); i++) {
            String[] allFields = entries.get(i).strip().split("\t");
            ArrayList<String> strippedFields = getFields(allFields, i + 1);
            Person newPerson = createPerson(strippedFields, i + 1);
            updatePersons(persons, newPerson, i + 1);
        }

        return persons;
    }

    private void checkFilePath(String path) throws ParseException {
        if (path.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        } else if (!FileUtil.isValidPath(path)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, INVALID_FILE_PATH));
        } else if (!FileUtil.isFileExists(Paths.get(path))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FILE_DOES_NOT_EXIST));
        } else if (!(path.endsWith(".csv"))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, WRONG_FILE_TYPE));
        }
    }

    private ArrayList<String> getFields(String[] allFields, int lineNo) throws ParseException {
        ArrayList<String> strippedFields = new ArrayList<>();

        for (String f : allFields) {
            String field = f.strip();

            if (!field.equals("")) {
                strippedFields.add(field);
            }
        }

        if (strippedFields.size() != NUM_OF_FIELDS) {
            throw new ParseException("Line " + lineNo + ": length of fields is not correct");
        }

        return strippedFields;
    }

    private Person createPerson(ArrayList<String> fields, int lineNo) throws ParseException {
        assert fields.size() == NUM_OF_FIELDS : "Number of fields are incorrect";

        try {
            Name name = ParserUtil.parseName(fields.get(0).strip());
            Phone phone = ParserUtil.parsePhone(fields.get(1).strip());
            Email email = ParserUtil.parseEmail(fields.get(2).strip());
            Address address = ParserUtil.parseAddress(fields.get(3).strip());
            Job job = ParserUtil.parseJob(fields.get(4).strip());
            Stage stage = ParserUtil.parseStage(fields.get(5).strip());
            return new Person(name, phone, email, address, job, stage);
        } catch (ParseException e) {
            throw new ParseException("Line " + lineNo + ": " + e.getMessage());
        }
    }

    private void updatePersons(List<Person> persons, Person newPerson, int lineNo) throws ParseException {
        requireNonNull(newPerson);
        boolean isUniquePerson = persons.stream().noneMatch(p -> p.isSamePerson(newPerson));

        if (isUniquePerson) {
            persons.add(newPerson);
        } else {
            throw new ParseException("Line " + lineNo + " applicant is duplicated in csv file.");
        }
    }
}
