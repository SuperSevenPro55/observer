import java.util.ArrayList;

public class Observer implements ObserverInterface {
    private ArrayList<ObserverInterface> listOb = new ArrayList<>();

    @Override
    public void update(){
        for (var i : listOb){
            i.update();
        }
    }

    void subscribe(ObserverInterface ob){
        listOb.add(ob);
    }

    void unsubscribe(ObserverInterface ob){
        listOb.remove(ob);
    }

}
