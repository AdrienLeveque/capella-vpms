/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
 *  
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.vp.ms.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.vp.ms.ui.messages"; //$NON-NLS-1$
  public static String ConfigurationContentFilter_showComponents;
  public static String ConfigurationContentFilter_showFunctionalChains;
  public static String ConfigurationContentFilter_showFunctions;
  public static String ConfigurationContentFilter_showPorts;
  public static String ConfigurationContentFilter_showScenarios;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
