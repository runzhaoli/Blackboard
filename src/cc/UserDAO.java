package cc;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class UserDAO {

    private Map<Integer, User> users;
    private static UserDAO unique;

    private UserDAO() {
        this.users = new ConcurrentHashMap<>();
        User u = new User("barney", 12);
        users.put(u.getId(), u);
    }

    public static UserDAO getUserDAO() {
        if (unique == null) {
            unique = new UserDAO();
        }
        return unique;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUser(int id) {
        return users.get(id);
    }

    public User createUser(User input) {
        User u = new User(input.getName(), input.getAge());
        users.put(u.getId(), u);
        return u;
    }

}
