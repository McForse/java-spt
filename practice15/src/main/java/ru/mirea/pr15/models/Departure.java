package ru.mirea.pr15.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
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
