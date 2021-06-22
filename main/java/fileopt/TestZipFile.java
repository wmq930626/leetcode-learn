package fileopt;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author: MingShi
 * @version: 2021/6/22
 * @description: 文件压缩优化
 * @see  <a href='https://mp.weixin.qq.com/s/B_CCTZ6xngEIwOLDlL1HoQ'>压缩 20M 文件从 30 秒到 1 秒的优化过程</a>
 */
public class TestZipFile {

    private static final String ZIP_FILE_1 = "/Users/wangmengqi/Desktop/电子书/test.zip";

    private static final String ZIP_FILE_2 = "/Users/wangmengqi/Desktop/电子书/test2.zip";

    private static final String ZIP_FILE_3 = "/Users/wangmengqi/Desktop/电子书/test3.zip";

    private static final File PDF_FILE = new File("/Users/wangmengqi/Desktop/电子书/《Spring Boot 2.5开发实战》.pdf");

    private static final String FILE_NAME = "《Spring Boot 2.5开发实战_%s》.pdf";


    public static void main(String[] args) {
        zipFileNoBuffer();
        zipFileBuffer();
        zipFileChannel();
    }

    public static void zipFileNoBuffer() {
        File zipFile = new File(ZIP_FILE_1);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile))) {
            //开始时间
            long beginTime = System.currentTimeMillis();

            for (int i = 0; i < 5; i++) {
                try (InputStream input = new FileInputStream(PDF_FILE)) {
                    zipOut.putNextEntry(new ZipEntry(String.format(FILE_NAME, i)));
                    int temp;
                    while ((temp = input.read()) != -1) {
                        zipOut.write(temp);
                    }
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("file size:" + zipFile.length() / 1024 / 1024);
            System.out.println("NoBuffer consume time:" + (endTime - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zipFileBuffer() {
        File zipFile = new File(ZIP_FILE_2);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOut)) {
            //开始时间
            long beginTime = System.currentTimeMillis();
            for (int i = 0; i < 5; i++) {
                try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(PDF_FILE))) {
                    zipOut.putNextEntry(new ZipEntry(String.format(FILE_NAME, i)));
                    int temp = 0;
                    while ((temp = bufferedInputStream.read()) != -1) {
                        bufferedOutputStream.write(temp);
                    }
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("file size:" + zipFile.length() / 1024 / 1024);
            System.out.println("Buffer consume time:" + (endTime - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zipFileChannel() {
        //开始时间
        long beginTime = System.currentTimeMillis();
        File zipFile = new File(ZIP_FILE_3);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             WritableByteChannel writableByteChannel = Channels.newChannel(zipOut)) {
            for (int i = 0; i < 5; i++) {
                try (FileChannel fileChannel = new FileInputStream(PDF_FILE).getChannel()) {
                    zipOut.putNextEntry(new ZipEntry(String.format(FILE_NAME, i)));
                    fileChannel.transferTo(0, PDF_FILE.length(), writableByteChannel);
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("file size:" + zipFile.length() / 1024 / 1024);
            System.out.println("Channel consume time:" + (endTime - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
