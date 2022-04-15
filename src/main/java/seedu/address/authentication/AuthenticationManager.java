package seedu.address.authentication;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.AesUtil;
import seedu.address.commons.util.FileUtil;
import seedu.address.encryption.Encryption;
import seedu.address.encryption.EncryptionManager;
import seedu.address.model.UserPrefs;

public class AuthenticationManager implements Authentication {
    private static final String DEFAULT_CIPHER = "AES/CBC/PKCS5Padding";
    private static final String DEFAULT_SALT = "ed6d63c6b5097d3f8c7c83e4f0f2a038c8c85fa4a16118190d6593ba2bed94e3";
    private static final Logger logger = LogsCenter.getLogger(AuthenticationManager.class);

    private final MainApp app;
    private final UserPrefs userPrefs;
    private boolean isLoggedIn;
    private boolean isSignedUp;

    private Encryption encryption;

    /**
     * Creates an Authentication Manager instance to manage authentication flow.
     *
     * @param app The main application instance.
     * @param userPrefs The user preference as defined by user.
     */
    public AuthenticationManager(MainApp app, UserPrefs userPrefs) {
        this.app = app;
        this.userPrefs = userPrefs;
        this.isLoggedIn = false;
        this.isSignedUp = hasEncryptedFile();
    }

    @Override
    public Encryption login(String password) throws AuthenticationException {
        requireNonNull(password);
        try {
            encryption = new EncryptionManager(AesUtil.getKeyFromPassword(password, DEFAULT_SALT), DEFAULT_CIPHER);

            // Password unable to decrypt the file.
            encryption.decryptFile(userPrefs.getEncryptedFilePath(), userPrefs.getAddressBookFilePath());

            isLoggedIn = true;
            this.app.initApplication(userPrefs);

            return encryption;
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchPaddingException
                | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AuthenticationException("Internal system error");
        } catch (IOException e) {
            throw new AuthenticationException("Wrong password");
        }
    }

    @Override
    public Encryption signup(String password) throws AuthenticationException {
        requireNonNull(password);
        logger.info("Setting up password.");
        try {
            encryption = new EncryptionManager(AesUtil.getKeyFromPassword(password, DEFAULT_SALT), DEFAULT_CIPHER);
            isLoggedIn = true;
            this.app.initApplication(userPrefs);
            return encryption;
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

    @Override
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    @Override
    public boolean isSignedUp() {
        return isSignedUp;
    }

    @Override
    public void createEncryptedFile(Path sourceFilePath) throws InvalidKeyException {
        requireNonNull(encryption);
        try {
            FileUtil.createFile(userPrefs.getEncryptedFilePath());
            encryption.encryptFile(sourceFilePath, userPrefs.getEncryptedFilePath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            throw new InvalidKeyException();
        }
    }

    private boolean hasEncryptedFile() {
        return FileUtil.isFileExists(userPrefs.getEncryptedFilePath());
    }

}
