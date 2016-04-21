package com.acme.waterheater;

import com.acme.ventoAdapters.HeaterAdapter;
import com.acme.ventoAdapters.ThermometerAdapter;

public class StandardThermoregulator_nonSpecific extends Thermoregulator implements Runnable, PoweredDevice {

	public StandardThermoregulator_nonSpecific(ThermometerAdapter ta, HeaterAdapter ha) {
		super(ta, ha);
		sleepTime = 3000;

	}

}