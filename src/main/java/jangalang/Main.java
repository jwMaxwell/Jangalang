import javax.swing.JFrame;

class Main {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = WIDTH / 12 * 9;

    public static void main(String[] args) {
        JFrame gameWindow = new JFrame();
        gameWindow.setSize(WIDTH, HEIGHT);
        gameWindow.setResizable(false); // Frick u, we pick the window size
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setTitle("Jangalang");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setVisible(true);
        // gameWindow.add(/* add rendering */);
        // gameWindow.addKeyListener(/* add key listening logic */));

        return;
    }
}
