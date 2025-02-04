package com.example.fetchtakehomeexercise.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiptProcessingRequest {

    @NotNull
    @JsonProperty("retailer")
    @Pattern(regexp = "^[\\w\\s\\-&]+$")
    private String retailer;

    @NotNull
    @JsonProperty("purchaseDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate;

    @NotNull
    @JsonProperty("purchaseTime")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime purchaseTime;

    @Valid
    @NotNull
    @JsonProperty("items")
    private List<Item> items;

    @NotNull
    @JsonProperty("total")
    @Pattern(regexp = "^\\d+\\.\\d{2}$")
    private String total;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Item {

        @NotNull
        @JsonProperty("shortDescription")
        @Pattern(regexp = "^[\\w\\s\\-]+$")
        private String shortDescription;

        @NotNull
        @JsonProperty("price")
        @Pattern(regexp = "^\\d+\\.\\d{2}$")
        private String price;
    }
}
