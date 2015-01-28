package com.example.jeedemo.util;

        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;
        import java.text.MessageFormat;

/**
 * Use this class to change the password stored in the databae from one that is
 * visible as plain text (a security threat) to one that is "hashed". Hashing is
 * a one-way encryption system. Hashes can be generated but cannot be reverse
 * engineered, which is why they are called one-way hashes. Use this class to
 * generate a hashed password, based on the original plain text version, using
 * SHA-256, which is a superior hashing algorithm. Then copy the output from the
 * console and use it to replace what you have in your database.
 *
 * @author jlombardo
 */
public class HashedPasswordGenerator {

    public static void generateHash(String password) {
        // This is one way of generating a SHA-256 hash. I uses classes/methods
        // from the Google Guava project. See the Maven pom.xml file which
        // I've modified to include the Guava libraries. See the imports
        // above which show what is being used.
        String hash = null;
    //    password = "ala";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(password.getBytes());

            byte[] bytes = md.digest();
            StringBuilder temp =new StringBuilder();
            for (int i = 0; i<bytes.length; i++) {
                temp.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            hash = temp.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String output = MessageFormat.format("{0} hashed to: {1}", password, hash);

        System.out.println(output);
    }

//    public static void main(String[] args) {
//        // you can generate as many as you need ... modify to suite...
//        generateHash("password1");
//        generateHash("password2");
//        generateHash("password3");
//    }
}