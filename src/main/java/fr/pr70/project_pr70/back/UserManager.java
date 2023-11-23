package fr.pr70.project_pr70.back;

import java.util.ArrayList;

public class UserManager
{
    private final ArrayList<User> users;


    /* ----------------- Constructor ----------------- */

    /**
     * Constructeur par défaut initialisant une liste d'utilisateurs vide.
     */
    public UserManager() {
        users = new ArrayList<>();
    }

    /* ----------------- Getters ----------------- */

    /**
     * Renvoie la liste des utilisateurs actuellement gérés par le gestionnaire d'utilisateurs.
     * @return La liste des utilisateurs
     */
    public ArrayList<User> getUsers() {
        return users;
    }


    /* ----------------- Methods ----------------- */
    /**
     * Ajoute l'utilisateur
     * @param user ; Utilisateur à ajouter
     *
     * @behaviour
     * Ajoute l'utilisateur à la liste
     */
    public void addUser(User user)
    {
        if(user == null) return;
        users.add(user);
    }

    /**
     * Supprime l'utilisateur
     * @param user ; Utilisateur à supprimer
     */
    public void removeUser(User user)
    {
        if(user == null) return;
        users.remove(user);
    }
    
    /**
     * Retourne l'utilisateur
     * @param _username ; Nom d'utilisateur qui a été entrer
     * @return l'utilisateur correspondant
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
     * Verifie si la liste d'utilisateur est vide
     * @return un boolean
     */
    public boolean isEmpty()
    {
        return users.isEmpty();
    }

    /**
     * Connecte l'utilisateur
     * @param _username ; Nom d'utilisateur qui a été entrer
     * @param _password ; mot de passe qui a été entrer
     * @return un boolean correspondant au résultat de la connexion
     *
     * @behaviour
     * Il faut verifier que l'utilisateur existe et que le mot de passe est correct
     */
    public boolean connectUser(String _username, String _password)
    {
        User user = getUser(_username);
        if (user == null) return false; // L'utilisateur n'existe pas
        if (!user.getPassword().checkPassword(_password)) return false; // Le mot de passe est incorrect
        user.setConnected(true);
        return true; // L'utilisateur a été connecté avec succès
    }

    /**
     * Verifie l'égaliter des deux mots de passes rentré par l'utilisateur
     * @param _password ; mot de passe qui a été entrer
     * @param _confirmPass ; Mot de passe de confirmation
     * @return un boolean correspondant au résultat de l'égalité
     */

    public boolean confirmPassword(String _password, String _confirmPass)
    {
        return _password.equals(_confirmPass);
    }


    /**
     * Verifie l'existance de l'utilisateur
     * @param _username ; Nom d'utilisateur qui a été entrer
     * @return  Renvoie true si l'utilisateur existe déjà et false dans le cas contraire
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
     * Retourne les utilisateurs autorisés en lecture/écriture par l'utilisateur
    *  @param _user ; Nom de l'utilisateur dont on veut les utilisateurs autorisés
    *  @return ArrayList<User> ; Liste des utilisateurs autorisés
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