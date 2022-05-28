module hurst.swimtimerecordsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens hurst.swimtimerecordsystem to javafx.fxml;
    exports hurst.swimtimerecordsystem;
}