package com.ventoelectrics.waterheater;

import com.acme.waterheater.*;

public class EfficientVentoWaterHeaterApp {

	public static void main(String[] args) throws Exception {

		final VentoThermometer ventoThermometer = new VentoThermometer();
		final VentoHeater ventoHeater = new VentoHeater();
		final VentoPowerSwitch ventoPowerSwitch = new VentoPowerSwitch();

		final VentoThermoregulator ventoThermoregulator = new EfficientThermoregulator(ventoThermometer, ventoHeater);
		
		final EfficientThermoregulator_nonSpecific effn = new EfficientThermoregulator_nonSpecific(ventoThermometer, ventoHeater);
		
		ventoPowerSwitch.controlPowerFor(ventoThermoregulator);
		ventoPowerSwitch.controlPowerFor(effn);
		ventoPowerSwitch.controlPowerFor(ventoHeater);
		ventoPowerSwitch.controlPowerFor(ventoThermometer);
		
		Thread effnThread = new Thread((Runnable) effn);
		effnThread.start();
		
		
		Thread VthremoThread = new Thread((Runnable) ventoThermoregulator);
		//VthremoThread.start();
		

		//VentoWaterHeaterApp.run(ventoThermoregulator, ventoPowerSwitch);
		VentoWaterHeaterApp.run(effn, ventoPowerSwitch);

	}
}
