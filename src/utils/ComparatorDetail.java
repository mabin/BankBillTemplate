package utils;

import java.util.Comparator;

import beans.Detail;

public class ComparatorDetail<T extends Detail> implements Comparator {

	@Override
	public int compare(Object obj1, Object obj2) {
		Detail d1 = (Detail)obj1;
		Detail d2 = (Detail)obj2;
		
		return d1.getDate().isBefore(d2.getDate())?-1:1 ;
	}

}
