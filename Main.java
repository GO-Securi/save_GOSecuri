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

        //Lecture du fichier staff.txt
        try (FileWriter writer = new FileWriter(new File(path + "html\\agents.html"));
        BufferedWriter bw = new BufferedWriter(writer)){


            bw.write("<!DOCTYPE html><html><head><link href=\"Style.css\" rel=\"stylesheet\" type=\"text/css\"></head><body><header><img class=\"logo\" src=\"logo.png\" alt=\"Logo de l'entreprise\"></header><ul class=\"agents\">");

            BufferedReader staff = new BufferedReader(new FileReader(new File(path + "datas\\staff.txt")));
            String line_staff = staff.readLine();



            while (line_staff != null) {

                try (FileWriter writer2 = new FileWriter(new File(path + line_staff + "\\" + line_staff + "agents.html"));
                BufferedWriter bw_fiche_agent = new BufferedWriter(writer2)){

                bw.write("<li class=\"agent\" ><a href=\""+ line_staff + "\\" + line_staff + ".html\">");
                BufferedReader agent = new BufferedReader(new FileReader(new File(path + "datas\\"+ line_staff +".txt")));
                String line_agent = agent.readLine();
                int i = 0; 
                while (i <= 1) {
                    i++;
                    bw.write(line_agent + " ");
                    line_agent = agent.readLine();
                }
                bw.write("</a></li>");
                line_staff = staff.readLine();

                //etape 2

    bw_fiche_agent.write("<!DOCTYPE html><html><head><link href=\"Style.css\" rel=\"stylesheet\" type=\"text/css\"></head><body><header><img class=\"logo\" src=\"logo.png\" alt=\"Logo de l'entreprise\"></header");




                

                
                    
             
        
                    
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bw.write("</ul><footer class=\"footer\"><p class=\"mentions\">Mentions légales</p></footer></body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }


        


    }
}