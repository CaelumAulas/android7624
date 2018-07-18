package br.com.caelum.casadocodigo;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.casadocodigo.modelo.Carrinho;
import br.com.caelum.casadocodigo.modelo.Item;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.modelo.TipoDeCompra;

public class CarrinhoTest {

    @Test
    public void adicionaTest() {
        Carrinho carrinho = new Carrinho();
        Livro livro = new Livro();
        livro.setNome("Livro Teste");
        Item item = new Item(livro, TipoDeCompra.FISICO);

        carrinho.adiciona(item);

        Assert.assertTrue("Testa se adicionou",  carrinho.getItens().size() > 0);
        Assert.assertEquals("Livro Teste", carrinho.getItens().get(0).getLivro().getNome());
    }

}
