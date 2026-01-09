package Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.UUID;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @Schema(description = "Unique user identifier (UUID)", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;

    @Schema(description = "Full name of the user", example = "Abhishek Raj")
    private String name;

    @Schema(description = "Age of the user", example = "28")
    private Integer age;

    @Schema(description = "Sex of the user", example = "Male", allowableValues = {"Male", "Female", "Other"})
    private String sex;

    @Schema(description = "Phone number of the user", example = "9876543210")
    private String phoneNo;

    @Schema(description = "Email address of the user", example = "test@example.com")
    private String email;

    @Schema(description = "When the user account was created", example = "2025-01-15T10:15:30")
    private LocalDateTime createdAt;

    @Schema(description = "When the user account was last updated", example = "2025-02-01T14:20:45")
    private LocalDateTime updatedAt;
}