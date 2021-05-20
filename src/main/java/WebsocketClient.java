import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WebsocketClient {

    List<String> messaggi = new ArrayList<>();
    private static String INDIRIZZO_SERVER_CHAT = "";
    private static String NOME_UTENTE = "";

    public static void main(String[] args) throws Exception {

        for (int i=0; i<args.length; i++) {
            if (args[i].equals("-url")) {
                INDIRIZZO_SERVER_CHAT = args[i+1].replace("\"", "");
            }
            if (args[i].equals("-nome")) {
                NOME_UTENTE = args[i+1].replace("\"", "");
            }
        }

        final ChatClientEndpoint clientEndPoint =
                new ChatClientEndpoint(new URI(INDIRIZZO_SERVER_CHAT));

        Scanner scanner = new Scanner(System.in);
        String msg = "";
        while (!"ESCI".equals(msg)) {
            System.out.print("> ");
            msg = scanner.nextLine().trim();
            clientEndPoint.sendMessage(NOME_UTENTE + ": " + msg);
        }

    }
}
