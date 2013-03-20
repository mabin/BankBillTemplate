package handler;

import java.util.List;

import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.joda.time.format.DateTimeFormat;

import utils.ExcelUtils;

import beans.DaLianDetail;

public class DaLianBankExcel {
	public void writeToExcel(String fileName, List<DaLianDetail> details,String cardNo,String userName,String oper, String printDate){   
        ExcelUtils excelUtils = new ExcelUtils(); 
		try{
	        WritableWorkbook wwb = excelUtils.createWorkBook(fileName);   
	        //创建Excel工作表 指定名称和位置   
	        WritableSheet ws = wwb.createSheet("Test Sheet 1",0);   
	        
            ws.getSettings().setDefaultRowHeight(195*2);
            
            ws.setColumnView(0, 7);
            ws.setColumnView(1, 7);
            ws.setColumnView(2, 8);
            ws.setColumnView(3, 7);
            ws.setColumnView(4, 10);
            ws.setColumnView(5, 10);
            ws.setColumnView(6, 6);
            ws.setColumnView(7, 5);
            ws.setColumnView(8, 9);
            ws.setColumnView(9, 9);
            ws.setColumnView(10, 9);
            ws.setColumnView(11, 7);
            
            //ws.getSettings().setFitHeight(5) ;                        // 打印区高度
            //ws.getSettings().setVerticalPrintResolution(1000);
           // ws.getSettings().setPrintArea(0,0,11,25);
            //ws.getSettings().setPageBreakPreviewMode(false);
            ws.getSettings().setPrintTitles(0, 2, 0, 11);
            //设定冻结行
            ws.getSettings().setVerticalFreeze(3);
            
	        //**************往工作表中添加数据*****************   
            WritableCellFormat   wcf   =   excelUtils.getCellFormat("Arial", 10); 
            wcf.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4)); 
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            
	        WritableCellFormat   songFont   =   excelUtils.getCellFormat("宋体", 10); 
	        songFont.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4)); 
	        songFont.setVerticalAlignment(VerticalAlignment.CENTRE);
	       
	        //NumberFormat numFormat = new NumberFormat("#,##0.00;(#,##0.00)");
	       	WritableCellFormat wcfN = excelUtils.getCellFormat(NumberFormats.FORMAT3);
	       	wcfN.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4));
	       	wcfN.setAlignment(Alignment.LEFT);
	       	wcfN.setVerticalAlignment(VerticalAlignment.CENTRE);
	       	
	       	WritableCellFormat subjectFormat=excelUtils.getCellFormat("宋体", 10); 
	       	subjectFormat.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4)); 
	       	subjectFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

	       	WritableCellFormat   title   =   excelUtils.getCellFormat("Arial", 10); 
	        title.setVerticalAlignment(VerticalAlignment.CENTRE);
	        ws.addCell(excelUtils.getLabel(5,0,"账户明细",title)); 
	        
	        ws.addCell(excelUtils.getLabel(0,1,"所号0010106",wcf));    
	        ws.addCell(excelUtils.getLabel(2,1,"  帐号"+cardNo,wcf));    
	        ws.addCell(excelUtils.getLabel(5,1,"姓名"+userName,wcf));    
	        ws.addCell(excelUtils.getLabel(7,1,"操作员"+oper,wcf));    
	        
	        ws.addCell(excelUtils.getLabel(9,1,"  打印日期"+printDate,wcf));    
	        
	        ws.mergeCells(0, 1, 1, 1);
	        ws.mergeCells(2, 1, 4, 1);
	        ws.mergeCells(5, 1, 6, 1);
	        ws.mergeCells(7, 1, 8, 1);
	        ws.mergeCells(9, 1, 11, 1);
	        
	        ws.addCell(excelUtils.getLabel(0,2,"序号",wcf));
	        ws.addCell(excelUtils.getLabel(1,2,"子户号",wcf));
	       
	        ws.addCell(excelUtils.getLabel(2,2,"  币种",wcf));
	       
	        ws.addCell(excelUtils.getLabel(3,2,"钞汇",wcf));
	        ws.addCell(excelUtils.getLabel(4,2,"交易日期",wcf));
	        ws.addCell(excelUtils.getLabel(5,2,"起息日期",wcf));
	        ws.addCell(excelUtils.getLabel(6,2,"摘要",wcf));
	        ws.addCell(excelUtils.getLabel(7,2,"借贷",wcf));
	        ws.addCell(excelUtils.getLabel(8,2,"交易金额",wcf));
	        ws.addCell(excelUtils.getLabel(9,2,"余额",wcf));
	        ws.addCell(excelUtils.getLabel(10,2,"发送机构",wcf));
	        ws.addCell(excelUtils.getLabel(11,2,"记账员",wcf));
	        
	        
	        
	        
	        jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#,##0.00;(#,##0.00)"); 
	        WritableFont numFont = new WritableFont(WritableFont.createFont("Arial"),11);
	        jxl.write.WritableCellFormat wcff = new jxl.write.WritableCellFormat(numFont,nf); 
	        wcff.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4));
	        wcff.setAlignment(Alignment.LEFT);
	        wcff.setVerticalAlignment(VerticalAlignment.CENTRE);
	        //序号
	        int serial = 366;
	        //一共的行数
	        int totalRows = details.size();
	        //开始行
	        int startRow = totalRows+2;
	        for (int i=0; i<details.size(); i++){
	        	DaLianDetail detail = details.get(i);
	        	int column = 0 ;
	        	ws.addCell(excelUtils.getLabel(column++,startRow-i,String.valueOf(serial++),wcff));
	        	ws.addCell(excelUtils.getLabel(column++,startRow-i,String.valueOf("0"),wcff));
	        	ws.addCell(excelUtils.getLabel(column++,startRow-i,String.valueOf("人民币"),songFont));
	        	ws.addCell(excelUtils.getLabel(column++,startRow-i,String.valueOf("现钞"),songFont));
	        	ws.addCell(excelUtils.getLabel(column++,startRow-i,String.valueOf(detail.getDate().toString(DateTimeFormat.forPattern("yyyyMMdd"))),wcff));
	        	ws.addCell(excelUtils.getLabel(column++,startRow-i,String.valueOf(detail.getDate().toString(DateTimeFormat.forPattern("yyyyMMdd"))),wcff));
	        	
		        Label subjectLabel = excelUtils.getLabel(column++,startRow-i,String.valueOf(detail.getSubject()),subjectFormat);
	        	ws.addCell(subjectLabel);
	        	
	        	ws.addCell(excelUtils.getLabel(column++,startRow-i,String.valueOf(detail.getOperation()),songFont));

	        	if (detail.getOperation().equals("取")){
	        	        jxl.write.Number n = new jxl.write.Number(column++,startRow-i,detail.getOut(), wcff); 
	        	        ws.addCell(n);
	        		if(i==0){
	        			jxl.write.Number nn = new jxl.write.Number(column++,startRow-i,detail.getAmount(), wcff); 
	        	        ws.addCell(nn);
	        		}else{
		        		Formula f = new Formula(column++, startRow-i, 
		        				"J"+String.valueOf(startRow-i+1+1)+"-I"+String.valueOf(startRow-i+1)+"",wcff); 
		        		ws.addCell(f);
	        		}
	        	}else{
	        		jxl.write.Number n = new jxl.write.Number(column++,startRow-i,detail.getIn(), wcff); 
        	        ws.addCell(n);
	        		if (i==0){
	        			jxl.write.Number nn = new jxl.write.Number(column++,startRow-i,detail.getAmount(), wcff); 
	        	        ws.addCell(nn);
	        		}else{
		        		Formula f = new Formula(column++, startRow-i, 
		        				"J"+String.valueOf(startRow-i+1+1)+"+I"+String.valueOf(startRow-i+1)+"",wcff); 
		        		ws.addCell(f);
	        		}
	        	}
	        	ws.addCell(excelUtils.getLabel(column++,startRow-i,String.valueOf(detail.getSendOrg()),wcff));
	        	ws.addCell(excelUtils.getLabel(column++,startRow-i,String.valueOf(detail.getRecorder()),wcff));
	        	
	        	//插入分页符 每页打印25行
	        	if ( i % 25 == 0 && i != 0){
		        	ws.addRowPageBreak(i+3);
		        }
	        }
	        
	        //写入工作表   
	        wwb.write();   
	        wwb.close();   
		}catch(Exception e){
			e.printStackTrace();
		}
    }   
}