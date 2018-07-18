package br.com.caelum.casadocodigo;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.webservices.WebClient;

@RunWith(MockitoJUnitRunner.class)
public class WebClientTest {

    @Mock
    WebClient client;

    @Test
    public void buscaLivrosTest() {
        List<Livro> livros = new ArrayList<>();
        Livro livro = new Livro();
        livro.setNome("Livro 1");
        livros.add(livro);
        Mockito.when(client.buscaLivros()).thenReturn(livros);

        List<Livro> livrosDevolvidos = client.buscaLivros();
        Assert.assertEquals("Livro 1", livrosDevolvidos.get(0).getNome());
    }
}
