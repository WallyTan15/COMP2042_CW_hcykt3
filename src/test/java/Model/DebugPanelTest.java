package Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class DebugPanelTest {
    Wall wall = new Wall(new Rectangle(0,0,600,450),30,3,6/2,new Point(300,430));
    DebugPanel debugPanel = new DebugPanel(wall);

    @Test
    public void setSliderValues(){
        debugPanel.setValues(1,1);
        assertEquals(1,debugPanel.getSpeedXSlider().getValue());
        assertEquals(1,debugPanel.getSpeedYSlider().getValue());
    }
}
