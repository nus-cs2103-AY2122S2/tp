package seedu.address.authentication;

import java.nio.file.Path;
import java.security.InvalidKeyException;

import seedu.address.encryption.Encryption;

public interface Authentication {
    Encryption login(String password) throws AuthenticationException;

    Encryption signup(String password) throws AuthenticationException;

    boolean isLoggedIn();

    boolean isSignedUp();

    void createEncryptedFile(Path sourceFilePath) throws InvalidKeyException;
}
