package com.acme.waterheater;

import com.ventoelectrics.waterheater.*;

public abstract class Thermoregulator implements VentoThermoregulator, Runnable {
	VentoThermometer vThermo;
	VentoHeater vHeater;
	Integer thresholdTemp;
	boolean powerEnabled;
	long sleepTime;
	
	
	
	public Thermoregulator(VentoThermometer vThermo, VentoHeater vHeater) {
		this.vThermo = vThermo;
		this.vHeater = vHeater;
		
	}
	
	@Override
	public void enablePower() {
		this.powerEnabled = true;
		
	}

	@Override
	public void disablePower() {
		this.powerEnabled = false;
		
	}

	@Override
	public void setTemperature(Integer temperature) {
		this.thresholdTemp = temperature;
		
	}

	@Override
	public void run() {
		boolean vThermoEnabled = true;
		while (true){
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoPowerException npe) {
				System.out.println("No power, exiting");
				npe.printStackTrace();
				return;
			}

			Integer currentTemp = null;
			try {
				currentTemp = vThermo.getTemperature();
			} catch (NoPowerException npe) {
				System.out.println("No power, exiting");
				npe.printStackTrace();
				return;
			}
			
			System.out.println("\n ThermoReg info: \n current heater temp: "+ currentTemp + "\t current threshold temp " + thresholdTemp );
			
			if ((currentTemp <= thresholdTemp - 5)){
				if (vThermoEnabled){
					System.out.println("ThermoReg info: Heater already enabled");
					continue;
				}
				vHeater.enable();
				vThermoEnabled = true;
			} else if ((currentTemp >= thresholdTemp + 5)){
				if (!vThermoEnabled){
					System.out.println("ThermoReg info: Heater already disabled");
					continue;
				}
				vHeater.disable();
				vThermoEnabled = false;
			}
			
			
		}
		
	}
	

}
