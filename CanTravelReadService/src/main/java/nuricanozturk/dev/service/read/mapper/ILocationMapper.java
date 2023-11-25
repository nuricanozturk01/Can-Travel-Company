package nuricanozturk.dev.service.read.mapper;

import nuricanozturk.dev.data.entity.Location;
import nuricanozturk.dev.service.read.dto.LocationDTO;
import org.mapstruct.Mapper;

@Mapper(implementationName = "LocationMapperImpl", componentModel = "spring")
public interface ILocationMapper
{
    /**
     * Convert location to locationDTO.
     *
     * @param location represent the location.
     * @return LocationDTO.
     */
    LocationDTO toLocationDTO(Location location);

    /**
     * Convert locationDTO to location.
     *
     * @param locationDTO represent the locationDTO.
     * @return Location.
     */
    Location toLocation(LocationDTO locationDTO);
}
