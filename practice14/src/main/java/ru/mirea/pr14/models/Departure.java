package ru.mirea.pr14.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Departure {
    private long id;
    private String type;
    private String date;
}
