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

import Controller.GameController;
import Controller.ScoreController;
import Model.*;
import View.DebugConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;


/**
 * GameBoard is a class that deals with the view of the gameplay.
 */
public class GameBoard extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private Timer gameTimer;

    private GameFrame owner;
    private GameController gameController;
    private ScoreController scoreController;

    private String message;

    private boolean showPauseMenu;

    private Font menuFont;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private int strLen;

    private DebugConsole debugConsole;

    /**
     * GameBoard constructor deals with the view of the gameplay and gameTimer.
     * Create a GameController object.
     * Create a ScoreController object.
     * Create a DebugConsole object.
     * Create a Timer object.
     * @param owner  the Jframe owner object
     */
    public GameBoard(JFrame owner){
        super();

        strLen = 0;
        showPauseMenu = false;

        this.owner = (GameFrame) owner;

        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);


        this.initialize();
        message = "";
        gameController = new GameController(new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430)));
        scoreController = new ScoreController(gameController.getWall().score);

        debugConsole = new DebugConsole(owner,gameController,this);
        //initialize the first level
        gameController.nextGameLevel();

        gameTimer = new Timer(10,e ->{
            gameController.move();
            gameController.findWallImpacts();
            message = String.format("Bricks: %d Balls: %d Score: %d",gameController.getCurrentBrickCount(),gameController.getCurrentBallCount(),scoreController.getCurrentScore());
            if(gameController.isCurrentBallLost()){
                if(gameController.isCurrentBallEnd()){
                    gameController.wallReset();
                    message = "Game over";
                    this.owner.enableGameOverMenu();
                    scoreController.readScoreFile();
                    scoreController.sortGameRecord();
                    scoreController.writeScoreFile();
                }
                gameController.ballReset();
                gameTimer.stop();
            }
            else if(gameController.isGameDone()){
                if(gameController.hasNextLevel()){
                    message = "Go to Next Level";
                    gameTimer.stop();
                    gameController.ballReset();
                    gameController.wallReset();
                    gameController.nextGameLevel();
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                }
            }

            repaint();
        });

    }


    /**
     * initialize is a method that initializes the game board.
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * paint is a method that draw the game board view.
     * Draw the message on game board screen.
     * Draw the ball.
     * Draw the brick.
     * Draw the player.
     * Draw the pause menu screen.
     * @param g  the Graphics Object
     */
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message,250,225);

        drawBall(gameController.getBall(),g2d);

        for(Brick b : gameController.getBrick())
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(gameController.getPlayer(),g2d);

        if(showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * clear is a method that clear the game board screen.
     * @param g2d  the Graphics2D object
     */
    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * drawBrick is a method that draw the brick on the game board screen.
     * @param brick  the Brick object
     * @param g2d    the Graphics2D object
     */
    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    /**
     * drawBall is a method that draw the ball on the game board screen screen.
     * @param ball  the Ball object
     * @param g2d   the Graphics2D object
     */
    private void drawBall(Ball ball, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * drawPlayer is a method that draw the player on the game board screen.
     * @param p    the Player object
     * @param g2d  the Graphics2D object
     */
    private void drawPlayer(Player p, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * drawMenu is a method that draw the pause menu on the game board screen.
     * @param g2d  the Graphics2D object
     */
    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * obscureGameBoard is a method that obscure the game board screen.
     * @param g2d  the Graphics2D object
     */
    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * drawPauseMenu is a method that draw the pause menu on the game board screen.
     * Draw texts.
     * Draw buttons.
     * @param g2d  the Graphics2D object
     */
    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    /**
     * getScoreController is a method that returns the ScoreController object which is created in GameBoard class.
     * @return  return the ScoreController object
     */
    public ScoreController getScoreController(){
        return scoreController;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * keyPressed is a method that deals with the implementations of the game board when the specified key is pressed.
     * @param keyEvent  the KeyEvent object that indicate if the specified key is pressed
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                gameController.getPlayer().moveLeft();
                break;
            case KeyEvent.VK_D:
                gameController.getPlayer().moveRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning())
                        gameTimer.stop();
                    else
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            default:
                gameController.getPlayer().stop();
        }
    }

    /**
     * keyReleased is a method that deals with the implementations of the game board when the key is released.
     * @param keyEvent   the KeyEvent object that indicate if the key is released
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        gameController.getPlayer().stop();
    }

    /**
     * mouseClicked is a method that deals with the implementations of the pause menu when it is being shown.
     * @param mouseEvent  the MouseEvent object that indicate the mouse click
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        if(continueButtonRect.contains(p)){
            showPauseMenu = false;
            repaint();
        }
        else if(restartButtonRect.contains(p)){
            message = "Restarting Game...";
            gameController.ballReset();
            gameController.wallReset();
            showPauseMenu = false;
            repaint();
        }
        else if(exitButtonRect.contains(p)){
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * mouseMotion is a method that deals with the implementation of pause menu when the cursor is pointing at the buttons.
     * @param mouseEvent  the MouseEvent object that indicate if the cursor is pointing at the buttons
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(exitButtonRect != null && showPauseMenu) {
            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * onLostFocus is a method that deals with the implementation of game board when it is not in focus.
     */
    public void onLostFocus(){
        gameTimer.stop();
        message = "Focus Lost";
        repaint();
    }

}
