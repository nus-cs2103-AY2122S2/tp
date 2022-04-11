package seedu.address.commons.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.storage.CsvAdaptedInsurancePackage;
import seedu.address.storage.CsvAdaptedPerson;

/**
 * A class that handles the conversion of a list of Person to a CSV file, and vice versa.
 */
public class CsvUtil {

    private static final Logger logger = LogsCenter.getLogger(CsvUtil.class);
    private static FileWriter fw;
    private static Scanner s;

    /**
     * The headers to be used for the CSV file for AddressBook.
     */
    private static String abHeaders = "Name,Phone Number,Email,Insurance Package,Address,Tags";

    /**
     * The headers to be used for the CSV file for InsurancePackagesSet.
     */
    private static String ipHeaders = "Package Name,Package Details";

    /**
     * Takes in a List of CsvAdaptedPerson, and the Path of the CSV file to be saved to,
     * and saves this list to a CSV file.
     *
     * @param persons a list of CsvAdaptedPerson to save to CSV to.
     * @param filePath the given Path to the CSV file.
     * @throws IOException if there are errors with file handling.
     */
    public static void saveAbCsvFile(List<CsvAdaptedPerson> persons, Path filePath) throws IOException {
        writeAbHeaders(filePath);
        writePeople(persons, filePath);
    }

    /**
     * Method that handles the writing of headers of the CSV file for AddressBook.
     *
     * @param filePath the given Path to the CSV file.
     * @throws IOException if there are errors with file handling.
     */
    public static void writeAbHeaders(Path filePath) throws IOException {
        fw = new FileWriter(filePath.toString());
        fw.write(abHeaders + "\n");
        fw.close();
    }

    /**
     * Method that handles the writing of the List of CsvAdaptedPerson to CSV.
     *
     * @param persons a list of CsvAdaptedPerson to save to CSV to.
     * @param filePath the given Path to the CSV file.
     * @throws IOException if there are errors with file handling.
     */
    public static void writePeople(List<CsvAdaptedPerson> persons, Path filePath) throws IOException {
        fw = new FileWriter(filePath.toString(), true);
        for (CsvAdaptedPerson p : persons) {
            fw.write(p.toCsvString() + "\n");
        }
        fw.close();
    }

    /**
     * Takes in the Path of an existing CSV file, and reads the CSV file to produce a List of CsvAdaptedPerson.
     * @param filePath the Path to the existing CSV file.
     * @return a List of CsvAdaptedPerson.
     * @throws IOException if there are errors with file handling.
     */
    public static List<CsvAdaptedPerson> loadAbCsvFile(Path filePath) throws DataConversionException {

        ArrayList<CsvAdaptedPerson> persons = new ArrayList<>();
        String personString;
        CsvAdaptedPerson p;

        try {
            s = new Scanner(new File(filePath.toString())); // create a Scanner using the File as the source
            if (!s.hasNext()) {
                logger.warning("Empty CSV File");
                throw new IOException("Empty CSV file");
            }
            s.nextLine(); // headers
            while (s.hasNext()) {
                personString = s.nextLine();
                p = new CsvAdaptedPerson(personString);
                persons.add(p);
            }
            s.close();
            return persons;
        } catch (IOException e) {
            logger.warning("Error reading from CSV file " + filePath + ": " + e);
            throw new DataConversionException(e);
        }
    }

    /**
     * Takes in a List of InsurancePackage, and the Path of the CSV file to be saved to,
     * and saves this list to a CSV file.
     *
     * @param packages a list of InsurancePackage to save to CSV to.
     * @param filePath the given Path to the CSV file.
     * @throws IOException if there are errors with file handling.
     */
    public static void saveIpCsvFile(List<CsvAdaptedInsurancePackage> packages, Path filePath) throws IOException {
        writeIpCsvHeaders(filePath);
        writePackages(packages, filePath);
    }

    /**
     * Method that handles the writing of headers of the CSV file for the insurance packages.
     *
     * @param filePath the given Path to the CSV file.
     * @throws IOException if there are errors with file handling.
     */
    public static void writeIpCsvHeaders(Path filePath) throws IOException {
        fw = new FileWriter(filePath.toString());
        fw.write(ipHeaders + "\n");
        fw.close();
    }

    /**
     * Method that handles the writing of the List of InsurancePackage to CSV.
     *
     * @param packages a list of InsurancePackage to save to CSV to.
     * @param filePath the given Path to the CSV file.
     * @throws IOException if there are errors with file handling.
     */
    public static void writePackages(List<CsvAdaptedInsurancePackage> packages, Path filePath) throws IOException {
        fw = new FileWriter(filePath.toString(), true);
        for (CsvAdaptedInsurancePackage p : packages) {
            fw.write(p.toCsvString() + "\n");
        }
        fw.close();
    }

    /**
     * Takes in the Path of an existing CSV file, and reads the CSV file to produce a List of InsurancePackage.
     * @param filePath the Path to the existing CSV file.
     * @return a List of InsurancePackage.
     * @throws IOException if there are errors with file handling.
     */
    public static List<CsvAdaptedInsurancePackage> loadIpCsvFile(Path filePath) throws DataConversionException {

        ArrayList<CsvAdaptedInsurancePackage> packages = new ArrayList<>();
        String packageString;
        CsvAdaptedInsurancePackage p;

        try {
            s = new Scanner(new File(filePath.toString())); // create a Scanner using the File as the source
            if (!s.hasNext()) {
                logger.warning("Empty CSV File");
                throw new IOException("Empty CSV file");
            }
            s.nextLine(); // headers
            while (s.hasNext()) {
                packageString = s.nextLine();
                try {
                    p = new CsvAdaptedInsurancePackage(packageString);
                    packages.add(p);
                } catch (IllegalValueException ive) { // row does not have 2 values
                    continue; // choose to ignore
                }
            }
            s.close();
            return packages;
        } catch (IOException e) {
            logger.warning("Error reading from CSV file " + filePath + ": " + e);
            throw new DataConversionException(e);
        }
    }

}
