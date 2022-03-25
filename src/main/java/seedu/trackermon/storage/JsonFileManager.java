package seedu.trackermon.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;s

public class JsonFileManager {

    public static final int SUCCESS = 0;
    public static final int CANCEL = 1;
    public static final int ERROR = 2;

    private JFileChooser jfc;

    /**
     * Creates a {@code FileManager} that handles import and export without baseFileName.
     */
    public JsonFileManager() {
        this("");
    }

    /**
     * Creates a {@code FileManager} that handles import and export.
     * @param baseFileName default file name in JFileChooser
     */
    public JsonFileManager(String baseFileName) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        jfc = new JFileChooser();

        if (!baseFileName.isBlank()) {
            baseFileName = (baseFileName.endsWith(".json")) ? baseFileName : baseFileName + ".json";
            jfc.setSelectedFile(new File(baseFileName));
        }

        jfc.setCurrentDirectory(new File(System.getProperty("user.home")));
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".json");
                }
            }

            @Override
            public String getDescription() {
                return "JSON file (*.json)";
            }
        });
        jfc.setAcceptAllFileFilterUsed(true);
    }

    /**
     * Takes in a dataPath and overwrites the selected file with filedata from dataPath.
     *
     * @param dataPath
     * @return int value representing outcome.
     */
    public int exportFile(Path dataPath) {
        jfc.setMultiSelectionEnabled(false);
        int result = jfc.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File f = new File(jfc.getSelectedFile().getAbsolutePath());
            if (!f.getAbsolutePath().endsWith(".json")) {
                f = new File(jfc.getSelectedFile().getAbsolutePath() + ".json");
            }
            System.out.println(f);
            Path exportPath = f.toPath();
            try {
                Files.copy(dataPath, exportPath, StandardCopyOption.REPLACE_EXISTING);
                return SUCCESS;
            } catch (IOException e) {
                e.printStackTrace();
                return ERROR;
            }
        }
        return CANCEL;
    }

    /**
     * Takes in a dataPath and overwrites the file at dataPath with selected file.
     *
     * @param dataPath
     * @return int value representing outcome.
     */
    public int importFile(Path dataPath) {
        jfc.setMultiSelectionEnabled(false);
        int result = jfc.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File f = new File(jfc.getSelectedFile().getAbsolutePath());
            Path importPath = f.toPath();
            try {
                Files.copy(importPath, dataPath, StandardCopyOption.REPLACE_EXISTING);
                return SUCCESS;
            } catch (IOException e) {
                e.printStackTrace();
                return ERROR;
            }
        }
        return CANCEL;
    }
}
