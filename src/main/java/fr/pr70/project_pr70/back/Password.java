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

    /* ----------------- Constructor ----------------- */

    /**
     * Constructeur de la classe Password qui prend un mot de passe en paramètre et génère son hachage.
     * @param password Le mot de passe à hasher
     */
    public Password(String password) {
        this.passwordHash = hashPassword(password);
    }

    /* ----------------- Getters ----------------- */

    /**
     * Renvoie le hachage du mot de passe.
     * @return Le hachage du mot de passe
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Met à jour le hachage du mot de passe.
     * @param passwordHash Le nouveau hachage à définir
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }


    /* ----------------- Methods ----------------- */

    /**
     * Vérifie si le mot de passe est correct
     * @param _password ; mot de passe à vérifier
     * @return true si le mot de passe est correct, false sinon
     */
    public boolean checkPassword(String _password)
    {
        // Check if the hash of the password is the same as the stored hash
        return this.passwordHash.equals(hashPassword(_password));
    }

    /**
     * Hash le mot de passe
     * @param _password ; mot de passe à hasher
     * @return le mot de passe hashé
     */
    private String hashPassword(String _password)
    {
        // Hash le mdp avec PBKDF2
        byte[] salt = new byte[16];
        for (int i = 0; i < salt.length; i++)
        {
            salt[i] = (byte) i;
        }
        KeySpec spec = new PBEKeySpec(_password.toCharArray(), salt, 65536, 128);
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