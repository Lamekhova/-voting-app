package com.example.sweater.service;

import com.example.sweater.model.User;
import com.example.sweater.repository.CrudUserRepository;
import com.example.sweater.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UserService {

    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public UserService(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    public User addNew(User user){
        Assert.notNull(user, "user must not be null");
        return crudUserRepository.save(user);
    }

    public User getById(Integer id) throws NotFoundException {
        return crudUserRepository.findById(id).orElse(null);
    }

    public User getByEmail(String email) throws NotFoundException {
        return crudUserRepository.findUserByEmail(email);
    }

    public List<User> getAll() {
        return crudUserRepository.findAll(SORT_NAME_EMAIL);
    }

    public void update(Integer id, User user) throws NotFoundException {
        Assert.notNull(user, "user must not be null");
        crudUserRepository.save(user);
    }

    public void deleteById(Integer id) throws NotFoundException {
        crudUserRepository.deleteById(id);
    }
}
