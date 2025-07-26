import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    JTextField textField;
    String operator;
    double num1, num2, result;

    Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        textField = new JTextField();
        textField.setBounds(20, 20, 340, 50);
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setEditable(false);
        add(textField);

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        int x = 20, y = 90;
        for (int i = 0; i < buttons.length; i++) {
            String text = buttons[i];
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setBounds(x, y, 80, 60);
            button.addActionListener(this);

            if (!text.equals("C")) {
                add(button);
            }

            x += 90;
            if ((i + 1) % 4 == 0) {
                x = 20;
                y += 70;
            }
        }

        JButton clearButton = new JButton("C");
        clearButton.setBounds(110, y, 170, 60);
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.addActionListener(this);
        add(clearButton);
    }

    public void actionPerformed(ActionEvent e) {
        String input = ((JButton)e.getSource()).getText();

        if (input.matches("[0-9\\.]")) {
            textField.setText(textField.getText() + input);
        } else if (input.matches("[\\+\\-\\*/]")) {
            try {
                num1 = Double.parseDouble(textField.getText());
                operator = input;
                textField.setText("");
            } catch (Exception ex) {
                textField.setText("Error");
            }
        } else if (input.equals("=")) {
            try {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 == 0) {
                            textField.setText("Cannot divide by 0");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                textField.setText(String.valueOf(result));
            } catch (Exception ex) {
                textField.setText("Error");
            }
        } else if (input.equals("C")) {
            textField.setText("");
            num1 = num2 = result = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        new Calculator().setVisible(true);
    }
}
