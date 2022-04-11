package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.InsurancePackagesSet;

/**
 * Represents a storage for {@link seedu.address.model.InsurancePackagesSet}.
 */
public interface InsurancePackagesStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInsurancePackagesFilePath();

    /**
     * Returns InsurancePackagesStorage data.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<InsurancePackagesSet> readInsurancePackages() throws DataConversionException, IOException;

    /**
     * @see #getInsurancePackagesFilePath()
     */
    Optional<InsurancePackagesSet> readInsurancePackages(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given insurance packages to the storage.
     * @param packagesSet cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInsurancePackages(InsurancePackagesSet packagesSet) throws IOException;

    /**
     * @see #saveInsurancePackages(InsurancePackagesSet)
     */
    void saveInsurancePackages(InsurancePackagesSet packagesSet, Path filePath) throws IOException;
}
