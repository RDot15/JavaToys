import java.text.DecimalFormat;
import java.util.ArrayList;


public class ControllerToys {
    private ArrayList<Toys> lottery;

    public ControllerToys(ArrayList<Toys> list){
        lottery = new ArrayList<>(list);
    }

    public Toys LotteryTime(){
        double totalWeight = 0;
        for (Toys toys : lottery) {
            totalWeight = totalWeight + (toys.getCount() * toys.getWeight());
        }
        int randomNumber = (int) (Math.random() * totalWeight);
        // Определяем случайную игрушку на основе веса
        int cumulativeWeight = 0;
        Toys temp = null;
        for (Toys toy : lottery) {
            cumulativeWeight += toy.getWeight() * toy.getCount();
            if (randomNumber < cumulativeWeight) {
                String formattedDouble = new DecimalFormat("#0.00").format((toy.getWeight()/totalWeight)*100);
                System.out.println(" Шанс на победу был "+ formattedDouble + "%");
                temp = new Toys(toy.getId(), toy.getName(), 1, toy.getWeight());
                toy.setCount(toy.getCount() - 1);
                break;
            }
        }
        if(temp.getCount() == 0){
            lottery.remove(temp.getId());
        }
        return temp;
    }

    public ArrayList<Toys> print(){
        return lottery;
    }
}