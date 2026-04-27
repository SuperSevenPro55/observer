package observer;

import utils.SvgExporter;

import java.util.Map;


public class TableChart implements Observer {
    private final String fileName;
    private final SvgExporter exporter;

    public TableChart(SvgExporter exporter, String fileName) {
        this.exporter = exporter;
        this.fileName = fileName;
    }

    @Override
    public void update(Map<String, Integer> data) {
        String svg = draw(data);
        exporter.export(fileName, svg);
    }

    private String draw(Map<String, Integer> data) {
        StringBuilder sb = new StringBuilder();
        int cellWidth = 66;
        int width = data.size() * cellWidth;

        sb.append(String.format("<svg " +
                "width='%d' " +
                "height='100' " +
                "xmlns='http://www.w3.org/2000/svg' " +
                "style='border:1px solid black'>\n", width));

        int x = 0;

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String name = entry.getKey();
            int value = entry.getValue();

            sb.append(String.format("<rect " +
                    "x='%d' " +
                    "y='0' " +
                    "width='%d' " +
                    "height='30' " +
                    "fill='#eee' " +
                    "stroke='black'/>\n", x, cellWidth));

            sb.append(String.format("<text " +
                    "x='%d' " +
                    "y='20' " +
                    "font-family='Arial'>%s</text>\n", x + 25, name));

            sb.append(String.format("<rect " +
                    "x='%d' " +
                    "y='30' " +
                    "width='%d' " +
                    "height='30' " +
                    "fill='white' " +
                    "stroke='black'/>\n", x, cellWidth));

            sb.append(String.format("<text " +
                    "x='%d' " +
                    "y='50' " +
                    "font-family='Arial'>%d%%</text>\n", x + 15, value));

            x += cellWidth;
        }

        sb.append("</svg>");
        return sb.toString();
    }
}
