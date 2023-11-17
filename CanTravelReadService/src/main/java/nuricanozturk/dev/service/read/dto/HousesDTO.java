package nuricanozturk.dev.service.read.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record HousesDTO(
        @JsonProperty("house_list")
        List<HouseDTO> houses)
{
}
