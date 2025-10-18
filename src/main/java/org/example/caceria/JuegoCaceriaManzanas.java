import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Clase principal que gestiona el juego, el bucle y la colisión.
 */
public class JuegoCaceriaManzanas extends JFrame implements KeyListener, ActionListener {

    private final int VELOCIDAD = 15;
    private Jugador jugador;
    private Manzana manzana;
    private JLabel infoPanel;
    private JPanel gamePanel;
    private Timer gameLoop;

    public JuegoCaceriaManzanas() {
        super("Cacería de Manzanas (Atributos y Métodos)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);

        // 1. Configuración de la interfaz
        setLayout(new BorderLayout());

        // Panel de información (Puntos y Vidas)
        infoPanel = new JLabel("Puntos: 0 | Vidas: 3", JLabel.CENTER);
        infoPanel.setFont(new Font("Arial", Font.BOLD, 16));
        add(infoPanel, BorderLayout.NORTH);

        // Panel del juego
        gamePanel = new JPanel();
        gamePanel.setLayout(null); // Permite el posicionamiento libre
        gamePanel.setBackground(new Color(150, 255, 150)); // Fondo verde
        add(gamePanel, BorderLayout.CENTER);

        // 2. Creación de instancias (Objetos)
        jugador = new Jugador(280, 200);
        manzana = new Manzana();

        gamePanel.add(jugador);
        gamePanel.add(manzana);

        // 3. Inicializar y posicionar objetos
        manzana.reubicar(gamePanel);

        // 4. Configuración del KeyListener para movimiento
        setFocusable(true);
        addKeyListener(this);

        // 5. Bucle del juego (Swing Timer)
        gameLoop = new Timer(50, this); // Cada 50ms, llama a actionPerformed
        gameLoop.start();

        setVisible(true);
    }

    private void actualizarUI() {
        infoPanel.setText("Puntos: " + jugador.getPuntos() + " | Vidas: " + jugador.getVidas());
    }

    /**
     * Método llamado por el Timer (bucle del juego).
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lógica de colisión
        if (jugador.getBounds().intersects(manzana.getBounds())) {
            // Colisión detectada

            // Llama al método del objeto Jugador para manipular su atributo privado
            jugador.sumarPunto();

            // Reubicar la Manzana
            manzana.reubicar(gamePanel);

            // Actualizar la interfaz
            actualizarUI();
        }

        // Opcional: Si el jugador pierde todas las vidas, detener el juego
        if (jugador.getVidas() <= 0) {
            gameLoop.stop();
            JOptionPane.showMessageDialog(this, "¡Juego Terminado! Puntuación: " + jugador.getPuntos(), "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // --- KeyListener para mover el Jugador ---

    @Override
    public void keyPressed(KeyEvent e) {
        int x = jugador.getX();
        int y = jugador.getY();

        // Usar los métodos setLocation() de JLabel para mover
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                jugador.setLocation(x - VELOCIDAD, y);
                break;
            case KeyEvent.VK_RIGHT:
                jugador.setLocation(x + VELOCIDAD, y);
                break;
            case KeyEvent.VK_UP:
                jugador.setLocation(x, y - VELOCIDAD);
                break;
            case KeyEvent.VK_DOWN:
                jugador.setLocation(x, y + VELOCIDAD);
                break;
        }
        // Asegurarse de que el jugador se mantenga dentro del panel
        jugador.setLocation(
                Math.max(0, Math.min(jugador.getX(), gamePanel.getWidth() - jugador.getWidth())),
                Math.max(0, Math.min(jugador.getY(), gamePanel.getHeight() - jugador.getHeight()))
        );
    }

    // Métodos KeyListener que no usamos
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    // --- Método Main ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JuegoCaceriaManzanas());
    }
}
