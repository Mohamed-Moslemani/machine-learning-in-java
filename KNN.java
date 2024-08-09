
import java.util.*;
public class KNN {
   
public static void main(String args[])
{
}

public double euclidean_Distance(double[] x1, double[] x2)
{
    double distance = 0;

    for(int i = 0; i < x1.length; i++)
    {
        distance += Math.pow(x1[i] - x2[i], 2);
    }

    return distance;
}

public double manhattan_Distance(double[] x1, double[] x2)
{
    double distance = 0;

    for(int i = 0; i < x1.length; i++)
    {
        distance += Math.abs(x1[i] - x2[i]);
    }

    return distance;
}

public double[] get_neighboring_k_els(double[] x_train,double[] y_train,double[] x_test,int k)
{
    double[] distances = new double[x_train.length];
    for(int i = 0; i < x_train.length; i++)
    {
        distances[i] = euclidean_Distance(x_train, x_test);
    }
    return distances;
}

public double get_most_common(double[] arr)
{
    double max = 0;
    double most_common = 0;
    for(int i = 0; i < arr.length; i++)
    {
        int count = 0;
        for(int j = 0; j < arr.length; j++)
        {
            if(arr[i] == arr[j])
            {
                count++;
            }
        }
        if(count > max)
        {
            max = count;
            most_common = arr[i];
        }
    }
    return most_common;
}
public double[] predict_clasification(double[] x_train,double[] y_train, double x_test, int k)
{
    double[] predictions = new double[x_train.length];
    for(int i = 0; i < x_train.length; i++)
    {
        double[] distances = get_neighboring_k_els(x_train, y_train, x_test, k);
        double[] sorted_distances = distances;
        Arrays.sort(sorted_distances);
        double[] k_nearest = Arrays.copyOfRange(sorted_distances, 0, k);
        double[] k_nearest_labels = new double[k];
        for(int j = 0; j < k; j++)
        {
            k_nearest_labels[j] = y_train[distances.indexOf(k_nearest[j])];
        }
        predictions[i] = get_most_common(k_nearest_labels);
    }
}

}
