package ru.mirea.pr16.models;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Departure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String date;
    @ManyToOne
    @JoinColumn(name="post_id", nullable=false)
    private PostOffice post;
}
