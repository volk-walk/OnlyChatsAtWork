package server;

import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {

    //внутренний класс юзеров имеющих логин, пароль и ник
    private class User {
        String login;
        String password;
        String nickname;

        //конструктор юзеров
        public User(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;

        }
    }

    //создаем List юзеров (вложенного класса)
    private List<User> users;

    //добавляем в этом методе Юзеров в наш List
    public SimpleAuthService() {
        users = new ArrayList<>();
        users.add(new User("qaz", "qaz", "qaz"));
        users.add(new User("wsx", "wsx", "wsx"));
        users.add(new User("edc", "edc", "edc"));
        for (int i = 0; i < 10; i++) {
            users.add(new User("log" + i, "pas" + i, "nick" + i));
        }
    }

    //возвращаем nickname юзеров по совпадению логина и пароля, если находим такие совпадения
    //если совпадений нет, возвращаем null
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
