package com.parknshop.service.serviceImpl;

import com.parknshop.service.DatabaseBackupService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Collection;

/**
 * Created by niewenzhi on 2016/12/24.
 */
public class BackupImpl implements DatabaseBackupService {
    File directory = new File("");//设定为当前文件夹
    String filepath=directory.getAbsolutePath()+"\\databasebackup";
    String username = "";
    String password = "";


    /*加载数据库密码账号*/
    {

        Properties props = new Properties();
        try {
            props.load(new FileInputStream(directory.getAbsolutePath()+ "\\src\\main\\java\\database.ini"));
            username=props.getProperty("username");
            password=props.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public  Boolean backup() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd-HH-mm-ss-SSS",Locale.ENGLISH);
        String filename =formatter.format(new Date());

       String cmd="mysqldump"+" -u"+username+" -p"+password+" parknshop>"+filepath+"\\"+filename+".sql";
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("cmd /c"+cmd);
            pr.waitFor();
            System.out.println(filepath);
            System.out.println(cmd);
            System.out.println("Backup success");
            return true;
        }
        catch (Exception e){
            System.out.println("failed");
            return false;
        }
    }

    @Override
    public Boolean rollback(String filename) {
        String cmd="mysql"+" -u"+username+" -p"+password+" parknshop<"+filepath+"\\"+filename+".sql";
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("cmd /c"+cmd);
            pr.waitFor();
            System.out.println(filepath);
            System.out.println(cmd);
            System.out.println("Rollback success");
            return true;
        }
        catch (Exception e){
            System.out.println("Rollback failed");
            return false;
        }
    }



    @Override
    public Boolean deletebackup(String filename) {
        Path path = Paths.get(filepath,filename);
        try {
            if (Files.deleteIfExists(path))
            {
                System.out.println("deletebackup Success");
                return true;
            }
            else
            System.out.println("deletebackup"+filename+"failed");
                return false;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("deletebackup failed");
            return false;
        }
    }

    @Override
    public List getallfile() {
        List list = new ArrayList();
        File file = new File(filepath+"\\");
        File[] files=file.listFiles();
        for (File targe : files){
            if (targe.getName().endsWith(".sql"))
                System.out.println("文件名: "+targe.getName());
                list.add(targe);
        }
        return list;
    }

    public static void main(String[] args) {
        BackupImpl backup = new BackupImpl();
        backup.backup();
        backup.getallfile();
    }
}
