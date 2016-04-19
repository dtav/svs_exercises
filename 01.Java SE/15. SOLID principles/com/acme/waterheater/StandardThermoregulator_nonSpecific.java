package com.acme.waterheater;

import com.ventoelectrics.waterheater.VentoHeater;
import com.ventoelectrics.waterheater.VentoThermometer;

public class StandardThermoregulator_nonSpecific extends Thermoregulator implements Runnable, PoweredDevice{
	
	public StandardThermoregulator_nonSpecific(VentoThermometer vThermo, VentoHeater vHeater) {
		super(vThermo, vHeater);
		sleepTime = 3000;
		
	}



}