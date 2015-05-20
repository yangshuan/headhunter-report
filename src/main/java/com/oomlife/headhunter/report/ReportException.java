package com.oomlife.headhunter.report;

/**
 * Main exception for this project.
 * Created by yangshuan on 15/5/19.
 */
public class ReportException extends RuntimeException {
    public ReportException(String message) {
        super(message);
    }

    public ReportException(Exception e) {
        super(e);
    }
}
