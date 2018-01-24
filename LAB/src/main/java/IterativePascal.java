
public class IterativePascal extends ErrorPascal implements Pascal {

    boolean direction;
    int[][] v;
    boolean first_printPascal;

    public void printPascal(int n){



        if (sanity_check(n)) {

            if (first_printPascal){
                v = new int [n+1][n+1];
                first_printPascal=false; }

            if (direction) {
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c <= r; c++) {
                        System.out.print(binom(r, c));
                        System.out.print(" ");

                    }
                    System.out.println(" ");
                }

                // if low to high call binom with each row and column starting with row 0 and column 0

            } else {
                for (int r = n - 1; r >= 0; r--) {
                    for (int c = r; c >= 0; c--) {
                        System.out.print(binom(r, c));
                        System.out.print(" ");

                    }
                    System.out.println(" ");
                }

            }
            // if high to low call binom with each row and coloumn starting with row = n-1 and column = n-1
        }else{
            System.out.println("parameter out of bounds");
        }
        // Sanity check error

    }






    public int binom (int n, int k){
        if (sanity_check(n,k)) {

            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= k; j++) {

                    if (j == 0) {
                        v[i][j] = 1;
                        // if first column: = 1
                    } else if (i == 0) {
                        v[i][j] = 1;
                        // if first row: = 1
                    } else if (i == j) {
                        v[i][j] = 1;
                        // if last column: = 1
                    } else {
                        v[i][j] = v[i - 1][j - 1] + v[i - 1][j];
                        // take values from numbers up to the right and to the left and add
                    }


                }
            }

            return v[n][k];

        }else{
            return -1;
        }
        // sanity check error
    }






}
