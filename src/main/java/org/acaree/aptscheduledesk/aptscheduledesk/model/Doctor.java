package org.acaree.aptscheduledesk.aptscheduledesk.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Doctor {
    private Long id;

    private Person doctorDetails;
    private String specialization;
    private String hospitalName;
    private String departmentName;
    private Set<DoctorAvailability> daysAvailable = new HashSet<>(); //<-- Set of days this doctor is available.
    private List<Appointment> appointments; //<-- List of appointments for this doctor in the system.

    public Doctor() {
    }

    public Doctor(Person doctorDetails, String specialization, String hospitalName, String departmentName) {
        this.doctorDetails = doctorDetails;
        this.specialization = specialization;
        this.hospitalName = hospitalName;
        this.departmentName = departmentName;
    }

    public Long getId() {
        return this.id;
    }

    public Person getDoctorDetails() {
        return this.doctorDetails;
    }

    public String getSpecialization() {
        return this.specialization;
    }

    public String getHospitalName() {
        return this.hospitalName;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public Set<DoctorAvailability> getDaysAvailable() {
        return this.daysAvailable;
    }

    public List<Appointment> getAppointments() {
        return this.appointments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDoctorDetails(Person doctorDetails) {
        this.doctorDetails = doctorDetails;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setDaysAvailable(Set<DoctorAvailability> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }




    //== Inner class to represent the days of the week a doctor is available.
    public enum DaysOfTheWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }


    @Override
    public String toString() {
        return "Doctor{" +
                "doctorDetails=" + doctorDetails.getFirstName() + " " + doctorDetails.getLastName() +
                ", id=" + id + doctorDetails.getEmail() + doctorDetails.getPhone() + doctorDetails.getPictureUrl() +
                ", specialization='" + specialization + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
