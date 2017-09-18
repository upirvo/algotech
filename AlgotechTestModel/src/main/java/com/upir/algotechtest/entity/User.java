package com.upir.algotechtest.entity;

import com.upir.algotechtest.entity.AbstractUser;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "User")
public class User extends AbstractUser {
    private static final long serialVersionUID = 838985504L;
    transient
    private int age;
    transient
    private LocalDate birth;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
}
