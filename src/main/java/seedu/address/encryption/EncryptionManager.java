package seedu.address.encryption;

import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.FileUtil;

/**
 * The main encryption manager to encrypt and decrypt MedBook files.
 *
 * Adapted from https://www.baeldung.com/java-cipher-input-output-stream
 */
public class EncryptionManager implements Encryption {
    private static final Logger logger = LogsCenter.getLogger(EncryptionManager.class);

    private final SecretKey secretKey;
    private final Cipher cipher;

    /**
     * Creates an encryption manager service to handle all encryption related tasks.
     *
     * @param secretKey The secret key generated based on the password supplied.
     * @param cipher The encryption algorithm.
     * @throws NoSuchPaddingException If the supplied secret key has invalid padding.
     * @throws NoSuchAlgorithmException If the supplied encryption algorithm is invalid.
     */
    public EncryptionManager(SecretKey secretKey, String cipher) throws NoSuchPaddingException,
            NoSuchAlgorithmException {
        requireNonNull(secretKey);
        requireNonNull(cipher);
        this.secretKey = secretKey;
        this.cipher = Cipher.getInstance(cipher);
    }

    @Override
    public void encryptFile(Path sourceFile, Path targetFile) throws InvalidKeyException, IOException {
        requireNonNull(sourceFile);
        requireNonNull(targetFile);

        logger.fine("Encrypting content to: " + targetFile);
        String content = FileUtil.readFromFile(sourceFile);
        encrypt(content, targetFile);
    }

    /**
     * Encrypts the string content based on the encryption algorithm defined in this manager.
     *
     * @param content The string content to be encrypted.
     * @param targetFile The file path to save the encryption file in .enc file format.
     * @throws InvalidKeyException If the supplied secret key is invalid.
     * @throws IOException If saving the file causes error.
     */
    public void encrypt(String content, Path targetFile) throws InvalidKeyException, IOException {
        requireNonNull(content);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] initializationVector = cipher.getIV();

        try (
                FileOutputStream fileOut = new FileOutputStream(targetFile.toFile());
                CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)
        ) {
            fileOut.write(initializationVector);
            cipherOut.write(content.getBytes());
        }
    }

    @Override
    public void decryptFile(Path encryptedFile, Path targetFile)
            throws InvalidAlgorithmParameterException, IOException, InvalidKeyException {
        requireNonNull(encryptedFile);
        requireNonNull(targetFile);
        logger.fine("Decrypting content from: " + encryptedFile);
        try (FileInputStream fileIn = new FileInputStream(encryptedFile.toFile())) {
            byte[] fileInitializationVector = new byte[16];
            fileIn.read(fileInitializationVector);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(fileInitializationVector));

            String decryptedContent = decrypt(fileIn);

            // Skip writing decrypted content if there exists a data file
            if (FileUtil.isFileExists(targetFile)) {
                logger.info("Reading content from data file");
                return;
            }

            logger.info("Reading content from encrypted file");
            FileUtil.writeToFile(targetFile, decryptedContent);

            logger.fine("Contents decrypted");
        }
    }

    /**
     * Decrypts the given input file stream to string content.
     *
     * @param fileIn The input stream of the given file.
     * @return The string content of the decrypted file.
     * @throws IOException If file reading causes error.
     */
    public String decrypt(FileInputStream fileIn) throws
            IOException {
        String content;

        try (
                CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
                InputStreamReader inputReader = new InputStreamReader(cipherIn);
                BufferedReader reader = new BufferedReader(inputReader)
        ) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            content = sb.toString();
        }

        return content;
    }
}
