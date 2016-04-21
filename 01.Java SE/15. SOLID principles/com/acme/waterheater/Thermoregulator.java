package com.acme.waterheater;

import com.acme.ventoAdapters.HeaterAdapter;
import com.acme.ventoAdapters.ThermometerAdapter;

//implemented adapters !

//this can be implemented in acme as standalone also
import com.ventoelectrics.waterheater.NoPowerException;

public abstract class Thermoregulator implements Runnable, PoweredDevice {
	ThermometerAdapter thermometer;
	HeaterAdapter heater;
	Integer thresholdTemp;
	boolean powerEnabled;
	long sleepTime;
	
	
	public ThermometerAdapter getThermoAdapter(){
		return this.thermometer;
	}
	
	public Thermoregulator(ThermometerAdapter ta, HeaterAdapter ha) {
		this.thermometer = ta;
		this.heater = ha;
		
	}
	
	@Override
	public void enablePower() {
		this.powerEnabled = true;
		
	}

	@Override
	public void disablePower() {
		this.powerEnabled = false;
		
	}

	public void setTemperature(Integer temperature) {
		this.thresholdTemp = temperature;
		
	}

	@Override
	public void run() {
		boolean thermoEnabled = true;
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
				currentTemp = thermometer.getTemperature();
			} catch (NoPowerException npe) {
				System.out.println("No power, exiting");
				npe.printStackTrace();
				return;
			}
			
			System.out.println("\n ThermoReg info: \n current heater temp: "+ currentTemp + "\t current threshold temp " + thresholdTemp );
			
			if ((currentTemp <= thresholdTemp - 5)){
				if (thermoEnabled){
					System.out.println("ThermoReg info: Heater already enabled");
					continue;
				}
				heater.enable();
				thermoEnabled = true;
			} else if ((currentTemp >= thresholdTemp + 5)){
				if (!thermoEnabled){
					System.out.println("ThermoReg info: Heater already disabled");
					continue;
				}
				heater.disable();
				thermoEnabled = false;
			}
			
			
		}
		
	}
	

}
