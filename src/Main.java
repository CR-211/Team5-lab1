import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JFrame {

    private Timer timer1;
    private Timer timer2;
    private Timer timer3;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    private JButton stopButton2;
    private JButton startButton3;

    public Main() {
        setTitle("Timers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new GridLayout(3, 1));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());

        textField1 = new JTextField(10);
        JButton startButton1 = new JButton("Start");
        label1 = new JLabel("Timer 1: ");

        startButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer1();
            }
        });

        panel1.add(new JLabel("Timer la un anumit timp "));
        panel1.add(textField1);
        panel1.add(startButton1);
        panel1.add(label1);

        textField2 = new JTextField(10);
        JButton startButton2 = new JButton("Start");
        stopButton2 = new JButton("Stop");
        label2 = new JLabel("Timer 2: ");

        startButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer2();
            }
        });

        stopButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimer2();
            }
        });

        panel2.add(new JLabel("Timer cu un interval: "));
        panel2.add(textField2);
        panel2.add(startButton2);
        panel2.add(stopButton2);
        panel2.add(label2);

        textField3 = new JTextField(10);
        startButton3 = new JButton("Start");
        label3 = new JLabel("Timer 3: ");

        startButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer3();
            }
        });

        panel3.add(new JLabel("Timer cu o ora anumita (HH:mm:ss): "));
        panel3.add(textField3);
        panel3.add(startButton3);
        panel3.add(label3);

        add(panel1);
        add(panel2);
        add(panel3);
    }

    private void startTimer1() {
        try {
            if (timer1 != null) {
                timer1.cancel();
            }

            int seconds = Integer.parseInt(textField1.getText());
            timer1 = new Timer();
            timer1.scheduleAtFixedRate(new TimerTask() {
                int secondsElapsed = 1;

                @Override
                public void run() {
                    if (secondsElapsed < seconds) {
                        label1.setText("Secunde trecute: " + secondsElapsed++);
                    } else {
                        timer1.cancel();
                        label1.setText("Timpul a expirat!");
                    }
                }
            }, 0, 1000);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Introduceți un număr valid pentru timp.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startTimer2() {
        try {
            if (timer2 != null) {
                timer2.cancel();
            }

            int totalTime = Integer.parseInt(textField2.getText());
            timer2 = new Timer();
            timer2.scheduleAtFixedRate(new TimerTask() {
                int elapsedTime = 1;

                @Override
                public void run() {
                    if (elapsedTime <= totalTime) {
                        label2.setText("Timp scurs: " + elapsedTime + " secunde");
                        elapsedTime++;
                    } else {
                        label2.setText("Timpul a expirat!");
                        elapsedTime = 1; // Resetează numărătoarea
                    }
                }
            }, 0, 1000); // Rulează task-ul la fiecare secundă
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Introduceți un număr valid pentru timp.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startTimer3() {
        try {
            if (timer3 != null) {
                timer3.cancel();
            }

            timer3 = new Timer();
            timer3.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Date currentTime = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    label3.setText("Ora curentă: " + sdf.format(currentTime));

                    if (sdf.format(currentTime).equals(textField3.getText())) {
                        label3.setText("Timpul a expirat la ora " + sdf.format(currentTime));
                        timer3.cancel();
                    }
                }
            }, 0, 1000);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Introduceți o oră validă (HH:mm:ss).", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void stopTimer2() {
        if (timer2 != null) {
            timer2.cancel();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
