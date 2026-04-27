package observer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Model {
    private final ArrayList<Observer> listOb = new ArrayList<>();
    private Map<String, Integer> data = new LinkedHashMap<>();

     public void notifyAllObs(){
        for (Observer ob : listOb){
            ob.update(data);
        }
    }

    public void subscribe(Observer ob){
        listOb.add(ob);

    }
    public void unsubscribe(Observer ob){
        listOb.remove(ob);
    }

    public void setData(Map<String, Integer> newData){
        int sum = newData.values().stream().mapToInt(Integer::intValue).sum();

        if (sum == 100) {
            this.data = new LinkedHashMap<>(newData);
            notifyAllObs();
        } else {
            throw new IllegalArgumentException("Сумма значений не равна 100. Сумма: " + sum);
        }
    }
}
