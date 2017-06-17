package com.anz.eventplanner.viewresolver;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.anz.eventplanner.model.Event;

@SuppressWarnings("deprecation")
public class EventExcelView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Event event = (Event) model.get("event");
		
		if(event != null){
			Sheet sheet = (Sheet) workbook.createSheet("Event Details");
	        CellStyle style = (CellStyle) workbook.createCellStyle();
	        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
	        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        style.setAlignment(CellStyle.ALIGN_LEFT);
	        Row row = null;
	        Cell cell = null;
	        int rowCount = 0;
	        int colCount = 0;
	 
	        // Create header cells
	        row = sheet.createRow(rowCount++); 
	        cell = row.createCell(colCount++);
	        cell.setCellStyle(style);
	        cell.setCellValue("Name");
	        row.createCell(colCount++).setCellValue(event.getEventName());
	        
	        row = sheet.createRow(rowCount++);
	        colCount = 0;
	        cell = row.createCell(colCount++);
	        cell.setCellStyle(style);
	        cell.setCellValue("Description");
	        row.createCell(colCount++).setCellValue(event.getEventDescription());
	 
	        row = sheet.createRow(rowCount++);
	        colCount = 0;        
	        cell = row.createCell(colCount++);
	        cell.setCellStyle(style);
	        cell.setCellValue("Planned Date");
	        row.createCell(colCount++).setCellValue(event.getEventPlannedDate().toString("dd/MM/yyyy"));
	 
	        row = sheet.createRow(rowCount++);
	        colCount = 0;
	        cell = row.createCell(colCount++);
	        cell.setCellStyle(style);
	        cell.setCellValue("Location");
	        row.createCell(colCount++).setCellValue(event.getEventLocation());
	        
	        row = sheet.createRow(rowCount++);
	        colCount = 0;
	        cell = row.createCell(colCount++);
	        cell.setCellStyle(style);
	        cell.setCellValue("Branch");
	        row.createCell(colCount++).setCellValue(event.getEventBranch());
	        
	        row = sheet.createRow(rowCount++);
	        colCount = 0;
	        cell = row.createCell(colCount++);
	        cell.setCellStyle(style);
	        cell.setCellValue("Audience");
	        row.createCell(colCount++).setCellValue(event.getTargetAudience());
	        
	        row = sheet.createRow(rowCount++);
	        colCount = 0;
	        cell = row.createCell(colCount++);
	        cell.setCellStyle(style);
	        cell.setCellValue("Max Participants");
	        row.createCell(colCount++).setCellValue(event.getMaxParticipants());
	        
	        row = sheet.createRow(rowCount++);
	        colCount = 0;
	        cell = row.createCell(colCount++);
	        cell.setCellStyle(style);
	        cell.setCellValue("Registered Participants");
	        row.createCell(colCount++).setCellValue(event.getRegisteredParticipants());   
	 
	        for (int i = 0; i < colCount; i++){
	        	sheet.autoSizeColumn(i, true);
	        }            			
		}		
 
    }	

}
