package in.anurag.crudSpingBootDemo.entity;

import in.anurag.crudSpingBootDemo.model.Address;
import in.anurag.crudSpingBootDemo.model.BooleanToStringConverter;
import in.anurag.crudSpingBootDemo.model.StudentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "school_name", nullable = false, length = 100)
    private String name;

    @Column(unique = true, length = 150, insertable = true, updatable = true)
    private String email;

    // when you want to set the precious example 100.00
    @Column(precision = 5, scale = 2)
    private BigDecimal percentage;

    private LocalDate dateOfBirth;

    // when you want to store enum as string
    @Enumerated(EnumType.STRING)
    private StudentStatus studentStatus;

    // when you want to save large object
//    @Lob
    private String profileDescription;

    // when you don't want to save column in db table
    @Transient
    private String displayName;

    @Convert(converter = BooleanToStringConverter.class)
    private Boolean isMonitor;

    private int age;
    private int rollNo;
    private String subject;
    private Boolean deleted;

    @ElementCollection
    @CollectionTable(
            name = "school_skills",
            joinColumns = @JoinColumn(name = "school_id")
    )
    private Set<String> skills;

    @ElementCollection
    @CollectionTable(
            name="school_address",
            joinColumns = @JoinColumn(name = "school_id")
    )
    private Set<Address> addresses;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "houseNo",
                    column = @Column(name = "current_house_no")
            ),
            @AttributeOverride(
                    name = "street",
                    column = @Column(name = "current_street")
            ),
            @AttributeOverride(
                    name = "city",
                    column = @Column(name = "current_city")
            ),
            @AttributeOverride(
                    name = "state",
                    column = @Column(name = "current_state")
            ),
            @AttributeOverride(
                    name = "pincode",
                    column = @Column(name = "current_pincode")
            )
    })
    private Address currentAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "houseNo",
                    column = @Column(name = "permanent_house_no")
            ),
            @AttributeOverride(
                    name = "street",
                    column = @Column(name = "permanent_street")
            ),
            @AttributeOverride(
                    name = "city",
                    column = @Column(name = "permanent_city")
            ),
            @AttributeOverride(
                    name = "state",
                    column = @Column(name = "permanent_state")
            ),
            @AttributeOverride(
                    name = "pincode",
                    column = @Column(name = "permanent_pincode")
            )
    })
    private Address permanentAddress;

    public School() {

    }

    public School(Long id, String name, String email, BigDecimal percentage, LocalDate dateOfBirth,
                  StudentStatus studentStatus, String profileDescription, String displayName,
                  Boolean isMonitor, int age, int rollNo, String subject, Boolean deleted,
                  Set<String> skills, Set<Address> addresses, Address currentAddress,
                  Address permanentAddress, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.percentage = percentage;
        this.dateOfBirth = dateOfBirth;
        this.studentStatus = studentStatus;
        this.profileDescription = profileDescription;
        this.displayName = displayName;
        this.isMonitor = isMonitor;
        this.age = age;
        this.rollNo = rollNo;
        this.subject = subject;
        this.deleted = deleted;
        this.skills = skills;
        this.addresses = addresses;
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private LocalDateTime createdAt;

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public StudentStatus getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getMonitor() {
        return isMonitor;
    }

    public void setMonitor(Boolean monitor) {
        isMonitor = monitor;
    }

    private LocalDateTime updatedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
