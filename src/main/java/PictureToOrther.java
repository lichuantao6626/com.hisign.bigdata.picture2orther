import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PictureToOrther {
    BufferedReader br = null;
    public static void main(String[] args) {
        PictureToOrther pictureToOrther = new PictureToOrther();
        pictureToOrther.mvTo();
    }

    public void mvTo(){
//        文件列表
        List<String> filelist = readFile("E:\\临时文件\\2023-09-26\\filelist.txt");
//       待检索库
        File[] listFiles_all = new File("E:\\临时文件\\2023-09-26\\fileall").listFiles();
//        存放所有文件的路径
        ArrayList<String> files_all_list = new ArrayList<String>();
        HashMap<String, String> fileall_map = new HashMap<String, String>();
        for(File file:listFiles_all){
            String fileName = file.getName();
            fileall_map.put(file.getAbsolutePath(),fileName);
        }
//        遍历，并把满足条件的图片复制一份到另一个目录下
        for(String file :filelist) {
            String inputPath=file;
            if (fileall_map.keySet().contains(inputPath)){
                String filename = fileall_map.get(inputPath);
                copyPicture(inputPath,"E:\\临时文件\\2023-09-26\\data\\"+filename);
            }

        }
    }

//    复制图片
    public void copyPicture(String inputPath,String outPath){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(inputPath);
        BufferedInputStream bis =new BufferedInputStream(fis);
        FileOutputStream fos = new FileOutputStream(outPath);
        BufferedOutputStream bos =new BufferedOutputStream(fos);
        int read;
        while ((read = bis.read())!=-1){
            bos.write(read);
        }

        bis.close();
        bos.close();
        fis.close();
        fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    读取文件内容
public List<String> readFile(String inputPath){
    List<String> lines = new ArrayList<String>();
    try {
        //按照UTF-8的字符编码读取
        br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inputPath)),"UTF-8"));
        String line = null;
        while((line=br.readLine())!=null){
            lines.add(line);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lines;
}

}

