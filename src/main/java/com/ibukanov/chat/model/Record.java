package com.ibukanov.chat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Record {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    private User user;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Date date;

    public static Record of(User user, String message) {
        Record record = new Record();
        record.setUser(user);
        record.setMessage(message);
        record.setDate(new Date());
        return record;
    }



}
