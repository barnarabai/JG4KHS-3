package edu.corvinus.JG4KHS.repository;

import edu.corvinus.JG4KHS.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
