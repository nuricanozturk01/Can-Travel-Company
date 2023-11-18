package nuricanozturk.dev.service.read.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseDTOv2(
        String message,
        @JsonProperty("item_count")
        int itemCount,
        boolean success,
        Object object)
{

}
