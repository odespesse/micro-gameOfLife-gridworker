import fr.olived19.microgameoflife.core.Cell;
import fr.olived19.microgameoflife.core.World;
import messages.NextWorldRequested;

import java.util.ArrayList;
import java.util.List;

public class WorldHelper {

    public static World worldFromRequest(NextWorldRequested request) {
        List<List<Cell>> grid = new ArrayList();
        for(List<Boolean> list : request.getGrid()) {
            List<Cell> nlc = new ArrayList();
            for(Boolean b : list) {
                Cell c = b ? Cell.alive : Cell.dead;
                nlc.add(c);
            }
            grid.add(nlc);
        }
        return new World(grid, request.getGeneration());
    }

    public static List<List<Boolean>> gridToBooleanList(World world) {
        List<List<Boolean>> grid = new ArrayList();
        for(List<Cell> list : world.getGridAsList()) {
            List<Boolean> nlc = new ArrayList();
            for(Cell c : list) {
                Boolean b = c == Cell.alive ? Boolean.TRUE: Boolean.FALSE;
                nlc.add(b);
            }
            grid.add(nlc);
        }
        return grid;
    }
}
