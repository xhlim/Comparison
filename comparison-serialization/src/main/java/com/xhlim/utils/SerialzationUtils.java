package com.xhlim.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.nustaq.serialization.FSTConfiguration;

import java.io.*;

/**
 * Created by xhlim on 2017/5/5.
 */
public class SerialzationUtils {


    // jdk原生序列化方案
    public static byte[] jdkSerialize(Object obj) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object jdkUnserialize(byte[] bits) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bits);
            ObjectInputStream ois = new ObjectInputStream(bais);

            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // fst序列化方案
    static FSTConfiguration configuration = FSTConfiguration.createStructConfiguration();

    public static byte[] fstSerialize(Object obj) {
        return configuration.asByteArray(obj);
    }

    public static Object fstUnSerialize(byte[] sec) {
        return configuration.asObject(sec);
    }

    // kryo序列化方案
    public static byte[] kryoSerizlize(Object obj) {
        Kryo kryo = new Kryo();
        byte[] buffer = new byte[2048];
        try {
            Output output = new Output(buffer);
            kryo.writeClassAndObject(output, obj);
            return output.toBytes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static Kryo kryo = new Kryo();

    public static Object kryoUnSerizlize(byte[] src) {
        try {
            Input input = new Input(src);
            return kryo.readClassAndObject(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
