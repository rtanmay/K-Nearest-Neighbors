import java.util.*;
import java.lang.Math;
import java.util.Map.Entry;

public class KNN
{
	/*
	 *Dataset- 20 datapoints, each datapoint has 3 attributes (Modified IRIS Dataset)
	 */
	static double[] att1 = {5.1, 4.9, 4.7, 4.6, 5.0, 5.4,4.6,5.0,4.4,4.9, 7.0,6.4,6.9,5.5,6.5,5.7,6.3,4.9,6.6,5.2};
	static double[] att2 = {1.4, 1.4, 1.3, 1.5, 1.4, 1.7,1.4,1.5,1.4,1.5, 4.7,4.5,4.9,4.0,4.6,4.5,4.7,3.3,4.6,3.9}; 
	static String[] att3 = {"orange","orange","orange","orange","orange","orange","orange","orange","orange","orange","yellow","yellow","yellow","yellow","yellow","yellow","yellow","yellow","yellow","yellow"};
	static int[] cls  = {1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2}; //This is class
	static int k,dval;
	static double a1,a2;
	static String a3;
	static double[] d1= new double[20];
	static double[] d2= new double[20];
	static int[] d3= new int[20];
	static double[] finald=new double[20];
	static int[] countclass=new int[2];
	static int finalclass;
	static HashMap<Double, Integer> hm= new HashMap<Double, Integer>();

	public static void takeinput()
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter the value of k you wish to take in KNN(0 for default): ");
		k=sc.nextInt();
		if(k==0)
		{
			k=(int)(Math.sqrt(20));
		}
		//System.out.println("This is k=" + k);
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
			for(int i=0;i<20;i++)
			{
				d1[i]=Math.sqrt( Math.abs(a1*a1 - att1[i]*att1[i]) );
				d2[i]=Math.sqrt( Math.abs(a2*a2 - att2[i]*att2[i]) );
			}
		}
		else //Manhattan distance
		{
			for(int i=0;i<20;i++)
			{
				d1[i]=Math.abs(a1-att1[i]);
				d2[i]=Math.abs(a2-att2[i]);
			}
		}

		//Hamming distance
		for(int i=0;i<20;i++)
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
		for(int i=0;i<20;i++)
		{
			finald[i]=d1[i]+d2[i]+d3[i];
		}
		//Storing in Hashmap
		for(int i=0;i<20;i++)
		{
			hm.put(finald[i],cls[i]);
		}
		TreeMap<Double,Integer> sorted= new TreeMap<>(hm);
		Set<Entry<Double, Integer> > aftersort= sorted.entrySet();

		int i=0;
		for(Entry<Double,Integer> x:aftersort)
		{
			if(i==k) break;
			countclass[x.getValue()-1]++;
			i++;
		}
		//System.out.println("This is countclass[0]=" + countclass[0]);
		//System.out.println("This is countclass[1]=" + countclass[1]);
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
