package com.example.userdirectory.userdirectorycassandra.repository;

import com.example.userdirectory.userdirectorycassandra.model.User;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserRepository extends CassandraRepository<User, Long> {

}
