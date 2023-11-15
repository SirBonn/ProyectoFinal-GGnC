package ggnc.guatechancesapi.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {

    public static String encodePassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            if ("".equals(password)) {
                return "";
            } else {
                return hexString.toString();
            }

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
            return null;
        }
    }
}