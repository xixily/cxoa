package com.chaoxing.oa.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class TestExcel {
	
//public static void main(String[] args) {
//	 Workbook wb = new XSSFWorkbook();
//
//     CreationHelper factory = wb.getCreationHelper();
//
//     Sheet sheet = wb.createSheet();
//
//     Cell cell1 = sheet.createRow(3).createCell(5);
//     cell1.setCellValue("F4");
//
//     Drawing drawing = sheet.createDrawingPatriarch();
//
//     ClientAnchor anchor = factory.createClientAnchor();
//
//     Comment comment1 = drawing.createCellComment(anchor);
//     RichTextString str1 = factory.createRichTextString("Hello, World!");
//     comment1.setString(str1);
//     comment1.setAuthor("Apache POI");
//     cell1.setCellComment(comment1);
//
//     Cell cell2 = sheet.createRow(2).createCell(2);
//     cell2.setCellValue("C3");
//
//     Comment comment2 = drawing.createCellComment(anchor);
//     RichTextString str2 = factory.createRichTextString("XSSF can set cell comments");
//     //apply custom font to the text in the comment
//     Font font = wb.createFont();
//     font.setFontName("Arial");
//     font.setFontHeightInPoints((short)14);
//     font.setBoldweight(Font.BOLDWEIGHT_BOLD);
//     font.setColor(IndexedColors.RED.getIndex());
//     str2.applyFont(font);
//
//     comment2.setString(str2);
//     comment2.setAuthor("Apache POI");
////     comment2.setAddress(new CellAddress("C3"));
//
//     String fname = "D://comments.xlsx";
//     FileOutputStream out = null;
//	try {
//		out = new FileOutputStream(fname);
//	} catch (FileNotFoundException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//     try {
//		wb.write(out);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//     try {
//		out.close();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//
//     try {
//		wb.close();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	}
	public static void main(String[] args) {
		UUIDGenerator uuidUtil = new UUIDGenerator();
		String dir = "D:/temp/excelDir/";
		File directory = new File(dir);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String date= format.format(new Date());
		String fileName = "export"+date+".xlsx";//fileDesc 是文件名
		String trueName=uuidUtil.generate()+".xlsx";
		String path=dir+ date +trueName;
		File file = new File(path);
//		file.createNewFile();//创建UUID生成的.xlsx文件
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
//			out = new FileOutputStream(new File("D://result2.xlsx"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  
        Workbook writeWB = new SXSSFWorkbook(500);  
        Sheet writeSheet = writeWB.createSheet();  
        //4.创建单元格样式  
        CellStyle cellStyle =writeWB.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        long start = System.currentTimeMillis(); // 记录起始时间
        for (int rr = 0; rr < 40; rr++) {  
            Row writeRow = writeSheet.createRow(rr);  
            for (int cc = 0; cc < 10; cc++) {  
                writeRow.createCell(cc).setCellValue("测试" + rr + "," + cc);  
            }  
        }  
        try {
			writeWB.write(out);
			long end = System.currentTimeMillis();  
			if((end - start)<1000){
				System.out.println("运行了" + (end - start) + "毫秒");
			}
			System.out.println("运行了" + (end - start)/1000 + "秒");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
//	 public static void main(String[]args) throws Exception {
//	        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();
//	        Sheet sheet = wb.createSheet("first-header - format sheet");
//	        sheet.createRow(0).createCell(0).setCellValue(123);
//
//	        //set page numbers in the footer
//	        Footer footer = sheet.getFooter();
//	        //&P == current page number
//	        //&N == page numbers
//	        footer.setRight("Page &P of &N");
//
//	        
//	        Header firstHeader=((XSSFSheet)sheet).getFirstHeader();
//	        //&F == workbook file name
//	        firstHeader.setLeft("&F ......... first header");
//	        
//	        for(int i=0;i<100;i=i+10){
//	            sheet.createRow(i).createCell(0).setCellValue(123);
//	        }
//	        
//	        
//	        XSSFSheet sheet2 = (XSSFSheet)wb.createSheet("odd header-even footer");
//	        Header oddHeader=sheet2.getOddHeader();
//	        //&B == bold
//	        //&E == double underline
//	        //&D == date
//	        oddHeader.setCenter("&B &E oddHeader     &D ");
//	        
//	        Footer evenFooter=sheet2.getEvenFooter();
//	        evenFooter.setRight("even footer &P");
//	        sheet2.createRow(10).createCell(0).setCellValue("Second sheet with an oddHeader and an evenFooter");
//
//	        for(int i=0;i<200;i=i+10){
//	            sheet2.createRow(i).createCell(0).setCellValue(123);
//	        }
//	        
//	        XSSFSheet sheet3 = (XSSFSheet)wb.createSheet("odd header- odd footer");
//	        sheet3.createRow(10).createCell(0).setCellValue("Third sheet with oddHeader and oddFooter");
//	        Header oddH=sheet3.getOddHeader();
//	        //&C == centered
//	        oddH.setCenter("centered oddHeader");
//	        oddH.setLeft("left ");
//	        oddH.setRight("right ");
//	        
//	        Footer oddF=sheet3.getOddFooter();
//	        oddF.setLeft("Page &P");
//	        oddF.setRight("Pages &N ");
//	        
//	        FileOutputStream fileOut = new FileOutputStream("D://headerFooter.xlsx");
//	        wb.write(fileOut);
//	        fileOut.close();
//
//	    }
}