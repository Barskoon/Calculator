package CalculatorMVC;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Viewer {

    private JTextField textField;

    public Viewer() {
        Controller controller = new Controller(this);
        Font font = new Font("Bernard MT Condensed", Font.BOLD, 20);

        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 525);
        frame.setResizable(false);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(25, 25, 300, 65);
        textField.setEditable(false);
        textField.setBackground(Color.white);
        textField.setFont(new Font("Consoles", Font.BOLD | Font.ITALIC, 20));
        textField.setForeground(Color.gray);
        textField.setMargin(new Insets(0, 15, 0, 15));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);

        JButton addBtn, subBtn, mulBtn, divBtn, decBtn, equBtn, delBtn, clrBtn;
        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("*");
        divBtn = new JButton("/");
        decBtn = new JButton(".");
        equBtn = new JButton("=");
        delBtn = new JButton("Del");
        clrBtn = new JButton("C");

        JButton[] numBtn = new JButton[10];
        JButton[] funBtn = new JButton[9];

        funBtn[0] = addBtn;
        funBtn[1] = subBtn;
        funBtn[2] = mulBtn;
        funBtn[3] = divBtn;
        funBtn[4] = decBtn;
        funBtn[5] = equBtn;
        funBtn[6] = delBtn;
        funBtn[7] = clrBtn;

        for (int i = 0; i < 8; i++) {
            funBtn[i].addActionListener(controller);
            funBtn[i].setFont(font);
            funBtn[i].setFocusable(false);
            funBtn[i].setBorder(new RoundedBorder(10));
            funBtn[i].setOpaque(true);
            funBtn[i].setForeground(Color.GRAY);
        }

        for (int i = 0; i < 10; i++) {
            numBtn[i] = new JButton(String.valueOf(i));
            numBtn[i].setFont(font);
            numBtn[i].addActionListener(controller);
            numBtn[i].setFocusable(false);
            numBtn[i].setBorder(new RoundedBorder(10));
            numBtn[i].setForeground(Color.gray);
            numBtn[i].setOpaque(false);
        }

        delBtn.setBounds(25, 410, 145, 50);
        clrBtn.setBounds(180, 410, 145, 50);

        JPanel panel;
        panel = new JPanel();
        panel.setBounds(25, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numBtn[1]);
        panel.add(numBtn[2]);
        panel.add(numBtn[3]);
        panel.add(addBtn);
        panel.add(numBtn[4]);
        panel.add(numBtn[5]);
        panel.add(numBtn[6]);
        panel.add(subBtn);
        panel.add(numBtn[7]);
        panel.add(numBtn[8]);
        panel.add(numBtn[9]);
        panel.add(mulBtn);
        panel.add(decBtn);
        panel.add(numBtn[0]);
        panel.add(equBtn);
        panel.add(divBtn);

        frame.add(panel);
        frame.add(delBtn);
        frame.add(clrBtn);
        frame.add(textField);
        frame.setVisible(true);
    }

    public void update(String text) {
        textField.setText(text);
    }



    class RoundedBorder implements Border {

        private int radius;


        RoundedBorder(int radius) {
            this.radius = radius;
        }


        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }


        public boolean isBorderOpaque() {
            return true;
        }


        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}