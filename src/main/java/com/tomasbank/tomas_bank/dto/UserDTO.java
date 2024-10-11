package com.tomasbank.tomas_bank.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@ToString
public class UserDTO {
    private String name;
    private String surname;
    private String id;
}
