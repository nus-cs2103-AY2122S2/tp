package seedu.contax.logic.commands;

import java.util.List;
import java.util.Set;

import seedu.contax.model.Model;
import seedu.contax.model.person.Person;
import seedu.contax.model.tag.Tag;
import seedu.contax.storage.CsvManager;

/**
 * Exports current address book manually to data/addressbook.csv
 */
public class ExportCsvCommand extends Command {
    public static final String COMMAND_WORD = "exportcsv";
    public static final String MESSAGE_USAGE = "`" + COMMAND_WORD + "`: **Exports contacts to CSV file**"
            + "Example: `" + COMMAND_WORD + "`";
    public static final String MESSAGE_SUCCESS = "Exported successfully";
    public static final String MESSAGE_FAILURE = "Export failed. Please try again";
    private static final String EXPORT_FILEPATH = "data/addressbook.csv";

    private String filePath;

    public ExportCsvCommand() {
        this.filePath = EXPORT_FILEPATH;
    }

    public ExportCsvCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) {
        CsvManager csvManager = new CsvManager(model, (csvData) -> {
            List<Person> listOfPersons = model.getAddressBook().getPersonList();
            for (Person person : listOfPersons) {
                String[] lineDat = new String[5];
                lineDat[0] = person.getName().toString();
                lineDat[1] = person.getPhone().toString();
                lineDat[2] = person.getEmail().toString();
                if (person.getAddress().toString().contains(",")) {
                    lineDat[3] = "\"" + person.getAddress().toString() + "\"";
                } else {
                    lineDat[3] = person.getAddress().toString();
                }
                Set<Tag> personTags = person.getTags();
                StringBuilder sb = new StringBuilder();
                for (Tag tag : personTags) {
                    sb.append(tag.getTagName() + ";");
                }
                lineDat[4] = sb.toString();
                csvData.add(lineDat);
            }
            return csvData;
        });
        boolean exportCsv = csvManager.exportCsv(filePath);
        if (exportCsv) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
