public class Ex1 {
    public String str;
    public Ex1(String str){
        this.str = str;
    }

    public String SortareString(){
        StringBuilder uppercase = new StringBuilder();
        StringBuilder lowercase = new StringBuilder();

        for(char caracter : this.str.toCharArray()){
            if(Character.isUpperCase(caracter)){
                uppercase.append(caracter);
            }else if(!Character.isUpperCase(caracter)){
                lowercase.append(caracter);
            }
        }

        this.str = lowercase.toString() + uppercase.toString();
        return this.str;
    }

    @Override
    public String toString(){
        return this.str;
    }
}
