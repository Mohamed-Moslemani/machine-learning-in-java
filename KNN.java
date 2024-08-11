import java.util.*;

public class KNN {
    
    public static void main(String[] args) {
        double[][] xT = {
            {1, 2}, {2, 3}, {3, 4}, {6, 5}, {7, 8}
        };
        double[] yT = {0, 0, 0, 1, 1};
        
        double[][] xTest = {
            {2, 2}, {4, 4}, {5, 5}, {6, 7}, {7, 9}
        };
        double[] yTest = {0, 0, 1, 1, 1};
        
        int k = 3;

        KNN knn=new KNN();
        double[] yP=knn.pred(xT,yT,xTest,k);
        double acc=knn.calcAcc(yTest,yP);
        System.out.println("Acc:"+acc);
    }

    public double eucDist(double[] x1,double[] x2){
        double dist=0;
        for(int i=0;i<x1.length;i++){
            dist+=Math.pow(x1[i]-x2[i],2);
        }
        return Math.sqrt(dist);
    }

    public double[] getNeigh(double[][] xT,double[] yT,double[] xTest,int k){
        double[][] distArr=new double[xT.length][2];
        for(int i=0;i<xT.length;i++){
            distArr[i][0]=eucDist(xT[i],xTest);
            distArr[i][1]=yT[i];
        }
        Arrays.sort(distArr,Comparator.comparingDouble(a->a[0]));
        
        double[] neigh=new double[k];
        for(int i=0;i<k;i++){
            neigh[i]=distArr[i][1];
        }
        return neigh;
    }

    public double mostComm(double[] arr){
        Map<Double,Integer> freqMap=new HashMap<>();
        for(double v:arr){
            freqMap.put(v,freqMap.getOrDefault(v,0)+1);
        }
        return Collections.max(freqMap.entrySet(),Map.Entry.comparingByValue()).getKey();
    }

    public double[] pred(double[][] xT,double[] yT,double[][] xTest,int k){
        double[] yP=new double[xTest.length];
        for(int i=0;i<xTest.length;i++){
            double[] neigh=getNeigh(xT,yT,xTest[i],k);
            yP[i]=mostComm(neigh);
        }
        return yP;
    }

    public double calcAcc(double[] yTest,double[] yP){
        int corr=0;
        for(int i=0;i<yTest.length;i++){
            if(yTest[i]==yP[i]){
                corr++;
            }
        }
        return (double)corr/yTest.length;
    }
}
