package com.example.redistest.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewMessage{
    private String data;
    private String roomId;

    @Override
    public String toString() {
        return "Message{" +
                "data='" + data + '\'' +
                '}';
    }
}
