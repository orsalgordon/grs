package com.grs.service;

import com.grs.exception.InternalServerException;
import com.grs.exception.NotFoundException;
import com.grs.model.Event;
import com.grs.model.Gift;
import com.grs.model.dto.EventDto;
import com.grs.model.dto.GiftDto;
import com.grs.model.dto.UpdateGiftRequest;
import com.grs.repository.GiftRepository;
import com.grs.util.DTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GiftService {

    private GiftRepository giftRepository;

    public List<GiftDto> getAllGiftsByEventId(Integer eventId) {
        List<Gift> events = giftRepository.findAllByEventId(eventId.longValue());
        return events.stream()
                .map(GiftDto::of)
                .toList();
    }

    public GiftDto getGiftById(Integer giftId) {
        Optional<Gift> gift = giftRepository.findById(giftId.longValue());
        if (gift.isPresent()) {
            return GiftDto.of(gift.get());
        }
        throw new NotFoundException(String.format("Gift with id %s not found", gift));
    }

    public GiftDto addGift(GiftDto giftDto) {
        Gift gift = DTOMapper.map(giftDto);
        Gift response = giftRepository.save(gift);
        return Optional.of(response)
                .map(GiftDto::of)
                .orElseThrow(() -> new InternalServerException("There's an error saving the gift details"));
    }

    public GiftDto updateGift(Integer id, GiftDto giftDto) {
        Optional<Gift> existingGift = giftRepository.findById(id.longValue());
        if (existingGift.isPresent()) {
            giftDto.setGiftId(id.longValue());
            Gift gift = DTOMapper.map(giftDto);
            Gift response = giftRepository.save(gift);
            return Optional.of(response)
                    .map(GiftDto::of)
                    .orElseThrow(() -> new InternalServerException("There's an error saving the gift details"));

        }
        throw new NotFoundException(String.format("Gift with id %s not found", id));
    }

    public void deleteGift(Integer id) {
        try {
            giftRepository.deleteById(id.longValue());
        } catch (Exception e) {
            throw new InternalServerException("There's an error deleting the gift details for id " + id);
        }
    }

    public GiftDto updateGiftAvailability(Integer id, UpdateGiftRequest request) {
        Gift existingGift = giftRepository.findByEventIdAndGiftId(request.getEventId(), id.longValue());
        if (existingGift != null) {
            existingGift.setAvailable(request.isAvailable());
            Gift response = giftRepository.save(existingGift);
            return Optional.of(response)
                    .map(GiftDto::of)
                    .orElseThrow(() -> new InternalServerException("There's an error saving the gift details"));
        }
        throw new NotFoundException(String.format("Gift with id %s not found", id));
    }
}
