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

import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * GameFrame is a class that deals with the game frame window.
 */
public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";

    private GameBoard gameBoard;
    private HomeMenu homeMenu;
    private InfoMenu infoMenu;
    private ScoreBoard scoreBoard;
    private GameOverMenu gameOverMenu;

    private boolean gaming;

    /**
     * GameFrame constructor creates a border layout.
     * Create the HomeMenu object.
     * Create the GameBoard object.
     * Create the InfoMenu object.
     * Create the ScoreBoard object.
     * Create the GameOverMenu object.
     * Add the HomeMenu to the container when GameFrame object is being created.
     */
    public GameFrame(){
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        gameBoard = new GameBoard(this);

        homeMenu = new HomeMenu(this,new Dimension(450,300));

        infoMenu = new InfoMenu(this,new Dimension(450,300));

        scoreBoard = new  ScoreBoard(this,new Dimension(450,300),gameBoard.getScoreController());

        gameOverMenu = new GameOverMenu(this,gameBoard.getScoreController());

        this.add(homeMenu,BorderLayout.CENTER);

        this.setUndecorated(true);

    }

    /**
     * initialize is a method that initialize and show the game frame.
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * enableGameBoard is a method that transform the game frame from HomeMenu to GameBoard screen.
     */
    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenu);
        this.add(gameBoard,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    /**
     * enableHomeMenu is a method that transform the game frame to HomeMenu screen.
     */
    public void enableHomeMenu() {
        this.dispose();
        this.remove(infoMenu);
        this.remove(scoreBoard);
        this.remove(gameOverMenu);
        this.add(homeMenu,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * enableScoreBoard is a method that transform the game frame from HomeMenu to ScoreBoard screen.
     */
    public void enableScoreBoard() {
        this.dispose();
        this.remove(homeMenu);
        this.add(scoreBoard,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * enableInfoMenu is a method that transform the game frame from HomeMenu to InfoMenu screen.
     */
    public void enableInfoMenu() {
        this.dispose();
        this.remove(homeMenu);
        this.add(infoMenu,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * enableGameOverMenu is a method that transform the game frame from GameBoard to GameOverMenu screen.
     */
    public void enableGameOverMenu() {
        this.dispose();
        this.remove(gameBoard);
        this.add(gameOverMenu,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * autoLocate is a method that set the position of the game frame screen based on the device's screen size.
     */
    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    /**
     * windowGainedFocus is a method that deals with the implementations when the game frame is in focus.
     * @param windowEvent  the event to show the status of the screen
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /**
     * widowLostFocus is a method that deals with the implementations when the game frame is not in focus.
     * @param windowEvent  the event to show the status of the screen
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }
}
