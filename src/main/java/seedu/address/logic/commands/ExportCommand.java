package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.PERSON;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Exports all data of the all applicants into a CSV file. Accepts absolute path or relative path.\n"
        + "Parameters: filepath to the exported csv file\n"
        + "Example: export ../../myDataFile.csv";
    public static final String MISSING_CSV_FILE = "CSV file could not be created.";

    private final Path csvFilePath;

    /**
     * Constructor for Export Command.
     *
     * @param csvFilePath File path of CSV file to export applicant data to.
     */
    public ExportCommand(Path csvFilePath) {
        requireNonNull(csvFilePath);
        this.csvFilePath = csvFilePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Person> personList = model.getFilteredPersonList(); //to read from json and write to CSV

        try {
            //create new csv file to export data into
            FileUtil.createFile(csvFilePath);
            PrintWriter pw = new PrintWriter(csvFilePath.toString());
            for (Person p : personList) {
                String name = p.getName().toString();
                String phone = p.getPhone().toString();
                String email = p.getEmail().toString();
                String address = p.getAddress().toString();
                String job = p.getJob().toString();
                String stage = p.getStage().toString();
                pw.printf("%s\t%s\t%s\t%s\t%s\t%s\n", name, phone, email, address, job, stage);
            }
            pw.close();
        } catch (IOException e) {
            throw new CommandException(MISSING_CSV_FILE);
        }
        return new CommandResult("Exported " + personList.size() + " applicants' data to "
            + csvFilePath, getType());
    }

    @Override
    public Type getType() {
        return PERSON;
    }
}
