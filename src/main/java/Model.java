import java.util.ArrayList;


public class Model {
    private ArrayList<ObserverInterface> listOb = new ArrayList<>();

    private int a;
    private int b;
    private int c;

     public void notifyAllObs(){
        for (var i : listOb){
            i.update();
        }
    }

    void subscribe(ObserverInterface  ob){
        listOb.add(ob);

    }

    void unsubscribe(ObserverInterface  ob){
        listOb.remove(ob);
    }
    public void setData(int a, int b, int c){
        if ((a + b + c) == 100){
            this.a = a;
            this.b = b;
            this.c = c;
            notifyAllObs();
        }else throw new ArithmeticException("СУММА НЕ РАВНА 100");

    }

    int getA(){
        return a;
    }
    int getB(){
        return b;
    }
    int getC(){
        return c;
    }

    void setA(int a){
         if(a + this.getB() + this.getC() == 100){
             this.a = a;
         }else throw new ArithmeticException("СУММА НЕ РАВНА 100");

    }
    void setB(int b){
        if(this.getA() + b + this.getC() == 100 ){
            this.b = b;
        }else throw new ArithmeticException("СУММА НЕ РАВНА 100");
    }
    void setC(int c){
        if(this.getA() + this.getB() + c == 100){
            this.c = c;
        }else throw new ArithmeticException("СУММА НЕ РАВНА 100");
    }



}


