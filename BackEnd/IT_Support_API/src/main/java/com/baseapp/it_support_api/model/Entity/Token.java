package com.baseapp.it_support_api.model.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "token")
    private String token;
    @Column(name = "is_logged_out")
    private boolean loggedOut;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
