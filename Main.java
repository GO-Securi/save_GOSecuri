import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Debut script");
        String path = "";

        //Lecture du fichier staff.txt
        try {
            System.out.println("<!DOCTYPE html><html><head><link href=\"Style.css\" rel=\"stylesheet\" type=\"text/css\"></head><body><header><img class=\"logo\" src=\"logo.png\" alt=\"Logo de l'entreprise\"></header><ul class=\"agents\">");
            BufferedReader staff = new BufferedReader(new FileReader(new File(path + "datas\\staff.txt")));
            String line_staff = staff.readLine();
            while (line_staff != null) {
                System.out.print("<li class=\"agent\" ><a href=\""+ line_staff +".html\">");
                BufferedReader agent = new BufferedReader(new FileReader(new File(path + "datas\\"+ line_staff +".txt")));
                String line_agent = agent.readLine();
                int i = 0;
                while (i <= 1) {
                    i++;
                    System.out.print(line_agent + " ");
                    line_agent = agent.readLine();
                }
                System.out.println("</a></li>");
                line_staff = staff.readLine();
            }
            System.out.println("</ul><footer class=\"footer\"><p class=\"mentions\">Mentions légales</p></footer></body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}