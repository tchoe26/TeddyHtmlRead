import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingControlDemo implements ActionListener {
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JLabel label1;
    private JLabel label2;
    private JPanel controlPanel;
    private JPanel controlPanel2;
    private JMenuItem cut, copy, paste, selectAll;
    private static JTextArea linkInput; //typing area
    private static JTextArea keywordInput;
    private static JTextArea output;
    private int WIDTH=800;
    private int HEIGHT=700;


    public SwingControlDemo() {
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingControlDemo swingControlDemo = new SwingControlDemo();
        swingControlDemo.showEventDemo();

    }

    private void prepareGUI() {
        mainFrame = new JFrame("teddy learning swing");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(1, 2));

        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(5, 1));

        controlPanel2 = new JPanel();
        controlPanel2.setLayout(new GridLayout(1, 1));

        linkInput = new JTextArea();
        linkInput.setBounds(50, 5, WIDTH-100, 100);

        keywordInput = new JTextArea();
        keywordInput.setBounds(50, 500, WIDTH-100, 100);

        output = new JTextArea();
        output.setBounds(50, 1, WIDTH-100, 100);


        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        mainFrame.setVisible(true);
    }

    private void showEventDemo() {

        JButton okButton = new JButton("OK");
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        JButton button1 = new JButton("start");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");
        JButton button5 = new JButton("Button 5");

        button1.setActionCommand("Start");
        button1.addActionListener(this);

        JLabel label1 = new JLabel("input link", JLabel.CENTER);
        JLabel label2 = new JLabel("input keyword", JLabel.CENTER);

        okButton.setActionCommand("OK");
        submitButton.setActionCommand("Submit");
        cancelButton.setActionCommand("Cancel");

        okButton.addActionListener(new ButtonClickListener());
        submitButton.addActionListener(new ButtonClickListener());
        cancelButton.addActionListener(new ButtonClickListener());

       /* mainFrame.add(button1);
        mainFrame.add(button2);
        mainFrame.add(button3);
        mainFrame.add(button4);*/
        mainFrame.add(controlPanel);
        mainFrame.add(controlPanel2);

        controlPanel.add(label1);
        controlPanel.add(linkInput);
        controlPanel.add(label2);
        controlPanel.add(keywordInput);
        controlPanel.add(button1);

        controlPanel2.add(output);


        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Start".equals(e.getActionCommand())) {
            HtmlRead html = new HtmlRead();
        }

    }
    public static String getLinkInput() {
        return linkInput.getText();
    }
    public static String getKeywordInput() {
        return keywordInput.getText();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("OK")) {
                statusLabel.setText("Ok Button clicked.");
            } else if (command.equals("Submit")) {
                statusLabel.setText("Submit Button clicked.");
            } else {
                statusLabel.setText("Cancel Button clicked.");
            }
        }
    }

    public static void writeToOutput(String outputParameter) {
        output.append(outputParameter+ "\n");
        System.out.println(outputParameter);
    }
}