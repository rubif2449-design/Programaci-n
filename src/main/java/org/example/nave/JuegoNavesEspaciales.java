package org.example.nave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class JuegoNavesEspaciales extends JFrame implements KeyListener, ActionListener {

    private final int VELOCIDAD = 15;
    private final int ANCHO_VENTANA = 600;
    private final int ALTO_VENTANA = 600;

    private NaveEspacial nave;
    private com.mx.curso.nave.Enemigo enemigo;
    private Moneda moneda;
    private JLabel infoPanel;
    private JPanel gamePanel;
    private Timer gameLoop;

    public JuegoNavesEspaciales() {
        super("Juego de Naves Espaciales (Integración POO)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ANCHO_VENTANA, ALTO_VENTANA);
        setLayout(new BorderLayout());

        // --- 1. Inicialización y Objetos ---
        infoPanel = new JLabel("Vidas: 3 | Puntos: 0", JLabel.CENTER);
        infoPanel.setFont(new Font("Arial", Font.BOLD, 16));
        add(infoPanel, BorderLayout.NORTH);

        gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setBackground(Color.BLACK); // Espacio
        add(gamePanel, BorderLayout.CENTER);

        // Crear instancias de los objetos
        nave = new NaveEspacial(ANCHO_VENTANA / 2 - 20, ALTO_VENTANA - 80);
        enemigo = new com.mx.curso.nave.Enemigo();
        moneda = new Moneda();

        gamePanel.add(nave);
        gamePanel.add(enemigo);
        gamePanel.add(moneda);

        // Posicionar objetos
        enemigo.setLocation(100, 50);
        moneda.reubicar(ANCHO_VENTANA, ALTO_VENTANA);

        // --- 2. Eventos y Bucle ---
        setFocusable(true);
        addKeyListener(this);

        gameLoop = new Timer(50, this); // Bucle del juego: 50ms por frame
        gameLoop.start();

        setVisible(true);
    }

    private void actualizarUI() {
        infoPanel.setText("Vidas: " + nave.getVidas() + " | Puntos: " + nave.getPuntos());
    }

    /**
     * Bucle del juego (Timer) - Maneja el movimiento y las colisiones
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // 1. Mover Enemigo
        enemigo.moverAleatorio(gamePanel.getWidth(), gamePanel.getHeight());

        // 2. Lógica de Colisión (Nave vs. Enemigo)
        if (nave.getBounds().intersects(enemigo.getBounds())) {
            // Llama al método del objeto NaveEspacial
            nave.perderVida();
            actualizarUI();

            // Reubicar enemigo para simular que ha sido destruido o evadido
            enemigo.setLocation(new Random().nextInt(gamePanel.getWidth()), 0);

            if (nave.getVidas() <= 0) {
                gameLoop.stop();
                JOptionPane.showMessageDialog(this, "¡GAME OVER! Puntos: " + nave.getPuntos(), "Fin del Juego", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }

        // 3. Lógica de Colisión (Nave vs. Moneda)
        if (nave.getBounds().intersects(moneda.getBounds())) {
            // Llama al método del objeto NaveEspacial
            nave.sumarPunto();
            actualizarUI();

            // Llama al método del objeto Moneda
            moneda.reubicar(gamePanel.getWidth(), gamePanel.getHeight());
        }
    }

    // --- KeyListener para mover el Jugador (Nave) ---

    @Override
    public void keyPressed(KeyEvent e) {
        int dx = 0;
        int dy = 0;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                dx = -VELOCIDAD;
                break;
            case KeyEvent.VK_RIGHT:
                dx = VELOCIDAD;
                break;
            case KeyEvent.VK_UP:
                dy = -VELOCIDAD;
                break;
            case KeyEvent.VK_DOWN:
                dy = VELOCIDAD;
                break;
        }

        // Llama al método mover() del objeto NaveEspacial
        nave.mover(dx, dy);

        // Mantener nave dentro de límites
        nave.setLocation(
                Math.max(0, Math.min(nave.getX(), gamePanel.getWidth() - nave.getWidth())),
                Math.max(0, Math.min(nave.getY(), gamePanel.getHeight() - nave.getHeight()))
        );
    }

    // Métodos KeyListener no usados
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    // --- Método Main ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JuegoNavesEspaciales());
    }
}
