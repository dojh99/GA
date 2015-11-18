import java.util.*;

public class Main
{
	static ArrayList<Result> result;
	static GAListener lis ;
	
	public static void main(String[] args)
	{
		result = new ArrayList<Result>();
		lis = new GAListener(){

			@Override
			public void AGenerationEnded(int generation,double average,double bestFitness){
				// TODO: Implement this method
				/*System.out.println("Gen : "+generation);
				 System.out.println("Avr : "+average);

				 System.out.println("Best : "+bestFitness);*/
			}

			@Override
			public void finished(Result result){
				// TODO: Implement this method
			}


		};
		
		double mutateProbab= Math.pow(10,2);
		System.out.println(mutateProbab);
		
		int i =0;
		
		while(i<50){
		GA ga = new GA(10,1000,mutateProbab);
		Result resultTemp;
		ga.setListener(lis);
		resultTemp=ga.work();
		resultTemp.print();
		result.add(resultTemp);
		i++;
		}
		//TEST_A();
		
		float avr =0;
		int k=0;

		while(k<result.size()){
			avr+=result.get(k).getGeneration();
			k++;
		}

		System.out.println( avr/result.size());
	}
	
	final static private void TEST_A(){
		/* Relationship between mutate probability and generation
		 * from 10 power -11 to 10 power 100 percent (×10)
		 * and from 10 power -3 to 100 (25*n)
		 */
		  double mutateProbab =0;
		  int power = 0;
		  
		  for(power=-3;power<=2;power++){
			  mutateProbab= Math.pow(10,power);
			  GA ga = new GA(10,1000,mutateProbab*10);
			  ga.work().print();
		  }
		 
		 
	}
	
}
