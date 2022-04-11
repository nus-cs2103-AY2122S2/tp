package seedu.address.commons.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CsvUtil {

    /**
     * Checks if the csv file has all the specified headers.
     * @param path Path for the file in consideration
     * @param expectedCsvHeaders A String with all the desired column headers appended
     * @return boolean if the file satisfies header criteria or not
     * @throws IOException if an unexpected input/output error with the file occurs
     * @throws CsvValidationException if file does not conform to csv formatting standards
     */
    public static boolean isTheFileConformingToHeaders(Path path, String expectedCsvHeaders) throws IOException,
            CsvValidationException, FileNotFoundException {
        CSVReader csvReader = new CSVReader(new FileReader(String.valueOf(path)));
        csvReader.skip(2);
        String[] allColumnNames = csvReader.readNext();

        Set<String> setOfColumnNames = new HashSet<>(Arrays.asList(allColumnNames));

        String[] expectedColumnNames = expectedCsvHeaders.split(",");

        for (String h : expectedColumnNames) {
            if (!setOfColumnNames.contains(h)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Extracts all the data under the given column header at the given file path.
     * @param header String, the column header.
     * @param path the path to the file in consideration.
     * @return a list of Strings with all the data from the column
     * @throws IOException if there are unexpected input/out errors with file
     * @throws CsvValidationException if file does not conform to csv formatting standards
     */
    public static List<String> extractColumn(String header, Path path) throws IOException, CsvValidationException {
        CSVReader csvReader = new CSVReader(new FileReader(String.valueOf(path)));
        csvReader.skip(2);
        String[] allColumnNames = csvReader.readNext();
        int positionOfHeader = Arrays.asList(allColumnNames).indexOf(header);

        List<String> toReturn = new ArrayList<>();

        String[] line;
        while ((line = csvReader.readNext()) != null) {
            toReturn.add(line[positionOfHeader].trim());
        }

        return toReturn;
    }
}
