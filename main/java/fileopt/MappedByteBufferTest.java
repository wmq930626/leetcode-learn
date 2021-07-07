package fileopt;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * @author: MingShi
 * @version: 2021/6/22
 * @description:
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/wangmengqi/Desktop/learn/code/leetcode-learn/main/java/fileopt/TestZipFile.java");
        long len = file.length();
        byte[] ds = new byte[(int) len];
        MappedByteBuffer map = new FileInputStream(file).getChannel().map(FileChannel.MapMode.READ_ONLY, 0, len);
        for (int i = 0; i < len; i++) {
            byte b = map.get();
            ds[i] = b;
        }
        Scanner scanner = new Scanner(new ByteArrayInputStream(ds)).useDelimiter("\n");
        while (scanner.hasNext()){
            System.out.println(scanner.next());
        }
    }
}
