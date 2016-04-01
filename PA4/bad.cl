class Errors {
	(* Test assign error *)
	a : Int <- "string";

};

class A {
	  (* Test wrong number of arguments method dispatch and wrong argument type *)
      bar() : Object {
      	{
		    (new B).foo(self, 29, 29);
		    (new B).foo(self, "moo");
		}
      };

      (* Test static dispatch *)
      testStaticDispatch(newA : A) : A {
      	{
	      	newA@B.foo(self, 29);
	      	newA;
	    }
      };

      (* Test bad if statement predicate *)
      testBadCond(temp2 : Int) : Int {
			if "moo" then
                temp2 <- 3
            else
                temp2 <- 4
            fi
      };

      (* Test bad while statement predicate *)
      testBadLoop(temp2 : Int) : Int {
			while "moo" loop {
				temp2 <- 1;
			} pool
      };

      (* Test bad let initialization *)
      testBadLet() : A {
			let x : Int <- "moo" in new A
      };

      (* Test bad comparison *)
      testBadComparison() : A {
		{
			"moo" < "moo";
			"moo" <= "moo";
			"moo" = 1;
		}
      };
};

class B inherits A {
      foo(b:Object, x:Int) : String { "moo" };

      (* Test mismatched return *)
      foo(b:Object, x:Int) : String { 1 };
};

class C {
	a : Int;
	b : Bool;
	init(x : Int, y : Bool) : C {
           {
		a <- x;
		b <- y;
		self;
           }
	};
};

Class Main {
	main():C {
	 {
	  (new C).init(1,1);
	  (new C).init(1,true,3);
	  (new C).iinit(1,true);
	  (new C);
	 }
	};
};
