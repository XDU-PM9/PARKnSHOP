package com.parknshop.service.serviceImpl;

import com.parknshop.service.DatabaseBackupService;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

/**
 * Created by niewenzhi on 2016/12/24.
 */
@Service
public class BackupImpl implements DatabaseBackupService {
    File directory;
    String filepath;
    {
         directory = new File("\\databasebackup");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        filepath = directory.getAbsolutePath() ;
        System.out.println("备份文件保存路径:"+filepath);
    }
    String username = "root";
    String password = "asd87060";
    
    @Override
    public  Boolean backup() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd-HH-mm-ss-SSS",Locale.ENGLISH);
        String filename =formatter.format(new Date());

       String cmd="mysqldump"+" -u"+username+" -p"+password+" parknshop>"+filepath+File.separator+filename+".sql";
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("cmd /c"+cmd);
            pr.waitFor();
            System.out.println("备份文件保存路径:"+filepath);
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
        String cmd="mysql"+" -u"+username+" -p"+password+" parknshop<"+filepath+File.separator+filename+".sql";
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
        Path path = Paths.get(filepath,filename+".sql");
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
    public List<File> getallfile() {

        System.out.println("备份文件保存路径:"+filepath);
        List<File> list = new ArrayList<>();
        File file = new File(filepath+"/");
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
       List<File> list = backup.getallfile();
        backup.rollback("2016-Dec-28-15-11-37-783");
    }
}
