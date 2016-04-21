package com.acme.ventoAdapters;

import com.ventoelectrics.waterheater.VentoHeater;

public class HeaterAdapter {
	VentoHeater vh;
	
	public HeaterAdapter(VentoHeater vh) {
		this.vh = vh;
	}
	
	public void enable(){
		vh.enable();
	}
	
	public void disable(){
		vh.disable();
	}
	
	public void enablePower(){
		vh.enablePower();
	}
	
	public void disablePower(){
		vh.disablePower();
	}
	
	
}
