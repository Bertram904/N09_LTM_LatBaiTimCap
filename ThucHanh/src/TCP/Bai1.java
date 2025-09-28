/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

/**
 *
 * @author ngotu
 */
import java.io.*;
import java.net.*;
import java.util.*;
public class Bai1 {
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("203.162.10.109", 2207);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        String code = "B22DCCN024;hBQ9Jw2m";
        out.writeUTF(code);
        out.flush();
         int k = in.readInt();
        String arrayStr = in.readUTF();

        System.out.println("k = " + k);
        System.out.println("Mảng nhận được: " + arrayStr);

        String[] parts = arrayStr.split(",");
        List<String> resultList = new ArrayList<>();

        for (int i = 0; i < parts.length; i += k) {
            int end = Math.min(i + k, parts.length);
            for (int j = end - 1; j >= i; j--) {
                resultList.add(parts[j]);
            }
        }

        String result = String.join(",", resultList);
        in.close();
        out.close();
        socket.close();
    }
}
