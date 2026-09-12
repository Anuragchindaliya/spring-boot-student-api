package in.anurag.crudSpingBootDemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import in.anurag.crudSpingBootDemo.model.StudentStatus;
import jakarta.validation.constraints.*;
import jdk.jfr.Timestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Timestamp
public class CreateSchoolDTO {
    @NotBlank(message = "Name cannot be null/Empty or blank")
    @Size(min = 2,max=50,message = "Student name must be within 2 to 50 character long")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 18,message = "Student must be atleast 18 years old")
    private int age;

    private String email;

    private int rollNo;

    private String subject;

    private String profileDescription;

    private StudentStatus studentStatus;

    @JsonProperty("isMonitor")
    private Boolean isMonitor;

    private LocalDate dateOfBirth;

    private BigDecimal percentage;

    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public StudentStatus getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public Boolean getMonitor() {
        return isMonitor;
    }

    public void setMonitor(Boolean monitor) {
        isMonitor = monitor;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
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


}
