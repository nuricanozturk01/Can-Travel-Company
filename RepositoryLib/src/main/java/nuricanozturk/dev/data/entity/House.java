package nuricanozturk.dev.data.entity;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "house")
@SuppressWarnings("all")
public class House
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "house_id")
    private UUID m_houseId;
    @Column(name = "house_photo")
    private String m_housePhoto;
    @Column(name = "description")
    private String m_description;
    @Column(name = "house_name")
    private String m_houseName;
    @Column(name = "price")
    private double m_price;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
    @Enumerated(EnumType.STRING)
    private ViewType m_viewType;
    @Enumerated(EnumType.STRING)
    private HouseType m_houseType;

    @OneToMany(mappedBy = "m_house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reservation> m_reservations;

    public House()
    {
    }

    public House(String housePhoto, String description, String houseName, double price, Location location, ViewType viewType, HouseType houseType)
    {
        m_housePhoto = housePhoto;
        m_description = description;
        m_houseName = houseName;
        m_price = price;
        this.location = location;
        m_viewType = viewType;
        m_houseType = houseType;
    }

    public Set<Reservation> getReservations()
    {
        return m_reservations;
    }

    public void setReservations(Set<Reservation> reservations)
    {
        m_reservations = reservations;
    }

    public UUID getHouseId()
    {
        return m_houseId;
    }

    public void setHouseId(UUID houseId)
    {
        m_houseId = houseId;
    }

    public String getHousePhoto()
    {
        return m_housePhoto;
    }

    public void setHousePhoto(String housePhoto)
    {
        m_housePhoto = housePhoto;
    }

    public String getDescription()
    {
        return m_description;
    }

    public void setDescription(String description)
    {
        m_description = description;
    }

    public String getHouseName()
    {
        return m_houseName;
    }

    public void setHouseName(String houseName)
    {
        m_houseName = houseName;
    }

    public double getPrice()
    {
        return m_price;
    }

    public void setPrice(double price)
    {
        m_price = price;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public ViewType getViewType()
    {
        return m_viewType;
    }

    public void setViewType(ViewType viewType)
    {
        m_viewType = viewType;
    }

    public HouseType getHouseType()
    {
        return m_houseType;
    }

    public void setHouseType(HouseType houseType)
    {
        m_houseType = houseType;
    }
}
