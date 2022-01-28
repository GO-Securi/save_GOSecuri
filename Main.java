import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
            System.out.println("Debut script");
            //Lecture du fichier staff.txt

            try {
                BufferedReader staff = new BufferedReader(new FileReader(new File("datas\\staff.txt")));
                String line_staff = staff.readLine();

                while (line_staff != null) {
                    System.out.print("<li class=\"agent\" ><a href=\""+ line_staff +".html\">");



                    BufferedReader agent = new BufferedReader(new FileReader(new File("datas\\"+ line_staff +".txt")));
                    String line_agent = agent.readLine();
                    int i = 0;

                    while (i <= 1) {
                        i++;
                        System.out.print(line_agent + " ");
                        // read next line
                        line_agent = agent.readLine();


                    }
                    
                    System.out.println("</a></li>");

                    // read next line
                    line_staff = staff.readLine();
                }
            
            } catch (IOException e) {
                e.printStackTrace();
            }
               


            // while(Agents!=null){
            //     System.out.println(Agent);
            //     //Lecture du fichier agent on recupere le nom hé le prenom 
            //     BufferedReader br = new BufferedReader(new FileReader(new File("datas\\staff.txt")));
            //     String Agent = br.readLine();
            //     while(Agent!=null){
                    
            //     }

            // }
    }
}
