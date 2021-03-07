package ru.mirea.pr22.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String cityName;
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private Set<Departure> departures;
}
