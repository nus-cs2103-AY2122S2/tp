package woofareyou.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import woofareyou.commons.core.GuiSettings;
import woofareyou.commons.core.LogsCenter;
import woofareyou.logic.commands.Command;
import woofareyou.logic.commands.CommandResult;
import woofareyou.logic.commands.exceptions.CommandException;
import woofareyou.logic.parser.PetBookParser;
import woofareyou.logic.parser.exceptions.ParseException;
import woofareyou.model.Model;
import woofareyou.model.ReadOnlyPetBook;
import woofareyou.model.pet.Pet;
import woofareyou.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final PetBookParser petBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        petBookParser = new PetBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = petBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.savePetBook(model.getPetBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyPetBook getPetBook() {
        return model.getPetBook();
    }

    @Override
    public ObservableList<Pet> getFilteredPetList() {
        return model.getFilteredPetList();
    }

    @Override
    public Path getPetBookFilePath() {
        return model.getPetBookFilePath();
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
