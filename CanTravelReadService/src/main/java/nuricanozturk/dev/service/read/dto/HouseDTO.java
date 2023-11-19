package nuricanozturk.dev.service.read.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record HouseDTO(

        @JsonProperty("house_id")
        UUID houseId,
        @JsonProperty("house_name")
        String m_houseName,
        @JsonProperty("description")
        String m_description,
        double price,
        @JsonProperty("location")
        LocationDTO m_locationDTO,
        @JsonProperty("view_type")
        String m_viewType,
        @JsonProperty("house_type")
        String m_houseType,

        @JsonProperty("max_participant")
        int m_maxParticipant)
{
}
