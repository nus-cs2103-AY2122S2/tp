package seedu.address.logic.commands;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import seedu.address.model.meeting.UrlOpener;

/**
 * A default {@code UrlOpener} stub that does not throw any exceptions.
 */
public class UrlOpenerStub implements UrlOpener {
    @Override
    public void open(URL url) throws IOException, URISyntaxException {
    }
}
