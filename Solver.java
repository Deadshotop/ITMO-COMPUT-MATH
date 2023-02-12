public class Solver {
    public static double[][] matrix;
    public static double[] vec_B;
    public static double[] vec_X;
    public static double[] vec_R;
    public static int size;
    public static int l;

    public Solver(double[][] matrix, double[] vec_B, int n) {
        this.matrix = matrix;
        this.vec_B = vec_B;
        this.size = n;
    }
    public static void setMatrix(double[][] Matrix) {
        matrix = new double[size][size];
        vec_B = new double[size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Matrix[i][j];
            }
            vec_B[i] = Matrix[i][size];
        }

        System.out.println("Given matrix: ");
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                System.out.print(matrix[i][j] + "   ");
            }
            System.out.print(vec_B[i]);
            System.out.println();
        }

    }
    //Выбор главного элемента по столбцу
    private static void chooseMainElement() {
        for (int i = 0; i < size - 1; i++) {
            l = i;
            for (int m = i + 1; m < size; m++) {
                if (Math.abs(matrix[m][i]) > Math.abs(matrix[l][i])) {
                    l = m;
                }
            }
            if (l != i) {
                for (int j = i; j < size; j++) {
                    double a = matrix[i][j];
                    matrix[i][j] = matrix[l][j];
                    matrix[l][j] = a;
                }
                double a = vec_B[i];
                vec_B[i] = vec_B[l];
                vec_B[l] = a;
            }
        }
    }

    public static void solve() {
        //прямой ход
        for (int i = 0; i < size - 1; i++) {
            chooseMainElement();
            for (int k = i + 1; k < size; k++) {
                double c = matrix[k][i] / matrix[i][i];
                for (int j = i; j < size; j++) {
                    matrix[k][j] = matrix[k][j] - c * matrix[i][j];
                }
                vec_B[k] = vec_B[k] - c * vec_B[i];
            }
        }
        printMatrix();
        //обратный ход
        vec_X = new double[size];
        for (int i = size - 1; i > -1; i--) {
            double s = 0;
            for (int j = i + 1; j < size; j++) {
                s = s + matrix[i][j] * vec_X[j];
            }
            vec_X[i] = (vec_B[i] - s) / matrix[i][i];
        }
        printResult();
        getErrors();
        printErrors();
    }

    private static void printMatrix() {
        System.out.println("Полученная треугольная матрица и преобразованный столбец В: \n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.print("\t|" + vec_B[i] + "\n");
        }
    }

    private static void printResult() {
        for (int i = 0; i < size; i++) {
            System.out.println("x" + (i + 1) + " = " + vec_X[i] + " ~ " + Math.round(vec_X[i]));
        }
    }

    //Вычисление вектора невязок
    private static void getErrors() {
        vec_R = new double[size];
        for (int i = 0; i < size; i++) {
            double s = 0;
            for (int j = 0; j < size; j++) {
                s = s + matrix[i][j] * vec_X[j];
            }
            vec_R[i] = s - vec_B[i];
        }
    }

    public static void printErrors() {
        getErrors();
        System.out.println("Вектор невязок: ");
        for (int i = 0; i < size; i++) {
            System.out.print(vec_R[i] + "\t");
        }
    }
}
