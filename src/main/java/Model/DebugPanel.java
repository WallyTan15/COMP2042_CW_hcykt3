/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Model;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * DebugPanel is a class that deals with the function of the debug panel.
 */
public class DebugPanel extends JPanel {

    private static final Color DEF_BKG = Color.WHITE;


    private JButton skipLevel;
    private JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private Wall wall;

    /**
     * DebugPanel constructor initialize the values of the debug panel.
     * Create the buttons to skip the level and reset the number of ball.
     * Create the sliders for the speed of ball in both x-axis and y-axis directions.
     * @param wall
     */
    public DebugPanel(Wall wall){

        this.wall = wall;

        initialize();

        skipLevel = makeButton("Skip Level",e -> wall.nextLevel());
        resetBalls = makeButton("Reset Balls",e -> wall.resetBallCount());

        ballXSpeed = makeSlider(-4,4,e -> wall.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4,4,e -> wall.setBallYSpeed(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

    }

    /**
     * initialize is a method that initialize the debug panel.
     */
    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    /**
     * makeButton is a method that creates the button.
     * @param title  the tittle of the button
     * @param e      the ActionListener
     * @return       return the button
     */
    private JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return  out;
    }

    /**
     * makeSlider is a method that creates the slider.
     * @param min  the minimum value of the slider
     * @param max  the maximum value of the slider
     * @param e    the ChangeListener
     * @return     return the slider
     */
    private JSlider makeSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    /**
     * setValues is a method that set the speed of the ball in x-axis and y-axis directions.
     * @param x  the speed of ball in x-axis
     * @param y  the speed of ball in y-axis
     */
    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

    /**
     * getSpeedXSlider is a method that returns the slider which controls the speed of the ball in x-axis
     * @return  return the slider which controls the speed of the ball in x-axis
     */
    public JSlider getSpeedXSlider(){
        return ballXSpeed;
    }

    /**
     * getSpeedYSlider is a method that returns the slider which controls the speed of the ball in y-axis
     * @return  return the slider which controls the speed of the ball in y-axis
     */
    public JSlider getSpeedYSlider(){
        return ballYSpeed;
    }

}
