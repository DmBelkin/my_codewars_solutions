public class MatrixDeterminant_4KYU {

//    DESCRIPTION:
//    Write a function that accepts a square matrix (N x N 2D array) and returns the determinant of the matrix.
//
//    How to take the determinant of a matrix -- it is simplest to start with the smallest cases:
//
//    A 1x1 matrix |a| has determinant a.
//
//            A 2x2 matrix [ [a, b], [c, d] ] or
//
//            |a  b|
//            |c  d|
//    has determinant: a*d - b*c.
//
//    The determinant of an n x n sized matrix is calculated by reducing the problem to the calculation of the determinants of n matrices ofn-1 x n-1 size.
//
//    For the 3x3 case, [ [a, b, c], [d, e, f], [g, h, i] ] or
//
//            |a b c|
//            |d e f|
//            |g h i|
//    the determinant is: a * det(a_minor) - b * det(b_minor) + c * det(c_minor) where det(a_minor) refers to taking the determinant of the 2x2 matrix created by crossing out the row and column in which the element a occurs:
//
//            |- - -|
//            |- e f|
//            |- h i|
//    Note the alternation of signs.


    public static int determinant(int[][] matrix) {
        int determinant = 0;
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        if (matrix.length == 2) {
            return (matrix[0][0] * matrix[1][1]) -
                    (matrix[1][0] * matrix[0][1]);
        }
        if (matrix.length >= 3) {
            for (int i = 0; i <= matrix.length - 1; i++) {
                if (i == 0) {
                    determinant = determinant + subDeterminant(getCofactor(matrix, 0, i)) * matrix[0][i];
                } else if (i == 1 || i % 2 > 0) {
                    determinant = determinant - subDeterminant(getCofactor(matrix, 0, i)) * matrix[0][i];
                } else if (i % 2 == 0) {
                    determinant = determinant + subDeterminant(getCofactor(matrix, 0, i)) * matrix[0][i];
                }
            }

        }
        return determinant;
    }


    public static int subDeterminant(int[][] cofactor) {
        int determinant = 0;
        for (int k = 0; k <= cofactor.length - 1; k++) {
            int diag1 = 1;
            int diag2 = 1;
            int minor;
            for (int i = 0; i <= cofactor.length - 1; i++) {
                for (int j = 0; j <= cofactor[i].length - 1; j++) {
                    if (i == j) {
                        diag1 = diag1 * cofactor[i][j];
                    }
                    if (j == cofactor.length - 1 - i) {
                        diag2 = diag2 * cofactor[i][j];
                    }
                }
            }
            if (cofactor.length == 2) {
                determinant = determinant + (diag1 - diag2);
                return determinant;
            } else if (cofactor.length > 2) {
                minor = subDeterminant(getCofactor(cofactor, 0, k)) * cofactor[0][k];
                if (k == 0) {
                    determinant = determinant + minor;
                }
                else if (k == 1 || k % 2 > 0) {
                    determinant = determinant - minor;
                }
                else if (k % 2 == 0) {
                    determinant = determinant + minor;
                }
            }
        }
        return determinant;
    }

    public static int[][] getCofactor(int[][] matrix, int a, int b) {
        int[][] cofactor = new int[matrix.length - 1][matrix.length - 1];
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i <= matrix.length - 1; i++) {
            for (int j = 0; j <= matrix[i].length - 1; j++) {
                if (j == b || i == a) {
                    continue;
                }
                cofactor[index1][index2] = matrix[i][j];
                index2++;
                if (index2 == matrix.length - 1) {
                    index2 = 0;
                    index1++;
                    break;
                }
            }
        }
        return cofactor;
    }

}
