package org.acaree.aptscheduledesk.aptscheduledesk.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;
import org.acaree.aptscheduledesk.aptscheduledesk.model.Appointment;
import org.slf4j.Logger;

import java.io.IOException;

public class CustomListCell extends ListCell<Appointment> {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CustomListCell.class);
    @FXML
    private ImageView patientImage;
    @FXML
    private Label patientName;
    @FXML
    private Button detailsButton;
    private HBox content;
    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Appointment appointment, boolean empty) {
        super.updateItem(appointment, empty);

        if (empty || appointment == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/custom-list-cell.fxml"));
                mLLoader.setController(this);
                try {
                    content = mLLoader.load();
                } catch (IOException e) {
                   log.error("Error loading custom list cell", e);
                }
            }

            patientName.setText(appointment.getPatient().getPatientDetails().getFirstName()
                    + " " + appointment.getPatient().getPatientDetails().getLastName());
            // Set the patient image using `patientImage.setImage(...)`
            String imageUrl = appointment.getPatient().getPatientDetails().getPictureUrl();
            if (imageUrl != null && !imageUrl.isEmpty()){
                String absolutePath = "http://localhost:8080/" + imageUrl;
                Image image = new Image(absolutePath, true);
                patientImage.setImage(image);
            }

            detailsButton.setUserData(appointment);
            detailsButton.setOnAction(this::showDetails);

            setText(null);
            setGraphic(content);
        }
    }

    @FXML
    private void showDetails(ActionEvent event) {
        Button detailsBtn = (Button) event.getSource();
        Appointment appointment = (Appointment) detailsBtn.getUserData();
        Dialog<Void> detailsDialog = new Dialog<>();
        detailsDialog.setTitle("Appointment Details");

        // Set the button types.
        detailsDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

        // Create the content for the dialog.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Create and configure the ImageView
        ImageView patientImageView = new ImageView();
        patientImageView.setFitHeight(100); // Set the height
        patientImageView.setFitWidth(100); // Set the width
        patientImageView.setPreserveRatio(true);

        // Load the image
        String imageUrl = appointment.getPatient().getPatientDetails().getPictureUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String absolutePath = "http://localhost:8080" + imageUrl;
            Image image = new Image(absolutePath, true); // true for background loading
            image.exceptionProperty().addListener((observable, oldValue, exception) -> {
                if (exception != null) {
                    log.error("Error loading image: ", exception);
                    patientImageView.setImage(new Image("https://blogs.nvidia.com/wp-content/uploads/2023/01/ready-player-me.png"));
                }
            });
            patientImageView.setImage(image);
        } else {
            // Set a default image if the URL is not available
            patientImageView.setImage(new Image("https://assets-global.website-files.com/61554cf069663530fc823d21/6369fecde696f0a08a3805b4_download-53-min.png"));
        }


        // Adding the ImageView to the grid
        grid.add(new Label("Patient:"), 0, 0);
        grid.add(patientImageView, 1, 0);

        // Add patient name next to the image
        grid.add(new Label("Name:"), 2, 0);
        grid.add(new Label(appointment.getPatient().getPatientDetails().getFirstName()
                + " " + appointment.getPatient().getPatientDetails().getLastName()), 3, 0);

        grid.add(new Label("Email:"), 0, 1);
        grid.add(new Label(appointment.getPatient().getPatientDetails().getEmail()), 1, 1);

        grid.add(new Label("Time Slot:"), 0, 2);
        String timeSlot = appointment.getTimeSlot().getStartTime().toString() + " - " +
                appointment.getTimeSlot().getEndTime().toString();
        grid.add(new Label(timeSlot), 1, 2);

        grid.add(new Label("Phone:"), 0, 3);
        grid.add(new Label(appointment.getPatient().getPatientDetails().getPhone()), 1, 3);

        grid.add(new Label("Reason for Appointment:"), 0, 4);
        grid.add(new Label(appointment.getReason()), 1, 4);

        // Set content and show the dialog.
        detailsDialog.getDialogPane().setContent(grid);
        detailsDialog.showAndWait();
    }


}
