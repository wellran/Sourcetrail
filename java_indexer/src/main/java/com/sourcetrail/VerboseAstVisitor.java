package com.sourcetrail;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.nodeTypes.NodeWithName;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

public class VerboseAstVisitor extends AstVisitor{
	
	public VerboseAstVisitor(AstVisitorClient client, String filePath, FileContent fileContent, TypeSolver typeSolver) {
		super(client, filePath, fileContent, typeSolver);
	}

	int indent = 0;
	String indentSymbol = "| ";
	
	private String obfuscate(final String s) 
	{
		if (s.isEmpty())
		{
			return "";
		}
		else if (s.length() <= 2)
		{
			return s;
		}
		return s.substring(0, 1) + ".." + s.substring(s.length() - 1, s.length());
	}
	
	private void dump(Node n)
	{
		String line = "";
		
		for (int i = 0; i < this.indent; i++)
		{
			line += this.indentSymbol;
		}
		
		line += n.getClass().getName();
		if (n instanceof NodeWithName<?>)
		{
			line += " [" + obfuscate(((NodeWithName<?>)n).getNameAsString()) + "]";
		}
		
		if (n.getBegin().isPresent())
		{
			line += " line: " + n.getBegin().get().line;
		}
		
		m_client.logInfo(line);
	}
	
	 //- Compilation Unit ----------------------------------

	public void visit(CompilationUnit n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(PackageDeclaration n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ImportDeclaration n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }
	
	public void visit(TypeParameter n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(LineComment n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(BlockComment n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	//- Body ----------------------------------------------

	public void visit(ClassOrInterfaceDeclaration n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(EnumDeclaration n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(EnumConstantDeclaration n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(AnnotationDeclaration n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(AnnotationMemberDeclaration n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(FieldDeclaration n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(VariableDeclarator n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ConstructorDeclaration n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(MethodDeclaration n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(Parameter n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(InitializerDeclaration n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(JavadocComment n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	//- Type ----------------------------------------------

	public void visit(ClassOrInterfaceType n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(PrimitiveType n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ArrayType n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ArrayCreationLevel n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

    public void visit(IntersectionType n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

    public void visit(UnionType n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(VoidType n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(WildcardType n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(UnknownType n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	//- Expression ----------------------------------------

	public void visit(ArrayAccessExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ArrayCreationExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ArrayInitializerExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(AssignExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(BinaryExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(CastExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ClassExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ConditionalExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(EnclosedExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(FieldAccessExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(InstanceOfExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(StringLiteralExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(IntegerLiteralExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(LongLiteralExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(CharLiteralExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(DoubleLiteralExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(BooleanLiteralExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(NullLiteralExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(MethodCallExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(NameExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ObjectCreationExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ThisExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(SuperExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(UnaryExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(VariableDeclarationExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(MarkerAnnotationExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(SingleMemberAnnotationExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(NormalAnnotationExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(MemberValuePair n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	//- Statements ----------------------------------------

	public void visit(ExplicitConstructorInvocationStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(AssertStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(BlockStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(LabeledStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(EmptyStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ExpressionStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(SwitchStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(SwitchEntryStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(BreakStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ReturnStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(IfStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(WhileStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ContinueStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(DoStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ForeachStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ForStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(ThrowStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(SynchronizedStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(TryStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

	public void visit(CatchClause n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

    public void visit(LambdaExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

    public void visit(MethodReferenceExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

    public void visit(TypeExpr n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }

    public void visit(UnparsableStmt n, Void v) { dump(n); indent++; super.visit(n, v); indent--; }
}