package br.com.vsi;

import br.com.vsi.utils.Features;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Features features = new Features();
        String valor = "a    0";
        features.gerarCombinacoes("abcde");
    }
}
