import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class BarChatView implements ObserverInterface {
    private final Path path = Path.of("BarChatView.svg");
    private final Model model;

    BarChatView(Model model){
        this.model = model;
    }

    @Override
    public void update(){
        int a = model.getA();
        int b = model.getB();
        int c = model.getC();
        writeFile(a,b,c);
    }
    private void writeFile(int a, int b, int c){
        String svg = getBarChatSVG(a,b,c);
        try{
            Files.writeString(path,
                    svg,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    private String getBarChatSVG(int a, int b, int c){
        //a = 50
        // y = пол (90) - высота(a) = 40
        // height = a
        return String.format(
                "<svg width='200' height='100' xmlns='http://www.w3.org/2000/svg'  style='border:1px solid black'>\n"+
                        "\n" +
                        "<rect x='0' y='0' width='200' height='100' fill='white' stroke='white'/>\n"+
                        "\n" +
                        "<rect x='0' y='%d' width='20' height='%d%%' fill='black' stroke='black'/>\n"+
                        "\n" +
                        "<rect x='40' y='%d' width='20' height='%d%%' fill='black' stroke='black'/>\n" +
                        "\n" +
                        "<rect x='80' y='%d' width='20' height='%d%%' fill='black' stroke='black'/>\n"+
                        "\n"+
                        "<text x='0' y='%d' font-family='Arial'>%d%%</text>\n"+
                        "\n" +
                        "<text x='40' y='%d' font-family='Arial'>%d%%</text>\n"+
                        "\n" +
                        "<text x='80' y='%d' font-family='Arial'>%d%%</text>\n" +
                        "</svg>",(90-a), a, (90-b),b,(90-c),c,(90-a-5), a, (90-b-5),b,(90-c-5),c);

    }


}
