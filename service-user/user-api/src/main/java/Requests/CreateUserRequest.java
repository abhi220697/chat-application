package Requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


import jakarta.validation.constraints.*;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                       // Lombok: generates getters, setters, toString, equals, hashCode
@NoArgsConstructor          // Lombok: generates no-args constructor
@AllArgsConstructor         // Lombok: generates all-args constructor
public class CreateUserRequest {

    @Schema(description = "Full name of the user", example = "Abhishek Raj")
    @NotBlank
    private String name;

    @Schema(description = "Age of the user", example = "28", minimum = "1", maximum = "120")
    @Min(1)
    @Max(120)
    private Integer age;

    @Schema(description = "Sex of the user", example = "Male", allowableValues = {"Male", "Female", "Other"})
    @Pattern(regexp = "Male|Female|Other")
    private String sex;

    @Schema(description = "10-digit phone number", example = "9876543210")
    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNo;

    @Schema(description = "Email address of the user", example = "test@example.com")
    @Email
    @NotBlank
    private String email;

    @Schema(description = "Password for the account", example = "P@ssw0rd123")
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}

