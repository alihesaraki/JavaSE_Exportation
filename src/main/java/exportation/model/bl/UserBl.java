package exportation.model.bl;

import exportation.controller.exception.NoUserFoundException;
import lombok.Getter;
import exportation.model.da.UserDa;
import exportation.model.entity.User;
import exportation.model.tools.CRUD;

import java.util.List;

public class UserBl implements CRUD<User> {
    @Getter
    private static UserBl userBl = new UserBl();

    private UserBl() {
    }

    //save
    @Override
    public User save(User user) throws Exception {
        try (UserDa userDa = new UserDa()) {
            userDa.save(user);
            return user;
        }
    }

    //edit
    @Override
    public User edit(User user) throws Exception {
        try (UserDa userDa = new UserDa()) {
            if (userDa.findById(user.getId()) != null) {
                userDa.edit(user);
                return user;
            } else {
                throw new NoUserFoundException();
            }
        }
    }

    //remove
    @Override
    public User remove(int id) throws Exception {
        try (UserDa userDa = new UserDa()) {
            User user = userDa.findById(id);
            if (user != null) {
                userDa.remove(id);
                return user;
            } else {
                throw new NoUserFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<User> findAll() throws Exception {
        try (UserDa userDa = new UserDa()) {
            List<User> user = userDa.findAll();
            if (!user.isEmpty()) {
                return user;
            } else {
                throw new NoUserFoundException();
            }
        }
    }

    //findById
    @Override
    public User findById(int id) throws Exception {
        try (UserDa userDa = new UserDa()) {
            User user = userDa.findById(id);
            if (user != null) {
                return user;
            } else {
                throw new NoUserFoundException();
            }
        }
    }

    //findByUsername
    public User findByUsername(String username) throws Exception {
        try (UserDa userDa = new UserDa()) {
            User user = userDa.findByUsername(username);
            if (user != null) {
                return user;
            } else {
                throw new NoUserFoundException();
            }
        }
    }
}
