package handler;

import java.io.File;
import java.util.List;

import jxl.CellView;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Workbook;
import jxl.biff.FontRecord;
import jxl.format.Alignment;
import jxl.format.CellFormat;
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
import utils.RandomNumUtils;

import beans.ABCDetail;
import beans.DaLianDetail;
import beans.GongHangDetail;

public class ABCBankExcel {
	public void writeToExcel(String fileName, List<DaLianDetail> details,String cardNo,String userName,String printOrg, String printDate){   
        ExcelUtils excelUtils = new ExcelUtils(); 
        
        try{
	        //一共的数据行数
	        int totalDataRows = details.size();
	        //每页数据行数
	        int prePageNum = 41;
	        //表头行数
	        int headerRows = 10;
	        //每页总行数行数
	        int pageNum = headerRows + prePageNum+1;
	        //工作表中一共的页数
	        int pages = (totalDataRows%pageNum==0) ? totalDataRows%pageNum : totalDataRows/pageNum+1 ;
	        System.out.println("共有："+totalDataRows+" 行数据  "+"共有："+pages+" 页");
			
	        WritableWorkbook wwb = excelUtils.createWorkBook(fileName);   
	        //创建Excel工作表 指定名称和位置   
	        WritableSheet ws = wwb.createSheet("Test Sheet 1",0); 
	        
	        /***************设定每列的列宽******************/
	        ws.setColumnView(0, 9);
	        ws.setColumnView(1, 9);
	        ws.setColumnView(2, 9);
	        ws.setColumnView(3, 4);
	        ws.setColumnView(4, 8);
	        ws.setColumnView(5, 9);
	        ws.setColumnView(6, 9);
	        ws.setColumnView(7, 8);
	        ws.setColumnView(8, 9);
	        ws.setColumnView(9, 6);
	        ws.setColumnView(10, 8);
	        ws.setColumnView(11, 9);
	        
	        
	        
	        //**************设定所需要的字体*****************   
	        NumberFormat numFormat = new NumberFormat("#,##0.00;(#,##0.00)");
	        WritableFont wenVinFont = new WritableFont(WritableFont.createFont("wenvin"),12);
	        WritableCellFormat rightNumWenVinFormat = new WritableCellFormat(wenVinFont,numFormat);
	        WritableCellFormat   wcf   =   excelUtils.getCellFormat("宋体", 10); 
	        //wcf.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4)); 
	        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf.setAlignment(Alignment.CENTRE);
	        
	        WritableCellFormat   wcfLeft   =   excelUtils.getCellFormat("宋体", 10); 
	        //wcf.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4)); 
	        wcfLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcfLeft.setAlignment(Alignment.LEFT);
	        
	        WritableCellFormat   wcfRight   =   excelUtils.getCellFormat("宋体", 10); 
	        //wcf.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4)); 
	        wcfRight.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcfRight.setAlignment(Alignment.RIGHT);
	       
	       	WritableCellFormat wcfN = excelUtils.getCellFormat(NumberFormats.FORMAT3);
	       	//wcfN.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4));
	       	wcfN.setAlignment(Alignment.LEFT);
	       	wcfN.setVerticalAlignment(VerticalAlignment.CENTRE);
	       	
            WritableCellFormat   headerLeftFormat   =   excelUtils.getCellFormat("宋体", 10); 
            headerLeftFormat.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(3));
            headerLeftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            headerLeftFormat.setAlignment(Alignment.LEFT);
            
            WritableCellFormat   headerRightFormat   =   excelUtils.getCellFormat("宋体", 10); 
            headerRightFormat.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(3));
            headerRightFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            headerRightFormat.setAlignment(Alignment.RIGHT);
	        
            WritableCellFormat   headerFormat   =   excelUtils.getCellFormat("宋体", 10); 
            headerFormat.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(3));
            headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            headerFormat.setAlignment(Alignment.CENTRE);
            ws.setRowView(9, 400);
            
            WritableCellFormat   bottomFormat   =   excelUtils.getCellFormat("宋体", 10); 
            bottomFormat.setVerticalAlignment(VerticalAlignment.BOTTOM);
            bottomFormat.setAlignment(Alignment.LEFT);
            
            WritableCellFormat   rowFormat   =   excelUtils.getCellFormat("宋体", 10); 
            rowFormat.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(3));
            rowFormat.setBorder(jxl.format.Border.TOP,jxl.format.BorderLineStyle.getStyle(3));
            
	        jxl.write.NumberFormat nf = new jxl.write.NumberFormat("0.00");
	        WritableFont digFont = new WritableFont(WritableFont.createFont("宋体"), 10);
	        jxl.write.WritableCellFormat wcff = new jxl.write.WritableCellFormat(digFont,nf); 
	        //wcff.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4));
	        wcff.setVerticalAlignment(VerticalAlignment.CENTRE);
	        
            WritableCellFormat   topFormat   =   excelUtils.getCellFormat("宋体", 10); 
            topFormat.setVerticalAlignment(VerticalAlignment.TOP);
            topFormat.setAlignment(Alignment.LEFT);
	        
	        int inCount = 0;
	        float inTotal = 0;
	        int outCount = 0;
	        float outTotal = 0;
	        for ( int page=1; page<=pages; page++){
	        	//表头部分
	        	ws.addCell(excelUtils.getLabel(6,(page-1)*pageNum+0," 中 国 农 业 银 行 ",wcfLeft)); 
		        
		        ws.addCell(excelUtils.getLabel(6,(page-1)*pageNum+1,"金穗借记卡明细对账单",wcfLeft));    
		        
		        ws.setRowView((page-1)*pageNum+3, 350);
		        //String[] zhihangs = {"大连西岗区风光支行","大连风光支行"};
		        ws.addCell(excelUtils.getLabel(0,(page-1)*pageNum+3,"打印机构:大连西岗支行",topFormat));    
		        ws.addCell(excelUtils.getLabel(6,(page-1)*pageNum+3,"打印日期:"+printDate,topFormat));    
		        ws.addCell(excelUtils.getLabel(10,(page-1)*pageNum+3,"      页码:"+page,topFormat));    
		        //空行
	            ws.addCell(excelUtils.getLabel(0,(page-1)*pageNum+4," "+page,rowFormat));
		        ws.setRowView((page-1)*pageNum+4, 45);
		        ws.mergeCells(0, (page-1)*pageNum+4, 12, (page-1)*pageNum+4);
		        
		        ws.setRowView((page-1)*pageNum+5, 328);
		        ws.addCell(excelUtils.getLabel(0,(page-1)*pageNum+5,"姓名:"+userName,bottomFormat));    
		        ws.addCell(excelUtils.getLabel(5,(page-1)*pageNum+5,"卡号:"+cardNo,bottomFormat));    
		        ws.addCell(excelUtils.getLabel(8,(page-1)*pageNum+5,"     账户序号:0000",bottomFormat));    
		        ws.mergeCells(8, (page-1)*pageNum+5, 10, (page-1)*pageNum+5);
		        ws.addCell(excelUtils.getLabel(11,(page-1)*pageNum+5,"     币种:人民币",bottomFormat));    
		        
		        ws.addCell(excelUtils.getLabel(0,(page-1)*pageNum+6,"上期积分：0.00",wcfLeft));    
		        ws.addCell(excelUtils.getLabel(5,(page-1)*pageNum+6,"本期积分：0.00",wcfLeft));    
		        ws.addCell(excelUtils.getLabel(9,(page-1)*pageNum+6,"上期转入积分：0.00",wcfLeft));
		        
		        ws.setRowView((page-1)*pageNum+7, 328);
		        ws.addCell(excelUtils.getLabel(0,(page-1)*pageNum+7,"本期消费积分：0.00",topFormat)); 
		        ws.addCell(excelUtils.getLabel(5,(page-1)*pageNum+7,"本期奖励积分：0.00",topFormat)); 
		        ws.addCell(excelUtils.getLabel(9,(page-1)*pageNum+7,"本期调整积分：0.00",topFormat)); 
		        
		        //ws.mergeCells(3, 0, 4, 0);
		        
		        ws.addCell(excelUtils.getLabel(0, (page-1)*pageNum+8, "",rowFormat));
		        ws.setRowView((page-1)*pageNum+8, 45);
		        ws.mergeCells(0, (page-1)*pageNum+8, 12,(page-1)*pageNum+8);
		        
//	        	ws.addCell(excelUtils.getLabel(0, (page-1)*pageNum+5, "",rowFormat));
//	        	ws.mergeCells(0, (page-1)*pageNum+5, 9, (page-1)*pageNum+5);
	        	ws.setRowView((page-1)*pageNum+9, 350);
		        
		        ws.addCell(excelUtils.getLabel(0,(page-1)*pageNum+9,"日期",headerFormat));
		        ws.addCell(excelUtils.getLabel(1,(page-1)*pageNum+9,"地点",headerFormat));
		        ws.addCell(excelUtils.getLabel(4,(page-1)*pageNum+9,"",headerFormat));
		        ws.addCell(excelUtils.getLabel(5,(page-1)*pageNum+9,"",headerFormat));
		        ws.mergeCells(1, (page-1)*pageNum+9, 3, (page-1)*pageNum+9);
		        ws.addCell(excelUtils.getLabel(6,(page-1)*pageNum+9,"摘要",headerLeftFormat));
		        ws.addCell(excelUtils.getLabel(7,(page-1)*pageNum+9,"",headerFormat));
		        ws.addCell(excelUtils.getLabel(8,(page-1)*pageNum+9,"存入",headerFormat));
		        ws.addCell(excelUtils.getLabel(9,(page-1)*pageNum+9,"",headerFormat));
		        ws.addCell(excelUtils.getLabel(10,(page-1)*pageNum+9,"支出",headerRightFormat));
		        ws.addCell(excelUtils.getLabel(11,(page-1)*pageNum+9,"",headerFormat));
		        ws.addCell(excelUtils.getLabel(12,(page-1)*pageNum+9,"余额",headerFormat));
		        
	        		//数据部分
	        		int row = (page-1)*pageNum;
	                int startRow = row+10;
	                int lastDataSize = totalDataRows - (page-1)*prePageNum;
	                for (int data=0; data<prePageNum&&data<lastDataSize; data++){
	                	//取出当前页的数据集元素序号
	                	int index = 0;
	                	
	                	if ( data == 0){
	                		//第一行
	            	        WritableCellFormat   bom_wcf   =   excelUtils.getCellFormat("宋体", 10); 
	            	        //wcf.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4)); 
	            	        bom_wcf.setVerticalAlignment(VerticalAlignment.BOTTOM);
	            	        bom_wcf.setAlignment(Alignment.CENTRE);
	                		
	            	        jxl.write.WritableCellFormat bom_wcff = new jxl.write.WritableCellFormat(digFont,nf); 
	            	        //wcff.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4));
	            	        bom_wcff.setVerticalAlignment(VerticalAlignment.BOTTOM);
	            	        
	            	        WritableCellFormat   bom_wcfLeft   =   excelUtils.getCellFormat("宋体", 10); 
	            	        //wcf.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4)); 
	            	        bom_wcfLeft.setVerticalAlignment(VerticalAlignment.BOTTOM);
	            	        bom_wcfLeft.setAlignment(Alignment.LEFT);
	                		
	                		index = (page-1)*prePageNum+data;
	                		ws.setRowView(startRow+data, 350);
	                		DaLianDetail detail = details.get(index);
		                	int column = 0;
				        	ws.addCell(excelUtils.getLabel(column++,startRow+data,String.valueOf(detail.getDate().toString(DateTimeFormat.forPattern("yyyyMMdd"))),bom_wcf));
				        	ws.addCell(excelUtils.getLabel(column++,startRow+data,detail.getSubject(),bom_wcfLeft));
				        	ws.mergeCells(column-1, startRow+data, column+2, startRow+data);
				        	column++;column++;column++;column++;
				        	ws.addCell(excelUtils.getLabel(column++,startRow+data,String.valueOf(detail.getOperation()),bom_wcfLeft));
			        		
			        		if ( detail.getIn() != 0){
			        			column++;
			        			jxl.write.Number nIn = new jxl.write.Number(column++,startRow+data,detail.getIn(), bom_wcff); 
			        			ws.addCell(nIn);
			        			column++;
			        			column++;
			        			
			        			inCount++;
			        			inTotal += detail.getIn();
			        		}else{
			        			column++;
			        			column++;
			        			column++;
			        			jxl.write.Number nOut = new jxl.write.Number(column++,startRow+data,detail.getOut(), bom_wcff); 
			        			ws.addCell(nOut);
			        			outCount++;
			        			outTotal += detail.getOut();
			        		}
			        		column++;
			        			jxl.write.Number amount = new jxl.write.Number(column++,startRow+data,detail.getAmount(), bom_wcff); 
			        			ws.addCell(amount);
	                	}
	                	else if ( data == prePageNum-1){
	            	        WritableCellFormat   top_wcf   =   excelUtils.getCellFormat("宋体", 10); 
	            	        //wcf.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4)); 
	            	        top_wcf.setVerticalAlignment(VerticalAlignment.TOP);
	            	        top_wcf.setAlignment(Alignment.CENTRE);
	                		
	            	        jxl.write.WritableCellFormat top_wcff = new jxl.write.WritableCellFormat(digFont,nf); 
	            	        //wcff.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4));
	            	        top_wcff.setVerticalAlignment(VerticalAlignment.TOP);
	            	        
	            	        WritableCellFormat   top_wcfLeft   =   excelUtils.getCellFormat("宋体", 10); 
	            	        //wcf.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.getStyle(4)); 
	            	        top_wcfLeft.setVerticalAlignment(VerticalAlignment.TOP);
	            	        top_wcfLeft.setAlignment(Alignment.LEFT);
	            	        ws.setRowView(startRow+data, 350);
	                		//最后一行
	                		index = (page-1)*prePageNum+data-1;
	                		DaLianDetail detail = details.get(index);
	                		int column = 0;
	                		ws.addCell(excelUtils.getLabel(column++,startRow+data,String.valueOf(detail.getDate().toString(DateTimeFormat.forPattern("yyyyMMdd"))),top_wcf));
	                		ws.addCell(excelUtils.getLabel(column++,startRow+data,detail.getSubject(),top_wcfLeft));
	                		ws.mergeCells(column-1, startRow+data, column+2, startRow+data);
	                		column++;column++;column++;column++;
	                		ws.addCell(excelUtils.getLabel(column++,startRow+data,String.valueOf(detail.getOperation()),top_wcfLeft));
	                		
	                		if ( detail.getIn() != 0){
	                			column++;
	                			jxl.write.Number nIn = new jxl.write.Number(column++,startRow+data,detail.getIn(), top_wcff); 
	                			ws.addCell(nIn);
	                			column++;
	                			column++;
	                			
	                			inCount++;
	                			inTotal += detail.getIn();
	                		}else{
	                			column++;
	                			column++;
	                			column++;
	                			jxl.write.Number nOut = new jxl.write.Number(column++,startRow+data,detail.getOut(), top_wcff); 
	                			ws.addCell(nOut);
	                			outCount++;
	                			outTotal += detail.getOut();
	                		}
	                		column++;
	                		if (data != 0){
	                			Formula f = new Formula(column++, startRow+data, 
	                					"M"+String.valueOf(startRow+data)+"+I"+String.valueOf(startRow+data+1)+"-K"+String.valueOf(startRow+data+1)
	                					,top_wcff
	                					); 
	                			ws.addCell(f);
	                		}else{
	                			jxl.write.Number amount = new jxl.write.Number(column++,startRow+data,detail.getAmount(), top_wcff); 
	                			ws.addCell(amount);
	                		}
	                		
	                	}
	                	else{
	                		//其他行
	                		index = (page-1)*prePageNum+data-1;
	                		DaLianDetail detail = details.get(index);
	                		int column = 0;
	                		ws.addCell(excelUtils.getLabel(column++,startRow+data,String.valueOf(detail.getDate().toString(DateTimeFormat.forPattern("yyyyMMdd"))),wcf));
	                		ws.addCell(excelUtils.getLabel(column++,startRow+data,detail.getSubject(),wcfLeft));
	                		ws.mergeCells(column-1, startRow+data, column+2, startRow+data);
	                		column++;column++;column++;column++;
	                		ws.addCell(excelUtils.getLabel(column++,startRow+data,String.valueOf(detail.getOperation()),wcfLeft));
	                		
	                		if ( detail.getIn() != 0){
	                			column++;
	                			jxl.write.Number nIn = new jxl.write.Number(column++,startRow+data,detail.getIn(), wcff); 
	                			ws.addCell(nIn);
	                			column++;
	                			column++;
	                			
	                			inCount++;
	                			inTotal += detail.getIn();
	                		}else{
	                			column++;
	                			column++;
	                			column++;
	                			jxl.write.Number nOut = new jxl.write.Number(column++,startRow+data,detail.getOut(), wcff); 
	                			ws.addCell(nOut);
	                			outCount++;
	                			outTotal += detail.getOut();
	                		}
	                		column++;
	                		if (data != 0){
	                			Formula f = new Formula(column++, startRow+data, 
	                					"M"+String.valueOf(startRow+data)+"+I"+String.valueOf(startRow+data+1)+"-K"+String.valueOf(startRow+data+1)
	                					,wcff
	                					); 
	                			ws.addCell(f);
	                		}else{
	                			jxl.write.Number amount = new jxl.write.Number(column++,startRow+data,detail.getAmount(), wcff); 
	                			ws.addCell(amount);
	                		}
	                	}
	                	
	                }
	                if (page != pages){
	                	ws.addCell(excelUtils.getLabel(0,page*pageNum-1,"", rowFormat));
	                	ws.setRowView(page*pageNum-1, 45);
	                	ws.mergeCells(0, page*pageNum-1, 12,page*pageNum-1);
	                	ws.addRowPageBreak(page*pageNum);
	                }
	        	}
	        
	        //得到最后一行记录的Row位置 = 页数×表头数+数据总数+分页数
	        int lastRow = pages*headerRows+details.size()+pages-1;
	        ws.addCell(excelUtils.getLabel(0, lastRow, "",rowFormat));
	        ws.setRowView(lastRow, 45);
	        ws.mergeCells(0, lastRow, 12, lastRow);
	        
	        //总数
	        ws.addCell(excelUtils.getLabel(7, lastRow+1, "支出笔数:"+String.valueOf(outCount),wcff));
	        ws.addCell(excelUtils.getLabel(9, lastRow+1, " 合计金额:"+outTotal,wcff));
			ws.mergeCells(9,  lastRow+1, 11,  lastRow+1);
			
			ws.addCell(excelUtils.getLabel(7, lastRow+2, "存入笔数:"+String.valueOf(inCount),wcff));
			ws.addCell(excelUtils.getLabel(9, lastRow+2, " 合计金额:"+inTotal,wcff));
			ws.mergeCells(9,  lastRow+2, 11,  lastRow+2);
	        //写入工作表   
	        wwb.write();   
	        wwb.close();   
	        System.out.println("写入完成！");
		}catch(Exception e){
			e.printStackTrace();
		}
    }   
}