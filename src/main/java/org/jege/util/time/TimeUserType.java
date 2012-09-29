package org.jege.util.time;


public class TimeUserType {
    public static final String THREETEN_PREFIX = "org.jadira.usertype.dateandtime.jsr310.";
    
    // Instant
    public static final String INSTANT_MILLIS_LONG = THREETEN_PREFIX + "PersistentInstantAsMillisLong";
    public static final String INSTANT_NANOS_BIGINTEGER = THREETEN_PREFIX + "PersistentInstantAsMillisLong";
    public static final String INSTANT_STRING = THREETEN_PREFIX + "PersistentInstantAsMillisLong";
    public static final String INSTANT_TIMESTAMP = THREETEN_PREFIX + "PersistentInstantAsMillisLong";
    
    // Duration
    public static final String DURATION_STRING = THREETEN_PREFIX + "PersistentDurationAsString";
    
    // LocalDate
    public static final String LOCAL_DATE = THREETEN_PREFIX + "PersistentLocalDate";
    public static final String LOCAL_DATE_STRING = THREETEN_PREFIX + "PersistentLocalDateAsString";
    
    // LocalTime
    public static final String LOCAL_TIME = THREETEN_PREFIX + "PersistentLocalTime";
    public static final String LOCAL_TIME_MILLIS_INTEGER = THREETEN_PREFIX + "PersistentLocalDate";
    public static final String LOCAL_TIME_NANOS_LONG = THREETEN_PREFIX + "PersistentLocalDate";
    public static final String LOCAL_TIME_STRING = THREETEN_PREFIX + "PersistentLocalDate";
    public static final String LOCAL_TIME_TIMESTAMP = THREETEN_PREFIX + "PersistentLocalDate";
    
    // LocalDateTime
    public static final String LOCAL_DATE_TIME = THREETEN_PREFIX + "PersistentLocalDateTime";
    public static final String LOCAL_DATE_TIME_STRING = THREETEN_PREFIX + "PersistentLocalDateTimeAsString";
    
    // OffsetDate
    
    // OffsetTime
    
    // OffsetDateTime
    
    // ZoneDateTime
    
    // Period
    public static final String PERIOD = THREETEN_PREFIX + "PersistentLocalDateTime";
}
