package jdbc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class inputData extends JFrame{

    Connector connector = new Connector();

    JFrame window = new JFrame("Login");

    JLabel lUsername = new JLabel("Username");
    JTextField tfUsername = new JTextField();
    JLabel lPassword = new JLabel("Password");
    JPasswordField tfPassword = new JPasswordField();


    JButton btnTambahPanel = new JButton("Sign Up");
    JButton btnLihat = new JButton("Login");

    public inputData() {
        window.setLayout(null);
        window.setSize(380,200);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        window.add(tfUsername);
        window.add(tfPassword);
        window.add(lUsername);
        window.add(lPassword);
        window.add(btnTambahPanel);
        window.add(btnLihat);



        lUsername.setBounds(5, 35, 120, 20);
        lPassword.setBounds(5, 60, 120, 20);

        tfUsername.setBounds(110, 35, 120, 20);
        tfPassword.setBounds(110, 60, 120, 20);


        btnTambahPanel.setBounds(250, 35, 90, 20);
        btnLihat.setBounds(250,60,90,20);


        btnTambahPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String query = "INSERT INTO `users`(`username`, `password`) VALUES ('"+getUsername()+"','"+getPassword() + "')";

                    connector.statement = connector.koneksi.createStatement();
                    connector.statement.executeUpdate(query);

                    System.out.println("Sign Up Berhasil");
                    JOptionPane.showMessageDialog(null,"Sign Up Berhasil !!");
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });

        btnLihat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //
                try {
                    String query = "select * from users";

                    connector.statement = connector.koneksi.createStatement();
                    //connector.statement.executeUpdate(query);

                    ResultSet list = connector.statement.executeQuery(query);
                    boolean ketemu = false;
                    while(list.next()){
                        if(getUsername().equals(list.getString("username")) ){
                            if (getPassword().equals(list.getString("password"))){
                                ketemu = true;
                                System.out.println(ketemu);
                            }
                        }
                    }
                    if (ketemu == true){
                        JOptionPane.showMessageDialog(null,"Login Berhasil !!");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Login Gagal !!");
                    }


                } catch (Exception ex){

                }
            }
        });
    }



    public String getUsername(){
        return tfUsername.getText();
    }

    public String getPassword() {
        return tfPassword.getText();
    }

}

