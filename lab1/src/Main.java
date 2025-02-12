public class Main {
    public static void main(String[] args) {
        Ex1 ex1 = new Ex1("CaRTe");
        ex1.SortareString();
        System.out.println("Exercitiul 1: \nString-ul nou este: " + ex1 + "\n");
        if(Ex2.friendly(220, 284)){
            System.out.println("Exercitiul 2: \nNumerele sunt friendly\n");
        }else{
            System.out.println("Exercitiul 2: \nNumerele nu sunt friendly\n");
        }
        System.out.println("Exercitiul 3: \n" + Ex3.hexToDecimal("1a3") + "\n");
        Ex4.valleyCounter("UDDDUDUU");

        System.out.println("Exercitiul 4:\n");
        SMSNotification sms = new SMSNotification();
        sms.setMessage("Hello sms message");
        EmailNotification email = new EmailNotification();
        email.setMessage("Hello email message");
        PushNotification push = new PushNotification();
        push.setMessage("Hello push message");
        sms.sendNotification();
        email.sendNotification();
        push.sendNotification();
        System.out.println("\n");
        sms.displayNotification();
        email.displayNotification();
        push.displayNotification();
    }
}