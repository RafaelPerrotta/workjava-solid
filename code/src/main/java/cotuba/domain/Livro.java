package cotuba.domain;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Livro {
	
	private FormatoLivro formato;
	
	private Path arquivoDeSaida;
	
	private List<Capitulo> capitulos = new ArrayList<>();
	
	public Livro(FormatoLivro formato, Path arquivoDeSaida) {
		this.formato = formato;
		this.arquivoDeSaida = arquivoDeSaida;
	}

	public FormatoLivro getFormato() {
		return formato;
	}

	public Path getArquivoDeSaida() {
		return arquivoDeSaida;
	}

	public List<Capitulo> getCapitulos() {
		return capitulos;
	}

	public void adicionaCapitulos(List<Capitulo> capitulos) {
		this.capitulos.addAll(capitulos);
	}
	
	public Capitulo ultimoCapitulo () {
		return this.capitulos.get(this.capitulos.size() - 1);
	}
	
}
