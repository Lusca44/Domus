package br.com.domus.imagem.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.domus.aplicacao.service.ImovelService;
import br.com.domus.imagem.service.ImageOptimizationService;
import br.com.domus.imagem.service.ImageService;
import br.com.domus.lancamento.service.LancamentoService;

@RestController
@RequestMapping("/imagem")
public class ImageUploadResource {

	@Value("${app.upload.dir}")
	private String uploadDir;

	@Autowired
	private ImageOptimizationService optimizationService;
	
	@Autowired
	private ImageService imagemService;
	
	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private ImovelService imovelService;

	@PostMapping("/salvarImagem")
	public ResponseEntity<String> uploadImagem(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("Arquivo não enviado");
		}

		try {
			Path uploadPath = Paths.get(uploadDir);
			if (Files.notExists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
			Path filePath = uploadPath.resolve(filename);

			// Otimiza e salva a imagem
            byte[] optimizedImage = optimizationService.optimizeImage(file.getBytes());
            Files.write(filePath, optimizedImage);
            
            return ResponseEntity.ok("/uploads/".concat(filename));
		} catch (IOException e) {
			return ResponseEntity.status(500).body("Erro ao salvar arquivo" + e.getMessage());
		}
	}

	@PostMapping("/salvarMultiplas")
	public ResponseEntity<?> uploadMultiplasImagens(@RequestParam("files") MultipartFile[] files) {
		if (files == null || files.length == 0) {
			return ResponseEntity.badRequest().body("Nenhum arquivo enviado");
		}

		List<String> imageUrls = new ArrayList<>();
		Path uploadPath = Paths.get(uploadDir);

		try {
			if (Files.notExists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			for (MultipartFile file : files) {
				if (file.isEmpty()) {
					continue;
				}

				String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
				Path filePath = uploadPath.resolve(filename);

				// Otimiza e salva a imagem
                byte[] optimizedImage = optimizationService.optimizeImage(file.getBytes());
                Files.write(filePath, optimizedImage);
                
                imageUrls.add("/uploads/" + filename);
			}

			return ResponseEntity.ok(imageUrls);
		} catch (IOException e) {
			return ResponseEntity.status(500).body("Erro ao salvar arquivos: " + e.getMessage());
		}
	}

	@PostMapping("/deletarImagem")
	public ResponseEntity<Void> deletarImagem(@RequestBody UrlImagemDTO urlImageDTO) {
		if (urlImageDTO != null && StringUtils.isNotBlank(urlImageDTO.urlImagem())) {
			imagemService.deleteImageFiles(Arrays.asList(urlImageDTO.urlImagem()));

			if (urlImageDTO.isLancamento()) {
				lancamentoService.deleteImagemLancamento(urlImageDTO.itemId(), urlImageDTO.urlImagem());
				return ResponseEntity.ok().build();
			}
			imovelService.deleteUrlImage(urlImageDTO.itemId(), urlImageDTO.urlImagem());
		}
		return ResponseEntity.ok().build();
	}
}
