package seedu.address.commons.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility class to generate encryption key based on AES algorithm
 */
public class AesUtil {
    public static SecretKey getKeyFromPassword(String secret, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secretKey = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
        return secretKey;
    }
}
