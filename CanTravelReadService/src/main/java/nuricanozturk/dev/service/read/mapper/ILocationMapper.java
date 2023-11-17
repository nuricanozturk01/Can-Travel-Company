package nuricanozturk.dev.service.read.mapper;

import nuricanozturk.dev.data.entity.Location;
import nuricanozturk.dev.service.read.dto.LocationDTO;
import org.mapstruct.Mapper;

@Mapper(implementationName = "LocationMapperImpl", componentModel = "spring")
public interface ILocationMapper
{
    LocationDTO toLocationDTO(Location location);
    Location toLocation(LocationDTO locationDTO);
}
