package com.wensir.bigdata.hos.server;


import com.wensir.bigdata.hos.core.HosConfiguration;
import org.apache.commons.io.FileExistsException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;


/**
 * <br>
 * 〈hdfs实现类〉
 *
 * @author wensir
 * @create 2019/3/29
 * @since 1.0.0
 */
public class HdfsServiceImpl implements IHdfsService {
    private static Logger logger = Logger.getLogger(HdfsServiceImpl.class);

    private FileSystem fileSystem;

    //    hdfs默认值128M.而我们的阈值是20M。所以如果文件小于64兆手动将block设置为64M
    private long defaultBlockSize = 128 * 1024 * 1024;

    private long initBlockSize = defaultBlockSize / 2;


    public HdfsServiceImpl() throws Exception {
//    1.    读取hdfs相关的配置信息
        HosConfiguration hosConfiguration = HosConfiguration.getHosConfiguration();
        String confDir = hosConfiguration.getString("hadoop.conf.dir");
        String hdfsUri = hosConfiguration.getString("hadoop.uri");
        //hdfs://localhost:9000
//    2.    通过配置获取一个fileSystem实例

        Configuration configuration = new Configuration();
        configuration.addResource(new Path(confDir + "/hdfs-site.xml"));
        configuration.addResource(new Path(confDir + "/core-site.xml"));
        fileSystem = FileSystem.get(new URI(hdfsUri), configuration);
    }


    @Override
    public void saveFile(String dir, String name, InputStream input, long length, short replication) throws IOException {
//        判断dir是否存在
        Path dirPath = new Path(dir);
        try {
            if (!fileSystem.exists(dirPath)) {
                boolean succ = fileSystem.mkdirs(dirPath, FsPermission.getDirDefault());
                logger.info("create dir " + dirPath + " success" + succ);
                if (!succ) {
                    throw new IOException("dir create failed:" + dir);
                }
            }
        } catch (FileExistsException ex) {
            //do nothing
        }
//        创建文件
        Path path = new Path(dir + "/" + name);
        long blockSize = length <= initBlockSize ? initBlockSize : defaultBlockSize;
        try (FSDataOutputStream outputStream = fileSystem.create(path, true, 512 * 1024, replication, blockSize)) {
            fileSystem.setPermission(path, FsPermission.getFileDefault());
            byte[] buffer = new byte[512 * 1024];
            int len = -1;
            while ((len = input.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
        } finally {
            input.close();

        }

    }

    @Override
    public void deleteFile(String dir, String name) throws IOException {
        //false不进行递归删除
        fileSystem.delete(new Path(dir + "/" + name), false);


    }

    @Override
    public InputStream openFile(String dir, String name) throws IOException {
        return fileSystem.open(new Path(dir + "/" + name));

    }

    @Override
    public void mikDir(String dir) throws IOException {
        fileSystem.mkdirs(new Path(dir));


    }

    @Override
    public void deleteDir(String dir) throws IOException {
        this.fileSystem.delete(new Path(dir), true);
    }
}