package br.com.domus.imagem.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

	@Value("${app.upload.dir}")
	private String uploadDir;

	public void deleteImageFiles(List<String> imageUrls) {
		for (String url : imageUrls) {
			try {
				String filename = url.substring(url.lastIndexOf('/') + 1);
				Path filePath = Paths.get(uploadDir, filename);

				if (Files.exists(filePath)) {
					Files.delete(filePath);
				}
			} catch (IOException e) {
				System.err.println("Erro ao deletar arquivo: " + e.getMessage());
			}
		}
	}
}
