public abstract class ErrorPascal implements Pascal {


    public boolean sanity_check (int n){

        if(n >= 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean sanity_check (int n, int k){

        if (sanity_check(n) && k >= 0 && k <= n){

            return true;
        } else {

            return false;
        }
    }
}
