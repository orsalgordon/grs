package com.grs.controller;

import com.grs.model.dto.EventDto;
import com.grs.model.dto.GiftDto;
import com.grs.model.dto.UpdateGiftRequest;
import com.grs.service.EventService;
import com.grs.service.GiftService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/rest/v1/gift")
@Tag(name = "Gift")
public class GiftEndpoint {

    @Autowired
    private GiftService giftService;

    @GetMapping("/event/{id}")
    public ResponseEntity<List<GiftDto>> getGiftByEventId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(giftService.getAllGiftsByEventId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftDto> getGiftById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(giftService.getGiftById(id));
    }

    @PostMapping
    public ResponseEntity<GiftDto> addGift(@RequestBody GiftDto giftDto){
        return ResponseEntity.ok(giftService.addGift(giftDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GiftDto> updateGift(@PathVariable("id") Integer id, @RequestBody GiftDto giftDto){
        return ResponseEntity.ok(giftService.updateGift(id, giftDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGift(@PathVariable("id") Integer id){
        giftService.deleteGift(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<GiftDto> addGift(@PathVariable("id") Integer id, @RequestBody UpdateGiftRequest request){
        return ResponseEntity.ok(giftService.updateGiftAvailability(id, request));
    }
}
