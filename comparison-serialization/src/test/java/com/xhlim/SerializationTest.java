package com.xhlim;

import com.xhlim.entity.User;
import com.xhlim.utils.SerialzationUtils;

/**
 * Created by xhlim on 2017/5/5.
 */
public class SerializationTest {

    static public void test() {
        System.out.println("序列化：Protobuf 使用");
        User user = new User();
        user.setUserName("张三");
        user.setAge(20);
        user.setPassword("123456");
        System.out.println("序列化、反序列化对比测试：");
        int num = 100000;  // 次数
        long size = 0;
        long jdk = System.nanoTime();
        for (int i = 0; i < num; i++) {
            byte[] jdkSerialize = SerialzationUtils.jdkSerialize(user);
            size += jdkSerialize.length;
            SerialzationUtils.jdkUnserialize(jdkSerialize);
        }
        System.out.println("原生序列化方案[序列化" + num + "次]耗时：" + (System.nanoTime() - jdk) / 1.0 / 1000 / 1000 / 1000 + "s size:=" + size);

        size = 0;
        long fst = System.nanoTime();
        for (int i = 0; i < num; i++) {
            byte[] fstSerialize = SerialzationUtils.fstSerialize(user);
            size += fstSerialize.length;
            SerialzationUtils.fstUnSerialize(fstSerialize);
        }
        System.out.println("fst序列化方案[序列化" + num + "次]耗时：" + (System.nanoTime() - fst) / 1.0 / 1000 / 1000 / 1000 + "s size:=" + size);

        size = 0;
        long kryo = System.nanoTime();
        for (int i = 0; i < num; i++) {
            byte[] serialize = SerialzationUtils.kryoSerizlize(user);
            size += serialize.length;
            SerialzationUtils.kryoUnSerizlize(serialize);
        }
        System.out.println("kryo序列化方案[序列化" + num + "次]耗时：" + (System.nanoTime() - kryo) / 1.0 / 1000 / 1000 / 1000 + "s size:=" + size);

    }

    public static void main(String[] args) {
        test();
    }
}
