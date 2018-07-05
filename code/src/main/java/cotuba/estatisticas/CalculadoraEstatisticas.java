package cotuba.estatisticas;

import com.itextpdf.html2pdf.jsoup.Jsoup;
import com.itextpdf.html2pdf.jsoup.nodes.Document;

import cotuba.domain.Capitulo;
import cotuba.domain.Livro;

public class CalculadoraEstatisticas {

	public ContagemPalavras contaPalavras(Livro livro) {
		ContagemPalavras contagem = new ContagemPalavras();
		for (Capitulo capitulo : livro.getCapitulos()) {
			String html = capitulo.getConteudoHTML();
			Document doc = Jsoup.parse(html);
			String textoCapitulo = doc.body().text();
			for (String palavra : textoCapitulo.split("\\s+")) {
				contagem.adicionaPalavra(palavra);
			}
		}
		return contagem;
	}

}
