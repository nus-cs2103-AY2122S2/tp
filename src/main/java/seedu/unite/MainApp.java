package seedu.unite;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.unite.commons.core.Config;
import seedu.unite.commons.core.LogsCenter;
import seedu.unite.commons.core.Version;
import seedu.unite.commons.exceptions.DataConversionException;
import seedu.unite.commons.util.ConfigUtil;
import seedu.unite.commons.util.StringUtil;
import seedu.unite.logic.Logic;
import seedu.unite.logic.LogicManager;
import seedu.unite.model.Model;
import seedu.unite.model.ModelManager;
import seedu.unite.model.ReadOnlyUnite;
import seedu.unite.model.ReadOnlyUserPrefs;
import seedu.unite.model.Unite;
import seedu.unite.model.UserPrefs;
import seedu.unite.model.util.SampleDataUtil;
import seedu.unite.storage.JsonUniteStorage;
import seedu.unite.storage.JsonUserPrefsStorage;
import seedu.unite.storage.Storage;
import seedu.unite.storage.StorageManager;
import seedu.unite.storage.UniteStorage;
import seedu.unite.storage.UserPrefsStorage;
import seedu.unite.ui.Ui;
import seedu.unite.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing UNite ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        UniteStorage uniteStorage = new JsonUniteStorage(userPrefs.getUniteFilePath());
        storage = new StorageManager(uniteStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s unite and {@code userPrefs}. <br>
     * The data from the sample unite will be used instead if {@code storage}'s unite is not found,
     * or an empty unite will be used instead if errors occur when reading {@code storage}'s unite.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyUnite> uniteOptional;
        ReadOnlyUnite initialData;
        try {
            uniteOptional = storage.readUnite();
            if (!uniteOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Unite");
            }
            initialData = uniteOptional.orElseGet(SampleDataUtil::getSampleUnite);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Unite");
            initialData = new Unite();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Unite");
            initialData = new Unite();
        }

        return new ModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Unite");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting Unite " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping UNite ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
