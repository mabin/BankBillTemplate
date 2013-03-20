package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BanksDataUtils {
	private String bankType = null ;
	private File bankFile = null ;
	private List<String> bankList = new ArrayList<String>();
	
	public List<String> getList(){
		try {
			bankFile = new File("resource/Bank_"+bankType+".txt");
			FileInputStream fis = new FileInputStream(bankFile);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			String line = null ;
			while ( (line = reader.readLine()) != null){
				bankList.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bankList;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	
	
}
