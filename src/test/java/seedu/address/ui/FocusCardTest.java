package seedu.address.ui;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FocusCardTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(ExceptionInInitializerError.class, () -> new FocusCard(null));
    }
}
