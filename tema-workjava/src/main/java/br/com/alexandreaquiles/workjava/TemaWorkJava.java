package br.com.alexandreaquiles.workjava;

import java.io.IOException;
import java.net.URISyntaxException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import br.com.alexandreaquiles.utils.FileUtils;
import cotuba.domain.Capitulo;
import cotuba.plugin.RenderizouMD;

public class TemaWorkJava implements RenderizouMD {

	@Override
	public void aposRenderizacaoMD(Capitulo capitulo) {

		try {
			String css = FileUtils.getResourceContents("/tema.css");
			
			String html = capitulo.getConteudoHTML();
			Document doc = Jsoup.parse(html);
			doc.select("head").append("<style>" + css+ "</style>");
			capitulo.setConteudoHTML(doc.html());
		
		} catch (URISyntaxException | IOException e) {
			throw new RuntimeException("Erro ao ler arquivo CSS do tema Work Java", e);
		}
		
	}

}
