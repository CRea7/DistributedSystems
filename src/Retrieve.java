import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Retrieve implements ActionListener{

    public static ResultSet rs = null;

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JLabel label1 = new JLabel("SSN: ");
        JLabel label2 = new JLabel("DOB: ");
        JLabel LName = new JLabel("Name");
        JLabel LAddress = new JLabel("Address");
        JLabel LSalary = new JLabel("Salary");
        JLabel LGender = new JLabel("gender");

        JTextField text1 = new JTextField(20);
        JTextField text2 = new JTextField(20);
        JTextField TName = new JTextField(20);
        JTextField TAddress = new JTextField(20);
        JTextField TSalary = new JTextField(20);
        JTextField TGender = new JTextField(20);

        JButton next = new JButton("NEXT");
        JButton previous = new JButton("PREVIOUS");
        JButton clear = new JButton("CLEAR");
        JButton add = new JButton("ADD");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_create_DB","root","");
            Statement st=con.createStatement();
            rs = st.executeQuery("select * from data");
            String name = "", gender = "",SSn = "", Address="", DOB = "", Salary = "";
            if (rs.next()) {
                name = rs.getString("name");
                gender = rs.getString("Gender");
                SSn = rs.getString("SSn");
                Address = rs.getString("Address");
                DOB = rs.getString("DOB");
                Salary = rs.getString("Salary");
            }
            text1.setText(SSn);
            text2.setText(DOB);
            TName.setText(name);
            TAddress.setText(Address);
            TSalary.setText(Salary);
            TGender.setText(gender);

        } catch (Exception e) {}

        //gets the next person in list
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    //text2.setText("oof");
                    if (rs.next()) {
                        next.setEnabled(true);
                        previous.setEnabled(true);

                        text1.setText(rs.getString("SSn"));
                        text2.setText(rs.getString("DOB"));
                        TName.setText(rs.getString("name"));
                        TAddress.setText(rs.getString("Address"));
                        TSalary.setText(rs.getString("Salary"));
                        TGender.setText(rs.getString("Gender"));
                        if (rs.isLast()){
                            next.setEnabled(false);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        //gets previous person in list
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    if (rs.previous()){
                        previous.setEnabled(true);
                        next.setEnabled(true);

                        text1.setText(rs.getString("SSn"));
                        text2.setText(rs.getString("DOB"));
                        TName.setText(rs.getString("name"));
                        TAddress.setText(rs.getString("Address"));
                        TSalary.setText(rs.getString("Salary"));
                        TGender.setText(rs.getString("Gender"));

                        if (rs.isFirst()) {
                            previous.setEnabled(false);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        //clears table
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                text1.setText("");
                text2.setText("");
                TName.setText("");
                TAddress.setText("");
                TSalary.setText("");
                TGender.setText("");
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_create_DB", "root", "");
                    Statement st = con.createStatement();

                    st.executeUpdate
                            ("INSERT INTO `data` " +
                                    "(`Gender`, `SSn`, `DOB`, `Salary`, `Address`, `name`) VALUES " +
                                    "(" +
                                    "'" + TGender.getText() + "'," +
                                    "'" + text1.getText() + "'," +
                                    "'" + text2.getText() + "'," +
                                    "'" + TSalary.getText() + "'," +
                                    "'" + TAddress.getText() + "'," +
                                    "'" + TName.getText() + "');");
                    st.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        JPanel p = new JPanel(new GridLayout(8, 2));
        p.add(label1);
        p.add(text1);
        p.add(label2);
        p.add(text2);
        p.add(LName);
        p.add(TName);
        p.add(LAddress);
        p.add(TAddress);
        p.add(LSalary);
        p.add(TSalary);
        p.add(LGender);
        p.add(TGender);
        p.add(previous);
        p.add(next);
        p.add(clear);
        p.add(add);
        f.add(p);
        f.setVisible(true);
        f.pack();
    }

    public void actionPerformed(ActionEvent e){
    }
}