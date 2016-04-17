package de.knightsoftnet.validationexample.shared.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.List;

@JsonFormat
public class ValidationResultData {
  /**
   * validation result, should be empty if validation is ok.
   */
  private List<ValidationDto> validationErrorSet = new ArrayList<>();

  public final List<ValidationDto> getValidationErrorSet() {
    return this.validationErrorSet;
  }

  public final void setValidationErrorSet(final List<ValidationDto> pvalidationErrorSet) {
    this.validationErrorSet = pvalidationErrorSet;
  }
}
