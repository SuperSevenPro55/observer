public class Main {
    static void main(String[] args){
        Model model = new Model();
        model.setA(10);
        model.setB(10);
        model.setC(10);
        TableView obTV = new TableView(model);
        obTV.update();

        BarChatView obBC = new BarChatView(model);
        model.setA(5);
        obBC.update();

        PieChartView obPC = new PieChartView(model);
        obPC.update();
    }
}
