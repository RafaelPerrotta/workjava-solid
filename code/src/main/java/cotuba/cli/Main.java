package cotuba.cli;

import java.nio.file.Path;

import cotuba.application.Cotuba;
import cotuba.domain.FormatoLivro;
import cotuba.estatisticas.ContagemPalavras;

public class Main {

	public static void main(String[] args) {
		
		LeitorOpcoesCLI leitorCLI = new LeitorOpcoesCLI();
		leitorCLI.le(args);

		Path diretorioDosMD = leitorCLI.getDiretorioDosMD();
		FormatoLivro formato = leitorCLI.getFormato();
		Path arquivoDeSaida = leitorCLI.getArquivoDeSaida();
		boolean modoVerboso = leitorCLI.isModoVerboso();
		boolean calcularEstatisticas = leitorCLI.isCalcularEstatisticas();
		
		try {

			Cotuba cotuba = new Cotuba();
			cotuba.executa(diretorioDosMD, arquivoDeSaida, formato, calcularEstatisticas);
			
			System.out.println("Arquivo gerado com sucesso: " + arquivoDeSaida);
			
			if (calcularEstatisticas) {
				System.out.println("Contagem de palavras: ");
				for (ContagemPalavras.Contagem contagem : cotuba.getContagemPalavras()) {
					System.out.println(contagem.getPalavra() + ":\t\t\t\t" + contagem.getQuantidade());
				}
			}
			
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			if (modoVerboso) {
				ex.printStackTrace();
			}
			System.exit(1);
		}
	}

}
