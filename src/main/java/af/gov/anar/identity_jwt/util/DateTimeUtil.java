package af.gov.anar.identity_jwt.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Component;


@Component
public class DateTimeUtil {

    ZoneId defaultZoneId = ZoneId.systemDefault();

    public LocalDateTime getCurrentDate() {
        Instant instant = (new Date()).toInstant();
        LocalDateTime dateTime = instant.atZone(defaultZoneId).toLocalDateTime();
        return dateTime;
    }

    public LocalDateTime convertStringToLocalDateTime(String dateStr) {
        if(dateStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            return dateTime;
        }
        return null;
    }

    public LocalDateTime convertStringToLocalDateTimeWithMilisec(String dateStr) {
        if(dateStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            return dateTime;
        }
        return null;
    }

    public LocalDateTime convertStringToLocalDateTimeSlashMDYHMS(String dateStr) {
        if(dateStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            return dateTime;
        }
        return null;
    }

    public Timestamp convertStringToTimestamp(String dateStr) {
        if(dateStr != null) {
            LocalDateTime localDateTime = convertStringToLocalDateTime(dateStr);
            return Timestamp.valueOf(localDateTime);
        }
        return null;
    }

    public Timestamp convertStringToTimestampWithMilisec(String dateStr) {
        if(dateStr != null) {
            LocalDateTime localDateTime = convertStringToLocalDateTimeWithMilisec(dateStr);
            return Timestamp.valueOf(localDateTime);
        }
        return null;
    }

    public LocalDateTime convertDateToLocalDateTime(Date date) {
        if(date != null) {
            Instant instant = date.toInstant();
            LocalDateTime dateTime = instant.atZone(defaultZoneId).toLocalDateTime();
            return dateTime;
        }
        return null;
    }
}
