package org.acaree.aptscheduledesk.aptscheduledesk.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.acaree.aptscheduledesk.aptscheduledesk.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import org.apache.http.client.utils.URIBuilder;
import java.util.List;

public class AppointmentService {
    private static final Logger log = LoggerFactory.getLogger(AppointmentService.class);
    private final String baseURL = "https://api.theacaree.com/api/v1/appointment/";


    public static void main(String[] args) {
        AppointmentService appointmentService = new AppointmentService();
        List<Appointment> appointments = appointmentService.fetchAppointmentsByDoctorId(1L, 0, 10);
        for (Appointment appointment : appointments) {
            log.info("Appointment: {}", appointment);
            log.info("Time Slot: {}", appointment.getTimeSlot());
            log.info("Patient: {}", appointment.getPatient().toString());
            log.info("Doctor: {}", appointment.getDoctor().toString());
        }

    }

    public ObservableList<Appointment> fetchAppointmentsByDoctorId(Long doctorId, int page, int size) {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        try {

            String url = baseURL + "doctor/" + doctorId + "?page=" + page + "&size=" + size;
            HttpResponse<JsonNode> response = Unirest.get(url).asJson();


            if (response.getStatus() == 200) {
                JSONObject body = response.getBody().getObject();
                log.info("Appointments: {}", body.get("content"));
                JSONArray appointmentsArray = body.getJSONArray("content");
                for (int i = 0; i < appointmentsArray.length(); i++) {
                    JSONObject obj = appointmentsArray.getJSONObject(i);
                    Appointment appointment = new Appointment();
                    appointment.setId(obj.getLong("id"));
                    appointment.setBooked(obj.getBoolean("booked"));
                    appointment.setReason(obj.getString("reason"));
                    String type = obj.isNull("type") ? null : obj.getString("type");
                    appointment.setType(type);

// Parsing the Patient
                    JSONObject patientObj = obj.getJSONObject("patient");
                    Patient patient = new Patient();
                    patient.setId(patientObj.getLong("id"));
                    JSONObject patientDetailsObj = patientObj.getJSONObject("patientDetails");
                    Person patientDetails = new Person(); // Assuming you have a Person model
                    // Populate patientDetails from patientDetailsObj...
                    patientDetails.setFirstName(patientDetailsObj.getString("firstName"));
                    patientDetails.setLastName(patientDetailsObj.getString("lastName"));
                    patientDetails.setEmail(patientDetailsObj.getString("email"));
                    patientDetails.setPhone(patientDetailsObj.getString("phone"));
                    String pictureUrl = patientDetailsObj.isNull("pictureUrl") ? null : patientDetailsObj.getString("pictureUrl");
                    patientDetails.setPictureUrl(pictureUrl);

                    patient.setPatientDetails(patientDetails);
                    appointment.setPatient(patient);

// Parsing the Doctor
                    JSONObject doctorObj = obj.getJSONObject("doctor");
                    Doctor doctor = new Doctor();
                    doctor.setId(doctorObj.getLong("id"));
                    JSONObject doctorDetailsObj = doctorObj.getJSONObject("personDetails");
                    var doctorDetails = new Person(); // Populate doctorDetails...
                    // Populate doctorDetails from doctorDetailsObj...
                    doctorDetails.setFirstName(doctorDetailsObj.getString("firstName"));
                    doctorDetails.setLastName(doctorDetailsObj.getString("lastName"));
                    doctorDetails.setEmail(doctorDetailsObj.getString("email"));
                    doctorDetails.setPhone(doctorDetailsObj.getString("phone"));
                    doctorDetails.setPictureUrl(doctorDetailsObj.getString("pictureUrl"));
                    doctor.setDoctorDetails(doctorDetails);
                    doctor.setSpecialization(doctorObj.getString("specialization"));
                    doctor.setHospitalName(doctorObj.getString("hospitalName"));
                    doctor.setDepartmentName(doctorObj.getString("departmentName"));
                    appointment.setDoctor(doctor);

// Parsing the TimeSlot
                    // Parse TimeSlot
                    JSONObject timeSlotObj = obj.getJSONObject("timeSlot");
                    TimeSlot timeSlot = new TimeSlot();
                    timeSlot.setId(timeSlotObj.getLong("id"));
                    // Handling startTime and endTime as arrays
                    JSONArray startTimeArray = timeSlotObj.getJSONArray("startTime");
                    JSONArray endTimeArray = timeSlotObj.getJSONArray("endTime");
                    timeSlot.setStartTime(convertJsonArrayToLocalDateTime(startTimeArray));
                    timeSlot.setEndTime(convertJsonArrayToLocalDateTime(endTimeArray));
                    appointment.setTimeSlot(timeSlot);

                    appointments.add(appointment);
                }
            } else {
               log.error("Error fetching appointments: {}", response.getStatusText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointments;
    }

    // Method to convert JSON array to LocalDateTime
    private LocalDateTime convertJsonArrayToLocalDateTime(JSONArray jsonArray) {
        return LocalDateTime.of(jsonArray.getInt(0), jsonArray.getInt(1), jsonArray.getInt(2),
                jsonArray.getInt(3), jsonArray.getInt(4), jsonArray.getInt(5));
    }

}
