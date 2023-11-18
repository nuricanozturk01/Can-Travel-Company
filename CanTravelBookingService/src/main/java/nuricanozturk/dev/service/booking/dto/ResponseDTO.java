package nuricanozturk.dev.service.booking.dto;

public record ResponseDTO(
        String message,
        boolean success,
        Object object
)
{

}