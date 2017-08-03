import fr.olived19.microgameoflife.core.Automaton;
import fr.olived19.microgameoflife.core.World;
import messages.Message;
import messages.NewWorldGenerated;
import messages.NextWorldRequested;
import queue.QueueConnection;
import queue.RPCAction;
import queue.RPCServer;

import java.util.ArrayList;


public class GridWorker {

    public static void main(String[] argv) {
        QueueConnection queueConnection = new QueueConnection();
        queueConnection.setHost("localhost");
        queueConnection.connect();

        final Automaton automaton = new Automaton();
        RPCServer server = new RPCServer(queueConnection);
        RPCAction action = new RPCAction() {
            public Message execute(String message) {
                NextWorldRequested request = NextWorldRequested.fromString(message);
                World world = WorldHelper.worldFromRequest(request);
                World nextWorld = automaton.createNextGeneration(world);
                return new NewWorldGenerated(request.correlationId(), WorldHelper.gridToBooleanList(nextWorld), nextWorld.getGeneration());
            }
        };
        server.consumeMessage(action);
    }
}
