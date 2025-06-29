package br.com.vsi.utils;

import java.util.*;

public class Features {

    public List<String> gerarCombinacoes(String valorInformado) throws RuntimeException{
        // Método criado para gerar as combinações possíveis
        // Para fins de performace, limitei o tamanho da string em até 5 caracteres

        //Inicialmente, removo espaços em branco
        String textIn = valorInformado.replaceAll("\\s+", "");

        //Em seguida, faço as validações
        if(!valorValido(textIn)){
            throw new RuntimeException("Favor informar um valor válido");
        }

        //Inicializo a variável que receberá a lista de combinações
        List<String> listaCombinacoes = new ArrayList<>();

        //Faço um mapa dos caracteres informados, atribuindo a eles uma chave
        //Constrói o mapa de chave-valor com os caracteres
        Map<Integer,String> mapa = atribuirCodigosPorCaractere(textIn);

        //Invertendo a ordem do mapa para pegar a última combinação final de índices
        Map<Integer,List<String>> inicioEFimCombinacoes = obterIncioEFimCombinacoes(mapa);

        String combinacao = "";

        //Obtenho o ponto de início e ponto fim das possíveis combinações
        String inicio = String.join("",inicioEFimCombinacoes.get(1));
        String fim = String.join("",inicioEFimCombinacoes.get(2));

        //Com os caracteres informados, valido um a um para saber como e quando poderá ser atribuído
        // à combinação
        for (int i = Integer.valueOf(inicio);i<=Integer.valueOf(fim);i++){
            List<String> chaves = Arrays.asList(String.valueOf(i).split(""));
            //Variável criada para não repetir o uso de chaves
            List<String> chavesUsadas = new ArrayList<>();
            for (String chave : chaves) {
                if(mapa.get(Integer.valueOf(chave)) != null && !chavesUsadas.contains(chave)){
                    //Monto a combinação
                    combinacao += mapa.get(Integer.valueOf(chave));
                    chavesUsadas.add(chave);
                } else {
                    //Se combinação inválida, interrompo e passo para a próxima possibilidade
                    combinacao = "";
                    break;
                }

            }
            //Se combinação válida, adiciono à lista e vou para a próxima
            if(combinacao.length() == textIn.length()){
                if(!listaCombinacoes.contains(combinacao)){
                    listaCombinacoes.add(combinacao);
                }
                combinacao = "";
            }
        }

        //Ordeno a lista final das combinações geradas
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

    public boolean valorMaiorQue1MenorQue5(String valor){
        return valor.length() <= 1 || valor.length() > 5;
    }

    public boolean possuiValorNumerico(String valor){
        return valor.matches(".*\\d.*");
    }

    public boolean possuiValorNulo(String valor){
        return Objects.isNull(valor);
    }

    public boolean possuiValorVazio(String valor){
        return valor.isEmpty();
    }

    public boolean valorValido(String textIn){

        //Por questões de performance, limitei o tamanho do anagrama em até 5 caracteres
        if(valorMaiorQue1MenorQue5(textIn)){
            throw new RuntimeException("Favor informar uma sequência de, no mínimo, 2 e no máximo 5 caracteres");
        }

        if(possuiValorNumerico(textIn)){
            throw new RuntimeException("Apenas letras são permitidas");
        }

        if (possuiValorNulo(textIn) || possuiValorVazio(textIn)){
            return false;
        }

        return true;
    }
}
