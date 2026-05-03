package io.funcionarios.ui;

import io.funcionarios.model.Funcionario;
import io.funcionarios.service.FuncionarioService;
import io.funcionarios.exception.DAOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

/**
 * Ventana principal de la aplicación.
 */
public class VentanaPrincipal extends JFrame {

    private final FuncionarioService servicio;
    private JTable tabla;
    private DefaultTableModel modelo;
    private boolean ordenarIdAscendente = true;
    private JButton btnOrdenarId;

    public VentanaPrincipal() {
        super("Gesti\u00f3n de funcionarios");
        this.servicio = new FuncionarioService();
        initComponents();
        cargarDatos();
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Panel de botones
        JPanel panelBotones = new JPanel();
        JButton btnCrear = new JButton("Crear");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnRefrescar = new JButton("Refrescar");
        JButton btnCapturar = new JButton("Capturar");
        btnOrdenarId = new JButton("ID Asc");
        panelBotones.add(btnCrear);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRefrescar);
        panelBotones.add(btnOrdenarId);
        panelBotones.add(btnCapturar);
        add(panelBotones, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"ID","Nombre","Apellido","Documento","Tipo","Estado","Salario","FechaIngreso","Email","Telefono"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        // Reduce el ancho visible de la tabla para dejar márgenes laterales.
        scrollTabla.setPreferredSize(new Dimension(920, 420));
        JPanel panelTabla = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelTabla.add(scrollTabla);
        add(panelTabla, BorderLayout.CENTER);

        // Acciones
        btnCrear.addActionListener(e -> abrirDialogoCrear());
        btnEditar.addActionListener(e -> abrirDialogoEditar());
        btnEliminar.addActionListener(e -> eliminarSeleccionado());
        btnRefrescar.addActionListener(e -> cargarDatos());
        btnOrdenarId.addActionListener(e -> alternarOrdenId());
        btnCapturar.addActionListener(e -> {
            try {
                String archivo = capturarPantalla();
                JOptionPane.showMessageDialog(this, "Captura guardada: " + archivo);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al capturar pantalla: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Captura una imagen de la ventana actual (contenido) y la guarda en /build/screenshots.
     * @return ruta del archivo donde se guardó la imagen
     * @throws Exception si ocurre un error de E/S
     */
    private String capturarPantalla() throws Exception {
        // Preparar directorio
        File dir = new File("build/screenshots");
        if (!dir.exists()) dir.mkdirs();

        // Crear imagen del tamaño del contenido
        Component contenido = getContentPane();
        BufferedImage img = new BufferedImage(contenido.getWidth(), contenido.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        contenido.paintAll(g2d);
        g2d.dispose();

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        File salida = new File(dir, "ventana_" + timestamp + ".png");
        ImageIO.write(img, "png", salida);
        return salida.getAbsolutePath();
    }

    private void cargarDatos() {
        try {
            List<Funcionario> lista = servicio.listarFuncionarios();
            Comparator<Funcionario> comparadorId = Comparator.comparingInt(Funcionario::getIdFuncio);
            lista.sort(ordenarIdAscendente ? comparadorId : comparadorId.reversed());
            modelo.setRowCount(0);
            for (Funcionario f : lista) {
                modelo.addRow(new Object[] {
                        f.getIdFuncio(),
                        f.getNombre(),
                        f.getApellido(),
                        f.getNumeroDocumento(),
                        f.getTipoDocumento() != null ? f.getTipoDocumento().getDescripcion() : null,
                        f.getEstadoCivil() != null ? f.getEstadoCivil().getDescripcion() : null,
                        f.getSalario(),
                        f.getFechaIngreso(),
                        f.getEmail(),
                        f.getTelefono()
                });
            }
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar funcionarios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alternarOrdenId() {
        ordenarIdAscendente = !ordenarIdAscendente;
        btnOrdenarId.setText(ordenarIdAscendente ? "ID Asc" : "ID Desc");
        cargarDatos();
    }

    private void abrirDialogoCrear() {
        new DialogoFuncionario(this, servicio, null);
        cargarDatos();
    }

    private void abrirDialogoEditar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un funcionario para editar.");
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        try {
            Funcionario f = servicio.obtenerFuncionario(id);
            new DialogoFuncionario(this, servicio, f);
            cargarDatos();
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un funcionario para eliminar.");
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Confirma eliminar el funcionario?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            boolean ok = servicio.eliminarFuncionario(id);
            if (ok) JOptionPane.showMessageDialog(this, "Funcionario eliminado.");
            else JOptionPane.showMessageDialog(this, "No se encontró el funcionario.");
            cargarDatos();
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new VentanaPrincipal().setVisible(true);
        });
    }
}

