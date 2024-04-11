package org.acaree.aptscheduledesk.aptscheduledesk.model;
import java.util.Objects;

public class Patient {
    private long id;
    private Person patientDetails;


    public Patient() {
    }

    public Patient(Person patientDetails) {
        this.patientDetails = patientDetails;
    }

    public long getId() {
        return this.id;
    }

    public Person getPatientDetails() {
        return this.patientDetails;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setPatientDetails(Person patientDetails) {
        this.patientDetails = patientDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientDetails=" + patientDetails.getFirstName() + " " + patientDetails.getLastName() +
                ", id=" + id + '\n' + patientDetails.getEmail() + '\n' + patientDetails.getPhone() + '\n' +
                patientDetails.getPictureUrl() + '\'' +
                '}';
    }
}
