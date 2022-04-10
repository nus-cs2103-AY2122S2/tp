package woofareyou;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import woofareyou.commons.core.Config;
import woofareyou.commons.core.LogsCenter;
import woofareyou.commons.core.Version;
import woofareyou.commons.exceptions.DataConversionException;
import woofareyou.commons.util.ConfigUtil;
import woofareyou.commons.util.StringUtil;
import woofareyou.logic.Logic;
import woofareyou.logic.LogicManager;
import woofareyou.model.Model;
import woofareyou.model.ModelManager;
import woofareyou.model.PetBook;
import woofareyou.model.ReadOnlyPetBook;
import woofareyou.model.ReadOnlyUserPrefs;
import woofareyou.model.UserPrefs;
import woofareyou.model.util.SampleDataUtil;
import woofareyou.storage.JsonPetBookStorage;
import woofareyou.storage.JsonUserPrefsStorage;
import woofareyou.storage.PetBookStorage;
import woofareyou.storage.Storage;
import woofareyou.storage.StorageManager;
import woofareyou.storage.UserPrefsStorage;
import woofareyou.ui.Ui;
import woofareyou.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing WoofAreYou ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        PetBookStorage petBookStorage = new JsonPetBookStorage(userPrefs.getPetBookFilePath());
        storage = new StorageManager(petBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s pet book and {@code userPrefs}. <br>
     * The data from the sample pet book will be used instead if {@code storage}'s pet book is not found,
     * or an empty pet book will be used instead if errors occur when reading {@code storage}'s pet book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyPetBook> petBookOptional;
        ReadOnlyPetBook initialData;
        try {
            petBookOptional = storage.readPetBook();
            if (!petBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample PetBook");
            }
            initialData = petBookOptional.orElseGet(SampleDataUtil::getSamplePetBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty PetBook");
            initialData = new PetBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty PetBook");
            initialData = new PetBook();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty PetBook");
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
        logger.info("Starting WoofAreYou " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping WoofAreYou ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
