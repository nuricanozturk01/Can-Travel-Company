package nuricanozturk.dev.service.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record BookingSaveDTO(
        @JsonProperty("house_id")
        String houseUUID,
        @JsonProperty("customer_username")
        String customer_username,
        @JsonProperty("participant_count")
        int participantCount,
        @JsonProperty("start_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate startDate,

        @JsonProperty("finish_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate finishDate
)
{
}
