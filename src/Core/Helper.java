package Core;

import javax.swing.*;

public class Helper {
    public static void setTheme(){

        for (UIManager.LookAndFeelInfo  info:UIManager.getInstalledLookAndFeels()){
            if (info.getName().equals("Nimbus")){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    /// alanlar dolu mu ?
    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    /// pipline
    public static boolean isFieldListEmpty(JTextField[] fields){
        for (JTextField field : fields){
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }

    public static boolean isEmailValid(String email) {
        if (email == null || email.trim().isEmpty()) return false;

        if (!email.contains("@")) return false;

        String [] parts = email.split("@");
        if (parts.length != 2) return false;

        if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) return false;

        if (!parts[1].contains(".")) return false;

        return true;

    }
    public static void   optionPaneDialogTR(){
        UIManager.put("OptionPane.okButtonText" , "Tamam");
        UIManager.put("OptionPane.yesButtonText" , "Evet");
        UIManager.put("OptionPane.noButtonText" , "Hayır");

    }

    // Costumaized pop-up message;
    public static void showMsg(String message){
        String msg;
        optionPaneDialogTR();
        String title = switch (message) {
            case "fill" -> {
                msg = "Lütfen tüm alanları doldurunuz!";
                yield "HATA !";
            }
            case "done" -> {
                msg = "İşlem başarılı";
                yield "Sonuç :";
            }
            case "error" -> {
                msg = "Bir Hata Oluştu.";
                yield "HATA !";
            }
            default -> {
                msg = message;
                yield "Mesaj";
            }
        };

        JOptionPane.showMessageDialog(null ,msg, title, JOptionPane.INFORMATION_MESSAGE);

    }

    public static boolean confirm(String str){
        optionPaneDialogTR();
        String msg;
        if (str.equals("sure")){
            msg = "Bu işlemi gerçekleştirmek istediğine \n emin misin?";
        }else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Emin misin ?" , JOptionPane.YES_NO_OPTION) == 0;
    }

}
