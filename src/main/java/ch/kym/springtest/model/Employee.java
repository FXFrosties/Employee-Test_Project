package ch.kym.springtest.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import ch.kym.springtest.request.EmployeeRequest;;

@Entity
@Setter
@Getter
@ToString
@Table(name = "tbl_employee")
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //alias @JsonProperty("full_name")
    @NotBlank(message = "name should not be null")
    private String name;

    //removing @JsonIgnore
    private Long age = 0L;

    private String location;

    @Email(message = "E-Mail must be valid")
    private String email;

    @NotBlank(message = "department should not be null")
    private String department;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name="updated_at", nullable=false)
    private Date updatedAt;

    @JoinColumn(name = "team_id")
    @OneToOne
    private Team team;

    public Employee(EmployeeRequest req){
        this.name = req.getName();
        this.email = req.getEmail();
        this.department = req.getDepartment();
    }
}
