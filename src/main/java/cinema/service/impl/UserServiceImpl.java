package cinema.service.impl;

import cinema.dao.UserDao;
import cinema.model.User;
import cinema.service.UserService;
import cinema.util.HashUtil;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        byte[] salt = HashUtil.getSalt();
        String hashedPassword = HashUtil.hashPassword(user.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(hashedPassword);
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id).orElseThrow(()
                -> new NoSuchElementException("There no user with id " + id));
    }
}
