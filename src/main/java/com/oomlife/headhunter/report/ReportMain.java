package com.oomlife.headhunter.report;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This is the main of this project.
 * Created by yangshuan on 15/5/19.
 */
public class ReportMain {
    private static final String TODAY = new SimpleDateFormat("y.M.d").format(new Date());
    private static final String REPORT_REGEX = "^" + TODAY + "\\-日报表\\-.+$";

    private final File reportFolder;

    private ReportParser parser = new ReportParser();
    private ReportGenerator generator = new ReportGenerator();

    public ReportMain(String reportPath) {
        reportFolder = new File(reportPath);
        if (!reportFolder.isDirectory()) {
            throw new ReportException(reportPath + " should be a folder.");
        }
    }

    private void report() throws IOException {
        File[] reportFolders = reportFolder.listFiles();
        List<ReportBean> reportBeans = new ArrayList<ReportBean>(reportFolders.length);
        for (File resourceFolder : reportFolder.listFiles()) {
            if (!resourceFolder.isDirectory()) {
                continue;
            }

            File resourceDailyReport = getResourceDailyReport(resourceFolder);
            if (resourceDailyReport == null || !resourceDailyReport.isFile()) {
                throw new ReportException("Can't find report of " + resourceFolder.getName());
            }

            ReportBean reportBean = parser.parse(resourceDailyReport);
            System.out.println(reportBean.toString());
            reportBeans.add(reportBean);
        }
        ReportBean aggregatedReport = combineReport(reportBeans);
        generator.generate(aggregatedReport);
    }

    private File getResourceDailyReport(File resourceFolder) {
        for (File resourceReport : resourceFolder.listFiles()) {
            if (resourceReport.isFile()
                    && resourceReport.getName().matches(REPORT_REGEX)) {
                return resourceReport;
            }
        }
        return null;
    }

    private ReportBean combineReport(List<ReportBean> reportBeans) {
        ReportBean aggregatedReport = new ReportBean();
        Set<String> newChannels = new LinkedHashSet<String>();
        Set<String> existingChannels = new LinkedHashSet<String>();
        int phoneNumber = 0;
        int validPhoneNumber = 0;
        int validResourceNumber = 0;
        int interviewAppointmentNumber = 0;
        int interviewPresentNumber = 0;
        Set<String> otherTasks = new LinkedHashSet<String>(reportBeans.size());
        Set<String> issues = new LinkedHashSet<String>(reportBeans.size());
        Set<String> issueSolutions = new LinkedHashSet<String>(reportBeans.size());
        for (ReportBean reportBean : reportBeans) {
            String newChannelStr = reportBean.getNewChannel().trim();
            newChannelStr = newChannelStr.replaceAll("，", ",").replaceAll("、", ",");
            newChannels.addAll(Arrays.asList(newChannelStr.split(",")));
            String existingChannelStr = reportBean.getExistingChannel().trim();
            existingChannelStr = existingChannelStr.replaceAll("，", ",").replaceAll("、", ",");
            existingChannels.addAll(Arrays.asList(existingChannelStr.split(",")));
            phoneNumber += reportBean.getPhoneNumber();
            validPhoneNumber += reportBean.getValidPhoneNumber();
            validResourceNumber += reportBean.getValidResourceNumber();
            interviewAppointmentNumber += reportBean.getInterviewAppointmentNumber();
            interviewPresentNumber += reportBean.getInterviewPresentNumber();
            if (!reportBean.getOtherTask().trim().isEmpty()) {
                otherTasks.add(reportBean.getOtherTask().trim());
            }
            String issue = reportBean.getIssue();
            if (issue.startsWith("问题：")) {
                issue = issue.substring("问题：".length()).trim();
            }
            if (!issue.isEmpty()) {
                issues.add(issue);
            }
            String issueSolution = reportBean.getIssueSolution();
            if (issueSolution.startsWith("解决办法：")) {
                issueSolution = issueSolution.substring("解决办法：".length()).trim();
            }
            if (!issueSolution.isEmpty()) {
                issueSolutions.add(issueSolution);
            }
        }
        aggregatedReport.setReportDate(new Date());
        aggregatedReport.setNewChannel(join(newChannels, "、"));
        aggregatedReport.setExistingChannel(join(existingChannels, "、"));
        aggregatedReport.setPhoneNumber(phoneNumber);
        aggregatedReport.setValidPhoneNumber(validPhoneNumber);
        aggregatedReport.setValidResourceNumber(validResourceNumber);
        aggregatedReport.setInterviewAppointmentNumber(interviewAppointmentNumber);
        aggregatedReport.setInterviewPresentNumber(interviewPresentNumber);
        aggregatedReport.setOtherTask(join(otherTasks, "\n"));
        aggregatedReport.setIssue("问题：" + join(issues, "\n"));
        aggregatedReport.setIssueSolution("解决办法：" + join(issueSolutions, "\n"));
        System.out.println(aggregatedReport.toString());
        return aggregatedReport;
    }

    private String join(Set<String> values, String delimit) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String value : values) {
            i++;
            if (value == null || value.isEmpty()) {
                continue;
            }

            builder.append(value);
            if (i < values.size()) {
                builder.append(delimit);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) throws IOException {
        args = new String[1];
        args[0] = "/Users/yangshuan/Downloads/数据20150518";

        if (args == null || args.length == 0) {
            throw new ReportException("Please input the reports path.");
        }

        String reportPath = args[0];
        if (reportPath == null || reportPath.isEmpty()) {
            throw new ReportException("Please input the reports path.");
        }

        ReportMain main = new ReportMain(reportPath);
        main.report();
    }
}
