package save_GOSecuri;
import java.awt.Desktop;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        File f = new File("source.html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        System.out.println("Mdr ça ne fonctionne pas");
        bw.write("<html><body>");
        bw.write("<div>");
        bw.write("<p>OwO</p>");
        bw.write("<p>UwU</p>");
        bw.write("</div>");
        bw.write("</body></html>");
        bw.close(); //test
    }
}
