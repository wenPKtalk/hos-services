package com.wensir.bigdata.hos.server;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * <br>
 * 〈HOS工具类〉
 *
 * @author wensir
 * @create 2019/3/29
 * @since 1.0.0
 */
public class HosUtil {
    static final byte[][] OBJ_REGIONS = new byte[][]{
            Bytes.toBytes("1"),
            Bytes.toBytes("4"),
            Bytes.toBytes("7")
    };

    static final String OBJ_TABLE_PREFIX = "hos_obj_";
    static final String DIR_TABLE_PREFIX = "hos_dir_";

    static final String DIR_META_CF = "cf";
    static final byte[] DIR_META_CF_BYTES = DIR_META_CF.getBytes();
    static final String DIR_SUBDIR_CF = "sub";
    static final byte[] DIR_SUBDIR_CF_BYTES = DIR_SUBDIR_CF.getBytes();
    static final String OBJ_CONT_CF = "c";
    static final byte[] OBJ_CONT_CF_BYTES = OBJ_CONT_CF.getBytes();
    static final String OBJ_META_CF = "cf";
    static final byte[] OBJ_META_CF_BYTES = OBJ_META_CF.getBytes();

    static final byte[] DIR_SEQID_QUALIFIER = "u".getBytes();
    static final byte[] OBJ_CONT_QUALIFIER = "c".getBytes();
    static final byte[] OBJ_LEN_QUALIFIER = "l".getBytes();
    static final byte[] OBJ_PROPS_QUALIFIER = "p".getBytes();
    static final byte[] OBJ_MEDIATYPE_QUALIFIER = "m".getBytes();

    static final String FILE_STORE_ROOT = "/hos";

    //阈值，超过该值则存入hdfs中
    static final int FILE_STORE_THRESHOLD = 20 * 1024 * 1024;

    static final int OBJ_LIST_MAX_COUNT = 200;
    //生成seq_id的表
    static final String BUCKET_DIR_SEQ_TABLE = "hos_dir_seq";
    static final String BUCKET_DIR_SEQ_CF = "s";
    static final byte[] BUCKET_DIR_SEQ_CF_BYTES = BUCKET_DIR_SEQ_CF.getBytes();
    static final byte[] BUCKET_DIR_SEQ_QUALIFIER = "s".getBytes();


    public static String getDirTableName(String bucket) {
        return DIR_TABLE_PREFIX + bucket;
    }

    public static String getObjTableName(String bucket) {
        return OBJ_TABLE_PREFIX + bucket;
    }

    public static String[] getDirColumnFamily() {
        return new String[]{DIR_SUBDIR_CF, DIR_META_CF};
    }

    public static String[] getObjColumnFamily() {
        return new String[]{OBJ_META_CF, OBJ_CONT_CF};
    }
}