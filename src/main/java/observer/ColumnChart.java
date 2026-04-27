package observer;

import utils.SvgExporter;

import java.util.Map;

public class ColumnChart implements Observer {
    private final String fileName;
    private final SvgExporter exporter;

    public ColumnChart(SvgExporter exporter, String fileName){
        this.exporter = exporter;
        this.fileName = fileName;
    }

    @Override
    public void update(Map<String, Integer> data){
        String svg = draw(data);
        exporter.export(fileName, svg);
    }

    private String draw(Map<String, Integer> data){
        StringBuilder sb = new StringBuilder();

        int width = Math.max(200, data.size() * 50 + 50);

        sb.append(String.format("<svg " +
                "width='%d' " +
                "height='100' " +
                "xmlns='http://www.w3.org/2000/svg' " +
                "style='border:1px solid black'>\n", width));

        sb.append(String.format("<rect " +
                "x='0' " +
                "y='0' " +
                "width='%d' " +
                "height='100' " +
                "fill='white' " +
                "stroke='white'/>\n", width));

        int x = 10;

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String name = entry.getKey();
            int value = entry.getValue();
            int y = 90 - value;

            sb.append(String.format("<rect " +
                    "x='%d' " +
                    "y='%d' " +
                    "width='20' " +
                    "height='%d%%' " +
                    "fill='black' " +
                    "stroke='black'/>\n", x, y, value));

            sb.append(String.format("<text " +
                    "x='%d' " +
                    "y='%d' " +
                    "font-family='Arial' " +
                    "font-size='10'>%s: %d%%</text>\n", x, y - 5, name, value));

            x += 40;
        }

        sb.append("</svg>");
        return sb.toString();
    }
}
