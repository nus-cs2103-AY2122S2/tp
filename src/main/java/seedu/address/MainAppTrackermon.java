package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.LogicManagerTrackermon;
import seedu.address.logic.LogicTrackermon;
import seedu.address.model.ModelManagerTrackermon;
import seedu.address.model.ModelTrackermon;
import seedu.address.model.ReadOnlyShowList;
import seedu.address.model.ReadOnlyUserPrefsTrackermon;
import seedu.address.model.ShowList;
import seedu.address.model.UserPrefsTrackermon;
import seedu.address.model.util.SampleDataUtilTrackermon;
import seedu.address.storage.JsonShowListStorage;
import seedu.address.storage.JsonUserPrefsStorageTrackermon;
import seedu.address.storage.ShowListStorage;
import seedu.address.storage.StorageManagerTrackermon;
import seedu.address.storage.StorageTrackermon;
import seedu.address.storage.UserPrefsStorageTrackermon;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManagerTrackermon;

/**
 * Runs the application.
 */
public class MainAppTrackermon extends Application {

    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainAppTrackermon.class);

    protected Ui ui;
    protected LogicTrackermon logic;
    protected StorageTrackermon storage;
    protected ModelTrackermon model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing ShowList ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorageTrackermon userPrefsStorage = new JsonUserPrefsStorageTrackermon(config.getUserPrefsFilePath());
        UserPrefsTrackermon userPrefs = initPrefs(userPrefsStorage);
        ShowListStorage showListStorage = new JsonShowListStorage(userPrefs.getShowListFilePath());
        storage = new StorageManagerTrackermon(showListStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManagerTrackermon(model, storage);

        ui = new UiManagerTrackermon(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s show list and {@code userPrefs}. <br>
     * The data from the sample show liost will be used instead if {@code storage}'s show list is not found,
     * or an empty show list will be used instead if errors occur when reading {@code storage}'s show list.
     */
    private ModelTrackermon initModelManager(StorageTrackermon storage, ReadOnlyUserPrefsTrackermon userPrefs) {
        Optional<ReadOnlyShowList> showListOptional;
        ReadOnlyShowList initialData;
        try {
            showListOptional = storage.readShowList();
            if (!showListOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ShowList");
            }
            initialData = showListOptional.orElseGet(SampleDataUtilTrackermon::getSampleShowList);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ShowList");
            initialData = new ShowList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ShowList");
            initialData = new ShowList();
        }

        return new ModelManagerTrackermon(initialData, userPrefs);
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
    protected UserPrefsTrackermon initPrefs(UserPrefsStorageTrackermon storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefsTrackermon initializedPrefs;
        try {
            Optional<UserPrefsTrackermon> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefsTrackermon());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefsTrackermon();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ShowList");
            initializedPrefs = new UserPrefsTrackermon();
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
        logger.info("Starting ShowList " + MainAppTrackermon.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
