package common.udf;

import java.io.File;
import java.io.IOException;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.City;
import org.apache.hadoop.hive.ql.exec.UDF;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 使用前提：
 * create temporary function ip_analyse as 'common.udf.IP2Location' using 'hdfs:///jars/hive-custom-udf-2.1-jar-with-dependencies.jar'
 * set mapred.cache.files=/data/ip/GeoLite2-City.mmdb#GeoLite2-City.mmdb;
 *
 * 开始使用：
 * select ip_analyse('ip','0'); --> 国家
 * select ip_analyse('ip','1'); --> 城市
 * select ip_analyse('ip','2'); --> 国家:城市
 */

/**
 * @program: hive_custom_udf
 * @description: 解析 IP 到城市信息
 * @author: TSL
 * @create: 2019-07-08 10:25
 **/
public class IP2Location extends UDF{

    /**
     * @param ip ip地址
     * @param type 解析类型：0 国家, 1 城市, 3 全部
     * @return
     * @throws IOException
     * @throws GeoIp2Exception
     */
    public static String evaluate(String ip,String type) {
        return IPAnalyse(ip,type);
    }


    /**
     * @param ip  ip地址
     * @return 返回字符串
     * @throws IOException
     * @throws GeoIp2Exception
     */
    public  static String IPAnalyse(String ip,String type){

        String res = "";

        // geoip2或geolite2数据库的文件

        File database  = new File("GeoLite2-City.mmdb");

        //这将创建databasereader对象。为了提高性能，重用跨查找的对象。对象是线程安全的
        DatabaseReader reader = null;
        try {
            reader = new DatabaseReader.Builder(database).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 构造 ip 对象
        InetAddress ipAddress = null;
        try {
            ipAddress = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // 构造响应对象
        CityResponse response = null;
        try {
            response = reader.city(ipAddress);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
        }

        // 返回不同类型的数据,具体到城市
        if ("0".equals(type)){
            Country country = response.getCountry();
            res = country.getNames().get("zh-CN");
        }else if("1".equals(type)){
            City city = response.getCity();
            res = city.getNames().get("zh-CN");
        }else if ("2".equals(type)){
            Country country = response.getCountry();
            City city = response.getCity();
            res = country.getNames().get("zh-CN")+":"+city.getNames().get("zh-CN");
        }else {
            res = "error";

        }
        return res;

    }

}
