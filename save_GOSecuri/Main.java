package save_GOSecuri;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        File f = new File("source.html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html><body>");
        bw.write("<div>");
        bw.write("<p>OwO</p>");
        bw.write("<p>UwU</p>");
        bw.write("</div>");
        bw.write("</body></html>");
        bw.close();
    }
}