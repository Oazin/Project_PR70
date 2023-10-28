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
        if (!comfirmPassword(_password,_confirmPass)) return false; // Le mot de passe de confirmation n'est pas identique au mot de passe rentré en premier lieu

        User newUser = new User(_username, new Password(_password));
        users.add(newUser);
        return true; // L'utilisateur a été créé avec succès
    }


    /*! @brief : Verifie l'égaliter des deux mots de passes rentré par l'utilisateur
     *  @param _password ; mot de passe qui a été entrer
     *  @param _confirmPass ; Mot de passe de confirmation
     *
     *  @behaviour
     *  Renvoie un boolean correspondant au résultat de l'égalité
     */
    public boolean comfirmPassword(String _password, String _confirmPass)
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
}