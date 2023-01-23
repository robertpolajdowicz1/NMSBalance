module com.nms.nmsbalance {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.nms.nmsbalance to javafx.fxml;
    exports com.nms.nmsbalance;
    exports com.nms.nmsbalance.alien;
    opens com.nms.nmsbalance.alien to javafx.fxml;
}