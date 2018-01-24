public class Main {

    public static void main(String[] args) {

        RecursivePascal a = new RecursivePascal();
        //create an object of type RecursivePascal
        IterativePascal b = new IterativePascal();
        //create an object of type IterativePascal
        RecursivePascal c = new RecursivePascal();
        IterativePascal d = new IterativePascal();
        RecursivePascal e = new RecursivePascal();
        IterativePascal f = new IterativePascal();


        int n = 6;
        int m = -2;
        int p = 100;

        a.first_printPascal = true;
        // first call to printPascal
        a.direction = false;
        // decide direction of triangle
        a.printPascal(1000);
        // print triangle

       /** b.first_printPascal = true;
        // first call to printPascal in iterative funtion
        b.direction = true;
        // decide direction of triangle
        b.printPascal(n);


        c.first_printPascal = true;
        c.direction = false;
        c.printPascal(p);

        d.first_printPascal = true;
        d.direction = false;
        d.printPascal(p);

        e.first_printPascal = true;
        e.direction = false;
        e.printPascal(m);

        f.first_printPascal = true;
        f.direction = false;
        f.printPascal(m);**/







    }
}