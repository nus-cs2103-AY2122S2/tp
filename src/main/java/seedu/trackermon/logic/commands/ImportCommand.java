package seedu.trackermon.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.swing.JFrame;

import seedu.trackermon.model.Model;


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

        /**
         * Executes the FileExportThread for FileDialog to appear.
         */
        public void run() {
            System.setProperty("com.apple.macos.use-file-dialog-packages", "true");
            System.setProperty("apple.awt.fileDialogForDirectories", "true");

            JFrame frame = new JFrame();
            FileDialog fd = new FileDialog(frame, "Choose a file to import Trackermon data: ", FileDialog.LOAD);
            fd.setFile("Trackermon.json");
            fd.setVisible(true);
            if (fd.getFile() != null) {
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
        FileImportThread thread = new FileImportThread(model);
        thread.start();
        try {
            thread.join();
            if (thread.getSuccess()) {
                return new CommandResult(MESSAGE_SUCCESS, false, false, true);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            thread.interrupt();
        }
        if (!thread.getCorrectFileType()) {
            return new CommandResult(MESSAGE_FAIL_WRONG_FILETYPE);
        }
        return new CommandResult(MESSAGE_FAIL);
    }
}
