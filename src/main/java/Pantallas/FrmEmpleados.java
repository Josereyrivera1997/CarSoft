/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantallas;

import Clases.Acceso;
import Clases.Cargo_empleado;
import Clases.Empleado;
import Clases.FacturaJRADATASOURCE;
import Clases.Hash;
import Clases.HistoricoCargo_empleado;
import Clases.HistoricoSueldo_empleado;
import Clases.Persona;
import Clases.Tipo_Documento;
import javax.swing.table.DefaultTableModel;
import Clases.Usuarios;
import FormModales.ModalCliente;
import FormModales.ModalEmpleado;
import JPAController.AccesoJpaController;
import JPAController.Cargo_empleadoJpaController;
import JPAController.EmpleadoJpaController;
import JPAController.HistoricoCargo_empleadoJpaController;
import JPAController.HistoricoSueldo_empleadoJpaController;
import JPAController.PersonaJpaController;
import JPAController.Tipo_DocumentoJpaController;
import JPAController.UsuariosJpaController;
import static Pantallas.FrmMenu.labelEmple1;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Component;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Usuario
 */
public final class FrmEmpleados extends javax.swing.JFrame {
    
    EntityManagerFactory emf =Persistence.createEntityManagerFactory("CarSoft");
    UsuariosJpaController UsuariosDao = new UsuariosJpaController(emf);
    HistoricoSueldo_empleadoJpaController HistoricoSueldoDao = new HistoricoSueldo_empleadoJpaController(emf);
    HistoricoCargo_empleadoJpaController HistoricoCargoDao = new HistoricoCargo_empleadoJpaController(emf);
    Cargo_empleadoJpaController CargoDao = new Cargo_empleadoJpaController(emf);
    /**
     * Creates new form FrmEmpleados
     */
    Tipo_DocumentoJpaController TipoDocumentodao = new Tipo_DocumentoJpaController(emf);
    PersonaJpaController Personadao = new PersonaJpaController(emf);
    EmpleadoJpaController Empleadodao = new EmpleadoJpaController(emf);
    AccesoJpaController accesoDao = new AccesoJpaController(emf);
    Connection con;
    
    public FrmEmpleados(Acceso a) {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);

        //Tab_Empleado.setIconAt(0, (new javax.swing.ImageIcon(getClass().getResource("/Img/Empleado.png"))));
       this.getContentPane().setBackground(new Color(0, 75, 143));
for (int i = 0; i < this.Tab_Empleado.getTabCount(); i++) { 
      this.Tab_Empleado.setBackgroundAt(i, new Color(0, 75, 143)); 
      this.Tab_Empleado.getComponentAt(i).setBackground(new Color(0, 75, 143)); 
} 
       this.Tab_Empleado.setBackgroundAt(0, new Color(0, 75, 143));
       this.Tab_Empleado.setBackgroundAt(1, new Color(0, 75, 143));
       this.Tab_Empleado.setBackgroundAt(2, new Color(0, 75, 143));
       this.Tab_Empleado.setBackgroundAt(3, new Color(0, 75, 143));
       this.Tab_Empleado.setBackgroundAt(4, new Color(0, 75, 143));
        this.jPanel1.setBackground( new Color(0, 75, 143));
        this.jPanel2.setBackground( new Color(0, 75, 143));
        this.jPanel3.setBackground( new Color(0, 75, 143));
        this.jPanel4.setBackground( new Color(0, 75, 143));
        this.jPanel5.setBackground( new Color(0, 75, 143));
        labelempleado.setVisible(false);
        btnAgregar1.setBackground( new Color(14, 209, 69));
        setIconImage(new ImageIcon(getClass().getResource("/Img/CarSoft-removebg-preview.png")).getImage());
        this.btnSalir1.setBackground( new Color(236, 28, 36));
        btnModificar1.setBackground( new Color(14, 209, 69));
        this.btnLimpiar1.setBackground( new Color(14, 209, 69));
        btnDesactivar1.setBackground( new Color(14, 209, 69));
        this.btnRegresar1.setBackground( new Color(14, 209, 69));
        this.btnGenerar.setBackground( new Color(14, 209, 69));
        this.btnGenerar2.setBackground( new Color(14, 209, 69));
        this.btnGenerar3.setBackground( new Color(14, 209, 69));
        this.btnGenerar4.setBackground( new Color(14, 209, 69));
        this.btnGenerar5.setBackground( new Color(14, 209, 69));
        this.btnGenerar6.setBackground( new Color(14, 209, 69));
        this.btnGenerar7.setBackground( new Color(14, 209, 69));
        this.btnGenerar8.setBackground( new Color(14, 209, 69));
        btnGenerar9.setBackground( new Color(14, 209, 69));
        btnGenerar10.setBackground( new Color(14, 209, 69));
        
        this.btnAgregar.setBackground( new Color(14, 209, 69));
        this.btnSalir.setBackground( new Color(236, 28, 36));
        this.btnModificar.setBackground( new Color(14, 209, 69));
        this.btnLimpiar.setBackground( new Color(14, 209, 69));
        this.btnDesactivar.setBackground( new Color(14, 209, 69));
        this.btnRegresar.setBackground( new Color(14, 209, 69));
        
        this.btnAgregar2.setBackground( new Color(14, 209, 69));
        this.btnSalir2.setBackground( new Color(236, 28, 36));
        this.btnModificar2.setBackground( new Color(14, 209, 69));
        this.btnLimpiar2.setBackground( new Color(14, 209, 69));
        this.btnDesactivar2.setBackground( new Color(14, 209, 69));
        btnDesactivar2.setEnabled(false);
        this.btnRegresar2.setBackground( new Color(14, 209, 69));
        btnBuscar1.setBackground( new Color(14, 209, 69));
        this.btnBuscar.setBackground( new Color(14, 209, 69));
        this.btnBuscar2.setBackground( new Color(14, 209, 69));
        this.btnBuscar.setBackground( new Color(14, 209, 69));
        this.btnSalir3.setBackground( new Color(236, 28, 36));
        this.btnRegresar3.setBackground( new Color(14, 209, 69));
        this.btnSalirCargoHistorico.setBackground( new Color(236, 28, 36));
        this.btnRegresar4.setBackground( new Color(14, 209, 69));
        //this.cmbIDEmpleado.setEnabled(false);
        /*int fila= tblEmpleados.getRowCount();
        for(int i = 0; i < fila;i++)
        {
        if(i%2==0){*/
            tblEmpleados.setForeground(Color.WHITE);
            tblEmpleados.setBackground(Color.BLACK);
            
            tblNuevoCargo.setForeground(Color.WHITE);
            tblNuevoCargo.setBackground(Color.BLACK);
            
            Tbl_Usuarios.setForeground(Color.WHITE);
            Tbl_Usuarios.setBackground(Color.BLACK);
            
            tblHistorialSueldo.setForeground(Color.WHITE);
            tblHistorialSueldo.setBackground(Color.BLACK);
            
            jTbHistorialCargo.setForeground(Color.WHITE);
            jTbHistorialCargo.setBackground(Color.BLACK);
        /*}
        else {

        }*/
        btnModificar1.setEnabled(false);
        btnModificar.setEnabled(false);
        btnModificar2.setEnabled(false);
        //jPanel1.setBackground(Color.CYAN);
        txtIDUsuario.setBackground(Color.GRAY);
        createComboBoxEmpleado();
        createTableEmpleado();
        createComboTipoDocumentoEmpleado();
        createComboCargoEmpleado();
        btnDesactivar.setEnabled(false);
        btnDesactivar1.setEnabled(false);
        createTableUsuario();
        createCmbIDEmpleado();
        createComboBoxNuevoCargo();
        createTableNuevoCargo();
        crearTbHistorialSueldo();
        crearTbHistorialCargo();
        habilitarAgregarEmpleado();
        setlabelEmpleado(String.valueOf(labelEmple1.getText()));
        txtIDUsuario.setText(String.valueOf(UsuariosDao.getUsuariosCount()+1));
        
        try{
            //Empleado
            btnAgregar1.setVisible(a.getPermisoSeccionEmpleadosAgregar());
            btnModificar1.setVisible(a.getPermisoSeccionEmpleadosModificar());
            btnDesactivar1.setVisible(a.getPermisoSeccionEmpleadosDesactivar());
            btnBuscar1.setVisible(a.getPermisoSeccionEmpleadosBuscar());
            btnGenerar10.setVisible(a.getPermisoSeccionEmpleadosReportePdf());
            btnGenerar9.setVisible(a.getPermisoSeccionEmpleadosReporteExcel());
            
            //Cargo
            btnAgregar2.setVisible(a.getPermisoSeccionCargosAgregar());
            btnModificar2.setVisible(a.getPermisoSeccionCargosModificar());
            btnDesactivar2.setVisible(a.getPermisoSeccionCargosDesactivar());
            btnGenerar.setVisible(a.getPermisoSeccionCargosReportePdf());
            btnGenerar2.setVisible(a.getPermisoSeccionCargosReporteExcel());
            
            
            //Usuarios
            btnAgregar.setVisible(a.getPermisoSeccionUsuariosAgregar());
            btnModificar.setVisible(a.getPermisoSeccionUsuariosModificar());
            btnDesactivar.setVisible(a.getPermisoSeccionUsuariosDesactivar());
            btnBuscar.setVisible(a.getPermisoSeccionUsuariosBuscarEmpleado());
            btnBuscar2.setVisible(a.getPermisoSeccionUsuariosBuscarUsuario());
            btnGenerar4.setVisible(a.getPermisoSeccionUsuariosReportePdf());
            btnGenerar3.setVisible(a.getPermisoSeccionUsuariosReporteExcel());
            
            //Historial Sueldos
            btnGenerar6.setVisible(a.getPermisoSeccionHistorialSueldosReportePdf());
            btnGenerar5.setVisible(a.getPermisoSeccionHistorialSueldosReporteExcel());
            
            //Historial Cargos
            btnGenerar8.setVisible(a.getPermisoSeccionHistorialCargosReportePdf());
            btnGenerar7.setVisible(a.getPermisoSeccionHistorialCargosReporteExcel());
            
            //Acceso Usuario
        }catch(Exception e){
            //Acceso root
        }
        
        
    }
    private void habilitarAgregarEmpleado(){
        //Validacion de agregar
        if(TipoDocumentodao.getTipo_DocumentoCount()!=0 || CargoDao.getCargo_empleadoCount()!=0){
            btnAgregar.setEnabled(true);
        }
        else{
            
        }
        //Fin de validacion agregar
    } 
    private void createCmbIDEmpleado(){
        
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        cmbIDEmpleado.setModel(modelo);
        cmbIDEmpleado.setModel(modelo);
        List<Empleado>  temp = Empleadodao.findEmpleadoEntities();
        List<Persona> tempp = Personadao.findPersonaEntities();
        modelo.addElement("Seleccione...");
        for (Empleado cc : temp){     
            for(Persona pp : tempp){
            if(pp.getId_persona()== cc.getId_Persona()){
            modelo.addElement(cc.getId_Empleado()+". "+pp.getNombre() +" "+pp.getApellido());         
           // return;
        }
        }
    }
    }
    private void btnActivarDesactivarUsuario(){
        Usuarios temp;
        temp = UsuariosDao.findUsuarios(Integer.parseInt(txtIDUsuario.getText()));
        
        if(temp.isEstado()){
        btnDesactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDesactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Desactivar.png"))); // NOI18N
        btnDesactivar.setText("Desactivar");  
        btnDesactivar.setEnabled(true);
        }
        else{
            btnDesactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            btnDesactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Activar.png"))); // NOI18N
            btnDesactivar.setText("Activar");
            btnDesactivar.setEnabled(true);
        }
    }
    private void btnActivarDesactivarCargo(){
        Cargo_empleado temp = new Cargo_empleado();
        temp = CargoDao.findCargo_empleado(cmbIDNuevoCargo.getSelectedIndex());
        
        if(temp.isEstado()){
        btnDesactivar2.setFont(new java.awt.Font("Tahoma", 1, 11));
        btnDesactivar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Desactivar.png")));
        btnDesactivar2.setText("Desactivar");  
        btnDesactivar1.setEnabled(true);
        }
        else{
            btnDesactivar2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            btnDesactivar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Activar.png")));
            btnDesactivar2.setText("Activar");
            btnDesactivar2.setEnabled(true);
        }
        }
        private  void crearTbHistorialSueldo(){
        DefaultTableModel modelo = (DefaultTableModel) tblHistorialSueldo.getModel();
        tblHistorialSueldo.setModel(modelo);
        //modelo.addColumn("ID Sueldo Historico");
        //modelo.addColumn("Empleado");
        //modelo.addColumn("Sueldo");
        //modelo.addColumn("Desde");
        //modelo.addColumn("Hasta");
        //modelo.addColumn("Estado");
        int i;
        for(i=modelo.getRowCount()-1;i>=0;i--){
            modelo.removeRow(i);
        }
        List<HistoricoSueldo_empleado> temp = HistoricoSueldoDao.findHistoricoSueldo_empleadoEntities();
        List<Empleado> temp2 = Empleadodao.findEmpleadoEntities();
        List<Persona> tempo = Personadao.findPersonaEntities();
        String auxEmpleadonombre="";
        String auxclienteapellido="";
        String auxNombreApellido="";
        String auxEmpleado="";
        String auxfecha="";
        String auxfechab="";
        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');
        DecimalFormat formato1 = new DecimalFormat("###,###.00",separadoresPersonalizados);
        for(HistoricoSueldo_empleado e : temp){
            //if(e.getId_sueldo()==(cmbIDCargo.getSelectedIndex()+1) && e.getFecha_final()!=null){
            for(Empleado tp : temp2){
                if(tp.getId_Empleado() == e.getId_empleado()){
                 for(Persona p : tempo){   
                            if(p.getId_persona()==tp.getId_Persona()){
                            auxEmpleadonombre=(p.getNombre());
                            auxclienteapellido=(p.getApellido());
                            auxNombreApellido= auxEmpleadonombre+" "+auxclienteapellido;
                            auxEmpleado=(tp.getId_Empleado() + ". " + auxNombreApellido);
                            auxfecha=(e.getFecha_inicial().substring(8, 10)+"/"+e.getFecha_inicial().substring(5, 7)+"/"+e.getFecha_inicial().substring(0, 4));
                            
                            }   
                        }
                }
                if(e.getFecha_final()==null) {
                                            auxfechab="";
                                    } else {
                                        auxfechab=(e.getFecha_final().substring(8, 10)+"/"+e.getFecha_final().substring(5, 7)+"/"+e.getFecha_final().substring(0, 4));
                                    }
            }
                        
            double auxsueldo=e.getSueldo();
            
            modelo.addRow(new Object[]{
                                  e.getId_sueldo(),
                                  auxEmpleado,
                                  String.format("%,.2f",auxsueldo),                                 
                                  auxfecha,
                                  auxfechab,
                                  e.isEstado()? "Activo" : "Inactivo"
            });
        
            //}
            }
        
            
    }
       
 /*       public void crearcmbSueldoHistorico(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        
        cmbIDSueldo.setModel(modelo);
        List<Empleado> temp = Empleadodao.findEmpleadoEntities();
        
        List<Persona> tempp = Personadao.findPersonaEntities();
        modelo.addElement("Seleccione...");
        for (Empleado cc : temp){     
            for(Persona pp : tempp){
        temp.forEach((tpp)-> {
        tempp.forEach((tp) -> {
            if(tpp.getId_Persona()== tp.getId_persona()){
            modelo.addElement(tpp.getId_Empleado()+". "+tp.getNombre()+" "+tp.getApellido());
            }
        });   
        });          
            return;
        }
        }
    }*/
    private  void crearTbHistorialCargo(){
        DefaultTableModel modelo = (DefaultTableModel) jTbHistorialCargo.getModel();
        
        jTbHistorialCargo.setModel(modelo);
        int i;
        for(i=modelo.getRowCount()-1;i>=0;i--){
            modelo.removeRow(i);
        }
        //modelo.addColumn("ID Cargo Historico");
        //modelo.addColumn("Empleado");
        //modelo.addColumn("Cargo");
        //modelo.addColumn("Desde");
        //modelo.addColumn("Hasta");
        //modelo.addColumn("Estado");
        List<HistoricoCargo_empleado> temp = HistoricoCargoDao.findHistoricoCargo_empleadoEntities();
        List<Empleado> temp2 = Empleadodao.findEmpleadoEntities();
        List<Cargo_empleado> tempce = CargoDao.findCargo_empleadoEntities();
        String auxCargo="";
        List<Persona> tempo = Personadao.findPersonaEntities();
        String auxEmpleadonombre="";
        String auxclienteapellido="";
        String auxNombreApellido="";
        String auxEmpleado="";
        String auxfecha="";
        String auxfechab="";
        for(HistoricoCargo_empleado e : temp){
            //if(e.getId_sueldo()==(cmbIDCargo.getSelectedIndex()+1) && e.getFecha_final()!=null){
            for(Empleado tp : temp2){
                if(tp.getId_Empleado() == e.getId_empleado()){
                 for(Persona p : tempo){   
                            if(p.getId_persona()==tp.getId_Persona()){
                            auxEmpleadonombre=(p.getNombre());
                            auxclienteapellido=(p.getApellido());
                            auxNombreApellido= auxEmpleadonombre+" "+auxclienteapellido;
                            auxEmpleado=(tp.getId_Empleado() + ". " + auxNombreApellido);
                            auxfecha=(e.getFecha_inicial().substring(8, 10)+"/"+e.getFecha_inicial().substring(5, 7)+"/"+e.getFecha_inicial().substring(0, 4));   
                            }   
                        }
                }
                for(Cargo_empleado ce : tempce){
                    if(ce.getId_cargo()==e.getId_cargo()){
                        auxCargo=(ce.getId_cargo() + ". " + ce.getCargo());
                    }
                }
                if(e.getFecha_final()==null) {
                                            auxfechab="";
                                    } else {
                                        auxfechab=(e.getFecha_final().substring(8, 10)+"/"+e.getFecha_final().substring(5, 7)+"/"+e.getFecha_final().substring(0, 4));
                                    } 
            }  
            
            modelo.addRow(new Object[]{
                                  e.getId_cargo_historico(),
                                  auxEmpleado,
                                  auxCargo,
                                  auxfecha,
                                  auxfechab,
                                  e.isEstado()? "Activo" : "Inactivo"
            });
        
            //}
            }
        
            
    }
       /* public void crearcmbCargoHistorico(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        
        cmbIDCargo.setModel(modelo);
        List<Empleado> temp = Empleadodao.findEmpleadoEntities();
        
        List<Persona> tempp = Personadao.findPersonaEntities();
        modelo.addElement("Seleccione...");
        for (Empleado cc : temp){     
            for(Persona pp : tempp){
        temp.forEach((tpp)-> {
        tempp.forEach((tp) -> {
            if(tpp.getId_Persona()== tp.getId_persona()){
            modelo.addElement(tpp.getId_Empleado()+". "+tp.getNombre()+" "+tp.getApellido());
            }
        });   
        });          
            return;
        }
        }
    }*/

     private  void createTableUsuario(){
        DefaultTableModel modelo = (DefaultTableModel) Tbl_Usuarios.getModel();
            
            Tbl_Usuarios.setModel(modelo);
            int i;
        for(i=modelo.getRowCount()-1;i>=0;i--){
            modelo.removeRow(i);
        }
            //modelo.addColumn("Numero de Usuario");
            //modelo.addColumn("ID de Empleado");
            //modelo.addColumn("Nombre de usuario");
            //modelo.addColumn("Contraseña");
            //modelo.addColumn("Estado"); //NUEVO
            List<Usuarios> usuario = UsuariosDao.findUsuariosEntities();
            List<Empleado> temp2 = Empleadodao.findEmpleadoEntities();
            List<Persona> tempo = Personadao.findPersonaEntities();
            String auxEmpleadonombre="";
            String auxclienteapellido="";
            String auxNombreApellido="";
            String auxEmpleado="";
            
            if(usuario ==null){
                
            }
            else{
            for(Usuarios usu : usuario){
                for(Empleado tp : temp2){
                if(tp.getId_Empleado() == usu.getId_Empleado()){
                 for(Persona p : tempo){   
                            if(p.getId_persona()==tp.getId_Persona()){
                            auxEmpleadonombre=(p.getNombre());
                            auxclienteapellido=(p.getApellido());
                            auxNombreApellido= (auxEmpleadonombre+" "+auxclienteapellido);
                            auxEmpleado=(tp.getId_Empleado() + ". " + auxNombreApellido);
                            }   
                        }
                }
            } 
                modelo.addRow(
                    new Object[]{
                        usu.getId_Usuario(),
                        auxEmpleado,
                        usu.getId_Nombre(),
                        usu.getContraseña(), 
                        (usu.isEstado())? "Activo" : "Inactivo" //NUEVO
                    }
                );
            }
            }
    
    }
private void btnActivarDesactivarEmpleado(){
        Empleado temp = new Empleado();
        temp = Empleadodao.findEmpleado(cmbIDEmpleado1.getSelectedIndex());
        
        if(temp.isEstado()){
        btnDesactivar1.setFont(new java.awt.Font("Tahoma", 1, 11));
        btnDesactivar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Desactivar.png")));
        btnDesactivar1.setText("Desactivar");  
        btnDesactivar1.setEnabled(true);
        }
        else{
            btnDesactivar1.setFont(new java.awt.Font("Tahoma", 1, 11));
            btnDesactivar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Activar.png")));
            btnDesactivar1.setText("Activar");
            btnDesactivar1.setEnabled(true);
        }
        }
 private  void createComboBoxEmpleado(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        cmbIDEmpleado1.setModel(modelo);
        List<Empleado> temp = Empleadodao.findEmpleadoEntities();
        modelo.addElement("Nuevo");
        temp.forEach((c) -> {
        modelo.addElement(c.getId_Empleado());
        });
        }
 private  void createComboTipoDocumentoEmpleado(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        
        cmbTipoDocumentoEmpleado.setModel(modelo);
        List<Tipo_Documento> temp = TipoDocumentodao.findTipo_DocumentoEntities();
        modelo.addElement("Seleccione...");

        for(Tipo_Documento tp : temp){    
        temp.forEach((tpp) -> {
            modelo.addElement(tpp.getId_Tipo_Documento()+". "+tpp.getDocumento());
        });         
            return;
        }
                }
 private void createComboCargoEmpleado(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        cmbCargoEmpleado.setModel(modelo);
        List<Cargo_empleado> tempp = CargoDao.findCargo_empleadoEntities();
        modelo.addElement("Seleccione...");   
        for(Cargo_empleado tp : tempp){   
        tempp.forEach((tpp) -> {
            modelo.addElement(tpp.getId_cargo()+". "+tpp.getCargo());
     
        });
            return;
        }
        

                }

 private  void createTableEmpleado(){
        DefaultTableModel modelo = (DefaultTableModel) tblEmpleados.getModel();
        tblEmpleados.setModel(modelo);
        /*modelo.addColumn("ID Empleado");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Dirección");
        modelo.addColumn("Correo electrónico");
        modelo.addColumn("Fecha Registro");
        modelo.addColumn("Tipo Documento");
        modelo.addColumn("Documento");
        modelo.addColumn("Cargo");
        modelo.addColumn("Sueldo");
        modelo.addColumn("Estado");*/
        int i;
        for(i=modelo.getRowCount()-1;i>=0;i--){
            modelo.removeRow(i);
        }
        List<Persona> tempo = Personadao.findPersonaEntities();
        String auxNombre="";
        String auxApellido="";
        String auxDireccion="";
        String auxCorreo="";
        String auxTelefono="";
        String auxDocumento="";
        List<Empleado> temp = Empleadodao.findEmpleadoEntities();
        String aux1="";
        
        String auxfecharegistro="";
        List<Tipo_Documento> temp2 = TipoDocumentodao.findTipo_DocumentoEntities();
        int aux=0;
        List<HistoricoSueldo_empleado> temp3 = HistoricoSueldoDao.findHistoricoSueldo_empleadoEntities();
        double auxSueldo=0;
        List<HistoricoCargo_empleado> tempc = HistoricoCargoDao.findHistoricoCargo_empleadoEntities();
        int auxCargo=0;
        String auxfecha="";
        List<Cargo_empleado> tempce = CargoDao.findCargo_empleadoEntities();
        String auxCargoEmp = null;
        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        DecimalFormatSymbols separadoresPersonalizados2 = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#.##",separadoresPersonalizados);
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
        
        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);
        
        for(Empleado cc : temp){
            for(Persona p : tempo){
                if(p.getId_persona()==cc.getId_Persona()){
                    auxNombre=(p.getNombre());
                    auxApellido=(p.getApellido());
                    auxDireccion=(p.getDireccion());
                    auxCorreo=p.getCorreo_electroncio();
                    auxTelefono=p.getTelefono();
                    auxDocumento=p.getDocumento_id();
                    auxfecha=(cc.getFecha_ingreso().substring(8, 10)+"/"+cc.getFecha_ingreso().substring(5, 7)+"/"+cc.getFecha_ingreso().substring(0, 4));
                    for(Tipo_Documento tp : temp2){
                if(tp.getId_Tipo_Documento() == p.getID_tipo_documento()){
                    aux1=(tp.getId_Tipo_Documento()+". "+tp.getDocumento());
                }
            }
                    }
                for(HistoricoCargo_empleado hce : tempc){
                if(hce.getId_empleado() == cc.getId_Empleado()){
                    if(hce.getFecha_final()==null){
                        auxCargo=(hce.getId_cargo());
                        }
                    for(Cargo_empleado tp : tempce){
                    if(tp.getId_cargo()== hce.getId_cargo()){
                    auxCargoEmp=(hce.getId_cargo()+". "+tp.getCargo());
                        }   
                    }
                }
                    }
                    
            for(HistoricoSueldo_empleado hse : temp3){
                if(hse.getId_empleado() == cc.getId_Empleado()){
                    if(hse.getFecha_final()==null){
                        auxSueldo=hse.getSueldo();
                        }
                    }
                }
            
            }

            modelo.addRow(
                    new Object[]{
                        cc.getId_Empleado(),
                        auxNombre,
                        auxApellido,
                        auxTelefono,
                        auxDireccion,
                        auxCorreo,
                        auxfecha,
                        aux1,
                        auxDocumento,
                        auxCargoEmp,
                        String.format("%,.2f",auxSueldo),
                        //decimalFormat.format(auxSueldo),
                        //auxSueldo,
                        cc.isEstado()? "Activo" : "Inactivo",
                        //(cc.isEstado())?"Activo":"Inactivo"
                      
            });  
    }
        }
        public void createComboBoxNuevoCargo(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel(); 
        cmbIDNuevoCargo.setModel(modelo);
        List<Cargo_empleado> temp = CargoDao.findCargo_empleadoEntities();
        modelo.addElement("Nuevo");
        temp.forEach((tp) -> {
        modelo.addElement(tp.getId_cargo());
        });
        
    }
        
         private void createTableNuevoCargo(){
        DefaultTableModel modelo = (DefaultTableModel) tblNuevoCargo.getModel();
        tblNuevoCargo.setModel(modelo);
        int i;
        for(i=modelo.getRowCount()-1;i>=0;i--){
            modelo.removeRow(i);
        }
        /*modelo.addColumn("ID Cargo");
        modelo.addColumn("Nuevo Cargo");
        modelo.addColumn("Estado");*/
        
        List<Cargo_empleado> temp = CargoDao.findCargo_empleadoEntities();
        
        for(Cargo_empleado tp : temp)
            modelo.addRow(
                    new Object[]{
                        tp.getId_cargo(),
                        tp.getCargo(),
                        (tp.isEstado())?"Activo":"Inactivo"
            });  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tab_Empleado = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        cmbIDEmpleado1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbTipoDocumentoEmpleado = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtaDirec = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        btnAgregar1 = new javax.swing.JButton();
        btnModificar1 = new javax.swing.JButton();
        btnLimpiar1 = new javax.swing.JButton();
        btnDesactivar1 = new javax.swing.JButton();
        btnSalir1 = new javax.swing.JButton();
        btnRegresar1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbCargoEmpleado = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSueldo = new javax.swing.JTextField();
        btnBuscar1 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();
        btnGenerar9 = new javax.swing.JButton();
        btnGenerar10 = new javax.swing.JButton();
        labelempleado = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnRegresar2 = new javax.swing.JButton();
        btnSalir2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblNuevoCargo = new javax.swing.JTable();
        btnAgregar2 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        cmbIDNuevoCargo = new javax.swing.JComboBox<>();
        txtNuevoCargo = new javax.swing.JTextField();
        btnModificar2 = new javax.swing.JButton();
        btnLimpiar2 = new javax.swing.JButton();
        btnDesactivar2 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        btnGenerar = new javax.swing.JButton();
        btnGenerar2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtIDUsuario = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tbl_Usuarios = new javax.swing.JTable();
        cmbIDEmpleado = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnDesactivar = new javax.swing.JButton();
        txtContraseña = new javax.swing.JPasswordField();
        txtConfirmarContraseña = new javax.swing.JPasswordField();
        btnBuscar = new javax.swing.JButton();
        btnBuscar2 = new javax.swing.JButton();
        btnGenerar3 = new javax.swing.JButton();
        btnGenerar4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblHistorialSueldo = new javax.swing.JTable();
        btnRegresar3 = new javax.swing.JButton();
        btnSalir3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtEmpleadoSueldo = new javax.swing.JTextField();
        btnGenerar5 = new javax.swing.JButton();
        btnGenerar6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnRegresar4 = new javax.swing.JButton();
        btnSalirCargoHistorico = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTbHistorialCargo = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtEmpleadoCargo = new javax.swing.JTextField();
        btnGenerar7 = new javax.swing.JButton();
        btnGenerar8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Tab_Empleado.setBackground(new java.awt.Color(0, 204, 204));
        Tab_Empleado.setForeground(new java.awt.Color(255, 255, 255));
        Tab_Empleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tab_EmpleadoMouseClicked(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbIDEmpleado1.setEnabled(false);
        cmbIDEmpleado1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbIDEmpleado1ItemStateChanged(evt);
            }
        });
        cmbIDEmpleado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbIDEmpleado1ActionPerformed(evt);
            }
        });
        jPanel1.add(cmbIDEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 94, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("ID Empleado:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 91, 90, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 124, 90, -1));

        txtNombre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombre1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre1KeyTyped(evt);
            }
        });
        jPanel1.add(txtNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 173, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Apellidos:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 159, 90, -1));

        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosKeyTyped(evt);
            }
        });
        jPanel1.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 173, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Teléfono:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 188, 90, -1));

        txtTel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTelFocusLost(evt);
            }
        });
        txtTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelKeyTyped(evt);
            }
        });
        jPanel1.add(txtTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 173, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Correo electrónico:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 223, -1, -1));

        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCorreoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 173, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tipo Documento:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 103, -1));

        cmbTipoDocumentoEmpleado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoDocumentoEmpleadoItemStateChanged(evt);
            }
        });
        jPanel1.add(cmbTipoDocumentoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 113, -1));

        txtaDirec.setColumns(20);
        txtaDirec.setRows(5);
        txtaDirec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtaDirecKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(txtaDirec);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 201, -1));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Documento:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 103, -1));

        txtDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocumentoKeyTyped(evt);
            }
        });
        jPanel1.add(txtDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 113, -1));

        btnAgregar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/agregar.png"))); // NOI18N
        btnAgregar1.setText("Agregar");
        btnAgregar1.setBorder(null);
        btnAgregar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 277, 121, 41));

        btnModificar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModificar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/modificar.png"))); // NOI18N
        btnModificar1.setText("Modificar");
        btnModificar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 277, 146, -1));

        btnLimpiar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLimpiar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Limpiar.png"))); // NOI18N
        btnLimpiar1.setText("Limpiar");
        btnLimpiar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 277, -1, 41));

        btnDesactivar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDesactivar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Desactivar.png"))); // NOI18N
        btnDesactivar1.setText("Desactivar");
        btnDesactivar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDesactivar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesactivar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnDesactivar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 278, -1, -1));

        btnSalir1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Salir.png"))); // NOI18N
        btnSalir1.setText("Salir");
        btnSalir1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 20, -1, -1));

        btnRegresar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRegresar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Regresar.png"))); // NOI18N
        btnRegresar1.setText("Regresar");
        btnRegresar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 600, -1, -1));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Dirección:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 103, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Empleados");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 17, -1, 35));

        cmbCargoEmpleado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCargoEmpleadoItemStateChanged(evt);
            }
        });
        jPanel1.add(cmbCargoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, 113, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Cargo:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, 80, -1));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Sueldo:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 80, -1));

        txtSueldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSueldoKeyTyped(evt);
            }
        });
        jPanel1.add(txtSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 130, 113, -1));

        btnBuscar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/buscar.png"))); // NOI18N
        btnBuscar1.setText("Buscar");
        btnBuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, -1, 29));

        jScrollPane8.setBackground(new java.awt.Color(102, 204, 0));

        tblEmpleados.setBackground(new java.awt.Color(102, 204, 0));
        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellidos", "Teléfono", "Dirección", "Correo electrónico", "Fecha Registro", "Tipo Documento", "Documento", "Cargo", "Sueldo", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmpleados.setGridColor(new java.awt.Color(0, 0, 0));
        tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblEmpleados);
        if (tblEmpleados.getColumnModel().getColumnCount() > 0) {
            tblEmpleados.getColumnModel().getColumn(0).setPreferredWidth(25);
            tblEmpleados.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblEmpleados.getColumnModel().getColumn(6).setPreferredWidth(50);
            tblEmpleados.getColumnModel().getColumn(11).setPreferredWidth(20);
        }

        jPanel1.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 344, 1346, 203));

        btnGenerar9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGenerar9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel (1).png"))); // NOI18N
        btnGenerar9.setText("Generar EXCEL");
        btnGenerar9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerar9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerar9ActionPerformed(evt);
            }
        });
        jPanel1.add(btnGenerar9, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 581, -1, -1));

        btnGenerar10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGenerar10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf (1).png"))); // NOI18N
        btnGenerar10.setText("Generar PDF");
        btnGenerar10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerar10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerar10ActionPerformed(evt);
            }
        });
        jPanel1.add(btnGenerar10, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 581, -1, -1));

        labelempleado.setText("jLabel1");
        jPanel1.add(labelempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 779, -1, -1));

        Tab_Empleado.addTab("Empleados", jPanel1);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRegresar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Regresar.png"))); // NOI18N
        btnRegresar2.setText("Regresar");
        btnRegresar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegresar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar2ActionPerformed(evt);
            }
        });
        jPanel3.add(btnRegresar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 610, -1, -1));

        btnSalir2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Salir.png"))); // NOI18N
        btnSalir2.setText("Salir");
        btnSalir2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir2ActionPerformed(evt);
            }
        });
        jPanel3.add(btnSalir2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 10, -1, -1));

        jScrollPane5.setBackground(new java.awt.Color(102, 204, 0));

        tblNuevoCargo.setBackground(new java.awt.Color(102, 204, 0));
        tblNuevoCargo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Cargo", "Nuevo Cargo", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNuevoCargo.setGridColor(new java.awt.Color(0, 0, 0));
        tblNuevoCargo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNuevoCargoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblNuevoCargoMouseEntered(evt);
            }
        });
        jScrollPane5.setViewportView(tblNuevoCargo);

        jPanel3.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 289, 904, 203));

        btnAgregar2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/agregar.png"))); // NOI18N
        btnAgregar2.setText("Agregar");
        btnAgregar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar2ActionPerformed(evt);
            }
        });
        jPanel3.add(btnAgregar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 205, -1, -1));

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Nuevo Cargo:");
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 151, 104, 23));

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("ID del Cargo:");
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 110, 104, 23));

        cmbIDNuevoCargo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbIDNuevoCargoItemStateChanged(evt);
            }
        });
        jPanel3.add(cmbIDNuevoCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 110, 102, -1));

        txtNuevoCargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoCargoKeyTyped(evt);
            }
        });
        jPanel3.add(txtNuevoCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 151, 212, -1));

        btnModificar2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModificar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/modificar.png"))); // NOI18N
        btnModificar2.setText("Modificar");
        btnModificar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar2ActionPerformed(evt);
            }
        });
        jPanel3.add(btnModificar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 205, -1, -1));

        btnLimpiar2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLimpiar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Limpiar.png"))); // NOI18N
        btnLimpiar2.setText("Limpiar");
        btnLimpiar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiar2ActionPerformed(evt);
            }
        });
        jPanel3.add(btnLimpiar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 205, -1, 41));

        btnDesactivar2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDesactivar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Desactivar.png"))); // NOI18N
        btnDesactivar2.setText("Desactivar");
        btnDesactivar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDesactivar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesactivar2ActionPerformed(evt);
            }
        });
        jPanel3.add(btnDesactivar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 206, -1, -1));

        jLabel29.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Ingresar Nuevo Cargo");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, -1, 35));

        btnGenerar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf (1).png"))); // NOI18N
        btnGenerar.setText("Generar PDF");
        btnGenerar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });
        jPanel3.add(btnGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 510, -1, -1));

        btnGenerar2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGenerar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel (1).png"))); // NOI18N
        btnGenerar2.setText("Generar EXCEL");
        btnGenerar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerar2ActionPerformed(evt);
            }
        });
        jPanel3.add(btnGenerar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 510, -1, -1));

        Tab_Empleado.addTab("Cargos", jPanel3);

        jPanel4.setBackground(new java.awt.Color(0, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Usuarios");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, -1, 35));

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Empleado:");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 119, 134, -1));

        txtIDUsuario.setEditable(false);
        txtIDUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIDUsuarioFocusLost(evt);
            }
        });
        jPanel4.add(txtIDUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 74, 97, -1));

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("ID Usuario: ");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 74, 134, -1));

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Nombre de Usuario:");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 161, 134, -1));

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel4.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 158, 270, -1));

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Contraseña:");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, 131, -1));

        Tbl_Usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Usuario", "Empleado", "Nombre de usuario", "Contraseña", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tbl_Usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tbl_UsuariosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Tbl_Usuarios);
        if (Tbl_Usuarios.getColumnModel().getColumnCount() > 0) {
            Tbl_Usuarios.getColumnModel().getColumn(0).setPreferredWidth(20);
            Tbl_Usuarios.getColumnModel().getColumn(1).setPreferredWidth(140);
            Tbl_Usuarios.getColumnModel().getColumn(3).setPreferredWidth(130);
            Tbl_Usuarios.getColumnModel().getColumn(4).setPreferredWidth(20);
        }

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 313, 1068, 187));

        cmbIDEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbIDEmpleadoActionPerformed(evt);
            }
        });
        jPanel4.add(cmbIDEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 116, 270, -1));

        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel36.setText("Confirmar contraseña:");
        jPanel4.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 131, -1));

        btnSalir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel4.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 10, -1, -1));

        btnRegresar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Regresar.png"))); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel4.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 600, -1, -1));

        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/agregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel4.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 222, -1, -1));

        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/modificar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel4.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 222, -1, -1));

        btnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel4.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 222, -1, 41));

        btnDesactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDesactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Desactivar.png"))); // NOI18N
        btnDesactivar.setText("Desactivar");
        btnDesactivar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDesactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesactivarActionPerformed(evt);
            }
        });
        jPanel4.add(btnDesactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 223, -1, -1));

        txtContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContraseñaKeyTyped(evt);
            }
        });
        jPanel4.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, 150, -1));

        txtConfirmarContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConfirmarContraseñaKeyTyped(evt);
            }
        });
        jPanel4.add(txtConfirmarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, 150, -1));

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel4.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, -1, 29));

        btnBuscar2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/buscar.png"))); // NOI18N
        btnBuscar2.setText("Buscar");
        btnBuscar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar2ActionPerformed(evt);
            }
        });
        jPanel4.add(btnBuscar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, -1, 29));

        btnGenerar3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGenerar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel (1).png"))); // NOI18N
        btnGenerar3.setText("Generar EXCEL");
        btnGenerar3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerar3ActionPerformed(evt);
            }
        });
        jPanel4.add(btnGenerar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 518, -1, -1));

        btnGenerar4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGenerar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf (1).png"))); // NOI18N
        btnGenerar4.setText("Generar PDF");
        btnGenerar4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerar4ActionPerformed(evt);
            }
        });
        jPanel4.add(btnGenerar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 518, -1, -1));

        Tab_Empleado.addTab("Usuarios", jPanel4);

        jPanel2.setLayout(null);

        tblHistorialSueldo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Sueldo Histórico", "Empleado", "Sueldo", "Desde", "Hasta", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblHistorialSueldo);
        if (tblHistorialSueldo.getColumnModel().getColumnCount() > 0) {
            tblHistorialSueldo.getColumnModel().getColumn(1).setPreferredWidth(160);
            tblHistorialSueldo.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblHistorialSueldo.getColumnModel().getColumn(4).setPreferredWidth(50);
            tblHistorialSueldo.getColumnModel().getColumn(5).setPreferredWidth(20);
        }

        jPanel2.add(jScrollPane6);
        jScrollPane6.setBounds(52, 191, 1069, 187);

        btnRegresar3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRegresar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Regresar.png"))); // NOI18N
        btnRegresar3.setText("Regresar");
        btnRegresar3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegresar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar3ActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegresar3);
        btnRegresar3.setBounds(1150, 580, 160, 45);

        btnSalir3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Salir.png"))); // NOI18N
        btnSalir3.setText("Salir");
        btnSalir3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir3ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir3);
        btnSalir3.setBounds(1230, 10, 100, 41);

        jLabel16.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Historial de Sueldos del Empleado");
        jPanel2.add(jLabel16);
        jLabel16.setBounds(500, 10, 396, 35);

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Empleado:");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(52, 127, 83, 14);

        txtEmpleadoSueldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpleadoSueldoActionPerformed(evt);
            }
        });
        txtEmpleadoSueldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmpleadoSueldoKeyTyped(evt);
            }
        });
        jPanel2.add(txtEmpleadoSueldo);
        txtEmpleadoSueldo.setBounds(145, 124, 113, 20);

        btnGenerar5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGenerar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel (1).png"))); // NOI18N
        btnGenerar5.setText("Generar EXCEL");
        btnGenerar5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerar5ActionPerformed(evt);
            }
        });
        jPanel2.add(btnGenerar5);
        btnGenerar5.setBounds(201, 396, 149, 41);

        btnGenerar6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGenerar6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf (1).png"))); // NOI18N
        btnGenerar6.setText("Generar PDF");
        btnGenerar6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerar6ActionPerformed(evt);
            }
        });
        jPanel2.add(btnGenerar6);
        btnGenerar6.setBounds(52, 396, 139, 41);

        Tab_Empleado.addTab("Historial de Sueldos", jPanel2);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRegresar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Regresar.png"))); // NOI18N
        btnRegresar4.setText("Regresar");
        btnRegresar4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegresar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar4ActionPerformed(evt);
            }
        });
        jPanel5.add(btnRegresar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 590, -1, -1));

        btnSalirCargoHistorico.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalirCargoHistorico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Salir.png"))); // NOI18N
        btnSalirCargoHistorico.setText("Salir");
        btnSalirCargoHistorico.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalirCargoHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirCargoHistoricoActionPerformed(evt);
            }
        });
        jPanel5.add(btnSalirCargoHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 10, -1, -1));

        jTbHistorialCargo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Cargo Histórico", "Empleado", "Cargo", "Desde", "Hasta", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTbHistorialCargo);
        if (jTbHistorialCargo.getColumnModel().getColumnCount() > 0) {
            jTbHistorialCargo.getColumnModel().getColumn(1).setPreferredWidth(140);
            jTbHistorialCargo.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTbHistorialCargo.getColumnModel().getColumn(4).setPreferredWidth(50);
            jTbHistorialCargo.getColumnModel().getColumn(5).setPreferredWidth(20);
        }

        jPanel5.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 169, 1100, 187));

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Empleado:");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 106, 83, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Historial de Cargos del Empleado");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, 35));

        txtEmpleadoCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpleadoCargoActionPerformed(evt);
            }
        });
        txtEmpleadoCargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmpleadoCargoKeyTyped(evt);
            }
        });
        jPanel5.add(txtEmpleadoCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 103, 113, -1));

        btnGenerar7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGenerar7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel (1).png"))); // NOI18N
        btnGenerar7.setText("Generar EXCEL");
        btnGenerar7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerar7ActionPerformed(evt);
            }
        });
        jPanel5.add(btnGenerar7, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 374, -1, -1));

        btnGenerar8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGenerar8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf (1).png"))); // NOI18N
        btnGenerar8.setText("Generar PDF");
        btnGenerar8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerar8ActionPerformed(evt);
            }
        });
        jPanel5.add(btnGenerar8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 374, -1, -1));

        Tab_Empleado.addTab("Historial de Cargos", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tab_Empleado)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tab_Empleado, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Tab_EmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tab_EmpleadoMouseClicked
        // TODO add your handling code here:
        /*if(Tab_Empleado.getSelectedIndex()==1)
        {
            DefaultTableModel modelo = new DefaultTableModel();
            Tbl_Usuarios.setModel(modelo);
            modelo.addColumn("Numero de Identidad");
            modelo.addColumn("Nombre");
            modelo.addColumn("Telefono");
            modelo.addColumn("Direccion");

            List<Usuarios> usuario = UsuariosDao.findUsuariosEntities();

            for(Usuarios usu : usuario){
                modelo.addRow(
                    new Object[]{
                        usu.getId_Usuario(),
                        usu.getId_Empleado(),
                        usu.getId_Nombre(),
                        usu.getContraseña(),
                        usu.getContraseña()

                    }
                );
            }
        }else if (Tab_Empleado.getSelectedIndex()== 0)
        txtNombre.requestFocus();
        */
    }//GEN-LAST:event_Tab_EmpleadoMouseClicked

    private void txtEmpleadoCargoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpleadoCargoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleadoCargoKeyTyped

    private void txtEmpleadoCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpleadoCargoActionPerformed
        filtrarCargoHistorico();
    }//GEN-LAST:event_txtEmpleadoCargoActionPerformed
private void filtrarCargoHistorico(){
    txtEmpleadoCargo.addKeyListener(new KeyAdapter() {
            public void keyReleased(final java.awt.event.KeyEvent e) {
                repaint();
                filtroCargoHistorico();
            }
        });
}
    private void btnSalirCargoHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirCargoHistoricoActionPerformed
       System.exit(0);
    }//GEN-LAST:event_btnSalirCargoHistoricoActionPerformed

    private void btnRegresar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar4ActionPerformed
         //FrmMenu m = new FrmMenu();
        //m.setVisible(true);
        RegresarCargoHistorico();
        
    }//GEN-LAST:event_btnRegresar4ActionPerformed
public void RegresarCargoHistorico(){
    this.setVisible(false);
}
    private void txtEmpleadoSueldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpleadoSueldoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleadoSueldoKeyTyped

    private void txtEmpleadoSueldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpleadoSueldoActionPerformed
        filtrarSueldoHistorico();
    }//GEN-LAST:event_txtEmpleadoSueldoActionPerformed
private void filtrarSueldoHistorico(){
    txtEmpleadoSueldo.addKeyListener(new KeyAdapter() {
            public void keyReleased(final java.awt.event.KeyEvent e) {
                repaint();
                filtroSueldoHistorico();
            }
        });
}
    private void btnSalir3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir3ActionPerformed
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalir3ActionPerformed

    private void btnRegresar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar3ActionPerformed
       // FrmMenu m = new FrmMenu();
       // m.setVisible(true);
        this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresar3ActionPerformed

    private void btnBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar2ActionPerformed
        try{
        BuscarUsuario();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"UsuarioBuscar"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnBuscar2ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try{
        BuscarEmpleadoUsuario();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"UsuarioBuscarEmpleado"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivarActionPerformed
        try{
        DesactivarUsuario();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"UsuarioDesactivar"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
// TODO add your handling code here:
    }//GEN-LAST:event_btnDesactivarActionPerformed
public boolean DesactivarUsuario()throws Exception{
    Usuarios temp;
        temp = UsuariosDao.findUsuarios(Integer.parseInt(txtIDUsuario.getText()));
        //temp = piezaDao.findPieza(txtIDPieza.getSelectedIndex()+1);
        //JOptionPane.showMessageDialog(null, temp.isEstado());
        if(temp.isEstado()){
            temp.setEstado(false);
            Icon icono = new ImageIcon(getClass().getResource("/Img/Desactivar.png"));
            JOptionPane.showMessageDialog(null,"Usuario Desactivado exitosamente","Guardado",JOptionPane.PLAIN_MESSAGE, icono);
        }
        else{
            temp.setEstado(true);
            temp.setIntentos(0);
            Icon icono = new ImageIcon(getClass().getResource("/Img/Activar.png"));
            JOptionPane.showMessageDialog(null,"Usuario Activado exitosamente","Guardado",JOptionPane.PLAIN_MESSAGE, icono);
        }
        
            UsuariosDao.edit(temp);
        
        createTableUsuario();
        btnActivarDesactivarUsuario();
        limpiar();
        btnDesactivar.setEnabled(false);
        btnAgregar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnBuscar.setEnabled(true);
        btnBuscar2.setEnabled(true);
        return true;
}
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        try{
        limpiar();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"UsuarioLimpiar"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try{
        ModificarUsuario();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"UsuarioModificar"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed
public boolean ModificarUsuario()throws Exception{
    boolean bandera = false;
        Usuarios usu = new  Usuarios();
        List<Usuarios> temporal = UsuariosDao.findUsuariosEntities();
        for(Usuarios sus : temporal){
            if(sus.getId_Nombre().equals(txtNombre.getText()))
            {
                bandera=true;
            }
        }
        String Contrasenia = txtContraseña.getText();
        String ConfirmarContrasenia = txtConfirmarContraseña.getText();
        if(cmbIDEmpleado.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"No ha seleccionado ningún Empleado","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        Empleado tempopp;
        tempopp = Empleadodao.findEmpleado(cmbIDEmpleado.getSelectedIndex());
        if(tempopp.isEstado()!=true){
            JOptionPane.showMessageDialog(null,"El Empleado seleccionado esta Inactivo","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if("".equals(txtNombre.getText())){
            JOptionPane.showMessageDialog(null, "El campo del nombre del usuario esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if(txtNombre.getText().length()<5){
            JOptionPane.showMessageDialog(null, "La cantidad mínima de caracteres para el usuario del empleado es de 5 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if(txtNombre.getText().length()>25){
            JOptionPane.showMessageDialog(null, "La cantidad máxima de caracteres para el usuario del empleado es de 25 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        Pattern pat = Pattern.compile("(.* .*|.*:.*)");
        Matcher mat = pat.matcher(txtNombre.getText());

        if(mat.matches()){
            JOptionPane.showMessageDialog(null, "No puede haber espacios en el nombre del usuario","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!ValidacionNombreUsuario(txtNombre.getText())){
            JOptionPane.showMessageDialog(null,"Formato de Nombre de usuario invalido!\nDebe contener letras Minúsculas, un punto, letras Minúsculas y un número\nEjemplo: agencia.carsoft1","Error!", JOptionPane.ERROR_MESSAGE);
            txtContraseña.requestFocus();
            return false;
        }else{
        }
        if("".equals(Contrasenia)){
            JOptionPane.showMessageDialog(null, "El campo de Contraseña esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if(Contrasenia.length()<8){
            JOptionPane.showMessageDialog(null, "La cantidad mínima de caracteres para la Contraseña es de 8 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if(Contrasenia.length()>15){
            JOptionPane.showMessageDialog(null, "La cantidad máxima de caracteres para la Contraseña es de 15 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if (!ValidacionContrasenia2(Contrasenia)){
            JOptionPane.showMessageDialog(null,"Formato de Contraseña invalido!\nDebe contener la primera letra Mayúscula, letras Minúsculas, un Número y un caracter","Error!", JOptionPane.ERROR_MESSAGE);
            txtContraseña.requestFocus();
            return false;
        }else{
        }
        if("".equals(ConfirmarContrasenia)){
            JOptionPane.showMessageDialog(null, "El campo de Confirmar Contraseña esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if(ConfirmarContrasenia.length()<8){
            JOptionPane.showMessageDialog(null, "La cantidad mínima de caracteres para el campo Confirmar la Contraseña es de 8 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if(ConfirmarContrasenia.length()>15){
            JOptionPane.showMessageDialog(null, "La cantidad máxima de caracteres para el campo Confirmar Contraseña es de 15 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if (Contrasenia.equals(ConfirmarContrasenia))
        {
            String NuevaContraseña = Hash.getHash(Contrasenia);

            usu.setId_Usuario(Integer.parseInt(txtIDUsuario.getText()));
            usu.setId_Empleado(cmbIDEmpleado.getSelectedIndex());
            usu.setId_Nombre(txtNombre.getText());
            usu.setContraseña(NuevaContraseña); //CAMBIO DE TXT A NuevaContraseña

            usu.setEstado(UsuariosDao.findUsuarios(Integer.parseInt(txtIDUsuario.getText())).isEstado()); //NUEVO
            //usu.setContraseña(txtConfirmarContrasenia.getText());

        } else {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden","Error!",JOptionPane.ERROR_MESSAGE);
            return false;

        }

        
            UsuariosDao.edit(usu);
        
        Icon icono = new ImageIcon(getClass().getResource("/Img/modificar.png"));
        JOptionPane.showMessageDialog(null,"Datos Modificados exitosamente","Modificado",JOptionPane.PLAIN_MESSAGE, icono);
        //txtNombre.setEditable(true);
        createTableUsuario();
        limpiar();//NUEVO
        btnAgregar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnDesactivar.setEnabled(true);
        return true;
}
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try{
        AgregarUsuario();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"UsuarioAgregar"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed
public boolean AgregarUsuario()throws Exception{
    // TODO add your handling code here:
        boolean bandera = false;
        Usuarios usu = new  Usuarios();
        List<Usuarios> temporal = UsuariosDao.findUsuariosEntities();
        for(Usuarios sus : temporal){
            if(sus.getId_Nombre().equals(txtNombre.getText()))
            {
                bandera=true;
            }
        }
        String Contrasenia = txtContraseña.getText();
        String ConfirmarContrasenia = txtConfirmarContraseña.getText();
        if(cmbIDEmpleado.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"No ha seleccionado ningún Empleado","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        Empleado tempopp;
        tempopp = Empleadodao.findEmpleado(cmbIDEmpleado.getSelectedIndex());
        if(tempopp.isEstado()!=true){
            JOptionPane.showMessageDialog(null,"El Empleado seleccionado esta Inactivo","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if("".equals(txtNombre.getText())){
            JOptionPane.showMessageDialog(null, "El campo del nombre del usuario esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if(txtNombre.getText().length()<5){
            JOptionPane.showMessageDialog(null, "La cantidad mínima de caracteres para el usuario del empleado es de 5 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if(txtNombre.getText().length()>25){
            JOptionPane.showMessageDialog(null, "La cantidad máxima de caracteres para el usuario del empleado es de 25 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        Pattern pat = Pattern.compile("(.* .*|.*:.*)");
        Matcher mat = pat.matcher(txtNombre.getText());

        if(mat.matches()){
            JOptionPane.showMessageDialog(null, "No puede haber espacios en el nombre del usuario","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!ValidacionNombreUsuario(txtNombre.getText())){
            JOptionPane.showMessageDialog(null,"Formato de Nombre de usuario invalido!\nDebe contener letras Minúsculas, un punto, letras Minúsculas y un número\nEjemplo: agencia.carsoft1","Error!", JOptionPane.ERROR_MESSAGE);
            txtContraseña.requestFocus();
            return false;
        }else{
        }
        if(bandera){
            JOptionPane.showMessageDialog(null, "Nombre de usuario no disponible \n Ingrese otro nombre de usuario","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if("".equals(Contrasenia)){
            JOptionPane.showMessageDialog(null, "El campo de Contraseña esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if(Contrasenia.length()<8){
            JOptionPane.showMessageDialog(null, "La cantidad mínima de caracteres para la Contraseña es de 8 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if(Contrasenia.length()>15){
            JOptionPane.showMessageDialog(null, "La cantidad máxima de caracteres para la Contraseña es de 15 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if (!ValidacionContrasenia2(Contrasenia)){
            JOptionPane.showMessageDialog(null,"Formato de Contraseña invalido!\nDebe contener mínimo una letra Mayúscula,mínimo una letra Minúsculas, un Número y un caracter Especial","Error!", JOptionPane.ERROR_MESSAGE);
            txtContraseña.requestFocus();
            return false;
        }else{
        }
        if("".equals(ConfirmarContrasenia)){
            JOptionPane.showMessageDialog(null, "El campo de Confirmar Contraseña esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
           return false;
        }
        else{

        }
        if(ConfirmarContrasenia.length()<8){
            JOptionPane.showMessageDialog(null, "La cantidad mínima de caracteres para el campo Confirmar la Contraseña es de 8 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if(ConfirmarContrasenia.length()>15){
            JOptionPane.showMessageDialog(null, "La cantidad máxima de caracteres para el campo Confirmar Contraseña es de 15 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        int i;
                boolean luces=false;
                for(i=0;i<UsuariosDao.findUsuariosEntities().size();i++){
                Empleado temp2 = new Empleado();
                Persona temp3 = new Persona();
                temp2=Empleadodao.findEmpleado(UsuariosDao.findUsuarios(i+1).getId_Empleado());
                temp3=Personadao.findPersona(temp2.getId_Persona());
                int U1 = UsuariosDao.findUsuarios(i+1).getId_Empleado();
                String U2 = temp3.getNombre();
                String U3 = temp3.getApellido();
                String U4 = ((String.valueOf(U1))+". "+U2+" "+U3);
                if(cmbIDEmpleado.getSelectedItem().equals(U4)){
                    JOptionPane.showMessageDialog(null, "Este empleado ya tiene un Usuario registrado en el sistema","Error!", JOptionPane.ERROR_MESSAGE);
                    luces=true;
                    return false;
                } else {
                }
                }
                if(luces){
                    return false;
                }
        if (Contrasenia.equals(ConfirmarContrasenia))
        {
            String NuevaContraseña = Hash.getHash(Contrasenia);
            usu.setId_Usuario(UsuariosDao.getUsuariosCount()+1);
            usu.setId_Empleado(cmbIDEmpleado.getSelectedIndex());
            usu.setId_Nombre(txtNombre.getText());
            usu.setContraseña(NuevaContraseña);

            usu.setEstado(true);
           
                UsuariosDao.create(usu);
                Acceso access  = new Acceso();
                access.setIdUsuario(UsuariosDao.getUsuariosCount());
                access.setPermisoMenuBancos(true);
                access.setPermisoMenuClientes(true);
                
                access.setPermisoMenuClientes(true);
access.setPermisoMenuEmpleados(true);
access.setPermisoMenuVentas(true);
access.setPermisoMenuPedidos(true);
access.setPermisoMenuPiezas(true);
access.setPermisoMenuVehiculos(true);
access.setPermisoMenuBancos(true);
access.setPermisoSeccionEmpleadosAgregar(true);
access.setPermisoSeccionEmpleadosModificar(true);
access.setPermisoSeccionEmpleadosDesactivar(true);
access.setPermisoSeccionEmpleadosBuscar(true);
access.setPermisoSeccionEmpleadosReportePdf(true);
access.setPermisoSeccionEmpleadosReporteExcel(true);
access.setPermisoSeccionCargosAgregar(true);
access.setPermisoSeccionCargosModificar(true);
access.setPermisoSeccionCargosDesactivar(true);
access.setPermisoSeccionCargosReportePdf(true);
access.setPermisoSeccionCargosReporteExcel(true);
access.setPermisoSeccionUsuariosAgregar(true);
access.setPermisoSeccionUsuariosModificar(true);
access.setPermisoSeccionUsuariosDesactivar(true);
access.setPermisoSeccionUsuariosBuscarUsuario(true);
access.setPermisoSeccionUsuariosBuscarEmpleado(true);
access.setPermisoSeccionUsuariosReportePdf(true);
access.setPermisoSeccionUsuariosReporteExcel(true);
access.setPermisoSeccionHistorialSueldosReportePdf(true);
access.setPermisoSeccionHistorialSueldosReporteExcel(true);
access.setPermisoSeccionHistorialCargosReportePdf(true);
access.setPermisoSeccionHistorialCargosReporteExcel(true);
access.setPermisoSeccionClientesAgregar(true);
access.setPermisoSeccionClientesModificar(true);
access.setPermisoSeccionClientesDesactivar(true);
access.setPermisoSeccionClientesBuscar(true);
access.setPermisoSeccionClientesReportePdf(true);
access.setPermisoSeccionClientesReporteExcel(true);
access.setPermisoSeccionTipoDocumentoAgregar(true);
access.setPermisoSeccionTipoDocumentoModificar(true);
access.setPermisoSeccionTipoDocumentoDesactivar(true);
access.setPermisoSeccionTipoDocumentoReportePdf(true);
access.setPermisoSeccionTipoDocumentoReporteExcel(true);
access.setPermisoSeccionBancosAgregar(true);
access.setPermisoSeccionBancosModificar(true);
access.setPermisoSeccionBancosDesactivar(true);
access.setPermisoSeccionBancosBuscar(true);
access.setPermisoSeccionBancosReportePdf(true);
access.setPermisoSeccionBancosReporteExcel(true);
access.setPermisoSeccionPrestamosAgregar(true);
access.setPermisoSeccionPrestamosModificar(true);
access.setPermisoSeccionPrestamosDesactivar(true);
access.setPermisoSeccionPrestamosBuscarBanco(true);
access.setPermisoSeccionPrestamosBuscarCliente(true);
access.setPermisoSeccionPrestamosReportePdf(true);
access.setPermisoSeccionPrestamosReporteExcel(true);
access.setPermisoSeccionPedidosAgregarPieza(true);
access.setPermisoSeccionPedidosAgregarVehiculo(true);
access.setPermisoSeccionPedidosBuscarPieza(true);
access.setPermisoSeccionPedidosBuscarVehiculo(true);
access.setPermisoSeccionPedidosAgregarPedido(true);
access.setPermisoSeccionListadoPedidosDesactivar(true);
access.setPermisoSeccionListadoPedidosReportePdf(true);
access.setPermisoSeccionListadoPedidosReporteExcel(true);
access.setPermisoSeccionPiezasAgregar(true);
access.setPermisoSeccionPiezasModificar(true);
access.setPermisoSeccionPiezasDesactivar(true);
access.setPermisoSeccionPiezasReportePdf(true);
access.setPermisoSeccionPiezasReporteExcel(true);
access.setPermisoSeccionHistorialPreciosPiezaBuscar(true);
access.setPermisoSeccionHistorialPreciosPiezaReportePdf(true);
access.setPermisoSeccionHistorialPreciosPiezaReporteExcel(true);
access.setPermisoSeccionTipoPiezaAgregar(true);
access.setPermisoSeccionTipoPiezaModificar(true);
access.setPermisoSeccionTipoPiezaDesactivar(true);
access.setPermisoSeccionTipoPiezaReportePdf(true);
access.setPermisoSeccionTipoPiezaReporteExcel(true);
access.setPermisoSeccionBuscarPiezaBuscar(true);
access.setPermisoSeccionBuscarPiezaReportePdf(true);
access.setPermisoSeccionBuscarPiezaReporteExcel(true);
access.setPermisoSeccionVehiculosAgregar(true);
access.setPermisoSeccionVehiculosModificar(true);
access.setPermisoSeccionVehiculosDesactivar(true);
access.setPermisoSeccionVehiculosReportePdf(true);
access.setPermisoSeccionVehiculosReporteExcel(true);
access.setPermisoSeccionHistorialPreciosVehiculoBuscar(true);
access.setPermisoSeccionHistorialPreciosVehiculoReportePdf(true);
access.setPermisoSeccionHistorialPreciosVehiculoReporteExcel(true);
access.setPermisoSeccionMarcaAgregar(true);
access.setPermisoSeccionMarcaModificar(true);
access.setPermisoSeccionMarcaDesactivar(true);
access.setPermisoSeccionMarcaReportePdf(true);
access.setPermisoSeccionMarcaReporteExcel(true);
access.setPermisoSeccionColorAgregar(true);
access.setPermisoSeccionColorModificar(true);
access.setPermisoSeccionColorDesactivar(true);
access.setPermisoSeccionColorReportePdf(true);
access.setPermisoSeccionColorReporteExcel(true); 
access.setPermisoSeccionTipoVehiculoAgregar(true);
access.setPermisoSeccionTipoVehiculoModificar(true);
access.setPermisoSeccionTipoVehiculoDesactivar(true);
access.setPermisoSeccionTipoVehiculoReportePdf(true);
access.setPermisoSeccionTipoVehiculoReporteExcel(true);
access.setPermisoSeccionTipoCabinaAgregar(true);
access.setPermisoSeccionTipoCabinaModificar(true);
access.setPermisoSeccionTipoCabinaDesactivar(true);
access.setPermisoSeccionTipoCabinaReportePdf(true);
access.setPermisoSeccionTipoCabinaReporteExcel(true);
access.setPermisoSeccionTipoCombustibleAgregar(true);
access.setPermisoSeccionTipoCombustibleModificar(true);
access.setPermisoSeccionTipoCombustibleDesactivar(true);
access.setPermisoSeccionTipoCombustibleReportePdf(true);
access.setPermisoSeccionTipoCombustibleReporteExcel(true);
access.setPermisoSeccionNumeroAsientosAgregar(true);
access.setPermisoSeccionNumeroAsientosModificar(true);
access.setPermisoSeccionNumeroAsientosDesactivar(true);
access.setPermisoSeccionNumeroAsientosReportePdf(true);
access.setPermisoSeccionNumeroAsientosReporteExcel(true);
access.setPermisoSeccionTipoTransmisionAgregar(true);
access.setPermisoSeccionTipoTransmisionModificar(true);
access.setPermisoSeccionTipoTransmisionDesactivar(true);
access.setPermisoSeccionTipoTransmisionReportePdf(true);
access.setPermisoSeccionTipoTransmisionReporteExcel(true);
access.setPermisoSeccionBuscarVehiculoBuscar(true);
access.setPermisoSeccionBuscarVehiculoReportePdf(true);
access.setPermisoSeccionBuscarVehiculoExcel(true);
access.setPermisoSeccionReporteReportePdfTabla(true);
access.setPermisoSeccionReporteBuscar(true);
access.setPermisoSeccionVentasAgregarPieza(true);
access.setPermisoSeccionVentasAgregarVehiculo(true);
access.setPermisoSeccionVentasBuscarPieza(true);
access.setPermisoSeccionVentasBuscarVehiculo(true);
access.setPermisoSeccionVentasGenerarVenta(true);
access.setPermisoSeccionTipoFacturaAgregar(true);
access.setPermisoSeccionTipoFacturaModificar(true);
access.setPermisoSeccionTipoFacturaDesactivar(true);
access.setPermisoSeccionTipoFacturaReportePdf(true);
access.setPermisoSeccionTipoFacturaReporteExcel(true);
access.setPermisoSeccionCaiAgregar(true);
access.setPermisoSeccionCaiModificar(true);
access.setPermisoSeccionCaiDesactivar(true);
access.setPermisoSeccionCaiReportePdf(true);
access.setPermisoSeccionCaiReporteExcel(true);
access.setPermisoSeccionTipoPagoAgregar(true);
access.setPermisoSeccionTipoPagoModificar(true);
access.setPermisoSeccionTipoPagoDesactivar(true);
access.setPermisoSeccionTipoPagoReportePdf(true);
access.setPermisoSeccionTipoPagoReporteExcel(true);
access.setPermisoSeccionFacturasRechazadasBuscar(true);
access.setPermisoSeccionFacturasRechazadasRechazar(true);
accesoDao.create(access);
            //NUEVO
            Icon icono = new ImageIcon(getClass().getResource("/Img/agregar.png"));
            JOptionPane.showMessageDialog(null,"Datos Guardados exitosamente","Guardado",JOptionPane.PLAIN_MESSAGE,icono);
            createTableUsuario();
            limpiar();
            btnAgregar.setEnabled(true);
            btnModificar.setEnabled(false);
            //FIN NUEVO

        } else {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden","Error!", JOptionPane.ERROR_MESSAGE);
            return false;

        }
        return true;
}
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
       // FrmMenu m = new FrmMenu();
        //m.setVisible(true);
        RegresarUsuario();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresarActionPerformed
public void RegresarUsuario(){
    this.setVisible(false);
}
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cmbIDEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbIDEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbIDEmpleadoActionPerformed

    private void Tbl_UsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tbl_UsuariosMouseClicked
        TablaUsuariosMouseClicked();
    }//GEN-LAST:event_Tbl_UsuariosMouseClicked
public void TablaUsuariosMouseClicked(){
    btnAgregar.setEnabled(false);
        btnModificar.setEnabled(true);
        btnBuscar.setEnabled(false);
        btnBuscar2.setEnabled(false);
        int fila = Tbl_Usuarios.getSelectedRow();
        if (fila > -1){
            Usuarios temp = UsuariosDao.findUsuarios(Integer.parseInt(Tbl_Usuarios.getModel().getValueAt(fila, 0).toString()));
            List<Empleado> tempc = Empleadodao.findEmpleadoEntities();
            List<Persona> tempp = Personadao.findPersonaEntities();
            for (Empleado cc : tempc) {
                if(temp.getId_Empleado()==cc.getId_Empleado()){
                    for (Persona pp : tempp) {
                        if(pp.getId_persona()==cc.getId_Persona()){
                            txtIDUsuario.setText(String.valueOf(temp.getId_Usuario()));
                            cmbIDEmpleado.setSelectedItem(cc.getId_Empleado()+". "+pp.getNombre()+" "+pp.getApellido());
                            txtNombre.setText((temp.getId_Nombre()));
                        } else {

                        }
                    }
                }

                
            }
            btnActivarDesactivarUsuario();
        }
}
    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtIDUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIDUsuarioFocusLost
        // TODO add your handling code here:
        /* Usuarios usu = new Usuarios();
        usu = UsuariosDao.findUsuarios(Integer.parseInt(txtIDUsuario.getText()));
        if (usu==null){
            usu.setId_Empleado(Integer.parseInt(cmbIDEmpleado.getSelectedItem().toString()));
            cmbIDEmpleado.setSelectedItem("");
            txtNombre.setText("");
            txtContraseña.setText("");
            txtConfirmarContraseña.setText("");

            btnAgregar.setEnabled(true);
            btnBuscar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnLimpiar.setEnabled(false);

        }else{
            usu.setId_Usuario(Integer.parseInt(txtIDUsuario.getText()));
            usu.setId_Usuario(Integer.parseInt(txtIDUsuario.getText()));
            txtIDUsuario.setText(String.valueOf(usu.getId_Usuario()));
            cmbIDEmpleado.setSelectedItem(usu.getId_Empleado());
            txtNombre.setText(usu.getId_Nombre());
            txtContraseña.setText(usu.getContraseña());
            txtConfirmarContraseña.setText(usu.getContraseña());

            btnAgregar.setEnabled(false);
            btnBuscar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnLimpiar.setEnabled(true);
        }*/

    }//GEN-LAST:event_txtIDUsuarioFocusLost

    private void btnDesactivar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivar2ActionPerformed
        try{
        DesactivarCargo();}
        catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"CargoDesactivar"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDesactivar2ActionPerformed
public boolean DesactivarCargo()throws Exception{
    Cargo_empleado temp;
        temp = CargoDao.findCargo_empleado(cmbIDNuevoCargo.getSelectedIndex());
        if(temp.isEstado()){
            temp.setEstado(false);
            Icon icono = new ImageIcon(getClass().getResource("/Img/Desactivar.png"));
            JOptionPane.showMessageDialog(null,"Tipo de Cargo Desactivado exitosamente","Guardado",JOptionPane.PLAIN_MESSAGE, icono);
        }
        else{
            temp.setEstado(true);
            Icon icono = new ImageIcon(getClass().getResource("/Img/Activar.png"));
            JOptionPane.showMessageDialog(null,"Tipo de Cargo Activado exitosamente","Guardado",JOptionPane.PLAIN_MESSAGE, icono);
        }
       
            CargoDao.edit(temp);
        
        createTableNuevoCargo();
        btnActivarDesactivarCargo();
        createComboCargoEmpleado();
        LimpiarNuevoCargo();
        btnDesactivar2.setEnabled(false);
        btnAgregar2.setEnabled(true);
        btnModificar2.setEnabled(false);
        return true;
}
    private void btnLimpiar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar2ActionPerformed
        try{
        LimpiarNuevoCargo();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"CargoLimpiar"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnLimpiar2ActionPerformed

    private void btnModificar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar2ActionPerformed
        try{
        ModificarCargo();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"CargoModificar"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnModificar2ActionPerformed
public boolean ModificarCargo()throws Exception{
    if(cmbIDNuevoCargo.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Cargo no encontrado");
            return false;
        }
        else{
            if("".equals(txtNuevoCargo.getText().trim())){
                JOptionPane.showMessageDialog(null,"Ingrese la cantidad necesaria de caracteres para el Cargo","Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else{

            }
            if(txtNuevoCargo.getText().length()<3){
                JOptionPane.showMessageDialog(null, "La cantidad mínima de caracteres para el cargo es de 3 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else{

            }
            if(txtNuevoCargo.getText().length()>25){
                JOptionPane.showMessageDialog(null, "La cantidad máxima de caracteres para el cargo es de 25 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else{

            }

            if (!ValidacionNombreMayusculaYDemasMinus(txtNuevoCargo.getText())){
                JOptionPane.showMessageDialog(null,"El Cargo debe contener la primera letra mayúscula y luego minúsculas","Error!", JOptionPane.ERROR_MESSAGE);
                txtNuevoCargo.requestFocus();
                return false;
            }else{

            }
            if (ValidacionTresletras(txtNuevoCargo.getText())){
                JOptionPane.showMessageDialog(null,"No se Admite en el Cargo la misma letra 3 veces en forma consecutiva","Error!", JOptionPane.ERROR_MESSAGE);
                txtNuevoCargo.requestFocus();
                return false;
            }else{
            }
            if("".equals(txtNuevoCargo.getText())){
                JOptionPane.showMessageDialog(null, "Ingrese la cantidad de caracteres necesarios para el cargo");
                return false;
            }
            else{

                Cargo_empleado tp;
                tp=CargoDao.findCargo_empleado(cmbIDNuevoCargo.getSelectedIndex());
                tp.setCargo(txtNuevoCargo.getText());
                
                    CargoDao.edit(tp);
                
                Icon icono = new ImageIcon(getClass().getResource("/Img/modificar.png"));
                JOptionPane.showMessageDialog(null,"Datos Modificados exitosamente","Modificado",JOptionPane.PLAIN_MESSAGE, icono);
                cmbIDNuevoCargo.setSelectedIndex(1);
                cmbIDNuevoCargo.setSelectedIndex(0);
                createTableNuevoCargo();
                createComboBoxNuevoCargo();
                createComboCargoEmpleado();
                LimpiarNuevoCargo();
                btnAgregar2.setEnabled(true);
                btnModificar2.setEnabled(false);
                btnDesactivar2.setEnabled(false);

            }
        }
    return true;
}
    private void txtNuevoCargoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoCargoKeyTyped
        char c = evt.getKeyChar();
                if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z'&& c != 'Ñ' && c != 'ñ' && c != 'Á' && c != 'É' && c != 'Í' && c != 'Ó' && c != 'Ú' && c != 'á' && c != 'é' && c != 'í' && c != 'ó' && c != 'ú')&& (c!=KeyEvent.VK_SPACE) ){

            evt.consume();

        }

              
        if (txtNuevoCargo.getText().length() >= 25){
        
        evt.consume();
        
        }
    }//GEN-LAST:event_txtNuevoCargoKeyTyped

    private void cmbIDNuevoCargoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbIDNuevoCargoItemStateChanged
      /*  if(cmbIDNuevoCargo.getSelectedIndex()==0){
            txtNuevoCargo.setText("");
            btnDesactivar2.setEnabled(false);

        }
        else{
            Cargo_empleado tp;
            tp=CargoDao.findCargo_empleado(cmbIDNuevoCargo.getSelectedIndex());
            txtNuevoCargo.setText(tp.getCargo());
            btnActivarDesactivarCargo();
        }*/
    }//GEN-LAST:event_cmbIDNuevoCargoItemStateChanged

    private void btnAgregar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar2ActionPerformed
        try{
        AgregarCargo();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"CargoAgregar"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAgregar2ActionPerformed
public boolean AgregarCargo() throws Exception{
    if(cmbIDNuevoCargo.getSelectedIndex()!=0){
            JOptionPane.showMessageDialog(null, "El ID Tipo de documento siempre debe estar en el ITEM de Nuevo para agregar un nuevo Cargo","Error!", JOptionPane.ERROR_MESSAGE);
            cmbIDNuevoCargo.setSelectedIndex(0);
            return false;
        }
        else{

        }
        if("".equals(txtNuevoCargo.getText().trim())){
            JOptionPane.showMessageDialog(null,"Ingrese la cantidad necesaria de caracteres para el Cargo","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if(txtNuevoCargo.getText().length()<3){
            JOptionPane.showMessageDialog(null, "La cantidad mínima de caracteres para el cargo es de 3 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if(txtNuevoCargo.getText().length()>25){
            JOptionPane.showMessageDialog(null, "La cantidad máxima de caracteres para el cargo es de 25 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }

        if (!ValidacionNombreMayusculaYDemasMinus(txtNuevoCargo.getText())){
            JOptionPane.showMessageDialog(null,"El Cargo debe contener la primera letra mayúscula y luego minúsculas","Error!", JOptionPane.ERROR_MESSAGE);
            txtNuevoCargo.requestFocus();
            return false;
        }else{

        }
        if (ValidacionTresletras(txtNuevoCargo.getText())){
            JOptionPane.showMessageDialog(null,"No se Admite en el Cargo la misma letra 3 veces en forma consecutiva","Error!", JOptionPane.ERROR_MESSAGE);
            txtNuevoCargo.requestFocus();
            return false;
        }else{
        }
        if("".equals(txtNuevoCargo.getText())){
            JOptionPane.showMessageDialog(null,"Ingrese la cantidad necesaria de caracteres para el Cargo","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
            int i;
            boolean flag=false;
            for(i=0;i<CargoDao.findCargo_empleadoEntities().size();i++){
                if(txtNuevoCargo.getText().toLowerCase().equals(CargoDao.findCargo_empleado(i+1).getCargo().toLowerCase())){
                    JOptionPane.showMessageDialog(null, "Ya existe este cargo registrado en el sistema","Error!", JOptionPane.ERROR_MESSAGE);
                    flag=true;
                    return false;
                } else {
                }
            }
            if(flag){
                return false;
            }
            else{
                Cargo_empleado tp = new Cargo_empleado();
                tp.setEstado(true);
                tp.setId_cargo(CargoDao.getCargo_empleadoCount()+1);
                tp.setCargo(txtNuevoCargo.getText());
                
                    CargoDao.create(tp);
               
                Icon icono = new ImageIcon(getClass().getResource("/Img/agregar.png"));
                JOptionPane.showMessageDialog(null,"Datos Guardados exitosamente","Guardado",JOptionPane.PLAIN_MESSAGE, icono);
                cmbIDNuevoCargo.setSelectedIndex(0);
                createTableNuevoCargo();
                createComboBoxNuevoCargo();
                createComboCargoEmpleado();
                LimpiarNuevoCargo();
                btnAgregar2.setEnabled(true);
                btnModificar2.setEnabled(false);
            }
        }
        return true;
}
    public boolean ValidacionNombreMayuscula(String num){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^(?=.{3,40}$)[A-ZÑÁÉÍÓÚ][a-zñáéíóú]+(?: [A-ZÑÁÉÍÓÚ][a-zñáéíóú]+)?(?: [A-ZÑÁÉÍÓÚ][a-zñáéíóú]+)?(?: [A-ZÑÁÉÍÓÚ][a-zñáéíóú]+)?$");
        mat =pat.matcher(num);
        if (mat.find()){
            return true;
        } else{
        return false;
        }
    }

    private void tblNuevoCargoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNuevoCargoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblNuevoCargoMouseEntered

    private void tblNuevoCargoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNuevoCargoMouseClicked
        TablaCargoMouseClicked();
    }//GEN-LAST:event_tblNuevoCargoMouseClicked
public void TablaCargoMouseClicked(){
    btnAgregar2.setEnabled(false);
        btnModificar2.setEnabled(true);
        btnDesactivar2.setEnabled(true);
        int column=0;
        int fila = tblNuevoCargo.getSelectedRow();
        if (fila > -1){
            cmbIDNuevoCargo.setSelectedIndex(Integer.parseInt(tblNuevoCargo.getModel().getValueAt(fila, column).toString()));
            txtNuevoCargo.setText(String.valueOf(tblNuevoCargo.getValueAt(fila, ++column)));
            btnActivarDesactivarCargo();
        }
}
    private void btnSalir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalir2ActionPerformed

    private void btnRegresar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar2ActionPerformed
//        FrmMenu m = new FrmMenu();
//        m.setVisiFrmMenuble(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegresar2ActionPerformed

    private void tblEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadosMouseClicked
        TblEmpleados();
    }//GEN-LAST:event_tblEmpleadosMouseClicked

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        try{
        BuscarEmpleadoDocumento();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"EmpleadoBuscar"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void txtSueldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSueldoKeyTyped
        char c = evt.getKeyChar();
        if((c < '0' || c > '9') && (c != '.' && c != ',')){

            evt.consume();

        }
        if (txtSueldo.getText().length() >= 12){
        
        evt.consume();
        }
    }//GEN-LAST:event_txtSueldoKeyTyped

    private void cmbCargoEmpleadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCargoEmpleadoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCargoEmpleadoItemStateChanged

    private void btnRegresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar1ActionPerformed
//        FrmMenu m = new FrmMenu();
        //m.setVisible(true);
        RegresarEmpleado();
    }//GEN-LAST:event_btnRegresar1ActionPerformed
public void RegresarEmpleado(){
    this.setVisible(false);
}
    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed

        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void btnDesactivar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivar1ActionPerformed
        try{
        DesactivarEmpleado();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"EmpleadoDesactivar"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDesactivar1ActionPerformed

    public boolean DesactivarEmpleado() throws Exception{
    Empleado temp;
        temp = Empleadodao.findEmpleado(cmbIDEmpleado1.getSelectedIndex());
        if(temp.isEstado()){
            temp.setEstado(false);
            Icon icono = new ImageIcon(getClass().getResource("/Img/Desactivar.png"));
            JOptionPane.showMessageDialog(null,"Empleado Desactivado exitosamente","Guardado",JOptionPane.PLAIN_MESSAGE, icono);

        }
        else{
            temp.setEstado(true);
            Icon icono = new ImageIcon(getClass().getResource("/Img/Activar.png"));
            JOptionPane.showMessageDialog(null,"Empleado Activado exitosamente","Guardado",JOptionPane.PLAIN_MESSAGE,  icono);

        }
        
            Empleadodao.edit(temp);
        
        createTableEmpleado();
        btnActivarDesactivarEmpleado();
        btnDesactivar1.setEnabled(false);
        btnAgregar1.setEnabled(true);
        btnModificar1.setEnabled(false);
        return true;
}
    private void btnLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar1ActionPerformed
        try{
        LimpiarEmpleado();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"EmpleadoLimpiar"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnLimpiar1ActionPerformed

    private void btnModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar1ActionPerformed
        try{
        ModificarEmpleado();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"EmpleadoModificar"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnModificar1ActionPerformed
public boolean ModificarEmpleado() throws Exception{
    if("".equals(txtNombre1.getText().trim())){
            JOptionPane.showMessageDialog(null, "El campo del nombre del empleado esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if("".equals(txtNombre1.getText().trim())){
            JOptionPane.showMessageDialog(null, "El campo del nombre del empleado esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if (ValidacionTresletras(txtNombre1.getText())){
            JOptionPane.showMessageDialog(null,"No se Admite en el nombre del Empleado la misma letra 3 veces en forma consecutiva","Error!", JOptionPane.ERROR_MESSAGE);
            txtNombre1.requestFocus();
            return false;
        }else{

        }
        if(txtNombre1.getText().length()<3){
            JOptionPane.showMessageDialog(null, "La cantidad mínima de caracteres para el nombre del empleado es de 3 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        
        if(txtNombre1.getText().length()>25){
            JOptionPane.showMessageDialog(null, "La cantidad máxima de caracteres para el nombre del empleado es de 25 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if (!ValidacionNombreMayuscula(txtNombre1.getText())){
            JOptionPane.showMessageDialog(null,"El nombre del Empleado debe contener la primera letra mayúscula y luego minúsculas","Error!", JOptionPane.ERROR_MESSAGE);
            txtNombre1.requestFocus();
            return false;
        }else{

        }
        if("".equals(txtApellidos.getText().trim())) {
            JOptionPane.showMessageDialog(null, "El campo del apellido del empleado esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if (ValidacionTresletras(txtApellidos.getText())){
            JOptionPane.showMessageDialog(null,"No se Admite en el apellido del Empleado la misma letra 3 veces en forma consecutiva","Error!", JOptionPane.ERROR_MESSAGE);
            txtApellidos.requestFocus();
            return false;
        }else{

        }
        if(txtApellidos.getText().length()<3) {
            JOptionPane.showMessageDialog(null, "La cantidad mínima de caracteres para el Apellido del empleado es de 3 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if(txtApellidos.getText().length()>25) {
            JOptionPane.showMessageDialog(null, "La cantidad máxima de caracteres para el Apellido del empleado es de 25 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if (!ValidacionNombreMayuscula(txtApellidos.getText())){
            JOptionPane.showMessageDialog(null,"El apellido del Empleado debe contener la primera letra mayúscula y luego minúsculas","Error!", JOptionPane.ERROR_MESSAGE);
            txtApellidos.requestFocus();
            return false;
        }else{

        }
        if("".equals(txtTel.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Ingrese la cantidad necesaria de caracteres para el teléfono del Empleado","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if(txtTel.getText().length()!=8) {
            JOptionPane.showMessageDialog(null, "El teléfono del Empleado solo puede tener 8 Digítos","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if (!telefono(txtTel.getText())){
            JOptionPane.showMessageDialog(null,"Formato de teléfono incorrecto debe comenzar con 2, 3, 7, 8 o 9","Error!", JOptionPane.ERROR_MESSAGE);
            txtTel.requestFocus();
            return false;
        }else{

        }
        if("".equals(txtCorreo.getText().trim())){
            JOptionPane.showMessageDialog(null, "El campo de  correo electrónico del Empleado esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }if (!correo(txtCorreo.getText())){
            JOptionPane.showMessageDialog(null,"Formato de correo electrónico incorrecto","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
        }
        if(txtCorreo.getText().length()<7){
            JOptionPane.showMessageDialog(null, "El correo electrónico del Empleado solo puede tener 7 caracteres como mínimo","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if(txtCorreo.getText().length()>40){
            JOptionPane.showMessageDialog(null, "El correo electrónico del Empleado solo puede tener 40 caracteres como máximo","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }

        if("".equals(txtaDirec.getText().trim())){
            JOptionPane.showMessageDialog(null,"El campo de dirección del Empleado esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if(txtaDirec.getText().length()<3){
            JOptionPane.showMessageDialog(null, "La dirección del Empleado solo puede tener 3 caracteres como mínimo","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if(txtaDirec.getText().length()>50){
            JOptionPane.showMessageDialog(null, "La dirección del Empleado solo puede tener 50 caracteres como máximo","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if (ValidacionTresletras(txtaDirec.getText())){
            JOptionPane.showMessageDialog(null,"No se Admite en la dirección del Empleado la misma letra 3 veces en forma consecutiva","Error!", JOptionPane.ERROR_MESSAGE);
            txtaDirec.requestFocus();
            return false;
        }else{

        }
        if (!ValidacionDireccion(txtaDirec.getText())){
            JOptionPane.showMessageDialog(null,"La dirección solo puede contener números, letras y los siguiente signos(&:|#\";.,-)","Error!", JOptionPane.ERROR_MESSAGE);
            txtaDirec.requestFocus();
            return false;
        }else{

        }
        if(cmbTipoDocumentoEmpleado.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"No ha seleccionado ningún tipo de documento","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if("".equals(txtDocumento.getText().trim())){
            JOptionPane.showMessageDialog(null,"El campo de documento del Empleado esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if(cmbTipoDocumentoEmpleado.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún Tipo de documento","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }

        Tipo_Documento tempop;
        tempop = TipoDocumentodao.findTipo_Documento(cmbTipoDocumentoEmpleado.getSelectedIndex());
        if(tempop.isEstado()!=true){
            JOptionPane.showMessageDialog(null,"Este tipo de documento esta Desactivado","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if("".equals(txtDocumento.getText().trim())){
            JOptionPane.showMessageDialog(null, "El campo para el documento del empleado esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        int aux=14;
        switch (cmbTipoDocumentoEmpleado.getSelectedItem().toString().toLowerCase()) {
            case "1. visa":
            aux=8;
            if(txtDocumento.getText().length()>aux || txtDocumento.getText().length()<aux){
                JOptionPane.showMessageDialog(null, "La Visa debe contener 8 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else{
            }

            if (!ValidacionVISA(txtDocumento.getText())){
                JOptionPane.showMessageDialog(null,"Formato de Visa inválido! Solo debe contener solo Letras Mayúsculas y Números\nEjemplo de número Visa: E0392984","Error!", JOptionPane.ERROR_MESSAGE);
                txtDocumento.requestFocus();
                return false;
            }else{
            }

            break;
            case "2. identidad":
            aux=13;
            if(txtDocumento.getText().length()>aux || txtDocumento.getText().length()<aux){
                JOptionPane.showMessageDialog(null, "La Identidad debe de contener 13 dígitos","Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else{

            }
            if (!ValidacionIdentidad3(txtDocumento.getText())){
                JOptionPane.showMessageDialog(null,"Formato de Identidad inválido! El primer Digito solo puede ser uno o cero","Error!", JOptionPane.ERROR_MESSAGE);
                txtDocumento.requestFocus();
                return false;
            }else{
            }

            break;
            case "3. pasaporte":
            aux=9;
            if(txtDocumento.getText().length()>aux || txtDocumento.getText().length()<aux){
                JOptionPane.showMessageDialog(null, "El Pasaporte debe de contener 9 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else{
                    
            }
            if (!ValidacionPasaporte(txtDocumento.getText())){
                JOptionPane.showMessageDialog(null,"Formato de Pasaporte inválido! Solo puede contener solo Letras Mayúsculas y Números\nEjemplo de número de pasaporte: XR1001R58","Error!", JOptionPane.ERROR_MESSAGE);
                txtDocumento.requestFocus();
                return false;
            }else{
            }

            break;
            case "4. rtn":
            aux=14;
            if(txtDocumento.getText().length()>aux || txtDocumento.getText().length()<aux){
                JOptionPane.showMessageDialog(null, "El Rtn debe de contener 14 dígitos","Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else{

            }
            if (!ValidacionRTN3(txtDocumento.getText())){
                JOptionPane.showMessageDialog(null,"Formato de Rtn inválido! El primer Digito solo puede ser uno o cero","Error!", JOptionPane.ERROR_MESSAGE);
                txtDocumento.requestFocus();
                return false;
            }else{
            }

            break;
            default:
            break;
        }

        if(cmbCargoEmpleado.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún Cargo","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        Cargo_empleado tempopp;
        tempopp = CargoDao.findCargo_empleado(cmbCargoEmpleado.getSelectedIndex());
        if(tempopp.isEstado()!=true){
            JOptionPane.showMessageDialog(null,"El cargo seleccionado esta Desactivado","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }

        if("".equals(txtSueldo.getText().trim())){
            JOptionPane.showMessageDialog(null, "El campo para el Sueldo esta vacio","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }

        if (!ValidacionRangoSueldo(txtSueldo.getText())){
            JOptionPane.showMessageDialog(null,"El rango de Sueldo solo puede estar entre 9,300.00-30,999.99","Error!", JOptionPane.ERROR_MESSAGE);
            txtSueldo.requestFocus();
            return false;
        }else{

        }

        if("".equals(txtDocumento.getText())){
           //JOptionPane.showMessageDialog(null, "Ingrese la cantidad necesaria de dígitos del Documento del empleado","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        else{
            Persona temp = new Persona();
            List<Persona> e = Personadao.findPersonaEntities();
            int auxCmb=cmbIDEmpleado1.getSelectedIndex();
            List<Empleado> f = Empleadodao.findEmpleadoEntities();
            for(Persona a : e){
                for(Empleado t : f){
                    if(a.getId_persona()==t.getId_Persona() && t.getId_Empleado()==auxCmb) {
                        temp.setId_persona(a.getId_persona());
                    } else {
                    }
                }

            }
            temp.setNombre(txtNombre1.getText());
            temp.setApellido(txtApellidos.getText());
            temp.setTelefono(txtTel.getText());
            temp.setDireccion(txtaDirec.getText());
            temp.setCorreo_electroncio(txtCorreo.getText());
            temp.setID_tipo_documento(cmbTipoDocumentoEmpleado.getSelectedIndex());
            temp.setDocumento_id(txtDocumento.getText());
            //JOptionPane.showMessageDialog(null,temp.getId_persona());
            
                Personadao.edit(temp);
            

            Empleado tempp = new Empleado();
            //tempp.setEstado(true);
            //JOptionPane.showMessageDialog(null,auxCmb);
            tempp=Empleadodao.findEmpleado(auxCmb);

            // JOptionPane.showMessageDialog(null,tempp.getId_Empleado());
            //JOptionPane.showMessageDialog(null,temp.getId_persona());
            
                Empleadodao.edit(tempp);
            
            List<HistoricoCargo_empleado> t1 = HistoricoCargoDao.findHistoricoCargo_empleadoEntities();
            HistoricoCargo_empleado temphc2 = new HistoricoCargo_empleado();
            for(HistoricoCargo_empleado  t : t1){
                if(t.getId_empleado() == auxCmb){
                    if(t.getFecha_final()==null){
                        temphc2= t;
                    }
                }
            }
            if(temphc2.getId_cargo()!= (cmbCargoEmpleado.getSelectedIndex())){
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String restar="";
                int pasar=0;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.MONTH)+1) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
            case "01":
                    aux2= "01";
                    break;
                    case "02":
                    aux2= "02";
                    break;case "03":
                    aux2= "03";
                    break;case "04":
                    aux2= "04";
                    break;case "05":
                   aux2= "05";
                    break;case "06":
                    aux2= "06";
                    break;case "07":
                    aux2= "07";
                    break;case "08":
                    aux2= "08";
                    break;case "09":
                    aux2= "09";
                    break;
            case "010":
                    aux2= "10";
                    break;
            case "011":
                   aux2= "11";
                    break;      
            case "012":
                    aux2= "12";
                    break;
                    default:
                    break;
        }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                pasar=(Integer.parseInt(aux3));
                restar=String.valueOf(pasar-1);

                switch(restar){
                    case "1":
                    restar= "0"+restar;
                    break;
                    case "2":
                    restar= "0"+restar;
                    break;
                    case "3":
                    restar= "0"+restar;
                    break;
                    case "4":
                    restar= "0"+restar;
                    break;
                    case "5":
                    restar= "0"+restar;
                    break;
                    case "6":
                    restar= "0"+restar;
                    break;
                    case "7":
                    restar= "0"+restar;
                    break;
                    case "8":
                    restar= "0"+restar;
                    break;
                    case "9":
                    restar= "0"+restar;
                    break;
                    default:
                    break;

                }

                fecha1 = aux1+aux2+restar;
                temphc2.setEstado(false);
                temphc2.setFecha_final((fecha1));
                //temphc2.setId_cargo((cmbCargoEmpleado.getSelectedIndex()));
                temphc2.setId_cargo(temphc2.getId_cargo());//=HistoricoCargoDao.findHistoricoCargo_empleado(temphc2.getId_cargo());
                
                    HistoricoCargoDao.edit(temphc2);
                
                HistoricoCargo_empleado temphc3 = new HistoricoCargo_empleado();
                temphc3.setEstado(true);
                temphc3.setId_cargo_historico(HistoricoCargoDao.getHistoricoCargo_empleadoCount()+1);
                Calendar fecha22 = new GregorianCalendar();
                String fecha11;
                String aux11,aux22,aux33;
                aux11 = Integer.toString(fecha22.get(Calendar.YEAR));
                aux22 = (fecha22.get(Calendar.MONTH)<10)? "0"+Integer.toString(fecha22.get(Calendar.MONTH)+1) : Integer.toString(fecha22.get(Calendar.MONTH));
                switch(aux22){
            case "01":
                    aux22= "01";
                    break;
                    case "02":
                    aux22= "02";
                    break;case "03":
                    aux22= "03";
                    break;case "04":
                    aux22= "04";
                    break;case "05":
                   aux22= "05";
                    break;case "06":
                    aux22= "06";
                    break;case "07":
                    aux22= "07";
                    break;case "08":
                    aux22= "08";
                    break;case "09":
                    aux22= "09";
                    break;
            case "010":
                    aux22= "10";
                    break;
            case "011":
                   aux22= "11";
                    break;      
            case "012":
                    aux22= "12";
                    break;
                    default:
                    break;
        }
                aux33 = (fecha22.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha22.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha22.get(Calendar.DAY_OF_MONTH));
                fecha11 = aux11+aux22+aux33;
                temphc3.setFecha_inicial(fecha11);
                temphc3.setId_cargo(cmbCargoEmpleado.getSelectedIndex());
                temphc3.setId_empleado(auxCmb);

                
                    HistoricoCargoDao.create(temphc3);
                
            }

            HistoricoSueldo_empleado temphs2 = new HistoricoSueldo_empleado();
            List<HistoricoSueldo_empleado> t2 = HistoricoSueldoDao.findHistoricoSueldo_empleadoEntities();
            for(HistoricoSueldo_empleado  tt : t2){
                if(tt.getId_empleado() == cmbIDEmpleado1.getSelectedIndex()){
                    if(tt.getFecha_final()==null){
                        temphs2= tt;
                    }
                }
            }
            if(temphs2.getSueldo()!= (Double.parseDouble(txtSueldo.getText().replace(",", "").replace(",", "").replace("%.2f%n", "")))){
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String restar="";
                int pasar=0;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.MONTH)+1) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
            case "01":
                    aux2= "01";
                    break;
                    case "02":
                    aux2= "02";
                    break;case "03":
                    aux2= "03";
                    break;case "04":
                    aux2= "04";
                    break;case "05":
                   aux2= "05";
                    break;case "06":
                    aux2= "06";
                    break;case "07":
                    aux2= "07";
                    break;case "08":
                    aux2= "08";
                    break;case "09":
                    aux2= "09";
                    break;
            case "010":
                    aux2= "10";
                    break;
            case "011":
                   aux2= "11";
                    break;      
            case "012":
                    aux2= "12";
                    break;
                    default:
                    break;
        }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                pasar=(Integer.parseInt(aux3));
                restar=(String.valueOf(pasar-1));
                switch(restar){
                    case "1":
                    restar= "0"+restar;
                    break;
                    case "2":
                    restar= "0"+restar;
                    break;
                    case "3":
                    restar= "0"+restar;
                    break;
                    case "4":
                    restar= "0"+restar;
                    break;
                    case "5":
                    restar= "0"+restar;
                    break;
                    case "6":
                    restar= "0"+restar;
                    break;
                    case "7":
                    restar= "0"+restar;
                    break;
                    case "8":
                    restar= "0"+restar;
                    break;
                    case "9":
                    restar= "0"+restar;
                    break;
                    default:
                    break;

                }
                fecha1 = aux1+aux2+restar;
                temphs2.setEstado(false);
                temphs2.setFecha_final((fecha1));

                //temphs2.getSueldo((cmbCargoEmpleado.getSelectedIndex()+1));
                //double auxsueldo1=temphs2.getSueldo();
                //double auxsueldo11=(Double.parseDouble(txtSueldo.getText().replace(",", "").replace(",", "").trim()));
                //temphs2=HistoricoSueldoDao.findHistoricoSueldo_empleado(Integer.parseInt(String.valueOf(temphs2.getSueldo())));
                //temphs2.setSueldo(auxsueldo11);
                
                    HistoricoSueldoDao.edit(temphs2);
                

                HistoricoSueldo_empleado temphs3 = new HistoricoSueldo_empleado();
                Calendar fecha222 = new GregorianCalendar();
                String fecha111;

                String aux111,aux222,aux333;
                aux111 = Integer.toString(fecha222.get(Calendar.YEAR));
                aux222 = (fecha222.get(Calendar.MONTH)<10)? "0"+Integer.toString(fecha222.get(Calendar.MONTH)+1) : Integer.toString(fecha222.get(Calendar.MONTH));
                switch(aux222){
            case "01":
                    aux222= "01";
                    break;
                    case "02":
                    aux222= "02";
                    break;case "03":
                    aux222= "03";
                    break;case "04":
                    aux222= "04";
                    break;case "05":
                   aux222= "05";
                    break;case "06":
                    aux222= "06";
                    break;case "07":
                    aux222= "07";
                    break;case "08":
                    aux222= "08";
                    break;case "09":
                    aux222= "09";
                    break;
            case "010":
                    aux222= "10";
                    break;
            case "011":
                   aux222= "11";
                    break;      
            case "012":
                    aux222= "12";
                    break;
                    default:
                    break;
        }
                aux333 = (fecha222.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha222.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha222.get(Calendar.DAY_OF_MONTH));
                fecha111 = aux111+aux222+aux333;
                temphs3.setId_sueldo(HistoricoSueldoDao.getHistoricoSueldo_empleadoCount()+1);
                temphs3.setEstado(true);
                temphs3.setFecha_inicial(fecha111);
                double auxsueldo111=(Double.parseDouble(txtSueldo.getText().replace(",", "").replace(",", "").trim()));
                temphs3.setSueldo(auxsueldo111);
                temphs3.setId_empleado(cmbIDEmpleado1.getSelectedIndex());
                
                    HistoricoSueldoDao.create(temphs3);
                
            }
            Icon icono = new ImageIcon(getClass().getResource("/Img/modificar.png"));
            JOptionPane.showMessageDialog(null,"Datos Modificados exitosamente","Modificado",JOptionPane.PLAIN_MESSAGE, icono);

            createTableEmpleado();
            
            crearTbHistorialSueldo();
            //Tab_Empleado.setSelectedIndex(4);
            crearTbHistorialCargo();
            //Tab_Empleado.setSelectedIndex(0);
            LimpiarEmpleado();
            btnAgregar1.setEnabled(true);
            btnModificar1.setEnabled(false);
            btnBuscar1.setEnabled(false);
            btnDesactivar1.setEnabled(false);
        }
        return true;
}
    private void btnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar1ActionPerformed
        try{
        AgregarEmpleado();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"EmpleadoAgregar"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAgregar1ActionPerformed
public boolean AgregarEmpleado() throws Exception{
    
        if(cmbIDEmpleado1.getSelectedIndex()==0){

        }
        else{
            JOptionPane.showMessageDialog(null, "El ID Empleado siempre debe estar en el ITEM de Nuevo para agregar un nuevo Cliente","Error!", JOptionPane.ERROR_MESSAGE);
            cmbIDEmpleado1.setSelectedIndex(0);
            return false;
        }
        if("".equals(txtNombre1.getText().trim())){
            JOptionPane.showMessageDialog(null, "El campo del nombre del empleado esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if (ValidacionTresletras(txtNombre1.getText())){
            JOptionPane.showMessageDialog(null,"No se Admite en el nombre del Empleado la misma letra 3 veces en forma consecutiva","Error!", JOptionPane.ERROR_MESSAGE);
            txtNombre1.requestFocus();
            return false;
        }else{

        }
        if(txtNombre1.getText().length()<3){
            JOptionPane.showMessageDialog(null, "La cantidad mínima de caracteres para el nombre del empleado es de 3 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        
        if(txtNombre1.getText().length()>25){
            JOptionPane.showMessageDialog(null, "La cantidad máxima de caracteres para el nombre del empleado es de 25 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if (!ValidacionNombreMayuscula(txtNombre1.getText())){
            JOptionPane.showMessageDialog(null,"El nombre del Empleado debe contener la primera letra mayúscula y luego minúsculas","Error!", JOptionPane.ERROR_MESSAGE);
            txtNombre1.requestFocus();
            return false;
        }else{

        }
        if("".equals(txtApellidos.getText().trim())) {
            JOptionPane.showMessageDialog(null, "El campo del apellido del empleado esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if (ValidacionTresletras(txtApellidos.getText())){
            JOptionPane.showMessageDialog(null,"No se Admite en el apellido del Empleado la misma letra 3 veces en forma consecutiva","Error!", JOptionPane.ERROR_MESSAGE);
            txtApellidos.requestFocus();
            return false;
        }else{

        }
        if(txtApellidos.getText().length()<3) {
            JOptionPane.showMessageDialog(null, "La cantidad mínima de caracteres para el Apellido del empleado es de 3 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if(txtApellidos.getText().length()>25) {
            JOptionPane.showMessageDialog(null, "La cantidad máxima de caracteres para el Apellido del empleado es de 25 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if (!ValidacionNombreMayuscula(txtApellidos.getText())){
            JOptionPane.showMessageDialog(null,"El apellido del Empleado debe contener la primera letra mayúscula y luego minúsculas","Error!", JOptionPane.ERROR_MESSAGE);
            txtApellidos.requestFocus();
            return false;
        }else{

        }
        if("".equals(txtTel.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Ingrese la cantidad necesaria de caracteres para el teléfono del Empleado","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if(txtTel.getText().length()!=8) {
            JOptionPane.showMessageDialog(null, "El teléfono del Empleado solo puede tener 8 Digítos","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if (!telefono(txtTel.getText())){
            JOptionPane.showMessageDialog(null,"Formato de teléfono incorrecto debe comenzar con 2, 3, 7, 8 o 9","Error!", JOptionPane.ERROR_MESSAGE);
            txtTel.requestFocus();
            return false;
        }else{

        }
        if("".equals(txtCorreo.getText().trim())){
            JOptionPane.showMessageDialog(null, "El campo de  correo electrónico del Empleado esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }if (!correo(txtCorreo.getText())){
            JOptionPane.showMessageDialog(null,"Formato de correo electrónico incorrecto","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
        }
        if(txtCorreo.getText().length()<7){
            JOptionPane.showMessageDialog(null, "El correo electrónico del Empleado solo puede tener 7 caracteres como mínimo","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if(txtCorreo.getText().length()>40){
            JOptionPane.showMessageDialog(null, "El correo electrónico del Empleado solo puede tener 40 caracteres como máximo","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }

        if("".equals(txtaDirec.getText().trim())){
            JOptionPane.showMessageDialog(null,"El campo de dirección del Empleado esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if(txtaDirec.getText().length()<3){
            JOptionPane.showMessageDialog(null, "La dirección del Empleado solo puede tener 3 caracteres como mínimo","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if(txtaDirec.getText().length()>50){
            JOptionPane.showMessageDialog(null, "La dirección del Empleado solo puede tener 50 caracteres como máximo","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if (ValidacionTresletras(txtaDirec.getText())){
            JOptionPane.showMessageDialog(null,"No se Admite en la dirección del Empleado la misma letra 3 veces en forma consecutiva","Error!", JOptionPane.ERROR_MESSAGE);
            txtaDirec.requestFocus();
            return false;
        }else{

        }
        if (!ValidacionDireccion(txtaDirec.getText())){
            JOptionPane.showMessageDialog(null,"La dirección solo puede contener números, letras y los siguiente signos(&:|#\";.,-)","Error!", JOptionPane.ERROR_MESSAGE);
            txtaDirec.requestFocus();
            return false;
        }else{

        }
        if(cmbTipoDocumentoEmpleado.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"No ha seleccionado ningún tipo de documento","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if("".equals(txtDocumento.getText().trim())){
            JOptionPane.showMessageDialog(null,"El campo de documento del Empleado esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        if(cmbTipoDocumentoEmpleado.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún Tipo de documento","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }

        Tipo_Documento tempop;
        tempop = TipoDocumentodao.findTipo_Documento(cmbTipoDocumentoEmpleado.getSelectedIndex());
        if(tempop.isEstado()!=true){
            JOptionPane.showMessageDialog(null,"Este tipo de documento esta Desactivado","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        if("".equals(txtDocumento.getText().trim())){
            JOptionPane.showMessageDialog(null, "El campo para el documento del empleado esta vacío","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }
        int aux=14;
        switch (cmbTipoDocumentoEmpleado.getSelectedItem().toString().toLowerCase()) {
            case "1. visa":
            aux=8;
            if(txtDocumento.getText().length()>aux || txtDocumento.getText().length()<aux){
                JOptionPane.showMessageDialog(null, "La Visa debe contener 8 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else{
            }

            if (!ValidacionVISA(txtDocumento.getText())){
                JOptionPane.showMessageDialog(null,"Formato de Visa inválido! Solo debe contener solo Letras Mayúsculas y Números\nEjemplo de número Visa: E0392984","Error!", JOptionPane.ERROR_MESSAGE);
                txtDocumento.requestFocus();
                return false;
            }else{
            }

            break;
            case "2. identidad":
            aux=13;
            if(txtDocumento.getText().length()>aux || txtDocumento.getText().length()<aux){
                JOptionPane.showMessageDialog(null, "La Identidad debe de contener 13 dígitos","Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else{

            }
            if (!ValidacionIdentidad3(txtDocumento.getText())){
                JOptionPane.showMessageDialog(null,"Formato de Identidad inválido! El primer Digito solo puede ser uno o cero","Error!", JOptionPane.ERROR_MESSAGE);
                txtDocumento.requestFocus();
                return false;
            }else{
            }

            break;
            case "3. pasaporte":
            aux=9;
            if(txtDocumento.getText().length()>aux || txtDocumento.getText().length()<aux){
                JOptionPane.showMessageDialog(null, "El Pasaporte debe de contener 9 caracteres","Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else{
                    
            }
            if (!ValidacionPasaporte(txtDocumento.getText())){
                JOptionPane.showMessageDialog(null,"Formato de Pasaporte inválido! Solo puede contener solo Letras Mayúsculas y Números\nEjemplo de número de pasaporte: XR1001R58","Error!", JOptionPane.ERROR_MESSAGE);
                txtDocumento.requestFocus();
                return false;
            }else{
            }

            break;
            case "4. rtn":
            aux=14;
            if(txtDocumento.getText().length()>aux || txtDocumento.getText().length()<aux){
                JOptionPane.showMessageDialog(null, "El Rtn debe de contener 14 dígitos","Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else{

            }
            if (!ValidacionRTN3(txtDocumento.getText())){
                JOptionPane.showMessageDialog(null,"Formato de Rtn inválido! El primer Digito solo puede ser uno o cero","Error!", JOptionPane.ERROR_MESSAGE);
                txtDocumento.requestFocus();
                return false;
            }else{
            }

            break;
            default:
            break;
        }

        if(cmbCargoEmpleado.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún Cargo","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }
        Cargo_empleado tempopp;
        tempopp = CargoDao.findCargo_empleado(cmbCargoEmpleado.getSelectedIndex());
        if(tempopp.isEstado()!=true){
            JOptionPane.showMessageDialog(null,"El cargo seleccionado esta Desactivado","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{

        }

        if("".equals(txtSueldo.getText().trim())){
            JOptionPane.showMessageDialog(null, "El campo para el Sueldo esta vacio","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        }

        if (!ValidacionRangoSueldo(txtSueldo.getText())){
            JOptionPane.showMessageDialog(null,"El rango de Sueldo solo puede estar entre 9,300.00-30,999.99","Error!", JOptionPane.ERROR_MESSAGE);
            txtSueldo.requestFocus();
            return false;
        }else{

        }
        

        if("".equals(txtDocumento.getText())){
            JOptionPane.showMessageDialog(null, "Ingrese la cantidad necesaria de dígitos del Documento del empleado","Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
                else{
                 int i;
                boolean flag=false;
                for(i=0;i<Personadao.findPersonaEntities().size();i++){
                    //System.out.println(i);
                if(txtDocumento.getText().equals(Personadao.findPersona(i+1).getDocumento_id())){
                    JOptionPane.showMessageDialog(null, "Ya existe este Documento registrado en el sistema","Error!", JOptionPane.ERROR_MESSAGE);
                    flag=true;
                    return false;
                } else {
                }
                }
                if(flag){
                    return false;
                }

            else{
                Persona temp = new Persona();
                temp.setId_persona(Personadao.getPersonaCount()+1);
                temp.setNombre(txtNombre1.getText());
                temp.setApellido(txtApellidos.getText());
                temp.setTelefono(txtTel.getText());
                temp.setDireccion(txtaDirec.getText());
                temp.setCorreo_electroncio(txtCorreo.getText());
                temp.setID_tipo_documento(cmbTipoDocumentoEmpleado.getSelectedIndex());
                temp.setDocumento_id(txtDocumento.getText());
               
                    Personadao.create(temp);
                
                Empleado tempp = new Empleado();
                tempp.setEstado(true);
                Calendar fecha = new GregorianCalendar();
                tempp.setId_Persona(Personadao.getPersonaCount());
                tempp.setId_Empleado(Empleadodao.getEmpleadoCount()+1);

                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
            case "01":
                    aux2= "01";
                    break;
                    case "02":
                    aux2= "02";
                    break;case "03":
                    aux2= "03";
                    break;case "04":
                    aux2= "04";
                    break;case "05":
                   aux2= "05";
                    break;case "06":
                    aux2= "06";
                    break;case "07":
                    aux2= "07";
                    break;case "08":
                    aux2= "08";
                    break;case "09":
                    aux2= "09";
                    break;
            case "010":
                    aux2= "10";
                    break;
            case "011":
                   aux2= "11";
                    break;      
            case "012":
                    aux2= "12";
                    break;
                    default:
                    break;
        }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+aux2+aux3;
                tempp.setFecha_ingreso(fecha1);

                
                    Empleadodao.create(tempp);
                

                HistoricoCargo_empleado temphc2 = new HistoricoCargo_empleado();
                temphc2.setEstado(true);
                temphc2.setId_cargo_historico(HistoricoCargoDao.getHistoricoCargo_empleadoCount()+1);
                Calendar fechace = new GregorianCalendar();
                String fechace1;
                String auxce1,auxce2,auxce3;
                auxce1 = Integer.toString(fechace.get(Calendar.YEAR));
                auxce2 = (fechace.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fechace.get(Calendar.MONTH)+1)) : Integer.toString(fechace.get(Calendar.MONTH));

                switch(auxce2){
            case "01":
                    auxce2= "01";
                    break;
                    case "02":
                    auxce2= "02";
                    break;case "03":
                    auxce2= "03";
                    break;case "04":
                    auxce2= "04";
                    break;case "05":
                   auxce2= "05";
                    break;case "06":
                    auxce2= "06";
                    break;case "07":
                    auxce2= "07";
                    break;case "08":
                    auxce2= "08";
                    break;case "09":
                    auxce2= "09";
                    break;
            case "010":
                    auxce2= "10";
                    break;
            case "011":
                   auxce2= "11";
                    break;      
            case "012":
                    auxce2= "12";
                    break;
                    default:
                    break;
        }
                auxce3 = (fechace.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fechace.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fechace.get(Calendar.DAY_OF_MONTH));
                fechace1 = auxce1+auxce2+auxce3;
                temphc2.setFecha_inicial(fechace1);
                temphc2.setId_empleado(Empleadodao.getEmpleadoCount());
                temphc2.setId_cargo(cmbCargoEmpleado.getSelectedIndex());
                
                    HistoricoCargoDao.create(temphc2);
                
                HistoricoSueldo_empleado temphs2 = new HistoricoSueldo_empleado();
                temphs2.setEstado(true);
                temphs2.setId_sueldo(HistoricoSueldoDao.getHistoricoSueldo_empleadoCount()+1);
                Calendar fechase = new GregorianCalendar();
                String fechase1;
                String auxse1,auxse2,auxse3;
                auxse1 = Integer.toString(fechase.get(Calendar.YEAR));
                auxse2 = (fechase.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fechase.get(Calendar.MONTH)+1)) : Integer.toString(fechase.get(Calendar.MONTH));
  
                switch(auxse2){
            case "01":
                    auxse2= "01";
                    break;
                    case "02":
                    auxse2= "02";
                    break;case "03":
                    auxse2= "03";
                    break;case "04":
                    auxse2= "04";
                    break;case "05":
                   auxse2= "05";
                    break;case "06":
                    auxse2= "06";
                    break;case "07":
                    auxse2= "07";
                    break;case "08":
                    auxse2= "08";
                    break;case "09":
                    auxse2= "09";
                    break;
            case "010":
                    auxse2= "10";
                    break;
            case "011":
                   auxse2= "11";
                    break;      
            case "012":
                    auxse2= "12";
                    break;
                    default:
                    break;
        }
                auxse3 = (fechase.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fechase.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fechase.get(Calendar.DAY_OF_MONTH));
                fechase1 = auxse1+auxse2+auxse3;
                temphs2.setFecha_inicial(fechase1);
                temphs2.setId_empleado(Empleadodao.getEmpleadoCount());
                double auxsueldo=(Double.parseDouble(txtSueldo.getText().replace(",", "").replace(",", "").trim()));

                temphs2.setSueldo(auxsueldo);
                
                    HistoricoSueldoDao.create(temphs2);
                
            }

            Icon icono = new ImageIcon(getClass().getResource("/Img/agregar.png"));
            JOptionPane.showMessageDialog(null,"Datos Guardados exitosamente","Guardado",JOptionPane.PLAIN_MESSAGE, icono);
            createTableEmpleado();
            crearTbHistorialSueldo();
            crearTbHistorialCargo();
            //Tab_Empleado.setSelectedIndex(2);
            createCmbIDEmpleado();
            //Tab_Empleado.setSelectedIndex(0);
            LimpiarEmpleado();
            createComboBoxEmpleado();
            btnAgregar1.setEnabled(true);
            btnModificar1.setEnabled(false);
            btnDesactivar1.setEnabled(false);
        }
        return true;
}

public void setlabelEmpleado(String valor){
        //IniciodeSesion i = new IniciodeSesion();
        //String valor = i.labelEmple1.getText();
        labelempleado.setText(valor);
    }
    private void txtDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoKeyTyped
char c = evt.getKeyChar();
        if((c < '0' || c > '9')&&(c < 'A' || c > 'Z')){

            evt.consume();

        }
        if (txtDocumento.getText().length() >= 14){
        
        evt.consume();
        
        }
    }//GEN-LAST:event_txtDocumentoKeyTyped

    private void txtaDirecKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaDirecKeyTyped
        char c = evt.getKeyChar();
        if((c < 'a' || c > 'z') && (c != '.' && c != '-' && c != ',' && c != ';' && c != '"' && c != '#' && c != '|' && c != ':' && c != '&')&&(c < '0' || c > '9')&& (c < 'A' || c > 'Z'&& c != 'Ñ' && c != 'ñ' && c != 'Á' && c != 'É' && c != 'Í' && c != 'Ó' && c != 'Ú' && c != 'á' && c != 'é' && c != 'í' && c != 'ó' && c != 'ú')&& (c!=KeyEvent.VK_SPACE)){

            evt.consume();

        }
              
        if (txtaDirec.getText().length() >= 50){
        
        evt.consume();
        
        }
    }//GEN-LAST:event_txtaDirecKeyTyped

    private void cmbTipoDocumentoEmpleadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoDocumentoEmpleadoItemStateChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoDocumentoEmpleadoItemStateChanged

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
        char c = evt.getKeyChar();
        if((c < 'a' || c > 'z') && (c != '@'&&c != 'ñ' && c != '.' && c != ';' && c != ',' && c != '#'&& c != ':'&& c != '-')&&(c < '0' || c > '9')&&(c < 'A' || c > 'Z'&& c != 'Ñ' && c != 'ñ' && c != 'Á' && c != 'É' && c != 'Í' && c != 'Ó' && c != 'Ú' && c != 'á' && c != 'é' && c != 'í' && c != 'ó' && c != 'ú') && (c!=KeyEvent.VK_SPACE)){

            evt.consume();

        }
              
        if (txtCorreo.getText().length() >= 40){
        
        evt.consume();
        
        }
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost

    }//GEN-LAST:event_txtCorreoFocusLost

    private void txtTelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelKeyTyped
        char c = evt.getKeyChar();
        if((c < '0' || c > '9') || c==KeyEvent.VK_BACKSPACE){

            evt.consume();

        }
              
        if (txtTel.getText().length() >= 8){
        
        evt.consume();
        
        }
    }//GEN-LAST:event_txtTelKeyTyped

    private void txtTelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelFocusLost
 
    }//GEN-LAST:event_txtTelFocusLost

    private void txtApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyTyped
        char c = evt.getKeyChar();
        if((c < 'A' || c > 'Z') && (c < 'a' || c > 'z'&& c != 'Ñ' && c != 'ñ' && c != 'Á' && c != 'É' && c != 'Í' && c != 'Ó' && c != 'Ú' && c != 'á' && c != 'é' && c != 'í' && c != 'ó' && c != 'ú') && (c!=KeyEvent.VK_SPACE)){

            evt.consume();

        }
              
        if (txtApellidos.getText().length() >= 25){
        
        evt.consume();
        
        }
    }//GEN-LAST:event_txtApellidosKeyTyped

    private void txtNombre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre1KeyTyped
        char c = evt.getKeyChar();
        if((c < 'A' || c > 'Z') && (c < 'a' || c > 'z' && c != 'Ñ' && c != 'ñ' && c != 'Á' && c != 'É' && c != 'Í' && c != 'Ó' && c != 'Ú' && c != 'á' && c != 'é' && c != 'í' && c != 'ó' && c != 'ú')&& (c!=KeyEvent.VK_SPACE) ){

            evt.consume();

        }
              
        if (txtNombre1.getText().length() >= 25){
        
        evt.consume();
        
        }
    }//GEN-LAST:event_txtNombre1KeyTyped

    private void cmbIDEmpleado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbIDEmpleado1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbIDEmpleado1ActionPerformed

    private void cmbIDEmpleado1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbIDEmpleado1ItemStateChanged
        /* if(cmbIDEmpleado1.getSelectedIndex()==0){
            txtNombre.setText("");
            txtApellidos.setText("");
            txtTel.setText("");
            txtaDirec.setText("");
            txtCorreo.setText("");
            //            cmbTipoDocumentoCli.setSelectedIndex(0);
            txtDocumento.setText("");

        }
        else{
            Persona p;
            p=Personadao.findPersona(cmbIDEmpleado1.getSelectedIndex());

            txtNombre.setText(p.getNombre());
            txtApellidos.setText(p.getApellido());
            txtTel.setText(p.getTelefono());
            txtaDirec.setText(p.getDireccion());
            txtCorreo.setText(p.getCorreo_electroncio());
            cmbTipoDocumentoCli.setSelectedIndex(p.getID_tipo_documento()-1);
            txtDocumento.setText(p.getDocumento_id());
            List<Empleado> c = Empleadodao.findEmpleadoEntities();
            for(Empleado c1 : c){
                if(c1.getId_Persona() == p.getId_persona()){
                    if(c1.getFecha_ingreso()==null){

                        break;
                    }
                    else{
                    }
                }
                btnActivarDesactivarEmpleado();

            }
        }*/
    }//GEN-LAST:event_cmbIDEmpleado1ItemStateChanged

    private void txtNombre1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre1KeyPressed

    }//GEN-LAST:event_txtNombre1KeyPressed

    private void txtCorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoKeyPressed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char c = evt.getKeyChar();
        if((c < 'a' || c > 'z') && (c != '.') && (c < '0' || c > '9')&& (c < 'A' || c > 'Z'&& c != 'Ñ' && c != 'ñ' && c != 'Á' && c != 'É' && c != 'Í' && c != 'Ó' && c != 'Ú' && c != 'á' && c != 'é' && c != 'í' && c != 'ó' && c != 'ú') ){

            evt.consume();

        }
              
        if (txtNombre.getText().length() >= 25){
        
        evt.consume();
        
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaKeyTyped
        char c = evt.getKeyChar();
        if((c < 'a' || c > 'z') && (c != '@'&& c != '#' && c != '$' && c != '%' &&c != '^' && c != '&' && c != '+' && c != '=' )&&(c < '0' || c > '9')&& (c < 'A' || c > 'Z'&& c != 'Ñ' && c != 'ñ' && c != 'Á' && c != 'É' && c != 'Í' && c != 'Ó' && c != 'Ú' && c != 'á' && c != 'é' && c != 'í' && c != 'ó' && c != 'ú') ){

            evt.consume();

        }
        if (txtContraseña.getText().length() >= 15){
        
        evt.consume();
        
        }
    }//GEN-LAST:event_txtContraseñaKeyTyped

    private void txtConfirmarContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmarContraseñaKeyTyped
        char c = evt.getKeyChar();
        if((c < 'a' || c > 'z') && (c != '@'&& c != '#' && c != '$' && c != '%' &&c != '^' && c != '&' && c != '+' && c != '=' )&&(c < '0' || c > '9')&& (c < 'A' || c > 'Z'&& c != 'Ñ' && c != 'ñ' && c != 'Á' && c != 'É' && c != 'Í' && c != 'Ó' && c != 'Ú' && c != 'á' && c != 'é' && c != 'í' && c != 'ó' && c != 'ú') ){

            evt.consume();

        }
        if (txtConfirmarContraseña.getText().length() >= 15){
        
        evt.consume();
        
        }
    }//GEN-LAST:event_txtConfirmarContraseñaKeyTyped

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        try {
            GenerarReporteCargo();
        } catch (JRException | ClassNotFoundException | SQLException e) {
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"CargoReportePDF"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnGenerar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerar2ActionPerformed
        try{
        reporteCargos();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"CargoReporteExcel"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGenerar2ActionPerformed
public void reporteCargos() throws ClassNotFoundException,SQLException, JRException, IOException{
        
   Class.forName("com.mysql.jdbc.Driver");
        
        
       con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsoft","root","");
        
        
        JasperReport reporte = null;
       
            reporte = JasperCompileManager.compileReport("C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteCargo.jrxml");
        
        HashMap param = new HashMap();
        param.put("Empleado", labelempleado.getText());
        JasperPrint print = null;
        
            print = JasperFillManager.fillReport(reporte, param,con);
            //JasperViewer.viewReport(print);
        
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, "C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteCargos.xlsx");

            exporter.exportReport();
        
            
            ProcessBuilder p = new ProcessBuilder();
        p.command("cmd.exe","/c","C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteCargos.xlsx");
        
       
            p.start();
        
        
        
        
}
public void reporteEmpleados() throws SQLException, ClassNotFoundException, JRException, IOException{
    
    Class.forName("com.mysql.jdbc.Driver");
        
        
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsoft","root","");
        
        
        JasperReport reporte = null;
        
            reporte = JasperCompileManager.compileReport("C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reportEmpleados.jrxml");
        
        HashMap param = new HashMap();
        param.put("Empleado", labelempleado.getText());
        JasperPrint print = null;
        
            print = JasperFillManager.fillReport(reporte, param,con);
            //JasperViewer.viewReport(print);
        
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, "C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteEmpleados.xlsx");

        
            exporter.exportReport();
        
            
            ProcessBuilder p = new ProcessBuilder();
        p.command("cmd.exe","/c","C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteEmpleados.xlsx");
        
      
            p.start();
        
}
 
    private void btnGenerar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerar3ActionPerformed
        try{
        reporteUsuarios();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"UsuarioReporteExcel"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGenerar3ActionPerformed
public void reporteUsuarios()throws ClassNotFoundException,SQLException, JRException, IOException{
    
     Class.forName("com.mysql.jdbc.Driver");
    
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsoft","root","");
       
        
        JasperReport reporte = null;
       
            reporte = JasperCompileManager.compileReport("C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteUsuarios.jrxml");
       
        HashMap param = new HashMap();
        param.put("Empleado", labelempleado.getText());
        JasperPrint print = null;
        
            print = JasperFillManager.fillReport(reporte, param,con);
            //JasperViewer.viewReport(print);
        
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, "C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteUsuarios.xlsx");

       
            exporter.exportReport();
       
            
            ProcessBuilder p = new ProcessBuilder();
        p.command("cmd.exe","/c","C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteUsuarios.xlsx");
        
            p.start();
      
}
    public void reporteHistorialSueldo() throws ClassNotFoundException, JRException, SQLException, IOException{
   
    Class.forName("com.mysql.jdbc.Driver");
        
        
          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsoft","root","");
       
        
        JasperReport reporte = null;
      
            reporte = JasperCompileManager.compileReport("C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteHistoricoSueldos.jrxml");
        
        HashMap param = new HashMap();
        param.put("Empleado", labelempleado.getText());
        JasperPrint print = null;
        
            print = JasperFillManager.fillReport(reporte, param,con);
            //JasperViewer.viewReport(print);
       
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, "C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteHistorialSueldos.xlsx");

      
            exporter.exportReport();
       
      
            
            ProcessBuilder p = new ProcessBuilder();
        p.command("cmd.exe","/c","C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteHistorialSueldos.xlsx");
        
       
            p.start();
        
}
public void reporteHistorialCargos() throws ClassNotFoundException, SQLException, JRException, IOException{
     
   Class.forName("com.mysql.jdbc.Driver");
        
       
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsoft","root","");
        
        
        JasperReport reporte = null;
       
        reporte = JasperCompileManager.compileReport("C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteHistorialCargos.jrxml");
       
        HashMap param = new HashMap();
        param.put("Empleado", labelempleado.getText());
        JasperPrint print = null;
        
            print = JasperFillManager.fillReport(reporte, param,con);
            //JasperViewer.viewReport(print);
        
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, "C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteHistorialCargos.xlsx");

       
            exporter.exportReport();
       
            
            ProcessBuilder p = new ProcessBuilder();
        p.command("cmd.exe","/c","C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteHistorialCargos.xlsx");
        
       
             p.start();
        
}
    private void btnGenerar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerar4ActionPerformed

          try{  
            Class.forName("com.mysql.jdbc.Driver");
        
        
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsoft","root","");
        
        
        JasperReport reporteFactura = null;
        
            reporteFactura = JasperCompileManager.compileReport("C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteUsuarios.jrxml");
        
        HashMap param = new HashMap();
         param.put("Empleado", labelempleado.getText());
         JasperPrint print= null;
        
                print = JasperFillManager.fillReport(reporteFactura, param,con);
                //JasperViewer.viewReport(print);
        
        
        File pdf = null;
        
            pdf = File.createTempFile("Reporte de Usuarios"+"-"+".", ".pdf",new File("C:\\CarSoft-Version-2.1\\Reportes"));
        
        
       
            JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(pdf));
        
        //JOptionPane.showMessageDialog(null,pdf.getPath());
        ProcessBuilder p = new ProcessBuilder();
        p.command("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe","/c",pdf.getPath());
        
            p.start();
        } catch (Exception e) {
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"UsuarioReportePDF"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGenerar4ActionPerformed

    private void btnGenerar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerar5ActionPerformed
        try{
        reporteHistorialSueldo();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"HistorialSueldoReporteExcel"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGenerar5ActionPerformed

    private void btnGenerar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerar6ActionPerformed

            try {
            Class.forName("com.mysql.jdbc.Driver");
       
        
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsoft","root","");
        
        
        JasperReport reporteFactura = null;
        
            reporteFactura = JasperCompileManager.compileReport("C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteHistoricoSueldos.jrxml");
       
        HashMap param = new HashMap();
         param.put("Empleado", labelempleado.getText());
         JasperPrint print= null;
       
                print = JasperFillManager.fillReport(reporteFactura, param,con);
                //JasperViewer.viewReport(print);
        
        
        File pdf = null;
      
            pdf = File.createTempFile("Reporte Historico Sueldos"+"-"+".", ".pdf",new File("C:\\CarSoft-Version-2.1\\Reportes"));
       
        
       
            JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(pdf));
        
        //JOptionPane.showMessageDialog(null,pdf.getPath());
        ProcessBuilder p = new ProcessBuilder();
        p.command("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe","/c",pdf.getPath());
      
            p.start();
        } catch (Exception e) {
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"HistorialSueldoReportePDF"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGenerar6ActionPerformed

    private void btnGenerar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerar7ActionPerformed
       try{
        reporteHistorialCargos();
       }catch(Exception e){
           try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"HistorialCargoReporteExcel"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }//GEN-LAST:event_btnGenerar7ActionPerformed

    private void btnGenerar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerar8ActionPerformed

            try {
            Class.forName("com.mysql.jdbc.Driver");
        
        
           
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsoft","root","");
       
        
        JasperReport reporteFactura = null;
       
            reporteFactura = JasperCompileManager.compileReport("C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteHistorialCargos.jrxml");
       
        HashMap param = new HashMap();
         param.put("Empleado", labelempleado.getText());
         JasperPrint print= null;
        
                print = JasperFillManager.fillReport(reporteFactura, param,con);
                //JasperViewer.viewReport(print);
       
        
        File pdf = null;
    
            pdf = File.createTempFile("Reporte Historico Cargos"+"-"+".", ".pdf",new File("C:\\CarSoft-Version-2.1\\Reportes"));
       
        
      
            JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(pdf));
       
        //JOptionPane.showMessageDialog(null,pdf.getPath());
        ProcessBuilder p = new ProcessBuilder();
        p.command("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe","/c",pdf.getPath());
        
            p.start();
        } catch (Exception e) {
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"HistorialCargoReportePDF"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGenerar8ActionPerformed

    private void btnGenerar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerar9ActionPerformed
        try{
        reporteEmpleados();
        }catch(Exception e){
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"EmpleadoReporteExcel"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGenerar9ActionPerformed

    private void btnGenerar10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerar10ActionPerformed
 
            try {
            Class.forName("com.mysql.jdbc.Driver");
        
        
           
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsoft","root","");
        
        
        JasperReport reporteFactura = null;
      
            reporteFactura = JasperCompileManager.compileReport("C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reportEmpleados.jrxml");
        
        HashMap param = new HashMap();
         param.put("Empleado", labelempleado.getText());
         JasperPrint print= null;
        
                print = JasperFillManager.fillReport(reporteFactura, param,con);
                //JasperViewer.viewReport(print);
       
        
        File pdf = null;
        
            pdf = File.createTempFile("Reporte de Empleados"+"-"+".", ".pdf",new File("C:\\CarSoft-Version-2.1\\Reportes"));
        
        
        
            JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(pdf));
        
        //JOptionPane.showMessageDialog(null,pdf.getPath());
        ProcessBuilder p = new ProcessBuilder();
        p.command("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe","/c",pdf.getPath());
        
            p.start();
        } catch (Exception e) {
            try {
                Calendar fecha = new GregorianCalendar();
                String fecha1;
                String aux1,aux2,aux3;
                aux1 = Integer.toString(fecha.get(Calendar.YEAR));
                aux2 = (fecha.get(Calendar.MONTH)<10)? "0"+(Integer.toString(fecha.get(Calendar.MONTH)+1)) : Integer.toString(fecha.get(Calendar.MONTH));
                switch(aux2){
                    case "01":
                        aux2= "01";
                        break;
                    case "02":
                        aux2= "02";
                        break;case "03":
                            aux2= "03";
                            break;case "04":
                                aux2= "04";
                                break;case "05":
                                    aux2= "05";
                                    break;case "06":
                                        aux2= "06";
                                        break;case "07":
                                            aux2= "07";
                                            break;case "08":
                                                aux2= "08";
                                                break;case "09":
                                                    aux2= "09";
                                                    break;
                                                case "010":
                                                    aux2= "10";
                                                    break;
                                                case "011":
                                                    aux2= "11";
                                                    break;
                                                case "012":
                                                    aux2= "12";
                                                    break;
                                                default:
                                                    break;
                }
                aux3 = (fecha.get(Calendar.DAY_OF_MONTH)<10)? "0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)) : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                fecha1 = aux1+""+aux2+""+aux3+"-"+fecha.get(Calendar.HOUR_OF_DAY)+""+fecha.get(Calendar.MINUTE)+""+fecha.get(Calendar.SECOND);
                Logger logger = Logger.getLogger(FrmEmpleados.class.getName());
                FileHandler fh = null;
                fh = new FileHandler("./"+"EmpleadoReportePDF"+fecha1+".log");
                logger.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
                logger.setLevel(Level.WARNING);
                logger.log(Level.SEVERE,e.getMessage());
                fh.close();
            } catch (IOException | SecurityException ex) {
                Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGenerar10ActionPerformed
public boolean GenerarReporteCargo() throws JRException, ClassNotFoundException, SQLException{

            try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsoft","root","");
        } catch (SQLException ex) {
            Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JasperReport reporteFactura = JasperCompileManager.compileReport("C:\\CarSoft-Version-2.1\\src\\main\\java\\Reportes\\reporteCargo.jrxml");
        HashMap param = new HashMap();
         param.put("Empleado", labelempleado.getText());
         JasperPrint print= null;
        try {
                print = JasperFillManager.fillReport(reporteFactura, param,con);
                //JasperViewer.viewReport(print);
        } catch (JRException ex) {
            Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        File pdf = null;
        try {
            pdf = File.createTempFile("Reporte de Cargos"+"-"+".", ".pdf",new File("C:\\CarSoft-Version-2.1\\Reportes"));
        } catch (IOException ex) {
            Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(pdf));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        //JOptionPane.showMessageDialog(null,pdf.getPath());
        ProcessBuilder p = new ProcessBuilder();
        p.command("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe","/c",pdf.getPath());
        try {
            p.start();
        } catch (IOException ex) {
            Logger.getLogger(FrmEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return true;
}
      private  void limpiar(){
        txtIDUsuario.setText("");
        txtNombre.setText("");
        txtContraseña.setText("");
        txtConfirmarContraseña.setText("");
        cmbIDEmpleado.setSelectedIndex(0);
        btnAgregar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnDesactivar.setEnabled(false);
        btnBuscar.setEnabled(true);
        btnBuscar2.setEnabled(true);
        
        
    }
 public boolean ValidacionContrasenia2(String num){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$");
        mat =pat.matcher(num);
        if (mat.find()){
            return true;
        } else{
        return false;
        }
    }
public boolean ValidacionNombreUsuario(String num){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("(?=.{3,40}$)[a-z][a-z]+(?=.{1,40}$)(?:[.][a-z]+)+(?=.{1,40}$)(?:[0-9])$");
        mat =pat.matcher(num);
        if (mat.find()){
            return true;
        } else{
        return false;
        }
    }
public boolean correo(String correo_elec){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[^@]{3,}+[@][^@]+[.][a-zA-Z]{2,}$");
        mat =pat.matcher(correo_elec);
        if (mat.find()){
            return true;
        } else{
        return false;
        }
    }

public boolean ValidacionRangoSueldo(String num){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([4-9][,][0][0][0][.][0][0]|[4-9][,][0-9][0-9][0-9][.][0-9][0-9]|[1-3][0-9][,][0-9][0-9][0-9][.][0-9][0-9]|[4][0][,][0][0][0][.][0][0])$");
        mat =pat.matcher(num);
        if (mat.find()){
            return true;
        } else{
        return false;
        }
    }
public boolean ValidacionDireccion(String num){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^(?=.{3,50}$)[A-ZÑÁÉÍÓÚ][a-zñáéíóú&:|#\";.,-]+(?: [&:|#\\\\\\\";.0-9a-zñA-ZÑÁÉÍÓ-Úáéíóús&:|#\";.,-]+)?(?: [&:|#\\\\\\\";.0-9a-znA-ZÑÁÉÍÓ-Úáéíóús&:|#\";.,-]+)?(?: [&:|#\\\\\\\";.0-9a-zñA-ZÑÁÉÍÓ-Úáéíóús&:|#\";.,-]+)?(?: [&:|#\\\\\\\";.0-9a-znA-ZÑÁÉÍÓ-Úáéíóús&:|#\";.,-]+)?(?: [&:|#\\\\\\\";.0-9a-znA-ZÑÁÉÍÓ-Úáéíóús&:|#\";.,-]+)?(?: [&:|#\\\\\\\";.0-9a-zñA-ZÑÁÉÍÓ-Úáéíóús&:|#\";.,-]+)?(?: [&:|#\\\\\\\";.0-9a-zñA-ZÑÁÉÍÓ-Úáéíóús&:|#\";.,-]+)?(?: [&:|#\\\\\\\";.0-9a-zñA-ZÑÁÉÍÓ-Úáéíóús&:|#\";.,-]+)?(?: [&:|#\\\\\\\";.0-9a-zñA-ZÑÁÉÍÓ-Úáéíóús&:|#\";.,-]+)?(?: [&:|#\\\\\\\";.0-9a-zñA-ZÑÁÉÍÓ-Úáéíóús&:|#\";.,-]+)?(?: [&:|#\\\\\\\";.0-9a-zñA-ZÑÁÉÍÓ-Úáéíóús&:|#\";.,-]+)?(?: [&:|#\\\\\\\";.0-9a-zñA-ZÑÁÉÍÓ-Úáéíóús&:|#\";.,-]+)?(?: [&:|#\\\\\\\";.0-9a-zñA-ZÑÁÉÍÓ-Úáéíóús&:|#\";.,-])?$");
        mat =pat.matcher(num);
        if (mat.find()){
            return true;
        } else{
        return false;
        }
    }
public boolean ValidacionNombreMayusculaYDemasMinus(String num){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^(?=.{3,40}$)[A-ZÑÁÉÍÓÚ][a-zñáéíóú]+(?: [a-zñáéíóúA-ZÑÁÉÍÓÚ]+)?+(?: [a-zñáéíóúA-ZÑÁÉÍÓÚ]+)?$");
        mat =pat.matcher(num);
        if (mat.find()){
            return true;
        } else{
        return false;
        }
    }
 private  void LimpiarEmpleado(){
    cmbIDEmpleado1.setSelectedIndex(0);
        createTableEmpleado();
        txtNombre1.setText("");
        txtApellidos.setText("");
        txtTel.setText("");
        txtaDirec.setText("");
        txtSueldo.setText("");
        cmbCargoEmpleado.setSelectedIndex(0);
        cmbTipoDocumentoEmpleado.setSelectedIndex(0);
        txtDocumento.setText("");
        txtCorreo.setText("");
        btnAgregar1.setEnabled(true);
        btnModificar1.setEnabled(false);
        btnDesactivar1.setEnabled(false);
        btnBuscar1.setEnabled(true);
}
 private  void TblEmpleados(){
        btnAgregar1.setEnabled(false);
        btnModificar1.setEnabled(true);
        btnBuscar1.setEnabled(false);
        btnDesactivar1.setEnabled(true);
        int column=0;
        int fila = tblEmpleados.getSelectedRow();
        if (fila > -1){
            Empleado temp = Empleadodao.findEmpleado(Integer.parseInt(tblEmpleados.getModel().getValueAt(fila, column).toString()));
            List<HistoricoSueldo_empleado> tempc = HistoricoSueldoDao.findHistoricoSueldo_empleadoEntities();
            List<Persona> tempp = Personadao.findPersonaEntities();
            List<HistoricoCargo_empleado> tempb = HistoricoCargoDao.findHistoricoCargo_empleadoEntities();
            List<Cargo_empleado> temppc = CargoDao.findCargo_empleadoEntities();
            List<Tipo_Documento> tempptp = TipoDocumentodao.findTipo_DocumentoEntities();
           for(Persona b : tempp){
                if(b.getId_persona()==temp.getId_Persona()){
            cmbIDEmpleado1.setSelectedIndex(Integer.parseInt(tblEmpleados.getModel().getValueAt(fila, column).toString()));
            txtNombre1.setText(b.getNombre());
            txtApellidos.setText(b.getApellido());
            txtTel.setText(b.getTelefono());
            txtaDirec.setText(b.getDireccion());
            txtCorreo.setText(b.getCorreo_electroncio());
            for(Tipo_Documento pptp : tempptp){
                  if(b.getID_tipo_documento()==pptp.getId_Tipo_Documento()){ 
                cmbTipoDocumentoEmpleado.setSelectedItem((b.getID_tipo_documento()+". "+pptp.getDocumento()));
                txtDocumento.setText(b.getDocumento_id());
                  }
                }
                }
            }
                for(HistoricoCargo_empleado cc : tempb){
                if(cc.getId_empleado()==temp.getId_Empleado()){
               for(Cargo_empleado pp : temppc){
            if(pp.getId_cargo()==cc.getId_cargo()){       
            cmbCargoEmpleado.setSelectedItem((cc.getId_cargo()+". "+pp.getCargo()));          
            }
                }
                }
            }
                for(HistoricoSueldo_empleado ccse : tempc){
                if(ccse.getId_empleado()==temp.getId_Empleado()){
                    double auxsueldo= ccse.getSueldo();
                    //double auxsueldo= Double.parseDouble(String.valueOf(ccse.getSueldo()).replace(",", "").replace(".", ""));
            txtSueldo.setText(String.format("%,.2f",auxsueldo));
            //txtSueldo.setText(String.valueOf(ccse.getSueldo()));
                }
            }
            
            btnActivarDesactivarEmpleado();
        }
} public boolean ValidacionTresletras(String Validar){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("(?i)(.*aaa.*|.*bbb.*|.*ccc.*|.*ddd.*|.*eee.*|.*fff.*|.*ggg.*|.*hhh.*|.*iii.*|.*jjj.*|.*kkk.*|.*lll.*|.*mmm.*|.*nnn.*|.*ooo.*|.*ppp.*|.*qqq.*|.*rrr.*|.*sss.*|.*ttt.*|.*uuu.*|.*vvv.*|.*www.*|.*xxx.*|.*yyy.*|.*zzz.*)");
        mat = pat.matcher(Validar);
            
            if(mat.matches()){
                return true;   
            }
            else{
               return false; 
            }
    }
 private  void LimpiarNuevoCargo(){
    cmbIDNuevoCargo.setSelectedIndex(0);
    txtNuevoCargo.setText("");
        btnAgregar2.setEnabled(true);
        btnModificar2.setEnabled(false);
        btnDesactivar2.setEnabled(false);
        createTableNuevoCargo();
        createComboBoxNuevoCargo();
}
    public boolean ValidacionIdentidad3(String num){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[0-1]{1}[0-9]{1}[0-9]{1}[1-9]{1}[19|20]{2}[0-9]{7}$");
        mat =pat.matcher(num);
        if (mat.find()){
            return true;
        } else{
        return false;
        }
    }

public boolean ValidacionRTN3(String num){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("[0-1]{1}[0-9]{1}[0-9]{1}[1-9]{1}[19|20]{2}[0-9]{7}[1-9]{1}$");
        mat =pat.matcher(num);
        if (mat.find()){
            return true;
        } else{
        return false;
        }
    }
public boolean ValidacionVISA(String num){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("[A-Z]{1}[0-9]{7}");
        mat =pat.matcher(num);
        if (mat.find()){
            return true;
        } else{
        return false;
        }
    }

public boolean ValidacionPasaporte(String num){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("[A-Z]{1}[A-Z]{1}[A-Z0-9]{1}[0-9]{3}[A-Z]{1}[0-9]{2}");
        mat =pat.matcher(num);
        if (mat.find()){
            return true;
        } else{
        return false;
        }
    }
   public boolean telefono(String tel){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[2|3|7|8|9]{1}[0-9]{2,10}$");
        mat =pat.matcher(tel);
        if (mat.find()){
            return true;
        } else{       
        return false;
        
        }
    }

            private  void filtroCargoHistorico() {
        TableRowSorter trsfiltro = new TableRowSorter(jTbHistorialCargo.getModel());
        jTbHistorialCargo.setRowSorter(trsfiltro);
        trsfiltro.setRowFilter(RowFilter.regexFilter(txtEmpleadoCargo.getText(), 1));      
}     private  void BuscarEmpleadoUsuario(){
        /*List<Usuarios> temp = UsuariosDao.findUsuariosEntities();
        Empleado temp2 = new Empleado();
        boolean bandera = false;
        Persona temp3 = new Persona();
        if("".equals(txtIDNombreEmpleado.getText())){
            JOptionPane.showMessageDialog(null,"El campo de nombre de empleado esta vacio","Error!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{   
        } 
                for(Usuarios u : temp){
                    temp2=Empleadodao.findEmpleado(u.getId_Empleado());
                    temp3=Personadao.findPersona(temp2.getId_Persona());   
                    String auxNombre=(temp3.getNombre()+" "+temp3.getApellido());
                    if (auxNombre.equals(txtIDNombreEmpleado.getText())){
                    txtIDUsuario.setText(String.valueOf(u.getId_Usuario()));
                    cmbIDEmpleado.setSelectedIndex(temp2.getId_Empleado());
                    txtNombre.setText(u.getId_Nombre());
                    
                    bandera=true;
                    }
                    else{
                     
                    }
                }        
                if(!bandera){
                        JOptionPane.showMessageDialog(null,"El Empleado que ingreso no existe","Error!",JOptionPane.ERROR_MESSAGE); 
                    }   */
        BuscarEmpleadoModal();
        
}

 private  void BuscarEmpleadoModal(){
    ModalEmpleado temp2 = new ModalEmpleado(this,true);
        temp2.setLocationRelativeTo(null);
        temp2.setVisible(true);
        cmbIDEmpleado.setSelectedItem(temp2.getId()+". "+temp2.getNombre()+" "+temp2.getApellido());
}


 public  boolean BuscarEmpleadoDocumento(){
        List<Empleado> temp = Empleadodao.findEmpleadoEntities();
        boolean flag = false;
        Persona temp3 = new Persona();
        Tipo_Documento temp4 = new Tipo_Documento();
        List<HistoricoSueldo_empleado> temp8 = HistoricoSueldoDao.findHistoricoSueldo_empleadoEntities();
        List<HistoricoCargo_empleado> temp9 = HistoricoCargoDao.findHistoricoCargo_empleadoEntities();
        if("".equals(txtDocumento.getText())){
            //JOptionPane.showMessageDialog(null,"El campo de documento del empleado esta vacio","Error!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{   
        } 
                for(Empleado e : temp){
                    temp3=Personadao.findPersona(e.getId_Persona());   
                    temp4=TipoDocumentodao.findTipo_Documento(temp3.getID_tipo_documento());  
                    if (temp3.getDocumento_id().equals(txtDocumento.getText())){
                    cmbIDEmpleado1.setSelectedIndex(e.getId_Empleado());
                    cmbIDEmpleado.setSelectedIndex(e.getId_Empleado());
                    txtNombre1.setText(temp3.getNombre());
                    txtApellidos.setText(temp3.getApellido());
                    txtTel.setText(temp3.getTelefono());
                    txtaDirec.setText(temp3.getDireccion());
                    cmbTipoDocumentoEmpleado.setSelectedIndex(temp3.getID_tipo_documento());
                    txtCorreo.setText(temp3.getCorreo_electroncio());
                    for(HistoricoSueldo_empleado es: temp8){
                        if(es.getId_empleado()==e.getId_Empleado()){
                        txtSueldo.setText(String.valueOf(es.getSueldo()));
                        }
                    }
                    for(HistoricoCargo_empleado ec: temp9){
                        if(ec.getId_empleado()==e.getId_Empleado()){   
                        cmbCargoEmpleado.setSelectedIndex(ec.getId_cargo());
                        }
                    }
                    
                    flag=true;
                    }
                    else{
                     
                    }
                }        
                if(!flag){
                        //JOptionPane.showMessageDialog(null,"El Empleado que busco no existe","Error!",JOptionPane.ERROR_MESSAGE); 
                    return false;
                }  
                return true;
}   
 private void BuscarUsuario(){
        if("".equals(txtNombre.getText())){
            JOptionPane.showMessageDialog(null,"El campo de nombre de usuario esta vacio","Error!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
     
        }
        List<Usuarios> tempp = UsuariosDao.findUsuariosEntities();
        List<Empleado> tempc = Empleadodao.findEmpleadoEntities();
        List<Persona> temp = Personadao.findPersonaEntities();
        boolean bandera = false;
            for(Usuarios e: tempp) {
                if((e.getId_Nombre()).equals(txtNombre.getText())){
                    for(Empleado tp: tempc){
                        if(e.getId_Empleado()==tp.getId_Empleado()){
                    for(Persona p: temp){
                        if(tp.getId_Persona()==p.getId_persona()){
                    cmbIDEmpleado.setSelectedItem(e.getId_Empleado()+". "+p.getNombre()+" "+p.getApellido());
                    txtIDUsuario.setText(String.valueOf(e.getId_Usuario()));
                    txtNombre.setText(e.getId_Nombre());
                    
                    btnBuscar.setEnabled(false);
                    btnBuscar2.setEnabled(false);
                    btnModificar.setEnabled(true);
                    btnAgregar.setEnabled(false);
                    btnDesactivar.setEnabled(true);
                    bandera=true;
                    }
                    }
                    }
                    }
                }  
                else{
                          
                }
                
            }
            if(!bandera){
                    JOptionPane.showMessageDialog(null,"El usuario que ingreso no existe","Error!",JOptionPane.ERROR_MESSAGE);
                }
            

} private void filtroSueldoHistorico() {
        TableRowSorter trsfiltro = new TableRowSorter(tblHistorialSueldo.getModel());
        tblHistorialSueldo.setRowSorter(trsfiltro);
        trsfiltro.setRowFilter(RowFilter.regexFilter(txtEmpleadoSueldo.getText(), 1)); 
        
}
    
    
    
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTabbedPane Tab_Empleado;
    private javax.swing.JTable Tbl_Usuarios;
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnAgregar1;
    public javax.swing.JButton btnAgregar2;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnBuscar1;
    public javax.swing.JButton btnBuscar2;
    public javax.swing.JButton btnDesactivar;
    public javax.swing.JButton btnDesactivar1;
    public javax.swing.JButton btnDesactivar2;
    public javax.swing.JButton btnGenerar;
    public javax.swing.JButton btnGenerar10;
    public javax.swing.JButton btnGenerar2;
    public javax.swing.JButton btnGenerar3;
    public javax.swing.JButton btnGenerar4;
    public javax.swing.JButton btnGenerar5;
    public javax.swing.JButton btnGenerar6;
    public javax.swing.JButton btnGenerar7;
    public javax.swing.JButton btnGenerar8;
    public javax.swing.JButton btnGenerar9;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiar1;
    private javax.swing.JButton btnLimpiar2;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnModificar1;
    public javax.swing.JButton btnModificar2;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnRegresar1;
    private javax.swing.JButton btnRegresar2;
    private javax.swing.JButton btnRegresar3;
    private javax.swing.JButton btnRegresar4;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JButton btnSalir2;
    private javax.swing.JButton btnSalir3;
    private javax.swing.JButton btnSalirCargoHistorico;
    public javax.swing.JComboBox<String> cmbCargoEmpleado;
    public javax.swing.JComboBox<String> cmbIDEmpleado;
    public javax.swing.JComboBox<String> cmbIDEmpleado1;
    public javax.swing.JComboBox<String> cmbIDNuevoCargo;
    public javax.swing.JComboBox<String> cmbTipoDocumentoEmpleado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTbHistorialCargo;
    public javax.swing.JLabel labelempleado;
    public javax.swing.JTable tblEmpleados;
    private javax.swing.JTable tblHistorialSueldo;
    public javax.swing.JTable tblNuevoCargo;
    public javax.swing.JTextField txtApellidos;
    public javax.swing.JPasswordField txtConfirmarContraseña;
    public javax.swing.JPasswordField txtContraseña;
    public javax.swing.JTextField txtCorreo;
    public javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtEmpleadoCargo;
    private javax.swing.JTextField txtEmpleadoSueldo;
    public javax.swing.JTextField txtIDUsuario;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtNombre1;
    public javax.swing.JTextField txtNuevoCargo;
    public javax.swing.JTextField txtSueldo;
    public javax.swing.JTextField txtTel;
    public javax.swing.JTextArea txtaDirec;
    // End of variables declaration//GEN-END:variables

}
