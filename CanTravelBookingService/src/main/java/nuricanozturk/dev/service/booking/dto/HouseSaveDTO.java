package nuricanozturk.dev.service.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import nuricanozturk.dev.data.entity.HouseType;
import nuricanozturk.dev.data.entity.ViewType;

public record HouseSaveDTO(
        @JsonProperty("house_photo")
        String housePhoto,
        String description,
        @JsonProperty("house_name")
        String houseName,
        double price,
        String city,
        String country,
        @JsonProperty("view_type")
        ViewType viewType,
        @JsonProperty("house_type")
        HouseType houseType,
        @JsonProperty("max_participant_count")
        int maxParticipantCount
)
{
}
