/*
 * Copyright (c) 2017 .Jorge Jesus Sainz Casalla
 * Ultima modificacion: 3/28/17 2:01 PM
 */

package aplicaciones.sainz.jesus.jorge.myapplication.jnilibs;

public class JNIFunciones {

    public native static String consulta();
    public native static String despacha(float f);

    static {
        System.loadLibrary("native-lib");
    }
}
