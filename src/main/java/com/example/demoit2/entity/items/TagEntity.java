package com.example.demoit2.entity.items;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "tags")
@NoArgsConstructor
@AllArgsConstructor
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String tagName;


    @ManyToMany(mappedBy = "tags")
    private List<ItemEntity> item;

    public TagEntity(Long id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }
}
