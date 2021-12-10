package Controller;

import Model.DebugPanel;

/**
 * DebugController is a class that act as the coordinator between DebugPanel class and the classes in package View.
 */
public class DebugController {
    private DebugPanel debugPanel;

    /**
     * DebugPanel constructor gets the DebugPanel object reference.
     * @param debugPanel  the DebugPanel object
     */
    public DebugController(DebugPanel debugPanel){
        this.debugPanel = debugPanel;
    }

    /**
     * getDebugPanel is a method that returns the DebugPanel object.
     * @return  return the DebugPanel object
     */
    public DebugPanel getDebugPanel(){
        return debugPanel;
    }

    /**
     * setBallSpeedValues is a method that invokes the setValues method in the DebugPanel class.
     * Set the speed values of ball in x-axis and y-axis directions.
     * @param x  speed of the ball in x-axis direction
     * @param y  speed of the ball in y-axis direction
     */
    public void setBallSpeedValues(int x, int y){
        debugPanel.setValues(x, y);
    }
}
