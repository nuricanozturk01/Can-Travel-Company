package nuricanozturk.dev.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private long m_locationId;
    @Column(name = "city")
    private String m_city;
    @Column(name = "country")
    private String m_country;

    public Location(String city, String country)
    {
        m_city = city;
        m_country = country;
    }

    public Location()
    {
    }

    public long getLocationId()
    {
        return m_locationId;
    }

    public void setLocationId(long locationId)
    {
        m_locationId = locationId;
    }

    public String getCity()
    {
        return m_city;
    }

    public void setCity(String city)
    {
        m_city = city;
    }

    public String getCountry()
    {
        return m_country;
    }

    public void setCountry(String country)
    {
        m_country = country;
    }
}