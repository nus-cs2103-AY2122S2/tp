package seedu.contax.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import seedu.contax.model.IndexedCsvFile;
import seedu.contax.model.Model;
import seedu.contax.model.person.Person;
import seedu.contax.model.tag.Tag;


public class CsvManager {
    private static final String CSV_HEADER = "Name,Phone,Email,Address,Tagged";

    private Model model;
    private BiFunction<Integer, String[], Boolean> lineAction;

    public CsvManager(Model model) {
        this.model = model;
    }

    /**
     * Creates a CsvManager to import to specified {@code model}
     */
    public CsvManager(Model model, BiFunction<Integer, String[], Boolean> lineAction) {
        this.model = model;
        this.lineAction = lineAction;
    }

    /**
     * Imports a given {@code IndexedCsvFile} to a {@code model}
     *
     * @param csvFile File to be imported
     * @return CommandResult upon success
     * @throws IOException if reading from file fails
     */
    // Return list of skipped lines
    public List<Integer> importCsv(IndexedCsvFile csvFile) throws IOException {
        BufferedReader importedCsvReader = new BufferedReader(new FileReader(csvFile.getFilePath()));
        String line = "";
        ArrayList<Integer> skippedLines = new ArrayList<>();
        int lineCounter = 0;
        //skip first line as it contains headers
        importedCsvReader.readLine();
        while ((line = importedCsvReader.readLine()) != null) {
            lineCounter++;
            String[] importedPerson = line.split(",(?=([^\"]|\"[^\"]*\")*$)");
            boolean successful = lineAction.apply(lineCounter, importedPerson);
            if (!successful) {
                skippedLines.add(lineCounter);
            }
        }
        importedCsvReader.close();
        return skippedLines;
    }

    /**
     * Exports the current address book into a pre-defined CSV file
     *
     * @return True if operation is successful, false otherwise
     */
    public boolean exportCsv(String filePath) {
        try {
            File exportedCsvFile = new File(filePath);
            exportedCsvFile.createNewFile();
            List<Person> listOfPersons = model.getFilteredPersonList();
            BufferedWriter exportedCsvWriter = new BufferedWriter(new FileWriter(exportedCsvFile));
            exportedCsvWriter.write(CSV_HEADER);
            exportedCsvWriter.newLine();
            for (Person person : listOfPersons) {
                exportedCsvWriter.write(person.getName().toString() + ",");
                exportedCsvWriter.write(person.getPhone().toString() + ",");
                exportedCsvWriter.write(person.getEmail().toString() + ",");
                if (person.getAddress().toString().contains(",")) {
                    exportedCsvWriter.write("\"" + person.getAddress().toString() + "\",");
                } else {
                    exportedCsvWriter.write(person.getAddress().toString() + ",");
                }
                Set<Tag> personTags = person.getTags();
                for (Tag tag : personTags) {
                    exportedCsvWriter.write(tag.getTagName() + ";");
                }
                exportedCsvWriter.newLine();
            }
            exportedCsvWriter.flush();
            exportedCsvWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
