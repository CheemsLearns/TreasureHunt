package treasurehunt;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Treasure Hunt");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); // Adjusts the window size to fit the game panel
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread(); // Start the game loop
    }
    
}
