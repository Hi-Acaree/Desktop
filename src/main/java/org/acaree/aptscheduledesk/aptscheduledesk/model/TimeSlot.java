package org.acaree.aptscheduledesk.aptscheduledesk.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class TimeSlot {
    private long version;
    private long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isBooked;
    private DoctorAvailability availability;
    private LocalDateTime date;

    public TimeSlot() {

    }

    public TimeSlot(LocalDateTime startTime, LocalDateTime endTime, boolean isBooked) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.isBooked = isBooked;
    }

    public TimeSlot(LocalDateTime startTime, LocalDateTime endTime, boolean isBooked, DoctorAvailability availability) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.isBooked = isBooked;
        this.availability = availability;
    }

    public TimeSlot(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return version == timeSlot.version && id == timeSlot.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, id);
    }

    public long getVersion() {
        return this.version;
    }

    public long getId() {
        return this.id;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public boolean isBooked() {
        return this.isBooked;
    }

    public DoctorAvailability getAvailability() {
        return this.availability;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public void setAvailability(DoctorAvailability availability) {
        this.availability = availability;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "version=" + version +
                ", id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isBooked=" + isBooked +
                ", availability=" + availability +
                ", date=" + date +
                '}';
    }
}
