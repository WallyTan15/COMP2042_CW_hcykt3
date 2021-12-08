package Controller;

import Model.DebugPanel;

public class DebugController {
    private DebugPanel debugPanel;

    public DebugController(DebugPanel debugPanel){
        this.debugPanel = debugPanel;
    }

    public DebugPanel getDebugPanel(){
        return debugPanel;
    }

    public void setBallSpeedValues(int x, int y){
        debugPanel.setValues(x, y);
    }
}
