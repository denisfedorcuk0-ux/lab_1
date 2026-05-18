import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try {
            long[][] matrixB = {
                    {12L, -5L, 20L, 8L},
                    {3L, 45L, -12L, 0L},
                    {-7L, 14L, 9L, 55L}
            };

            System.out.println("Початкова матриця B:");
            printMatrix(matrixB);

            long[][] matrixC = transposeMatrix(matrixB);
            System.out.println("\nРезультуюча матриця C (транспонована):");
            printMatrix(matrixC);

            long totalSum = calculateSpecialSum(matrixC);
            System.out.println("\nРезультат другої дії (C11 = 6):");
            System.out.println("Сума найбільших елементів у парних стовпцях "
                    + "та найменших у непарних стовпцях = " + totalSum);

        } catch (IllegalArgumentException e) {
            System.err.println("Помилка валідації даних: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Помилка: Матриця не ініціалізована (null). " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Виникло непередбачуване виключення: " + e.getMessage());
        }
    }

    public static long[][] transposeMatrix(long[][] matrix) {
        if (matrix == null) {
            throw new NullPointerException("Матриця не може бути null.");
        }
        if (matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            throw new IllegalArgumentException("Матриця не повинна бути порожньою.");
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 1; i < rows; i++) {
            if (matrix[i] == null || matrix[i].length != cols) {
                throw new IllegalArgumentException("Матриця повинна бути прямокутною.");
            }
        }

        long[][] transposed = new long[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }

    public static long calculateSpecialSum(long[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Матриця для обчислення суми порожня або null.");
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        long totalSum = 0;

        for (int j = 0; j < cols; j++) {
            long targetValue = matrix[0][j];

            if (j % 2 == 0) {
                for (int i = 1; i < rows; i++) {
                    if (matrix[i][j] > targetValue) {
                        targetValue = matrix[i][j];
                    }
                }
                System.out.printf("Стовпець %d (парний) -> Максимум: %d\n", j, targetValue);
            } else {
                for (int i = 1; i < rows; i++) {
                    if (matrix[i][j] < targetValue) {
                        targetValue = matrix[i][j];
                    }
                }
                System.out.printf("Стовпець %d (непарний) -> Мінімум: %d\n", j, targetValue);
            }

            totalSum += targetValue;
        }

        return totalSum;
    }

    private static void printMatrix(long[][] matrix) {
        for (long[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}