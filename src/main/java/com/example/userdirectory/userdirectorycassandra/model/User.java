package com.example.userdirectory.userdirectorycassandra.model;


import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @PrimaryKey
    private Long id;
    private String name;
    private String phoneNumber;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;

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
               && Objects.equals(this.phoneNumber, user.phoneNumber) && Objects.equals(this.street1, user.street1)
               && Objects.equals(this.street2, user.street2) && Objects.equals(this.city, user.city)
               && Objects.equals(this.state, user.state) && Objects.equals(this.zip, user.zip);
    }

    @Override
    public int hashCode(){
       return Objects.hash(this.id, this.name, this.phoneNumber, this.street1, this.street2, this.city, this.state, this.zip);
    }

    @Override
    public String toString(){
       return "User{id="+this.id+", name='"+this.name+"'"+", phoneNumber='"+this.phoneNumber+"'"+", street1='"+this.street1+", street2='"+this.street2+", city='"+this.city+", state='"+this.state+", zip='"+this.zip+"'}";
    }

}
