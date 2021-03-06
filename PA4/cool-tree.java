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
import java.util.HashMap;
import java.util.HashSet;


/** Defines simple phylum Program */
abstract class Program extends TreeNode {
    protected Program(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract void semant();

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
        class_c childClass;
        class_c mainClass = null;
        String childName;
        String parentName;
        PrintStream error;

        /* Add valid vertices to inheritance graph and check basic class semantics */
        /* Checks for multiple definitions of class */
        for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
            childClass = (class_c) e.nextElement();
            if (preGraphClassSemanticCheck(childClass, classTable)) {
                if (!classTable.inheritanceGraph.addVertex(childClass)) {
                    error = classTable.semantError(childClass);
                    error.println("Multiple definitions of class" + childClass.getName().toString() + "not allowed.");
                }
            }
        }

        /* Add edges to inheritance graph and check for class semantics */
        /* Checks for inheritance from undefined class */
        for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
            childClass = (class_c) e.nextElement();
            childName = childClass.getName().toString();
            if (classTable.inheritanceGraph.s2v.containsKey(childName)) {
                parentName = childClass.getParent().toString();
                /* Edge cannot be added if parent is undefined */
                if (!classTable.inheritanceGraph.addEdge(parentName, childName)) {
                    error = classTable.semantError(childClass);
                    error.println("Class" + childName + "may not inherit from undefined Class" + parentName + ".");
                }
            }
        }

        class_c cyclicClass = classTable.inheritanceGraph.hasCycle();
        if (cyclicClass != null) {
            error = classTable.semantError(cyclicClass);
            error.println("Class" + cyclicClass.getName().toString() + "is involved in an inheritance cycle.");
        }

        if (classTable.errors()) {
            System.err.println("Compilation halted due to static semantic errors.");
            System.exit(1);
        }

        /* Get class attributes and methods for use in Symbol Table */
        fillClassFeatureInformation(classTable, classes);

        /* Last step in semantic analyzer - Type Checking */
        nameScopeTypeChecking(classTable, classes);

        if (classTable.errors()) {
            System.err.println("Compilation halted due to static semantic errors.");
            System.exit(1);
        }
    }

    private boolean preGraphClassSemanticCheck(class_c childClass, ClassTable classTable) {
        boolean noErrors = true;
        String childName = childClass.getName().toString();
        PrintStream error;
        /* Basic classes may not be redefined */
        if (childName.equals(TreeConstants.Object_.toString())) {
            noErrors = false;
            error = classTable.semantError(childClass);
            error.println("Cannot redefine basic class Object.");
        } else if (childName.equals(TreeConstants.Int.toString())) {
            noErrors = false;
            error = classTable.semantError(childClass);
            error.println("Cannot redefine basic class Int.");
        } else if (childName.equals(TreeConstants.Str.toString())) {
            noErrors = false;
            error = classTable.semantError(childClass);
            error.println("Cannot redefine basic class String.");
        } else if (childName.equals(TreeConstants.Bool.toString())) {
            noErrors = false;
            error = classTable.semantError(childClass);
            error.println("Cannot redefine basic class Bool.");
        } else if (childName.equals(TreeConstants.IO.toString())) {
            noErrors = false;
            error = classTable.semantError(childClass);
            error.println("Cannot redefine basic class IO.");
        } else {
            /* childClass may not inherit from Int, String, or Bool basic classes */
            String parentName = childClass.getParent().toString();
            if (parentName.equals(TreeConstants.Int.toString())) {
                noErrors = false;
                error = classTable.semantError(childClass);
                error.println("Class " + childName + " may not inherit from basic class Int.");
            } else if (parentName.equals(TreeConstants.Str.toString())) {
                noErrors = false;
                error = classTable.semantError(childClass);
                error.println("Class " + childName + " may not inherit from basic class String.");
            } else if (parentName.equals(TreeConstants.Bool.toString())) {
                noErrors = false;
                error = classTable.semantError(childClass);
                error.println("Class " + childName + " may not inherit from basic class Bool.");
            }
        }

        return noErrors;
    }

    private void fillClassFeatureInformation(ClassTable classTable, Classes classes) {
        class_c childClass;
        String childName;
        for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
            childClass = (class_c) e.nextElement();
            childName = childClass.getName().toString();
            Features features = childClass.getFeatures();
            classTable.attributeTable.put(childName, new HashMap<AbstractSymbol, AbstractSymbol>());
            classTable.methodTable.put(childName, new HashMap<AbstractSymbol, method>());
            for (Enumeration<Feature> f = features.getElements(); f.hasMoreElements();){
                Feature currFeature = f.nextElement();
                if(currFeature instanceof attr){
                    attr a = (attr) currFeature;
                    HashMap<AbstractSymbol, AbstractSymbol> classAttributeTable = classTable.attributeTable.get(childName);
                    classAttributeTable.put(a.name, a.type_decl);
                } else {
                    method m = (method) currFeature;
                    HashMap<AbstractSymbol, method> classMethodTable = classTable.methodTable.get(childName);
                    if (classMethodTable.containsKey(m.name)) {
                        PrintStream error = classTable.semantError(childClass);
                        error.println("Method " + m.name.toString() + " is multiple defined.");
                    }
                    classMethodTable.put(m.name, m);
                }
            }
        }
    }

    private void nameScopeTypeChecking(ClassTable classTable, Classes classes) {
        /* For each class, build the symbol table */
        /* Check for attribute definition in child class */
        /* Check for attribute/method multiple definition */
        boolean hasMainClass = false;
        boolean hasMainMethod = false;
        boolean hasNoFormals = false;
        class_c childClass;
        String childName;
        SymbolTable scopeTable = new SymbolTable();
        for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
            childClass = (class_c) e.nextElement();
            childName = childClass.getName().toString();
            fillClassScope(classTable, scopeTable, childName);
            if (childName.equals("Main")) {
                hasMainClass = true;
            }

            Features features = childClass.getFeatures();
            for (Enumeration<Feature> f = features.getElements(); f.hasMoreElements();){
                Feature currFeature = f.nextElement();
                if(currFeature instanceof attr){
                    attr a = (attr) currFeature;
                    checkAttribute(classTable, scopeTable, childClass, a);
                } else{
                    method m = (method) currFeature;
                    checkMethod(classTable, scopeTable, childClass, m);
                    if (childName.equals("Main") && m.name.toString().equals("main")) {
                        hasMainMethod = true;
                        if (!m.formals.getElements().hasMoreElements()) {
                            hasNoFormals = true;
                        }
                    }
                }
            }
            scopeTable.exitScope();
        }

        /* Check semantics for Main class */
        checkMainClass(classTable, hasMainClass, hasMainMethod, hasNoFormals);
    }

    /* Populates class/inherited attributes into scope of class childName */
    private void fillClassScope(ClassTable classTable, SymbolTable scopeTable, String childName) {
        scopeTable.enterScope();
        for (Vertex v = classTable.inheritanceGraph.s2v.get(childName); v != null; v = v.parent) {
            HashMap<AbstractSymbol, AbstractSymbol> classAttributes = classTable.attributeTable.get(v.toString());
            if (classAttributes == null) {
                continue;
            }
            for (AbstractSymbol attrName : classAttributes.keySet()) {
                if (scopeTable.lookup(attrName) != null) {
                    PrintStream error = classTable.semantError();
                    error.println("Attribute " + attrName.toString() + " in Class " + childName + " already defined in parent class");
                }
                scopeTable.addId(attrName, classAttributes.get(attrName));
            }
        }
        scopeTable.addId(TreeConstants.self, TreeConstants.SELF_TYPE);
    }

    private void checkAttribute(ClassTable classTable, SymbolTable scopeTable, class_c childClass, attr a) {
        AbstractSymbol typeSymbol = typeCheckExpression(classTable, scopeTable, childClass, a.init);
        String typeString = typeSymbol.toString();
        String declaredType = a.type_decl.toString();
        if (!typeString.equals(TreeConstants.No_type.toString())) {
            if (!checkTypeInheritance(classTable, childClass, typeString, declaredType)) {
                PrintStream error = classTable.semantError(childClass);
                error.println("Initialization of type " + typeString
                                + " does not inherit from declared type " + declaredType
                                + " of attribute " + a.name.toString() + ".");
            }
        }
    }

    private void checkMethod(ClassTable classTable, SymbolTable scopeTable, class_c childClass, method m) {
        fillMethodScope(scopeTable, m);
        /* Check if returned type matches method declaration return type */
        AbstractSymbol returnTypeSymbol = typeCheckExpression(classTable, scopeTable, childClass, m.expr);
        String returnTypeString = returnTypeSymbol.toString();
        String declaredReturnType = m.return_type.toString();
        if (!classTable.inheritanceGraph.s2v.containsKey(declaredReturnType) && !declaredReturnType.equals(TreeConstants.SELF_TYPE.toString())) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Undefined return type " + declaredReturnType + " in method " + m.name.toString() + ".");
        }
        if (!checkTypeInheritance(classTable, childClass, returnTypeString, declaredReturnType)) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Return object type " + returnTypeString
                            + " does not match or inherit from declared return type " + declaredReturnType
                            + " of method " + m.name.toString() + ".");
        }

        /* Check for multiple defined formal method parameters */
        checkFormals(childClass, classTable, m);

        /* Check overridden method */
        checkMethodOverride(childClass, classTable, m);
        scopeTable.exitScope();
    }

    /* Set the scope for a method of a class */
    private void fillMethodScope(SymbolTable scopeTable, method m) {
        scopeTable.enterScope();
        for (Enumeration<Formal> e = m.formals.getElements(); e.hasMoreElements();){
            formalc f = (formalc) e.nextElement();
            scopeTable.addId(f.name, f.type_decl);
        }
    }

    /* Check if childType inherits from parentType */
    private boolean checkTypeInheritance(ClassTable classTable, class_c childClass, String childType, String parentType) {
        if(childType.equals(parentType)){
            return true;
        } else {
            String selfTypeString = TreeConstants.SELF_TYPE.toString();
            if (parentType.equals(selfTypeString) && !childType.equals(selfTypeString)){
                return false;
            } else if(childType.equals(selfTypeString)){
                childType = childClass.getName().toString();
            }
        }
        
        /* Check if childType inherits from parentType */
        boolean inherits = false;
        for (Vertex v = classTable.inheritanceGraph.s2v.get(childType); v != null; v = v.parent) {
            if (v.toString().equals(parentType)) {
                inherits = true;
                break;
            }
        }
        return inherits;
    }

    /* Checks whether formal parameters of method m are multiple defined */
    private void checkFormals(class_c childClass, ClassTable classTable, method m) {
        HashSet<String> formals = new HashSet<String>();
        for (Enumeration<Formal> e = m.formals.getElements(); e.hasMoreElements();){
            formalc f = (formalc) e.nextElement();
            String formalName = f.name.toString();
            if (f.type_decl.toString().equals(TreeConstants.SELF_TYPE.toString())) {
                PrintStream error = classTable.semantError(childClass);
                error.println("Formal parameter " + formalName + " cannot have type TreeConstants.SELF_TYPE.");
            }
            if (formals.contains(formalName)) {
                PrintStream error = classTable.semantError(childClass);
                error.println("Formal parameter " + formalName + " is multiple defined.");
            } else {
                formals.add(formalName);
            }
        }
    }

    /* Checks if method overrides parent class method. If so, checks if signature matches */
    private void checkMethodOverride(class_c childClass, ClassTable classTable, method m) {
        method parentMethod = null;
        String childName = childClass.getName().toString();
        AbstractSymbol methodName = m.name;
        for (Vertex v = classTable.inheritanceGraph.s2v.get(childName).parent; v != null; v = v.parent) {
            String parentName = v.toString();
            HashMap<AbstractSymbol, method> parentMethodTable = classTable.methodTable.get(parentName);
            if (parentMethodTable == null) {
                continue;
            }
            if (parentMethodTable.containsKey(methodName)) {
                parentMethod = parentMethodTable.get(methodName);
                break;
            }
        }

        /* Method m does not override any method of parent class */
        if (parentMethod == null) {
            return;
        }

        /* Method m overrides method in parent class */
        /* Check formal parameters and return type */
        for (Enumeration<Formal> e = m.formals.getElements(), e2 = parentMethod.formals.getElements();
             e.hasMoreElements() && e2.hasMoreElements();){

            formalc childFormal = (formalc) e.nextElement();
            formalc parentFormal = (formalc) e2.nextElement();
            String childFormalType = childFormal.type_decl.toString();
            String parentFormalType = parentFormal.type_decl.toString();
            if (!childFormalType.equals(parentFormalType)) {
                PrintStream error = classTable.semantError(childClass);
                error.println("Type" + childFormalType + " of formal parameter"
                            + childFormal.name.toString() + "in method " + methodName
                            + "does not match type" + parentFormalType 
                            + " of formal parameter of overriden method in parent class.");
                break;
            }
        }
        /* Check return type of overridden method */
        String childReturnType = m.return_type.toString();
        String parentReturnType = parentMethod.return_type.toString();
        if (!childReturnType.equals(parentReturnType)) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Return type" + childReturnType + "of method " + methodName
                        + "does not match return type" + parentReturnType 
                        + " of overriden method in parent class.");
        }
    }

    /* Checks semantics for Main Class of program */
    private void checkMainClass(ClassTable classTable, boolean hasMainClass, boolean hasMainMethod, boolean hasNoFormals) {
        PrintStream error;
        if (!hasMainClass) {
            error = classTable.semantError();
            error.println("Main Class has not been defined.");
        } else {
            if (!hasMainMethod) {
                error = classTable.semantError();
                error.println("Method main in Main Class has not been defined.");
            } else {
                if (!hasNoFormals) {
                    error = classTable.semantError();
                    error.println("Method main in Main Class must not have arguments.");
                }
            }
        }
    }
    private AbstractSymbol typeCheckExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, Expression e) {
        if (e instanceof assign) {
            return typeCheckAssignExpression(classTable, scopeTable, childClass, (assign) e);
        } else if (e instanceof static_dispatch) {
            return typeCheckStaticDispatchExpression(classTable, scopeTable, childClass, (static_dispatch) e);
        } else if (e instanceof dispatch) {
            return typeCheckDispatchExpression(classTable, scopeTable, childClass, (dispatch) e);
        } else if (e instanceof cond) {
            return typeCheckCondExpression(classTable, scopeTable, childClass, (cond) e);
        } else if (e instanceof loop) {
            return typeCheckLoopExpression(classTable, scopeTable, childClass, (loop) e);
        } else if (e instanceof typcase) {
            return typeCheckTypcaseExpression(classTable, scopeTable, childClass, (typcase) e);
        } else if (e instanceof block) {
            return typeCheckBlockExpression(classTable, scopeTable, childClass, (block) e);
        } else if (e instanceof let) {
            return typeCheckLetExpression(classTable, scopeTable, childClass, (let) e);
        } else if (e instanceof plus) {
            return typeCheckPlusExpression(classTable, scopeTable, childClass, (plus) e);
        } else if (e instanceof sub) {
            return typeCheckSubExpression(classTable, scopeTable, childClass, (sub) e);
        } else if (e instanceof mul) {
            return typeCheckMulExpression(classTable, scopeTable, childClass, (mul) e);
        } else if (e instanceof divide) {
            return typeCheckDivideExpression(classTable, scopeTable, childClass, (divide) e);
        } else if (e instanceof neg) {
            return typeCheckNegExpression(classTable, scopeTable, childClass, (neg) e);
        } else if (e instanceof lt) {
            return typeCheckLtExpression(classTable, scopeTable, childClass, (lt) e);
        } else if (e instanceof eq) {
            return typeCheckEqExpression(classTable, scopeTable, childClass, (eq) e);
        } else if (e instanceof leq) {
            return typeCheckLeqExpression(classTable, scopeTable, childClass, (leq) e);
        } else if (e instanceof comp) {
            return typeCheckCompExpression(classTable, scopeTable, childClass, (comp) e);
        } else if (e instanceof int_const) {
            e.set_type(TreeConstants.Int);
            return TreeConstants.Int;
        } else if (e instanceof bool_const) {
            e.set_type(TreeConstants.Bool);
            return TreeConstants.Bool;
        } else if (e instanceof string_const) {
            e.set_type(TreeConstants.Str);
            return TreeConstants.Str;
        } else if (e instanceof new_) {
            return typeCheckNewExpression(classTable, scopeTable, childClass, (new_) e);
        } else if (e instanceof isvoid) {
            return typeCheckIsVoidExpression(classTable, scopeTable, childClass, (isvoid) e);
        } else if (e instanceof no_expr) { // nocheck
            e.set_type(TreeConstants.No_type);
            return TreeConstants.No_type;
        } else if (e instanceof object) {
            return typeCheckObjectExpression(classTable, scopeTable, childClass, (object) e);
        }

        return TreeConstants.No_type;
    }

    /* Perform typechecking on an assign expression */
    private AbstractSymbol typeCheckAssignExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, assign e) {
        AbstractSymbol returnType = typeCheckExpression(classTable, scopeTable, childClass, e.expr);
        String returnTypeString = returnType.toString();
        AbstractSymbol declaredType = (AbstractSymbol) scopeTable.lookup(e.name);

        if (declaredType == null) {
            return TreeConstants.Object_;
        }
        /* Check if initialization type inherits from declared type of variable */
        if (!checkTypeInheritance(classTable, childClass, returnTypeString, declaredType.toString())) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Initialization of type " + returnTypeString
                            + " does not inherit from declared type " + declaredType.toString()
                            + " of variable " + e.name.toString() + ".");
            return TreeConstants.Object_;
        }

        e.set_type(returnType);
        return returnType;
    }

    private AbstractSymbol typeCheckStaticDispatchExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, static_dispatch e) {
        AbstractSymbol e0Type = typeCheckExpression(classTable, scopeTable, childClass, e.expr);
        Vector<AbstractSymbol> actualTypes = new Vector<AbstractSymbol>();

        /* Get types of actuals and typecheck actuals */
        for (Enumeration<Expression> exprs = e.actual.getElements(); exprs.hasMoreElements();) {
            Expression expression = (Expression) exprs.nextElement();
            actualTypes.add(typeCheckExpression(classTable, scopeTable, childClass, expression));
        }

        method m = classTable.methodTable.get(e.type_name.toString()).get(e.name);
        if (m == null) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Method " + e.name.toString() + "of class " + e.type_name.toString() + "not defined.");
            return TreeConstants.Object_;
        }

        Enumeration<AbstractSymbol> a = actualTypes.elements();
        Enumeration<Formal> f = m.formals.getElements();
        AbstractSymbol returnType = null;
        while (f.hasMoreElements() && a.hasMoreElements()) {
            formalc formalParameter = (formalc) f.nextElement();
            AbstractSymbol formalType = formalParameter.type_decl;
            AbstractSymbol actualType = (AbstractSymbol) a.nextElement();
            String formalTypeString = formalType.toString();
            String actualTypeString = actualType.toString();
            if (!checkTypeInheritance(classTable, childClass, actualTypeString, formalTypeString)) {
                PrintStream error = classTable.semantError(childClass);
                error.println("Initialization of type " + actualTypeString
                                + " does not inherit from declared type " + formalTypeString
                                + " of parameter " + formalParameter.name.toString() + " in method " + m.name.toString()
                                + " of class " + e.type_name.toString() + ".");
                returnType = TreeConstants.Object_;
            }
        }

        /* Check of number of actual arguments match method formal arguments */
        if (f.hasMoreElements() || a.hasMoreElements()) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Number of arguments to method " + m.name.toString()
                            + " in class " + e.type_name.toString()
                            + " does not match given number of arguments.");
            return TreeConstants.Object_;
        }

        /* Actual argument type did not match method formal argument type */
        if (returnType != null) {
            return returnType;
        }

        /* Check if e0 class inherits from class of static dispatch */
        String e0TypeString = e0Type.toString();
        if (!checkTypeInheritance(classTable, childClass, e0TypeString, e.type_name.toString())) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Type" + e0TypeString + " of static function dispatch "
                            + "does not inherit from class " + e.type_name.toString()
                            + " containing method " + m.name.toString() + ".");
            return TreeConstants.Object_;
        }

        if (m.return_type.toString().equals(TreeConstants.SELF_TYPE.toString())) {
            returnType = e0Type;
        } else {
            returnType = m.return_type;
        }

        e.set_type(returnType);
        return returnType;
    }

    private AbstractSymbol typeCheckDispatchExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, dispatch e) {
        AbstractSymbol e0Type = typeCheckExpression(classTable, scopeTable, childClass, e.expr);
        Vector<AbstractSymbol> actualTypes = new Vector<AbstractSymbol>();

        /* Get types of actuals and typecheck actuals */
        for (Enumeration<Expression> exprs = e.actual.getElements(); exprs.hasMoreElements();) {
            Expression expression = (Expression) exprs.nextElement();
            actualTypes.add(typeCheckExpression(classTable, scopeTable, childClass, expression));
        }

        String methodClassName;
        if (e0Type.toString().equals(TreeConstants.SELF_TYPE.toString())) {
            methodClassName = childClass.getName().toString();
        } else {
            methodClassName = e0Type.toString();
        }
        method m = null;
        for (Vertex v = classTable.inheritanceGraph.s2v.get(methodClassName); v != null; v = v.parent) {
            String parentName = v.toString();
            HashMap<AbstractSymbol, method> parentMethodTable = classTable.methodTable.get(parentName);
            if (parentMethodTable == null) {
                continue;
            }
            if (parentMethodTable.containsKey(e.name)) {
                m = parentMethodTable.get(e.name);
                break;
            }
        }

        if (m == null) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Method " + e.name.toString() + " of class " + e0Type.toString() + " not defined.");
            return TreeConstants.Object_;
        }

        Enumeration<AbstractSymbol> a = actualTypes.elements();
        Enumeration<Formal> f = m.formals.getElements();
        AbstractSymbol returnType = null;
        while (f.hasMoreElements() && a.hasMoreElements()) {
            formalc formalParameter = (formalc) f.nextElement();
            AbstractSymbol formalType = formalParameter.type_decl;
            AbstractSymbol actualType = (AbstractSymbol) a.nextElement();
            String formalTypeString = formalType.toString();
            String actualTypeString = actualType.toString();
            if (!checkTypeInheritance(classTable, childClass, actualTypeString, formalTypeString)) {
                PrintStream error = classTable.semantError(childClass);
                error.println("Initialization of type " + actualTypeString
                                + " does not inherit from declared type " + formalTypeString
                                + " of parameter " + formalParameter.name.toString() + " in method " + m.name.toString()
                                + " of class " + e0Type.toString() + ".");
                returnType = TreeConstants.Object_;
            }
        }

        /* Check of number of actual arguments match method formal arguments */
        if (f.hasMoreElements() || a.hasMoreElements()) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Number of arguments to method " + m.name.toString()
                            + " in class " + e0Type.toString()
                            + " does not match given number of arguments.");
            return TreeConstants.Object_;
        }

        /* Actual argument type did not match method formal argument type */
        if (returnType != null) {
            return returnType;
        }

        if (m.return_type.toString().equals(TreeConstants.SELF_TYPE.toString())) {
            returnType = e0Type;
        } else {
            returnType = m.return_type;
        }

        e.set_type(returnType);
        return returnType;
    }

    private AbstractSymbol typeCheckPlusExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, plus e) {
        AbstractSymbol e1Type = typeCheckExpression(classTable, scopeTable, childClass, e.e1);
        AbstractSymbol e2Type = typeCheckExpression(classTable, scopeTable, childClass, e.e2);
        String e1TypeString = e1Type.toString();
        String e2TypeString = e2Type.toString();
        String intTypeString = TreeConstants.Int.toString();
        if (!e1TypeString.equals(intTypeString) || !e2TypeString.equals(intTypeString) ) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Argument of plus expression is not of type Int.");
            return TreeConstants.Object_;
        }

        e.set_type(TreeConstants.Int);
        return TreeConstants.Int;
    }
    private AbstractSymbol typeCheckSubExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, sub e) {
        AbstractSymbol e1Type = typeCheckExpression(classTable, scopeTable, childClass, e.e1);
        AbstractSymbol e2Type = typeCheckExpression(classTable, scopeTable, childClass, e.e2);
        String e1TypeString = e1Type.toString();
        String e2TypeString = e2Type.toString();
        String intTypeString = TreeConstants.Int.toString();
        if (!e1TypeString.equals(intTypeString) || !e2TypeString.equals(intTypeString) ) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Argument of sub expression is not of type Int.");
            return TreeConstants.Object_;
        }

        e.set_type(TreeConstants.Int);
        return TreeConstants.Int;
    }
    private AbstractSymbol typeCheckMulExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, mul e) {
        AbstractSymbol e1Type = typeCheckExpression(classTable, scopeTable, childClass, e.e1);
        AbstractSymbol e2Type = typeCheckExpression(classTable, scopeTable, childClass, e.e2);
        String e1TypeString = e1Type.toString();
        String e2TypeString = e2Type.toString();
        String intTypeString = TreeConstants.Int.toString();
        if (!e1TypeString.equals(intTypeString) || !e2TypeString.equals(intTypeString) ) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Argument of mul expression is not of type Int.");
            return TreeConstants.Object_;
        }

        e.set_type(TreeConstants.Int);
        return TreeConstants.Int;
    }

    private AbstractSymbol typeCheckDivideExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, divide e) {
        AbstractSymbol e1Type = typeCheckExpression(classTable, scopeTable, childClass, e.e1);
        AbstractSymbol e2Type = typeCheckExpression(classTable, scopeTable, childClass, e.e2);
        String e1TypeString = e1Type.toString();
        String e2TypeString = e2Type.toString();
        String intTypeString = TreeConstants.Int.toString();
        
        if (!e1TypeString.equals(intTypeString) || !e2TypeString.equals(intTypeString) ) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Argument of divide expression is not of type Int.");
            return TreeConstants.Object_;
        }

        e.set_type(TreeConstants.Int);
        return TreeConstants.Int;
    }

    private AbstractSymbol typeCheckEqExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, eq e) {
        AbstractSymbol e1Type = typeCheckExpression(classTable, scopeTable, childClass, e.e1);
        AbstractSymbol e2Type = typeCheckExpression(classTable, scopeTable, childClass, e.e2);
        String e1TypeString = e1Type.toString();
        String e2TypeString = e2Type.toString();
        String intTypeString = TreeConstants.Int.toString();
        String boolTypeString = TreeConstants.Bool.toString();
        String stringTypeString = TreeConstants.Str.toString();
        if ((e1TypeString.equals(intTypeString) || e2TypeString.equals(intTypeString)
            || e1TypeString.equals(boolTypeString) || e2TypeString.equals(boolTypeString)
            || e1TypeString.equals(stringTypeString) ||e2TypeString.equals(stringTypeString))
            && !e1TypeString.equals(e2TypeString)) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Type " + e1TypeString + " cannot be compared to type " + e2TypeString + ".");
            return TreeConstants.Object_;
        }

        e.set_type(TreeConstants.Bool);
        return TreeConstants.Bool;
    }

    private AbstractSymbol typeCheckNegExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, neg e) {
        AbstractSymbol e1Type = typeCheckExpression(classTable, scopeTable, childClass, e.e1);
        String e1TypeString = e1Type.toString();
        String intTypeString = TreeConstants.Int.toString();
        
        if (!e1TypeString.equals(intTypeString)) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Argument of neg expression is not of type Int.");
            return TreeConstants.Object_;
        }

        e.set_type(TreeConstants.Int);
        return TreeConstants.Int;
    }

    private AbstractSymbol typeCheckLtExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, lt e) {
        AbstractSymbol e1Type = typeCheckExpression(classTable, scopeTable, childClass, e.e1);
        AbstractSymbol e2Type = typeCheckExpression(classTable, scopeTable, childClass, e.e2);
        String e1TypeString = e1Type.toString();
        String e2TypeString = e2Type.toString();
        String intTypeString = TreeConstants.Int.toString();
        
        if (!e1TypeString.equals(intTypeString) || !e2TypeString.equals(intTypeString) ) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Argument of lt expression is not of type Int.");
            return TreeConstants.Object_;
        }

        e.set_type(TreeConstants.Bool);
        return TreeConstants.Bool;
    }

    private AbstractSymbol typeCheckLeqExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, leq e) {
        AbstractSymbol e1Type = typeCheckExpression(classTable, scopeTable, childClass, e.e1);
        AbstractSymbol e2Type = typeCheckExpression(classTable, scopeTable, childClass, e.e2);
        String e1TypeString = e1Type.toString();
        String e2TypeString = e2Type.toString();
        String intTypeString = TreeConstants.Int.toString();
        
        if (!e1TypeString.equals(intTypeString) || !e2TypeString.equals(intTypeString) ) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Argument of leq expression is not of type Int.");
            return TreeConstants.Object_;
        }

        e.set_type(TreeConstants.Bool);
        return TreeConstants.Bool;
    }

    private AbstractSymbol typeCheckCompExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, comp e) {
        AbstractSymbol e1Type = typeCheckExpression(classTable, scopeTable, childClass, e.e1);
        String e1TypeString = e1Type.toString();
        String boolTypeString = TreeConstants.Bool.toString();
        
        if (!e1TypeString.equals(boolTypeString)) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Argument of not expression is not of type Bool.");
            return TreeConstants.Object_;
        }

        e.set_type(TreeConstants.Bool);
        return TreeConstants.Bool;
    }

    private AbstractSymbol typeCheckCondExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, cond e) {
        AbstractSymbol predType = typeCheckExpression(classTable, scopeTable, childClass, e.pred);
        String predTypeString = predType.toString();
        String boolTypeString = TreeConstants.Bool.toString();
        String thenTypeString = typeCheckExpression(classTable, scopeTable, childClass, e.then_exp).toString();
        String elseTypeString = typeCheckExpression(classTable, scopeTable, childClass, e.else_exp).toString();
        if (!predTypeString.equals(boolTypeString)) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Predicate of loop expression is not of type Bool.");
            return TreeConstants.Object_;
        }

        AbstractSymbol returnType = getCommonAncestor(classTable, thenTypeString, elseTypeString);
        e.set_type(returnType);
        return returnType;
    }

    private AbstractSymbol typeCheckTypcaseExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, typcase e) {
        typeCheckExpression(classTable, scopeTable, childClass, e.expr);

        AbstractSymbol leastCommonAncestor = null;
        for (Enumeration<branch> exprs = e.cases.getElements(); exprs.hasMoreElements();) {
            branch b = (branch) exprs.nextElement();
            scopeTable.enterScope();
            scopeTable.addId(b.name, b.type_decl);
            AbstractSymbol branchType = typeCheckExpression(classTable, scopeTable, childClass, b.expr);
            scopeTable.exitScope();

            if (leastCommonAncestor == null) {
                leastCommonAncestor = branchType;
            }
            leastCommonAncestor = getCommonAncestor(classTable, leastCommonAncestor.toString(), branchType.toString());
        }

        if (leastCommonAncestor == null) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Case expression must contain at least one case.");
            return TreeConstants.Object_;
        }

        e.set_type(leastCommonAncestor);
        return leastCommonAncestor;
    }

    private AbstractSymbol typeCheckLetExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, let e) {
        AbstractSymbol identifierType = e.type_decl;
        if (e.type_decl.toString().equals(TreeConstants.SELF_TYPE.toString())) {
            identifierType = childClass.getName();
        }
        if (!(e.init instanceof no_expr)) {
            AbstractSymbol initType = typeCheckExpression(classTable, scopeTable, childClass, e.init);
            String initTypeString = initType.toString();
            String initDeclaredType = identifierType.toString();
            if (!checkTypeInheritance(classTable, childClass, initTypeString, initDeclaredType)) {
                PrintStream error = classTable.semantError(childClass);
                error.println("Initialization of type " + initTypeString
                                + "does not inherit from declared type" + initDeclaredType
                                + "of variable " + e.identifier.toString() + " in let expression.");
            }
        }

        scopeTable.enterScope();
        scopeTable.addId(e.identifier, identifierType);
        AbstractSymbol bodyType = typeCheckExpression(classTable, scopeTable, childClass, e.body);
        scopeTable.exitScope();

        e.set_type(bodyType);
        return bodyType;
    }

    private AbstractSymbol typeCheckLoopExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, loop e) {
        AbstractSymbol predType = typeCheckExpression(classTable, scopeTable, childClass, e.pred);
        String predTypeString = predType.toString();
        String boolTypeString = TreeConstants.Bool.toString();
        typeCheckExpression(classTable, scopeTable, childClass, e.body);
        if (!predTypeString.equals(boolTypeString)) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Predicate of loop expression is not of type Bool.");
            return TreeConstants.Object_;
        }

        e.set_type(TreeConstants.Object_);
        return TreeConstants.Object_;
    }

    private AbstractSymbol typeCheckBlockExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, block e) {
        AbstractSymbol returnType = null;
        for (Enumeration exprs = e.body.getElements(); exprs.hasMoreElements();) {
            Expression exp = (Expression) exprs.nextElement();
            returnType = typeCheckExpression(classTable, scopeTable, childClass, exp);
        }

        if (returnType == null) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Block expression must contain at least one expression.");
            return TreeConstants.Object_;
        }

        e.set_type(returnType);
        return returnType;
    }

    private AbstractSymbol typeCheckNewExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, new_ e) {
        AbstractSymbol typeName = e.type_name;

        if (!classTable.inheritanceGraph.s2v.containsKey(typeName.toString())) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Cannot construct undefined class" + typeName.toString() + ".");
            return TreeConstants.Object_;
        }

        if (typeName.toString().equals(TreeConstants.SELF_TYPE.toString())) {
            e.set_type(childClass.getName());
            return childClass.getName(); 
        }

        e.set_type(typeName);
        return typeName;
    }

    private AbstractSymbol typeCheckIsVoidExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, isvoid e) {
        typeCheckExpression(classTable, scopeTable, childClass, e.e1);

        e.set_type(TreeConstants.Bool);
        return TreeConstants.Bool;
    }

    private AbstractSymbol typeCheckObjectExpression(ClassTable classTable, SymbolTable scopeTable, class_c childClass, object e) {
        AbstractSymbol objectType = (AbstractSymbol) scopeTable.lookup(e.name);
        if (objectType == null) {
            PrintStream error = classTable.semantError(childClass);
            error.println("Object " + e.name.toString() + " is not in current scope.");
            return TreeConstants.Object_;
        }

        e.set_type(objectType);
        return objectType;
    }

    /* Get the common ancestor class of class 1 and class 2 */
    private AbstractSymbol getCommonAncestor(ClassTable classTable, String class1, String class2) {
        AbstractSymbol ancestorName = null;
        int depth1 = 0, depth2 = 0;
        Vertex v1 = classTable.inheritanceGraph.s2v.get(class1);
        Vertex v2 = classTable.inheritanceGraph.s2v.get(class2);

        for (Vertex v = v1; v != null; v = v.parent) {
            depth1++;
        }

        for (Vertex v = v2; v != null; v = v.parent) {
            depth2++;
        }

        if (depth1 > depth2) {
            for (int i = 0; i < depth1 - depth2; i++) {
                v1 = v1.parent;
            }
        } else {
            for (int i = 0; i < depth2 - depth1; i++) {
                v2 = v2.parent;
            }
        }
        while (!v1.toString().equals(v2.toString())) {
            v1 = v1.parent;
            v2 = v2.parent;
        }

        return v1.myClass.getName();
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

}


