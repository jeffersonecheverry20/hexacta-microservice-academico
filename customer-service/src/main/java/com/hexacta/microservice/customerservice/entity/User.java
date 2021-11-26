package com.hexacta.microservice.customerservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    //private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "userId")
    private int userId;
    @Column
    private String name;
    @Column
    private String lastname;
    @Column
    private String username;
    @Column
    private String password;
    @ManyToOne()
    @JoinColumn(name = "role", referencedColumnName = "roleId", nullable = false)
    @JsonManagedReference
    private Role role;

}
