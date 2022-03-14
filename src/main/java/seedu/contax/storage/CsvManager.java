package seedu.contax.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import seedu.contax.model.IndexedCsvFile;
import seedu.contax.model.Model;


public class CsvManager {
    private Model model;
    private BiFunction<Integer, String[], Boolean> lineAction;

    /**
     * Creates a CsvManager to import to specified {@code model}
     */
    public CsvManager(Model model, BiFunction<Integer, String[], Boolean> lineAction) {
        this.model = model;
        this.lineAction = lineAction;
    }

    /**
     * Imports a given {@code IndexedCSvFile} to a {@code model}
     * @param csvFile File to be imported
     * @return CommandResult upon success
     * @throws IOException if reading from file fails
     */
    // Return list of skipped lines
    public List<Integer> importCsv(IndexedCsvFile csvFile) throws IOException {
        BufferedReader importedCsv = new BufferedReader(new FileReader(csvFile.getFilePath()));
        String line = "";
        ArrayList<Integer> skippedLines = new ArrayList<>();
        int lineCounter = 0;
        //skip first line as it contains headers
        importedCsv.readLine();
        while ((line = importedCsv.readLine()) != null) {
            lineCounter++;
            String[] importedPerson = line.split(",");
            boolean successful = lineAction.apply(lineCounter, importedPerson);
            if (!successful) {
                skippedLines.add(lineCounter);
            }
        }
        importedCsv.close();
        return skippedLines;
    }
}
