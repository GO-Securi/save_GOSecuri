import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.FileWriter;
import java.io.BufferedWriter;

public class Main {
    public static void main(String[] args) throws IOException {


        //etape 1
        System.out.println("Debut generation liste agents");
        String path = "";

        //Ecriture du fichier agents.html (liste des agents)
        try (FileWriter writer = new FileWriter(new File(path + "html\\index.html"));
        BufferedWriter bw = new BufferedWriter(writer)){


            bw.write("<!DOCTYPE html><html><head><link href=\"Style.css\" rel=\"stylesheet\" type=\"text/css\"></head><body><header><img class=\"logo\" src=\"logo.png\" alt=\"Logo de l'entreprise\"></header><ul class=\"agents\">");
            //Lecture du fichier staff.txt
            BufferedReader staff = new BufferedReader(new FileReader(new File(path + "datas\\staff.txt")));
            String line_staff = staff.readLine();



            while (line_staff != null) {
                bw.write("<li class=\"agent\" ><a href=\""+ line_staff + "\\" + "index.html\">");
                BufferedReader agent = new BufferedReader(new FileReader(new File(path + "datas\\"+ line_staff +".txt")));
                //etape2
                //Pour chaque agents on cree un fichier + htpasswd --------------------------------------------------------------------------------
                System.out.println("Dossier crée: "+path + "html\\" + line_staff);
                File dossier = new File(path + "html\\" + line_staff); // On cree le dossier si il n'existe pas
                dossier.mkdir();

                try (FileWriter writer2 = new FileWriter(new File(path + "html\\"+line_staff+"\\index.html"));
                BufferedWriter bw_fiche_agent = new BufferedWriter(writer2)){
                    System.out.println("    Fichier crée: "+path + "html\\" + line_staff + "\\" + "index.html");

                    //htpasswd ---------------------------------------------------------------------------------------------------------------------
                    try (FileWriter writer3 = new FileWriter(new File(path + "html\\"+line_staff+"\\htpasswd"));
                    BufferedWriter htacces = new BufferedWriter(writer3)){
                        System.out.println("    Fichier crée: "+path + "html\\"+line_staff+"\\htpasswd");
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    //Fin htpasswd -----------------------------------------------------------------------------------------------------------------


                    //Header
                    bw_fiche_agent.write("<!DOCTYPE html><html><head><link href=\"..\\Style.css\" rel=\"stylesheet\" type=\"text/css\"></head><body><header><img class=\"logo\" src=\"logo.png\" alt=\"Logo de l'entreprise\"></header");


                }catch (IOException e) {
                    e.printStackTrace();
                }

                String line_agent = agent.readLine();
                int i = 0; 
                while (i <= 1) {
                    i++;
                    bw.write(line_agent + " ");
                    line_agent = agent.readLine();
                }
                bw.write("</a></li>");
                line_staff = staff.readLine();
            }
            bw.write("</ul><footer class=\"footer\"><p class=\"mentions\">Mentions légales</p></footer></body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }


        


    }
}