import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;


public class HtmlRead {

   /* public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }
*/
    public HtmlRead() {

        try {
            URL url = new URL(SwingControlDemo.getLinkInput());
            String keyword = SwingControlDemo.getKeywordInput();
            //System.out.println(url + "," + keyword);

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
            SwingControlDemo.errorMessage();
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
        String substring = line.substring(start, start + end);
        if (substring.toLowerCase().contains(keyword.toLowerCase())) {
            SwingControlDemo.writeToOutput(substring);
        }
       // System.out.println(line.substring(start+1));
        //test for multiple links in the line
        if (line.substring(start).contains("href")) {
            //System.out.println("contains nested links");
            pullLink(line.substring(start), keyword);
        }
    }
    public void pullImage(String line) {
        int start = (line.indexOf("src="))+5;
        String link = line.substring(start);

        int end = link.indexOf("\"");
        if (end==-1) {
            end = link.indexOf("'");
        } if (end==-1) {
            end = link.indexOf("--");
        }
        String substring = line.substring(start, start + end);
        System.out.println(substring);
    }


}