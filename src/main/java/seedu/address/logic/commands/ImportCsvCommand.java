package seedu.address.logic.commands;

import static java.util.Objects.isNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.opencsv.exceptions.CsvValidationException;

import seedu.address.commons.util.CsvUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Group;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentNumber;
import seedu.address.model.tag.Tag;

public class ImportCsvCommand extends Command {
    public static final String COMMAND_WORD = "import-csv";

    public static final String EXPECTED_HEADERS = "Name,Student Number,Email,Group";

    public static final String FILE_DOES_NOT_EXIST = "TAilor was unable to find a file at the stated path.";

    public static final String NOT_A_CSV_FILE = "This file is not in CSV format. Please convert to .csv before "
            + "importing.";

    public static final String UNEXPECTED_ERROR = "TAilor ran into an unexpected error. Please try again.";

    public static final String WRONG_FORMAT_ERROR = "The file does not follow the required format. Please check for \n"
            + "our file requirements in TAilor's user guide";

    public static final String NOTHING_NEW_TO_IMPORT = "The current roster is in sync with data in the csv file.\n "
            + "Nothing new to import!";

    public static final String MESSAGE_SUCCESS = "Successfully imported data from the csv file!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds people, from a csv file, to the roster. "
            + "Parameters: "
            + "FILE_PATH " + "\n"
            + "Example: " + COMMAND_WORD + " "
            + "data/sample-tutorial-group-data.csv";

    private final Path path;

    public ImportCsvCommand(Path path) {
        this.path = path;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> listToAddToModel = extractUniquePersonList(model);

        if (isNull(listToAddToModel)) {
            return new CommandResult(NOTHING_NEW_TO_IMPORT);
        }

        listToAddToModel.forEach(model::addPerson);
        model.commitContent();

        return new CommandResult(MESSAGE_SUCCESS);
    }

    private List<Person> extractUniquePersonList(Model model) throws CommandException {
        boolean passingChecks = passingChecks();

        if (passingChecks) {
            try {
                List<Name> nameList = obtainNameList();
                List<StudentNumber> studentNumberList = obtainStudentNumberList();
                List<Email> emailList = obtainEmailList();

                List<Mod> modList = new ArrayList<>();
                List<Group> groupList = new ArrayList<>();
                setModAndGroupList(modList, groupList);

                assert nameList.size() == studentNumberList.size();
                assert nameList.size() == emailList.size();
                assert nameList.size() == modList.size();
                assert nameList.size() == groupList.size();

                List<Person> toReturn = IntStream.range(0, emailList.size())
                        .mapToObj(i -> new Person(nameList.get(i), studentNumberList.get(i),
                                emailList.get(i), modList.get(i), groupList.get(i), new HashSet<Tag>()))
                        .collect(Collectors.toList());

                return toReturn.stream()
                        .filter(person -> !model.hasPersonIgnoreTags(person))
                        .collect(Collectors.toList());

            } catch (FileNotFoundException e) {
                throw new CommandException(FILE_DOES_NOT_EXIST);
            } catch (CsvValidationException e) {
                throw new CommandException(NOT_A_CSV_FILE);
            } catch (IOException e) {
                throw new CommandException(UNEXPECTED_ERROR);
            }
        }

        return null;
    }

    private void setModAndGroupList(List<Mod> modList, List<Group> groupList) throws CsvValidationException,
            IOException {
        CsvUtil.extractColumn("Group", this.path).forEach(s -> {
            String[] splitModAndGroup = s.split(" ");
            String group = splitModAndGroup[0].trim();
            groupList.add(new Group(group));
            String mod = splitModAndGroup[1].trim().substring(1).replace(")", "");
            modList.add(new Mod(mod));
        });
    }

    private List<Email> obtainEmailList() throws CsvValidationException, IOException {
        return CsvUtil.extractColumn("Email", this.path).stream()
                .map(Email::new).collect(Collectors.toList());
    }

    private List<StudentNumber> obtainStudentNumberList() throws CsvValidationException, IOException {
        return CsvUtil.extractColumn("Student Number", this.path).stream()
                .map(StudentNumber::new).collect(Collectors.toList());
    }

    private List<Name> obtainNameList() throws CsvValidationException, IOException {
        return CsvUtil.extractColumn("Name", this.path).stream().map(x -> {
            x = x.replaceAll("[^a-zA-Z0-9\\s+]", "");
            return new Name(x);
        }).collect(Collectors.toList());
    }


    private boolean passingChecks() throws CommandException {
        boolean fileConforms;

        try {
            fileConforms = CsvUtil.isTheFileConformingToHeaders(this.path, EXPECTED_HEADERS);
        } catch (FileNotFoundException e) {
            throw new CommandException(FILE_DOES_NOT_EXIST);
        } catch (CsvValidationException e) {
            throw new CommandException(NOT_A_CSV_FILE);
        } catch (IOException e) {
            throw new CommandException(UNEXPECTED_ERROR);
        }

        if (!fileConforms) {
            throw new CommandException(WRONG_FORMAT_ERROR);
        }

        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ImportCsvCommand)) {
            return false;
        }

        return this.path.toString().equals(((ImportCsvCommand) other).path.toString());
    }

}
