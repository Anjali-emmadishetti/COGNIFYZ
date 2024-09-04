import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        JTextField textField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(4, 4));
        
        String[] buttons = {"7", "8", "9", "/",
                            "4", "5", "6", "*",
                            "1", "2", "3", "-",
                            "C", "0", "=", "+"};
        
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new ActionListener() {
                private StringBuilder sb = new StringBuilder();
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();
                    if (cmd.equals("C")) sb.setLength(0);
                    else if (cmd.equals("=")) {
                        try { sb.setLength(0); sb.append(eval(textField.getText())); }
                        catch (Exception ex) { sb.setLength(0); sb.append("Error"); }
                    } else sb.append(cmd);
                    textField.setText(sb.toString());
                }
                private double eval(String expr) {
                    return new Object() {
                        int pos = -1, ch;
                        void nextChar() { ch = (++pos < expr.length()) ? expr.charAt(pos) : -1; }
                        boolean eat(int charToEat) { while (ch == ' ') nextChar(); if (ch == charToEat) { nextChar(); return true; } return false; }
                        double parse() { nextChar(); double x = parseExpression(); if (pos < expr.length()) throw new RuntimeException("Unexpected: " + (char)ch); return x; }
                        double parseExpression() { double x = parseTerm(); for (; ; ) { if (eat('+')) x += parseTerm(); else if (eat('-')) x -= parseTerm(); else return x; } }
                        double parseTerm() { double x = parseFactor(); for (; ; ) { if (eat('*')) x *= parseFactor(); else if (eat('/')) x /= parseFactor(); else return x; } }
                        double parseFactor() { if (eat('+')) return parseFactor(); if (eat('-')) return -parseFactor(); double x; int startPos = this.pos; if (eat('(')) { x = parseExpression(); eat(')'); } else if ((ch >= '0' && ch <= '9') || ch == '.') { while ((ch >= '0' && ch <= '9') || ch == '.') nextChar(); x = Double.parseDouble(expr.substring(startPos, this.pos)); } else { throw new RuntimeException("Unexpected: " + (char)ch); } return x; }
                    }.parse();
                }
            });
            panel.add(button);
        }
        
        frame.setLayout(new BorderLayout());
        frame.add(textField, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
