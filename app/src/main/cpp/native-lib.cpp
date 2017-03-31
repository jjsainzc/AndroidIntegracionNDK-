#include <jni.h>
#include <string>
#include <sstream>

std::string FloatToStr(float number) {
    std::ostringstream buff;
    buff << number;
    return buff.str();
}

class Bomba {
private:
    float lit, plit, cant, total;
public:
    Bomba() {
        lit = 900;    //constructor
        plit = 6.4;
        cant = 0;
        total = 0.0;
    }

    virtual ~Bomba();

    std::string despliega();

    std::string despacha(float);
};

std::string Bomba::despliega() {
    std::string resultado;

    resultado = "\nCantidad disponible:" + FloatToStr(lit) + " Lts";
    resultado += "\nPrecio por litro $:" + FloatToStr(plit);

    return resultado;
}

std::string Bomba::despacha(float x) {
    float w, v;
    std::string resultado;

    if (lit < x) {
        resultado = "No es posible suministrar la cantidad solicitada\n";
        resultado += "Solo hay " + FloatToStr(lit) + " litros disponibles";
    } else if (lit >= x) {
        lit = lit - x;
        total = plit * x;
        resultado = "Litros vendidos: " + FloatToStr(x);
        resultado += "\nLitros disponibles: " + FloatToStr(lit);
        resultado += "\nCantidad a pagar $ " + FloatToStr(total);
    }
    return resultado;
}

Bomba::~Bomba() {

}


extern "C" {
JNIEXPORT jstring JNICALL
Java_aplicaciones_sainz_jesus_jorge_myapplication_jnilibs_JNIFunciones_consulta(JNIEnv *env, jobject obj) {


    Bomba b;
    std::string resultado = b.despliega();
    return env->NewStringUTF(resultado.c_str());
}

JNIEXPORT jstring JNICALL
Java_aplicaciones_sainz_jesus_jorge_myapplication_jnilibs_JNIFunciones_despacha(JNIEnv *env, jobject obj,
                                                                        jfloat c) {
    float f = c;
    Bomba b;
    std::string resultado = b.despacha(f);
    return env->NewStringUTF(resultado.c_str());
}
}