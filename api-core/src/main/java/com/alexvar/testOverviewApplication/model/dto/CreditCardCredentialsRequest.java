package com.alexvar.testOverviewApplication.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Model representation of credit card to payments when makes requests.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditCardCredentialsRequest {

  //TODO: Pattern
  String cardNumber;

  //TODO: Pattern
  String dateExpiration;

  String CVC2;

  String owner;
}
