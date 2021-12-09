package View;

import Controller.ScoreController;
import Model.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class GameOverMenu extends JComponent implements MouseListener, MouseMotionListener {

    private static final String GAMEOVER_TITTLE = "GAME OVER";
    private static final String FINALSCORE_TEXT = "FINAL SCORE: ";
    private static final String BACK_TEXT = "BACK MENU";
    private static final int TEXT_SIZE = 30;

    private static final Color MENU_COLOR = new Color(0,255,0);
    private static final Color CLICKED_TEXT = Color.WHITE;

    private Font buttonFont;
    private Font menuFont;

    private static GameFrame owner;
    private static ScoreController scoreController;

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private Rectangle menuFace;
    private Rectangle backButton;
    private boolean backClicked;

    private int strLen;

    public GameOverMenu(GameFrame owner, ScoreController scoreController) {
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;//this
        this.scoreController = scoreController;

        menuFace = new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT);
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));

        Dimension btnDim = new Dimension(DEF_WIDTH / 3, DEF_HEIGHT / 12);
        backButton = new Rectangle(btnDim);

        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);
        buttonFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);

        strLen = 0 ;
    }

    public void paint(Graphics g){
        obscureGameBoard((Graphics2D)g);
        drawGameOverMenu((Graphics2D)g);
    }

    public void obscureGameBoard(Graphics2D g2d) {
        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }


    public void drawGameOverMenu(Graphics2D g2d){
        Font prevFont = g2d.getFont();
        Color prevColor = g2d.getColor();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    public void drawText(Graphics2D g2d) {
        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();
        strLen = menuFont.getStringBounds(GAMEOVER_TITTLE,frc).getBounds().width;

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(GAMEOVER_TITTLE,x,y);

        String finalScore = String.format(FINALSCORE_TEXT + "%d", scoreController.getCurrentScore());//this

        strLen = menuFont.getStringBounds(finalScore,frc).getBounds().width;


        x = (this.getWidth() - strLen) / 2 ;
        y = this.getHeight() / 2;

        g2d.drawString(finalScore,x,y);

        if(scoreController.getCurrentScore() <= 61){
            x -= 110;
            y += 50;
            g2d.drawString("You are in beginner level!", x, y);
        }
        else if(scoreController.getCurrentScore() >= 62 && scoreController.getCurrentScore() <= 123){
            x -= 55;
            y += 50;
            g2d.drawString("You get the skills!",x,y);
        }
        else if(scoreController.getCurrentScore() >= 124 && scoreController.getCurrentScore() <= 185){
            x -= 110;
            y += 50;
            g2d.drawString("You are becoming an expert!",x,y);
        }
        else if(scoreController.getCurrentScore() >= 186 && scoreController.getCurrentScore() <= 216){
            x -= 110;
            y += 50;
            g2d.drawString("You are the master of game!",x,y);
        }
        else if(scoreController.getCurrentScore() == 217){
            x -= 20;
            y += 50;
            g2d.drawString("Congratulations!",x,y);

            x -= 60;
            y += 30;
            g2d.drawString("All walls are destroyed!",x,y);
        }
    }

    public void drawButton(Graphics2D g2d) {
        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D buttonTextRect = buttonFont.getStringBounds(BACK_TEXT,frc).getBounds();

        g2d.setFont(buttonFont);

        int x = (menuFace.width - backButton.width) / 2;
        int y = (int) ((menuFace.height - backButton.height) * 0.9);

        backButton.setLocation(x,y);

        x = (int)(backButton.getWidth() - buttonTextRect.getWidth()) / 2;
        y = (int)(backButton.getHeight() - buttonTextRect.getHeight()) / 2;

        x += backButton.x;
        y += backButton.y + (backButton.height * 0.9);

        if(backClicked){
            Color tmp = g2d.getColor();
            g2d.draw(backButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(BACK_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(backButton);
            g2d.drawString(BACK_TEXT,x,y);
        }
    }

    public static GameFrame getOwner(){
        return owner;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)) {
            owner.enableHomeMenu();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)) {
            backClicked = true;
            repaint(backButton.x, backButton.y, backButton.width + 1, backButton.height + 1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(backClicked) {
            backClicked = false;
            repaint(backButton.x, backButton.y, backButton.width + 1, backButton.height + 1);
        }
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

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());
    }
}
