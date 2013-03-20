package utils;

import java.text.DecimalFormat;
import java.util.Random;

public class RandomNumUtils { 
	
	/**
	 * 
	 * @param min 最小值
	 * @param max 最大值
	 * @return 返回一个随机整型数
	 */
	public static int getInt(int min, int max){

		Random random = new Random();
		int result ;
		while ( (result = random.nextInt(max)) < min){}
		return result ;
	}
	
	/**
	 * 
	 * @param min 最小值
	 * @param max 最大值
	 * @return 返回一个随机带有两位小数的float数
	 */
	public static float getFloat(int min, int max){
		
		//得到最大值的位数
		String strNum = String.valueOf(max);
		int length = strNum.trim().length();
		Random random = new Random();
		double result ;
		while ( (result = random.nextGaussian()*((int)Math.pow(100, length))) <min || result >max){};
		
		DecimalFormat ft = new DecimalFormat("#.00");
		
		return Float.valueOf(ft.format(result));
	}
} 