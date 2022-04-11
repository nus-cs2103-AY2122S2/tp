package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.commons.util.MailUtil.MAIL_TO;
import static seedu.address.commons.util.MailUtil.isValidUri;
import static seedu.address.commons.util.MailUtil.preprocessEmailAddresses;

import org.junit.jupiter.api.Test;

public class MailUtilTest {
    private final String[] sampleEmails = new String[] { "rgarton@yahoo.ca",
        "bonmots@icloud.com", "jespley@aol.com", "suresh@comcast.net", "martyloo@yahoo.ca" };

    @Test
    public void mailUriTest_noExceptionThrown() {
        String uriSampleOne = preprocessEmailAddresses(sampleEmails);
        String uriSampleTwo = preprocessEmailAddresses(sampleEmails);

        assert uriSampleOne.contains(MAIL_TO);

        assert uriSampleOne.startsWith(MAIL_TO);

        assertEquals(uriSampleOne, uriSampleTwo);
    }

    @Test
    public void uriSyntaxCheck_noExceptionThrown() {
        assertFalse(isValidUri(sampleEmails[0]));

        assertFalse(isValidUri("mail"));

        assertFalse(isValidUri(""));

        assertFalse(isValidUri("@@@@@"));
    }

}
