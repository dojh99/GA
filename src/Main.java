import java.util.ArrayList;

public class Main {
    static ArrayList<Result> result;
    static GAListener lis;
    static int[] n_survivals ={5,10,25,50,100,150,200,250,300,350,400,450,500};

    public static void main(String[] args) {
        result = new ArrayList<Result>();
        lis = new GAListener() {

            @Override
            public void AGenerationEnded(int generation,Population population, double average, double bestFitness) {
                // TODO: Implement this method
            }

            @Override
            public void finished(Result result_Temp) {
                // TODO: Implement this method
                result.add(result_Temp);
                result_Temp.print();
            }
        };

        for(int n_sur:n_survivals){

            for (int i = 0; i < 10; i++)
                R_SN_PN(500,n_sur);
            printAllResults();
            result.clear();
        }

    }

    private static void R_SN_PN(int population, int numOfSurvival) {
        /* 1퍼센트의 확률로 돌연변이를 발생시켜
           population 만큼의 개체들 속에서 상위 numOfSurvival 만큼의 개체들을 선별한다.
         */
        GA genetocAl = new GA(population,numOfSurvival, 10);
        genetocAl.setListener(lis);
        genetocAl.work();
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
