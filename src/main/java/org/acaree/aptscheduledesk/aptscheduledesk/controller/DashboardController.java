package org.acaree.aptscheduledesk.aptscheduledesk.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import org.acaree.aptscheduledesk.aptscheduledesk.model.Appointment;
import org.acaree.aptscheduledesk.aptscheduledesk.service.AppointmentService;
import org.acaree.aptscheduledesk.aptscheduledesk.ui.CustomListCell;

import java.time.format.DateTimeFormatter;

public class DashboardController {

    @FXML
    private ListView<Appointment> newPatientList;
    @FXML
    private ListView<Appointment> returningPatientList;

    private final AppointmentService appointmentService;

    public DashboardController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    // Initialize your controller
    @FXML
    public void initialize() {
        loadAppointments();
    }

    private void loadAppointments() {
        // Mock data for demonstration
        ObservableList<Appointment> newAppointments = FXCollections.observableArrayList(
                appointmentService.fetchAppointmentsByDoctorId(1L, 0, 10)



        );
        ObservableList<Appointment> returningAppointments = FXCollections.observableArrayList(
        );

        newPatientList.setItems(newAppointments);
//        returningPatientList.setItems(returningAppointments);

        // Define how each appointment should be displayed in the ListView
        newPatientList.setCellFactory(listView -> new CustomListCell());

}


}

