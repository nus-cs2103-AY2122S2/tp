package seedu.trackermon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.trackermon.commons.core.Config;
import seedu.trackermon.commons.core.LogsCenter;
import seedu.trackermon.commons.core.Version;
import seedu.trackermon.commons.exceptions.DataConversionException;
import seedu.trackermon.commons.util.ConfigUtil;
import seedu.trackermon.commons.util.StringUtil;
import seedu.trackermon.logic.Logic;
import seedu.trackermon.logic.LogicManager;
import seedu.trackermon.model.Model;
import seedu.trackermon.model.ModelManager;
import seedu.trackermon.model.ReadOnlyShowList;
import seedu.trackermon.model.ReadOnlyUserPrefs;
import seedu.trackermon.model.ShowList;
import seedu.trackermon.model.UserPrefs;
import seedu.trackermon.model.util.SampleDataUtil;
import seedu.trackermon.storage.JsonShowListStorage;
import seedu.trackermon.storage.JsonUserPrefsStorage;
import seedu.trackermon.storage.ShowListStorage;
import seedu.trackermon.storage.Storage;
import seedu.trackermon.storage.StorageManager;
import seedu.trackermon.storage.UserPrefsStorage;
import seedu.trackermon.ui.Ui;
import seedu.trackermon.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 1, false);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    /**
     * Initialize the application data and information.
     * @throws Exception if there is an error being thrown by the application.
     */
    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Trackermon ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ShowListStorage showListStorage = new JsonShowListStorage(userPrefs.getShowListFilePath());
        storage = new StorageManager(showListStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);

        if (!Files.exists(storage.getShowListFilePath())) {
            storage.saveShowList(model.getShowList());
        }
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s show list and {@code userPrefs}. <br>
     * The data from the sample show list will be used instead if {@code storage}'s show list is not found,
     * or an empty show list will be used instead if errors occur when reading {@code storage}'s show list.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyShowList> showListOptional;
        ReadOnlyShowList initialData;
        try {
            showListOptional = storage.readShowList();
            if (!showListOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ShowList");
            }
            initialData = showListOptional.orElseGet(SampleDataUtil::getSampleShowList);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ShowList");
            initialData = new ShowList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ShowList");
            initialData = new ShowList();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty ShowList");
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

    /**
     * Executes the method during the startup of the application.
     * @param primaryStage the stage provided to display the UI.
     */
    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting ShowList " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    /**
     * Executes the method during the shutting down of the application.
     */
    @Override
    public void stop() {
        logger.info("============================ [ Stopping Trackermon ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
