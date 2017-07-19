package tw.andrew;

import java.math.BigDecimal;

class Pressure {
	double presSetPoint = 1010.325; // Unit: kPa
	double presAccuracy = 0.005;
	double presRange = presSetPoint * (Math.random() * presAccuracy);
	double tempPres;
	
	String getPressure() {
		if ( (int)(Math.random()*2) == 1) { // Set Point + Range
			tempPres = presSetPoint + presRange;		
		}else { // Set Point - Range
			tempPres = presSetPoint - presRange;		
		}
		BigDecimal pres;
		pres = new BigDecimal(tempPres).setScale(1, BigDecimal.ROUND_HALF_UP); // 取小數點後第一位，四捨五入
		String pressure = pres.toString();
		
		return pressure;
	}
}
