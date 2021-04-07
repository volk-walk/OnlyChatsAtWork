package server;

import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {


    private class User {
        String login;
        String password;
        String nickname;

        public User(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;

        }
    }

    private List<User> users;
    public SimpleAuthService() {
        users = new ArrayList<>();
        users.add(new User("qaz", "qaz", "qaz"));
        users.add(new User("wsx", "wsx", "wsx"));
        users.add(new User("edc", "edc", "edc"));
        for (int i = 0; i < 10; i++) {
            users.add(new User("log" + i, "pas" + i, "nick" + i));
        }
    }
    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {

        for (User u:users) {
            if(u.login.equals(login) && u.password.equals(password)){
                return u.nickname;
            }
        }
        return null;
    }
}
