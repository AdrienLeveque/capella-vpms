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
package org.polarsys.capella.vp.ms.helpers;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.vp.ms.NotOperation;

/**
 * @generated
 */
public class NotOperationHelper {

  private static final NotOperationHelper instance = new NotOperationHelper();

  /**
   * @generated
   */
  public static NotOperationHelper getInstance() {
    return instance;
  }

  /**
   * @generated
   */
  public Object doSwitch(NotOperation object, EStructuralFeature feature) {
    // handle derivated feature

    // delegate to parent helper
    return org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper.getInstance()
        .doSwitch(object, feature);

  }

}