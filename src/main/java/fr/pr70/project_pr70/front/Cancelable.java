package fr.pr70.project_pr70.front;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public interface Cancelable {

    /**
     *  Action lier au bouton Cancel sur l'affichage graphique
     *  @param actionEvent ; Action lier au bouton Cancel sur l'affichage graphique
     */
    @FXML
    public void handleCancel(ActionEvent actionEvent);
}
