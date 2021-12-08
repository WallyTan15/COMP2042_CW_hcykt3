package View;

import Model.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class InfoMenu extends JComponent implements MouseListener, MouseMotionListener {

    private static final String INSTRUCTIONS_TITLE = "INSTRUCTIONS";
    private static final String BACK_TEXT = "BACK";
    private static final String INSTRUCTIONS_1 = "Key Space: ";
    private static final String INSTRUCTIONS_2 = "To Start Or Pause The Game";
    private static final String INSTRUCTIONS_3 = "Key A: ";
    private static final String INSTRUCTIONS_4 = "To Move The Player Left";
    private static final String INSTRUCTIONS_5 = "Key D: ";
    private static final String INSTRUCTIONS_6 = "To Move The Player Right";
    private static final String INSTRUCTIONS_7 = "Key ESC: ";
    private static final String INSTRUCTIONS_8 = "To Open Or CLose The Pause Menu";
    private static final String INSTRUCTIONS_9 = "Key Alt + Shift + F1: ";
    private static final String INSTRUCTIONS_10 = "To Open The Console";

    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color BORDER_COLOR = new Color(200,8,21); //Venetian Red
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);//school bus yellow
    private static final Color TEXT_COLOR = new Color(16, 52, 166);
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private Font instructionsTittleFont;
    private Font instructionsKeyFont;
    private Font instructionsContentFont;
    private Font buttonFont;

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private Rectangle menuFace;
    private Rectangle backButton;

    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private boolean returnClicked;

    private GameFrame owner;

    public InfoMenu(GameFrame owner, Dimension area) {
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        menuFace = new Rectangle(new Point(0,0),new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;

        Dimension btnDim = new Dimension(area.width / 5, area.height / 12);
        backButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        instructionsTittleFont = new Font("Noto Mono",Font.BOLD,30);
        instructionsKeyFont = new Font("Noto Mono",Font.BOLD,15);
        instructionsContentFont = new Font("SansSerif",Font.ITALIC | Font.BOLD,12);
        buttonFont = new Font("Monospaced",Font.BOLD, backButton.height-2);
    }

    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }

    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

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

    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();

        g2d.setColor(BG_COLOR);
        g2d.fill(menuFace);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);
    }

    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D instructionsTittleRect = instructionsTittleFont.getStringBounds(INSTRUCTIONS_TITLE,frc);
        Rectangle2D instructionsContentRect = instructionsContentFont.getStringBounds(INSTRUCTIONS_1,frc);

        int sX, sY;

        sX = (int)(menuFace.getWidth() - instructionsTittleRect.getWidth()) / 10;
        sY = (int)(menuFace.getHeight() / 8);

        g2d.setFont(instructionsTittleFont);
        g2d.drawString(INSTRUCTIONS_TITLE,sX,sY);

        sX = (int)((menuFace.getWidth() - instructionsContentRect.getWidth()) / 2.5);
        sY += (int) instructionsContentRect.getHeight() * 4;

        g2d.setFont(instructionsKeyFont);
        g2d.drawString(INSTRUCTIONS_1,sX,sY);

        sY += (int) instructionsContentRect.getHeight() * 1.25;

        g2d.setFont(instructionsContentFont);
        g2d.drawString(INSTRUCTIONS_2,sX,sY);

        sY += (int) instructionsContentRect.getHeight() * 2.5;

        g2d.setFont(instructionsKeyFont);
        g2d.drawString(INSTRUCTIONS_3,sX,sY);

        sY += (int) instructionsContentRect.getHeight() * 1.25;

        g2d.setFont(instructionsContentFont);
        g2d.drawString(INSTRUCTIONS_4,sX,sY);

        sY += (int) instructionsContentRect.getHeight() * 2.5;

        g2d.setFont(instructionsKeyFont);
        g2d.drawString(INSTRUCTIONS_5,sX,sY);

        sY += (int) instructionsContentRect.getHeight() * 1.25;

        g2d.setFont(instructionsContentFont);
        g2d.drawString(INSTRUCTIONS_6,sX,sY);

        sY += (int) instructionsContentRect.getHeight() * 2.5;

        g2d.setFont(instructionsKeyFont);
        g2d.drawString(INSTRUCTIONS_7,sX,sY);

        sY += (int) instructionsContentRect.getHeight() * 1.25;

        g2d.setFont(instructionsContentFont);
        g2d.drawString(INSTRUCTIONS_8,sX,sY);

        sY += (int) instructionsContentRect.getHeight() * 2.5;

        g2d.setFont(instructionsKeyFont);
        g2d.drawString(INSTRUCTIONS_9,sX,sY);

        sY += (int) instructionsContentRect.getHeight() * 1.25;

        g2d.setFont(instructionsContentFont);
        g2d.drawString(INSTRUCTIONS_10,sX,sY);

    }

    private void drawButton(Graphics2D g2d) {
        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D buttonTextRect = buttonFont.getStringBounds(BACK_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - backButton.width) / 8;
        int y =(int) ((menuFace.height - backButton.height * 1.5));

        //draw start button
        backButton.setLocation(x,y);

        x = (int)(backButton.getWidth() - buttonTextRect.getWidth()) / 2;
        y = (int)(buttonTextRect.getHeight() - buttonTextRect.getHeight()) / 2;

        x += backButton.x;
        y += backButton.y + (backButton.height * 0.9);

        if(returnClicked){
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



    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if(backButton.contains(p)){
            owner.enableHomeMenu();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        if(backButton.contains(p)){
            returnClicked = true;
            repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(returnClicked ){
            returnClicked = false;
            repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();
        if(backButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());
    }
}
