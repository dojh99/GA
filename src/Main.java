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
				System.out.println("Gen : "+generation);
				System.out.println("Avr : "+average);
				System.out.println("Best : "+bestFitness);
			}

			@Override
			public void finished(Result result_Temp){
				// TODO: Implement this method
				result.add(result_Temp);
				result_Temp.print();
			}
		};
				
		int i;
		for( i=0;i<10;i++)
		R_SN_PN(500,5);
		
		printAllResults();
		
	}
	

	
	final static private void R_SN_PN(int population,int numOfSurvival){
		GA genetocAl = new GA(numOfSurvival,population,0);
		genetocAl.setListener(lis);
		genetocAl.work();
	}
	
	final static private void TEST_A(){
		/* Relationship between mutate probability and generation
		 * from 10 power -11 to 10 power 100 percent (횞10)
		 * and from 10 power -3 to 100 (25*n)
		 */
		  double mutateProbab =0;
		  int power = 2;
		  
		  for(power=0;power<3;power++){
			  mutateProbab= Math.pow(10,power);
			  GA ga = new GA(10,1000,mutateProbab);
			  ga.setListener(lis);
			  ga.work();
		  }
		 
		 
	}
	
	final static private void Temp(){
		double mutateProbab= Math.pow(10,2);
		System.out.println(mutateProbab);

		int i =0;

		while(i<50){
			GA ga = new GA(100,1000,mutateProbab);
			ga.setListener(lis);
			ga.work();
			i++;
			}
			
		float avr =0;
		int k=0;

		while(k<result.size()){
			avr+=result.get(k).getGeneration();
			k++;
		}

		System.out.println( avr/result.size());
	}
	
	static private void printAllResults(){
		int i=0;
		int size = result.size();
		double avr =0;
		
		while(i<size){
			avr+=result.get(i).getGeneration();
			System.out.println(i+"  :  " + result.get(i).getGeneration());
			i++;
		}
		System.out.println("avr :" +avr/size);
	}
	
}
