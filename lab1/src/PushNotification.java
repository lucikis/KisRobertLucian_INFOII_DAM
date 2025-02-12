public class PushNotification extends Notification implements Notifiable{

    @Override
    public void sendNotification() {
        System.out.println("Push notification sent!");
    }
}