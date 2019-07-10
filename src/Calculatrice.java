import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculatrice extends JFrame {
	private JPanel container = new JPanel();
	String[] tab_affichage_boutons = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "+", "-", "*", "/",
			"C" };
	JButton[] tab_buttons = new JButton[tab_affichage_boutons.length];
	private JLabel screen = new JLabel();
	private Dimension smallButton = new Dimension(50, 40);
	private Dimension largeButton = new Dimension(50, 30);
	private boolean clicOperator = false, update = false;
	private double chiffre1;
	private String operator = "";

	public Calculatrice() {
		this.setSize(350, 270);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Ma calculatrice");
		initComponent();
		this.setContentPane(container);
		this.setVisible(true);
	}

	private void initComponent() {

		// ecran
		Font font = new Font("Arial", Font.BOLD, 15);
		screen = new JLabel("0");
		screen.setFont(font);

		screen.setHorizontalAlignment(JLabel.RIGHT);
		screen.setPreferredSize(new Dimension(220, 25));

		// operateur
		JPanel operator = new JPanel();
		operator.setPreferredSize(new Dimension(60, 230));

		// chiffre
		JPanel number = new JPanel();
		number.setPreferredSize(new Dimension(190, 230));

		// container de l'écran entier
		JPanel panScreen = new JPanel();
		panScreen.setPreferredSize(new Dimension(220, 25));

		for (int i = 0; i < tab_affichage_boutons.length; i++) {
			tab_buttons[i] = new JButton(tab_affichage_boutons[i]);
			tab_buttons[i].setPreferredSize(smallButton);
			switch (i) {
			// attribuer le bon listener pour chaque élément qui n'est pas un chiffre
			case 11:
				tab_buttons[i].addActionListener(new EgalListener());
				number.add(tab_buttons[i]);
				break;
			case 12:
				tab_buttons[i].addActionListener(new PlusListener());
				tab_buttons[i].setPreferredSize(largeButton);
				operator.add(tab_buttons[i]);
				break;
			case 13:
				tab_buttons[i].addActionListener(new MoinsListener());
				tab_buttons[i].setPreferredSize(largeButton);
				operator.add(tab_buttons[i]);
				break;
			case 14:
				tab_buttons[i].addActionListener(new MultiplicationListener());
				tab_buttons[i].setPreferredSize(largeButton);
				operator.add(tab_buttons[i]);
				break;
			case 15:
				tab_buttons[i].addActionListener(new DivisionListener());
				tab_buttons[i].setPreferredSize(largeButton);
				operator.add(tab_buttons[i]);
				break;
			case 16:
				tab_buttons[i].setForeground(Color.red);
				tab_buttons[i].addActionListener(new ResetListener());
				operator.add(tab_buttons[i]);
				break;
			default:
				tab_buttons[i].addActionListener(new ChiffreListener());
				number.add(tab_buttons[i]);
				break;
			}
		}
		panScreen.add(screen);
		container.add(panScreen, BorderLayout.NORTH);
		container.add(number, BorderLayout.CENTER);
		container.add(operator, BorderLayout.EAST);
	}

	private void calcul() {
		// ecran.getText() : récupère la valeur sous forme de String
		// Double.valueOf(ecran.getText()) : converti la valeur de String vers Double
		// (le type Object)
		// Double.valueOf(ecran.getText()).doubleValue() : converti le Double en double
		// (type primitif)
		// chiffre1 + Double.valueOf(ecran.getText()).doubleValue() : fait la somme de
		// la valeur initiale avec celle à l'écran
		// String.valueOf(chiffre1) : converti la nouvelle valeur sous forme de String
		// ecran.setText(String.valueOf(chiffre1)) : affiche la valeur finale
		if (operator.equals("+")) {
			chiffre1 = chiffre1 + Double.valueOf(screen.getText()).doubleValue();
			screen.setText(String.valueOf(chiffre1));

		}
		if (operator.equals("-")) {
			chiffre1 = chiffre1 - Double.valueOf(screen.getText()).doubleValue();
			screen.setText(String.valueOf(chiffre1));

		}
		if (operator.equals("*")) {
			chiffre1 = chiffre1 * Double.valueOf(screen.getText()).doubleValue();
			screen.setText(String.valueOf(chiffre1));
		}
		if (operator.equals("/")) {
			try {
				chiffre1 = chiffre1 / Double.valueOf(screen.getText()).doubleValue();
				screen.setText(String.valueOf(chiffre1));

			} catch (ArithmeticException e) {
				screen.setText("0");

			}
		}

	}
	
	//Listener 
	class ChiffreListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//Stocker les chiffres tapés par l'user et les afficher sur l'écran 
			String str= ((JButton)e.getSource()).getText();
			if(update) {
				update= false;
			}
			else {
				if(!screen.getText().equals("0")) {
					str= screen.getText() + str;
				}
			}
			screen.setText(str);
		}
	}
	
	class EgalListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			calcul();
			update= true;
			clicOperator= false;
		}
	}
	
	class PlusListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(clicOperator) {
				calcul();
				screen.setText(String.valueOf(chiffre1));
			}
			else {
				chiffre1= Double.valueOf(screen.getText()).doubleValue();
				clicOperator= true;
			}
			operator= "+";
			update= true;
		}
		
	}
	
	class MoinsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(clicOperator) {
				calcul();
				screen.setText(String.valueOf(chiffre1));
			}
			else {
				chiffre1 = Double.valueOf(screen.getText()).doubleValue();
				clicOperator= true;
			}
			operator= "-";
			update= true;
		}
	}
	
	class MultiplicationListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(clicOperator) {
				calcul();
				screen.setText(String.valueOf(chiffre1));
			}
			else {
				chiffre1= Double.valueOf(screen.getText()).doubleValue();
				clicOperator= true;
			}
			operator = "*";
			update= true;
		}
	}
	
	class DivisionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(clicOperator) {
				calcul();
				screen.setText(String.valueOf(chiffre1));
			}
			else {
				chiffre1= Double.valueOf(screen.getText()).doubleValue();
				clicOperator= true;
			}
			operator= "/";
			update= true;
		}
	}
	
	class ResetListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			clicOperator= false;
			update= true;
			chiffre1= 0;
			operator="";
			screen.setText("");
			
		}
	}

}
