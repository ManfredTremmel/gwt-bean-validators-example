package de.knightsoftnet.validationexample.server.controller;

import de.knightsoftnet.validationexample.shared.ResourcePaths;
import de.knightsoftnet.validationexample.shared.models.SepaData;
import de.knightsoftnet.validationexample.shared.models.ValidationDto;
import de.knightsoftnet.validationexample.shared.models.ValidationResultData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@RestController
@RequestMapping(value = ResourcePaths.SEPA, produces = MediaType.APPLICATION_JSON_VALUE)
public class SepaController {

  @Autowired
  private Validator validator;

  @RequestMapping(method = RequestMethod.POST)
  @PermitAll
  ResponseEntity<ValidationResultData> checkSepa(final SepaData psepaData) {
    final ValidationResultData validationResult = new ValidationResultData();
    final Set<ConstraintViolation<SepaData>> cv1 = this.validator.validate(psepaData);

    if (cv1.isEmpty()) {
      // do something usefull
    } else {
      final Iterator<ConstraintViolation<SepaData>> iterator = cv1.iterator();
      while (iterator.hasNext()) {
        final ConstraintViolation<?> violation = iterator.next();
        validationResult.getValidationErrorSet().add(new ValidationDto(violation));
      }
    }
    return new ResponseEntity<>(validationResult, HttpStatus.OK);
  }
}
