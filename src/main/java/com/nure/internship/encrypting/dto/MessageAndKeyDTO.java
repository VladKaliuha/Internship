package com.nure.internship.encrypting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageAndKeyDTO {

    private String key;

    private String message;
}
