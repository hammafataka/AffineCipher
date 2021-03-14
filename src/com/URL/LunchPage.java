package com.URL;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;

public class LunchPage implements ActionListener {
    JFrame frame=new JFrame();
    JButton myButton=new JButton("Encrypt");
    JTextField afield= new JTextField();
    JLabel aLabel=new JLabel("A:");
    JLabel bLabel=new JLabel("B:");
    JTextField bField=new JTextField();
    JLabel enterLabel=new JLabel("Enter The Text:");
    JTextField enterField=new JTextField();
    JButton myButton2=new JButton("Decrypt");

    private static final int ALPHABET_SIZE = 26;

    LunchPage(){

        myButton.setBounds(100,400,200,40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);

        myButton2.setBounds(100,500,200,40);
        myButton2.setFocusable(false);
        myButton2.addActionListener(this);

        aLabel.setBounds(0,0,50,50);
        aLabel.setFont(new Font(null,Font.PLAIN,25));

        bLabel.setBounds(200,0,50,50);
        bLabel.setFont(new Font(null,Font.PLAIN,25));

        enterLabel.setBounds(100,100,200,80);
        enterLabel.setFont(new Font(null,Font.PLAIN,25));


        afield.setBounds(40,13,100,30);
        bField.setBounds(240,13,100,30);
        enterField.setBounds(0,160,420,80);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(myButton);
        frame.add(afield);
        frame.add(aLabel);
        frame.add(bLabel);
        frame.add(bField);
        frame.add(enterLabel);
        frame.add(enterField);
        frame.add(myButton2);




    }

    public void decryption()
    {



        int aInverse = 0;
        boolean isAOk = false;
        try {
            int a=Integer.parseInt(afield.getText());
            int b =Integer.parseInt(bField.getText());
            String ciphertext = enterField.getText();

            while (!isAOk) {

                if (gcd(a, ALPHABET_SIZE) == 1) {
                    isAOk = true;
                    aInverse = findInverse(a, ALPHABET_SIZE);
                } else {
                    System.out.println("'a' is not ok, please try again.");
                }
            }


            decrypt(aInverse, b, ciphertext);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Please Put Correct format of A and B");

        }


    }

    private static void decrypt(int aInverse, int b, String ciphertext) {
        if (ciphertext == null || ciphertext.length() <= 0) {
            JOptionPane.showMessageDialog(null,"Plaintext has a problem.");
            return;
        }

        ciphertext = ciphertext.toLowerCase();
        StringBuilder plaintext = new StringBuilder();
        int z, j;

        for (int i = 0; i < ciphertext.length(); i++) {
            char agent = ciphertext.charAt(i);
            z = aInverse * ((agent - 97) - b);
            j = z < 0 ? minusMod(z, ALPHABET_SIZE) : z % ALPHABET_SIZE;
            plaintext.append((char) ('A' + j));
        }
        JOptionPane.showMessageDialog(null,"Plaintext: " + plaintext);
    }
    private static int minusMod(int minus, int mod) {
        int a = Math.abs(minus);
        return (mod * ((a / mod) + 1)) - a;
    }


    private  int findInverse(double firstNumber, double anotherNumber) {
        int a1, b1, a2, b2, r, q, temp_a2, temp_b2, n1, n2, max;

        if (firstNumber > anotherNumber) {
            max = (int) firstNumber;
            n1 = (int) firstNumber;
            n2 = (int) anotherNumber;
        } else {
            max = (int) anotherNumber;
            n1 = (int) anotherNumber;
            n2 = (int) firstNumber;
        }

        a1 = b2 = 1;
        b1 = a2 = 0;
        temp_a2 = a2;
        temp_b2 = b2;

        r = n1 % n2;
        q = n1 / n2;

        while (r != 0) {
            n1 = n2;
            n2 = r;
            a2 = a1 - q * a2;
            b2 = b1 - q * b2;
            a1 = temp_a2;
            b1 = temp_b2;
            temp_a2 = a2;
            temp_b2 = b2;
            r = n1 % n2;
            q = n1 / n2;
        }

        int result;
        if (firstNumber == max) {
            if (a2 < 0) {
                result = (int) (a2 - anotherNumber * Math.floor(a2 / anotherNumber));
            } else {
                result = a2;
            }
        } else {
            if (b2 < 0) {
                result = (int) (b2 - anotherNumber * Math.floor(b2 / anotherNumber));
            } else
                result = b2;
        }
        return result;
    }

    public  void encryption() {
        boolean isAOk = false, isBOk = false;
        int a = 0, b = 0;

        String plaintext = enterField.getText();


        try {
            while (!isAOk) {
                a =Integer.parseInt( afield.getText());
                if (gcd(a, ALPHABET_SIZE) == 1) {
                    isAOk = true;
                } else {
                    JOptionPane.showMessageDialog(null,"'a' is not ok, pls try again.");
                }
            }

            while (!isBOk) {

                System.out.print("Choose 'b' which number you want but not equal 1 >> ");
                b =Integer.parseInt( bField.getText());
                if (b != 1) {
                    isBOk = true;
                } else {
                    JOptionPane.showMessageDialog(null,"'b' is not ok, pls try again.");
                }
            }
            encrypt(a, b, plaintext);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Please Put Correct format of A and B");
        }


    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    private static void encrypt(int a, int b, String plaintext) {
        if (plaintext == null || plaintext.length() <= 0) {
            System.out.println("Plaintext has a problem. Bye bye :)");
            return;
        }

        plaintext = plaintext.toLowerCase();
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char agent = plaintext.charAt(i);
            int value = ((a * (agent - 97) + b) % ALPHABET_SIZE);
            ciphertext.append((char) (value + 97));
        }
        JOptionPane.showMessageDialog(null,"Ciphertext: " + ciphertext);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(afield.getText().length()<1&&bField.getText().length()<1){
            JOptionPane.showMessageDialog(null, "Please set Value A and B ", "Error: "
                    , JOptionPane.INFORMATION_MESSAGE);

        }else {
            if(e.getSource()==myButton){
                encryption();
            }else if(e.getSource()==myButton2){
                decryption();
            }
        }
    }
}
