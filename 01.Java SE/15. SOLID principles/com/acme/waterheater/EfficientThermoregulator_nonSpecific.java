package com.acme.waterheater;

import com.acme.ventoAdapters.HeaterAdapter;
import com.acme.ventoAdapters.ThermometerAdapter;

public class EfficientThermoregulator_nonSpecific extends Thermoregulator implements Runnable, PoweredDevice{
	
	public EfficientThermoregulator_nonSpecific(ThermometerAdapter ta, HeaterAdapter ha) {
		super(ta, ha);
		sleepTime = 1000;
		
	}



}
