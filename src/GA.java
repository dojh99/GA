import java.util.ArrayList;
import java.util.Random;

public class GA {
    Population<Gene> population;
    Gene bestData;
    int generation;
    int n_survival;
    int n_population;

    Population<Gene> populationT;

    double mutateProbab;

    GAListener listener;

    int endCondition = 100;

    public GA(int n_population,int n_survival, double mutateProbab) {
        init(new Population<Gene>(), n_survival, n_population, mutateProbab);
    }

    public GA(Population<Gene> population, int n_population,int n_survival, double mutateProbab) {
        init(population, n_survival, n_population, mutateProbab);
    }


    public void init(Population<Gene> population, int n_population,int n_survival, double mutateProbab) {
        this.population = population;
        this.n_survival = n_survival;
        this.n_population = n_population;
        this.mutateProbab = mutateProbab;
    }

    public void work() {
        populationInit();
        population.scoreAllEntities();
        population.sortEntities();
        isBest(population.get(0));
        listener.AGenerationEnded(generation,population, population.getFitnessAvr(), population.getBestFitness());
        generation++;

        while (!endConditionMeets()) {
            aGenerationWork();
        }

        listener.finished(new Result(bestData.fitness, generation, bestData));

    }

    public void aGenerationWork() {
        makeNextGen();
        population.scoreAllEntities();
        population.sortEntities();
        isBest(population.get(0));
        listener.AGenerationEnded(generation,population, population.getFitnessAvr(), population.getBestFitness());
        generation++;
    }


    public void populationInit() {
        int i;

        for (i = 0; i < n_population; i++) {
            Gene gene = new Gene();
            gene.autoInit();
            population.add(gene);
        }

        generation++;
    }

    private void makeNextGen() {
        ArrayList<Gene> survivals = population.getTopN(n_survival);
        int i = 0;
        Random rand = new Random();

        population.clear();

        while (i < n_population) {
            int i1 = rand.nextInt(n_survival);
            int i2 = rand.nextInt(n_survival);

            while (i1 == i2) {
                i2 = rand.nextInt(n_survival);
            }
            Gene data = (Gene) survivals.get(i1).crossOver(survivals.get(i2));
            data.mutate(mutateProbab);
            population.add(data);
            i++;
        }

    }

    private boolean isBest(Gene gene) {
        if (bestData == null)
            bestData = gene;
        if (gene.compareTo(bestData) == -1) {
            bestData = gene;
            return true;
        }
        return false;
    }

    private boolean endConditionMeets() {
        return (bestData.fitness >= endCondition);
    }

    private boolean endConditionMeetsAsGen() {
        return (generation >= endCondition);
    }

    private boolean endConditionAsZCH() {
        return (population.equals(populationT));
    }

    private void printFinalResult() {
        System.out.println("Generation : " + (generation - 1));
        System.out.println("BestData.toString :" + bestData.toString());
        System.out.println("BestFitness : " + bestData.fitness);
    }

    public void setListener(GAListener listener) {
        this.listener = listener;
    }

    public GAListener getListener() {
        return listener;
    }
}
