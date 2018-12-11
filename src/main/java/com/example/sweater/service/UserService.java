package com.example.sweater.service;

import com.example.sweater.security.UserPrincipal;
import com.example.sweater.model.User;
import com.example.sweater.repository.CrudUserRepository;
import com.example.sweater.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.example.sweater.util.ExceptionUtil.checkNotFound;
import static com.example.sweater.util.ExceptionUtil.checkNotFoundObjectWithId;
import static com.example.sweater.util.ExceptionUtil.checkNotFoundWithId;

@Service
public class UserService implements UserDetailsService {

    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public UserService(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    public User addNew(User user) {
        Assert.notNull(user, "user must not be null");
        return crudUserRepository.save(user);
    }

    public User getById(Integer id) throws NotFoundException {
        return checkNotFoundObjectWithId(crudUserRepository.findById(id).orElse(null), id);
    }

    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(crudUserRepository.findUserByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return crudUserRepository.findAll(SORT_NAME_EMAIL);
    }

    public void update(Integer id, User user) throws NotFoundException {
        Assert.notNull(user, "user must not be null");
        checkNotFoundObjectWithId(getById(user.getId()), id);
        crudUserRepository.save(user);
    }

    public void deleteById(Integer id) throws NotFoundException {
        checkNotFoundWithId(crudUserRepository.delete(id) != 0, id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = crudUserRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + email + " was not found");
        }
        return new UserPrincipal(user);
    }
}
