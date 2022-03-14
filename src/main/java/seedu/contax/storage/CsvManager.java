package seedu.contax.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

import seedu.contax.model.IndexedCsvFile;
import seedu.contax.model.Model;

/**
 * Manager to handle import/export csv logic
 */
public class CsvManager {
    private static final String CSV_HEADER = "Name,Phone,Email,Address,Tagged";

    private Model model;
    private BiFunction<Integer, String[], Boolean> lineAction;
    private UnaryOperator<List<String[]>> csvLines;

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
     * Creates a CsvManager to import to specified {@code model}
     */
    public CsvManager(Model model, UnaryOperator<List<String[]>> csvLines) {
        this.model = model;
        this.csvLines = csvLines;
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
            BufferedWriter exportedCsvWriter = new BufferedWriter(new FileWriter(exportedCsvFile));
            exportedCsvWriter.write(CSV_HEADER);
            exportedCsvWriter.newLine();

            List<String[]> csvData = new ArrayList<>();
            csvData = csvLines.apply(csvData);
            for (String[] line : csvData) {
                exportedCsvWriter.write(String.join(",", line));
                exportedCsvWriter.newLine();
            }
            exportedCsvWriter.flush();
            exportedCsvWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
