package com.example.demoit2.entity.collection;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "topics")
@NoArgsConstructor
@AllArgsConstructor
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String topicName;
}