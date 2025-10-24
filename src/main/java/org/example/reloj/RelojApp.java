package org.example.reloj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RelojApp extends JFrame {

    private final RelojDigital motorReloj;
    private final JLabel interfazReloj;

    public RelojApp() {

        setTitle("Reloj Digital (Timer y Lambda)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        motorReloj = new RelojDigital();

        interfazReloj = new JLabel("Cargando...", SwingConstants.CENTER);
        interfazReloj.setFont(new Font("Monospaced", Font.BOLD, 48));

        interfazReloj.setPreferredSize(new Dimension(300, 80));
        interfazReloj.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        add(interfazReloj);

        int delay = 1000;

        ActionListener tareaRepetitiva = (ActionEvent e) -> {
            String nuevaHora = motorReloj.getHoraFormateada();
            interfazReloj.setText(nuevaHora);
        };

        Timer timer = new Timer(delay, tareaRepetitiva);

        timer.start();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RelojApp());
    }
}

