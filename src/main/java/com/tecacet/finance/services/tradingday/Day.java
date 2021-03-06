package com.tecacet.finance.services.tradingday;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Day implements Comparable<Day> {

    enum DayStatus {
        open,
        closed
    }

    private LocalDate date;
    private DayStatus status;
    private String description;
    private Open open;

    public String getDescription() {
        return description;
    }

    public LocalTime getStartTime() {
        return open == null ? null : LocalTime.parse(open.start);
    }

    public LocalTime getEndTime() {
        return open == null ? null : LocalTime.parse(open.start);
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isHoliday() {
        return status == DayStatus.closed && !isWeekend();
    }

    public boolean isWeekend() {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public boolean isEarlyClose() {
        return status == DayStatus.open && open.isEarlyClose();
    }

    @Override
    public int compareTo(@NotNull Day o) {
        return date.compareTo(o.date);
    }
}
