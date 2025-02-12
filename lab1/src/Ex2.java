public class Ex2 {
    public static boolean friendly(int a, int b){
        int sumaDivizoriA = 0, sumaDivizoriB = 0;
        for(int i = 1; i < a; ++i){
            if (a % i == 0){
                sumaDivizoriA += i;
            }
        }
        for(int i = 1; i < b; ++i){
            if (b % i == 0){
                sumaDivizoriB += i;
            }
        }

        if((sumaDivizoriA == b) && (sumaDivizoriB == a)){
            return true;
        }
        return false;
    }
}
