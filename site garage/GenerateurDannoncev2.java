import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class GenerateurDannoncev2 extends JFrame implements ActionListener {
	private JLabel title;
	private JTextField textFieldModele;
	private JTextField textFieldAnnee;
	private JTextField textFieldKm;
	private JTextField textFieldEnergie;
	private JTextField textFieldPuissanceFiscale;
	private JTextField textFieldCouleur;
	private JTextField textFieldPrix;
	private JTextField textFieldImgUrl;
	private JTextField textFieldDelAnnonce;

	private JLabel txtModele;
	private JLabel txtAnnee;
	private JLabel txtKm;
	private JLabel txtEnergie;
	private JLabel txtPuissanceFiscale;
	private JLabel txtCouleur;
	private JLabel txtPrix;
	private JLabel txtImgUrl;

	private JButton generateButton;

	public static String htmlCode;

	public GenerateurDannoncev2() {
		this.setTitle("Générateur d'annonce");
		this.setLayout(new GridLayout(3, 1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		// Création des composants
		JPanel panelMilieu = new JPanel(new GridLayout(8, 2));
		JPanel panelTitle = new JPanel(new FlowLayout());
		JPanel panelBas = new JPanel(new FlowLayout());

		this.title = new JLabel("Generateur d'annonce");

		this.textFieldModele = new JTextField(20);
		this.textFieldAnnee = new JTextField(4);
		this.textFieldKm = new JTextField(20);
		this.textFieldEnergie = new JTextField(20);
		this.textFieldPuissanceFiscale = new JTextField(1);
		this.textFieldCouleur = new JTextField(20);
		this.textFieldPrix = new JTextField(20);
		this.textFieldImgUrl = new JTextField(20);
		this.textFieldDelAnnonce = new JTextField(2);

		this.txtModele = new JLabel("Modele    :");
		this.txtAnnee = new JLabel("Annee     :");
		this.txtKm = new JLabel("Km        :");
		this.txtEnergie = new JLabel("Energie   :");
		this.txtPuissanceFiscale = new JLabel("P Fiscale :");
		this.txtCouleur = new JLabel("Couleur   :");
		this.txtPrix = new JLabel("Prix      :");
		this.txtImgUrl = new JLabel("ImgUrl    :");

		this.generateButton = new JButton("Generer");

		// Ajout des composants aux panels

		panelTitle.add(this.title);

		panelMilieu.add(this.txtModele);
		panelMilieu.add(this.textFieldModele);
		panelMilieu.add(this.txtAnnee);
		panelMilieu.add(this.textFieldAnnee);
		panelMilieu.add(this.txtKm);
		panelMilieu.add(this.textFieldKm);
		panelMilieu.add(this.txtEnergie);
		panelMilieu.add(this.textFieldEnergie);
		panelMilieu.add(this.txtPuissanceFiscale);
		panelMilieu.add(this.textFieldPuissanceFiscale);
		panelMilieu.add(this.txtCouleur);
		panelMilieu.add(this.textFieldCouleur);
		panelMilieu.add(this.txtPrix);
		panelMilieu.add(this.textFieldPrix);
		panelMilieu.add(this.txtImgUrl);
		panelMilieu.add(this.textFieldImgUrl);

		panelBas.add(this.generateButton);

		// Pré-réglage du textField image url

		this.textFieldImgUrl.setText("../images/");

		// Ajout du panel à la frame
		this.add(panelTitle);
		this.add(panelMilieu);
		this.add(panelBas);

		// Ajout de l'action listener au bouton
		this.setVisible(true);
		this.pack();

		// Ajout du bouton au listener

		generateButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == generateButton) {
			String modele = textFieldModele.getText();
			String annee = textFieldAnnee.getText();
			String km = textFieldKm.getText();
			String puissanceFiscale = textFieldPuissanceFiscale.getText();
			String energie = textFieldEnergie.getText();
			String couleur = textFieldCouleur.getText();
			String imgUrl = textFieldImgUrl.getText();
			String prix = textFieldPrix.getText();

			GenerateurDannoncev2.htmlCode = "\n\n<div class=\"card-row\">\n" +
					"    <div class=\"card cardAnnonces\">\n" +
					"        <img src=" + imgUrl + " alt= " + modele + " class=\"card-img-top\">\n" +
					"        <div class=\"card-body\">\n" +
					"            <h5 class=\"card-title\">" + modele + "</h5>\n" +
					"            <p class=\"card-text\">\n" +
					"                Annee : " + annee + "<br>\n" +
					"                Km : " + km + " km<br>\n" +
					"                Energie : " + energie + "<br>\n" +
					"                Couleur : " + couleur + "<br>\n" +
					"				 Puissance Fiscale : " + puissanceFiscale + "\n" +
					"            </p>\n" +
					"            <div class=\"card-footer\">\n" +
					"                <strong><b>" + prix + "Euros </b></strong>\n" +
					"            </div>\n" +
					"        </div>\n" +
					"    </div>\n" +
					"</div>";

			// Écriture dans le fichier
			try {
				writeAtLine("vente/vente.html", 125, GenerateurDannoncev2.htmlCode);

			} catch (IOException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Une erreur est survenue lors de la génération de l'annonce.");
			}
		}

	}

	public static void writeAtLine(String filePath, int lineNumber, String content) throws IOException {
	
		File file = new File(filePath);
		RandomAccessFile raf = new RandomAccessFile(file, "rw");

		// Se positionner à la ligne spécifiée
		for (int i = 1; i < lineNumber; i++) {
			if (raf.readLine() == null) {
				// La ligne spécifiée est en dehors du fichier, vous pouvez gérer cela en conséquence
				raf.close();
				throw new IOException("La ligne spécifiée est en dehors du fichier.");
			}
		}

		// Stocker la position actuelle dans le fichier
		long currentPosition = raf.getFilePointer();

		// Lire le contenu restant du fichier à partir de la position actuelle
		StringBuilder remainingContent = new StringBuilder();
		String line;
		while ((line = raf.readLine()) != null) {
			remainingContent.append(line).append("\n");
		}

		// Se repositionner à la position enregistrée
		raf.seek(currentPosition);

		// Ecrire le contenu à la ligne spécifiée
		raf.writeBytes(htmlCode + "\n");

		// Ecrire le contenu restant à partir de la ligne suivante
		raf.writeBytes(remainingContent.toString());

		raf.close();
	}

	public static void deleteNextLine(String filePath) {
		try {
			// Création d'un objet RandomAccessFile en mode "lecture/écriture"
			RandomAccessFile file = new RandomAccessFile(new File(filePath), "rw");

			// Récupération de la position actuelle dans le fichier
			long currentPosition = file.getFilePointer();

			// Lecture de la prochaine ligne
			String line = file.readLine();

			// Récupération de la position de la ligne suivante
			long nextPosition = file.getFilePointer();

			// Calcul de la longueur de la ligne lue
			int lineLength = line.getBytes().length;

			// Calcul de la longueur totale du contenu après la ligne à supprimer
			long remainingLength = file.length() - nextPosition;

			// Déplacement du pointeur de fichier à la position de la ligne à supprimer
			file.seek(currentPosition);

			// Copie du contenu après la ligne à supprimer
			byte[] remainingContent = new byte[(int) remainingLength];
			file.read(remainingContent);

			// Déplacement du pointeur de fichier à la position de la ligne suivante
			file.seek(nextPosition);

			// Écriture du contenu copié à la position de la ligne à supprimer
			file.write(remainingContent);

			// Tronquer le fichier pour supprimer le contenu restant
			file.setLength(file.length() - lineLength);

			// Fermeture du fichier
			file.close();

			System.out.println("La ligne suivante a été supprimée avec succès.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void delALine(String filePath, int firstLineToDelete, int lineNumber) throws IOException {
		File file = new File(filePath);
		RandomAccessFile raf = new RandomAccessFile(file, "rw");

		// Se posistionner à la lineToDelete -1
		if (raf.readLine() == null) {
			// La ligne spécifiée est en dehors du fichier, vous pouvez gérer cela en
			// conséquence
			raf.close();
			throw new IOException("La ligne spécifiée est en dehors du fichier.");
		}

		// Stocker la position actuelle dans le fichier
		long currentPosition = raf.getFilePointer();

		for(int i = 0; i < lineNumber; i++)
		{
			raf.readLine();
			deleteNextLine(filePath);
		}

	}

	public static void main(String[] args) {
		new GenerateurDannoncev2();
		File file = new File("vente/vente.html");
		String filePath = file.getPath();

		
	}

}


