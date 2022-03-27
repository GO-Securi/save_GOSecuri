import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Main {
    public static void main(String[] args) throws IOException {

        //etape 1
        System.out.println("Debut generation liste agents");
        String path = "";

        //Ecriture du fichier agents.html (liste des agents)
        File dossierhtml = new File(path + "html"); // On cree le dossier si il n'existe pas
        dossierhtml.mkdir();
        Files.copy(Paths.get("Style.css"), Paths.get("html", "Style.css"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("LogoGoSecuri.png"), Paths.get("html", "LogoGoSecuri.png"), StandardCopyOption.REPLACE_EXISTING);
        try (FileWriter writer = new FileWriter(new File(path + "html/index.html"));
        BufferedWriter bw = new BufferedWriter(writer)){


            bw.write("<!DOCTYPE html><html><head><link href=\"Style.css\" rel=\"stylesheet\" type=\"text/css\"></head><body><header><img class=\"logo\" src=\"LogoGoSecuri.png\" alt=\"Logo de l'entreprise\"></header><ul class=\"agents\">");
            //Lecture du fichier staff.txt
            BufferedReader staff = new BufferedReader(new FileReader(new File(path + "staff.txt")));
            String line_staff = staff.readLine();




            while (line_staff != null) {
                BufferedReader agent = new BufferedReader(new FileReader(new File(path + line_staff +".txt")));

                //de 0 a 3 : infos agents
                //0: Nom
                //1: prenom
                //2: role
                //3: mdp
                //de 5 a ? : inventaire

                ArrayList<String> infos_agents = new ArrayList<String>();
                ArrayList<String> inv_agents = new ArrayList<String>();
                String line_agent = agent.readLine();
                int i = 0;
                while (line_agent != null) {
                    //System.out.println("Test" + i + ": " + line_agent);
                    if (i < 5) {
                        infos_agents.add(line_agent);
                    }else{
                        inv_agents.add(line_agent);
                    }
                    line_agent = agent.readLine();
                    i++;
                }

                //etape2
                //Pour chaque agents on cree un fichier + htpasswd + htaccess --------------------------------------------------------------------------------

                System.out.println("Dossier crée: "+path + "html/" + line_staff);
                File dossier = new File(path + "html/" + line_staff); // On cree le dossier si il n'existe pas
                dossier.mkdir();

                try (FileWriter writer2 = new FileWriter(new File(path + "html/" +line_staff+ "/index.html"));
                BufferedWriter bw_fiche_agent = new BufferedWriter(writer2)){
                    System.out.println("    Fichier crée: "+ path + "html/" + line_staff + "/" + "index.html");

                    //htaccess ---------------------------------------------------------------------------------------------------------------------
                    try (FileWriter writer3 = new FileWriter(new File(path + "html/"+line_staff+ "/.htaccess"));
                    BufferedWriter htaccess = new BufferedWriter(writer3)){
                        System.out.println("    Fichier crée: " + path + "html/"+line_staff+ "/.htaccess");

                        htaccess.write("AuthUserFile /usr/local/apache2/htdocs/" +line_staff+ "/.htpasswd" + "\n");
                        htaccess.write("AuthGroupFile /dev/null"+ "\n");
                        htaccess.write("AuthName \"Veuillez vous identifier\""+ "\n");
                        htaccess.write("AuthType Basic"+ "\n");

                        htaccess.write("<Limit GET POST>"+ "\n");
                        htaccess.write("require valid-user"+ "\n");
                        htaccess.write("</Limit>"+ "\n");
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    //Fin htaccess -----------------------------------------------------------------------------------------------------------------

                    //htpasswd ---------------------------------------------------------------------------------------------------------------------
                    try (FileWriter writer4 = new FileWriter(new File(path + "html/"+line_staff+ "/.htpasswd"));
                    BufferedWriter htpasswd = new BufferedWriter(writer4)){
                        System.out.println("    Fichier crée: " + path + "html/"+line_staff+ "/.htpasswd");
                        htpasswd.write(line_staff+":"+ infos_agents.get(3));
                    }catch (IOException e) {
                        e.printStackTrace();
                    } 
                    //Fin htpasswd -----------------------------------------------------------------------------------------------------------------
                    System.out.println("    Liste info: " + infos_agents);
                    System.out.println("    Liste inventaire: " + inv_agents);

                    //Header
                    bw_fiche_agent.write("<!DOCTYPE html><html><head><link href=\"..\\Style.css\" rel=\"stylesheet\" type=\"text/css\"></head><body><header><img class=\"logo\" src=\"..\\LogoGoSecuri.png\" alt=\"Logo de l'entreprise\"></header><div class=\"div_nomprenom\"><p class=\"nomprenom\">"+infos_agents.get(0) + " " + infos_agents.get(1)+ " (" + infos_agents.get(2) + ")" +"</p><img class=\"img_profil\" src=\"/img/prenom_nom.png\" alt=\"Photo de X\"><h2>Mon Equipement:</h2><ul class=\"liste\">");
                    //Boucle pour afficher l'inventaire
                    

            

                    for (String item : inv_agents) {
                        bw_fiche_agent.write("<li>"+ item +"</li>");
                    }


                    bw_fiche_agent.write("</ul></div><footer class=\"footer\"><p class=\"mentions\">Mentions legales</p></footer></body><html>");
                }catch (IOException e) {
                    e.printStackTrace();
                }

                bw.write("<li class=\"agent\" ><a href=\""+ line_staff + "\\" + "index.html\">");
                bw.write(infos_agents.get(0) + " " + infos_agents.get(1));
                bw.write("</a></li>");


                line_staff = staff.readLine();
            }
            bw.write("</ul><footer class=\"footer\"><p class=\"mentions\">Mentions légales</p></footer></body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}