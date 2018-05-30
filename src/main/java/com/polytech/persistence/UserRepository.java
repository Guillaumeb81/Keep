package com.polytech.persistence;
import com.polytech.services.User;

public interface UserRepository {
    void save(User user);
}
