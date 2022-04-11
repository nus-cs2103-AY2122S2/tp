package seedu.trackermon.commons.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.trackermon.commons.core.Config;
import seedu.trackermon.commons.exceptions.DataConversionException;

/**
 * A class for accessing the Config File.
 */
public class ConfigUtil {

    /**
     * Reads the config file based on the specified config file path.
     * @param configFilePath the file path to retrieve the config information.
     * @return an optional {@code Config} object.
     * @throws DataConversionException if the data is invalid.
     */
    public static Optional<Config> readConfig(Path configFilePath) throws DataConversionException {
        return JsonUtil.readJsonFile(configFilePath, Config.class);
    }

    /**
     * Saves the config file based on the specified {@code Config} object.
     * @param configFilePath the file path to save the config information.
     * @throws IOException if data saving fails.
     */
    public static void saveConfig(Config config, Path configFilePath) throws IOException {
        JsonUtil.saveJsonFile(config, configFilePath);
    }

}
