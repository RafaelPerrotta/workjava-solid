package cotuba.plugin;

import java.util.ServiceLoader;

import cotuba.domain.Capitulo;

public interface RenderizouMD {

	void aposRenderizacaoMD(Capitulo capitulo);
	
	static void renderizouMD(Capitulo capitulo) {
		ServiceLoader.load(RenderizouMD.class)
			.forEach(p -> p.aposRenderizacaoMD(capitulo));
	}
}
