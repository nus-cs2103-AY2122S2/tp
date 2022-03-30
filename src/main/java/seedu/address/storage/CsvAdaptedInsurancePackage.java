package seedu.address.storage;

import java.util.Objects;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.InsurancePackage;


/**
 * CSV-friendly version of {@link InsurancePackage}.
 */
public class CsvAdaptedInsurancePackage {

    public static final String INVALID_CSV_FORMAT_ERROR = "Invalid insurance package in CSV file!";
    public static final String MISSING_NAME_MESSAGE_FORMAT = "Insurance package name is missing!";

    private final String insurancePackageName;
    private String insurancePackageDetails;

    /**
     * Constructs a {@code CsvAdaptedInsurancePackage} with the given package details.
     */
    public CsvAdaptedInsurancePackage(String insurancePackageName, String insurancePackageDetails) {
        this.insurancePackageName = insurancePackageName;
        this.insurancePackageDetails = insurancePackageDetails;
    }

    /**
     * Converts a given {@code InsurancePackage} into this class for CSV use.
     * @param source The InsurancePackage object to convert from.
     */
    public CsvAdaptedInsurancePackage(InsurancePackage source) {
        insurancePackageName = source.getPackageName();
        insurancePackageDetails = source.getPackageDescription();
    }

    /**
     * A constructor that takes in a comma-separated string to a CsvAdaptedInsurancePackage.
     * @param s The String representation of this package.
     */
    public CsvAdaptedInsurancePackage(String s) throws IllegalValueException {

        String[] packageDetails = s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        if (packageDetails.length != 2) {
            throw new IllegalValueException(INVALID_CSV_FORMAT_ERROR);
        }
        insurancePackageName = cleanup(packageDetails[0]);
        insurancePackageDetails = cleanup(packageDetails[1]);
    }

    /**
     * Converts this CSV-friendly adapted insurance package object into the model's {@code InsurancePackage} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted package.
     */
    public InsurancePackage toModelType() throws IllegalValueException {

        if (insurancePackageName == null || insurancePackageName.equals("")) {
            throw new IllegalValueException(String.format(MISSING_NAME_MESSAGE_FORMAT,
                    InsurancePackage.class.getSimpleName()));
        }

        if (insurancePackageDetails == null) {
            insurancePackageDetails = "";
        }

        return new InsurancePackage(insurancePackageName, insurancePackageDetails);
    }


    /**
     * Converts this CsvAdaptedInsurancePackage to a comma-separated string.
     * To be used for storing a CsvAdaptedInsurancePackage into a CSV file.
     *
     * @return The String representation of this InsurancePackage.
     */
    public String toCsvString() {
        return addQuotes(insurancePackageName) + "," + addQuotes(insurancePackageDetails);
    }

    /**
     * Adds quotes around a field, for the purposes of storing it into CSV.
     * This is necessary in case the existing field value has commas inside.
     *
     * @param s the String to add double quotes around.
     * @return the CSV-friendly version of the string.
     */
    public static String addQuotes(String s) {
        return '"' + s + '"';
    }

    /**
     * Removes quotes around the field read from the CSV file, if exists.
     *
     * @param s the raw String read from the CSV file.
     * @return the actual value of the field.
     */
    public static String cleanup(String s) {
        if (s.length() > 0 // if the string has characters
                && s.charAt(0) == '"' // if the string starts with "
                && s.charAt(s.length() - 1) == '"') { // if the string ends with "
            return s.substring(1, s.length() - 1);
        } else {
            return s;
        }
    }

    /**
     * Returns true if both CsvAdaptedInsurancePackage have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CsvAdaptedInsurancePackage)) {
            return false;
        }

        CsvAdaptedInsurancePackage otherPackage = (CsvAdaptedInsurancePackage) other;
        return otherPackage.insurancePackageName.equals(insurancePackageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(insurancePackageName);
    }
}
