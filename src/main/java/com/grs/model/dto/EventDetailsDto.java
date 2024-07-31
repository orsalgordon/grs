package com.grs.model.dto;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDetailsDto extends EventDto {

    private List<GiftDto> gifts;

    public static EventDetailsDto of(EventDto event, List<GiftDto> gifts) {
        EventDetailsDto dto = new EventDetailsDto();
        dto.setEventId(event.getEventId());
        dto.setHostId(event.getHostId());
        dto.setEventName(event.getEventName());
        dto.setEventDescription(event.getEventDescription());
        dto.setEventType(event.getEventType());
        dto.setGifts(gifts);
        return dto;
    }
}
