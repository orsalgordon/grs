package com.grs.model.dto;

import com.grs.model.Event;
import com.grs.model.Host;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    private Long eventId;
    private Long hostId;
    private String eventName;
    private String eventDescription;
    private String eventType;

    public static EventDto of(Event event) {
        return EventDto.builder()
                .eventId(event.getEventId())
                .hostId(event.getHostId())
                .eventName(event.getEventName())
                .eventDescription(event.getEventDescription())
                .eventType(event.getEventType())
                .build();
    }
}
