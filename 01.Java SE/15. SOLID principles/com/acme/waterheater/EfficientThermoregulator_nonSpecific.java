package com.acme.waterheater;

import com.ventoelectrics.waterheater.VentoHeater;
import com.ventoelectrics.waterheater.VentoThermometer;

public class EfficientThermoregulator_nonSpecific extends Thermoregulator implements Runnable, PoweredDevice{
	
	public EfficientThermoregulator_nonSpecific(VentoThermometer vThermo, VentoHeater vHeater) {
		super(vThermo, vHeater);
		sleepTime = 1000;
		
	}



}
