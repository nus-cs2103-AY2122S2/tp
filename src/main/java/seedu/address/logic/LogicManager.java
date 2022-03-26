package seedu.address.logic;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Pair;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.Preference;
import seedu.address.model.property.Property;
import seedu.address.model.property.Region;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredAndSortedPersonList();
    }

    /**
     * Iterate through the list of Persons and only add those that are favourited into
     * favouritedList to be returned.
     */
    @Override
    public ObservableList<Person> getFavouritedPersonList() {
        //Resets to full list of Persons to prevent any logical error after `find` command
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        ObservableList<Person> favouritedList = FXCollections.observableArrayList();
        for (Person person : getFilteredPersonList()) {
            if (person.getFavourite().getStatus()) {
                favouritedList.add(person);
            }
        }
        return favouritedList;
    }

    /**
     * Iterate through the list of Persons and return number of persons in
     * the region given
     * @param strRegion is the region where buyers have their preference
     *               for their potential property and sellers have their property in
     */
    @Override
    public int getPersonsBasedOnRegion(String strRegion) {
        int totalPersons = 0;
        //Resets to full list of Persons to prevent any logical error after `find` command
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        //@flairekq
        //Author provided code to help with arrow code issue: https://github.com/nus-cs2103-AY2122S2/forum/issues/233
        if (!Region.isValidRegion(strRegion)) { //defensive code
            return totalPersons;
        }
        Region region = Region.fromString(strRegion);
        for (Person person : getFilteredPersonList()) {
            Preference preference = person.getPreference().isPresent() ? person.getPreference().get() : null;
            if (person.getUserType().isBuyer() && preference != null && preference.getRegion().equals(region)) {
                totalPersons++;
                continue;
            }
            //If usertype is seller
            Set<Property> setOfPropertyValues = person.getProperties();
            for (Property p : setOfPropertyValues) {
                if (p.getRegion().equals(region)) {
                    totalPersons++;
                }
            }
        }
        return totalPersons;
        //@flairekq
    }

    @Override
    public List<Pair<Person>> getMatchList() {
        model.updateMatchList();
        return model.getMatchList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
