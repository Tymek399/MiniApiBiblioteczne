package com.example.miniapibiblioteczne.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowRequestDto {
    @NotBlank(message = "Username cannot be empty")
    private String userName;
    @NotBlank(message = "Barcode cannot be empty")
    private String barcode;
}
