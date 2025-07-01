module io.github.mikhkuts.gamelauncher {
    requires javafx.controls;
    requires javafx.graphics;
    requires java.desktop;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    
    exports io.github.mikhkuts.gamelauncher.main;
    exports io.github.mikhkuts.gamelauncher.frameElement;
    opens io.github.mikhkuts.gamelauncher.forGL to com.fasterxml.jackson.databind;
}