// -*- mode: java -*- 
//
// file: cool-tree.m4
//
// This file defines the AST
//
//////////////////////////////////////////////////////////



import java.util.Enumeration;
import java.io.PrintStream;
import java.util.Vector;


/** Defines simple phylum Program */
abstract class Program extends TreeNode {
    protected Program(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract void semant();
    public abstract void cgen(PrintStream s);

}


/** Defines simple phylum Class_ */
abstract class Class_ extends TreeNode {
    protected Class_(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract AbstractSymbol getName();
    public abstract AbstractSymbol getParent();
    public abstract AbstractSymbol getFilename();
    public abstract Features getFeatures();

}


/** Defines list phylum Classes
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Classes extends ListNode {
    public final static Class elementClass = Class_.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Classes(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Classes" list */
    public Classes(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Class_" element to this list */
    public Classes appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Classes(lineNumber, copyElements());
    }
}


/** Defines simple phylum Feature */
abstract class Feature extends TreeNode {
    protected Feature(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Features
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Features extends ListNode {
    public final static Class elementClass = Feature.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Features(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Features" list */
    public Features(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Feature" element to this list */
    public Features appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Features(lineNumber, copyElements());
    }
}


/** Defines simple phylum Formal */
abstract class Formal extends TreeNode {
    protected Formal(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Formals
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Formals extends ListNode {
    public final static Class elementClass = Formal.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Formals(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Formals" list */
    public Formals(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Formal" element to this list */
    public Formals appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Formals(lineNumber, copyElements());
    }
}


/** Defines simple phylum Expression */
abstract class Expression extends TreeNode {
    protected Expression(int lineNumber) {
        super(lineNumber);
    }
    private AbstractSymbol type = null;                                 
    public AbstractSymbol get_type() { return type; }           
    public Expression set_type(AbstractSymbol s) { type = s; return this; } 
    public abstract void dump_with_types(PrintStream out, int n);
    public void dump_type(PrintStream out, int n) {
        if (type != null)
            { out.println(Utilities.pad(n) + ": " + type.getString()); }
        else
            { out.println(Utilities.pad(n) + ": _no_type"); }
    }
    public abstract void code(CgenClassTable table, PrintStream s);

}


/** Defines list phylum Expressions
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Expressions extends ListNode {
    public final static Class elementClass = Expression.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Expressions(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Expressions" list */
    public Expressions(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Expression" element to this list */
    public Expressions appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Expressions(lineNumber, copyElements());
    }
}


/** Defines simple phylum Case */
abstract class Case extends TreeNode {
    protected Case(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Cases
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Cases extends ListNode {
    public final static Class elementClass = Case.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Cases(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Cases" list */
    public Cases(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Case" element to this list */
    public Cases appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Cases(lineNumber, copyElements());
    }
}


/** Defines AST constructor 'programc'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class programc extends Program {
    protected Classes classes;
    /** Creates "programc" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for classes
      */
    public programc(int lineNumber, Classes a1) {
        super(lineNumber);
        classes = a1;
    }
    public TreeNode copy() {
        return new programc(lineNumber, (Classes)classes.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "programc\n");
        classes.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_program");
        for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
	    ((Class_)e.nextElement()).dump_with_types(out, n + 2);
        }
    }
    /** This method is the entry point to the semantic checker.  You will
        need to complete it in programming assignment 4.
	<p>
        Your checker should do the following two things:
	<ol>
	<li>Check that the program is semantically correct
	<li>Decorate the abstract syntax tree with type information
        by setting the type field in each Expression node.
        (see tree.h)
	</ol>
	<p>
	You are free to first do (1) and make sure you catch all semantic
    	errors. Part (2) can be done in a second stage when you want
	to test the complete compiler.
    */
    public void semant() {
	/* ClassTable constructor may do some semantic analysis */
	ClassTable classTable = new ClassTable(classes);
	
	/* some semantic analysis code may go here */

	if (classTable.errors()) {
	    System.err.println("Compilation halted due to static semantic errors.");
	    System.exit(1);
	}
    }
    /** This method is the entry point to the code generator.  All of the work
      * of the code generator takes place within CgenClassTable constructor.
      * @param s the output stream 
      * @see CgenClassTable
      * */
    public void cgen(PrintStream s) {
	CgenClassTable codegen_classtable = new CgenClassTable(classes, s);
    }

}


/** Defines AST constructor 'class_c'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class class_c extends Class_ {
    protected AbstractSymbol name;
    protected AbstractSymbol parent;
    protected Features features;
    protected AbstractSymbol filename;
    /** Creates "class_c" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for parent
      * @param a2 initial value for features
      * @param a3 initial value for filename
      */
    public class_c(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Features a3, AbstractSymbol a4) {
        super(lineNumber);
        name = a1;
        parent = a2;
        features = a3;
        filename = a4;
    }
    public TreeNode copy() {
        return new class_c(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(parent), (Features)features.copy(), copy_AbstractSymbol(filename));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "class_c\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, parent);
        features.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, filename);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_class");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, parent);
        out.print(Utilities.pad(n + 2) + "\"");
        Utilities.printEscapedString(out, filename.getString());
        out.println("\"\n" + Utilities.pad(n + 2) + "(");
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
	    ((Feature)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
    }
    public AbstractSymbol getName()     { return name; }
    public AbstractSymbol getParent()   { return parent; }
    public AbstractSymbol getFilename() { return filename; }
    public Features getFeatures()       { return features; }

}


/** Defines AST constructor 'method'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class method extends Feature {
    protected AbstractSymbol name;
    protected Formals formals;
    protected AbstractSymbol return_type;
    protected Expression expr;
    /** Creates "method" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for formals
      * @param a2 initial value for return_type
      * @param a3 initial value for expr
      */
    public method(int lineNumber, AbstractSymbol a1, Formals a2, AbstractSymbol a3, Expression a4) {
        super(lineNumber);
        name = a1;
        formals = a2;
        return_type = a3;
        expr = a4;
    }
    public TreeNode copy() {
        return new method(lineNumber, copy_AbstractSymbol(name), (Formals)formals.copy(), copy_AbstractSymbol(return_type), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "method\n");
        dump_AbstractSymbol(out, n+2, name);
        formals.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, return_type);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_method");
        dump_AbstractSymbol(out, n + 2, name);
        for (Enumeration e = formals.getElements(); e.hasMoreElements();) {
	    ((Formal)e.nextElement()).dump_with_types(out, n + 2);
        }
        dump_AbstractSymbol(out, n + 2, return_type);
	expr.dump_with_types(out, n + 2);
    }

}


/** Defines AST constructor 'attr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class attr extends Feature {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    protected Expression init;
    /** Creates "attr" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      * @param a2 initial value for init
      */
    public attr(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        init = a3;
    }
    public TreeNode copy() {
        return new attr(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)init.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "attr\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_attr");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
    }

}


/** Defines AST constructor 'formalc'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class formalc extends Formal {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    /** Creates "formalc" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      */
    public formalc(int lineNumber, AbstractSymbol a1, AbstractSymbol a2) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
    }
    public TreeNode copy() {
        return new formalc(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "formalc\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_formal");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
    }

}


/** Defines AST constructor 'branch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class branch extends Case {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    protected Expression expr;
    /** Creates "branch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      * @param a2 initial value for expr
      */
    public branch(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        expr = a3;
    }
    public TreeNode copy() {
        return new branch(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "branch\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_branch");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	expr.dump_with_types(out, n + 2);
    }

}


/** Defines AST constructor 'assign'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class assign extends Expression {
    protected AbstractSymbol name;
    protected Expression expr;
    /** Creates "assign" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for expr
      */
    public assign(int lineNumber, AbstractSymbol a1, Expression a2) {
        super(lineNumber);
        name = a1;
        expr = a2;
    }
    public TreeNode copy() {
        return new assign(lineNumber, copy_AbstractSymbol(name), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "assign\n");
        dump_AbstractSymbol(out, n+2, name);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_assign");
        dump_AbstractSymbol(out, n + 2, name);
	expr.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        expr.code(table, s);
        if (table.lookup(name) == null) {
            // Variable name not in current scope, so it is an attribute.
            // Get attribute offset
            Vector<attr> attrs = table.currentSelf.attrs;
            int attrOffset = 3;
            for (int i = 0; i < attrs.size(); i++) {
                if (attrs.get(i).name.equals(name)) {
                    attrOffset = 3 + i;
                    break;
                }
            }
            CgenSupport.emitStore(CgenSupport.ACC, attrOffset, CgenSupport.SELF, s);
            if (Flags.cgen_Memmgr != Flags.GC_NOGC) {
                CgenSupport.emitAddiu(CgenSupport.A1, CgenSupport.SELF, CgenSupport.WORD_SIZE * attrOffset, s);
                CgenSupport.emitGCAssign(s);
            }
        } else {
            // Variable name is in current scope, so get offset in the frame
            int offset = (Integer) table.lookup(name);
            CgenSupport.emitStore(CgenSupport.ACC, offset, CgenSupport.FP, s);
        }
    }


}


/** Defines AST constructor 'static_dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class static_dispatch extends Expression {
    protected Expression expr;
    protected AbstractSymbol type_name;
    protected AbstractSymbol name;
    protected Expressions actual;
    /** Creates "static_dispatch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for type_name
      * @param a2 initial value for name
      * @param a3 initial value for actual
      */
    public static_dispatch(int lineNumber, Expression a1, AbstractSymbol a2, AbstractSymbol a3, Expressions a4) {
        super(lineNumber);
        expr = a1;
        type_name = a2;
        name = a3;
        actual = a4;
    }
    public TreeNode copy() {
        return new static_dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(type_name), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "static_dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, type_name);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_static_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, type_name);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        // evaluate arguments in order, but store them on stack in reverse order
        for (int i = 0; i < actual.getLength(); i++) {
            Expression a = (Expression) actual.getNth(i);
            a.code(table, s);
            table.emitPush(CgenSupport.ACC, s);
        }

        // evaluate expr and store result in ACC
        expr.code(table, s);
        if (expr.get_type().equals(TreeConstants.SELF_TYPE))
            CgenSupport.emitMove(CgenSupport.ACC, CgenSupport.SELF, s);

        // prepare labels
        int voidLabel = table.getNextLabel();
        int endLabel = table.getNextLabel();

        // if expr is void go to voidLabel
        CgenSupport.emitBeqz(CgenSupport.ACC, voidLabel, s);

        // else
        // extract dispatch table pointer from expr object
        CgenSupport.emitLoad(CgenSupport.T1, CgenSupport.DISPTABLE_OFFSET, CgenSupport.ACC, s);
        // get dispatch table offset and call method
        CgenNode exprNode = (CgenNode) table.lookup(type_name);
        if (type_name.equals(TreeConstants.SELF_TYPE))
            exprNode = table.currentSelf; 
        Vector<method> methods = exprNode.methods;
        int offset = -1;
        for (int i = 0; i < methods.size(); i++) {
            if (methods.get(i).name.equals(name))
                offset = i;
        }
        CgenSupport.emitLoad(CgenSupport.T1, offset, CgenSupport.T1, s);
        CgenSupport.emitJalr(CgenSupport.T1, s);
        // b endLabel
        CgenSupport.emitBranch(endLabel, s);

        // voidLabel:
        CgenSupport.emitLabelDef(voidLabel, s);
        StringSymbol filename = (StringSymbol) exprNode.getFilename();
        CgenSupport.emitLoadString(CgenSupport.ACC, filename, s);
        CgenSupport.emitLoadImm(CgenSupport.T1, getLineNumber(), s);
        CgenSupport.emitJal("_dispatch_abort", s);

        // endLabel:
        CgenSupport.emitLabelDef(endLabel, s);
    }


}


/** Defines AST constructor 'dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class dispatch extends Expression {
    protected Expression expr;
    protected AbstractSymbol name;
    protected Expressions actual;
    /** Creates "dispatch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for name
      * @param a2 initial value for actual
      */
    public dispatch(int lineNumber, Expression a1, AbstractSymbol a2, Expressions a3) {
        super(lineNumber);
        expr = a1;
        name = a2;
        actual = a3;
    }
    public TreeNode copy() {
        return new dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        // evaluate arguments in order, but store them on stack in reverse order
        for (int i = 0; i < actual.getLength(); i++) {
            Expression a = (Expression) actual.getNth(i);
            a.code(table, s);
            table.emitPush(CgenSupport.ACC, s);
        }

        // evaluate expr and store result in ACC
        expr.code(table, s);
        if (expr.get_type().equals(TreeConstants.SELF_TYPE))
            CgenSupport.emitMove(CgenSupport.ACC, CgenSupport.SELF, s);

        // prepare labels
        int voidLabel = table.getNextLabel();
        int endLabel = table.getNextLabel();

        // if expr is void go to voidLabel
        CgenSupport.emitBeqz(CgenSupport.ACC, voidLabel, s);

        // else
        // extract dispatch table pointer from expr object
        CgenSupport.emitLoad(CgenSupport.T1, CgenSupport.DISPTABLE_OFFSET, CgenSupport.ACC, s);
        // get dispatch table offset and call method
        AbstractSymbol exprType = expr.get_type();
        CgenNode exprNode = (CgenNode) table.lookup(exprType);
        if (exprType.equals(TreeConstants.SELF_TYPE))
            exprNode = table.currentSelf; 
        Vector<method> methods = exprNode.methods;
        int offset = -1;
        for (int i = 0; i < methods.size(); i++) {
            if (methods.get(i).name.equals(name))
                offset = i;
        }
        CgenSupport.emitLoad(CgenSupport.T1, offset, CgenSupport.T1, s);
        CgenSupport.emitJalr(CgenSupport.T1, s);
        // b endLabel
        CgenSupport.emitBranch(endLabel, s);

        // voidLabel:
        CgenSupport.emitLabelDef(voidLabel, s);
        StringSymbol filename = (StringSymbol) exprNode.getFilename();
        CgenSupport.emitLoadString(CgenSupport.ACC, filename, s);
        CgenSupport.emitLoadImm(CgenSupport.T1, getLineNumber(), s);
        CgenSupport.emitJal("_dispatch_abort", s);

        // endLabel:
        CgenSupport.emitLabelDef(endLabel, s);
    }


}


/** Defines AST constructor 'cond'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class cond extends Expression {
    protected Expression pred;
    protected Expression then_exp;
    protected Expression else_exp;
    /** Creates "cond" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for pred
      * @param a1 initial value for then_exp
      * @param a2 initial value for else_exp
      */
    public cond(int lineNumber, Expression a1, Expression a2, Expression a3) {
        super(lineNumber);
        pred = a1;
        then_exp = a2;
        else_exp = a3;
    }
    public TreeNode copy() {
        return new cond(lineNumber, (Expression)pred.copy(), (Expression)then_exp.copy(), (Expression)else_exp.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "cond\n");
        pred.dump(out, n+2);
        then_exp.dump(out, n+2);
        else_exp.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_cond");
	pred.dump_with_types(out, n + 2);
	then_exp.dump_with_types(out, n + 2);
	else_exp.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        // evaluate pred and store pointer to resulting bool object in ACC
        pred.code(table, s);
        // extract bool attribute from bool object
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.ACC, s);

        // prepare labels
        int falseLabel = table.getNextLabel();
        int endLabel = table.getNextLabel();

        // if pred is false (0) go to falseLabel
        CgenSupport.emitBeqz(CgenSupport.T1, falseLabel, s);
        // evaluate then_exp and store result in ACC
        then_exp.code(table, s);
        // b endLabel
        CgenSupport.emitBranch(endLabel, s);
        // falseLabel:
        CgenSupport.emitLabelDef(falseLabel, s);
        // evaluate else_exp and store result in ACC
        else_exp.code(table, s);
        // endLabel:
        CgenSupport.emitLabelDef(endLabel, s);
    }


}


/** Defines AST constructor 'loop'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class loop extends Expression {
    protected Expression pred;
    protected Expression body;
    /** Creates "loop" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for pred
      * @param a1 initial value for body
      */
    public loop(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        pred = a1;
        body = a2;
    }
    public TreeNode copy() {
        return new loop(lineNumber, (Expression)pred.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "loop\n");
        pred.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_loop");
	pred.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        // prepare labels
        int startLabel = table.getNextLabel();
        int endLabel = table.getNextLabel();

        // label start of loop
        CgenSupport.emitLabelDef(startLabel, s);

        // evaluate pred and store pointer to resulting bool object in ACC
        pred.code(table, s);
        // extract bool attribute from bool object
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.ACC, s);

        // if pred is false (0) go to endLabel
        CgenSupport.emitBeqz(CgenSupport.T1, endLabel, s);
        // else evaluate body and store in ACC
        body.code(table, s);
        // b startLabel
        CgenSupport.emitBranch(startLabel, s);
        // endLabel:
        CgenSupport.emitLabelDef(endLabel, s);
        // return void
        CgenSupport.emitMove(CgenSupport.ACC, CgenSupport.ZERO, s);
    }


}


/** Defines AST constructor 'typcase'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class typcase extends Expression {
    protected Expression expr;
    protected Cases cases;
    /** Creates "typcase" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for cases
      */
    public typcase(int lineNumber, Expression a1, Cases a2) {
        super(lineNumber);
        expr = a1;
        cases = a2;
    }
    public TreeNode copy() {
        return new typcase(lineNumber, (Expression)expr.copy(), (Cases)cases.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "typcase\n");
        expr.dump(out, n+2);
        cases.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_typcase");
	expr.dump_with_types(out, n + 2);
        for (Enumeration e = cases.getElements(); e.hasMoreElements();) {
	    ((Case)e.nextElement()).dump_with_types(out, n + 2);
        }
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        expr.code(table, s);

        // Error: Case on void
        int notVoidCaseExpr = table.getNextLabel();
        int endLabel = table.getNextLabel();
        CgenSupport.emitBne(CgenSupport.ACC, CgenSupport.ZERO, notVoidCaseExpr, s);
        CgenSupport.emitLoadString(CgenSupport.ACC, (StringSymbol) table.currentSelf.getFilename(), s);
        CgenSupport.emitLoadImm(CgenSupport.T1, getLineNumber(), s);
        CgenSupport.emitJal("_case_abort2", s);

        CgenSupport.emitLabelDef(notVoidCaseExpr, s);

        for (Enumeration e = cases.getElements(); e.hasMoreElements();) {
            branch b = (branch) e.nextElement();
            
        }

        CgenSupport.emitLabelDef(endLabel, s);
        CgenSupport.emitJal("_case_abort", s);
    }


}


/** Defines AST constructor 'block'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class block extends Expression {
    protected Expressions body;
    /** Creates "block" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for body
      */
    public block(int lineNumber, Expressions a1) {
        super(lineNumber);
        body = a1;
    }
    public TreeNode copy() {
        return new block(lineNumber, (Expressions)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "block\n");
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_block");
        for (Enumeration e = body.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        for (Enumeration e = body.getElements(); e.hasMoreElements(); )
            ((Expression) e.nextElement()).code(table, s);
    }


}


/** Defines AST constructor 'let'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class let extends Expression {
    protected AbstractSymbol identifier;
    protected AbstractSymbol type_decl;
    protected Expression init;
    protected Expression body;
    /** Creates "let" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for identifier
      * @param a1 initial value for type_decl
      * @param a2 initial value for init
      * @param a3 initial value for body
      */
    public let(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3, Expression a4) {
        super(lineNumber);
        identifier = a1;
        type_decl = a2;
        init = a3;
        body = a4;
    }
    public TreeNode copy() {
        return new let(lineNumber, copy_AbstractSymbol(identifier), copy_AbstractSymbol(type_decl), (Expression)init.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "let\n");
        dump_AbstractSymbol(out, n+2, identifier);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_let");
	dump_AbstractSymbol(out, n + 2, identifier);
	dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        if(init instanceof no_expr) {
            // Set to default value of object if no initialization, default void if not basic class
            if (type_decl.equals(TreeConstants.Int))
                CgenSupport.emitLoadInt(CgenSupport.ACC, (IntSymbol) AbstractTable.inttable.lookup("0"), s);
            else if (type_decl.equals(TreeConstants.Str))
                CgenSupport.emitLoadString(CgenSupport.ACC, (StringSymbol) AbstractTable.stringtable.lookup(""), s);
            else if (type_decl.equals(TreeConstants.Bool))
                CgenSupport.emitLoadBool(CgenSupport.ACC, BoolConst.falsebool, s);
            else
                CgenSupport.emitMove(CgenSupport.ACC, CgenSupport.ZERO, s);
        } else {
            init.code(table, s);
        }

        table.enterScope();
        // Map identifier to offset and decrement frame offset
        table.addId(identifier, table.frameOffset);
        // Push identifier value onto stack
        table.emitPush(CgenSupport.ACC, s);
        // Generate code for body of let
        body.code(table, s);
        // Increment frame offset and stack pointer back to previous value
        table.emitPop(s);
        table.exitScope();


    }


}


/** Defines AST constructor 'plus'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class plus extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "plus" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public plus(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new plus(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "plus\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_plus");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        // evaluate e1, store pointer in ACC
        e1.code(table, s);
        // push ACC onto stack
        table.emitPush(CgenSupport.ACC, s);
        // evaluate e2, store pointer in ACC
        e2.code(table, s);
        // copy ACC, store pointer of the new int object in ACC
        CgenSupport.emitJal("Object.copy", s);
        // load pointer to int object on stack (result of e1)
        CgenSupport.emitLoad(CgenSupport.T1, 1, CgenSupport.SP, s);
        // extract actual int attribute from this int object (result of e1)
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.T1, s);
        // extract actual int attribute from int object containing result of e2
        CgenSupport.emitLoad(CgenSupport.T2, 3, CgenSupport.ACC, s);
        // perform the addition
        CgenSupport.emitAdd(CgenSupport.T1, CgenSupport.T1, CgenSupport.T2, s);
        // store result in the newly allocated int object
        CgenSupport.emitStore(CgenSupport.T1, 3, CgenSupport.ACC, s);
        // restore stack pointer
        table.emitPop(s);
    }


}


/** Defines AST constructor 'sub'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class sub extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "sub" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public sub(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new sub(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "sub\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_sub");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        // same as add but with emitSub
        e1.code(table, s);
        table.emitPush(CgenSupport.ACC, s);
        e2.code(table, s);
        CgenSupport.emitJal("Object.copy", s);
        CgenSupport.emitLoad(CgenSupport.T1, 1, CgenSupport.SP, s);
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.T1, s);
        CgenSupport.emitLoad(CgenSupport.T2, 3, CgenSupport.ACC, s);
        CgenSupport.emitSub(CgenSupport.T1, CgenSupport.T1, CgenSupport.T2, s);
        CgenSupport.emitStore(CgenSupport.T1, 3, CgenSupport.ACC, s);
        table.emitPop(s);
    }


}


/** Defines AST constructor 'mul'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class mul extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "mul" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public mul(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new mul(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "mul\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_mul");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        // same as add but with emitMul
        e1.code(table, s);
        table.emitPush(CgenSupport.ACC, s);
        e2.code(table, s);
        CgenSupport.emitJal("Object.copy", s);
        CgenSupport.emitLoad(CgenSupport.T1, 1, CgenSupport.SP, s);
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.T1, s);
        CgenSupport.emitLoad(CgenSupport.T2, 3, CgenSupport.ACC, s);
        CgenSupport.emitMul(CgenSupport.T1, CgenSupport.T1, CgenSupport.T2, s);
        CgenSupport.emitStore(CgenSupport.T1, 3, CgenSupport.ACC, s);
        table.emitPop(s);
    }


}


/** Defines AST constructor 'divide'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class divide extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "divide" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public divide(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new divide(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "divide\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_divide");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        // same as add but with emitDiv
        e1.code(table, s);
        table.emitPush(CgenSupport.ACC, s);
        e2.code(table, s);
        CgenSupport.emitJal("Object.copy", s);
        CgenSupport.emitLoad(CgenSupport.T1, 1, CgenSupport.SP, s);
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.T1, s);
        CgenSupport.emitLoad(CgenSupport.T2, 3, CgenSupport.ACC, s);
        CgenSupport.emitDiv(CgenSupport.T1, CgenSupport.T1, CgenSupport.T2, s);
        CgenSupport.emitStore(CgenSupport.T1, 3, CgenSupport.ACC, s);
        table.emitPop(s);
    }


}


/** Defines AST constructor 'neg'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class neg extends Expression {
    protected Expression e1;
    /** Creates "neg" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public neg(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new neg(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "neg\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_neg");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        // evaluate e1 and store pointer in ACC
        e1.code(table, s);
        // copy int object and store pointer to newly allocated int in ACC
        CgenSupport.emitJal("Object.copy", s);
        // extract int attribute from int object
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.ACC, s);
        // perform the neg
        CgenSupport.emitNeg(CgenSupport.T1, CgenSupport.T1, s);
        // store result in newly allocated int
        CgenSupport.emitStore(CgenSupport.T1, 3, CgenSupport.ACC, s);
    }


}


/** Defines AST constructor 'lt'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class lt extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "lt" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public lt(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new lt(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "lt\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_lt");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        // first few lines similar to add
        e1.code(table, s);
        table.emitPush(CgenSupport.ACC, s);
        e2.code(table, s);
        CgenSupport.emitLoad(CgenSupport.T1, 1, CgenSupport.SP, s);
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.T1, s);
        CgenSupport.emitLoad(CgenSupport.T2, 3, CgenSupport.ACC, s);

        // restore stack pointer
        table.emitPop(s);

        // set up label numbers
        int trueLabel = table.getNextLabel();
        int endLabel = table.getNextLabel();

        // blt $t1 $a0 trueLabel
        CgenSupport.emitBlt(CgenSupport.T1, CgenSupport.T2, trueLabel, s);
        // load false into ACC
        CgenSupport.emitLoadBool(CgenSupport.ACC, BoolConst.falsebool,s);
        // b endLabel
        CgenSupport.emitBranch(endLabel, s);
        // trueLabel:
        CgenSupport.emitLabelDef(trueLabel,s);
        // load true into ACC
        CgenSupport.emitLoadBool(CgenSupport.ACC, BoolConst.truebool,s);
        // endLabel:
        CgenSupport.emitLabelDef(endLabel, s);
    }


}


/** Defines AST constructor 'eq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class eq extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "eq" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public eq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new eq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "eq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_eq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        // first few lines similar to add
        e1.code(table, s);
        table.emitPush(CgenSupport.ACC, s);
        e2.code(table, s);

        // get object pointers into $t1 and $t2
        CgenSupport.emitLoad(CgenSupport.T1, 1, CgenSupport.SP, s);
        CgenSupport.emitMove(CgenSupport.T2, CgenSupport.ACC, s);
        //restore stac, pointer
        table.emitPop(s);

        // store true in $a0
        CgenSupport.emitLoadBool(CgenSupport.ACC, BoolConst.truebool, s);
        // store false in $a1
        CgenSupport.emitLoadBool(CgenSupport.A1, BoolConst.falsebool, s);
        // put $a0 (true) into ACC if t1=t2, else put $a1 (false) into ACC
        CgenSupport.emitJal("equality_test", s);
    }


}


/** Defines AST constructor 'leq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class leq extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "leq" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public leq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new leq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "leq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_leq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        // same as lt but with emitBleq
        e1.code(table, s);
        table.emitPush(CgenSupport.ACC, s);
        e2.code(table, s);
        CgenSupport.emitLoad(CgenSupport.T1, 1, CgenSupport.SP, s);
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.T1, s);
        CgenSupport.emitLoad(CgenSupport.T2, 3, CgenSupport.ACC, s);
        table.emitPop(s);
        int trueLabel = table.getNextLabel();
        int endLabel = table.getNextLabel();
        CgenSupport.emitBleq(CgenSupport.T1, CgenSupport.T2, trueLabel, s);
        CgenSupport.emitLoadBool(CgenSupport.ACC, BoolConst.falsebool,s);
        CgenSupport.emitBranch(endLabel, s);
        CgenSupport.emitLabelDef(trueLabel,s);
        CgenSupport.emitLoadBool(CgenSupport.ACC, BoolConst.truebool,s);
        CgenSupport.emitLabelDef(endLabel, s);
    }


}


/** Defines AST constructor 'comp'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class comp extends Expression {
    protected Expression e1;
    /** Creates "comp" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public comp(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new comp(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "comp\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_comp");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        // evaluate expression and store pointer to resulting bool object in ACC
        e1.code(table, s);
        // extract bool attribute from bool object
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.ACC, s);

        // set up label numbers
        int falseLabel = table.getNextLabel();
        int endLabel = table.getNextLabel();

        // beqz $t1 falseLabel
        CgenSupport.emitBeqz(CgenSupport.T1, falseLabel, s);
        // load false into ACC (since e1 is true)
        CgenSupport.emitLoadBool(CgenSupport.ACC, BoolConst.falsebool, s);
        // b endLabel
        CgenSupport.emitBranch(endLabel, s);
        // falseLabel:
        CgenSupport.emitLabelDef(falseLabel, s);
        // load true into ACC (since e1 is false)
        CgenSupport.emitLoadBool(CgenSupport.ACC, BoolConst.truebool, s);
        // endLabel:
        CgenSupport.emitLabelDef(endLabel, s);
    }


}


/** Defines AST constructor 'int_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class int_const extends Expression {
    protected AbstractSymbol token;
    /** Creates "int_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for token
      */
    public int_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new int_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "int_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_int");
	dump_AbstractSymbol(out, n + 2, token);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method method is provided
      * to you as an example of code generation.
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
	CgenSupport.emitLoadInt(CgenSupport.ACC,
                                (IntSymbol)AbstractTable.inttable.lookup(token.getString()), s);
    }

}


/** Defines AST constructor 'bool_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class bool_const extends Expression {
    protected Boolean val;
    /** Creates "bool_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for val
      */
    public bool_const(int lineNumber, Boolean a1) {
        super(lineNumber);
        val = a1;
    }
    public TreeNode copy() {
        return new bool_const(lineNumber, copy_Boolean(val));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "bool_const\n");
        dump_Boolean(out, n+2, val);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_bool");
	dump_Boolean(out, n + 2, val);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method method is provided
      * to you as an example of code generation.
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
	CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(val), s);
    }

}


/** Defines AST constructor 'string_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class string_const extends Expression {
    protected AbstractSymbol token;
    /** Creates "string_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for token
      */
    public string_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new string_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "string_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_string");
	out.print(Utilities.pad(n + 2) + "\"");
	Utilities.printEscapedString(out, token.getString());
	out.println("\"");
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method method is provided
      * to you as an example of code generation.
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
	CgenSupport.emitLoadString(CgenSupport.ACC,
                                   (StringSymbol)AbstractTable.stringtable.lookup(token.getString()), s);
    }

}


/** Defines AST constructor 'new_'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class new_ extends Expression {
    protected AbstractSymbol type_name;
    /** Creates "new_" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for type_name
      */
    public new_(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        type_name = a1;
    }
    public TreeNode copy() {
        return new new_(lineNumber, copy_AbstractSymbol(type_name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "new_\n");
        dump_AbstractSymbol(out, n+2, type_name);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_new");
	dump_AbstractSymbol(out, n + 2, type_name);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        String prototypeName;
        // If SELF_TYPE, use name of current self object
        if (type_name.equals(TreeConstants.SELF_TYPE)) {
            prototypeName = table.currentSelf.getName().getString();
        } else {
            prototypeName = type_name.getString();
        }
        // Load object prototype address
        CgenSupport.emitLoadAddress(CgenSupport.ACC, prototypeName + CgenSupport.PROTOBJ_SUFFIX, s);
        // Call Object.copy to copy prototype object
        CgenSupport.emitJal("Object.copy", s);
        // Initialize object with init function
        CgenSupport.emitJal(prototypeName + CgenSupport.CLASSINIT_SUFFIX, s);
    }


}


/** Defines AST constructor 'isvoid'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class isvoid extends Expression {
    protected Expression e1;
    /** Creates "isvoid" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public isvoid(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new isvoid(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "isvoid\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_isvoid");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        e1.code(table, s);

        // set up label numbers
        int trueLabel = table.getNextLabel();
        int endLabel = table.getNextLabel();

        // beqz $a0 trueLabel
        CgenSupport.emitBeqz(CgenSupport.ACC, trueLabel, s);
        // load false into ACC (since e1 is not void)
        CgenSupport.emitLoadBool(CgenSupport.ACC, BoolConst.falsebool, s);
        // b endLabel
        CgenSupport.emitBranch(endLabel, s);
        // trueLabel:
        CgenSupport.emitLabelDef(trueLabel, s);
        // load true into ACC (since e1 is void)
        CgenSupport.emitLoadBool(CgenSupport.ACC, BoolConst.truebool, s);
        // endLabel:
        CgenSupport.emitLabelDef(endLabel, s);
    }


}


/** Defines AST constructor 'no_expr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class no_expr extends Expression {
    /** Creates "no_expr" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      */
    public no_expr(int lineNumber) {
        super(lineNumber);
    }
    public TreeNode copy() {
        return new no_expr(lineNumber);
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "no_expr\n");
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_no_expr");
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
    }


}


/** Defines AST constructor 'object'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class object extends Expression {
    protected AbstractSymbol name;
    /** Creates "object" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      */
    public object(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        name = a1;
    }
    public TreeNode copy() {
        return new object(lineNumber, copy_AbstractSymbol(name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "object\n");
        dump_AbstractSymbol(out, n+2, name);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_object");
	dump_AbstractSymbol(out, n + 2, name);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenClassTable table, PrintStream s) {
        if (name.equals(TreeConstants.self)) {
            CgenSupport.emitMove(CgenSupport.ACC, CgenSupport.SELF, s);
        } else {
            if (table.lookup(name) == null) {
                // Variable name not in current scope, so it is an attribute.
                // Get attribute offset
                int attrOffset = 3;
                for (Enumeration e = table.currentSelf.attrs.elements(); e.hasMoreElements(); ) {
                    attr a = (attr) e.nextElement();
                    if (a.name.equals(name))
                        break;
                    attrOffset++;
                }
                CgenSupport.emitLoad(CgenSupport.ACC, attrOffset, CgenSupport.SELF, s);
            } else {
                // Variable name is in current scope, so get offset in the frame
                int offset = (Integer) table.lookup(name);
                CgenSupport.emitLoad(CgenSupport.ACC, offset, CgenSupport.FP, s);
            }
        }
    }


}


