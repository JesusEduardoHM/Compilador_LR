package Ventanas;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

import Analizadores.Ana_LexSem;
import Analizadores.Ana_Sint;

public class Errores extends JFrame {

	private JPanel contentPane;
	private String errorlex[],errorsinc[];
	JScrollPane scroll;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Errores frame = new Errores();
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
	public Errores() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTablaDeErrores = new JLabel("Tabla de Errores");
		lblTablaDeErrores.setHorizontalAlignment(SwingConstants.CENTER);
		lblTablaDeErrores.setForeground(Color.WHITE);
		lblTablaDeErrores.setFont(new Font("Yu Gothic", Font.BOLD, 20));
		lblTablaDeErrores.setBounds(52, 11, 259, 27);
		contentPane.add(lblTablaDeErrores);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setForeground(Color.WHITE);
		btnCerrar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnCerrar.setBackground(Color.BLACK);
		btnCerrar.setBounds(251, 372, 89, 27);
		contentPane.add(btnCerrar);
		
		JTextArea JTextArea = new JTextArea();
		JTextArea.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		JTextArea.setEditable(false);
		
		scroll = new JScrollPane(JTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(22, 44, 305, 322);
		contentPane.add(scroll);
		
		if(errorlex.length==0 && errorsinc.length==0)
			JOptionPane.showMessageDialog(null, "No se encontraron errores Lexicos ni Sintacticos"); //si no se encuentran errores lex y sint arrojara este mensaje
		else
		{ //si se encuentran errores entonces entra al else
			JTextArea.setText("");
			String cad="";
			for(int i=0;i<errorlex.length;i++) //aqui recopilamos nuestros errores lex
			{
				cad+=errorlex[i]+"\n";
			}
			for(int i=0;i<errorsinc.length;i++) //aqui los errores sint
			{
				cad+=errorsinc[i]+"\n";
			}
			
		}
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(Errores.class.getResource("/Imagenes/Fondo2.jpg")));
		label.setBounds(0, 0, 350, 410);
		contentPane.add(label);
		setUndecorated(true);
	    this.setLocationRelativeTo(null);
	}
	
	public Errores(Ana_LexSem obj, Ana_Sint obj2){ //en este constructor resivimos un objeto de tipo lex que nos va a mostrar los errores lex que se encontraron
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTablaDeErrores = new JLabel("Tabla de Errores");
		lblTablaDeErrores.setHorizontalAlignment(SwingConstants.CENTER);
		lblTablaDeErrores.setForeground(Color.WHITE);
		lblTablaDeErrores.setFont(new Font("Yu Gothic", Font.BOLD, 20));
		lblTablaDeErrores.setBounds(52, 11, 259, 27);
		contentPane.add(lblTablaDeErrores);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setForeground(Color.WHITE);
		btnCerrar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnCerrar.setBackground(Color.BLACK);
		btnCerrar.setBounds(251, 372, 89, 27);
		contentPane.add(btnCerrar);
		
		JTextArea JTextArea = new JTextArea();
		JTextArea.setEditable(false);
		
		scroll = new JScrollPane(JTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(22, 44, 305, 322);
		contentPane.add(scroll);
		
		String errorl[]=obj.getPilaerror();
		String errors[]=obj2.getPilaerror();
		String errorsem[]=obj.getPilaerrorS();
		
		JTextArea.setText("Errores \n");
		if(errorl.length==0)
			JOptionPane.showMessageDialog(null, "No se encontro ningun error Lexico");
		else
		{
			String cad="";
			for(int i=0;i<errorl.length;i++) //solo recopilamos los errores lex
			{
				cad+=errorl[i]+"\n";
			}
			JTextArea.setText(JTextArea.getText()+cad);
			
		}
		
		if(errors.length==0)
			JOptionPane.showMessageDialog(null, "No se encontro ningun error Sintactico");
		else
		{
			String cad="";
			for(int i=0;i<errors.length;i++) //solo recopilamos los errores Ana_Sint
			{
				cad+=errors[i]+"\n";
			}
			JTextArea.setText(JTextArea.getText()+cad);
			
		}
		
		if(errorsem.length==0)
			JOptionPane.showMessageDialog(null, "No se encontro ningun error Semanticos");
		else
		{
			String cad="";
			for(int i=0;i<errorsem.length;i++) //solo recopilamos los errores Ana_Sint
			{
				cad+=errorsem[i]+"\n";
			}
			JTextArea.setText(JTextArea.getText()+cad);
			
		}
		
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(Errores.class.getResource("/Imagenes/Fondo2.jpg")));
		label.setBounds(0, 0, 350, 410);
		contentPane.add(label);
		setUndecorated(true);
	    this.setLocationRelativeTo(null);
	}
}
