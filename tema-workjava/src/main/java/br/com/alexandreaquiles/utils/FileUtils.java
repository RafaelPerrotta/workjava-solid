package br.com.alexandreaquiles.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class FileUtils {

	public static String getResourceContents(String resource) throws URISyntaxException, IOException {
		Path resourcePath = getResourceAsPath(resource);
		return getPathContents(resourcePath);
	}

	private static Path getResourceAsPath(String resource) throws URISyntaxException, IOException {
		URI uri = FileUtils.class.getResource(resource).toURI();

		System.out.println(uri.getScheme());

		if (isResourceInJar(uri)) {
			return getResourceFromJar(uri);
		} else {
			return Paths.get(uri);
		}
	}

	private static boolean isResourceInJar(URI uri) {
		return uri.getScheme().equals("jar");
	}

	private static Path getResourceFromJar(URI fullURI) throws IOException {
		// see http://stackoverflow.com/a/22605905
		String[] uriParts = fullURI.toString().split("!");
		URI jarURI = URI.create(uriParts[0]);

		FileSystem fs;

		try {
			fs = FileSystems.newFileSystem(jarURI, Collections.<String, String>emptyMap());
		} catch (FileSystemAlreadyExistsException ex) {
			fs = FileSystems.getFileSystem(jarURI);
		}
		String resourceURI = uriParts[1];
		return fs.getPath(resourceURI);
	}

	private static String getPathContents(Path path) throws IOException {
		return new String(Files.readAllBytes(path));
	}

}