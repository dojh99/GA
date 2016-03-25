import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Gene extends GeneTemplate<Integer> {
    static private int maxSize = 100;
    static private int maxValue = 100;
    static private int minValue = 0;

    static public Random rand = new Random();

    static public int n_pointCross = 5; //1 ~ maxSize-1

    ArrayList<Integer> crossPoint = new ArrayList<Integer>();


    @Override
    public boolean mutate(double permil) {
        // TODO: Implement this method
        boolean mutated = (Math.random() * 1000) < permil;

        if (mutated) {
            int point = rand.nextInt(maxSize);
            int mutatednum = rand.nextInt(maxValue - minValue + 1) + minValue;
            this.set(point, mutatednum);
        }

        return mutated;
    }

    @Override
    public GeneTemplate<Integer> crossOver(GeneTemplate<Integer> others) {
        Gene crossed = new Gene();
        ArrayList<Integer> pointTemp = new ArrayList<Integer>(n_pointCross);

        int i;

        for (i = 0; i < n_pointCross; i++) {
            Integer point = rand.nextInt(maxSize - 1) + 1;

            while (pointTemp.contains(point)) {
                point = rand.nextInt(maxSize - 1) + 1;
            }
            pointTemp.add(point);
        }
        Collections.sort(pointTemp);


        boolean toggle = true;
        int k = 0;

        for (i = 0; i < n_pointCross; i++) {

            while (k < pointTemp.get(i)) {
                Integer data = toggle ? this.get(k) : others.get(k);
                crossed.add(data);
                k++;
            }
            toggle = !toggle;
        }

        while (k < maxSize) {
            Integer data = toggle ? this.get(k) : others.get(k);
            crossed.add(data);
            k++;
        }

        crossed.crossPoint = pointTemp;

        return crossed;
    }

    @Override
    public void scoring() {
        scoring_test1();
    }

    @Override
    public void autoInit() {
        int i;
        final int k = maxSize;

        for (i = 0; i < k; i++) {
            int n = rand.nextInt(maxValue - minValue + 1) + minValue;
            this.add(i, n);
        }
    }

    private void scoring_test1() {
        int score = 0;
        int k = 1;
        for (Integer i : this) {
            if (i.intValue() == k)
                score++;
            k++;
        }
        this.fitness = score;

    }

    @Override
    public boolean add(Integer object) {
        // TODO: Implement this method
        if (this.size() < maxSize)
            return super.add(object);

        return false;
    }

    @Override
    public String toString() {
        // TODO: Implement this method
        return "Fitness : " + fitness + " Values : " + super.toString() + "  lenth : " + this.size() + " CPOINT :" + crossPoint.toString();

    }

}
