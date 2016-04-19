package investmentCalculator;

/*
Exercise: InvestmentCalculator
Create a program that calculates how much a $14,000 investment would be worth if it 
increased in value by 40% during the first year, lost $1,500 in value the second year, and 
increased 12% in the third year.
 */

public class InvestmentCalculator {

	Investment inv;
	
	public InvestmentCalculator(Investment inv) {
		this.inv = inv;
	}
	
	public void yearlyChangePercentage(float factor, String qualifier){
		float multiplier = 1;
		if (qualifier.equals("positive")){
			multiplier = 1 + factor / 100;
		} else if (qualifier.equals("negative")){
			multiplier = 1 - factor / 100;
		} else {
			System.out.println("Qualifier can only be positive or negative");
		}
		System.out.println("multiplier is: " + multiplier);
		inv.setInvestment(inv.getInvestment() * multiplier);
		inv.printStatus();
	}
	
	public void yearlyChangeValue(float factor, String qualifier){
		float addedValue = 0;
		if(qualifier.equalsIgnoreCase("positive")){
			addedValue = factor;
		} else if (qualifier.equalsIgnoreCase("negative")){
			addedValue = -factor;
		} else {
			System.out.println("Qualifier can only be positive or negative");
		}
		System.out.println("added values is: " + addedValue);
		inv.setInvestment(inv.getInvestment() + addedValue);
		inv.printStatus();
	}
	
	
	
	public static void main(String[] args) {
		Investment investment = new Investment(14000);
		InvestmentCalculator ic = new InvestmentCalculator(investment);
		ic.yearlyChangePercentage(40, "positive");
		ic.yearlyChangeValue(1500, "negative");
		ic.yearlyChangePercentage(12, "positive");
		
	}
}
