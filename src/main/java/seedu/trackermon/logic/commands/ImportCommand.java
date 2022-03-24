package seedu.trackermon.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.trackermon.model.Model;

import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.swing.JFrame;


/**
 * Lists all shows in Trackermon to the user.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_SUCCESS = "Imported data file";
    public static final String MESSAGE_FAIL = "Data file import failed";
    public static final String MESSAGE_FAIL_WRONG_FILETYPE = "Incorrect file type selected. "
            + "Only JSON(.json) files can be imported into Trackermon!";

    public class FileImportThread extends Thread {
        private Model model;
        private boolean isSuccess = false;
        private boolean isCorrectFileType = true;

        public FileImportThread(Model model) {
            this.model = model;
        }

        public void run() {
            System.setProperty("com.apple.macos.use-file-dialog-packages", "true");
            System.setProperty("apple.awt.fileDialogForDirectories", "true");

            JFrame F = new JFrame();
            FileDialog fd = new FileDialog(F, "Choose a file to import Trackermon data: ", FileDialog.LOAD);
            fd.setFile("Trackermon.json");
            fd.setVisible(true);
            File f = new File(fd.getFile());
            if (!f.getName().toLowerCase().endsWith(".json")) {
                isCorrectFileType = false;
                return;
            }

            Path importPath = Path.of(fd.getDirectory(), f.getName());
            Path dataPath = model.getShowListFilePath();
            try {
                Files.copy(importPath, dataPath, StandardCopyOption.REPLACE_EXISTING);
                isSuccess = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public boolean getSuccess() {
            return isSuccess;
        }

        public boolean getCorrectFileType() {
            return isCorrectFileType;
        }
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        FileImportThread test = new FileImportThread(model);
        test.start();
        try {
            test.join();
            if (test.getSuccess()) {
                return new CommandResult(MESSAGE_SUCCESS, false, false, true);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            test.interrupt();
        }
//        if (test.getSuccess()) {
//            return new CommandResult(MESSAGE_SUCCESS, false, false, true);
//        }
//        if (!test.getCorrectFileType()) {
//            return new CommandResult(MESSAGE_FAIL_WRONG_FILETYPE);
//        }
        return new CommandResult(MESSAGE_FAIL);
    }
}
