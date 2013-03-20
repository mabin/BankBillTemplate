package handler;

import java.io.File;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.PaperSize;
import jxl.format.VerticalAlignment;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.joda.time.format.DateTimeFormat;

import utils.ExcelUtils;

import beans.DaLianDetail;
import beans.GongHangDetail;
import beans.ZhongHangDetail;

public class ZhongHangExcel {
	public void writeToExcel(String fileName, List<ZhongHangDetail> details,String cardNo,String userName,String oper, String printDate){   
        ExcelUtils excelUtils = new ExcelUtils(); 
		try{	        WritableWorkbook wwb = excelUtils.createWorkBook(fileName);   
        //创建Excel工作表 指定名称和位置   
        WritableSheet ws = wwb.createSheet("Test Sheet 1",0);   
        
        ws.setColumnView(0, 11);
        ws.setColumnView(1, 11);
        ws.setColumnView(2, 10);
        ws.setColumnView(3, 7);
        ws.setColumnView(4, 10);
        ws.setColumnView(5, 14);
        ws.setColumnView(6, 17);
        ws.setColumnView(7, 16);
        
        //设定冻结行
        ws.getSettings().setVerticalFreeze(12);
        
        NumberFormat numFormat = new NumberFormat("#,##0.000;(#,##0.000)");
        WritableFont wenVinFont = new WritableFont(WritableFont.createFont("wenvin"),12);
        
        WritableCellFormat   titleFormat   =   excelUtils.getCellFormat("SimSun-ExtB", 10); 
        titleFormat.setAlignment(Alignment.LEFT);
        titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        
        WritableCellFormat   title13Format   =   excelUtils.getCellFormat("SimSun-ExtB", 10); 
        title13Format.setAlignment(Alignment.LEFT);
        title13Format.setVerticalAlignment(VerticalAlignment.CENTRE);
        
        WritableCellFormat headerFormat = excelUtils.getCellFormat("方正宋三简体", 10);
        headerFormat.setAlignment(Alignment.LEFT);
        headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        
        WritableCellFormat dateOneStr = excelUtils.getCellFormat("方正宋三简体", 10);
        dateOneStr.setAlignment(Alignment.LEFT);
        dateOneStr.setVerticalAlignment(VerticalAlignment.CENTRE);
        WritableCellFormat dateOneStrNum = excelUtils.getCellFormat("wenvin", 12);
        dateOneStrNum.setAlignment(Alignment.LEFT);
        dateOneStrNum.setVerticalAlignment(VerticalAlignment.CENTRE);
        
        
        WritableCellFormat dateOneNum = new WritableCellFormat(wenVinFont, numFormat);
        dateOneNum.setAlignment(Alignment.LEFT);
        dateOneNum.setVerticalAlignment(VerticalAlignment.CENTRE);
        
        WritableCellFormat dateTowStr = excelUtils.getCellFormat("方正宋三简体", 10);
        dateTowStr.setAlignment(Alignment.LEFT);
        dateTowStr.setVerticalAlignment(VerticalAlignment.CENTRE);
        dateTowStr.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4));
        WritableCellFormat dateTowStrNum = excelUtils.getCellFormat("wenvin", 10);
        dateTowStrNum.setAlignment(Alignment.LEFT);
        dateTowStrNum.setVerticalAlignment(VerticalAlignment.CENTRE);
        dateTowStrNum.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4));
        
        WritableCellFormat dateTowNum = new WritableCellFormat(wenVinFont, numFormat);
        dateTowNum.setAlignment(Alignment.LEFT);
        dateTowNum.setVerticalAlignment(VerticalAlignment.CENTRE);
        dateTowNum.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4));
        
    	
        //**************往工作表中添加数据*****************   
       	
        ws.addCell(excelUtils.getLabel(1,0,"新线存款历史交易明细清单",titleFormat)); 
        
        ws.addCell(excelUtils.getLabel(0,2,"交易区间:",titleFormat));    
        ws.addCell(excelUtils.getLabel(1,2,printDate.split("#")[0].replace("-", "/")+"   至",title13Format));    
        //ws.addCell(excelUtils.getLabel(2,2,"   至",titleFormat));    
        ws.addCell(excelUtils.getLabel(3,2,printDate.split("#")[1].replace("-", "/"),title13Format)); 
        
        ws.addCell(excelUtils.getLabel(0,3,"打印日期:",titleFormat));    
        ws.addCell(excelUtils.getLabel(1,3,printDate.split("#")[1].replace("-", "/")+"   打印网点: 4832",title13Format));    
        //ws.addCell(excelUtils.getLabel(2,3,"   打印网点: 4832",titleFormat));    
        ws.addCell(excelUtils.getLabel(5,3,"打印柜员:",titleFormat)); 
        ws.addCell(excelUtils.getLabel(6,3,"2978618 ",title13Format)); 
        
        ws.addCell(excelUtils.getLabel(0,4,"帐号:",titleFormat)); 
        ws.addCell(excelUtils.getLabel(1,4,"289560723498",title13Format)); 
        ws.addCell(excelUtils.getLabel(4, 4, "客户号:",titleFormat));
        ws.addCell(excelUtils.getLabel(5, 4, "89414804",title13Format));
        
        ws.addCell(excelUtils.getLabel(0,5,"帐户名:",titleFormat)); 
        ws.addCell(excelUtils.getLabel(1,5,userName,titleFormat)); 
        
        ws.addCell(excelUtils.getLabel(0,6,"开户日期:",titleFormat));    
        ws.addCell(excelUtils.getLabel(1,6,"2012/05/23",title13Format));    
        ws.addCell(excelUtils.getLabel(4,6,"开户行:",titleFormat));    
        ws.addCell(excelUtils.getLabel(5,6,"04386",title13Format));    
        
        ws.addCell(excelUtils.getLabel(0,7,"产品大类:",titleFormat));    
        ws.addCell(excelUtils.getLabel(1,7,"5502",title13Format));    
        ws.addCell(excelUtils.getLabel(4,7,"产品子类",titleFormat));
        ws.addCell(excelUtils.getLabel(5,7,"1001",title13Format));
        
        ws.addCell(excelUtils.getLabel(0,8,"起息日",titleFormat));    
        ws.addCell(excelUtils.getLabel(1,8,"2012/06/21",title13Format));    
        ws.addCell(excelUtils.getLabel(4,8,"到期日:",titleFormat));
        
        ws.addCell(excelUtils.getLabel(0,9,"存折号:",titleFormat));    
        ws.addCell(excelUtils.getLabel(4,9,"货币号:",titleFormat));
        
        ws.addCell(excelUtils.getLabel(0,10,"交易日",headerFormat));
        ws.addCell(excelUtils.getLabel(1,10,"网点",headerFormat));
        ws.addCell(excelUtils.getLabel(2,10,"交易代码",headerFormat));
        ws.addCell(excelUtils.getLabel(3,10,"货币号",headerFormat));
        ws.addCell(excelUtils.getLabel(4,10,"",headerFormat));
        ws.addCell(excelUtils.getLabel(5,10,"交易金额",headerFormat));
        ws.addCell(excelUtils.getLabel(6,10,"交易余额",headerFormat));
        ws.addCell(excelUtils.getLabel(7,10,"摘要",headerFormat));
        
        ws.addCell(excelUtils.getLabel(0,11,"交易类别",headerFormat));
        ws.addCell(excelUtils.getLabel(1,11,"对方帐号",headerFormat));
        ws.addCell(excelUtils.getLabel(2,11,"",headerFormat));
        ws.addCell(excelUtils.getLabel(3,11,"冲正",headerFormat));
        ws.addCell(excelUtils.getLabel(4,11,"",headerFormat));
        ws.addCell(excelUtils.getLabel(5,11,"过账日期",headerFormat));
        ws.addCell(excelUtils.getLabel(6,11,"柜员",headerFormat));
        ws.addCell(excelUtils.getLabel(7,11,"交易名称",headerFormat));
        //一共的行数
        int totalRows = details.size();
        //开始行
        int startRow = 12;
        for (int i=0; i<details.size(); i+=2){
        	ZhongHangDetail detail = details.get(i);
        	int column = 0 ;
        	ws.addCell(excelUtils.getLabel(column++,startRow+i,String.valueOf(detail.getDate().toString(DateTimeFormat.forPattern("yyyyMMdd"))),dateOneStrNum));
        	ws.addCell(excelUtils.getLabel(column++,startRow+i,detail.getBranch(),dateOneStrNum));
        	ws.addCell(excelUtils.getLabel(column++,startRow+i,detail.getTradeCode(),dateOneStrNum));
        	ws.addCell(excelUtils.getLabel(column++,startRow+i,detail.getSubject(),dateOneStr));
        	column++;

        	if (detail.getIn() == 0){
        	        jxl.write.Number n = new jxl.write.Number(column++,startRow+i,detail.getOut(), dateOneNum); 
        	        ws.addCell(n);
        		if(i==0){
        			jxl.write.Number nn = new jxl.write.Number(column++,startRow+i,detail.getAmount(), dateOneNum); 
        	        ws.addCell(nn);
        		}else{
	        		Formula f = new Formula(column++, startRow+i, 
	        				"J"+String.valueOf(startRow+i+1+1)+"-I"+String.valueOf(startRow+i+1)+"",dateOneNum); 
	        		ws.addCell(f);
        		}
        	}else{
        		jxl.write.Number n = new jxl.write.Number(column++,startRow+i,detail.getIn(), dateOneNum); 
    	        ws.addCell(n);
        		if (i==0){
        			jxl.write.Number nn = new jxl.write.Number(column++,startRow+i,detail.getAmount(), dateOneNum); 
        	        ws.addCell(nn);
        		}else{
	        		Formula f = new Formula(column++, startRow+i, 
	        				"J"+String.valueOf(startRow+i+1+1)+"+I"+String.valueOf(startRow+i+1)+"",dateOneNum); 
	        		ws.addCell(f);
        		}
        	}
        	ws.addCell(excelUtils.getLabel(column++,startRow+i,detail.getSubject(),dateOneStr));
        	
        	//换行
        	column = 0;
        	ws.addCell(excelUtils.getLabel(column++,startRow+i+1,detail.getTradeType(),dateTowStrNum));
        	ws.mergeCells(column, startRow+i+1, column+1, startRow+i+1);
        	ws.addCell(excelUtils.getLabel(column++,startRow+i+1,detail.getObjectAccount(),dateTowStrNum));
        	column++;
        	ws.addCell(excelUtils.getLabel(column++,startRow+i+1,"",dateTowStr));
        	ws.addCell(excelUtils.getLabel(column++,startRow+i+1,"",dateTowStr));
        	ws.addCell(excelUtils.getLabel(column++,startRow+i+1,String.valueOf(detail.getDate().toString(DateTimeFormat.forPattern("yyyyMMdd"))),dateTowStrNum));
        	ws.addCell(excelUtils.getLabel(column++,startRow+i+1,detail.getOperation(),dateTowStrNum));
        	ws.addCell(excelUtils.getLabel(column++,startRow+i+1,detail.getTradeName(),dateTowStr));
        	
        	//插入分页符 每页打印25行
        	if ( i % 15 == 0 && i != 0){
	        	ws.addRowPageBreak(i+10);
	        }
        }	        //写入工作表   
	        wwb.write();   
	        wwb.close(); 
        }catch(Exception e){
			e.printStackTrace();
		}
		
		
    }   
}