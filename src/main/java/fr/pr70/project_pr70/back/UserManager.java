package fr.pr70.project_pr70.back;

import java.util.ArrayList;
import java.util.List;

public class UserManager
{
    private List<User> users;
    
    /* ----------------- Constructor ----------------- */
    public UserManager()
    {
        users = new ArrayList<>();
    }
    
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

    /*! @brief : Verifie si la liste d'utilisateur est vide
     *
     *  @behaviour
     *  Renvoie un boolean correspondant au résultat de la vérification
     */
    public boolean isEmpty()
    {
        return users.isEmpty();
    }
    
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
        if (!user.getPassword().equals(_password)) return false; // Le mot de passe est incorrect
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

    public boolean confirmPassword(String _password, String _confirmPass)
    {
        return _password.equals(_confirmPass);
    }


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

    public void resetConnectedUser()
    {
        for (User user : users)
        {
            if (user.isConnected())
            {
                user.setConnected(false);
            }
        }
    }

    public User getConnectedUser()
    {
        for (User user : users)
        {
            if (user.isConnected())
            {
                return user;
            }
        }
        return null; // Aucun utilisateur trouvé avec ce nom d'utilisateur
    }
}