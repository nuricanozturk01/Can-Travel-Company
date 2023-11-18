package nuricanozturk.dev.service.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
@Schema(description = "Booking Save Request Informations")
public record BookingSaveDTO(
        @JsonProperty("house_id")
        String houseUUID,
        @JsonProperty("customer_username")
        String customer_username,
        @JsonProperty("participant_count")
        int participantCount,
        @Schema(description = "date format: dd/MM/yyyy - [23/12/2023]")
        @JsonProperty("start_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate startDate,
        @Schema(description = "date format: dd/MM/yyyy - [23/12/2023]")
        @JsonProperty("finish_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate finishDate
)
{
}
