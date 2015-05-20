package com.oomlife.headhunter.report;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is the main bean for report
 * Created by yangshuan on 15/5/19.
 */
public class ReportBean {
    private DateFormat format = new SimpleDateFormat("y.M.d");

    private Date reportDate;
    private String newChannel;
    private String existingChannel;
    private int phoneNumber;
    private int validPhoneNumber;
    private int validResourceNumber;
    private int interviewAppointmentNumber;
    private int interviewPresentNumber;
    private String otherTask;
    private String issue;
    private String issueSolution;

    public String getReportDate() {
        return format.format(reportDate);
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public void setReportDate(String reportDate) throws ParseException {
        this.reportDate = format.parse(reportDate);
    }

    public String getNewChannel() {
        return newChannel;
    }

    public void setNewChannel(String newChannel) {
        this.newChannel = newChannel;
    }

    public String getExistingChannel() {
        return existingChannel;
    }

    public void setExistingChannel(String existingChannel) {
        this.existingChannel = existingChannel;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getValidPhoneNumber() {
        return validPhoneNumber;
    }

    public void setValidPhoneNumber(int validPhoneNumber) {
        this.validPhoneNumber = validPhoneNumber;
    }

    public int getValidResourceNumber() {
        return validResourceNumber;
    }

    public void setValidResourceNumber(int validResourceNumber) {
        this.validResourceNumber = validResourceNumber;
    }

    public int getInterviewAppointmentNumber() {
        return interviewAppointmentNumber;
    }

    public void setInterviewAppointmentNumber(int interviewAppointmentNumber) {
        this.interviewAppointmentNumber = interviewAppointmentNumber;
    }

    public int getInterviewPresentNumber() {
        return interviewPresentNumber;
    }

    public void setInterviewPresentNumber(int interviewPresentNumber) {
        this.interviewPresentNumber = interviewPresentNumber;
    }

    public String getOtherTask() {
        return otherTask;
    }

    public void setOtherTask(String otherTask) {
        this.otherTask = otherTask;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getIssueSolution() {
        return issueSolution;
    }

    public void setIssueSolution(String issueSolution) {
        this.issueSolution = issueSolution;
    }

    @Override
    public String toString() {
        return "ReportBean{" +
                "reportDate=" + format.format(reportDate) +
                ", newChannel='" + newChannel + '\'' +
                ", existingChannel='" + existingChannel + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", validPhoneNumber=" + validPhoneNumber +
                ", validResourceNumber=" + validResourceNumber +
                ", interviewAppointmentNumber=" + interviewAppointmentNumber +
                ", interviewPresentNumber=" + interviewPresentNumber +
                ", otherTask='" + otherTask + '\'' +
                ", issue='" + issue + '\'' +
                ", issueSolution='" + issueSolution + '\'' +
                '}';
    }
}
