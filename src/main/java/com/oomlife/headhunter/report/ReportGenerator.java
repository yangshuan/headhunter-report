package com.oomlife.headhunter.report;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * To generate the aggregated report
 * Created by yangshuan on 15/5/19.
 */
public class ReportGenerator {
    public void generate(ReportBean reportBean) throws IOException {
        InputStream inputStream = new FileInputStream("template.xlsx");
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        sheet.getRow(0).getCell(0).setCellValue(reportBean.getReportDate());
        Row row3 = sheet.getRow(2);
        row3.getCell(1).setCellValue(reportBean.getNewChannel());
        row3.getCell(2).setCellValue(reportBean.getExistingChannel());
        row3.getCell(3).setCellValue(reportBean.getPhoneNumber());
        row3.getCell(4).setCellValue(reportBean.getValidPhoneNumber());
        row3.getCell(5).setCellValue(reportBean.getValidResourceNumber());
        row3.getCell(6).setCellValue(reportBean.getInterviewAppointmentNumber());
        row3.getCell(7).setCellValue(reportBean.getInterviewPresentNumber());

        sheet.getRow(4).getCell(1).setCellValue(reportBean.getOtherTask());
        sheet.getRow(6).getCell(1).setCellValue(reportBean.getIssue());
        sheet.getRow(7).getCell(1).setCellValue(reportBean.getIssueSolution());

        OutputStream result = new FileOutputStream("result.xlsx");
        System.out.println(System.getProperty("user.dir") + "/result.xlsx");
        workbook.write(result);
    }
}
