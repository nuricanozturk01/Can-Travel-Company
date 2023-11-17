package nuricanozturk.dev.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private UUID houseId;

    @Column(name = "house_photo")
    private String housePhoto;

    @Column(name = "description")
    private String description;

    @Column(name = "house_name")
    private String houseName;

    @Column(name = "price")
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location location;

    @Enumerated(EnumType.STRING)
    private ViewType viewType;

    @Enumerated(EnumType.STRING)
    private HouseType houseType;

    @Column(name = "max_participant", nullable = false)
    private int maxParticipantCount;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Reservation> reservations;

    public House()
    {
    }

    public static class Builder
    {
        private final House m_house;

        public Builder()
        {
            m_house = new House();
        }

        public Builder setHousePhoto(String housePhotoPath)
        {
            m_house.housePhoto = housePhotoPath;
            return this;
        }

        public Builder setDescription(String description)
        {
            m_house.description = description;
            return this;
        }

        public Builder setHouseName(String houseName)
        {
            m_house.houseName = houseName;
            return this;
        }

        public Builder setPrice(double price)
        {
            m_house.price = price;
            return this;
        }

        public Builder setLocation(Location location)
        {
            m_house.location = location;
            return this;
        }

        public Builder setView(ViewType view)
        {
            m_house.viewType = view;
            return this;
        }

        public Builder setHouseType(HouseType houseType)
        {
            m_house.houseType = houseType;
            return this;
        }

        public Builder setMaxParticipantCount(int maxParticipantCount)
        {
            m_house.maxParticipantCount = maxParticipantCount;
            return this;
        }

        public Builder addReservation(Reservation reservation)
        {
            m_house.reservations.add(reservation);
            return this;
        }

        public House build()
        {
            return m_house;
        }
    }

    @Override
    public boolean equals(Object o)
    {
        return (o instanceof House other) && houseId.toString().compareTo(other.getHouseId().toString()) == 0;
    }


    public UUID getHouseId()
    {
        return houseId;
    }

    public String getHousePhoto()
    {
        return housePhoto;
    }

    public String getDescription()
    {
        return description;
    }

    public String getHouseName()
    {
        return houseName;
    }

    public double getPrice()
    {
        return price;
    }

    public Location getLocation()
    {
        return location;
    }

    public ViewType getViewType()
    {
        return viewType;
    }

    public HouseType getHouseType()
    {
        return houseType;
    }

    public int getMaxParticipantCount()
    {
        return maxParticipantCount;
    }

    public Set<Reservation> getReservations()
    {
        return reservations;
    }
}
