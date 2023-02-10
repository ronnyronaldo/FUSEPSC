package com.baustro.posicionconsolidada.Utils;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.encoders.Hex;
//import org.drools.util.StringUtils;
import org.drools.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class EncryptDecrypt {

    private static final int ITERATIONS = 1000;
    private static final String ALGORITHM = "AES";
    private static final String CIPHER_S = "AES/CBC/PKCS5Padding";
    private static final int KEY_SIZE = 128;
    public static String PHRASE;

    public static String getPHRASE() {
        return PHRASE;
    }

    public static void setGetPHRASE(String PHRASE) {
        EncryptDecrypt.PHRASE = PHRASE;
    }

    /**
     * Desencriptar un mensaje, enviado desde la peticion web.
     *
     * @param message Mensaje representado en bytes
     * @return String desencriptado
     * @throws Exception En caso de no soportar el Cipher PKCS5Padding
     */
    public static String decryptAES(byte[] message) {
        try {

            String salt = new String(Arrays.copyOfRange(message, 0, 32));
            String iv = new String(Arrays.copyOfRange(message, 32, 64));
            String data = new String(Arrays.copyOfRange(message, 64, message.length));
            SecretKey key = generateKey(salt);
            Cipher cipher = Cipher.getInstance(CIPHER_S);
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(hex(iv)));
            return new String(cipher.doFinal(Base64.decodeBase64(data.getBytes())));

        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Encriptar un mensaje usando una frase
     *
     * @param message Frase Secreta
     * @return Mensaje encriptado usando SALT + IV + Message
     * @throws java.lang.Exception En caso de no poder encriptar
     */
    public static String encryptAES(String message) throws Exception {
        String salt = random(16);
        String iv = random(16);
        SecretKey key = generateKey(salt);
        Cipher cipher = Cipher.getInstance(CIPHER_S);
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(hex(iv)));
        byte[] encrypted = cipher.doFinal(message.getBytes("UTF-8"));
        String code = Base64.encodeBase64String(encrypted).replaceAll("\r\n", StringUtils.EMPTY);
        return salt + iv + code;
    }

    /**
     * Genera un texto aleatorio de la longitud solicitada
     *
     * @param length Longitud solicitada
     * @return Texto aleatorio
     */
    private static String random(int length) {
        byte[] salt = new byte[length];
        new SecureRandom().nextBytes(salt);
        return hex(salt);
    }

    /**
     * Codificar un contenido, usando HEX
     *
     * @param bytes bytes a codificar
     * @return HEX codificado
     */
    public static String hex(byte[] bytes) {
        return new String(Hex.encode(bytes));
    }

    /**
     * Decodificar un contenido, usando HEX
     *
     * @param str Cadena a decodificar
     * @return HEX Decodificado
     */
    public static byte[] hex(String str) {
        return Hex.decode(str.getBytes());
    }

    /**
     * Metodo obtiene objeto ScretKey en base al Salt y Phrase
     *
     * @param salt Salt del password
     * @return SecretKey resultante
     * @throws Exception en caso de no soportar el encoding PBKDF2WithHmacSHA1
     */
    private static SecretKey generateKey(String salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(getPHRASE().toCharArray(), hex(salt), ITERATIONS, KEY_SIZE);
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), ALGORITHM);
    }
}
