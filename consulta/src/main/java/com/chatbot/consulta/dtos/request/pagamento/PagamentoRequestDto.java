package com.chatbot.consulta.dtos.request.pagamento;

import com.chatbot.consulta.enums.TipoCartao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class PagamentoRequestDto {
    @NotNull(message = "O numero do cartão não pode ser nulo.", groups = {CreateCredito.class})
    @Pattern(
            regexp = "\\d{16}",
            message = "O número do cartão deve conter exatamente 16 dígitos.",
            groups = {CreateCredito.class}
    )
    private String numeroCartao;

    @NotBlank(message = "O nome do titular não pode ser vazio.", groups = {CreateCredito.class})
    private String nomeTitular;

    @NotNull(message = "A validade não pode ser nula.", groups = {CreateCredito.class})
    @Pattern(
            regexp = "(0[1-9]|1[0-2])/\\d{2}",
            message = "A validade deve estar no formato MM/YY.",
            groups = {CreateCredito.class}
    )
    private String validade;

    @NotNull(message = "O CVV não pode ser nulo.", groups = {CreateCredito.class})
    @Pattern(
            regexp = "\\d{3}",
            message = "O CVV deve conter exatamente 3 dígitos.",
            groups = {CreateCredito.class}
    )
    private String cvv;

    @NotBlank(message = "O tipo do cartão não pode ser vazio.", groups = {CreateCredito.class})
    private TipoCartao tipoCartao;

}
