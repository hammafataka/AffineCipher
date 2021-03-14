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




    String encryptMessage(char[] msg)
    {


        int a=Integer.parseInt(afield.getText());
        int b =Integer.parseInt(bField.getText());
        /// Cipher Text initially empty
        String cipher = "";
        for (int i = 0; i < msg.length; i++)
        {
            // Avoid space to be encrypted
            /* applying encryption formula ( a x + b ) mod m
            {here x is msg[i] and m is 26} and added 'A' to
            bring it in range of ascii alphabet[ 65-90 | A-Z ] */
            if (msg[i] != ' ')
            {
                cipher = cipher + (char) ((((a * (msg[i] - 'A')) + b) % 26) + 'A');
            } else // else simply append space character
            {
                cipher += "XSPACEX";
            }
        }
        return cipher;
    }

    String decryptCipher(String cipher)
    {

            int a=Integer.parseInt(afield.getText());
            int b =Integer.parseInt(bField.getText());



        String msg = "";
        int a_inv = 0;
        int flag = 0;

        //Find a^-1 (the multiplicative inverse of a
        //in the group of integers modulo m.)
        for (int i = 0; i < 26; i++)
        {
            flag = (a * i) % 26;

            // Check if (a*i)%26 == 1,
            // then i will be the multiplicative inverse of a
            if (flag == 1)
            {
                a_inv = i;
            }
        }
        for (int i = 0; i < cipher.length(); i++)
        {
            /*Applying decryption formula a^-1 ( x - b ) mod m
            {here x is cipher[i] and m is 26} and added 'A'
            to bring it in range of ASCII alphabet[ 65-90 | A-Z ] */
            if (cipher.charAt(i) != ' ')
            {
                msg = msg + (char) (((a_inv *
                        ((cipher.charAt(i) + 'A' - b)) % 26)) + 'A');
            }
            else //else simply append space characte
            {
                msg += cipher.charAt(i);
            }
        }

        return msg;
    }


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


    @Override
    public void actionPerformed(ActionEvent e) {
        if(afield.getText().length()<1&&bField.getText().length()<1){
            JOptionPane.showMessageDialog(null, "Please set Value A and B ", "Error: "
                    , JOptionPane.INFORMATION_MESSAGE);

        }else {
            if(e.getSource()==myButton){
                String text=enterField.getText();

                // Calling encryption function
                String cipherText = encryptMessage(text.toCharArray());
                JOptionPane.showMessageDialog(null, "Encrypted Message is: "+cipherText, "Result: " , JOptionPane.INFORMATION_MESSAGE);
            }else if(e.getSource()==myButton2){
                String text=enterField.getText();

                // Calling encryption function
                String cipherText = encryptMessage(text.toCharArray());
                JOptionPane.showMessageDialog(null, "Decrypted Message is: "+decryptCipher(cipherText), "Result: " , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
