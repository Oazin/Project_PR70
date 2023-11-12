package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class ProfileController {

    @FXML
    protected VBox profileBox;
    @FXML
    private Label userLabel;

    @FXML
    private TextField oldPasswordTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField confirmPasswordTextField;

    @FXML
    private Button editButton;

    @FXML
    private  Button cancelButton;

    /*! @brief : Modifie le mot de passe de l'utilisateur
     *
     *  Behaviour : Le mot de passe est modifier uniquement si l'ancien mot de passe est correct
     */
    @FXML
    public void handleEdit(ActionEvent actionEvent)
    {

    }

    /*! @brief : Action lier au bouton cancel sur l'affichage graffique
     *
     *  Behaviour : Permet a l'utilisateur de retourner sur le dashboard
     *  lorsqu'il decide de ne pas modifier son mot de passe
     */
    @FXML
    public void handleCancel(ActionEvent actionEvent)
    {
        MainApplication.setDashboard();
    }


    /*! @brief : Update les informations presente sur la page à chaque chargement
     */
    @FXML
    public void updateProfile() {
        // clear profile
        profileBox.getChildren().clear();

        // get Currrent User
        User user = MainApplication.getUserManager().getUser(MainApplication.getCurrentUsername());
        if(user == null) return;

        // update board
        VBox board = new VBox();
        board.setId("board");

        // Ajout de padding à la VBox
        board.setPadding(new Insets(20));
        // Alignement au centre de la VBox
        board.setAlignment(Pos.CENTER);

        profileBox.getChildren().add(board);

        // Creation du label avec le nom du user
        userLabel = new Label(user.getUsername());

        // Creation des champs d'information à entrer par l'utilisateur
        oldPasswordTextField = new TextField();
        passwordTextField = new TextField();
        confirmPasswordTextField = new TextField();

        // Creation des boutons
        editButton = new Button("Edit");
        editButton.setOnAction(this::handleEdit);
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(this::handleCancel);

        // Ajout sur le board
        board.getChildren().addAll(userLabel, oldPasswordTextField, passwordTextField, confirmPasswordTextField, editButton, cancelButton);

    }

}
