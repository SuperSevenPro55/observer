package application;

import observer.*;
import utils.SvgExporter;

import java.util.Map;

public class Application {
    private final Model model;

    public Application() {
        this.model = new Model();
    }

    public void start() {
        SvgExporter exporter = new SvgExporter();

        Observer pieChart = new PieChart(exporter, "PieChart.svg");
        Observer tableView = new TableChart(exporter, "TableChart.svg");
        Observer columnChart = new ColumnChart(exporter, "ColumnChart.svg");

        model.subscribe(pieChart);
        model.subscribe(tableView);
        model.subscribe(columnChart);

        model.unsubscribe(tableView);

        model.setData(Map.of(
                "A", 20,
                "B", 20,
                "C", 30,
                "D", 20,
                "E", 10
        ));
    }
}
