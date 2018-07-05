package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.Capitulo;
import cotuba.domain.FormatoLivro;
import cotuba.domain.Livro;
import cotuba.estatisticas.CalculadoraEstatisticas;
import cotuba.estatisticas.ContagemPalavras;
import cotuba.plugin.GerouLivro;

public class Cotuba {

	private ContagemPalavras contagemPalavras;

	public void executa (Path diretorioDosMD, Path arquivoDeSaida, FormatoLivro formato, boolean calcularEstatisticas) {
		
		RenderizadorMD renderizadorMD = RenderizadorMD.cria();
		
		List<Capitulo> capitulos = renderizadorMD.renderiza(diretorioDosMD);
		
		Livro livro = new Livro(formato, arquivoDeSaida);
		livro.adicionaCapitulos(capitulos);
		
		GeradorLivro gerador = formato.getGerador();
		
		gerador.gera(livro);
		
		GerouLivro.gerou(livro);
		
		if (calcularEstatisticas) {
			CalculadoraEstatisticas calculadoraEstatisticas = new CalculadoraEstatisticas();
			contagemPalavras = calculadoraEstatisticas.contaPalavras(livro);
		}

	}

	public ContagemPalavras getContagemPalavras() {
		return contagemPalavras;
	}
	
	
}
