module fr.pr70.project_pr70 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens fr.pr70.project_pr70 to javafx.fxml;
    exports fr.pr70.project_pr70;
    exports fr.pr70.project_pr70.front;
    opens fr.pr70.project_pr70.front to javafx.fxml;
}