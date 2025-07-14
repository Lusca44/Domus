package br.com.domus.aplicacao.controller;

/**
 * DTO responsavel por pegar os dados para atualizacao de senha do usuario;
 */
public record EditarSenhaDTO(String email, String senha, String senhaNova) {
}