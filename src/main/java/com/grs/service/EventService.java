package com.grs.service;

import com.grs.exception.InternalServerException;
import com.grs.exception.NotFoundException;
import com.grs.model.Event;
import com.grs.model.dto.EventDetailsDto;
import com.grs.model.dto.EventDto;
import com.grs.model.dto.GiftDto;
import com.grs.repository.EventRepository;
import com.grs.util.DTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventService {

    private final GiftService giftService;
    private EventRepository eventRepository;

    public List<EventDto> getAllEventsByHostId(Integer hostId) {
        List<Event> events = eventRepository.findAllByHostId(hostId.longValue());
        return events.stream()
                .map(EventDto::of)
                .toList();
    }

    public EventDetailsDto getEventDetails(Integer eventId) {
        Optional<Event> event = eventRepository.findById(eventId.longValue());
        if (event.isPresent()) {
            List<GiftDto> giftList = giftService.getAllGiftsByEventId(eventId);
            return EventDetailsDto.of(EventDto.of(event.get()), giftList);
        }
        throw new NotFoundException(String.format("Event with id %s not found", eventId));
    }

    public EventDto createEvent(EventDto eventDto) {
        Event event = DTOMapper.map(eventDto);
        Event response = eventRepository.save(event);
        return Optional.of(response)
                .map(EventDto::of)
                .orElseThrow(() -> new InternalServerException("There's an error saving the event details"));
    }

    public EventDto updateEvent(Integer id, EventDto eventDto) {
        Optional<Event> existingHost = eventRepository.findById(id.longValue());
        if (existingHost.isPresent()) {
            eventDto.setEventId(id.longValue());
            Event host = DTOMapper.map(eventDto);
            Event response = eventRepository.save(host);
            return Optional.of(response)
                    .map(EventDto::of)
                    .orElseThrow(() -> new InternalServerException("There's an error saving the event details"));

        }
        throw new NotFoundException(String.format("Event with id %s not found", id));
    }

    public void deleteEvent(Integer id) {
        try {
            eventRepository.deleteById(id.longValue());
        } catch (Exception e) {
            throw new InternalServerException("There's an error deleting the event details for the id " + id);
        }
    }
}
