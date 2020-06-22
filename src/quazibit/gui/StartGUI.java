package quazibit.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

import quazibit.javabeangenerator.*;

public class StartGUI extends JFrame 
{

	private JPanel contentPanel;
	private JTextPane textLog;
	private JTextPane textOutput;
	
	
	private JFileChooser chooser;
	
	private String filePath = null;
	private File dataFile = null;

	public StartGUI() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 469);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		buttonPanel.setBounds(10, 11, 321, 50);
		contentPanel.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		JButton btnOpenFile = new JButton("Open File");
		btnOpenFile.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				openFile();
			}
		});
		btnOpenFile.setBounds(10, 11, 93, 27);
		buttonPanel.add(btnOpenFile);
		btnOpenFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				start();
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStart.setBounds(218, 11, 93, 27);
		buttonPanel.add(btnStart);
		
		JPanel logPanel = new JPanel();
		logPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		logPanel.setBounds(10, 72, 321, 346);
		contentPanel.add(logPanel);
		logPanel.setLayout(null);
		
		JLabel lblLog = new JLabel("Log");
		lblLog.setBounds(0, 0, 321, 24);
		lblLog.setHorizontalAlignment(SwingConstants.CENTER);
		lblLog.setFont(new Font("Tahoma", Font.PLAIN, 14));
		logPanel.add(lblLog);
		
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBounds(10, 24, 301, 311);
		logPanel.add(jScrollPane);
		
		textLog = new JTextPane();
		jScrollPane.setViewportView(textLog);
		
		JPanel outputPanel = new JPanel();
		outputPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		outputPanel.setBounds(341, 11, 273, 407);
		contentPanel.add(outputPanel);
		outputPanel.setLayout(null);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOutput.setBounds(0, 0, 273, 24);
		outputPanel.add(lblOutput);
		
		JScrollPane jScrollPane2 = new JScrollPane();
		jScrollPane2.setBounds(10, 24, 253, 372);
		outputPanel.add(jScrollPane2);
		
		textOutput = new JTextPane();
		jScrollPane2.setViewportView(textOutput);
		
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
				
	}
	
	private void openFile()
	{
		textLog.setText("");
		textOutput.setText("");
		
        int result = chooser.showOpenDialog(null);

        if(result == JFileChooser.APPROVE_OPTION)
        {
        	filePath = chooser.getSelectedFile().getPath();

            String str = "[FILE-PATH]: " + filePath.toString() + "\n";
            System.out.println(str);
			
			try
			{
				dataFile = new File(filePath);
				
				textLog.setText(str);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
        }
	}
	
	private void start()
	{
		if (dataFile != null)
		{
			StartConsole startConsole = new StartConsole();
			startConsole.start(dataFile);	
			
			String log = startConsole.getGenerateDataClass().getUniversalCSVReader().getDataParser().getLogOutput();
			textLog.setText(textLog.getText() + log); 
			textOutput.setText(startConsole.getGenerateDataClass().getLogOutput());
		}
		else
		{
			textLog.setText("You have to open a data file.");
		}
	}
	
	
	
	
	
}
