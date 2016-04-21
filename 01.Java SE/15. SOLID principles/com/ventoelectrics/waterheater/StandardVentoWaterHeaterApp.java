package com.ventoelectrics.waterheater;

import com.acme.ventoAdapters.HeaterAdapter;
import com.acme.ventoAdapters.ThermometerAdapter;
import com.acme.waterheater.StandardThermoregulator;
import com.acme.waterheater.StandardThermoregulator_nonSpecific;

public class StandardVentoWaterHeaterApp {

	public static void main(String[] args) throws Exception {

		final VentoThermometer ventoThermometer = new VentoThermometer();
		final VentoHeater ventoHeater = new VentoHeater();
		final VentoPowerSwitch ventoPowerSwitch = new VentoPowerSwitch();

		final VentoThermoregulator ventoThermoregulator = new StandardThermoregulator(ventoThermometer, ventoHeater);

		ThermometerAdapter vento_acme_thermo_adapter = new ThermometerAdapter(ventoThermometer);
		HeaterAdapter vento_acme_heater_adapter = new HeaterAdapter(ventoHeater);

		final StandardThermoregulator_nonSpecific thermoregulator = new StandardThermoregulator_nonSpecific(
				vento_acme_thermo_adapter, vento_acme_heater_adapter);

		ventoPowerSwitch.controlPowerFor(ventoThermoregulator);
		ventoPowerSwitch.controlPowerFor(ventoHeater);
		ventoPowerSwitch.controlPowerFor(ventoThermometer);

		Thread standardThread = new Thread((Runnable) thermoregulator);
		standardThread.start();

		VentoWaterHeaterApp.run(thermoregulator, ventoPowerSwitch);
	}
}
