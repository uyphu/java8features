package com.java8.datetime;

import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.*;

/**
 * Comprehensive examples of Date/Time API in Java 8
 */
public class DateTimeExamples {
    
    public static void main(String[] args) {
        DateTimeExamples examples = new DateTimeExamples();
        
        System.out.println("=== Date/Time API Examples ===\n");
        
        examples.localDateExamples();
        examples.localTimeExamples();
        examples.localDateTimeExamples();
        examples.periodAndDuration();
        examples.dateTimeFormatter();
        examples.temporalAdjusters();
        examples.timeZones();
        examples.legacyDateConversion();
        examples.dateTimeCalculations();
    }
    
    /**
     * LocalDate Examples
     */
    public void localDateExamples() {
        System.out.println("1. LocalDate Examples:");
        
        // Creating LocalDate
        LocalDate today = LocalDate.now();
        System.out.println("Today: " + today);
        
        LocalDate specificDate = LocalDate.of(2023, 12, 25);
        System.out.println("Specific date: " + specificDate);
        
        LocalDate parsedDate = LocalDate.parse("2023-06-15");
        System.out.println("Parsed date: " + parsedDate);
        
        // Getting date components
        System.out.println("Year: " + today.getYear());
        System.out.println("Month: " + today.getMonth());
        System.out.println("Month value: " + today.getMonthValue());
        System.out.println("Day of month: " + today.getDayOfMonth());
        System.out.println("Day of week: " + today.getDayOfWeek());
        System.out.println("Day of year: " + today.getDayOfYear());
        
        // Date arithmetic
        LocalDate tomorrow = today.plusDays(1);
        LocalDate yesterday = today.minusDays(1);
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate nextMonth = today.plusMonths(1);
        LocalDate nextYear = today.plusYears(1);
        
        System.out.println("Tomorrow: " + tomorrow);
        System.out.println("Yesterday: " + yesterday);
        System.out.println("Next week: " + nextWeek);
        System.out.println("Next month: " + nextMonth);
        System.out.println("Next year: " + nextYear);
        
        // Date comparison
        LocalDate date1 = LocalDate.of(2023, 1, 1);
        LocalDate date2 = LocalDate.of(2023, 12, 31);
        
        System.out.println("Date1 is before Date2: " + date1.isBefore(date2));
        System.out.println("Date1 is after Date2: " + date1.isAfter(date2));
        System.out.println("Date1 equals Date2: " + date1.isEqual(date2));
        
        // Leap year
        LocalDate leapYear = LocalDate.of(2024, 2, 29);
        System.out.println("Is leap year: " + leapYear.isLeapYear());
        
        System.out.println();
    }
    
    /**
     * LocalTime Examples
     */
    public void localTimeExamples() {
        System.out.println("2. LocalTime Examples:");
        
        // Creating LocalTime
        LocalTime now = LocalTime.now();
        System.out.println("Current time: " + now);
        
        LocalTime specificTime = LocalTime.of(14, 30, 45);
        System.out.println("Specific time: " + specificTime);
        
        LocalTime parsedTime = LocalTime.parse("09:15:30");
        System.out.println("Parsed time: " + parsedTime);
        
        // Getting time components
        System.out.println("Hour: " + now.getHour());
        System.out.println("Minute: " + now.getMinute());
        System.out.println("Second: " + now.getSecond());
        System.out.println("Nano: " + now.getNano());
        
        // Time arithmetic
        LocalTime plusHours = now.plusHours(2);
        LocalTime plusMinutes = now.plusMinutes(30);
        LocalTime plusSeconds = now.plusSeconds(45);
        LocalTime minusHours = now.minusHours(1);
        
        System.out.println("Plus 2 hours: " + plusHours);
        System.out.println("Plus 30 minutes: " + plusMinutes);
        System.out.println("Plus 45 seconds: " + plusSeconds);
        System.out.println("Minus 1 hour: " + minusHours);
        
        // Time comparison
        LocalTime time1 = LocalTime.of(9, 0);
        LocalTime time2 = LocalTime.of(17, 30);
        
        System.out.println("Time1 is before Time2: " + time1.isBefore(time2));
        System.out.println("Time1 is after Time2: " + time1.isAfter(time2));
        
        // Constants
        System.out.println("Midnight: " + LocalTime.MIDNIGHT);
        System.out.println("Noon: " + LocalTime.NOON);
        System.out.println("Min: " + LocalTime.MIN);
        System.out.println("Max: " + LocalTime.MAX);
        
        System.out.println();
    }
    
    /**
     * LocalDateTime Examples
     */
    public void localDateTimeExamples() {
        System.out.println("3. LocalDateTime Examples:");
        
        // Creating LocalDateTime
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current date and time: " + now);
        
        LocalDateTime specificDateTime = LocalDateTime.of(2023, 12, 25, 14, 30, 45);
        System.out.println("Specific date and time: " + specificDateTime);
        
        LocalDateTime parsedDateTime = LocalDateTime.parse("2023-06-15T09:15:30");
        System.out.println("Parsed date and time: " + parsedDateTime);
        
        // Creating from LocalDate and LocalTime
        LocalDate date = LocalDate.of(2023, 6, 15);
        LocalTime time = LocalTime.of(14, 30);
        LocalDateTime combined = LocalDateTime.of(date, time);
        System.out.println("Combined date and time: " + combined);
        
        // Converting to LocalDate and LocalTime
        LocalDate extractedDate = now.toLocalDate();
        LocalTime extractedTime = now.toLocalTime();
        System.out.println("Extracted date: " + extractedDate);
        System.out.println("Extracted time: " + extractedTime);
        
        // DateTime arithmetic
        LocalDateTime plusDays = now.plusDays(7);
        LocalDateTime plusWeeks = now.plusWeeks(2);
        LocalDateTime plusMonths = now.plusMonths(3);
        LocalDateTime plusYears = now.plusYears(1);
        
        System.out.println("Plus 7 days: " + plusDays);
        System.out.println("Plus 2 weeks: " + plusWeeks);
        System.out.println("Plus 3 months: " + plusMonths);
        System.out.println("Plus 1 year: " + plusYears);
        
        // DateTime comparison
        LocalDateTime dt1 = LocalDateTime.of(2023, 1, 1, 9, 0);
        LocalDateTime dt2 = LocalDateTime.of(2023, 12, 31, 17, 30);
        
        System.out.println("DT1 is before DT2: " + dt1.isBefore(dt2));
        System.out.println("DT1 is after DT2: " + dt1.isAfter(dt2));
        
        System.out.println();
    }
    
    /**
     * Period and Duration Examples
     */
    public void periodAndDuration() {
        System.out.println("4. Period and Duration Examples:");
        
        // Period - for date-based amounts
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        Period period = Period.between(startDate, endDate);
        
        System.out.println("Period between dates: " + period);
        System.out.println("Years: " + period.getYears());
        System.out.println("Months: " + period.getMonths());
        System.out.println("Days: " + period.getDays());
        
        // Creating Period
        Period oneYear = Period.ofYears(1);
        Period sixMonths = Period.ofMonths(6);
        Period twoWeeks = Period.ofWeeks(2);
        Period tenDays = Period.ofDays(10);
        
        System.out.println("One year: " + oneYear);
        System.out.println("Six months: " + sixMonths);
        System.out.println("Two weeks: " + twoWeeks);
        System.out.println("Ten days: " + tenDays);
        
        // Duration - for time-based amounts
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 30);
        Duration duration = Duration.between(startTime, endTime);
        
        System.out.println("Duration between times: " + duration);
        System.out.println("Hours: " + duration.toHours());
        System.out.println("Minutes: " + duration.toMinutes());
        System.out.println("Seconds: " + duration.getSeconds());
        
        // Creating Duration
        Duration twoHours = Duration.ofHours(2);
        Duration thirtyMinutes = Duration.ofMinutes(30);
        Duration fortyFiveSeconds = Duration.ofSeconds(45);
        Duration oneDay = Duration.ofDays(1);
        
        System.out.println("Two hours: " + twoHours);
        System.out.println("Thirty minutes: " + thirtyMinutes);
        System.out.println("Forty-five seconds: " + fortyFiveSeconds);
        System.out.println("One day: " + oneDay);
        
        // Using Period and Duration with dates/times
        LocalDate futureDate = LocalDate.now().plus(Period.ofMonths(3));
        LocalTime futureTime = LocalTime.now().plus(Duration.ofHours(2));
        
        System.out.println("Date plus 3 months: " + futureDate);
        System.out.println("Time plus 2 hours: " + futureTime);
        
        System.out.println();
    }
    
    /**
     * DateTimeFormatter Examples
     */
    public void dateTimeFormatter() {
        System.out.println("5. DateTimeFormatter Examples:");
        
        LocalDateTime now = LocalDateTime.now();
        
        // Predefined formatters
        System.out.println("ISO_LOCAL_DATE: " + now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("ISO_LOCAL_TIME: " + now.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println("ISO_LOCAL_DATE_TIME: " + now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        // Custom formatters
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("Custom format: " + now.format(customFormatter));
        
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        System.out.println("Date only: " + now.format(dateOnlyFormatter));
        
        DateTimeFormatter timeOnlyFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        System.out.println("Time only: " + now.format(timeOnlyFormatter));
        
        // Parsing with formatters
        String dateString = "25/12/2023 14:30:45";
        LocalDateTime parsed = LocalDateTime.parse(dateString, customFormatter);
        System.out.println("Parsed date: " + parsed);
        
        // Localized formatters
        DateTimeFormatter localizedFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        ZonedDateTime zonedNow = ZonedDateTime.now();
        System.out.println("Localized format: " + zonedNow.format(localizedFormatter));
        
        // Formatter with locale
        DateTimeFormatter frenchFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
                .withLocale(Locale.FRENCH);
        System.out.println("French format: " + zonedNow.format(frenchFormatter));
        
        // Formatting individual components
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        
        System.out.println("Date: " + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println("Time: " + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        
        System.out.println();
    }
    
    /**
     * Temporal Adjusters Examples
     */
    public void temporalAdjusters() {
        System.out.println("6. Temporal Adjusters Examples:");
        
        LocalDate today = LocalDate.now();
        
        // Predefined adjusters
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate firstDayOfNextMonth = today.with(TemporalAdjusters.firstDayOfNextMonth());
        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        
        System.out.println("First day of month: " + firstDayOfMonth);
        System.out.println("Last day of month: " + lastDayOfMonth);
        System.out.println("First day of next month: " + firstDayOfNextMonth);
        System.out.println("Last day of year: " + lastDayOfYear);
        
        // Day of week adjusters
        LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDate previousFriday = today.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        LocalDate nextOrSameMonday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        
        System.out.println("Next Monday: " + nextMonday);
        System.out.println("Previous Friday: " + previousFriday);
        System.out.println("Next or same Monday: " + nextOrSameMonday);
        
        // Custom adjusters
        LocalDate nextWorkingDay = today.with(temporal -> {
            DayOfWeek dayOfWeek = DayOfWeek.from(temporal);
            if (dayOfWeek == DayOfWeek.FRIDAY) {
                return temporal.plus(3, ChronoUnit.DAYS);
            } else if (dayOfWeek == DayOfWeek.SATURDAY) {
                return temporal.plus(2, ChronoUnit.DAYS);
            } else {
                return temporal.plus(1, ChronoUnit.DAYS);
            }
        });
        
        System.out.println("Next working day: " + nextWorkingDay);
        
        System.out.println();
    }
    
    /**
     * Time Zones Examples
     */
    public void timeZones() {
        System.out.println("7. Time Zones Examples:");
        
        // Getting current time in different zones
        ZoneId utc = ZoneId.of("UTC");
        ZoneId newYork = ZoneId.of("America/New_York");
        ZoneId tokyo = ZoneId.of("Asia/Tokyo");
        ZoneId london = ZoneId.of("Europe/London");
        
        ZonedDateTime utcTime = ZonedDateTime.now(utc);
        ZonedDateTime newYorkTime = ZonedDateTime.now(newYork);
        ZonedDateTime tokyoTime = ZonedDateTime.now(tokyo);
        ZonedDateTime londonTime = ZonedDateTime.now(london);
        
        System.out.println("UTC: " + utcTime);
        System.out.println("New York: " + newYorkTime);
        System.out.println("Tokyo: " + tokyoTime);
        System.out.println("London: " + londonTime);
        
        // Converting between zones
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime converted = localDateTime.atZone(ZoneId.systemDefault())
                .withZoneSameInstant(newYork);
        
        System.out.println("Local time: " + localDateTime);
        System.out.println("Converted to New York: " + converted);
        
        // Available zones
        Set<String> availableZones = ZoneId.getAvailableZoneIds();
        System.out.println("Number of available zones: " + availableZones.size());
        
        // System default zone
        ZoneId systemZone = ZoneId.systemDefault();
        System.out.println("System default zone: " + systemZone);
        
        // Offset
        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime offsetDateTime = OffsetDateTime.now(offset);
        System.out.println("Offset date time: " + offsetDateTime);
        
        System.out.println();
    }
    
    /**
     * Legacy Date Conversion Examples
     */
    public void legacyDateConversion() {
        System.out.println("8. Legacy Date Conversion Examples:");
        
        // Converting from legacy Date to new API
        Date legacyDate = new Date();
        Instant instant = legacyDate.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        
        System.out.println("Legacy Date: " + legacyDate);
        System.out.println("Converted to LocalDateTime: " + localDateTime);
        
        // Converting from new API to legacy Date
        LocalDateTime now = LocalDateTime.now();
        Instant newInstant = now.atZone(ZoneId.systemDefault()).toInstant();
        Date newLegacyDate = Date.from(newInstant);
        
        System.out.println("New LocalDateTime: " + now);
        System.out.println("Converted to legacy Date: " + newLegacyDate);
        
        // Converting Calendar
        Calendar calendar = Calendar.getInstance();
        Instant calendarInstant = calendar.toInstant();
        LocalDateTime calendarDateTime = LocalDateTime.ofInstant(calendarInstant, ZoneId.systemDefault());
        
        System.out.println("Calendar: " + calendar.getTime());
        System.out.println("Converted to LocalDateTime: " + calendarDateTime);
        
        // Working with Instant
        Instant nowInstant = Instant.now();
        System.out.println("Current instant: " + nowInstant);
        
        Instant plusOneHour = nowInstant.plus(Duration.ofHours(1));
        System.out.println("Plus one hour: " + plusOneHour);
        
        System.out.println();
    }
    
    /**
     * DateTime Calculations Examples
     */
    public void dateTimeCalculations() {
        System.out.println("9. DateTime Calculations Examples:");
        
        // Age calculation
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);
        
        System.out.println("Birth date: " + birthDate);
        System.out.println("Age: " + age.getYears() + " years, " + 
                          age.getMonths() + " months, " + age.getDays() + " days");
        
        // Working days calculation
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 31);
        
        long workingDays = startDate.datesUntil(endDate.plusDays(1))
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SATURDAY 
                        && date.getDayOfWeek() != DayOfWeek.SUNDAY)
                .count();
        
        System.out.println("Working days in January 2023: " + workingDays);
        
        // Time difference calculation
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 30);
        Duration workDuration = Duration.between(startTime, endTime);
        
        System.out.println("Work duration: " + workDuration.toHours() + " hours, " + 
                          workDuration.toMinutesPart() + " minutes");
        
        // Business hours calculation
        LocalDateTime businessStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0));
        LocalDateTime businessEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0));
        
        Duration businessHours = Duration.between(businessStart, businessEnd);
        System.out.println("Business hours: " + businessHours.toHours() + " hours");
        
        // Date range
        LocalDate start = LocalDate.of(2023, 1, 1);
        LocalDate end = LocalDate.of(2023, 1, 10);
        
        List<LocalDate> dateRange = start.datesUntil(end.plusDays(1))
                .collect(java.util.stream.Collectors.toList());
        
        System.out.println("Date range: " + dateRange);
        
        System.out.println();
    }
} 