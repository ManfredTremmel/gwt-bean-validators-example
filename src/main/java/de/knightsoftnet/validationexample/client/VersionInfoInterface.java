package de.knightsoftnet.validationexample.client;

import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * The implementation of the <code>VersionInfoInterface</code> should provide version information
 * for the application.
 *
 * @author Manfred Tremmel
 */
public interface VersionInfoInterface {

  /**
   * get the copyright text.
   *
   * @return copyright text
   */
  SafeHtml getCopyrightText();

  /**
   * get the version number.
   *
   * @return version number
   */
  SafeHtml getVersionNumber();

  /**
   * get the version date.
   *
   * @return version date
   */
  SafeHtml getVersionDate();

  /**
   * get the author.
   *
   * @return author
   */
  SafeHtml getAuthor();

}
