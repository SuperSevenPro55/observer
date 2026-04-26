public class Main {
    static void main(String[] args){
        Model model = new Model();
        ObserverInterface pieChart = new PieChartView(model);
        TableView tableView = new TableView(model);
        BarChatView barChatView = new BarChatView(model);

       model.unsubscribe(tableView);
       model.setData(20,20,60);
    }
}
