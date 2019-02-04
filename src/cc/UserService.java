package cc;

import java.util.List;

public class UserService {

    private UserDAO userDAO = UserDAO.getUserDAO();

    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    public User createUser(User user) {
        return userDAO.createUser(user);
    }

    // implement delete

    // implement update

}
