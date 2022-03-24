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
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_SUCCESS = "Exported data file";
    public static final String MESSAGE_FAIL = "Data file export failed";

    public class FileExportThread extends Thread {
        private Model model;
        private volatile boolean isSuccess = false;

        public FileExportThread(Model model) {
            this.model = model;
        }

        /**
         * Executes the FileExportThread for FileDialog to appear.
         */
        public void run() {
            System.setProperty("com.apple.macos.use-file-dialog-packages", "true");
            System.setProperty("apple.awt.fileDialogForDirectories", "true");

            JFrame frame = new JFrame();
            FileDialog fd = new FileDialog(frame, "Choose a location to save Trackermon data: ", FileDialog.SAVE);
            fd.setFile("trackermon.json");
            fd.setVisible(true);

            if (fd.getFile() != null) {
                File f = new File(fd.getFile());
                Path exportPath = Path.of(fd.getDirectory(), f.getName());
                Path dataPath = model.getShowListFilePath();
                System.out.println(exportPath);
                try {
                    Files.copy(dataPath, exportPath, StandardCopyOption.REPLACE_EXISTING);
                    isSuccess = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public boolean getSuccess() {
            return isSuccess;
        }
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        FileExportThread thread = new FileExportThread(model);
        thread.start();
        try {
            thread.join();
            if (thread.getSuccess()) {
                return new CommandResult(MESSAGE_SUCCESS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            thread.interrupt();
        }
        return new CommandResult(MESSAGE_FAIL);
    }
}
