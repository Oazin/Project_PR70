package fr.pr70.project_pr70.back;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class Password
{
    private String passwordHash;

    public Password(String password)
    {
        this.passwordHash = hashPassword(password);
    }

    public void setPassword(String password)
    {
        this.passwordHash = hashPassword(password);
    }

    public boolean checkPassword(String password)
    {
        // Check if the hash of the password is the same as the stored hash
        return this.passwordHash.equals(hashPassword(password));
    }

    private String hashPassword(String password)
    {
        // Hash the password with PBKDF2
        byte[] salt = new byte[16];
        for (int i = 0; i < salt.length; i++)
        {
            salt[i] = (byte) i;
        }
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            BigInteger bigInt = new BigInteger(1, hash);
            return bigInt.toString();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return "";
    }

}