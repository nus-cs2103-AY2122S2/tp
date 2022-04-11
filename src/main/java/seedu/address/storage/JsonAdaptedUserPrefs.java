package seedu.address.storage;

import java.io.File;
import java.nio.file.Paths;
import java.util.regex.Matcher;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.UserPrefs;

/**
 * Jackson-friendly version of {@link UserPrefs}.
 */
public class JsonAdaptedUserPrefs {
    private final GuiSettings guiSettings;
    private final String filePathString;

    /**
     * Constructs a {@code JsonAdaptedUserPrefs} with the given user pref details.
     */
    @JsonCreator
    public JsonAdaptedUserPrefs(@JsonProperty("guiSettings") GuiSettings guiSettings,
                                @JsonProperty("linkyTimeFilePath") String filePathString) {
        this.guiSettings = guiSettings;
        this.filePathString = filePathString;
    }

    /**
     * Converts a given {@code UserPrefs} into this class for Jackson use.
     */
    public JsonAdaptedUserPrefs(UserPrefs source) {
        this.guiSettings = source.getGuiSettings();
        this.filePathString = source.getLinkyTimeFilePath().toString();
    }

    /**
     * Converts this Jackson-friendly adapted user pref object into the model's {@code UserPrefs} object.
     *
     * @throws DataConversionException if there were any data constraints violated in the adapted userPrefs.
     */
    public UserPrefs toModelType() throws DataConversionException {
        if (guiSettings == null && filePathString == null) {
            return new UserPrefs();
        }

        try {
            final UserPrefs userPrefs = new UserPrefs();
            userPrefs.setGuiSettings(guiSettings);
            final String reliableFilePath = filePathString.replaceAll("[/\\\\]",
                    Matcher.quoteReplacement(File.separator));
            userPrefs.setLinkyTimeFilePath(Paths.get(reliableFilePath));
            return userPrefs;
        } catch (Exception e) {
            throw new DataConversionException(e);
        }
    }
}
