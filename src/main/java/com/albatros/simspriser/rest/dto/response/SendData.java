package com.albatros.simspriser.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendData {
    private long id;
    private int position;
    private boolean right;
    private int time;
}