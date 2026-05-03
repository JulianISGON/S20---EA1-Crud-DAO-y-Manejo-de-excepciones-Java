package io.funcionarios.ui;

import io.funcionarios.model.EstadoCivil;
import io.funcionarios.model.Funcionario;
import io.funcionarios.model.TipoDocumento;
import io.funcionarios.service.FuncionarioService;
import io.funcionarios.exception.DAOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Diálogo para crear o editar un funcionario.
 */
public class DialogoFuncionario extends JDialog {

    private final FuncionarioService servicio;
    private Funcionario funcionario; // null = crear

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDocumento;
    private JComboBox<TipoDocumento> cmbTipoDocumento;
    private JComboBox<EstadoCivil> cmbEstadoCivil;
    private JTextField txtSalario;
    private JTextField txtFechaIngreso;
    private JTextField txtEmail;
    private JTextField txtTelefono;

    public DialogoFuncionario(Frame parent, FuncionarioService servicio, Funcionario funcionario) {
        super(parent, true);
        this.servicio = servicio;
        this.funcionario = funcionario;

        setTitle(funcionario == null ? "Crear Funcionario" : "Editar Funcionario");
        initComponents();
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4,4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        // Nombre
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField(20);
        gbc.gridx = 1; panel.add(txtNombre, gbc);
        y++;

        // Apellido
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Apellido:"), gbc);
        txtApellido = new JTextField(20);
        gbc.gridx = 1; panel.add(txtApellido, gbc);
        y++;

        // Documento
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Número documento:"), gbc);
        txtDocumento = new JTextField(20);
        gbc.gridx = 1; panel.add(txtDocumento, gbc);
        y++;

        // Tipo Documento
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Tipo documento:"), gbc);
        cmbTipoDocumento = new JComboBox<>();
        gbc.gridx = 1; panel.add(cmbTipoDocumento, gbc);
        y++;

        // Estado civil
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Estado civil:"), gbc);
        cmbEstadoCivil = new JComboBox<>();
        gbc.gridx = 1; panel.add(cmbEstadoCivil, gbc);
        y++;

        // Salario
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Salario:"), gbc);
        txtSalario = new JTextField(20);
        gbc.gridx = 1; panel.add(txtSalario, gbc);
        y++;

        // Fecha ingreso
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Fecha ingreso (YYYY-MM-DD):"), gbc);
        txtFechaIngreso = new JTextField(20);
        gbc.gridx = 1; panel.add(txtFechaIngreso, gbc);
        y++;

        // Email
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Email:"), gbc);
        txtEmail = new JTextField(20);
        gbc.gridx = 1; panel.add(txtEmail, gbc);
        y++;

        // Telefono
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Teléfono:"), gbc);
        txtTelefono = new JTextField(20);
        gbc.gridx = 1; panel.add(txtTelefono, gbc);
        y++;

        // Botones
        JPanel pnlBotones = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        pnlBotones.add(btnGuardar);
        pnlBotones.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2; panel.add(pnlBotones, gbc);

        btnGuardar.addActionListener(this::onGuardar);
        btnCancelar.addActionListener(e -> dispose());

        setContentPane(panel);

        cargarCatalogos();
        if (funcionario != null) {
            cargarDatosAFormulario();
        }
    }

    private void cargarCatalogos() {
        try {
            List<TipoDocumento> tipos = servicio.listarTiposDocumento();
            DefaultComboBoxModel<TipoDocumento> modelTipos = new DefaultComboBoxModel<>();
            for (TipoDocumento t : tipos) modelTipos.addElement(t);
            cmbTipoDocumento.setModel(modelTipos);

            List<EstadoCivil> estados = servicio.listarEstadosCiviles();
            DefaultComboBoxModel<EstadoCivil> modelEstados = new DefaultComboBoxModel<>();
            for (EstadoCivil e : estados) modelEstados.addElement(e);
            cmbEstadoCivil.setModel(modelEstados);
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar catálogos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatosAFormulario() {
        txtNombre.setText(funcionario.getNombre());
        txtApellido.setText(funcionario.getApellido());
        txtDocumento.setText(funcionario.getNumeroDocumento());
        txtSalario.setText(funcionario.getSalario() != null ? funcionario.getSalario().toString() : "");
        txtFechaIngreso.setText(funcionario.getFechaIngreso() != null ? funcionario.getFechaIngreso().toString() : "");
        txtEmail.setText(funcionario.getEmail());
        txtTelefono.setText(funcionario.getTelefono());

        // Seleccionar tipo y estado
        if (funcionario.getTipoDocumento() != null) {
            cmbTipoDocumento.setSelectedItem(funcionario.getTipoDocumento());
        }
        if (funcionario.getEstadoCivil() != null) {
            cmbEstadoCivil.setSelectedItem(funcionario.getEstadoCivil());
        }
    }

    private void onGuardar(ActionEvent evt) {
        try {
            if (funcionario == null) funcionario = new Funcionario();

            funcionario.setNombre(txtNombre.getText().trim());
            funcionario.setApellido(txtApellido.getText().trim());
            funcionario.setNumeroDocumento(txtDocumento.getText().trim());
            funcionario.setTipoDocumento((TipoDocumento) cmbTipoDocumento.getSelectedItem());
            funcionario.setEstadoCivil((EstadoCivil) cmbEstadoCivil.getSelectedItem());

            try {
                funcionario.setSalario(new BigDecimal(txtSalario.getText().trim()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Salario inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                funcionario.setFechaIngreso(LocalDate.parse(txtFechaIngreso.getText().trim()));
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Fecha de ingreso inválida. Use formato YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            funcionario.setEmail(txtEmail.getText().trim());
            funcionario.setTelefono(txtTelefono.getText().trim());

            if (funcionario.getIdFuncio() <= 0) {
                int id = servicio.crearFuncionario(funcionario);
                JOptionPane.showMessageDialog(this, "Funcionario creado con ID: " + id);
            } else {
                boolean ok = servicio.actualizarFuncionario(funcionario);
                if (ok) JOptionPane.showMessageDialog(this, "Funcionario actualizado correctamente.");
                else JOptionPane.showMessageDialog(this, "No se encontró el funcionario para actualizar.");
            }

            dispose();
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

