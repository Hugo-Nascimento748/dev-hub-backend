package com.devhub.api.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "tags")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Tag implements Serializable{
    private static final long serialVersion = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    public Tag(String name){
        this.name = name.toLowerCase().trim();
    }
}
