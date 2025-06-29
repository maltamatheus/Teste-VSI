package br.com.vsi.utils;

import java.util.*;

public class Features {

    public List<String> gerarCombinacoes(String textIn){

        //Para fins de performance, limitei o tamanho do anagrama em até 5 caracteres
        if(textIn.length() > 5){
            throw new RuntimeException("Favor informar uma sequência de até no máximo 5 caracteres");
        }

        List<String> listaCombinacoes = new ArrayList<>();

        //Constrói o mapa de chave-valor com os caracteres
        Map<Integer,String> mapa = atribuirCodigosPorCaractere(textIn);

        //Invertendo a ordem do mapa para pegar a última combinação final de índices
        Map<Integer,List<String>> inicioEFimCombinacoes = obterIncioEFimCombinacoes(mapa);

        String combinacao = "";

        String inicio = String.join("",inicioEFimCombinacoes.get(1));
        String fim = String.join("",inicioEFimCombinacoes.get(2));

        for (int i = Integer.valueOf(inicio);i<=Integer.valueOf(fim);i++){
            List<String> chaves = Arrays.asList(String.valueOf(i).split(""));
            //Variável criada para não repetir o uso de chaves
            List<String> chavesUsadas = new ArrayList<>();
            for (String chave : chaves) {
                if(mapa.get(Integer.valueOf(chave)) != null && !chavesUsadas.contains(chave)){
                    combinacao += mapa.get(Integer.valueOf(chave));
                    chavesUsadas.add(chave);
                } else {
                    combinacao = "";
                    break;
                }

            }
            if(combinacao.length() == textIn.length()){
                if(!listaCombinacoes.contains(combinacao)){
                    listaCombinacoes.add(combinacao);
                }
                combinacao = "";
            }
        }

        Collections.sort(listaCombinacoes);

        return listaCombinacoes;
    }

    //Usar nos testes
    public Long getTotalDeCombinacoesPossiveis(String textIn){
        long valor = textIn.length();
        Long fatorial = 1l;
        for(long i=valor;i >= 1;i--){
            fatorial *= i;
        }
        return fatorial;
    }

    public Map<Integer,String> atribuirCodigosPorCaractere(String textIn){
        Map<Integer,String> mapa = new HashMap<>();
        String[] chars = textIn.split("");
        for(int i = 1;i<=textIn.length();i++) {
            mapa.put(i,chars[i-1]);
        }
        return mapa;
    }

    private Map<Integer,List<String>> obterIncioEFimCombinacoes(Map<Integer,String> mapa){
        List<String> listaStrChavesAsc = new ArrayList<>();
        List<String> listaStrChavesDesc = new ArrayList<>();
        mapa.keySet().forEach(key -> {
            listaStrChavesAsc.add(String.valueOf(key));
        });
        listaStrChavesDesc.addAll(listaStrChavesAsc);
        Map<Integer,List<String>> mapaRetorno = new HashMap<>();
        Collections.sort(listaStrChavesAsc);
        mapaRetorno.put(1,listaStrChavesAsc);
        Collections.reverse(listaStrChavesDesc);
        mapaRetorno.put(2,listaStrChavesDesc);
        return mapaRetorno;
    }

}
