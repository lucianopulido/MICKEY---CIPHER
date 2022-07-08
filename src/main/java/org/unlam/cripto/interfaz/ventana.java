package org.unlam.cripto.interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.unlam.cripto.CipherRunner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.springframework.boot.autoconfigure.SpringBootApplication;

public class ventana extends JFrame {

    public String rutaInput;
    private JPanel contentPane;
    private JTextField textNombreImagen;
    private JButton btnNewButton_1;
    public BigInteger key = new BigInteger("ab8322676fd1333f160f", 16);
    public BigInteger iv = new BigInteger("a7a36548a932c54a0cfc", 16);

    public String binaryKey = key.toString(2);
    public String binaryIV = iv.toString(2);

    public static String imageEncrypted = "C:\\Users\\Luciano\\Desktop\\Mickey\\imagenes\\encrypted.bmp";
	/*public String getRutaInput() {
        return rutaInput;
    }*/

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ventana frame = new ventana();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */

    public ventana() {

        System.out.println("BinaryKey: " + binaryKey);
        System.out.println("BinaryIV: " + binaryIV);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 504, 296);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textNombreImagen = new JTextField();
        textNombreImagen.setBounds(113, 68, 263, 20);
        contentPane.add(textNombreImagen);
        textNombreImagen.setColumns(10);

        JButton btnNewButton = new JButton("Cargar imagen a encriptar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String Ruta = "";
                JFileChooser jFileChooser = new JFileChooser();
                FileNameExtensionFilter filtrado = new FileNameExtensionFilter("BMP, JPG", "bmp", "jpg");
                jFileChooser.setFileFilter(filtrado);

                int respuesta = jFileChooser.showOpenDialog(ventana.this);
                if (respuesta == JFileChooser.APPROVE_OPTION) {
                    Ruta = jFileChooser.getSelectedFile().getPath();
                    textNombreImagen.setText(jFileChooser.getSelectedFile().getName());
                    rutaInput = Ruta;
                    System.out.print(Ruta);

                }
            }
        });
        btnNewButton.setBounds(144, 23, 199, 23);
        contentPane.add(btnNewButton);

        btnNewButton_1 = new JButton("ENCRIPTAR/DESENCRIPTAR");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SpringApplication.run(CipherRunner.class, binaryKey, binaryIV, rutaInput, imageEncrypted);
            }

        });
        btnNewButton_1.setBounds(176, 143, 143, 73);
        contentPane.add(btnNewButton_1);


    }
}
