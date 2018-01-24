

public class RecursivePascal extends ErrorPascal implements Pascal {

        boolean direction = false;
        int [][] array;
        boolean first_printPascal;

        public void printPascal (int n) {
            if(sanity_check(n)) {

                if (first_printPascal) {
                array = new int[n][n];
                first_printPascal = false;
                }
                // check if first call of function if not create array
                if (n != 0) {

                    if (direction) {
                        printPascal(n - 1);
                        // print lowest to highest
                    }

                    /**************
                     by keep calling the printPascal function the algorithm will go through all of the numbers
                    and then when n = 0 it will jump down one step and call the binom function with the saved row
                    number - which by now is in descending order.
                     **********/


                    for (int i = 0; i < n; i++) {
                        System.out.print(binom(n - 1, i));
                        // call the binom function, n-1 becuse we start at row 0 and column 0 instead on 1
                        System.out.print(" ");
                        // space between numbers
                    }
                    System.out.println("");
                    if (!direction) {
                        printPascal(n - 1);
                        // print out highest to lowest
                    }

                }

            } else {
                System.out.println("parameter out of bounds");
                // sanity_check error
            }

        }


            public int binom (int n, int k) {
            if(sanity_check(n,k)) {
                int v;
                if (array[n][k] != 0) {
                    v = array[n][k];
                } else {
                    if (n == 0) {
                        v = 1;
                        // first row
                    }

                    else if (n == 1) {
                        v = 1;
                        // second row
                    }

                    else if (k == 0) {
                        v = 1;
                    }
                    // first column always one
                    else if (k == n) {
                        v = 1;
                        // last column always one
                    }


                    else if (k > (n / 2)) {

                        v = binom(n, n - k);
                        // skip duplicate calculations if left side of triangle already 'filled'
                    } else {
                        v = binom(n - 1, k - 1) + binom(n - 1, k);
                        // recursive function
                    }

                }
                array[n][k] = v;
                return v;
            }

            else{
                return -1;
            }
            // sanity check error
        }
    }





