import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Retrieve implements ActionListener{

    public static ResultSet rs = null;

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JLabel label1 = new JLabel("Name: ");
        JLabel label2 = new JLabel("Email: ");
        JTextField text1 = new JTextField(20);
        JTextField text2 = new JTextField(20);
        JButton B1 = new JButton("NEXT");
        JButton B2 = new JButton("PREVIOUS");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_create_DB","root","");
            Statement st=con.createStatement();
            rs = st.executeQuery("select * from data");
            String name = "", email = "";
            if (rs.next()) {
                name = rs.getString("name");
                email = rs.getString("email");
            }
            text1.setText(name);
            text2.setText(email);
        } catch (Exception e) {}

        B1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    //text2.setText("oof");
                    if (rs.next()) {
                        B1.setEnabled(true);
                        B2.setEnabled(true);

                        text1.setText(rs.getString("name"));
                        text2.setText(rs.getString("email"));
                        if (rs.isLast()){
                            B1.setEnabled(false);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        B2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    if (rs.previous()){
                        B2.setEnabled(true);
                        B1.setEnabled(true);

                        text1.setText(rs.getString("name"));
                        text2.setText(rs.getString("email"));

                        if (rs.isFirst()) {
                            B2.setEnabled(false);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        JPanel p = new JPanel(new GridLayout(3, 2));
        p.add(label1);
        p.add(text1);
        p.add(label2);
        p.add(text2);
        p.add(B2);
        p.add(B1);
        f.add(p);
        f.setVisible(true);
        f.pack();
    }

    public void actionPerformed(ActionEvent e){
    }
}