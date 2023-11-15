package nuricanozturk.dev.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "reservation")
public class Reservation
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "reservation_id")
    private UUID m_reservationId;
    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    private House m_house;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer m_customer;
    @Column(name = "start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate m_startDate;
    @Column(name = "finish_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate m_finishDate;

    public Reservation()
    {
    }


    public Reservation(House house, Customer customer, LocalDate startDate, LocalDate finishDate)
    {
        m_house = house;
        m_customer = customer;
        m_startDate = startDate;
        m_finishDate = finishDate;
    }

    public UUID getReservationId()
    {
        return m_reservationId;
    }

    public void setReservationId(UUID reservationId)
    {
        m_reservationId = reservationId;
    }

    public House getHouse()
    {
        return m_house;
    }

    public void setHouse(House house)
    {
        m_house = house;
    }

    public Customer getCustomer()
    {
        return m_customer;
    }

    public void setCustomer(Customer customer)
    {
        m_customer = customer;
    }

    public LocalDate getStartDate()
    {
        return m_startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        m_startDate = startDate;
    }

    public LocalDate getFinishDate()
    {
        return m_finishDate;
    }

    public void setFinishDate(LocalDate finishDate)
    {
        m_finishDate = finishDate;
    }
}
