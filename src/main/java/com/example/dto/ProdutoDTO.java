package com.example.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProdutoDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100)
    public String nome;
    
    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    public BigDecimal preco;
}