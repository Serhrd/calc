import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите два числа: ");
        String expression = in.nextLine();
         System.out.println(Parse(expression));
    }

    public static String Parse(String expression) throws Exception{
        int num;
        int num1;
        String operation;
        String result;
        boolean isRoman;
        String[] operands = expression.split("[+\\-=*/]");

        if(operands.length !=2) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор");

        operation = detectOperation(expression);

        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])){
            num = Roman.convertToArabian(operands[0]);
            num1 = Roman.convertToArabian(operands[1]);
            isRoman=true;
        }else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])){
            num = Integer.parseInt(operands[0]);
            num1 = Integer.parseInt(operands[1]);
            isRoman =false;
        }else throw new Exception("используются  разные системы счисления");
        if (num> 10 || num1>10) throw new Exception("Числа должны бать меньше 10");
        int arabian = calc(num,num1,operation);
        if(isRoman){
            if(arabian<=0){
                throw new Exception("Римское число должно быть больше нуля");
            }
            result = Roman.convertToRoman(arabian);
        }
        else result = String.valueOf(arabian);
        return result;
    }



    static String detectOperation(String expression){
        if(expression.contains("+")) return "+";
        else if(expression.contains("-")) return "-";
        else if(expression.contains("/")) return "/";
        else if(expression.contains("*")) return "*";
        else return null;
    }
    static int calc(int num, int num1, String operation){
        if(operation.equals("+")) return num+num1;
        else if(operation.equals("-")) return num-num1;
        else if(operation.equals("/")) return num/num1;
        else return num*num1;
    }



    class Roman{
        static String[] romanArray = {"0","I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII","XIII","XIV","XV","XVI","XVII","XVIII","XIX","XX",
                "XXI","XXII","XXIII","XXIV","XXV","XXVI","XXVII","XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXII","LXXXVIII","LXXXIX","XC","XCI","XCII","XCIII","XCIV","XCV","XCVI","XCVII","XCVIII","XCIX","C"};
        public static boolean isRoman(String val){
            for (int i = 0; i <romanArray.length; i++) {
                if(val.equals(romanArray[i])) return true;
            }
            return false;
        }

        public static int convertToArabian(String roman){
            for (int i = 0; i < romanArray.length; i++) {
                if(roman.equals(romanArray[i])) return i;
            }
            return -1;
        }
        public static String convertToRoman(int arabian){return romanArray[arabian];}
    }
}