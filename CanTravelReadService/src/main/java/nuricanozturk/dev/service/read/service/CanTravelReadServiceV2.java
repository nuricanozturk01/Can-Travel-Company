package nuricanozturk.dev.service.read.service;

import nuricanozturk.dev.data.dal.CanTravelServiceHelper;
import nuricanozturk.dev.service.read.dto.HousesDTO;
import nuricanozturk.dev.service.read.dto.ResponseDTOv2;
import nuricanozturk.dev.service.read.mapper.IHouseMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static nuricanozturk.dev.service.read.util.Constants.READ_SERVICE_V2;
import static nuricanozturk.dev.service.read.util.StreamUtil.toListConcurrent;

@Service(READ_SERVICE_V2)
@Lazy
public class CanTravelReadServiceV2 implements ICanTravelReadServiceV2
{
    private final CanTravelServiceHelper m_travelServiceHelper;
    private final IHouseMapper m_houseMapper;

    public CanTravelReadServiceV2(CanTravelServiceHelper travelServiceHelper, IHouseMapper houseMapper)
    {
        m_travelServiceHelper = travelServiceHelper;
        m_houseMapper = houseMapper;
    }

    /**
     * Prepare response message.
     *
     * @param houses represent the houses.
     * @return ResponseDTO.
     */
    private ResponseDTOv2 prepareMessage(HousesDTO houses)
    {
        var msg = format("%d item found successfully!", houses.houses().size());

        return new ResponseDTOv2(msg, houses.houses().size(), true, houses.houses());
    }

    /**
     * Find all house pageable
     *
     * @return ResponseDTO.
     */
    @Override
    public ResponseDTOv2 findAllHouse()
    {
        return prepareMessage(m_houseMapper.toHousesDTO(toListConcurrent(m_travelServiceHelper.findAllHouse(), m_houseMapper::toHouseDTO)));
    }
}
