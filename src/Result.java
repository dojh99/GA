

public class Result
{
	private int fitness;
	private int generation;
	private Gene bestData;
	
	public Result(int fitness,int generation,Gene bestData){
		this.fitness=fitness;
		this.generation = generation;
		this.bestData = bestData;
	}

	public void setFitness(int fitness){
		this.fitness = fitness;
	}

	public int getFitness(){
		return fitness;
	}

	public void setGeneration(int generation){
		this.generation = generation;
	}

	public int getGeneration(){
		return generation;
	}

	public void setBestData(Gene bestData){
		this.bestData = bestData;
	}

	public Gene getBestData(){
		return bestData;
	}
	
	public void print(){
		System.out.println("Generation : " + generation);
		System.out.println("BestData.toString :"+bestData.toString());
		System.out.println("BestFitness : " + bestData.fitness);
	}
}
