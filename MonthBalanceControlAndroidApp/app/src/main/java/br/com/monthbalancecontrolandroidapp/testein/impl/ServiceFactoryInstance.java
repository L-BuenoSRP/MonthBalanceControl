package br.com.monthbalancecontrolandroidapp.testein.impl;

import br.com.monthbalancecontrolandroidapp.other.pckg.OtherPackageTeste;

public class ServiceFactoryInstance {
    private static ServiceFactoryInstance instance = null;
    private boolean callThisPackage = true;
    private ServiceFactoryInstance() {}

    public static ServiceFactoryInstance getInstance() {
        if (instance == null) {
//            synchronized(ServiceFactoryInstance.class) {
                instance = new ServiceFactoryInstance();
//            }
        }
        return instance;
    }


    public ClassMainTeste getClassImpl(){
        return callThisPackage  ? new ThisPackageTeste() : new OtherPackageTeste();
    }
}
