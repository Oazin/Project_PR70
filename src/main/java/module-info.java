module fr.pr70.project_pr70 {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;


    exports fr.pr70.project_pr70;
    exports fr.pr70.project_pr70.front;
    exports fr.pr70.project_pr70.back;
    opens fr.pr70.project_pr70 to javafx.fxml;
    opens fr.pr70.project_pr70.front to javafx.fxml;
}