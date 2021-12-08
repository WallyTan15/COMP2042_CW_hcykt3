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

public class ScoreBoard extends JComponent implements MouseListener, MouseMotionListener {

    private static final String SCOREBOARD_TITTLE = "SCOREBOARD";
    private static final String BACK_TEXT = "BACK";

    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color BORDER_COLOR = new Color(200,8,21);
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);
    private static final Color TEXT_COLOR = new Color(16, 52, 166);
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private Font buttonFont;
    private Font titleFont;
    private Font scoreFont;

    private int[] record;
    private String scoreContent;

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private GameFrame owner;
    private ScoreController scoreController;

    private Rectangle scoreBoardFace;
    private Rectangle backButton;

    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private boolean backClicked;

    public ScoreBoard(GameFrame owner, Dimension area, ScoreController scoreController) {
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;
        this.scoreController = scoreController;

        scoreBoardFace = new Rectangle(new Point(0,0),new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));

        Dimension btnDim = new Dimension(area.width / 5, area.height / 12);
        backButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        titleFont = new Font("Noto Mono",Font.BOLD,30);
        scoreFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, 17);
        buttonFont = new Font("Monospaced",Font.BOLD,backButton.height-2);

        record = new int[10];
    }

    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }

    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = scoreBoardFace.getX();
        double y = scoreBoardFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawScoreBoard(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();

        g2d.setColor(BG_COLOR);
        g2d.fill(scoreBoardFace);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(scoreBoardFace);

        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(scoreBoardFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);
    }

    private void drawScoreBoard(Graphics2D g2d){
        copyRecord();

        int i = 0;

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        scoreContent = String.format("%d  %d", i + 1, record[i]); //this

        Rectangle2D instructionsRect = titleFont.getStringBounds(SCOREBOARD_TITTLE,frc);
        Rectangle2D scoreBoardRec = scoreFont.getStringBounds(scoreContent, frc);

        int sX, sY;
        sX = (int)(scoreBoardFace.getWidth() - instructionsRect.getWidth()) / 10;
        sY = (int)(scoreBoardFace.getHeight() / 8);

        g2d.setFont(titleFont);
        g2d.drawString(SCOREBOARD_TITTLE, sX, sY);

        sX = (int)(scoreBoardFace.getWidth() - 1.6 * instructionsRect.getWidth());
        sY += (int) scoreBoardRec.getHeight() * 3;

        g2d.setFont(scoreFont);

        for(i = 0; i < 9; i ++){
            scoreContent = String.format("%d.  %d", i + 1, record[i]);
            g2d.drawString(scoreContent, sX, sY);
            sY += (int) scoreBoardRec.getHeight() * 1.25;
        }

        sX -= 11;
        scoreContent = String.format("%d.  %d", 10, record[9]);
        g2d.drawString(scoreContent, sX, sY);
    }

    private void drawButton(Graphics2D g2d) {
        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D buttonTextRect = buttonFont.getStringBounds(BACK_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (scoreBoardFace.width - backButton.width) / 8;
        int y =(int) ((scoreBoardFace.height - backButton.height) * 0.97);

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


    public void copyRecord() {
        scoreController.copyGameRecord(record);

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

    }
}
