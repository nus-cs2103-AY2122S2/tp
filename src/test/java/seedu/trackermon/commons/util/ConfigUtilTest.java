package seedu.trackermon.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.trackermon.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.trackermon.commons.core.Config;
import seedu.trackermon.commons.exceptions.DataConversionException;

/**
 * Contains integration tests for {@code ConfigUtil}.
 */
public class ConfigUtilTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "ConfigUtilTest");

    @TempDir
    public Path tempDir;

    /**
     * Tests reading of a null config file.
     */
    @Test
    public void read_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> read(null));
    }

    /**
     * Tests a non-existent file boolean method isPresent.
     */
    @Test
    public void read_missingFile_emptyResult() throws DataConversionException {
        assertFalse(read("NonExistentFile.json").isPresent());
    }

    /**
     * Tests whether the file is in a JSON format.
     */
    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> read("NotJsonFormatConfig.json"));
    }

    /**
     * Tests whether file has been successfully read.
     */
    @Test
    public void read_fileInOrder_successfullyRead() throws DataConversionException {

        Config expected = getTypicalConfig();

        Config actual = read("TypicalConfig.json").get();
        assertEquals(expected, actual);
    }

    /**
     * Tests whether default values of config fill is used when reading from a file with missing values.
     */
    @Test
    public void read_valuesMissingFromFile_defaultValuesUsed() throws DataConversionException {
        Config actual = read("EmptyConfig.json").get();
        assertEquals(new Config(), actual);
    }

    /**
     * Tests whether values are ignored when reading from a file with extra values.
     */
    @Test
    public void read_extraValuesInFile_extraValuesIgnored() throws DataConversionException {
        Config expected = getTypicalConfig();
        Config actual = read("ExtraValuesConfig.json").get();

        assertEquals(expected, actual);
    }

    private Config getTypicalConfig() {
        Config config = new Config();
        config.setLogLevel(Level.INFO);
        config.setUserPrefsFilePath(Paths.get("preferences.json"));
        return config;
    }

    private Optional<Config> read(String configFileInTestDataFolder) throws DataConversionException {
        Path configFilePath = addToTestDataPathIfNotNull(configFileInTestDataFolder);
        return ConfigUtil.readConfig(configFilePath);
    }

    /**
     * Tests saving of a null config file.
     */
    @Test
    public void save_nullConfig_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> save(null, "SomeFile.json"));
    }

    /**
     * Tests saving of a null file.
     */
    @Test
    public void save_nullFile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> save(new Config(), null));
    }

    /**
     * Tests whether files has been successfully saved.
     */
    @Test
    public void saveConfig_allInOrder_success() throws DataConversionException, IOException {
        Config original = getTypicalConfig();

        Path configFilePath = tempDir.resolve("TempConfig.json");

        //Try writing when the file doesn't exist
        ConfigUtil.saveConfig(original, configFilePath);
        Config readBack = ConfigUtil.readConfig(configFilePath).get();
        assertEquals(original, readBack);

        //Try saving when the file exists
        original.setLogLevel(Level.FINE);
        ConfigUtil.saveConfig(original, configFilePath);
        readBack = ConfigUtil.readConfig(configFilePath).get();
        assertEquals(original, readBack);
    }

    private void save(Config config, String configFileInTestDataFolder) throws IOException {
        Path configFilePath = addToTestDataPathIfNotNull(configFileInTestDataFolder);
        ConfigUtil.saveConfig(config, configFilePath);
    }

    private Path addToTestDataPathIfNotNull(String configFileInTestDataFolder) {
        return configFileInTestDataFolder != null
                                  ? TEST_DATA_FOLDER.resolve(configFileInTestDataFolder)
                                  : null;
    }
}
