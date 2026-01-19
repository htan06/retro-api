package com.retro.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role extends BaseEntity{
    @Column(name = "role_name")
    private String roleName;

    @Column(name = "descriptions")
    private String descriptions;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
