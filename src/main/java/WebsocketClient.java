import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WebsocketClient {

    List<String> messaggi = new ArrayList<>();
    private final static String INDIRIZZO_SERVER_CHAT = "";

    public static void main(String[] args) throws Exception {

        final ChatClientEndpoint clientEndPoint =
                new ChatClientEndpoint(new URI(INDIRIZZO_SERVER_CHAT));

        Scanner scanner = new Scanner(System.in);
        String msg = "";
        while (!"ESCI".equals(msg)) {
            System.out.print("> ");
            msg = scanner.nextLine().trim();
            clientEndPoint.sendMessage(msg);
        }

    }
}
