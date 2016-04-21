package com.acme.ventoAdapters;

//unfinished 


import com.ventoelectrics.waterheater.VentoThermometer;



public class ThermometerAdapter {
	
	VentoThermometer vt;
	
	public ThermometerAdapter(VentoThermometer vt){
		this.vt = vt;
	}
	
	
	public Integer getTemperature() {
		return this.vt.getTemperature();
	}
	
	public void enablePower(){
		vt.enablePower();
	}
	
	public void disablePower(){
		vt.disablePower();
	}
	
	public VentoThermometer getVentoCounterpart(){
		return this.vt;
	}

	
}
