package View;

import Controllers.RequestSender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationDialog {
    private JDialog dialog;
    private JLabel label;
    private JTextArea textArea;
    private JButton buttonOK;
    private RequestSender requestSender;
    public RegistrationDialog (final RequestSender requestSender, View view){
        this.requestSender = requestSender;
        dialog=new JDialog();
        label=new JLabel("Имя:");
        label.setBounds(20,30,50,30);
        textArea=new JTextArea();
        textArea.setBounds(80,30,80,30);
        buttonOK=new JButton("ОК");
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestSender.sendMessage("/reg "+textArea.getText());
            }
        });
        view.updaitUserName(textArea.getText());
        buttonOK.setBounds(180,30,40,30);
        dialog.add(label);
        dialog.add(textArea);
        dialog.add(buttonOK);
        dialog.setLayout(null);
        dialog.setSize(300,150);
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);
    }
}
