package nuricanozturk.dev.data.entity;

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
    private UUID reservationId;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "finish_date")
    private LocalDate finishDate;

    public Reservation()
    {
    }

    public Reservation(House house, Customer customer, LocalDate startDate, LocalDate finishDate)
    {
        this.house = house;
        this.customer = customer;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public UUID getReservationId()
    {
        return reservationId;
    }

    public void setReservationId(UUID reservationId)
    {
        this.reservationId = reservationId;
    }

    public House getHouse()
    {
        return house;
    }

    public void setHouse(House house)
    {
        this.house = house;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate()
    {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate)
    {
        this.finishDate = finishDate;
    }

    public boolean isAvailableByDates(LocalDate p_startDate, LocalDate p_finishDate)
    {
        return (!p_startDate.isBefore(finishDate) && p_finishDate.isAfter(startDate));
    }
}
