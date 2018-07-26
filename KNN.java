import java.util.*;
import java.lang.Math;
import java.util.Map.Entry;

public class KNN
{
	/*
	 *Dataset- 5 datapoints, each datapoint has 3 attributes
	 */
	static double[] att1 = {2.2, 3.3, 4, 8.9, 2.5};
	static double[] att2 = {3.2, 4.3, 5.2, 6.2 ,7.4}; 
	static String[] att3 = {"red", "orange", "yellow", "orange", "orange"};
	static int[] cls  = {1, 2, 1, 2, 2}; //This is class
	static int k,dval;
	static double a1,a2;
	static String a3;
	static double[] d1= new double[5];
	static double[] d2= new double[5];
	static int[] d3= new int[5];
	static double[] finald=new double[5];
	static int[] countclass=new int[2];
	static int finalclass;
	static HashMap<Double, Integer> hm= new HashMap<Double, Integer>();

	public static void takeinput()
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter the value of k you wish to take in KNN: ");
		k=sc.nextInt();
		System.out.println("Enter value of attribute 1: ");
		a1=sc.nextDouble();
		System.out.println("Enter value of attribute 2: ");
		a2=sc.nextDouble();
		System.out.println("Enter value of attribute 3: ");
		a3=sc.next();
		System.out.println("Enter the type of Distance Function you wish to choose for continous attribute variable (1=Euclidean,2=Manhattan): ");
		dval=sc.nextInt();
	}

	public static void calcdistance()
	{
		if(dval==1) //Euclidean distance
		{
			for(int i=0;i<5;i++)
			{
				d1[i]=Math.sqrt( Math.abs(a1*a1 - att1[i]*att1[i]) );
				d2[i]=Math.sqrt( Math.abs(a2*a2 - att2[i]*att2[i]) );
			}
		}
		else //Manhattan distance
		{
			for(int i=0;i<5;i++)
			{
				d1[i]=Math.abs(a1-att1[i]);
				d2[i]=Math.abs(a2-att2[i]);
			}
		}

		//Hamming distance
		for(int i=0;i<5;i++)
		{
			int count=0;
			for(int j=0;j<Math.min(att3[i].length(),a3.length());j++)
			{
				if(att3[i].charAt(j)!=a3.charAt(j))
				{
					count++;
				}
			}
			d3[i]=count;
		}
	}

	public static void selectk()
	{
		for(int i=0;i<5;i++)
		{
			finald[i]=d1[i]+d2[i]+d3[i];
		}
		//Storing in Hashmap
		for(int i=0;i<5;i++)
		{
			hm.put(finald[i],cls[i]);
		}
		TreeMap<Double,Integer> sorted= new TreeMap<>(hm);
		Set<Entry<Double, Integer> > aftersort= sorted.entrySet();

		for(Entry<Double,Integer> x:aftersort)
		{
			countclass[x.getValue()-1]++;
		}
		if(countclass[0]>=countclass[1])
		{
			finalclass=1;
		}
		else
		{
			finalclass=2;
		}
	}

	public static void result()
	{
		System.out.println("The class of the input data point is: " + finalclass);
		System.out.println("Program End!\n");
	}

	public static void main(String[] args)
	{
		//API
		takeinput();
		calcdistance();
		selectk();
		result();
	}
}