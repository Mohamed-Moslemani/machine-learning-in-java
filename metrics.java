public class metrics {
    public static void main(String[] args) {
        double[] y_test = {1, 0, 1, 0, 1, 0, 1, 0, 1, 0};
        double[] y_pred = {1, 0, 1, 0, 1, 0, 0, 0, 0, 0};
        System.out.println("Accuracy: " + new accuracy().get_accuracy(y_test, y_pred));
        System.out.println("Confusion Matrix: ");
        double[][] matrix = new confusion_matrix().get_confusion_matrix(y_test, y_pred);
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[i].length; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Precision: " + new precision().get_precision(y_test, y_pred));
        System.out.println("Recall: " + new recall().get_recall(y_test, y_pred));
        System.out.println("F1 Score: " + new f1_score().get_f1_score(y_test, y_pred));
        System.out.println("ROC AUC: " + new roc_auc().get_roc_auc(y_test, y_pred));
        System.out.println("Log Loss: " + new log_loss().get_log_loss(y_test, y_pred));
    }
    
}

class accuracy
{
    public double get_accuracy(double[] y_test, double[] y_pred)
    {
        double correct = 0;
        for(int i = 0; i < y_test.length; i++)
        {
            if(y_test[i] == y_pred[i])
            {
                correct++;
            }
        }
        return correct / y_test.length;
    }
}

class confusion_matrix
{
    public double[][] get_confusion_matrix(double[] y_test, double[] y_pred)
    {
        double[][] matrix = new double[2][2];
        for(int i = 0; i < y_test.length; i++)
        {
            if(y_test[i] == 1 && y_pred[i] == 1)
            {
                matrix[0][0]++;
            }
            else if(y_test[i] == 1 && y_pred[i] == 0)
            {
                matrix[0][1]++;
            }
            else if(y_test[i] == 0 && y_pred[i] == 1)
            {
                matrix[1][0]++;
            }
            else if(y_test[i] == 0 && y_pred[i] == 0)
            {
                matrix[1][1]++;
            }
        }
        return matrix;
    }
}

class precision
{
    public double get_precision(double[] y_test, double[] y_pred)
    {
        double[][] matrix = new confusion_matrix().get_confusion_matrix(y_test, y_pred);
        return matrix[0][0] / (matrix[0][0] + matrix[0][1]);
    }
}

class recall
{
    public double get_recall(double[] y_test, double[] y_pred)
    {
        double[][] matrix = new confusion_matrix().get_confusion_matrix(y_test, y_pred);
        return matrix[0][0] / (matrix[0][0] + matrix[1][0]);
    }
}

class f1_score
{
    public double get_f1_score(double[] y_test, double[] y_pred)
    {
        double precision = new precision().get_precision(y_test, y_pred);
        double recall = new recall().get_recall(y_test, y_pred);
        return 2 * ((precision * recall) / (precision + recall));
    }
}

class roc_auc
{
    public double get_roc_auc(double[] y_test, double[] y_pred)
    {
        double[][] matrix = new confusion_matrix().get_confusion_matrix(y_test, y_pred);
        return (matrix[0][0] / (matrix[0][0] + matrix[0][1])) - (matrix[1][0] / (matrix[1][0] + matrix[1][1]));
    }
}

class log_loss
{
    public double get_log_loss(double[] y_test, double[] y_pred)
    {
        double loss = 0;
        for(int i = 0; i < y_test.length; i++)
        {
            loss += y_test[i] * Math.log(y_pred[i]) + (1 - y_test[i]) * Math.log(1 - y_pred[i]);
        }
        return -loss / y_test.length;
    }
}