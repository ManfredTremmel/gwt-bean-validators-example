package de.knightsoftnet.validationexample.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * The <code>AbstractVersionInfo</code> class provides a view version informations.
 *
 * @author Manfred Tremmel
 */
public abstract class AbstractVersionInfo implements VersionInfoInterface {
  /**
   * version date format.
   */
  private final DateTimeFormat versionDateFormat;

  /**
   * date format to display in dialog.
   */
  private final DateTimeFormat dateFormatDisplay;

  /**
   * default constructor.
   */
  public AbstractVersionInfo() {
    super();
    this.versionDateFormat = DateTimeFormat.getFormat("yyyyMMdd-HHmm");
    this.dateFormatDisplay = DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM);
  }

  /**
   * parse and format a date string.
   *
   * @param pversionDate string with date in versionDateFormat
   * @return the same date formated as dateFormatDisplay
   */
  protected final String parseAndFormatDate(final String pversionDate) {
    Date date;
    if (StringUtils.isEmpty(pversionDate)) {
      date = new Date();
    } else {
      try {
        date = this.versionDateFormat.parse(pversionDate);
      } catch (final IllegalArgumentException e) {
        date = new Date();
      }
    }
    return this.dateFormatDisplay.format(date);
  }
}
