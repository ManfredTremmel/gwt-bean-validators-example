package de.knightsoftnet.validationexample.server.controller;

import de.knightsoftnet.validators.shared.data.PhoneNumberData;
import de.knightsoftnet.validators.shared.data.PhoneNumberDataWithFormats;
import de.knightsoftnet.validators.shared.data.ValueWithPos;
import de.knightsoftnet.validators.shared.data.ValueWithPosAndCountry;
import de.knightsoftnet.validators.shared.util.LocaleUtil;
import de.knightsoftnet.validators.shared.util.PhoneNumberUtil;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

import java.util.List;

@RestController
@RequestMapping("gwtBeanValidatorsExample/api/phonenumber")
public class PhoneNumberServiceController {

  /**
   * phone number utils to test phone numbers.
   */
  private final PhoneNumberUtil phoneNumberUtil = new PhoneNumberUtil();

  @RequestMapping(value = "/parsePhoneNumber", method = RequestMethod.GET)
  @PermitAll
  public PhoneNumberData parsePhoneNumber(@RequestParam(value = "language", required = true) final String planguage,
      @RequestParam(value = "country", required = true) final String pcountry,
      @RequestParam(value = "phonenumber", required = true) final String pphoneNumber) {
    return this.phoneNumberUtil.parsePhoneNumber(pphoneNumber, pcountry, LocaleUtil.convertLanguageToLocale(planguage));
  }

  @RequestMapping(value = "/parsewithpos", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<PhoneNumberData> parsePhoneNumber(@RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.phoneNumberUtil.parsePhoneNumber(pphoneNumber, pphoneNumber.getCountry());
  }

  /**
   * parse and reformat the phone number in all available formats.
   *
   * @param planguage language to use
   * @param pcountry default country
   * @param pphoneNumber phone number to format
   * @return PhoneNumberDataWithFormats
   */
  @RequestMapping(value = "/parseandformat", method = RequestMethod.GET)
  @PermitAll
  public PhoneNumberDataWithFormats parseAndFormatPhoneNumber(
      @RequestParam(value = "language", required = true) final String planguage,
      @RequestParam(value = "country", required = true) final String pcountry,
      @RequestParam(value = "phonenumber", required = true) final String pphoneNumber) {
    final PhoneNumberDataWithFormats result = new PhoneNumberDataWithFormats(
        this.phoneNumberUtil.parsePhoneNumber(pphoneNumber, pcountry, LocaleUtil.convertLanguageToLocale(planguage)));
    result.setCommonInternational(this.phoneNumberUtil.formatCommonInternational(result));
    result.setCommonNational(this.phoneNumberUtil.formatCommonNational(result));
    result.setDin5008International(this.phoneNumberUtil.formatDin5008International(result));
    result.setDin5008National(this.phoneNumberUtil.formatDin5008National(result));
    result.setE123International(this.phoneNumberUtil.formatE123International(result));
    result.setE123National(this.phoneNumberUtil.formatE123National(result));
    result.setMs(this.phoneNumberUtil.formatMs(result));
    result.setUrl(this.phoneNumberUtil.formatUrl(result));
    return result;
  }


  @RequestMapping(value = "/formate123", method = RequestMethod.GET)
  @PermitAll
  public String formatE123(@RequestParam(value = "language", required = true) final String planguage,
      @RequestParam(value = "country", required = true) final String pcountry,
      @RequestParam(value = "phonenumber", required = true) final String pphoneNumber) {
    return this.phoneNumberUtil.formatE123(pphoneNumber, pcountry);
  }

  @RequestMapping(value = "/formate123withpos", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatE123WithPos(@RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.valueWithPosDefaults(this.phoneNumberUtil.formatE123WithPos( //
        pphoneNumber, pphoneNumber.getCountry()), pphoneNumber);
  }

  @RequestMapping(value = "/formate123international", method = RequestMethod.GET)
  @PermitAll
  public String formatE123International(@RequestParam(value = "language", required = true) final String planguage,
      @RequestParam(value = "country", required = true) final String pcountry,
      @RequestParam(value = "phonenumber", required = true) final String pphoneNumber) {
    return this.phoneNumberUtil.formatE123International(pphoneNumber, pcountry);
  }

  @RequestMapping(value = "/formate123internationalwithpos", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatE123InternationalWithPos(@RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.valueWithPosDefaults(this.phoneNumberUtil.formatE123InternationalWithPos( //
        pphoneNumber, pphoneNumber.getCountry()), pphoneNumber);
  }

  @RequestMapping(value = "/formate123national", method = RequestMethod.GET)
  @PermitAll
  public String formatE123National(@RequestParam(value = "language", required = true) final String planguage,
      @RequestParam(value = "country", required = true) final String pcountry,
      @RequestParam(value = "phonenumber", required = true) final String pphoneNumber) {
    return this.phoneNumberUtil.formatE123National(pphoneNumber, pcountry);
  }

  @RequestMapping(value = "/formate123nationalwithpos", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatE123NationalWithPos(@RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.valueWithPosDefaults(this.phoneNumberUtil.formatE123NationalWithPos( //
        pphoneNumber, pphoneNumber.getCountry()), pphoneNumber);
  }


  @RequestMapping(value = "/formatdin5008", method = RequestMethod.GET)
  @PermitAll
  public String formatDin5008(@RequestParam(value = "language", required = true) final String planguage,
      @RequestParam(value = "country", required = true) final String pcountry,
      @RequestParam(value = "phonenumber", required = true) final String pphoneNumber) {
    return this.phoneNumberUtil.formatDin5008(pphoneNumber, pcountry);
  }

  @RequestMapping(value = "/formatdin5008withpos", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatDin5008WithPos(@RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.valueWithPosDefaults(this.phoneNumberUtil.formatDin5008WithPos( //
        pphoneNumber, pphoneNumber.getCountry()), pphoneNumber);
  }

  @RequestMapping(value = "/formatdin5008international", method = RequestMethod.GET)
  @PermitAll
  public String formatDin5008International(@RequestParam(value = "language", required = true) final String planguage,
      @RequestParam(value = "country", required = true) final String pcountry,
      @RequestParam(value = "phonenumber", required = true) final String pphoneNumber) {
    return this.phoneNumberUtil.formatDin5008International(pphoneNumber, pcountry);
  }

  @RequestMapping(value = "/formatdin5008internationalwithpos", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatDin5008InternationalWithPos(
      @RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.valueWithPosDefaults(this.phoneNumberUtil.formatDin5008InternationalWithPos( //
        pphoneNumber, pphoneNumber.getCountry()), pphoneNumber);
  }

  @RequestMapping(value = "/formatdin5008national", method = RequestMethod.GET)
  @PermitAll
  public String formatDin5008National(@RequestParam(value = "language", required = true) final String planguage,
      @RequestParam(value = "country", required = true) final String pcountry,
      @RequestParam(value = "phonenumber", required = true) final String pphoneNumber) {
    return this.phoneNumberUtil.formatDin5008National(pphoneNumber, pcountry);
  }

  @RequestMapping(value = "/formatdin5008nationalwithpos", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatDin5008NationalWithPos(@RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.valueWithPosDefaults(this.phoneNumberUtil.formatDin5008NationalWithPos( //
        pphoneNumber, pphoneNumber.getCountry()), pphoneNumber);
  }


  @RequestMapping(value = "/formatrfc3966", method = RequestMethod.GET)
  @PermitAll
  public String formatRfc3966(@RequestParam(value = "language", required = true) final String planguage,
      @RequestParam(value = "country", required = true) final String pcountry,
      @RequestParam(value = "phonenumber", required = true) final String pphoneNumber) {
    return this.phoneNumberUtil.formatRfc3966(pphoneNumber, pcountry);
  }

  @RequestMapping(value = "/formatrfc3966withpos", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatRfc3966WithPos(@RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.valueWithPosDefaults(this.phoneNumberUtil.formatRfc3966WithPos( //
        pphoneNumber, pphoneNumber.getCountry()), pphoneNumber);
  }


  @RequestMapping(value = "/formatms", method = RequestMethod.GET)
  @PermitAll
  public String formatMs(@RequestParam(value = "language", required = true) final String planguage,
      @RequestParam(value = "country", required = true) final String pcountry,
      @RequestParam(value = "phonenumber", required = true) final String pphoneNumber) {
    return this.phoneNumberUtil.formatMs(pphoneNumber, pcountry);
  }

  @RequestMapping(value = "/formatmswithpos", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatMsWithPos(@RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.valueWithPosDefaults(this.phoneNumberUtil.formatMsWithPos( //
        pphoneNumber, pphoneNumber.getCountry()), pphoneNumber);
  }


  @RequestMapping(value = "/formaturl", method = RequestMethod.GET)
  @PermitAll
  public String formatUrl(@RequestParam(value = "language", required = true) final String planguage,
      @RequestParam(value = "country", required = true) final String pcountry,
      @RequestParam(value = "phonenumber", required = true) final String pphoneNumber) {
    return this.phoneNumberUtil.formatUrl(pphoneNumber, pcountry);
  }

  @RequestMapping(value = "/formaturlwithpos", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatUrlWithPos(@RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.valueWithPosDefaults(this.phoneNumberUtil.formatUrlWithPos( //
        pphoneNumber, pphoneNumber.getCountry()), pphoneNumber);
  }


  @RequestMapping(value = "/formatcommon", method = RequestMethod.GET)
  @PermitAll
  public String formatCommon(@RequestParam(value = "language", required = true) final String planguage,
      @RequestParam(value = "country", required = true) final String pcountry,
      @RequestParam(value = "phonenumber", required = true) final String pphoneNumber) {
    return this.phoneNumberUtil.formatCommon(pphoneNumber, pcountry);
  }

  @RequestMapping(value = "/formatcommonwithpos", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatCommonWithPos(@RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.valueWithPosDefaults(this.phoneNumberUtil.formatCommonWithPos( //
        pphoneNumber, pphoneNumber.getCountry()), pphoneNumber);
  }


  @RequestMapping(value = "/formatcommoninternational", method = RequestMethod.GET)
  @PermitAll
  public String formatCommonInternational(@RequestParam(value = "language", required = true) final String planguage,
      @RequestParam(value = "country", required = true) final String pcountry,
      @RequestParam(value = "phonenumber", required = true) final String pphoneNumber) {
    return this.phoneNumberUtil.formatCommonInternational(pphoneNumber, pcountry);
  }

  @RequestMapping(value = "/formatcommoninternationalwithpos", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatCommonInternationalWithPos(@RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.valueWithPosDefaults(this.phoneNumberUtil.formatCommonInternationalWithPos( //
        pphoneNumber, pphoneNumber.getCountry()), pphoneNumber);
  }

  @RequestMapping(value = "/formatcommonnational", method = RequestMethod.GET)
  @PermitAll
  public String formatCommonNational(@RequestParam(value = "language", required = true) final String planguage,
      @RequestParam(value = "country", required = true) final String pcountry,
      @RequestParam(value = "phonenumber", required = true) final String pphoneNumber) {
    return this.phoneNumberUtil.formatCommonNational(pphoneNumber, pcountry);
  }

  @RequestMapping(value = "/formatcommonnationalwithpos", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatCommonNationalWithPos(@RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.valueWithPosDefaults(this.phoneNumberUtil.formatCommonNationalWithPos( //
        pphoneNumber, pphoneNumber.getCountry()), pphoneNumber);
  }

  private ValueWithPos<String> valueWithPosDefaults(final ValueWithPos<String> pformatValueWithPos,
      final ValueWithPosAndCountry<String> pdefaultNumber) {
    if (StringUtils.isEmpty(pformatValueWithPos.getValue()) //
        || StringUtils.startsWith(pdefaultNumber.getValue(), pformatValueWithPos.getValue())
            && !Character.isDigit(pdefaultNumber.getValue().charAt(StringUtils.length(pdefaultNumber.getValue()) - 1))) {
      pformatValueWithPos.setValue(pdefaultNumber.getValue());
      pformatValueWithPos.setPos(pdefaultNumber.getPos());
    }
    return pformatValueWithPos;
  }


  @RequestMapping(value = "/getsuggestions", method = RequestMethod.GET)
  @PermitAll
  public List<PhoneNumberData> getSuggestions(@RequestParam(value = "language", required = true) final String planguage,
      @RequestParam(value = "search", required = true) final String psearch,
      @RequestParam(value = "limit", required = true) final int plimit) {
    return this.phoneNumberUtil.getSuggstions(psearch, plimit, LocaleUtil.convertLanguageToLocale(planguage));
  }


  /**
   * validate a phone number.
   *
   * @param pcountry default country
   * @param pphoneNumber phone number to check
   * @param pdin5008 set to true if DIN 5008 format is allowed
   * @param pe123 set to true if E123 format is allowed
   * @param puri set to true if URI format is allowed
   * @param pms set to true if Microsoft format is allowed
   * @param pcommon set to true if common format is allowed
   * @return true if number is valid
   */
  @RequestMapping(value = "/validate", method = RequestMethod.GET)
  @PermitAll
  public Boolean validate(@RequestParam(value = "country", required = true) final String pcountry,
      @RequestParam(value = "phonenumber", required = true) final String pphoneNumber,
      @RequestParam(value = "din5008", required = false) final Boolean pdin5008,
      @RequestParam(value = "e123", required = false) final Boolean pe123,
      @RequestParam(value = "uri", required = false) final Boolean puri,
      @RequestParam(value = "ms", required = false) final Boolean pms,
      @RequestParam(value = "common", required = false) final Boolean pcommon) {
    final PhoneNumberData parsedNumber = this.phoneNumberUtil.parsePhoneNumber(pphoneNumber, pcountry);
    if (parsedNumber.isValid()) {
      if (BooleanUtils.isTrue(pdin5008)
          && (StringUtils.equals(pphoneNumber, this.phoneNumberUtil.formatDin5008National(parsedNumber))
              || StringUtils.equals(pphoneNumber, this.phoneNumberUtil.formatDin5008International(parsedNumber)))) {
        return Boolean.TRUE;
      }
      if (BooleanUtils.isTrue(pe123) && (StringUtils.equals(pphoneNumber, this.phoneNumberUtil.formatE123National(parsedNumber))
          || StringUtils.equals(pphoneNumber, this.phoneNumberUtil.formatE123International(parsedNumber)))) {
        return Boolean.TRUE;
      }
      if (BooleanUtils.isTrue(puri) && StringUtils.equals(pphoneNumber, this.phoneNumberUtil.formatUrl(parsedNumber))) {
        return Boolean.TRUE;
      }
      if (BooleanUtils.isTrue(pms) && StringUtils.equals(pphoneNumber, this.phoneNumberUtil.formatMs(parsedNumber))) {
        return Boolean.TRUE;
      }
      if (BooleanUtils.isTrue(pcommon)
          && (StringUtils.equals(pphoneNumber, this.phoneNumberUtil.formatCommonNational(parsedNumber))
              || StringUtils.equals(pphoneNumber, this.phoneNumberUtil.formatCommonInternational(parsedNumber)))) {
        return Boolean.TRUE;
      }
    }
    return Boolean.FALSE;
  }
}
