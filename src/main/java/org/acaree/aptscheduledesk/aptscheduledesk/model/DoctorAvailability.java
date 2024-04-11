package org.acaree.aptscheduledesk.aptscheduledesk.model;

import java.util.Objects;
import java.util.Set;

public class DoctorAvailability {
    private long id;
    private Doctor doctor;
    private Doctor.DaysOfTheWeek day;
    private Set<TimeSlot> timeSlots;

    public DoctorAvailability() {

    }

    public DoctorAvailability(Doctor doctor, Doctor.DaysOfTheWeek day, Set<TimeSlot> timeSlots) {
        this.doctor = doctor;
        this.day = day;
        this.timeSlots = timeSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorAvailability that = (DoctorAvailability) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getId() {
        return this.id;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public Doctor.DaysOfTheWeek getDay() {
        return this.day;
    }

    public Set<TimeSlot> getTimeSlots() {
        return this.timeSlots;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setDay(Doctor.DaysOfTheWeek day) {
        this.day = day;
    }

    public void setTimeSlots(Set<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
