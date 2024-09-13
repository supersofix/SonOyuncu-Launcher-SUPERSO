import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class SonOyuncu {

    private JFrame frame;
    private JPanel panel;
    private JProgressBar progressBar;
    private JLabel statusLabel;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new SonOyuncu().createLauncher());
    }


    public void createLauncher() {

        frame = new JFrame("SuperSO Launcher");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("SuperSO Launcher", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(50, 30, 400, 30);
        panel.add(title);


        statusLabel = new JLabel("Checking SuperSO", SwingConstants.CENTER);
        statusLabel.setBounds(50, 80, 400, 30);
        panel.add(statusLabel);


        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(50, 130, 400, 30);
        panel.add(progressBar);


        frame.add(panel);
        frame.setVisible(true);


        new Thread(this::checkLibraries).start();
    }

// hmm working libraries check..
    private void checkLibraries() {
        try {
            for (int i = 0; i <= 100; i += 10) {
                TimeUnit.SECONDS.sleep(1);
                progressBar.setValue(i);
                statusLabel.setText("Checking SuperSO %" + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(this::showMainMenu);
    }


    private void showMainMenu() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        JLabel mainMenuLabel = new JLabel("SuperSo", SwingConstants.CENTER);
        mainMenuLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(mainMenuLabel, BorderLayout.CENTER);


        JButton startButton = new JButton("Start SuperSO");
        startButton.setFont(new Font("Arial", Font.PLAIN, 18));
        startButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Start SuperSO"));
        panel.add(startButton, BorderLayout.SOUTH);

        panel.revalidate();
        panel.repaint();
    }
}
