package ru.rdavydov.tokenizier.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.rdavydov.tokenizier.entities.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Integer> {
    public List<User> findByEmail(String email);

    public List<User> findByEmailAndPassword(String email, String password);

}
