import java.util.ArrayList;

abstract public class GeneTemplate<T> extends ArrayList<T> implements Comparable<GeneTemplate<T>> {
    int fitness;

    @Override
    public int compareTo(GeneTemplate<T> other) {
        // TODO: Implement this method
        // when asnd : return -1 is better

        if (this.fitness > other.fitness)
            return -1;

        else if (this.fitness == other.fitness)
            return 0;

        return 1;
    }

    abstract public void scoring();

    abstract public void autoInit();

    abstract public boolean mutate(double permil);

    abstract public GeneTemplate<T> crossOver(GeneTemplate<T> others);


    public int getFitness() {
        return fitness;
    }


}
