package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.User;
import fr.pr70.project_pr70.back.UserManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static fr.pr70.project_pr70.MainApplication.*;


public class ProfileController {

    @FXML
    private Label invalidText;

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

    @FXML
    private ImageView profileLogo;

    /*! @brief : Modifie le mot de passe de l'utilisateur
     *
     *  Behaviour : Le mot de passe est modifier uniquement si l'ancien mot de passe est correct
     */
    @FXML
    public void handleEdit(ActionEvent actionEvent)
    {
        User user = getUserManager().getUser(getCurrentUsername());
        if (user.authenticate(getCurrentUsername(), oldPasswordTextField.getText()))
        {
            if (getUserManager().confirmPassword(passwordTextField.getText(), confirmPasswordTextField.getText()))
            {
                user.changePassword(passwordTextField.getText());
                MainApplication.setDashboard();
            }
            else
            {
                invalidText.setText("Password and confirm password need to be identical");
            }

        }
        else
        {
            invalidText.setText("The old password is incorrect");
        }
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


        // Recupere l'utilisateur courant
        User user = MainApplication.getUserManager().getUser(MainApplication.getCurrentUsername());
        if(user == null) return;

        // Ajout de padding à la VBox
        profileBox.setPadding(new Insets(20));
        // Alignement au centre de la VBox
        profileBox.setAlignment(Pos.CENTER);


        // Definir le logo du profile
        profileLogo = new ImageView(new Image(getClass().getResource("/fr/pr70/project_pr70/icon/profile-logo.png").toString()));
        profileLogo.setFitHeight(100);
        profileLogo.setPreserveRatio(true);

        // Creation du label avec le nom du user
        userLabel = new Label(user.getUsername());
        userLabel.setStyle("-fx-font-weight: bold");

        // Creation des champs d'information à entrer par l'utilisateur
        oldPasswordTextField = new TextField();
        oldPasswordTextField.setPromptText("Enter your old password");
        passwordTextField = new TextField();
        passwordTextField.setPromptText("Enter your new password");
        confirmPasswordTextField = new TextField();
        confirmPasswordTextField.setPromptText("Confirm your password");

        // Invalide text
        invalidText = new Label();
        invalidText.setTextFill(Color.RED);

        // Creation des boutons
        editButton = new Button("Edit");
        editButton.setOnAction(this::handleEdit);
        editButton.setId("button");
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(this::handleCancel);
        cancelButton.setId("button");

        // Ajout sur le board
        profileBox.getChildren().addAll(profileLogo, userLabel, oldPasswordTextField, passwordTextField, confirmPasswordTextField, invalidText, editButton, cancelButton);
        profileBox.setSpacing(10);
    }

}
