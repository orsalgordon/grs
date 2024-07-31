package com.grs.model.dto;

import com.grs.model.Gift;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiftDto {
    private Long giftId;
    private Long eventId;
    private String name;
    private String description;
    private String link;

    public static GiftDto of(Gift gift) {
        return GiftDto.builder()
                .eventId(gift.getEventId())
                .giftId(gift.getGiftId())
                .name(gift.getName())
                .description(gift.getDescription())
                .link(gift.getLink())
                .build();
    }
}
