package nuricanozturk.dev.data.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "address")
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "customer_id")
    private UUID m_customerId;
    @Column(name = "first_name")
    private String m_firstName;
    @Column(name = "middle_name")
    private String m_middleName;
    @Column(name = "last_name")
    private String m_lastName;
    @Column(name = "email")
    private String m_email;

    public Customer()
    {
    }

    public Customer(String firstName, String middleName, String lastName, String email)
    {
        m_firstName = firstName;
        m_middleName = middleName;
        m_lastName = lastName;
        m_email = email;
    }

    public UUID getCustomerId()
    {
        return m_customerId;
    }

    public void setCustomerId(UUID customerId)
    {
        m_customerId = customerId;
    }

    public String getFirstName()
    {
        return m_firstName;
    }

    public void setFirstName(String firstName)
    {
        m_firstName = firstName;
    }

    public String getMiddleName()
    {
        return m_middleName;
    }

    public void setMiddleName(String middleName)
    {
        m_middleName = middleName;
    }

    public String getLastName()
    {
        return m_lastName;
    }

    public void setLastName(String lastName)
    {
        m_lastName = lastName;
    }

    public String getEmail()
    {
        return m_email;
    }

    public void setEmail(String email)
    {
        m_email = email;
    }
}
