package br.com.domus.aplicacao.emailsender.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import br.com.domus.aplicacao.emailsender.dto.ImovelAnunciosSiteDTO;

@RestController
@RequestMapping("/imovel-anuncio")
public class ImovelAnuncioResource {

	@Value("${sendgrid.api.key}")
	private String sendGridApiKey;

	@Value("${sendgrid.from.email}")
	private String fromEmail;

	private final String toEmail = "Feitozaimoveis@yahoo.com";

	@PostMapping("/enviar-email")
	public ResponseEntity<String> enviarImovelAnuncioProEmail(@RequestBody ImovelAnunciosSiteDTO imovelAnuncioSiteDTO) {
		try {
			enviarEmail(imovelAnuncioSiteDTO);
			return ResponseEntity.ok("Anúncio recebido com sucesso! Entraremos em contato em breve.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao enviar email: " + e.getMessage());
		}
	}

	private void enviarEmail(ImovelAnunciosSiteDTO imovelAnuncioSite) throws Exception {
		Email from = new Email(fromEmail);
		String subject = "Novo Anúncio: " + imovelAnuncioSite.tipo() + " para " + imovelAnuncioSite.finalidade();
		Email to = new Email(toEmail);
		Content content = new Content("text/html", construirConteudoEmail(imovelAnuncioSite));

		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(sendGridApiKey);
		Request request = new Request();

		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);

			if (response.getStatusCode() < 200 || response.getStatusCode() >= 300) {
				throw new Exception("SendGrid error: " + response.getBody());
			}
		} catch (Exception ex) {
			throw new Exception("Erro ao enviar email via SendGrid: " + ex.getMessage());
		}
	}

	private String construirConteudoEmail(ImovelAnunciosSiteDTO imovelAnuncioSite) {
		return """
				<!DOCTYPE html>
				<html>
				<head>
				    <style>
				        body { font-family: Arial, sans-serif; }
				        .container { max-width: 600px; margin: 0 auto; }
				        .header { background-color: #f8f9fa; padding: 20px; text-align: center; }
				        .content { padding: 30px; background-color: #ffffff; }
				        .section { margin-bottom: 20px; }
				        .section-title { font-weight: bold; color: #2c3e50; border-bottom: 1px solid #eee; padding-bottom: 5px; }
				        .field { margin-bottom: 10px; }
				        .label { font-weight: bold; display: inline-block; width: 150px; }
				    </style>
				</head>
				<body>
				    <div class="container">
				        <div class="header">
				            <h2>Quero anunciar meu imovel com vocês!</h2>
				        </div>

				        <div class="content">
				            <div class="section">
				                <div class="section-title">Dados Pessoais</div>
				                <div class="field"><span class="label">Nome:</span> %s</div>
				                <div class="field"><span class="label">Telefone:</span> %s</div>
				                <div class="field"><span class="label">Email:</span> %s</div>
				            </div>

				            <div class="section">
				                <div class="section-title">Dados do Imóvel</div>
				                <div class="field"><span class="label">Finalidade:</span> %s</div>
				                <div class="field"><span class="label">Tipo:</span> %s</div>
				                <div class="field"><span class="label">Endereço:</span> %s</div>
				                <div class="field"><span class="label">Bairro:</span> %s</div>
				                <div class="field"><span class="label">Cidade:</span> %s</div>
				                <div class="field"><span class="label">CEP:</span> %s</div>
				            </div>

				            <div class="section">
				                <div class="section-title">Características</div>
				                <div class="field"><span class="label">Quartos:</span> %s</div>
				                <div class="field"><span class="label">Banheiros:</span> %s</div>
				                <div class="field"><span class="label">Vagas:</span> %s</div>
				                <div class="field"><span class="label">Área (m²):</span> %s</div>
				                <div class="field"><span class="label">Valor (R$):</span> %s</div>
				            </div>

				            <div class="section">
				                <div class="section-title">Descrição</div>
				                <p>%s</p>
				            </div>

				            <div class="section">
				                <div class="section-title">Observações</div>
				                <p>%s</p>
				            </div>
				        </div>
				    </div>
				</body>
				</html>
				"""
				.formatted(imovelAnuncioSite.nome(), //
						imovelAnuncioSite.telefone(), //
						imovelAnuncioSite.email(), //
						imovelAnuncioSite.finalidade(), //
						imovelAnuncioSite.tipo(), //
						imovelAnuncioSite.endereco(), //
						imovelAnuncioSite.bairro(), //
						imovelAnuncioSite.cidade(), //
						imovelAnuncioSite.cep(), //
						imovelAnuncioSite.quartos(), //
						imovelAnuncioSite.banheiros(), //
						imovelAnuncioSite.vagas(), //
						imovelAnuncioSite.area(), //
						imovelAnuncioSite.valor(), //
						imovelAnuncioSite.descricao(), //
						imovelAnuncioSite.observacoes());
	}
}