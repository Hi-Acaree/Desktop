package org.acaree.aptscheduledesk.aptscheduledesk.model;

import org.slf4j.Logger;

import java.util.Objects;


/**
 * This class represents an appointment in the system.
 * An appointment is a scheduled time for a patient to see a doctor.
 * An appointment can have a patient.
 * An appointment can have a doctor.
 * An appointment can have a time slot.
 * An appointment can have a reason.
 * An appointment can have a type.
 * An appointment can be booked.
 **/

public class Appointment {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Appointment.class);
    private long id;
    private Patient patient;
    private Doctor doctor;
    private TimeSlot timeSlot;
    private String reason;
    private String type;
    private boolean booked;

    public Appointment() {
    }

    public Appointment(Patient patient, Doctor doctor, String reason, TimeSlot timeSlot) {
        this.patient = patient;
        this.doctor = doctor;
        this.reason = reason;
        this.timeSlot = timeSlot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getId() {
        return this.id;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public TimeSlot getTimeSlot() {
        return this.timeSlot;
    }

    public String getReason() {
        return this.reason;
    }

    public String getType() {
        return this.type;
    }

    public boolean isBooked() {
        return this.booked;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", timeSlot=" + timeSlot +
                ", reason='" + reason + '\'' +
                ", type='" + type + '\'' +
                ", booked=" + booked +
                '}';
    }
}
