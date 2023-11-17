package nuricanozturk.dev.service.read.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record AvailableHouseQueryDTO(
        @JsonProperty("start_date")
        @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
        LocalDate startDate,
        @JsonProperty("finish_date")
        @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
        LocalDate finishDate,
        @JsonProperty("page")
        int page,
        @JsonProperty("participant_count")
        int participantCount
)
{
}
