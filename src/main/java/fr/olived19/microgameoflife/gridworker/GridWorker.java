package fr.olived19.microgameoflife.gridworker;

import fr.olived19.microgameoflife.core.Automaton;
import fr.olived19.microgameoflife.core.World;
import fr.olived19.microgameoflife.messages.Message;
import fr.olived19.microgameoflife.messages.NewWorldGenerated;
import fr.olived19.microgameoflife.messages.NextWorldRequested;
import fr.olived19.microgameoflife.queue.QueueConnection;
import fr.olived19.microgameoflife.queue.RPCAction;
import fr.olived19.microgameoflife.queue.RPCServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GridWorker {

    private static final Logger LOG = LoggerFactory.getLogger(GridWorker.class);

    public static void main(String[] argv) {
        QueueConnection queueConnection = new QueueConnection();
        final String messageQueueHost = "messagequeue";
        queueConnection.setHost(messageQueueHost);
        LOG.info("Connecting to {}", messageQueueHost);
        queueConnection.connect();
        LOG.info("Connection successful to {}", messageQueueHost);

        final Automaton automaton = new Automaton();
        RPCServer server = new RPCServer(queueConnection);
        RPCAction action = new RPCAction() {
            public Message execute(String message) {
                NextWorldRequested request = NextWorldRequested.fromString(message);
                LOG.info("Received NextWorldRequested with id : {}", request.correlationId());
                World world = WorldHelper.worldFromRequest(request);
                World nextWorld = automaton.createNextGeneration(world);
                return new NewWorldGenerated(request.correlationId(), WorldHelper.gridToBooleanList(nextWorld), nextWorld.getGeneration());
            }
        };
        server.consumeMessage(action);
        LOG.info("GridWorker ready");
    }
}
