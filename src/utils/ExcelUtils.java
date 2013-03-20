package utils;

import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.biff.DisplayFormat;
import jxl.format.Alignment;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtils {
	
	public WritableWorkbook createWorkBook(String filePath){
		WritableWorkbook wwb = null;
		try {
			wwb = Workbook.createWorkbook(new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
		return wwb ;
	}
	
	public WritableCellFormat getCellFormat(String fontName,int fontSize){
        WritableFont wf = new WritableFont(WritableFont.createFont(fontName),fontSize,WritableFont.NO_BOLD,false);  
        WritableCellFormat   wcf   =   new   WritableCellFormat(wf); 
        
        return wcf ;
	}	
	public WritableCellFormat getCellFormat(String fontName,int fontSize,String alignType){
        WritableFont wf = new WritableFont(WritableFont.createFont(fontName),fontSize,WritableFont.NO_BOLD,false);  
        WritableCellFormat   wcf   =   new   WritableCellFormat(wf); 
        if (alignType.equals("left")){
        	try {
				wcf.setAlignment(Alignment.LEFT);
			} catch (WriteException e) {
				e.printStackTrace();
			}
        }
        else if (alignType.equals("center")){
        	try {
				wcf.setAlignment(Alignment.CENTRE);
			} catch (WriteException e) {
				e.printStackTrace();
			}
        }
        else if (alignType.equals("right")){
        	try {
				wcf.setAlignment(Alignment.RIGHT);
			} catch (WriteException e) {
				e.printStackTrace();
			}
        }
        
        return wcf ;
	}
	
	public WritableCellFormat getCellFormat(DisplayFormat format){
		return new WritableCellFormat(format);
	}
	
	
	public Formula getFormula(int column, int row, String formulaStr, WritableCellFormat format){
		Formula f = new Formula(column, row, formulaStr,format);
		return f ;
	}
	
	public Label getLabel(int column, int row, String value, WritableCellFormat format){
		Label label = new Label(column,row,value,format);
		return label;
	}
	
	public Label getLabel(int column, int row, String value){
		Label label = new Label(column,row,value);
		return label;
	}
	
}
