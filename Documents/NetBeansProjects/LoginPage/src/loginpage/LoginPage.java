package loginpage;
import java.util.Scanner;
public class LoginPage {
    static boolean aktif=true;
    static int hakSayisi=3;
    
    public static void main(String[] args) {  
       Scanner scanner= new Scanner(System.in);
       if(aktif){
       if(hakSayisi>0){
           while(aktif){
               System.out.println("Kullanıcı adınızı giriniz; ");
               String username=scanner.nextLine();
               System.out.println("Parolanızı giriniz; ");
               String password=scanner.nextLine();    
               boolean sonuc = login(username,password);
               if(sonuc){
                   System.out.println("Uygulamaya başarılı bir şekilde giriş yapıldı");
                   break;
               }else{
               if(hakSayisi==0){
               aktif=false;
               break;
               }
               }
               if(aktif==false){
                   System.out.println("Hak sayınız dolmuştur!!!");
               }
           }
       }else{
           System.out.println("Hak Sayınız Dolmuştur, hesabınız bloke oldu");
       }
       }else{
           System.out.println("Hesabınız aktif değil");
       }
    }
    public static boolean login(String username, String password){
    if(username.equals("hazal")&& password.equals("6161")){
    return true;
    }else{
        hakSayisi--;
        System.out.println("Kullanıcı adı veya şifreniz hatalı!!!");
        if(hakSayisi==0){
        aktif=false;
        }
        return false;}  
    }
}
