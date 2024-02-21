public class Main {
    public static void main(String[] args) {

        ServerWindow serverWindow = new ServerWindow(); // создается объект сервера
        new ClientGUI(serverWindow); // объект передается двум клиентам (чтоб они могли коммуницировать)
        new ClientGUI(serverWindow);
    }
}