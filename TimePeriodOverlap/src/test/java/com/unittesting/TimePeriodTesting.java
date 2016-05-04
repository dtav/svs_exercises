package com.unittesting;

import java.time.LocalTime;

import org.junit.*;

public class TimePeriodTesting {

	TimePeriod tpTest;

	@Before
	public void setup() {
		LocalTime startTime = LocalTime.of(15, 00);
		LocalTime endTime = LocalTime.of(18, 00);
		this.tpTest = new TimePeriod(startTime, endTime);

	}

	@Test
	public void sameDate() {
		TimePeriod copyOfTpTest = new TimePeriod(tpTest.getStartTime(), tpTest.getEndTime());
		Assert.assertTrue(tpTest.overlapsWith(copyOfTpTest));

	}
	
	@Test
	public void overlapsWithEndToStart(){
		TimePeriod comparisonTestCase =  new TimePeriod(tpTest.getEndTime(), tpTest.getStartTime());
		Assert.assertFalse(tpTest.overlapsWith(comparisonTestCase));

	}
	
	@Test
	public void overlapsWithStartSameEndBigger(){
		TimePeriod comparisonTestCase =  new TimePeriod(tpTest.getStartTime(), LocalTime.of(19, 00));
		Assert.assertTrue(tpTest.overlapsWith(comparisonTestCase));

	}
	@Test	
	public void overlapsWithStartSameEndSmaller(){
		TimePeriod comparisonTestCase =  new TimePeriod(tpTest.getStartTime(), LocalTime.of(17, 00));
		Assert.assertTrue(tpTest.overlapsWith(comparisonTestCase));
	}
	@Test
	public void overlapsWithStartSmallerEndSame(){
		TimePeriod comparisonTestCase =  new TimePeriod(LocalTime.of(14, 00), tpTest.getEndTime());
		Assert.assertTrue(tpTest.overlapsWith(comparisonTestCase));
	}
	@Test
	public void overlapsWithStartBiggerEndSame(){
		TimePeriod comparisonTestCase =  new TimePeriod(LocalTime.of(16, 00), tpTest.getEndTime());
		Assert.assertTrue(tpTest.overlapsWith(comparisonTestCase));
	}
	
	

}
