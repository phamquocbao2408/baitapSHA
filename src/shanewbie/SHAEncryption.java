package shanewbie;
import java.io.*;
import java.security.*;

public class SHAEncryption {
    public static void main(String[] args) throws UnsupportedEncodingException {
        try {
            // Đọc dữ liệu nhập từ bàn phím
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Nhập thông tin cần mã hóa: ");
            String data = reader.readLine();
            
            // Tạo một đối tượng MessageDigest với thuật toán SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            // Mã hóa dữ liệu
            byte[] hashedBytes = digest.digest(data.getBytes("UTF-8"));
            
            // Chuyển đổi byte array thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                String hex = Integer.toHexString(0xff & hashedByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            // Ghi mã băm và thông tin ban đầu vào một file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("hashed_data.txt"))) {
                writer.write("Thông tin ban đầu: " + data + "\n");
                writer.write("Mã băm: " + hexString.toString());
                System.out.println("Dữ liệu đã được mã hóa và lưu vào file hashed_data.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        } catch (NoSuchAlgorithmException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
