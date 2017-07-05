package com.anz.eventplanner.viewresolver;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.anz.eventplanner.model.Child;
import com.anz.eventplanner.model.Event;
import com.anz.eventplanner.model.EventOrganiser;
import com.anz.eventplanner.model.Participant;

@SuppressWarnings("deprecation")
public class EventExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Event event = (Event) model.get("event");

		if (event != null) {
			buildEventSheet(event, workbook);
			buildOrganiserSheet(event, workbook);
			buildParticipantSheet(event, workbook);
			buildChildrenSheet(event, workbook);
		}

	}

	private void buildEventSheet(Event event, HSSFWorkbook workbook) {

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

		for (int i = 0; i < colCount; i++) {
			sheet.autoSizeColumn(i, true);
		}		
	}

	private void buildOrganiserSheet(Event event, HSSFWorkbook workbook) {

		Sheet sheet = (Sheet) workbook.createSheet("Organisers");
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
		cell.setCellValue("LAN ID");

		cell = row.createCell(colCount++);
		cell.setCellStyle(style);
		cell.setCellValue("Name");

		cell = row.createCell(colCount++);
		cell.setCellStyle(style);
		cell.setCellValue("Category");

		for (EventOrganiser eo : event.getAssociatedOrganisers()) {
			row = sheet.createRow(rowCount++);
			colCount = 0;
			row.createCell(colCount++).setCellValue(eo.getLANId());
			row.createCell(colCount++).setCellValue(eo.getOrganiserName());
			row.createCell(colCount++).setCellValue(eo.getCategory());
		}

		for (int i = 0; i < colCount; i++) {
			sheet.autoSizeColumn(i, true);
		}		
		
	}
	
	private void buildParticipantSheet(Event event, HSSFWorkbook workbook) {

		Sheet sheet = (Sheet) workbook.createSheet("Participants");
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
		cell.setCellValue("LAN ID");

		cell = row.createCell(colCount++);
		cell.setCellStyle(style);
		cell.setCellValue("Location");

		cell = row.createCell(colCount++);
		cell.setCellStyle(style);
		cell.setCellValue("Level");
		
		cell = row.createCell(colCount++);
		cell.setCellStyle(style);
		cell.setCellValue("Registration status");

		for (Participant p: event.getAssociatedParticipants()) {
			row = sheet.createRow(rowCount++);
			colCount = 0;
			row.createCell(colCount++).setCellValue(p.getLANId());
			row.createCell(colCount++).setCellValue(p.getLevel());
			row.createCell(colCount++).setCellValue(p.getLocation());
			row.createCell(colCount++).setCellValue(p.getRegistrationStatus());
		}

		for (int i = 0; i < colCount; i++) {
			sheet.autoSizeColumn(i, true);
		}		
		
	}

	private void buildChildrenSheet(Event event, HSSFWorkbook workbook) {

		Sheet sheet = (Sheet) workbook.createSheet("Children");
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
		cell.setCellValue("Parent LAN ID");

		cell = row.createCell(colCount++);
		cell.setCellStyle(style);
		cell.setCellValue("Name");

		cell = row.createCell(colCount++);
		cell.setCellStyle(style);
		cell.setCellValue("Gender");
		
		cell = row.createCell(colCount++);
		cell.setCellStyle(style);
		cell.setCellValue("Age");
		
		cell = row.createCell(colCount++);
		cell.setCellStyle(style);
		cell.setCellValue("Has Dietary Requirements");
		
		cell = row.createCell(colCount++);
		cell.setCellStyle(style);
		cell.setCellValue("Food preference");
		
		cell = row.createCell(colCount++);
		cell.setCellStyle(style);
		cell.setCellValue("Is Allergic");

		cell = row.createCell(colCount++);
		cell.setCellStyle(style);
		cell.setCellValue("Other Details");		
				
		for (Participant p: event.getAssociatedParticipants()) {
			Set<Child> children = new HashSet<Child>();
			children.addAll(p.getChildren());
			for (Child c: children) {
				row = sheet.createRow(rowCount++);
				colCount = 0;
				row.createCell(colCount++).setCellValue(p.getLANId());
				row.createCell(colCount++).setCellValue(c.getChildName());
				row.createCell(colCount++).setCellValue(c.getChildGender());
				row.createCell(colCount++).setCellValue(c.getChildAge());
				row.createCell(colCount++).setCellValue(c.getHasDietRequirement());
				row.createCell(colCount++).setCellValue(c.getChildFoodPreference());
				row.createCell(colCount++).setCellValue(c.getIsChildAllergic());
				row.createCell(colCount++).setCellValue(c.getOtherDetails());
			}			
		}

		for (int i = 0; i < colCount; i++) {
			sheet.autoSizeColumn(i, true);
		}		
		
	}
	
}
