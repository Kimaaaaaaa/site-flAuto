import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.io.IOException;

public class GenerateurDannonce{

    public static String htmlCode;
    private static int averageLineLength(String filePath) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        long totalLength = 0;
        int lineCount = 0;
        
        String line;
        while ((line = file.readLine()) != null) {
            totalLength += line.length();
            lineCount++;
        }
        
        file.close();
        
        if (lineCount == 0) {
            return 0;
        }
        
        return (int) (totalLength / lineCount);
    }
    public static void main(String [] args) throws FileNotFoundException , IOException 
    {
        int cpt;
        String nom_fichier      = "database.txt"; //Fichier de base de donnée
        String nomVehicule      = "";
        String annee            = "";
        String km               = "";
        String energie          = "";
        String puissanceFiscale = "";
        String couleur          = "";
        String prix             = "";

        PrintWriter pw = new PrintWriter(new FileWriter("vente/vente.html", true));
        try (Scanner sc = new Scanner(new File(nom_fichier))) 
        {
            cpt = 0;
            while(sc.hasNextLine() && cpt<7)
            {   
                System.out.println(cpt);
                switch(cpt)
                {
                    case 0 :{
                        if (sc.hasNextLine()) {
                            nomVehicule = sc.nextLine();
                            System.out.println(nomVehicule);
                        }
                        break;
                        
                    } 

                    case 1 :{
                        if (sc.hasNextLine()) {
                            annee = sc.nextLine();
                            System.out.println(annee);
                        }
                        break;     
                    } 

                    case 2 :{
                        if (sc.hasNextLine()) {
                            km = sc.nextLine();
                            System.out.println(km);
                        }
                        break;     
                    }
                    
                    case 3 :{
                        if (sc.hasNextLine()) {
                            energie = sc.nextLine();
                            System.out.println(energie);
                        }
                        break;     
                    } 

                    case 4 :{
                        if (sc.hasNextLine()) {
                            puissanceFiscale = sc.nextLine();
                            System.out.println(puissanceFiscale);
                        }
                        break;     
                    } 

                    case 5 :{
                        if (sc.hasNextLine()) {
                            couleur = sc.nextLine();
                            System.out.println(couleur);
                        }
                        break;     
                    } 

                    case 6 :{
                        if (sc.hasNextLine()) {
                            prix = sc.nextLine();
                            System.out.println(prix);
                        }
                        break;     
                    } 

                    
                   


                }
                cpt++;
                
            }

            try {
                int lineNumber = 124; // La ligne à laquelle vous souhaitez vous positionner 
                String filePath = "database.txt";
                
                RandomAccessFile file = new RandomAccessFile(filePath, "rw");
                
                // Calculer la position de la ligne en utilisant la taille moyenne d'une ligne
                long position = (lineNumber - 1) * averageLineLength(filePath);
                
                // Se positionner à la ligne spécifique
                file.seek(position);
                
                // Écrire dans le fichier
                                System.out.println(nomVehicule);
                                System.out.println(annee);
                                System.out.println(km);
                                System.out.println(energie);
                                System.out.println(puissanceFiscale);
                                System.out.println(couleur);
                                System.out.println(prix);


                                pw.println("\n\n<div class=\"card-row\">\n");
                                pw.println("  <div class=\"card cardAnnonces\">\n");
                                pw.println("    <img src=\"../images/" + nomVehicule + " alt= "+ nomVehicule + "class=\"card-img-top\">\n");
                                pw.println("    <div class=\"card-body\">\n");
                                pw.println("      <h5 class=\"card-title\">" + nomVehicule + "</h5>\n");
                                pw.println("      <p class=\"card-text\">\n");
                                pw.println("        Année : "+ annee +"<br>\n");
                                pw.println("        Km : " + km + "<br>\n");
                                pw.println("        Energie :" + energie + "<br>\n");
                                pw.println("        Puissance fiscale :" + puissanceFiscale + "<br>\n");
                                pw.println("        Couleur :" +  couleur + "\n");
                                pw.println("      </p>\n");
                                pw.println("      <div class=\"card-footer\">\n");
                                pw.println("        <strong><b>" + prix + "</b></strong>\n");
                                pw.println("      </div>\n");
                                pw.println("    </div>\n");
                                pw.println("  </div>\n");
                                pw.println("</div>");
                                pw.close();

                
                // Fermer le fichier
                file.close();
                
                System.out.println("Écriture terminée.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


 
}
            



        

    

