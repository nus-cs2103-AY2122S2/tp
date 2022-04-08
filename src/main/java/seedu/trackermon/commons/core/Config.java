package seedu.trackermon.commons.core;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;

/**
 * Config values used by the app
 */
public class Config {

    public static final Path DEFAULT_CONFIG_FILE = JarTools.runningFromJarFile()
                    ? Paths.get(JarTools.getClassLocationString(Config.class), "config.json")
                    : Paths.get("config.json");

    // Config values customizable through config file
    private Level logLevel = Level.INFO;
    private Path userPrefsFilePath = JarTools.runningFromJarFile()
            ? Paths.get(JarTools.getClassLocationString(Config.class), "preferences.json")
            : Paths.get("preferences.json");

    public Level getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }

    public Path getUserPrefsFilePath() {
        return userPrefsFilePath;
    }

    public void setUserPrefsFilePath(Path userPrefsFilePath) {
        this.userPrefsFilePath = userPrefsFilePath;
    }

    /**
     * Returns whether the other object specified is equals this object.
     * @param other object to be checked against this object.
     * @return true if the other object is equal to this object.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Config)) { //this handles null as well.
            return false;
        }

        Config o = (Config) other;

        return Objects.equals(logLevel, o.logLevel)
                && Objects.equals(userPrefsFilePath, o.userPrefsFilePath);
    }

    /**
     * Returns the hash code of the {@code Config} object.
     * @return a hash code of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(logLevel, userPrefsFilePath);
    }

    /**
     * Returns a string representation of the {@code Config} object.
     * @return the string of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current log level : " + logLevel);
        sb.append("\nPreference file Location : " + userPrefsFilePath);
        return sb.toString();
    }

}
