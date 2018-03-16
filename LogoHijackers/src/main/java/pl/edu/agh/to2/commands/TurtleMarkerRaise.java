package pl.edu.agh.to2.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.controllers.OverviewController;
import pl.edu.agh.to2.models.Marker;
import pl.edu.agh.to2.models.MarkerState;
import pl.edu.agh.to2.models.Turtle;

import java.util.ArrayDeque;

public class TurtleMarkerRaise implements Command {
    private static final Logger log = LogManager.getLogger(TurtleMarkerRaise.class);
    private Marker marker;
    private ArrayDeque<MarkerState> prevStates = new ArrayDeque<>();

    public TurtleMarkerRaise(Marker marker) {
        this.marker = marker;
    }

    @Override
    public void execute() {
        log.debug("Executing command marker raise");
        this.prevStates.addFirst(marker.getState());
        this.marker.setState(MarkerState.RAISED);
    }

    @Override
    public void revert() {
        log.debug("Reverting command marker raise");
        if (!prevStates.isEmpty()){
            this.marker.setState(prevStates.pollFirst());
        } else {
            log.warn("Trying to revert unexecuted command marker raise");
        }
    }

    @Override
    public String toString() {
        return "pod";
    }
}
