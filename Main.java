import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner getInfo() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to enter matrix from file or console? (Write a file or console)");
        String str = scanner.nextLine();
        boolean isFile = false;
        while (true) {
            if (str.equalsIgnoreCase("file")) {
                isFile = true;
                break;
            }
            if (str.equalsIgnoreCase("console")) {
                break;
            }
            System.out.println("Incorrect input. Please repeat");
            str = scanner.nextLine();
        }

        if (isFile) {
            System.out.println("Enter path to file");
            File file = new File(scanner.nextLine());
            scanner = new Scanner(file);
        } else {
            System.out.println("Enter a matrix size");
        }
        return scanner;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner;
        try {
            scanner = getInfo();
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found! Read from console");
            scanner = new Scanner(System.in);
        }

        // init size of matrix
        int n = scanner.nextInt();

        // init matrix
        double[][] startMatrix = new double[n][n + 1];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n + 1; ++j)
                startMatrix[i][j] = scanner.nextDouble();

        // do actions with matrix
        Solver.size = n;
        Solver.setMatrix(startMatrix);
        Solver.solve();
    }
}
