package cotuba.plugin;

import java.util.ServiceLoader;

import cotuba.domain.Livro;

public interface GerouLivro {

	void aoFinalizarGeracao(Livro livro);

	static void gerou(Livro livro) {
		ServiceLoader.load(GerouLivro.class).forEach(p -> p.aoFinalizarGeracao(livro));
	}

}
