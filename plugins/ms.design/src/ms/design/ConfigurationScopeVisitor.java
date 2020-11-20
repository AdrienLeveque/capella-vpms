/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package ms.design;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.ui.css.CSSAdapter;

import ms.configuration.services.cs.CsConfigurationServices;

public class ConfigurationScopeVisitor implements ScopeVisitor<Collection<CSConfiguration>> {

  private static final CsConfigurationServices cs = new CsConfigurationServices();

  private final static String KEY_CONFIGURATIONS = "vpms_Configurations";
  private final static String KEY_ALL_CONFIGURATIONS = "vpms_AllConfigurations";

  @Override
  public Collection<CSConfiguration> visitDiagramScope(DiagramScope ds) {
    updateConfigurationsAttribute(ds, cs.getSelectedConfigurations((DSemanticDiagram) ds.getElement()));
    return getAllParentScopeConfigurations(ds);
  }

  @Override
  public Collection<CSConfiguration> visitAbstracDNodeScope(AbstractDNodeScope asd) {
    updateConfigurationsAttribute(asd, cs.getSelectedConfigurations((AbstractDNode) asd.getElement(), false));
    updateStyle(asd);
    return getAllParentScopeConfigurations(asd);
  }

  public Collection<CSConfiguration> visitDNodeContainerScope(DNodeContainerScope dns){
    return visitAbstracDNodeScope(dns);
  }
  
  public Collection<CSConfiguration> visitDNodeScope(DNodeScope dns){
    return visitAbstracDNodeScope(dns);
  }

  protected void updateStyle(AbstractDNodeScope asd) {
    updateStyle((DDiagramElement) asd.getElement(), getAllParentScopeConfigurations(asd));
  }

  @Override
  public Collection<CSConfiguration> visitDefaultScope(DefaultScope ds) {
    return Collections.emptyList();
  }

  @SuppressWarnings("unchecked")
  private void updateConfigurationsAttribute(Scope scope, Collection<CSConfiguration> local) {
    scope.setAttribute(KEY_CONFIGURATIONS, Collections.unmodifiableCollection(local));
    ArrayList<CSConfiguration> allConfigurations = new ArrayList<CSConfiguration>(local);
    if (scope.getParentScope() != null) {
      allConfigurations.addAll((Collection<CSConfiguration>) scope.getParentScope().getAttribuge(KEY_ALL_CONFIGURATIONS));
    }
    scope.setAttribute(KEY_ALL_CONFIGURATIONS, Collections.unmodifiableCollection(allConfigurations));
  }

  protected Collection<CSConfiguration> getAllParentScopeConfigurations(Scope scope){
    if (scope.getParentScope() != null) {
      return getAllScopeConfigurations(scope.getParentScope());
    }
    return Collections.emptyList();
  }
  
  @SuppressWarnings("unchecked")
  protected Collection<CSConfiguration> getAllScopeConfigurations(Scope scope){
    return (Collection<CSConfiguration>) scope.getAttribuge(KEY_ALL_CONFIGURATIONS);
  }

  /**
   * Clears the style classes and adds the 'excluded' and/or 'included' classes,
   * depending on the applied configurations for the given element.
   * 
   * @param style the style to update
   * @param target the target semantic element
   * @param appliedConfigurations the configurations that should be queried for inclusion/exclusion of the semantic element
   */
  protected void updateStyle(DDiagramElement element, Collection<CSConfiguration> appliedConfigurations) {
    CSSAdapter style = CSSAdapter.getAdapter(element).clear();
    EObject target = getTarget(element);
    updateStyle(style, target, appliedConfigurations);
  }

  protected void updateStyle(CSSAdapter style, EObject semantic, Collection<CSConfiguration> appliedConfigurations) {
    if (semantic != null) {
      for (Iterator<CSConfiguration> it = appliedConfigurations.iterator(); it.hasNext();) {
        CSConfiguration c = it.next();
        
        // adding a class that includes the configuration name allows
        // users to apply styles to a concrete configuration

        if (c.getScope().contains(semantic)) {
          if (c.includes((ModelElement)semantic)) {//FIXME cast
            style.addCSSClass("vpms-included");
            style.addCSSClass("vpms-included-" + c.getName());
          } else {
            style.addCSSClass("vpms-excluded");
            style.addCSSClass("vpms-excluded-" + c.getName());
          }
        }
      }
    }
  }

  /**
   * Which semantic element should be checked for inclusion/exclusion when the style
   * for the given diagram element is calculated?
   * 
   * @param e
   * @return the semantic element or null to indicate that the style should be cleared
   */
  protected EObject getTarget(DDiagramElement e) {  
    EObject semanticTarget = e.getTarget();
    if (semanticTarget instanceof Part) {
      return semanticTarget = ((Part) semanticTarget).getType();
    }
    return semanticTarget;
  }

}
