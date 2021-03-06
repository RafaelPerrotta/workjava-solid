package cotuba.cli;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cotuba.domain.FormatoLivro;

class LeitorOpcoesCLI {
	
	private Path diretorioDosMD;
	private FormatoLivro formato;
	private Path arquivoDeSaida;
	private boolean modoVerboso = false;
	private boolean calcularEstatisticas;
	
	public void le (String[] args) {
		Options opcoesCLI = criaOpcoes();

		CommandLineParser cmdParser = new DefaultParser();
		HelpFormatter ajuda = new HelpFormatter();
		CommandLine cmd;

		try {
			cmd = cmdParser.parse(opcoesCLI, args);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			ajuda.printHelp("cotuba", opcoesCLI);
			System.exit(1);
			return;
		}

		
		trataDiretorioDosMD(cmd);

		trataFormato(cmd);

		trataArquivoDeSaida(cmd);

		trataVerbosidade(cmd);
		
		trataEstatisticas(cmd);

	}

	private void trataEstatisticas(CommandLine cmd) {
		calcularEstatisticas = cmd.hasOption("stats");
	}
	
	private void trataVerbosidade(CommandLine cmd) {
		modoVerboso = cmd.hasOption("verbose");
	}

	private void trataArquivoDeSaida(CommandLine cmd) {
		String nomeDoArquivoDeSaidaDoEbook = cmd.getOptionValue("output");
		if (nomeDoArquivoDeSaidaDoEbook != null) {
			arquivoDeSaida = Paths.get(nomeDoArquivoDeSaidaDoEbook);
			if (Files.exists(arquivoDeSaida) && Files.isDirectory(arquivoDeSaida)) {
				throw new RuntimeException(nomeDoArquivoDeSaidaDoEbook + " é um diretório.");
			}
		} else {
			arquivoDeSaida = Paths.get("book." + formato.name().toLowerCase());
		}
	}

	private void trataFormato(CommandLine cmd) {
		String nomeDoFormatoDoEbook = cmd.getOptionValue("format");

		if (nomeDoFormatoDoEbook != null) {
			try {
				formato = FormatoLivro.valueOf(nomeDoFormatoDoEbook.toUpperCase());
			} catch (IllegalArgumentException ex) {
				throw new RuntimeException("O formato " + nomeDoFormatoDoEbook + " não é suportado.", ex);

			}
		} else {
			formato = FormatoLivro.PDF;
		}
	}

	private void trataDiretorioDosMD(CommandLine cmd) {
		String nomeDoDiretorioDosMD = cmd.getOptionValue("dir");

		if (nomeDoDiretorioDosMD != null) {
			diretorioDosMD = Paths.get(nomeDoDiretorioDosMD);
			if (!Files.isDirectory(diretorioDosMD)) {
				throw new RuntimeException(nomeDoDiretorioDosMD + " não é um diretório.");
			}
		} else {
			Path diretorioAtual = Paths.get("");
			diretorioDosMD = diretorioAtual;
		}
	}

	private Options criaOpcoes() {
		Options options = new Options();

		Option opcaoDeDiretorioDosMD = new Option("d", "dir", true,
				"Diretório que contem os arquivos md. Default: diretório atual.");
		options.addOption(opcaoDeDiretorioDosMD);

		Option opcaoDeFormatoDoEbook = new Option("f", "format", true,
				"Formato de saída do ebook. Pode ser: pdf ou epub. Default: pdf");
		options.addOption(opcaoDeFormatoDoEbook);

		Option opcaoDeArquivoDeSaida = new Option("o", "output", true,
				"Arquivo de saída do ebook. Default: book.{formato}.");
		options.addOption(opcaoDeArquivoDeSaida);

		Option opcaoModoVerboso = new Option("v", "verbose", false,
				"Habilita modo verboso.");
		options.addOption(opcaoModoVerboso);
		

		Option opcaoEstatisticas = new Option("s", "stats", false,
				"Habilita geração de estatísticas do livro.");
		options.addOption(opcaoEstatisticas);
		
		Options opcoesCLI = options;
		return opcoesCLI;
	}

	public Path getDiretorioDosMD() {
		return diretorioDosMD;
	}

	public FormatoLivro getFormato() {
		return formato;
	}

	public Path getArquivoDeSaida() {
		return arquivoDeSaida;
	}

	public boolean isModoVerboso() {
		return modoVerboso;
	}
	
	public boolean isCalcularEstatisticas() {
		return calcularEstatisticas;
	}
	
}
