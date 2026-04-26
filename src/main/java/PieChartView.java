import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Locale;

public class PieChartView implements ObserverInterface {

    private final Path path = Path.of("PieChartView.svg");
    private final Model model;

    PieChartView(Model model){
        this.model = model;
        model.subscribe(this);
    }
    @Override
    public void update() {
        int a = model.getA();
        int b = model.getB();
        int c = model.getC();
        writeFile(a,b,c);
    }

    private void writeFile(int a, int b, int c) {

        String svg = getPieChart(a,b,c);
        try {
            Files.writeString(path,
                    svg,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getPieChart(int a, int b, int c) {
        int cx = 100, cy = 100, r = 80;
        double currentAngle = 0;
        int[] values = {a, b, c};
        String[] names = {"a","b","c"};
        String[] colors = {"red", "green", "blue"};

        StringBuilder sb = new StringBuilder();
        sb.append("<svg width='200' height='200' xmlns='http://www.w3.org/2000/svg'>");

        for (int i = 0; i < values.length; i++) {
            // Вычисляем долю сектора в градусах (сумма a+b+c должна быть 100)
            double sliceAngle = (values[i] / 100.0) * 360;

            // Находим координаты начала дуги
            double x1 = cx + r * Math.cos(Math.toRadians(currentAngle));
            double y1 = cy + r * Math.sin(Math.toRadians(currentAngle));

            // Сдвигаем угол и находим конец дуги
            currentAngle += sliceAngle;
            double x2 = cx + r * Math.cos(Math.toRadians(currentAngle));
            double y2 = cy + r * Math.sin(Math.toRadians(currentAngle));

            // Флаг большой дуги (нужен, если сектор больше 180 градусов)
            int largeArcFlag = sliceAngle > 180 ? 1 : 0;

            // Собираем строку пути
            String pathData = String.format(Locale.US,
                    "M %d %d L %.2f %.2f A %d %d 0 %d 1 %.2f %.2f Z",
                    cx, cy, x1, y1, r, r, largeArcFlag, x2, y2);

            sb.append(String.format("<path d='%s' fill='%s' />", pathData, colors[i]));


            // Угол середины сектора (currentAngle - это угол, на котором мы закончили дугу)
            double midAngle = currentAngle - (sliceAngle / 2.0);

            // Радиус, на котором будет стоять текст (чуть меньше основного радиуса круга)
            double labelRadius = r * 0.7;

            // Координаты для текста
            double tx = cx + labelRadius * Math.cos(Math.toRadians(midAngle));
            double ty = cy + labelRadius * Math.sin(Math.toRadians(midAngle));

            // Добавляем текст с выравниванием по центру
            sb.append(String.format(java.util.Locale.US,
                    "<text x='%.2f' y='%.2f' font-family='Arial' font-size='14' text-anchor='middle' dominant-baseline='central' fill='black'>%s</text>",
                    tx, ty, names[i]));
        }

        sb.append("</svg>");
        return sb.toString();
    }


}


