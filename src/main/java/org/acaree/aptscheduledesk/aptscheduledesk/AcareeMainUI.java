package org.acaree.aptscheduledesk.aptscheduledesk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.acaree.aptscheduledesk.aptscheduledesk.controller.DashboardController;
import org.acaree.aptscheduledesk.aptscheduledesk.service.AppointmentService;
import org.acaree.aptscheduledesk.aptscheduledesk.service.WebSocketClientService;
import org.slf4j.Logger;

public class AcareeMainUI extends Application {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AcareeMainUI.class);
    private WebSocketClientService webSocketClientService;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        webSocketClientService = new WebSocketClientService();
        log.info("Starting WebSocket client");


        try {

            // Load FXML without setting the controller
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("dashboard.fxml"));
            // Set the controller manually
            AppointmentService appointmentService = new AppointmentService();
            DashboardController controller = new DashboardController(appointmentService);
            loader.setController(controller);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Acaree Dashboard");
            primaryStage.show();

            // After showing the stage, you may need to call a method on the controller to initialize data
            controller.initialize();
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Rethrow the exception to see the stack trace in the console
        }

    }

    @Override
    public void stop() {
        // Clean up resources, disconnect WebSocket, etc.
        if (webSocketClientService != null) {
            System.out.println(webSocketClientService);
            webSocketClientService.disconnect();
        }
    }


}
