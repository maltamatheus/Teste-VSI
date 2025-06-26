package br.com.vsi.utils;

public class Features {

    public Long getFatorial(long valor){
        Long fatorial = 1l;
        for(long i=valor;i >= 1;i--){
            fatorial *= i;
        }
        return fatorial;
    }

    public String getCombinacao(String combinacao){
        String[] split = combinacao.split("");
        for (String s : split) {
            System.out.println(s);
        }
        return "foi";
    }
}
