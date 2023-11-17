package nuricanozturk.dev.service.read.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseDTO(
        String message,
        @JsonProperty("total_page")
        long totalPage,
        @JsonProperty("current_page")
        int currentPage,
        @JsonProperty("item_count")
        int itemCount,
        boolean success,
        Object object
)
{

}
