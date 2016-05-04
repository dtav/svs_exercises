package com.unittesting;

import java.time.*;

public class TimePeriod {

	private LocalTime startTime, endTime;

	public TimePeriod() {
		// TODO Auto-generated constructor stub
	}

	public TimePeriod(LocalTime startTime, LocalTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public boolean overlapsWith(TimePeriod tp) {
		return startTime.isBefore(tp.getEndTime()) && endTime.isAfter(tp.getStartTime());

	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

}
