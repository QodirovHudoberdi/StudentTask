package com.company.utils;




import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ImageUtils {
        public static byte[] saveImageFromBase64(String base64Image, String fileName) throws IOException {
            byte[] decoded = Base64.getDecoder().decode(base64Image);

            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                fos.write(decoded);
                return decoded;
            }
        }

}
