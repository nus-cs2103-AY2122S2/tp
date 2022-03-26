package seedu.address.logic.commands;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import seedu.address.model.meeting.UrlOpener;

/**
 * A {@code UrlOpener} stub with {@code UrlOpener::open} that throws {@code URISyntaxException}.
 */
public class UrlOpenerStubThrowsUriSyntaxException implements UrlOpener {
    @Override
    public void open(URL url) throws IOException, URISyntaxException {
        throw new URISyntaxException("stub", "due to stubbing");
    }
}
