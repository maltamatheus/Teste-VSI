package br.com.vsi;

import br.com.vsi.utils.Features;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    Features features = new Features();
    @Test
    public void testaProgramaValorMaiorQue5(){
        features.gerarCombinacoes("abcdef");
    }

    @Test
    public void testaProgramaValorNumerico(){
        features.gerarCombinacoes("abcd0");
    }

    @Test
    public void testaProgramaValorNullOuVazio(){
        features.gerarCombinacoes("  ");
    }

    @Test
    public void testeGeraCombinacao(){
        List<String> combinacoes = features.gerarCombinacoes("abcde");

        combinacoes.forEach(c -> System.out.println(c));
        System.out.println("Total de combinações possíveis é: " + combinacoes.size());
    }
}
