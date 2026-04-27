public class Application {
    private final Model model;

    public Application() {
        this.model = new Model();
    }

    public void start() {
        ObserverInterface pieChart = new PieChartView(model);
        TableView tableView = new TableView(model);
        BarChatView barChatView = new BarChatView(model);

        model.unsubscribe(tableView);
        model.setData(20,20,60);
    }
}
