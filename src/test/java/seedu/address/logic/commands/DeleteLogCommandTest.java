package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests (interaction with Model) and unit tests for
 * {@code DeleteLogCommand}.
 */
public class DeleteLogCommandTest {

    // ===== UNIT TESTS =====
    @Test
    public void equals() {

    }

    // ===== INTEGRATION TESTS =====

    @Test
    public void execute_validPersonValidLog_success() {

    }

    @Test
    public void execute_validPersonAll_success() {

        // ===== PERSON HAS SOME LOGS =====


        // ===== PERSON HAS NO LOGS ======

    }

    @Test
    public void execute_validAll_success() {

        // ===== SOME PEOPLE HAVE LOGS, SOME DON'T =====


        // ===== NO PERSONS IN ADDRESS BOOK =====

    }

    @Test void execute_invalidPerson_throwsCommandException() {

        // ===== EMPTY ADDRESS BOOK =====

        // ===== NON-EMPTY ADDRESS BOOK BUT PERSON NOT IN LIST =====

    }

    @Test
    public void execute_invalidLog_throwsCommandException() {
        // ===== PERSON HAS LOGS BUT OUT OF BOUNDS =====


        // ===== PERSON HAS NO LOGS =====

    }

}
