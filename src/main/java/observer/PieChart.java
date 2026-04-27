package observer;

import utils.SvgExporter;

import java.util.Locale;
import java.util.Map;

public class PieChart implements Observer {
    private final String fileName;
    private final SvgExporter exporter;
    private final String[] colors = {
            "red",
            "green",
            "blue",
            "orange",
            "purple",
            "cyan",
            "magenta",
            "yellow"
    };

    public PieChart(SvgExporter exporter, String fileName) {
        this.exporter = exporter;
        this.fileName = fileName;
    }

    @Override
    public void update(Map<String, Integer> data) {
        String svg = draw(data);
        exporter.export(fileName, svg);
    }

    private String draw(Map<String, Integer> data) {
        int cx = 100, cy = 100, r = 80;
        double currentAngle = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("<svg " +
                "width='200' " +
                "height='200' " +
                "xmlns='http://www.w3.org/2000/svg'>\n");

        int i = 0;
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String name = entry.getKey();
            int value = entry.getValue();

            double sliceAngle = (value / 100.0) * 360;
            double x1 = cx + r * Math.cos(Math.toRadians(currentAngle));
            double y1 = cy + r * Math.sin(Math.toRadians(currentAngle));

            currentAngle += sliceAngle;
            double x2 = cx + r * Math.cos(Math.toRadians(currentAngle));
            double y2 = cy + r * Math.sin(Math.toRadians(currentAngle));

            int largeArcFlag = sliceAngle > 180 ? 1 : 0;

            String color = colors[i % colors.length];

            String pathData = String.format(Locale.US,
                    "M %d %d L %.2f %.2f A %d %d 0 %d 1 %.2f %.2f Z",
                    cx, cy, x1, y1, r, r, largeArcFlag, x2, y2);

            sb.append(String.format("<path " +
                    "d='%s' " +
                    "fill='%s' />\n", pathData, color));

            double midAngle = currentAngle - (sliceAngle / 2.0);
            double labelRadius = r * 0.7;
            double tx = cx + labelRadius * Math.cos(Math.toRadians(midAngle));
            double ty = cy + labelRadius * Math.sin(Math.toRadians(midAngle));

            sb.append(String.format(Locale.US,
                    "<text " +
                            "x='%.2f' " +
                            "y='%.2f' " +
                            "font-family='Arial' " +
                            "font-size='14' " +
                            "text-anchor='middle' " +
                            "dominant-baseline='central' " +
                            "fill='black'>%s</text>\n",
                    tx, ty, name));

            i++;
        }

        sb.append("</svg>");
        return sb.toString();
    }
}
