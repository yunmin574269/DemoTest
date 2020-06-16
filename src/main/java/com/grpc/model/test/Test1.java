package com.grpc.model.test;

//import com.company.keystore.wallet.TxUtility;
import com.google.common.annotations.VisibleForTesting;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Test1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

//        System.out.printf("%d ni yaowo a ",1);
//        System.out.println();
//        new Test();
//        new Test();
//        System.out.println(TxUtility.ClientToTransferAccount("5b7514a3d3337022cfaf9619b8d7dc8c5fbbb3c3d942ded3ee240248c0550ad8","7d4d105a3fc6db71d35ed654b1b7aab73d8fa50d", BigDecimal.valueOf(1),"f4b6b5b72dfb8b44241e7ed61e2c61e56e48e8d035650f35b5ebc58981ce009e",1L));


        List<String > size=new ArrayList<String>();
        String des="D:\\IdeaProjects\\well-tech-consensus\\libs";
        String namess="facade-1.0.0.jar";
        @SuppressWarnings("resource")
        JarFile jarFile = new JarFile(des+ File.separator+namess);
        File file=new File(des+File.separator+namess);
        URL url=file.toURI().toURL();
        ClassLoader  loader=new URLClassLoader(new URL[]{url});


        Enumeration<JarEntry> jfs = jarFile.entries();
        StringBuffer sb  = new StringBuffer();
        while(jfs.hasMoreElements()){
            JarEntry jfn = jfs.nextElement();
            if(jfn.getName().endsWith("org/tdf/sunflower/consensus/poa/PoA.class")){
                InputStream is = jarFile.getInputStream(jfn);
                BufferedInputStream bis = new BufferedInputStream(is);
                byte[] buf = new byte[is.available()];
                while(bis.read(buf)!=-1){
                    sb.append(new String(buf).trim());
                }
                bis.close();
                is.close();
                break;
            }
            System.out.println(sb.toString());
        }




    }





}
