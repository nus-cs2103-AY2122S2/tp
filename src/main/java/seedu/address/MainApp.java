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
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentBook;
import seedu.address.model.InsuranceBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyInsuranceBook;
import seedu.address.model.ReadOnlyRecordBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.RecordBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.AppointmentBookStorage;
import seedu.address.storage.InsuranceBookStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonAppointmentBookStorage;
import seedu.address.storage.JsonInsuranceBookStorage;
import seedu.address.storage.JsonRecordBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.RecordBookStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

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
        logger.info("=============================[ Initializing AddressBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(this.getParameters());
        this.config = this.initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(this.config.getUserPrefsFilePath());
        UserPrefs userPrefs = this.initPrefs(userPrefsStorage);
        AddressBookStorage addressBookStorage = new JsonAddressBookStorage(userPrefs.getAddressBookFilePath());

        AppointmentBookStorage appointmentBookStorage =
                new JsonAppointmentBookStorage(userPrefs.getAppointmentBookFilePath());

        InsuranceBookStorage insuranceBookStorage = new JsonInsuranceBookStorage(userPrefs.getInsuranceBookFilePath());

        RecordBookStorage recordBookStorage =
                new JsonRecordBookStorage(userPrefs.getRecordBookFilePath());

        this.storage = new StorageManager(addressBookStorage, insuranceBookStorage,
                appointmentBookStorage, recordBookStorage, userPrefsStorage);

        this.initLogging(this.config);

        this.model = this.initModelManager(this.storage, userPrefs);

        this.logic = new LogicManager(this.model, this.storage);

        this.ui = new UiManager(this.logic);

        logic.setUi((UiManager) this.ui);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook initialAddressBookData;

        try {
            addressBookOptional = storage.readAddressBook();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AddressBook");
            }
            initialAddressBookData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
            initialAddressBookData = new AddressBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initialAddressBookData = new AddressBook();
        }

        Optional<ReadOnlyInsuranceBook> insuranceBookOptional;
        ReadOnlyInsuranceBook initialInsuranceBookData;
        try {
            insuranceBookOptional = storage.readInsuranceBook();
            if (!insuranceBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample InsuranceBook");
            }
            initialInsuranceBookData = insuranceBookOptional.orElseGet(SampleDataUtil::getSampleInsuranceBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty InsuranceBook");
            initialInsuranceBookData = new InsuranceBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty InsuranceBook");
            initialInsuranceBookData = new InsuranceBook();
        }

        Optional<ReadOnlyAppointmentBook> appointmentBookOptional;
        ReadOnlyAppointmentBook initialAppointmentData;
        try {
            appointmentBookOptional = storage.readAppointmentBook();
            if (!appointmentBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AppointmentBook");
            }
            initialAppointmentData = appointmentBookOptional.orElseGet(SampleDataUtil::getSampleAppointmentBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AppointmentBook");
            initialAppointmentData = new AppointmentBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AppointmentBook");
            initialAppointmentData = new AppointmentBook();
        }

        Optional<ReadOnlyRecordBook> recordBookOptional;
        ReadOnlyRecordBook initialRecordData;
        try {
            recordBookOptional = storage.readRecordBook();
            if (!recordBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample RecordBook");
            }
            initialRecordData = recordBookOptional.orElseGet(SampleDataUtil::getSampleRecordBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty RecordBook");
            initialRecordData = new RecordBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty RecordBook");
            initialRecordData = new RecordBook();
        }

        System.out.println(initialAppointmentData.toString());
        System.out.println(initialAddressBookData.toString());
        System.out.println(initialInsuranceBookData.toString());
        System.out.println(initialRecordData.toString());

        return new ModelManager(initialAddressBookData, initialInsuranceBookData,
                initialAppointmentData, initialRecordData, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
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
        logger.info("Starting AddressBook " + MainApp.VERSION);
        this.ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            this.storage.saveUserPrefs(this.model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
