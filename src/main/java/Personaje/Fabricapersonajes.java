import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FabricaPersonajes extends JFrame implements KeyListener {

    private final int VELOCIDAD = 10;
    private ArrayList<Personaje> personajes; // Lista para mantener todos los objetos

    public FabricaPersonajes() {
        super("Fábrica de Personajes (POO)");

        // Ventana Principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);
        setFocusable(true);
        addKeyListener(this);

        // 2. Crear las instancias (Objetos)
        personajes = new ArrayList<>();

        // Gato
        Personaje gato = new Personaje(Color.ORANGE);
        gato.setLocation(50, 50);
        personajes.add(gato);
        add(gato);

        //Robot
        Personaje robot = new Personaje(Color.CYAN);
        robot.setText("Robot");
        robot.setLocation(150, 50);
        personajes.add(robot);
        add(robot);

        // Fantasma
        Personaje fantasma = new Personaje(Color.MAGENTA);
        fantasma.setText("👻");
        fantasma.setLocation(250, 50);
        personajes.add(fantasma);
        add(fantasma);

        // Mostrar la ventana
        setVisible(true);
    }

    /**
     * Este método se llama cada vez que se presiona una tecla.
     * Aquí se implementa el comportamiento de movimiento compartido.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int dx = 0;
        int dy = 0;

        // Determinar la dirección del movimiento
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

        // Aplicar el mismo movimiento a TODOS los objetos (Instancias)
        for (Personaje p : personajes) {
            p.mover(dx, dy);
        }
    }

    // Métodos KeyListener que no usamos, pero deben ser implementados
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    // --- Método Main ---
    public static void main(String[] args) {
        // Ejecutar la aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> new FabricaPersonajes());
    }
}
