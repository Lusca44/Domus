package br.com.domus.lancamento.resource;

import java.util.List;

/**
 * DTO responsavel por agrupar os dados de update de corretor opcionista em lote
 */
public record InserirCorretorLoteDTO(List<String> leadIds, String corretorId) {

}
