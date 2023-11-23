package fr.pr70.project_pr70.back;

import java.util.ArrayList;

/**
 * The type User manager.
 */
public class UserManager
{
    private final ArrayList<User> users;


    /**
     * Instantiates a new User manager.
     */
    /* ----------------- Constructor ----------------- */
    public UserManager()
    {
        users = new ArrayList<>();
    }


    /**
     * Gets users.
     *
     * @return the users
     */
    /* ----------------- Getters ----------------- */
    public ArrayList<User> getUsers()
    {
        return users;
    }


    /**
     * Add user.
     *
     * @param user the user
     */
    /* ----------------- Methods ----------------- */
    /*! @brief : Ajoute l'utilisateur
     *  @param user ; Utilisateur à ajouter
     *
     *  @behaviour
     *  Ajoute l'utilisateur à la liste
     */
    public void addUser(User user)
    {
        if(user == null) return;
        users.add(user);
    }

    /**
     * Remove user.
     *
     * @param user the user
     */
    /*! @brief : Supprime l'utilisateur
     *  @param user ; Utilisateur à supprimer
     *
     *  @behaviour
     *  Supprime l'utilisateur de la liste
     */
    public void removeUser(User user)
    {
        if(user == null) return;
        users.remove(user);
    }

    /**
     * Gets user.
     *
     * @param _username the username
     * @return the user
     */
    /*! @brief : Retourne l'utilisateur
     *  @param _username ; Nom d'utilisateur qui a été entrer
     *
     *  @behaviour
     *  Renvoie l'utilisateur correspondant au nom d'utilisateur entré
     */
    public User getUser(String _username)
    {
        for (User user : users)
        {
            if (user.getUsername().equals(_username))
            {
                return user;
            }
        }
        return null; // Aucun utilisateur trouvé avec ce nom d'utilisateur
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    /*! @brief : Verifie si la liste d'utilisateur est vide
     *
     *  @behaviour
     *  Renvoie un boolean correspondant au résultat de la vérification
     */
    public boolean isEmpty()
    {
        return users.isEmpty();
    }

    /**
     * Create user boolean.
     *
     * @param _username    the username
     * @param _password    the password
     * @param _confirmPass the confirm pass
     * @return the boolean
     */
    /*! @brief : Cree le nouveau user
     *  @param _username ; Nom d'utilisateur qui a été entrer
     *  @param _password ; mot de passe qui a été entrer
     *  @param _confirmPass ; Mot de passe de confirmation
     *
     *  @behaviour
     *  Creer un nouvel utilistateur et retroune un boolean si celui a été créé
     *  Il faut verifier que l'utilisateur n'existe pas deja et que le mot de passe de confrimation est identique au mot de passe voulu par l'utilisateur
     */
    public boolean createUser(String _username, String _password, String _confirmPass)
    {
        if (alreadyExist(_username)) return false; // L'utilisateur existe déjà, la création a échoué
        if (!confirmPassword(_password,_confirmPass)) return false; // Le mot de passe de confirmation n'est pas identique au mot de passe rentré en premier lieu

        User newUser = new User(_username, _password);
        users.add(newUser);
        return true; // L'utilisateur a été créé avec succès
    }

    /**
     * Connect user boolean.
     *
     * @param _username the username
     * @param _password the password
     * @return the boolean
     */
    /*! @brief : Connecte l'utilisateur
     *  @param _username ; Nom d'utilisateur qui a été entrer
     *  @param _password ; mot de passe qui a été entrer
     *
     *  @behaviour
     *  Renvoie un boolean correspondant au résultat de la connexion
     *  Il faut verifier que l'utilisateur existe et que le mot de passe est correct
     */
    public boolean connectUser(String _username, String _password)
    {
        User user = getUser(_username);
        if (user == null) return false; // L'utilisateur n'existe pas
        if (!user.getPassword().checkPassword(_password)) return false; // Le mot de passe est incorrect
        user.setConnected(true);
        return true; // L'utilisateur a été connecté avec succès
    }

    /*! @brief : Verifie l'égaliter des deux mots de passes rentré par l'utilisateur
     *  @param _password ; mot de passe qui a été entrer
     *  @param _confirmPass ; Mot de passe de confirmation
     *
     *  @behaviour
     *  Renvoie un boolean correspondant au résultat de l'égalité
     */

    /**
     * Confirm password boolean.
     *
     * @param _password    the password
     * @param _confirmPass the confirm pass
     * @return the boolean
     */
    public boolean confirmPassword(String _password, String _confirmPass)
    {
        return _password.equals(_confirmPass);
    }


    /**
     * Already exist boolean.
     *
     * @param _username the username
     * @return the boolean
     */
    /*! @brief : Verifie l'existance de l'utilisateur
     *  @param _username ; Nom d'utilisateur qui a été entrer
     *
     *  @behaviour
     *  Renvoie true si l'utilisateur existe déjà et false dans le cas contraire
     */
    public boolean alreadyExist(String _username)
    {
        for (User user : users)
        {
            if (user.getUsername().equals(_username))
            {
                return true;
            }
        }
        return false; // Aucun utilisateur trouvé avec ce nom d'utilisateur
    }

    /**
     * Gets allowed users.
     *
     * @param _user the user
     * @return the allowed users
     */
    /*! @brief : Retourne les utilisateurs autorisés en lecture/écriture par l'utilisateur
    *   @param userName ; Nom de l'utilisateur dont on veut les utilisateurs autorisés
    *   @return ArrayList<User> ; Liste des utilisateurs autorisés
    */
    public ArrayList<User> getAllowedUsers(User _user)
    {
        if(_user.isAdmin())
            return getUsers();
        ArrayList<User> users = new ArrayList<>();
        users.add(_user);
        return users;
    }
}