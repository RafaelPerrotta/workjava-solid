package cotuba.domain;

import cotuba.application.GeradorLivro;
import cotuba.epub.GeradorEPUBComEpubLib;
import cotuba.pdf.GeradorPDFComIText;

public enum FormatoLivro {
	
	PDF(new GeradorPDFComIText()),
	EPUB(new GeradorEPUBComEpubLib());
	
	private GeradorLivro gerador;
	
	private FormatoLivro(GeradorLivro gerador) {
		this.gerador = gerador;
	}
	public GeradorLivro getGerador() {
		return gerador;
	}

}
