package Requests;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequest {

    @Schema(description = "Full name of the user", example = "Abhishek Raj")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Age of the user", example = "28", minimum = "1", maximum = "120")
    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 120, message = "Age must be realistic")
    private Integer age;

    @Schema(description = "Sex of the user", example = "Male", allowableValues = {"Male", "Female", "Other"})
    @Pattern(regexp = "Male|Female|Other", message = "Sex must be Male, Female, or Other")
    private String sex;

    @Schema(description = "Phone number of the user", example = "9876543210")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String phoneNo;

    @Schema(description = "Email address of the user", example = "test@example.com")
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
}
