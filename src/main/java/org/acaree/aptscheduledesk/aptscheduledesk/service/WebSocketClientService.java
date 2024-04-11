package org.acaree.aptscheduledesk.aptscheduledesk.service;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.acaree.aptscheduledesk.aptscheduledesk.model.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class WebSocketClientService {
    private static final Logger log = LoggerFactory.getLogger(WebSocketClientService.class);

    private final WebSocketStompClient stompClient;
    private StompSession stompSession;

    public WebSocketClientService() {
        stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandlerAdapter sessionHandler = new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                stompSession = session;
                session.subscribe("/topic/newAppointment", this);
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                log.info("Received message: {}", payload);
                try {
                    String jsonPayload = (String) payload;
                    Appointment appointment = new ObjectMapper().readValue(jsonPayload, Appointment.class);
                    Platform.runLater(() -> showAppointmentPopup(appointment));
                } catch (JsonProcessingException e) {
                    log.error("Error parsing JSON payload", e);
                    e.printStackTrace();
                }
            }
        };

        String url = "ws://api.theacaree.com/ws";
        stompClient.connect(url, sessionHandler); // Use the defined sessionHandler

    }

    private void showAppointmentPopup(Appointment appointment) {
        // Use Platform.runLater to ensure UI updates are done on the JavaFX Application Thread
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("New Appointment");
            alert.setHeaderText("A new appointment has been booked");

            // Customize the content with appointment details
            String content = String.format("Patient: %s %s\nEmail: %s\nTime Slot: %s to %s\nPhone: %s\nReason: %s",
                    appointment.getPatient().getPatientDetails().getFirstName(),
                    appointment.getPatient().getPatientDetails().getLastName(),
                    appointment.getPatient().getPatientDetails().getEmail(),
                    appointment.getTimeSlot().getStartTime().toString(),
                    appointment.getTimeSlot().getEndTime().toString(),
                    appointment.getPatient().getPatientDetails().getPhone(),
                    appointment.getReason());

            alert.setContentText(content);

            alert.showAndWait();
        });
    }


    // Other methods, such as disconnect, send messages, etc.
    public void disconnect() {
        if (stompSession != null && stompSession.isConnected()) {
            stompSession.disconnect();
        }
        if (stompClient != null) {
            stompClient.stop();
        }
    }
}
