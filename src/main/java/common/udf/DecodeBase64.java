package common.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import sun.misc.BASE64Decoder;

import java.util.Iterator;

/**
 * @program: hive_custom_udf
 * @description: Hive解码base64
 * @author: TSL
 * @create: 2019-06-20 16:32
 **/
public class DecodeBase64 extends UDF {

    public static  String evaluate(String base64_str) {

        if (base64_str == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(base64_str);
            return new String(b, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }
}
