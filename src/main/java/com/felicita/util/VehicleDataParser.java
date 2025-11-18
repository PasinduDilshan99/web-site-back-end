package com.felicita.util;

import com.felicita.model.response.VehicleDetailResponse.Assignment;
import com.felicita.model.response.VehicleDetailResponse.UsageLog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VehicleDataParser {

    private static final String RECORD_SEPARATOR = "||||";
    private static final String FIELD_SEPARATOR = "\\|\\|\\|";

    public static List<Assignment> parseAssignments(String concatenatedAssignments) {
        List<Assignment> assignments = new ArrayList<>();

        if (concatenatedAssignments == null || concatenatedAssignments.trim().isEmpty()) {
            return assignments;
        }

        String[] assignmentRecords = concatenatedAssignments.split(RECORD_SEPARATOR);

        for (String record : assignmentRecords) {
            String[] fields = record.split(FIELD_SEPARATOR);
            if (fields.length >= 6) {
                Assignment assignment = Assignment.builder()
                        .assignmentId(parseInteger(fields[0]))
                        .driverId(parseInteger(fields[1]))
                        .assignmentStartDate(parseLocalDate(fields[2]))
                        .assignmentEndDate(parseLocalDate(fields[3]))
                        .assignmentPurpose(emptyToNull(fields[4]))
                        .assignmentRemarks(emptyToNull(fields[5]))
                        .build();
                assignments.add(assignment);
            }
        }

        return assignments;
    }

    public static List<UsageLog> parseUsageLogs(String concatenatedUsageLogs) {
        List<UsageLog> usageLogs = new ArrayList<>();

        if (concatenatedUsageLogs == null || concatenatedUsageLogs.trim().isEmpty()) {
            return usageLogs;
        }

        String[] usageRecords = concatenatedUsageLogs.split(RECORD_SEPARATOR);

        for (String record : usageRecords) {
            String[] fields = record.split(FIELD_SEPARATOR);
            if (fields.length >= 11) {
                UsageLog usageLog = UsageLog.builder()
                        .usageId(parseInteger(fields[0]))
                        .packageId(parseInteger(fields[1]))
                        .tourId(parseInteger(fields[2]))
                        .usageStartDatetime(parseLocalDateTime(fields[3]))
                        .usageEndDatetime(parseLocalDateTime(fields[4]))
                        .startOdometer(parseInteger(fields[5]))
                        .endOdometer(parseInteger(fields[6]))
                        .routeDescription(emptyToNull(fields[7]))
                        .usagePurpose(emptyToNull(fields[8]))
                        .fuelUsedLiters(parseBigDecimal(fields[9]))
                        .usageRemarks(emptyToNull(fields[10]))
                        .build();
                usageLogs.add(usageLog);
            }
        }

        return usageLogs;
    }

    // Helper methods for parsing
    private static Integer parseInteger(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static BigDecimal parseBigDecimal(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static LocalDate parseLocalDate(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(value);
        } catch (Exception e) {
            return null;
        }
    }

    private static LocalDateTime parseLocalDateTime(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            // Handle both timestamp formats
            if (value.contains(" ")) {
                value = value.replace(' ', 'T');
            }
            return LocalDateTime.parse(value);
        } catch (Exception e) {
            return null;
        }
    }

    private static String emptyToNull(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value.trim();
    }
}