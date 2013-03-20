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

public class GongHangExcel {
	public void writeToExcel(String fileName, List<GongHangDetail> details,String cardNo,String userName,String oper, String printDate){   
        ExcelUtils excelUtils = new ExcelUtils(); 
		try{
			
	        //一共的数据行数
	        int totalDataRows = details.size();
	        //每页数据行数
	        int prePageNum = 50;
	        //表头行数
	        int headerRows = 6;
	        //每页总行数行数
	        int pageNum = headerRows + prePageNum;
	        //工作表中一共的页数
	        int pages = (totalDataRows%pageNum==0) ? totalDataRows%pageNum : totalDataRows/pageNum+1 ;
	        System.out.println("共有："+totalDataRows+" 行数据  "+"共有："+pages+" 页");
			
	        WritableWorkbook wwb = excelUtils.createWorkBook(fileName);   
	        //创建Excel工作表 指定名称和位置   
	        WritableSheet ws = wwb.createSheet("Test Sheet 1",0); 
	        
	        /***************设定每列的列宽******************/
	        ws.setColumnView(0, 4);
	        ws.setColumnView(1, 6);
	        ws.setColumnView(2, 16);
	        ws.setColumnView(3, 12);
	        ws.setColumnView(4, 10);
	        ws.setColumnView(5, 7);
	        ws.setColumnView(6, 10);
	        ws.setColumnView(7, 11);
	        ws.setColumnView(8, 11);
	        ws.setColumnView(9, 7);
	        ws.setColumnView(10, 10);
	        
	        //**************设定所需要的字体*****************   
	        NumberFormat numFormat = new NumberFormat("#,##0.00;(#,##0.00)");
	        WritableFont wenVinFont = new WritableFont(WritableFont.createFont("wenvin"),12);
	        WritableCellFormat rightNumWenVinFormat = new WritableCellFormat(wenVinFont,numFormat);
	        WritableCellFormat leftWenVinFormat12 = excelUtils.getCellFormat("wenvin", 12, "left");
	        WritableCellFormat leftWenVinFormat10 = excelUtils.getCellFormat("wenvin", 10, "left");
	        WritableCellFormat centerWenVinFormat12 = excelUtils.getCellFormat("wenvin", 12, "center");
	        WritableCellFormat centerWenVinFormat10 = excelUtils.getCellFormat("wenvin", 10, "center");
	        WritableCellFormat leftFangZFormat = excelUtils.getCellFormat("方正宋三简体", 10, "left");
	        
            WritableCellFormat   topFormat   =   excelUtils.getCellFormat("方正宋三简体", 10); 
            topFormat.setVerticalAlignment(VerticalAlignment.TOP);
            topFormat.setAlignment(Alignment.LEFT);
	        
	        for ( int page=1; page<=pages; page++){
	        	//表头部分
	        	ws.addRowPageBreak((page-1)*pageNum-1);
	        	ws.addCell(excelUtils.getLabel(3,(page-1)*pageNum+0," 牡丹灵通卡帐户历史明细清单",excelUtils.getCellFormat("方正宋三简体", 10, "left"))); 
	        	ws.addCell(excelUtils.getLabel(0,(page-1)*pageNum+1," 卡号:6222083400002511091   户名：谢亚光                                                             起始日期:"+printDate+" 截止日期:"+printDate,leftFangZFormat));    
	        	ws.mergeCells(0, (page-1)*pageNum+1, 10, (page-1)*pageNum+1);    
	        	ws.addCell(excelUtils.getLabel(0,(page-1)*pageNum+2," 操作地区：3400   操作网点：2011       操作柜员：05152   授权柜员号：00000 ",leftFangZFormat ));    
	        	ws.mergeCells(0, (page-1)*pageNum+2, 10, (page-1)*pageNum+2);    
	        	ws.addCell(excelUtils.getLabel(0,(page-1)*pageNum+3,"  工作日期               账号                     应用号 序号        币种  钞汇   交易代码  注释           借贷                  发生额                            余额",leftFangZFormat));    
	        	ws.mergeCells(0, (page-1)*pageNum+3, 10, (page-1)*pageNum+3);    
	        	ws.addCell(excelUtils.getLabel(0,(page-1)*pageNum+4," 存期  约转期  通知种类/发行代码    利息                   利息税                         起息日            止息日       地区号  网点号  操作员   界面",topFormat));    
	        	ws.setRowView((page-1)*pageNum+4, 180*2);
	        	ws.mergeCells(0, (page-1)*pageNum+4, 10, (page-1)*pageNum+4); 
	        	//虚线部分
	        	WritableCellFormat   rowFormat   =   excelUtils.getCellFormat("wenvin", 10); 
	        	rowFormat.setBorder(jxl.format.Border.TOP,jxl.format.BorderLineStyle.getStyle(3));
	        	ws.addCell(excelUtils.getLabel(0, (page-1)*pageNum+5, "",rowFormat));
	        	ws.mergeCells(0, (page-1)*pageNum+5, 10, (page-1)*pageNum+5);
	        	ws.setRowView((page-1)*pageNum+5, 100);
	        		//数据部分
	        		int row = (page-1)*pageNum;
	                int startRow = row+6;
	                int lastDataSize = totalDataRows - (page-1)*prePageNum;
	                for (int data=0; data<prePageNum&&data<lastDataSize; data+=2){
	                	//取出当前页的数据集元素序号
	                	int index = 0;
	                	
	                	if ( data == 0){
	                		index = (page-1)*prePageNum+data;
	                	}else{
	                		index = (page-1)*prePageNum+data-1;
	                	}
	                	
	                	GongHangDetail detail = details.get(index);
	                	int column = 0;
	                	//本页第一行数据
	                		//工作日期
	                		ws.addCell(excelUtils.getLabel(column++,startRow+data,String.valueOf(detail.getDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd"))),leftWenVinFormat12));
	                		column++;
	                		ws.mergeCells(0, startRow+data, 1, startRow+data);
	                		//帐号
	                		ws.addCell(excelUtils.getLabel(column++,startRow+data,"3400072901003194240",leftWenVinFormat12));
	                		//应用号序号
	                		ws.addCell(excelUtils.getLabel(column++,startRow+data," 1         0",leftWenVinFormat12));
	                		//币种钞汇
	                		ws.addCell(excelUtils.getLabel(column++,startRow+data,"RMB       钞",centerWenVinFormat10));
	                		//交易代码
	                		ws.addCell(excelUtils.getLabel(column++,startRow+data,detail.getSubject().split(" ")[0],centerWenVinFormat12));
	                		//注释
	                		ws.addCell(excelUtils.getLabel(column++,startRow+data,detail.getSubject().split(" ")[1],leftFangZFormat));
	                		//借贷
	                		ws.addCell(excelUtils.getLabel(column++,startRow+data,detail.getOperation(),leftFangZFormat));
	                		//发生额
	                		if (detail.getOperation().equals("借")){
	                			jxl.write.Number out = new jxl.write.Number(column++,startRow+data,detail.getOut(),rightNumWenVinFormat);
	                			ws.addCell(out);
	                		}else{
	                			jxl.write.Number in = new jxl.write.Number(column++,startRow+data,detail.getIn(),rightNumWenVinFormat);
	                			ws.addCell(in);
	                		}
	                		column++;
	                		//余额
	                		if ( data == 0 ){
	                			jxl.write.Number amount = new jxl.write.Number(column++,startRow+data,detail.getAmount(),rightNumWenVinFormat);
	                			ws.addCell(amount);
	                		}else{
	                			Formula f = new Formula(column++, startRow+data, 
	                					"IF(EXACT(H"+String.valueOf(startRow+data+1)+",\"借\")" +
	                							",K"+String.valueOf(startRow+data-1)+"-I"+String.valueOf(startRow+data+1)+
	                							",K"+String.valueOf(startRow+data-1)+"+I"+String.valueOf(startRow+data+1)+")"
	        	        				,rightNumWenVinFormat
	        	        				); 
	        	        		ws.addCell(f);
	                		}
                			//换行
                			column = 0 ;
                			//存期 约转期
                			ws.addCell(excelUtils.getLabel(column++,startRow+data+1," 000",leftWenVinFormat12));
                			ws.addCell(excelUtils.getLabel(column++,startRow+data+1,"不转存",leftFangZFormat));
                			ws.addCell(excelUtils.getLabel(column++,startRow+data+1,"        0                    0.00",leftWenVinFormat12));
                			column++;
                			//利息税
                			ws.addCell(excelUtils.getLabel(column++,startRow+data+1,"    0.00",leftWenVinFormat12));
                			//起息日
                			column++;
                			ws.addCell(excelUtils.getLabel(column++,startRow+data+1,String.valueOf(detail.getDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd"))),leftWenVinFormat12));
                			//止息日
                			ws.addCell(excelUtils.getLabel(column++,startRow+data+1,"9999-12-31",leftWenVinFormat12));
                			//地区号 网点号
                			ws.addCell(excelUtils.getLabel(column++,startRow+data+1,detail.getRecorder(),leftWenVinFormat12));
                			//操作员
                			ws.addCell(excelUtils.getLabel(column++,startRow+data+1,"  "+detail.getSendOrg().split(" ")[0],leftWenVinFormat12));
                			//界面
                			ws.addCell(excelUtils.getLabel(column++,startRow+data+1," "+detail.getSendOrg().split(" ")[1],leftFangZFormat));
                			ws.setRowView(startRow+data, 123*2);
                			ws.setRowView(startRow+data+1, 123*2);
	                }
	                if (page != pages){
	                	ws.addRowPageBreak(page*pageNum);
	                }
	        	}
	        
	        //写入工作表   
	        wwb.write();   
	        wwb.close();   
	        System.out.println("写入完成！");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
    }   
}