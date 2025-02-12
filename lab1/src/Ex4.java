public class Ex4 {
    public static void valleyCounter(String path){
        char[] str = path.toCharArray();
        int contorVale = 0, parcurs = 0;
        boolean poateFiVale = false;
        for(int i = 0; i < str.length; ++i){
            if(parcurs >= 1){
                poateFiVale = true;
            } else if(parcurs < 0) {
                poateFiVale = false;
            }
            if(str[i] == 'U'){
                parcurs += 1;
            }else if(str[i] == 'D'){
                parcurs -= 1;
            }
            if(parcurs == 0 && poateFiVale){
                contorVale++;
            }
        }
        System.out.println("Numarul de vai este: " + contorVale);
    }
}
