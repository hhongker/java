
import data.Data1;

import java.io.*;
import java.util.List;

public class SFile {

    //    字符输出流
    public static void ziFuWrite(String fileName, String content)   {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(content);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //    字节输出流
    public static void ziJieWrite(String fileName, String content)   {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bufferedOutputStream.write(content.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //    字符输入流
    public static  String ziFuRead(String fileName){
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        String content = null;
        try {
//            bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
//            用转换流实现
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
        //            按行读取
        //            while ((content = bufferedReader.readLine()) != null){
        //                stringBuilder.append(content+System.lineSeparator());
        //            }
            char[] chars = new char[1024];
            int i = -1;
            while ((i = bufferedReader.read(chars)) != -1) {
                stringBuilder.append(chars);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    //    字节输入流
    public static String ziJieRead(String fielName) {
        BufferedInputStream bufferedInputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(fielName)));
            byte[] bytes = new byte[1024];
            while (bufferedInputStream.read(bytes) != -1){
                String byteStr = new String(bytes,"UTF-8");
                stringBuilder.append(byteStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }


    //    对象输入流
    public static Object getObject(String fileName)  {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(new File(fileName)));
            Object obj = objectInputStream.readObject();
            return obj;
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        } finally {
            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //    对象输出流
    public static  <E>void setObject(E object,String fileName) throws Exception {
        ObjectOutputStream objectOutputStream = null;
        objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
    }


    //    拷贝文件
    public static void copyFile(String origin, String target,int i) throws IOException {
        if (i == 10) return;
        i ++; //安全措施，防止一直递归嵌套创建文件夹
        File originFile = new File(origin);
        File tartgetFile = new File(target);
        //  如果是目标文件夹是源文件的父级或目标文件夹是文件则结束操作
        if (originFile.getParent().equals(target) || tartgetFile.isFile()) return;
        //  如果目标文件夹中含有名字跟原文件名字相同的文件，则源文件改名字
        String[] fileNames = tartgetFile.list();
        if (fileNames != null) {
            for (String name : fileNames) {
                if (name.equals(originFile.getName())) {
                    originFile = new File(origin + "+");
                }
            }
        }
        //  如果不存在目标文件夹则创建目标文件夹
        if(tartgetFile.exists()) {tartgetFile.mkdirs();}

        String newTargetFileName =  target +File.separator+ originFile.getName();
        File newTargetFile = new File(newTargetFileName);

        if (originFile.isFile()) { //如果是文件
            String str = ziJieRead(originFile.getAbsolutePath());
            ziJieWrite(newTargetFileName,str);
        }else if(originFile.list().length != 0) { //非空文件夹
            newTargetFile.mkdirs();
            for (File file : originFile.listFiles()) {
                copyFile(file.getAbsolutePath(),newTargetFileName, i ++);
            }
        }else { //空的文件夹
            newTargetFile.mkdirs();
        }
    }

    //    删除文件
    public static void deleteFile(String origin, int i) {
        if(i == 12) return;
        i ++;

        File file = new File(origin);
        //  如果文件本身不存在，不操作
        if (!file.exists()) return;
        //  如果只是一个文件或者空的文件夹，直接删除
        if (file.isFile() || file.list().length == 0) {
            file.delete();
            return;
        }
        //  如果是一个非空的文件夹，则递归删除
        File[] files = file.listFiles();
        for (File f : files) {
            deleteFile(f.getAbsolutePath(),i);
        }
        if (file.exists()) file.delete();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(ziFuRead("E:\\hhr\\Desktop\\Java复习\\基础知识\\java的IO\\src\\data\\Data2"));
    }
}
