package com.grs.controller;

import com.grs.model.dto.EventDetailsDto;
import com.grs.model.dto.EventDto;
import com.grs.model.dto.HostDto;
import com.grs.service.EventService;
import com.grs.service.HostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/rest/v1/event")
@Tag(name = "Event")
public class EventEndpoint {

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<List<EventDto>> getEventByHostId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(eventService.getAllEventsByHostId(id));
    }

    @GetMapping("/{id}/gifts")
    public ResponseEntity<EventDetailsDto> getEventDetails(@PathVariable("id") Integer id){
        return ResponseEntity.ok(eventService.getEventDetails(id));
    }

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto){
        return ResponseEntity.ok(eventService.createEvent(eventDto));
    }

    @PatchMapping("{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable("id") Integer id, @RequestBody EventDto eventDto){
        return ResponseEntity.ok(eventService.updateEvent(id, eventDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Integer id){
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
