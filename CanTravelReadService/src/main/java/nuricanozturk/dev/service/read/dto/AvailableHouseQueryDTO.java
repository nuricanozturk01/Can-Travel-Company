package nuricanozturk.dev.service.read.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record AvailableHouseQueryDTO(
        @JsonProperty("start_date")
        LocalDate startDate,
        @JsonProperty("finish_date")
        LocalDate finishDate,
        @JsonProperty("page")
        int page,
        @JsonProperty("participant_count")
        int participantCount
)
{
}
