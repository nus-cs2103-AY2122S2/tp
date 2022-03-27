package seedu.ibook.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.stage.Stage;
import seedu.ibook.logic.Logic;
import seedu.ibook.logic.LogicManager;
import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.storage.JsonIBookStorage;
import seedu.ibook.storage.JsonUserPrefsStorage;
import seedu.ibook.storage.StorageManager;

@ExtendWith(ApplicationExtension.class)
public class GuiTest {

    @TempDir
    public Path temporaryFolder;

    private MainWindow mainWindow;

    @Start
    public void start(Stage stage) {
        init(stage);
    }

    @Test
    public void isShowing_noExceptionThrown() {
        assertTrue(mainWindow.getRoot().isShowing());
    }

    public void init(Stage stage) {
        JsonIBookStorage iBookStorage =
                new JsonIBookStorage(temporaryFolder.resolve("ibook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(iBookStorage, userPrefsStorage);

        Model model = new ModelManager();
        Logic logic = new LogicManager(model, storage);
        mainWindow = new MainWindow(stage, logic);
        mainWindow.fillInnerParts();
        mainWindow.show();
    }

}
