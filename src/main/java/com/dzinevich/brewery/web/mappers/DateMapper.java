package com.dzinevich.brewery.web.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Component
public class DateMapper {
    public OffsetDateTime asOffsetDateTime(Timestamp timestamp) {
        return Optional.ofNullable(timestamp)
                .map(ts -> {
                    var localDateTime = ts.toLocalDateTime();
                    return OffsetDateTime.of(
                            localDateTime.getYear(),
                            localDateTime.getMonthValue(),
                            localDateTime.getDayOfMonth(),
                            localDateTime.getHour(),
                            localDateTime.getMinute(),
                            localDateTime.getSecond(),
                            localDateTime.getNano(),
                            ZoneOffset.UTC);
                })
                .orElse(null);
    }

    public Timestamp asTimestamp(OffsetDateTime offsetDateTime) {
        return Optional.ofNullable(offsetDateTime)
                .map(dateTime -> Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime()))
                .orElse(null);
    }
}
