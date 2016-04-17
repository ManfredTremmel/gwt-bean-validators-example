package de.knightsoftnet.validationexample.shared.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.ConstraintViolation;

@JsonFormat
public class ValidationDto {

  private String message;
  private String propertyPath;

  /**
   * default constructor.
   */
  public ValidationDto() {
    super();
  }

  /**
   * constructor fills data from violation.
   *
   * @param pviolation violation to fill date from
   */
  public ValidationDto(final ConstraintViolation<?> pviolation) {
    super();
    this.message = pviolation.getMessage();
    this.propertyPath = pviolation.getPropertyPath().toString();
  }

  public final String getMessage() {
    return this.message;
  }

  public final void setMessage(final String pmessage) {
    this.message = pmessage;
  }

  public final String getPropertyPath() {
    return this.propertyPath;
  }

  public final void setPropertyPath(final String ppropertyPath) {
    this.propertyPath = ppropertyPath;
  }
}
