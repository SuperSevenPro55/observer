import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TableView implements ObserverInterface {

    private Path path = Path.of("TableView.svg");
    private Model model;

    TableView(Model model){
        this.model = model;
    }
    @Override
    public void update() {
        int a = model.getA();
        int b = model.getB();
        int c = model.getC();
        writeFile(a,b,c);
    }

    private void writeFile(int a, int b, int c) {

        String svg = getTableSVG(a,b,c);
        try {
            Files.writeString(path,
                    svg,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String getTableSVG(int a, int b, int c) {

        return String.format(
                "<svg width='200' height='100' xmlns='http://www.w3.org/2000/svg' style='border:1px solid black'>\n" +
                        "  \n" +
                        "  <rect x='0' y='0' width='66' height='30' fill='#eee' stroke='black'/>\n" +
                        "  <rect x='66' y='0' width='66' height='30' fill='#eee' stroke='black'/>\n" +
                        "  <rect x='132' y='0' width='68' height='30' fill='#eee' stroke='black'/>\n" +
                        "  <text x='25' y='20' font-family='Arial'>a</text>\n" +
                        "  <text x='91' y='20' font-family='Arial'>b</text>\n" +
                        "  <text x='157' y='20' font-family='Arial'>c</text>\n" +
                        "  \n" +
                        "  <rect x='0' y='30' width='66' height='30' fill='white' stroke='black'/>\n" +
                        "  <rect x='66' y='30' width='66' height='30' fill='white' stroke='black'/>\n" +
                        "  <rect x='132' y='30' width='68' height='30' fill='white' stroke='black'/>\n" +
                        "  <text x='15' y='50' font-family='Arial'>%d%%</text>\n" +
                        "  <text x='81' y='50' font-family='Arial'>%d%%</text>\n" +
                        "  <text x='147' y='50' font-family='Arial'>%d%%</text>\n" +
                        "</svg>", a, b, c);

    }


    }


