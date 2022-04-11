package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.net.MalformedURLException;
import java.net.URL;

import seedu.address.model.meeting.exceptions.InvalidUrlException;

/**
 * Represents a Meeting's URL in the meeting list.
 * Guarantees: immutable; is valid as declared in {@link #isValidUrl(String)}
 */
public class MeetingUrl {

    public static final String MESSAGE_CONSTRAINTS =
            "URLs should be a valid link, and it should not be blank";

    public static final String INVALID_URL_STRING = "%s is not a valid URL";

    /* Regex to check if a string starts with `http[s]://`. */
    public static final String HTTP_REGEX = "^(https?)://.*$";

    /* Adapted from Diego Perini https://gist.github.com/dperini/729294 */
    public static final String VALIDATION_REGEX = "^(?:(?:(?:https?):)?\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)"
            + "(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])"
            + "(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}"
            + "(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z0-9\\u00a1-\\uffff][a-z0-9\\u00a1-\\uffff_-]"
            + "{0,62})?[a-z0-9\\u00a1-\\uffff]\\.)+(?:[a-z\\u00a1-\\uffff]{2,}\\.?))(?::\\d{2,5})?(?:[/?#]\\S*)?$";

    public final URL meetingUrl;

    /**
     * Constructs a {@code MeetingUrl}.
     *
     * @param url A valid URL string.
     */
    public MeetingUrl(String url) {
        requireNonNull(url);
        checkArgument(isValidUrl(url), MESSAGE_CONSTRAINTS);
        this.meetingUrl = parseUrl(url);
    }

    /**
     * Checks if a {@code String} is a valid URL.
     *
     * @param test The {@code String} to test.
     * @return True if the URL {@code String} is valid.
     */
    public static boolean isValidUrl(String test) {
        test = checkAndPrependHttps(test);
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Converts a URL string into a URL object.
     *
     * @param url The URL string to convert.
     * @return The URL object.
     */
    public static URL parseUrl(String url) {
        try {
            final String prependedUrl = checkAndPrependHttps(url);
            return new URL(prependedUrl);
        } catch (MalformedURLException e) {
            throw new InvalidUrlException(String.format(INVALID_URL_STRING, url));
        }
    }

    /**
     * Prepends {@code https://} to {@code url} if it does not start with it.
     *
     * @param url The string to check.
     * @return The url string with {@code https://} prepended to it.
     */
    public static String checkAndPrependHttps(String url) {
        if (!url.matches(HTTP_REGEX)) {
            url = "https://" + url;
        }
        return url;
    }

    @Override
    public String toString() {
        return meetingUrl.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingUrl // instanceof handles nulls
                && meetingUrl.equals(((MeetingUrl) other).meetingUrl)); // state check
    }

    @Override
    public int hashCode() {
        return meetingUrl.hashCode();
    }
}
