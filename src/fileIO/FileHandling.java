package fileIO;

import java.io.*;

public class FileHandling {
    public static void main(String[] args) throws IOException {
        FileHandler fileHandler = new FileHandler();

        System.out.println("1 : (절대 경로)파일 단순 생성 + 초기값 입력");
        //fileHandler.createFileAbsolutePath();
        System.out.println("2 : (상대 경로)파일 단순 생성 + 초기값 입력");
        //fileHandler.createFileRelativePath();
        System.out.println("3 : 파일 읽어오기 - character 하나씩 읽기");
        //fileHandler.readFile();
        System.out.println("4 : 파일 입력 스트림 - BufferedOutputStream - 성능 향상");
        //fileHandler.bufferedOutputStream();
        System.out.println("5 : 파일 불러오기 - BufferedInputStream");
        //fileHandler.bufferedInputStream();

    }
}

class FileHandler {

    /**
     * 파일 단순 생성 - 절대 경로
     */
    public void createFileAbsolutePath(){
        try {
            OutputStream outputStream = new FileOutputStream("C:/MyDev/test/createdFile.txt");
            String str = "text first line1";
            byte[] bytes = str.getBytes();
            outputStream.write(bytes);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 파일 단순 생성 - 상대 경로
     */
    public void createFileRelativePath(){
        try {
            OutputStream outputStream = new FileOutputStream("test.txt");
            String str = "text first line1";
            byte[] bytes = str.getBytes();
            outputStream.write(bytes);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 파일 읽어오기 - character 하나씩 읽기
     */
    public void readFile(){
        try {

            //파일 생성 or 파일 불러오기
            File file = new File("test.txt");

            //입력 스트림 생성
            FileReader fr = new FileReader(file);

            int cur = 0;
            while((cur = fr.read()) != -1){
                System.out.print((char)cur);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 파일 입력 스트림 - BufferedOutputStream - 성능 향상
     */
    public void bufferedOutputStream() throws IOException {
        BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream("test.txt"));
        try {
            String str = "text first line1\ntext first line2";

            bs.write(str.getBytes()); // byte 형만 넣을 수 있음.
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            bs.close();
        }
    }

    /**
     * 파일 불러오기 - BufferedInputStream
     */
    public void bufferedInputStream() throws IOException{
        try{
            FileInputStream fis = new FileInputStream("test.txt");

            byte[] fileReadBuffer = new byte[fis.available()];
            while(fis.read(fileReadBuffer) != -1){

                System.out.println(new String(fileReadBuffer));
            }

            fis.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
