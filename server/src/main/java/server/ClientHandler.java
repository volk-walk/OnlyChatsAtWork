package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    //конструктор подключаемого клиента
    public ClientHandler (Server server, Socket socket){
        try {
            this.server = server;
            this.socket = socket;

            //инициализируем отправку и чтение данных
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            //отдельный поток для чтения приходящих данных,
            //чтобы можно было читать сразу нескольким клиентам, обновляя при этом графику окна чата
            new Thread(() -> {
                try {
                    //в бесконечном цикле ждем пока нам что-либо напишут
                    while (true) {
                        //считываем приходящие данные
                        String clientMessage = in.readUTF();
                        //при отправке "/end" закрываем соединение
                        if (clientMessage.equals("/end")) {
                            out.writeUTF("/end");
                            break;
                        }
                        //отправляем введенное сообщение всем подключенным клиентам
                        server.broadcastMessage(clientMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    //обязательно удаляем клиента из списка подключенных клиентов,
                    // при его выходе из чата
                    server.unsubscribe(this);
                    System.out.println("Client " +socket.getRemoteSocketAddress() + " disconnect");
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //метод для отправки сообщений, написанных клиентом
    public void sendMessage (String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
