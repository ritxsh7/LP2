import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

class AES {
    // Class private variables
    private static final String SECRET_KEY = "my_super_secret_key_ho_ho_ho";

    private static final String SALT = "ssshhhhhhhhhhh!!!!";

    // This method use to encrypt to string
    public static String encrypt(String strToEncrypt) {
        try {

            // Create default byte array
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            // Create SecretKeyFactory object
            SecretKeyFactory factory = SecretKeyFactory.getInstance(
                    "PBKDF2WithHmacSHA256");

            // Create KeySpec object and assign with
            // constructor
            KeySpec spec = new PBEKeySpec(
                    SECRET_KEY.toCharArray(), SALT.getBytes(),
                    65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(
                    tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance(
                    "AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,
                    ivspec);
            // Return encrypted string
            return Base64.getEncoder().encodeToString(
                    cipher.doFinal(strToEncrypt.getBytes(
                            StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: "
                    + e.toString());
        }
        return null;
    }

    // This method use to decrypt to string
    public static String decrypt(String strToDecrypt) {
        try {

            // Default byte array
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0 };
            // Create IvParameterSpec object and assign with
            // constructor
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            // Create SecretKeyFactory Object
            SecretKeyFactory factory = SecretKeyFactory.getInstance(
                    "PBKDF2WithHmacSHA256");

            // Create KeySpec object and assign with
            // constructor
            KeySpec spec = new PBEKeySpec(
                    SECRET_KEY.toCharArray(), SALT.getBytes(),
                    65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(
                    tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance(
                    "AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey,
                    ivspec);
            // Return decrypted string
            return new String(cipher.doFinal(
                    Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: "
                    + e.toString());
        }
        return null;
    }
}

// driver code
public class Main {
    public static void main(String[] args) {
        // Create String variables
        String originalString = "Ritesh Patil";

        // Call encryption method
        String encryptedString = AES.encrypt(originalString);

        // Call decryption method
        String decryptedString = AES.decrypt(encryptedString);

        // Print all strings
        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
    }
}

/*
 * explanation:
 * import java.nio.charset.StandardCharsets;: This line imports the
 * StandardCharsets class from the java.nio.charset package, which provides
 * constants for various character encodings.
 * 
 * import java.security.spec.KeySpec;: This line imports the KeySpec interface
 * from the java.security.spec package, which represents a key specification for
 * a cryptographic algorithm.
 * 
 * import java.util.Base64;: This line imports the Base64 class from the
 * java.util package, which provides methods for Base64 encoding and decoding.
 * 
 * import javax.crypto.Cipher;: This line imports the Cipher class from the
 * javax.crypto package, which provides the functionality for encryption and
 * decryption.
 * 
 * import javax.crypto.SecretKey;: This line imports the SecretKey interface
 * from the javax.crypto package, which represents a secret (symmetric) key for
 * encryption and decryption.
 * 
 * import javax.crypto.SecretKeyFactory;: This line imports the SecretKeyFactory
 * class from the javax.crypto package, which is used to generate secret keys.
 * 
 * import javax.crypto.spec.IvParameterSpec;: This line imports the
 * IvParameterSpec class from the javax.crypto.spec package, which represents an
 * initialization vector (IV) for a symmetric cipher.
 * 
 * import javax.crypto.spec.PBEKeySpec;: This line imports the PBEKeySpec class
 * from the javax.crypto.spec package, which is a key specification for
 * password-based encryption (PBE).
 * 
 * import javax.crypto.spec.SecretKeySpec;: This line imports the SecretKeySpec
 * class from the javax.crypto.spec package, which is a key specification for a
 * secret key.
 * 
 * class AES {: This line declares a class named AES, which will contain the
 * encryption and decryption methods.
 * 
 * private static final String SECRET_KEY = "my_super_secret_key_ho_ho_ho";:
 * This line declares a private static final variable SECRET_KEY and initializes
 * it with the secret key used for encryption and decryption.
 * 
 * private static final String SALT = "ssshhhhhhhhhhh!!!!";: This line declares
 * a private static final variable SALT and initializes it with the salt value
 * used for encryption and decryption.
 * 
 * public static String encrypt(String strToEncrypt): This line declares a
 * public static method named encrypt that takes a string (strToEncrypt) as
 * input and returns an encrypted string.
 * 
 * try {: This line starts a try block to handle any exceptions that may occur
 * during encryption.
 * 
 * byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };: This line
 * creates a byte array iv representing the initialization vector (IV) with all
 * elements set to 0.
 * 
 * IvParameterSpec ivspec = new IvParameterSpec(iv);: This line creates an
 * IvParameterSpec object ivspec using the iv byte array.
 * 
 * SecretKeyFactory factory =
 * SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");: This line creates a
 * SecretKeyFactory object factory using the "PBKDF2WithHmacSHA256" algorithm.
 * 
 * KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(),
 * 65536, 256);: This line creates a KeySpec object spec using the secret key,
 * salt value, iteration count (65536), and key length (256).
 * 
 * SecretKey tmp = factory.generateSecret(spec);: This line generates a secret
 * key tmp using the factory and spec objects.
 * 
 * SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");: This
 * line creates a SecretKeySpec object secretKey using the encoded form of the
 * tmp secret key and the "AES" algorithm.
 * 
 * Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");: This line
 * creates a Cipher object cipher using the "AES/CBC/PKCS5Padding"
 * transformation.
 * 
 * cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);: This line initializes
 * the cipher object for encryption mode using the secretKey and ivspec.
 * 
 * return
 * Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(
 * StandardCharsets.UTF_8)));: This line encrypts the strToEncrypt string by
 * first converting it to bytes using the UTF-8 encoding, then performing the
 * encryption using cipher.doFinal(), and finally encoding the encrypted bytes
 * to a Base64 string using Base64.getEncoder().
 * 
 * public static String decrypt(String strToDecrypt): This line declares a
 * public static method named decrypt that takes an encrypted string
 * (strToDecrypt) as input and returns the decrypted string.
 * 
 * try {: This line starts a try block to handle any exceptions that may occur
 * during decryption.
 * 
 * byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };: This line
 * creates a byte array iv representing the initialization vector (IV) with all
 * elements set to 0.
 * 
 * IvParameterSpec ivspec = new IvParameterSpec(iv);: This line creates an
 * IvParameterSpec object ivspec using the iv byte array.
 * 
 * SecretKeyFactory factory =
 * SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");: This line creates a
 * SecretKeyFactory object factory using the "PBKDF2WithHmacSHA256" algorithm.
 * 
 * KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(),
 * 65536, 256);: This line creates a KeySpec object spec using the secret key,
 * salt value, iteration count (65536), and key length (256).
 * 
 * SecretKey tmp = factory.generateSecret(spec);: This line generates a secret
 * key tmp using the factory and spec objects.
 * 
 * SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");: This
 * line creates a SecretKeySpec object secretKey using the encoded form of the
 * tmp secret key and the "AES" algorithm.
 * 
 * Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");: This line
 * creates a Cipher object cipher using the "AES/CBC/PKCS5PADDING"
 * transformation.
 * 
 * cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);: This line initializes
 * the cipher object for decryption mode using the secretKey and ivspec.
 * 
 * return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));:
 * This line decrypts the strToDecrypt string by first decoding the Base64
 * string to encrypted bytes using Base64.getDecoder(), then performing the
 * decryption using cipher.doFinal(), and finally converting the decrypted bytes
 * to a string using new String().
 * 
 * public static void main(String[] args): This line declares the main method,
 * the entry point of the program.
 * 
 * String originalString = "ALFAAZ2321";: This line declares a string variable
 * originalString and initializes it with the value "ALFAAZ2321".
 * 
 * String encryptedString = AES.encrypt(originalString);: This line calls the
 * encrypt method from the AES class to encrypt the originalString and assigns
 * the encrypted string to the encryptedString variable.
 * 
 * String decryptedString = AES.decrypt(encryptedString);: This line calls the
 * decrypt method from the AES class to decrypt the encryptedString and assigns
 * the decrypted string to the decryptedString variable.
 * 
 * 89-91. These lines print the original string, encrypted string, and decrypted
 * string respectively.
 * 
 * Overall, this code demonstrates how to use the AES (Advanced Encryption
 * Standard) algorithm with a secret key and salt value to encrypt and decrypt a
 * string. The encryption is performed in CBC (Cipher Block Chaining) mode with
 * PKCS5 padding.
 */