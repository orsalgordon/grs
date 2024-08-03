package com.grs.util;

import com.grs.model.Event;
import com.grs.model.Gift;
import com.grs.model.Host;
import com.grs.model.dto.EventDto;
import com.grs.model.dto.GiftDto;
import com.grs.model.dto.HostDto;

public class DTOMapper {
    public static Host map(HostDto hostDto) {
        if (hostDto == null || hostDto.getHostId() < 0) {
            return null;
        }
        Host host = new Host();
        host.setHostId(hostDto.getHostId());
        host.setFirstName(hostDto.getFirstName());
        host.setLastName(hostDto.getLastName());
        host.setEmail(hostDto.getEmail());
        return host;
    }

    public static Event map(EventDto eventDto) {
        if (eventDto == null) {
            return null;
        }
        Event event = new Event();
        event.setEventId(eventDto.getEventId());
        event.setHostId(eventDto.getHostId());
        event.setEventName(eventDto.getEventName());
        event.setEventDescription(eventDto.getEventDescription());
        event.setEventType(eventDto.getEventType());
        return event;
    }

    public static Gift map(GiftDto giftDto) {
        if (giftDto == null) {
            return null;
        }
        Gift gift = new Gift();
        gift.setEventId(giftDto.getEventId());
        gift.setGiftId(giftDto.getGiftId());
        gift.setName(giftDto.getName());
        gift.setDescription(giftDto.getDescription());
        gift.setLink(giftDto.getLink());
        gift.setAvailable(giftDto.isAvailable());
        return gift;
    }
}
