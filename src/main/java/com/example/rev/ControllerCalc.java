package com.example.rev;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class ControllerCalc {
    public Label label;
    @FXML
    Button b1, b2,b3,b4,b5,bo,on;
    @FXML
    Button rb1, rb2,rb3, rb4;
    @FXML
    TextArea txt1;
    @FXML
    ImageView ima;


    @FXML
    protected void addi(){
        txt1.appendText("+");

    }
    @FXML
    protected void soustr(){
        txt1.appendText("-");


    }
    @FXML
    protected void multi(){
        txt1.appendText("*");

    }
    @FXML
    protected void diviser(){
        txt1.appendText("/");


    }


    @FXML
    protected void onHelloButtonClick1() {
        txt1.appendText("1");

    }
    @FXML
    protected void onHelloButtonClick2() {
        txt1.appendText("2");

    }
    @FXML
    protected void onHelloButtonClick3() {
        txt1.appendText("3");

    }
    @FXML
    protected void onHelloButtonClick4() {
        txt1.appendText("4");

    }
    @FXML
    protected void onHelloButtonClick5() {
        txt1.appendText("5");

    }
    @FXML
    protected void onHelloButtonClick6() {
        txt1.appendText("6");

    }
    @FXML
    protected void onHelloButtonClick7() {
        txt1.appendText("7");

    }
    @FXML
    protected void onHelloButtonClick8() {
        txt1.appendText("8");

    }
    @FXML
    protected void onHelloButtonClick9() {
        txt1.appendText("9");

    }
    @FXML
    protected  void On(){
        txt1.setText("");
    }
    @FXML
    protected void calculateResult() {
        try {
            String expression = txt1.getText();
            double result = eval(expression);
            txt1.setText(String.valueOf(result));
        } catch (Exception e) {
            txt1.setText("Erreur");
        }
    }
    protected double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // soustraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                return x;
            }
        }.parse();
    }
}