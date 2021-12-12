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
package View;

import Controller.DebugController;
import Controller.GameController;
import Model.Ball;
import Model.DebugPanel;
import Model.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * DebugConsole is a class that deals with the function of the debug console.
 */
public class DebugConsole extends JDialog implements WindowListener{

    private static final String TITLE = "Debug Console";


    private JFrame owner;
    private DebugController debugController;
    private GameController gameController;
    private GameBoard gameBoard;

    /**
     * DebugConsole constructor deals with the view of the debug console when it is being created.
     * @param owner           the Jframe owner object
     * @param gameController  the GameController object
     * @param gameBoard       the GameBoard object
     */
    public DebugConsole(JFrame owner, GameController gameController, DebugController debugController, GameBoard gameBoard){

        this.gameController = gameController;
        this.owner = owner;
        this.gameBoard = gameBoard;
        initialize();

        this.debugController = debugController;
        this.add(this.debugController.getDebugPanel(),BorderLayout.CENTER);


        this.pack();
    }

    /**
     * initialize is a method that initialize and show the debug console.
     */
    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }

    /**
     * setLocation is a method that set the position of the debug console.
     */
    private void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }


    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    /**
     * WindowActivated is a method that deals with implementation of the debug console when the window is activated.
     * @param windowEvent  the event to show the status of the window
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = gameController.getBall();
        debugController.setBallSpeedValues(b.getSpeedX(), b.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
