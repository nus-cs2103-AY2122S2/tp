package seedu.address.model.meeting;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import seedu.address.model.meeting.exceptions.UnsupportedDesktopException;

public class UrlOpenerManager implements UrlOpener {
    private static final String MESSAGES_INVALID_URL = "Invalid URL provided";
    private Desktop desktop;
    private URI uri;

    /**
     * Creates a {@code UrlOpenerManager} and checks if the device supports
     * the {@code java.awt.Desktop} class and the {@code java.awt.Desktop::browse} method.
     *
     * @throws UnsupportedDesktopException thrown when the {@code Desktop} class or
     *                                     {@code Desktop::browse} method is unsupported
     */
    public UrlOpenerManager() throws UnsupportedDesktopException {
        checkAndSetDesktop();
    }

    @Override
    public void open(URL url) throws SecurityException, IOException, URISyntaxException {
        checkAndSetUrl(url);
        desktop.browse(uri);
    }

    private void checkAndSetDesktop() throws UnsupportedDesktopException {
        if (!Desktop.isDesktopSupported()) {
            throw new UnsupportedDesktopException();
        }
        desktop = Desktop.getDesktop();
        if (!desktop.isSupported(Desktop.Action.BROWSE)) {
            throw new UnsupportedDesktopException();
        }
    }

    private void checkAndSetUrl(URL url) throws URISyntaxException {
        uri = url.toURI();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UrlOpenerManager // instanceof handles nulls
                && uri.equals(((UrlOpenerManager) other).uri)); // state check
    }
}
