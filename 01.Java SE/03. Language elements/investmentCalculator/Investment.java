package investmentCalculator;

public class Investment {
	float investment;

	public Investment(float investment) {
		this.investment = investment;
	}

	public void printStatus() {
		System.out.println("Current ivestment status: " + this.investment);
	}

	public float getInvestment() {
		return investment;
	}

	public void setInvestment(float investment) {
		this.investment = investment;
	}
	
	
}
