package com.oomlife.headhunter.report;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.ParseException;

/**
 * To parse each headhunter's daily report
 * Created by yangshuan on 15/5/19.
 */
public class ReportParser {
    public ReportBean parse(File reportFile) throws IOException{
        System.out.println("Parsing data from " + reportFile.getName());
        InputStream inputStream = new FileInputStream(reportFile);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        ReportBean reportBean = new ReportBean();
        try {
            reportBean.setReportDate(sheet.getRow(0).getCell(0).getStringCellValue());
        } catch (ParseException e) {
            throw new ReportException(e);
        }
        Row row3 = sheet.getRow(2);
        reportBean.setNewChannel(row3.getCell(1).getStringCellValue());
        reportBean.setExistingChannel(row3.getCell(2).getStringCellValue());
        reportBean.setPhoneNumber(Double.valueOf(row3.getCell(3).getNumericCellValue()).intValue());
        reportBean.setValidPhoneNumber(Double.valueOf(row3.getCell(4).getNumericCellValue()).intValue());
        reportBean.setValidResourceNumber(Double.valueOf(row3.getCell(5).getNumericCellValue()).intValue());
        reportBean.setInterviewAppointmentNumber(Double.valueOf(row3.getCell(6).getNumericCellValue()).intValue());
        reportBean.setInterviewPresentNumber(Double.valueOf(row3.getCell(7).getNumericCellValue()).intValue());

        reportBean.setOtherTask(sheet.getRow(4).getCell(1).getStringCellValue());
        reportBean.setIssue(sheet.getRow(6).getCell(1).getStringCellValue());
        reportBean.setIssueSolution(sheet.getRow(7).getCell(1).getStringCellValue());
        return reportBean;
    }
}
