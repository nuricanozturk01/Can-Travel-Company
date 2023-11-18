package nuricanozturk.dev.service.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import nuricanozturk.dev.data.entity.HouseType;
import nuricanozturk.dev.data.entity.ViewType;

import java.time.LocalDate;

@Schema(description = "Booking Response DTO")
public record BookingResponseDTO(
        @JsonProperty("house_name")
        String houseName,
        String description,
        @JsonProperty("view_type")
        ViewType viewType,
        @JsonProperty("house_type")
        HouseType houseType,
        @JsonProperty("max_participant_count")
        int maxParticipantCount,
        String city,
        String country,
        @JsonProperty("start_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate startDate,
        @JsonProperty("finish_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate finishDate,
        double price

)
{
}
