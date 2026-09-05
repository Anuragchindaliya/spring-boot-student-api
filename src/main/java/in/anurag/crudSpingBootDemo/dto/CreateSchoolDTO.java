package in.anurag.crudSpingBootDemo.dto;

import jakarta.validation.constraints.*;

public class CreateSchoolDTO {
    @NotBlank(message = "Name cannot be null/Empty or blank")
    @Size(min = 2,max=50,message = "Student name must be within 2 to 50 character long")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 18,message = "Student must be atleast 18 years old")
    private int age;


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
