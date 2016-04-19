package com.acme.waterheater;

//unfinished 

import com.ventoelectrics.waterheater.NoPowerException;
import com.ventoelectrics.waterheater.VentoThermometer;
import java.util.Random;


public class ThermometerAdapter {
	boolean powerEnabled;
	Random random;
	VentoThermometer vt;
	
	public ThermometerAdapter(VentoThermometer vt, boolean powerEnabled, Random random){
		this.vt = vt;
		this.powerEnabled = powerEnabled;
		this.random = random;
	}
	
	
	public Integer getTemperature() {
		if (!powerEnabled) {
			throw new NoPowerException();
		}
		return random.nextInt(60);
	}

	public void enablePower() {
		powerEnabled = true;
	}

	public void disablePower() {
		powerEnabled = false;
	}

}
