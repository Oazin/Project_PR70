package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public interface Cancelable {
    @FXML
    public void handleCancel(ActionEvent actionEvent);
}
