package fr.olived19.microgameoflife.gridworker;

import fr.olived19.microgameoflife.core.Automaton;
import fr.olived19.microgameoflife.core.World;
import fr.olived19.microgameoflife.messages.Message;
import fr.olived19.microgameoflife.messages.NewWorldGenerated;
import fr.olived19.microgameoflife.messages.NextWorldRequested;
import fr.olived19.microgameoflife.queue.QueueConnection;
import fr.olived19.microgameoflife.queue.RPCAction;
import fr.olived19.microgameoflife.queue.RPCServer;


public class GridWorker {

    public static void main(String[] argv) {
        QueueConnection queueConnection = new QueueConnection();
        queueConnection.setHost("messagequeue");
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
