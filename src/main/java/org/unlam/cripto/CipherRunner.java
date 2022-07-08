package org.unlam.cripto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.unlam.cripto.ciphers.Cipher;
import org.unlam.cripto.ciphers.mickey.Mickey;
import org.unlam.cripto.utils.Utils;

@SpringBootApplication
public class CipherRunner implements CommandLineRunner {

    private final static int HEADERS_LENGHT = 54;


    @Override
    public void run(String... args) throws Exception {

        boolean[] K = Utils.initBooleanArrayFromBinaryString(args[0]);
        boolean[] IV = Utils.initBooleanArrayFromBinaryString(args[1]);
        Cipher mickey = new Mickey(K, IV);

        byte[] bytemessage = Utils.getImageAsByteArray(args[2]);
        byte[] headers = new byte[HEADERS_LENGHT];
        byte[] imagen = new byte[bytemessage.length - HEADERS_LENGHT];

        for (int i = 0; i < HEADERS_LENGHT; i++) {
            headers[i] = bytemessage[i];
        }
        for (int i = 0; i < imagen.length; i++) {
            imagen[i] = bytemessage[i + HEADERS_LENGHT];
        }

        byte[] encryptedImage = mickey.encrypt(imagen);
        byte[] encriptedImageWithHeader = new byte[encryptedImage.length + HEADERS_LENGHT];


        for (int i = 0; i < HEADERS_LENGHT; i++) {
            encriptedImageWithHeader[i] = headers[i];
        }
        for (int i = 0; i < encryptedImage.length; i++) {
            encriptedImageWithHeader[i + HEADERS_LENGHT] = encryptedImage[i];
        }

        Utils.saveByteArrayToFile(args[3], encriptedImageWithHeader);

    }
}
