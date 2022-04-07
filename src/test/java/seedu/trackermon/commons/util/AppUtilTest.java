package seedu.trackermon.commons.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.trackermon.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests for {@code AppUtil}.
 */
public class AppUtilTest {

    /**
     * Tests getting image of a valid image file path.
     */
    @Test
    public void getImage_exitingImage() {
        assertNotNull(AppUtil.getImage("/images/trackermon_icon.png"));
    }

    /**
     * Tests getting image of a null image file path.
     */
    @Test
    public void getImage_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AppUtil.getImage(null));
    }

    /**
     * Tests the checking of a true condition.
     */
    @Test
    public void checkArgument_true_nothingHappens() {
        AppUtil.checkArgument(true);
        AppUtil.checkArgument(true, "");
    }

    /**
     * Tests the checking of a false condition without
     * an error message where a IllegalArgumentException will be thrown.
     */
    @Test
    public void checkArgument_falseWithoutErrorMessage_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> AppUtil.checkArgument(false));
    }

    /**
     * Tests the checking of a false condition with
     * an error message where a IllegalArgumentException will be thrown.
     */
    @Test
    public void checkArgument_falseWithErrorMessage_throwsIllegalArgumentException() {
        String errorMessage = "error message";
        assertThrows(IllegalArgumentException.class,
                errorMessage, () -> AppUtil.checkArgument(false, errorMessage));
    }
}
