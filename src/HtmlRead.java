import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class HtmlRead {

    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {

        try {
            Scanner scan = new Scanner(System.in);

            System.out.println();
            System.out.print("enter URL:");
            URL url = new URL(scan.nextLine());
            System.out.println("enter keyword:");
            String keyword = scan.nextLine();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String line;
            while ( (line = reader.readLine()) != null ) {
                if(line.contains("href")) {
                    pullLink(line, keyword);



                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public void pullLink(String line, String keyword) {
        int start = (line.indexOf("href"))+6;
        String link = line.substring(start);

        //check for hrefs that start with //
        while (link.charAt(0)=='/') {
            start++;
            link = line.substring(start);
        }

        int end = link.indexOf("\"");
        if (end==-1) {
            end = link.indexOf("'");
        } if (end==-1) {
            end = link.indexOf("--");
        }
        // System.out.println(start + "," + end);
        //System.out.println("normal line:" + line);
        if (line.substring(start, start+end).contains(keyword)) {
            System.out.println(line.substring(start, start + end));
        }
       // System.out.println(line.substring(start+1));
        //test for multiple links in the line
        if (line.substring(start).contains("href")) {
            //System.out.println("contains nested links");
            pullLink(line.substring(start), keyword);
        }
    }
}