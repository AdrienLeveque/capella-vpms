/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
// Generated from MsExpression.g4 by ANTLR 4.3
package org.polarsys.capella.vp.ms.expression.parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MsExpressionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MsExpressionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MsExpressionParser#notExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(@NotNull MsExpressionParser.NotExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link MsExpressionParser#orExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(@NotNull MsExpressionParser.OrExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link MsExpressionParser#href}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHref(@NotNull MsExpressionParser.HrefContext ctx);

	/**
	 * Visit a parse tree produced by {@link MsExpressionParser#andExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(@NotNull MsExpressionParser.AndExprContext ctx);
}