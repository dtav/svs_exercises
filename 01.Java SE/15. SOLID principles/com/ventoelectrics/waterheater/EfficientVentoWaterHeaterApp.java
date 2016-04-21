package com.ventoelectrics.waterheater;

import com.acme.ventoAdapters.HeaterAdapter;
import com.acme.ventoAdapters.ThermometerAdapter;
import com.acme.waterheater.*;

public class EfficientVentoWaterHeaterApp {

	public static void main(String[] args) throws Exception {

		final VentoThermometer ventoThermometer = new VentoThermometer();
		final VentoHeater ventoHeater = new VentoHeater();
		final VentoPowerSwitch ventoPowerSwitch = new VentoPowerSwitch();

		final VentoThermoregulator ventoThermoregulator = new EfficientThermoregulator(ventoThermometer, ventoHeater);

		ThermometerAdapter vento_acme_thermo_adapter = new ThermometerAdapter(ventoThermometer);
		HeaterAdapter vento_acme_heater_adapter = new HeaterAdapter(ventoHeater);

		final EfficientThermoregulator_nonSpecific effn = new EfficientThermoregulator_nonSpecific(
				vento_acme_thermo_adapter, vento_acme_heater_adapter);

		ventoPowerSwitch.controlPowerFor(ventoThermoregulator);
		// ventoPowerSwitch.controlPowerFor(effn);
		ventoPowerSwitch.controlPowerFor(ventoHeater);
		ventoPowerSwitch.controlPowerFor(ventoThermometer);

		Thread effnThread = new Thread((Runnable) effn);
		effnThread.start();

		VentoWaterHeaterApp.run(effn, ventoPowerSwitch);

	}
}
