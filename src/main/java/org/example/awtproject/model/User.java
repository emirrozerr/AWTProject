package org.example.awtproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Column(updatable = false, name= "created_at_utc")
    @CreationTimestamp
    LocalDateTime createdAtUTC;

    @Column(updatable = false, name= "updated_at_utc")
    @UpdateTimestamp
    LocalDateTime updatedAtUTC;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private List<Role> roles = new ArrayList<>();

}
