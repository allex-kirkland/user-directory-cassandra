package com.example.userdirectory.userdirectorycassandra.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String phoneNumber;
    private String address;

    @PrimaryKey
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o){
       if(this == o){
           return true;
       }
       if(!(o instanceof User)){
           return false;
       }
       User user = (User) o;
       return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name)
               && Objects.equals(this.phoneNumber, user.phoneNumber) && Objects.equals(this.address, user.address);
    }

    @Override
    public int hashCode(){
       return Objects.hash(this.id, this.name, this.phoneNumber, this.address);
    }

    @Override
    public String toString(){
       return "User{id="+this.id+", name='"+this.name+"'"+", phoneNumber='"+this.phoneNumber+"'"+", address='"+this.address+"'}";
    }

}
