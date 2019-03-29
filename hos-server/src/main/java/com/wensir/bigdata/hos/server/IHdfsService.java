package com.wensir.bigdata.hos.server;

import java.io.IOException;
import java.io.InputStream;

/**
 * <br>
 * 〈hdfs接口类〉
 *
 * @author wensir
 * @create 2019/3/29
 * @since 1.0.0
 */
public interface IHdfsService {
    public void saveFile(String dir, String name,
                         InputStream input, long length, short replication) throws IOException;

    public void deleteFile(String dir, String name) throws IOException;

    public InputStream openFile(String dir, String name) throws IOException;

    public void mikDir(String dir) throws IOException;

    public void deleteDir(String dir) throws IOException;


}