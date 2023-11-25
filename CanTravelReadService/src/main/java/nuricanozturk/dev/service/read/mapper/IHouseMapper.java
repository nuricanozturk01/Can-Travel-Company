package nuricanozturk.dev.service.read.mapper;

import nuricanozturk.dev.data.entity.House;
import nuricanozturk.dev.service.read.dto.HouseDTO;
import nuricanozturk.dev.service.read.dto.HousesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(implementationName = "HouseMapperImpl", componentModel = "spring", uses = ILocationMapper.class)
public interface IHouseMapper
{
    /**
     * Convert house to houseDTO.
     *
     * @param house represent the house.
     * @return HouseDTO.
     */
    @Mappings({
            @Mapping(source = "location", target = "m_locationDTO"),
            @Mapping(source = "houseName", target = "m_houseName"),
            @Mapping(source = "description", target = "m_description"),
            @Mapping(source = "viewType", target = "m_viewType"),
            @Mapping(source = "houseType", target = "m_houseType"),
            @Mapping(source = "maxParticipantCount", target = "m_maxParticipant")
    })
    HouseDTO toHouseDTO(House house);

    /**
     * Convert houseDTO to house.
     *
     * @param houseDTO represent the houseDTO.
     * @return House.
     */
    House toHouse(HouseDTO houseDTO);

    /**
     * Convert houseDTO to house.
     *
     * @param houseDTOS represent the houseDTOs.
     * @return HousesDTO.
     */
    default HousesDTO toHousesDTO(List<HouseDTO> houseDTOS)
    {
        return new HousesDTO(houseDTOS);
    }
}