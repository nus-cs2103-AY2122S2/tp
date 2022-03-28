package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.CsvUtil;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.InsurancePackagesSet;
import seedu.address.model.person.InsurancePackage;

/**
 * A class to access Insurance Packages data stored as a CSV file on the hard disk.
 */
public class CsvInsurancePackagesStorage implements InsurancePackagesStorage {

    public static final String MESSAGE_DUPLICATE_PACKAGE = "Package list contains duplicate package(s).";

    private static final Logger logger = LogsCenter.getLogger(CsvInsurancePackagesStorage.class);

    private Path filePath;

    public CsvInsurancePackagesStorage() {
        this.filePath = null;
    }

    public CsvInsurancePackagesStorage(Path filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(Path p) {
        this.filePath = p;
    }

    public Path getInsurancePackagesFilePath() {
        return filePath;
    }

    /**
     * Returns InsurancePackagesStorage data.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    public Optional<InsurancePackagesSet> readInsurancePackages() throws DataConversionException, IOException {
        return readInsurancePackages(filePath);
    }

    /**
     * @see #getInsurancePackagesFilePath()
     */
    @Override
    public Optional<InsurancePackagesSet> readInsurancePackages(Path filePath)
            throws DataConversionException, IOException {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            return Optional.empty();
        }

        // get the people in the addressbook
        List<CsvAdaptedInsurancePackage> packages = CsvUtil.loadIpCsvFile(filePath);
        InsurancePackage convertedPackage;

        InsurancePackagesSet packagesSet = new InsurancePackagesSet();
        for (CsvAdaptedInsurancePackage csvPackage : packages) {
            try {
                convertedPackage = csvPackage.toModelType();
                if (packagesSet.hasPackage(convertedPackage)) {
                    logger.info(MESSAGE_DUPLICATE_PACKAGE);
                    throw new IllegalValueException(MESSAGE_DUPLICATE_PACKAGE);
                }
                packagesSet.addPackage(convertedPackage);
            } catch (IllegalValueException ive) {
                logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
                throw new DataConversionException(ive);
            }
        }
        return Optional.of(packagesSet);
    }

    /**
     * Saves the given insurance packages to the storage.
     * @param packagesSet cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveInsurancePackages(InsurancePackagesSet packagesSet) throws IOException {
        saveInsurancePackages(packagesSet, filePath);
    }

    /**
     * @see #saveInsurancePackages(InsurancePackagesSet)
     */
    @Override
    public void saveInsurancePackages(InsurancePackagesSet packagesSet, Path filePath) throws IOException {
        requireNonNull(packagesSet);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);

        // get the packages in the packagesSet
        List<CsvAdaptedInsurancePackage> packages = new ArrayList<>();
        packages.addAll(packagesSet.getPackagesList().stream().map(CsvAdaptedInsurancePackage::new)
                .collect(Collectors.toList()));

        CsvUtil.saveIpCsvFile(packages, filePath);
    }
}
