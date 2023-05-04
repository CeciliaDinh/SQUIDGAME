package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    GamePanel gp;
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // //TITLE STATE
        // if (GamePanel.gameState == GamePanel.menuState) {
        // if (code == KeyEvent.VK_W) {
        // GamePanel.commandNum--;
        // if (GamePanel.commandNum < 0) {
        // GamePanel.commandNum = 1;
        // }
        // }
        // if (code == KeyEvent.VK_S) {
        // GamePanel.commandNum++;
        // if (GamePanel.commandNum > 1) {
        // GamePanel.commandNum = 0;
        // }
        // }
        // if (code == KeyEvent.VK_ENTER) {
        // if (GamePanel.commandNum == 0) {
        // GamePanel.gameState = GamePanel.playState;
        // GamePanel.playMusic(0);

        // }
        // if (GamePanel.commandNum == 1) {
        // System.exit(0);
        // }
        // }
        // }
        // check if user pressed enter
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if(code==KeyEvent.VK_P) {
            if(gp.gameState == gp.playState) {
            	gp.gameState = gp.pauseState;
            }
            else if (gp.gameState == gp.pauseState) {
            	gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
