package com.itqf.task;

import org.springframework.stereotype.Component;

@Component("backupDatabase")
public class BackupDatabase {
    public void backup(String s){
        try {
            System.out.println(s);
            Runtime runtime=Runtime.getRuntime();
            String fileName=System.currentTimeMillis()+".sql";
            runtime.exec("D:/mariadb/bin/mysqldump -uroot -pdaixu mydb -r E:/sql/"+fileName);
            System.out.println("备份成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
