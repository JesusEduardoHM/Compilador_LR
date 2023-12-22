package Ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Analizadores.Generador;
import Analizadores.Generador;
import Analizadores.Ana_LexSem;
import Analizadores.Ana_Sint;

import java.awt.Toolkit;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.Window.Type;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.TextArea;
import java.awt.Scrollbar;


public class Interfaz extends JFrame {

	private JPanel contentPane;
	private Generador gen=new Generador();
	private Ana_LexSem obl=new Ana_LexSem();
	private Ana_Sint obs=new Ana_Sint();
	private Errores obe;
	private Generador obg=new Generador();
	private String Ruta="";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
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
	public Interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1085, 620);
		contentPane = new JPanel();
		contentPane.setBackground(Color.getHSBColor(270f / 360f, 1.0f, 1.0f));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton Salir = new JButton("Salir");
		Salir.setBackground(SystemColor.controlText);
		Salir.setForeground(Color.WHITE);
		Salir.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		Salir.setBounds(930, 560, 89, 27);
		contentPane.add(Salir);
		
		JButton ArchG = new JButton("Guardar Archivo");		
		ArchG.setForeground(Color.WHITE);
		ArchG.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		ArchG.setBackground(Color.BLACK);
		ArchG.setBounds(900, 400, 146, 27);
		contentPane.add(ArchG);
		
		JButton ArchC = new JButton("Cargar Archivo");		
		ArchC.setForeground(Color.WHITE);
		ArchC.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		ArchC.setBackground(Color.BLACK);
		ArchC.setBounds(900, 440, 146, 27);
		contentPane.add(ArchC);
		
		TextArea Prin = new TextArea();
		Prin.setBounds(10, 60, 1060, 300);
		contentPane.add(Prin);
		
		JButton Analizar = new JButton("Analizar");	
		Analizar.setForeground(Color.WHITE);
		Analizar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		Analizar.setBackground(Color.BLACK);
		Analizar.setBounds(900, 480, 146, 27);
		contentPane.add(Analizar);
		
		JLabel PSin = new JLabel("Pila Sintáctica");
		PSin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		PSin.setForeground(Color.BLACK);
		PSin.setBounds(150, 365, 153, 14);
		contentPane.add(PSin);
		
		JLabel TablaSem = new JLabel("Semántico");
		TablaSem.setForeground(Color.BLACK);
		TablaSem.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		TablaSem.setBounds(720, 365, 153, 14);
		contentPane.add(TablaSem);
		
		TextArea textSin = new TextArea();
		textSin.setBounds(10, 385, 350, 214);
		textSin.setEditable(false);
		contentPane.add(textSin);
		
		JLabel Cod = new JLabel("Código C");
		Cod.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		Cod.setForeground(Color.BLACK);
		Cod.setBounds(470, 365, 153, 17);
		contentPane.add(Cod);
		
		TextArea textCod = new TextArea();
		textCod.setBounds(400, 385, 200, 214);
		textCod.setEditable(false);
		contentPane.add(textCod);
		
		TextArea textSem = new TextArea();
		textSem.setBounds(630, 385, 250, 214);
		textSem.setEditable(false);
		contentPane.add(textSem);
		
		JLabel Lex = new JLabel("Componentes Lexicos");
		Lex.setForeground(Color.BLACK);
		Lex.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		Lex.setBounds(10, 20, 150, 14);
		contentPane.add(Lex);
		
		TextArea textLex = new TextArea();
		textLex.setBounds(160, 10, 915, 40);
		textLex.setEditable(false);
		contentPane.add(textLex);
		
		JButton VerErrores = new JButton("Ver Errores");
		VerErrores.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(obs!=null)
				{					
					obe=new Errores(obl,obs); //recopilamos los errores
					obe.setVisible(true);
				}
				else
				{
					if(obl!=null)
					{		
						obe=new Errores(obl,obs); //recopilamos los errores
						obe.setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(null, "Se nesesita analizar el codigo");
					}
				}	
					
			}
		});
		VerErrores.setForeground(Color.WHITE);
		VerErrores.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		VerErrores.setBackground(Color.BLACK);
		VerErrores.setBounds(900, 520, 146, 27);
		contentPane.add(VerErrores);
		
		
		
		JLabel Fondo = new JLabel("");		
		Fondo.setBackground(Color.BLACK);
		Fondo.setHorizontalAlignment(SwingConstants.CENTER);
		//Fondo.setIcon(new ImageIcon(Interfaz.class.getResource("/Imagenes/Fondo.jpg")));
		Fondo.setBounds(0, 0, 950, 620);
		contentPane.add(Fondo);
		setUndecorated(true);
	    this.setLocationRelativeTo(null);

	    
	    //Acciones de botones
	    
	    ArchC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File obf = null;
				 JFileChooser chooser = new JFileChooser();
				    FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "TXT","txt");
				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showOpenDialog(getParent());
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				    	Prin.setText("");
				    	obf=chooser.getSelectedFile();
				    	Txt obx=new Txt(obf.getAbsolutePath());// abrimos el contenido del archivo
				    	//System.out.println(obf);
				    	Ruta=obf+"";
						Prin.setText(obx.getTexto());	// se lo asignamos al control
				    }
				    else
				    {
				    	JOptionPane.showMessageDialog(null, "No se cargo ningun archivo");
				    }
			   			
			}
		});
	    
	    ArchG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					FileWriter f=new FileWriter(Ruta);
					f.write(Prin.getText());
					f.close();
				}catch(IOException e1) 
				{
				}
			}
	    });
	    Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	    
	    Analizar.addActionListener(new ActionListener() {// analizarrr
			public void actionPerformed(ActionEvent e) {
				
				obl=new Ana_LexSem();
				textLex.setText("");
				textSin.setText("");
				textSem.setText("");
				textCod.setText("");
				
				obg=new Generador();
				obg.Analizar(Prin.getText());
				textCod.setText(obg.getCodigo());
				
				obl.Analizar(Prin.getText());
				textLex.setText(obl.getCodigo());
				obs=new Ana_Sint();
				textSin.setText(obs.metodo(obl.getComponentesLexicos()));
				textSem.setText(obl.tablaSem());
			}
		});
	}
}
