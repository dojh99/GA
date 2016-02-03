import java.util.ArrayList;

public class Main {
    static ArrayList<Result> result;
    static GAListener lis;
    static int[] probabilities ={50,100,200,300,400,500,600,700,800};

    public static void main(String[] args) {
        result = new ArrayList<Result>();
        lis = new GAListener() {

            @Override
            public void AGenerationEnded(int generation,Population population, double average, double bestFitness) {
                // TODO: Implement this method

              /* Removed useless outputs

                System.out.println("Gen : " + generation);
                System.out.println("Avr : " + average);
                System.out.println("Best : " + bestFitness);*/
            }

            @Override
            public void finished(Result result_Temp) {
                // TODO: Implement this method
                result.add(result_Temp);
                result_Temp.print();
            }
        };

        for(int prob:probabilities){
            /* Do this for 10 times */

            for (int i = 0; i < 10; i++)
                R_SN_PN(prob, 5);
            printAllResults();
            result.clear();
        }

    }


    private static void R_SN_PN(int population, int numOfSurvival) {
        GA genetocAl = new GA(numOfSurvival, population, 100);
        genetocAl.setListener(lis);
        genetocAl.work();
    }

     private static void TEST_A() {

        /* Relationship between mutate probability and generation
		 * from 10 power -11 to 10 power 100 percent (횞10)
		 * and from 10 power -3 to 100 (25*n)
		 */
        double mutateProbab = 0;
        int power = 2;

        for (power = 0; power < 3; power++) {
            mutateProbab = Math.pow(10, power);
            GA ga = new GA(10, 1000, mutateProbab);
            ga.setListener(lis);
            ga.work();
        }


    }

     private static void Temp() {
        double mutateProbab = Math.pow(10, 2);
        System.out.println(mutateProbab);

        int i = 0;

        while (i < 50) {
            GA ga = new GA(100, 1000, mutateProbab);
            ga.setListener(lis);
            ga.work();
            i++;
        }

        float avr = 0;
        int k = 0;

        while (k < result.size()) {
            avr += result.get(k).getGeneration();
            k++;
        }

        System.out.println(avr / result.size());
    }

    private static void printAllResults() {
        int i = 0;
        int size = result.size();
        double avr = 0;

        while (i < size) {
            avr += result.get(i).getGeneration();
            System.out.println(i + "  :  " + result.get(i).getGeneration());
            i++;
        }
        System.out.println("avr :" + avr / size);
    }

}
