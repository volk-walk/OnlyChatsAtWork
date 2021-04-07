package server;

public interface AuthService {
    /**
     * Метод полученея никнейма по логину и паролю
     * Если учетной записи с таким логином и паролем нет, то вернет null
     * Если учетная запись есть, то вернет никнейм
     * @return никнейм если есть совпадения по логину и паролю, null - если совпадений не будет
     * */
    String getNicknameByLoginAndPassword(String login, String password);
}
