package com.emailapp.emailservice;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailRequest implements Serializable {
    private String to;
    private String subject;
    private String body;
}