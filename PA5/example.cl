
(*  Example cool program testing as many aspects of the code generator
    as possible.
 *)
class A {
};

Class C inherits A {
    (* Test feature attr wit hand without initialization *)
    testattr : String;
    testattrassign : Int <- 1;
   
    (* Test feature method *)
    init(map : String) : SELF_TYPE {
        {
          (* Test assign expression*)
            testattr <- map;
            self;
        }
    };

    (* Test new *)
    testatmethod(num : Int) : A {
        {
            num <- 2;
            new A;
        }
    };
};

Class BB inherits C {

    testC : C <- new C;
    bbNum : Int <- 1;

    (* Test order of arguments *)
    testargumentorder(a1 : Int, a2: Int) : IO {{
      (new IO).out_string("Argument 1 should be 1: ");
      (new IO).out_int(a1);
      (new IO).out_string("\n");
      (new IO).out_string("Argument 2 should be 2: ");
      (new IO).out_int(a2);
      (new IO).out_string("\n");
    }};

    (* Test static dispatch *)
    testatmethod(num : Int) : A {
        {
            num <- 3;
            new A;
        }
    };

    (* Test static dispatch *)
    testat(var : C) : SELF_TYPE {
        {
            var@C.testatmethod(1);
            self;
        }
    };

    (* Test case expression *)
    testcase(var : A) : IO {
      case var of
        a : A => (new IO).out_string("Class type is now A\n");
        o : Object => (new IO).out_string("Oooops\n");
      esac
    };

    (* Test case expression no match *)
    testcasenomatch(var : Int) : IO {
      case var of
        a : A => (new IO).out_string("Class type is now A\n");
        o : String => (new IO).out_string("Oooops\n");
      esac
    };

    (* Test case expression void *)
    testcasevoid(var : A) : IO {
      (let position : Object in
        case position of
          a : A => (new IO).out_string("Class type is now A\n");
          o : Object => (new IO).out_string("Oooops\n");
        esac
      )
    };

    (* Test dispatch expression void *)
    testdispatchvoid() : IO {
      (let position : IO in
        position.out_int(1)
      )
    };

    testmany() : SELF_TYPE {

        (* Test let with and without initialization*)
        (let position : Int <- 1 in
        (let selfobj : Object <- self in
        (let temp : Int, temp2 : Int, temp3 : Int, voidobj : Object in
            {
                (new IO).out_string("should be 1: ");
                (new IO).out_int(position);
                (new IO).out_string("\n");

                (new IO).out_string("should be 0: ");
                (new IO).out_int(temp);
                (new IO).out_string("\n");

                (* Test assign on attr*)
                (new IO).out_string("should be 1: ");
                (new IO).out_int(bbNum);
                (new IO).out_string("\n");
                (new IO).out_string("should be 2: ");
                (new IO).out_int(bbNum <- 2);
                (new IO).out_string("\n");
                (new IO).out_string("should be 2: ");
                (new IO).out_int(bbNum);
                (new IO).out_string("\n");
                self;
            }
        ) ) )
    };
};
class Main inherits IO {
  main():Object {{
  	out_string("\n");

  	(* tseting if *)
  	out_string("should be 1: ");
  	out_int(if true then 1 else 0 fi);
  	out_string("\n");

  	(* testing = *)
  	out_string(if 1+1=2 then "1 + 1 = 2" else "1 + 1 not = 2" fi);
  	out_string("\n");

  	(* testing int comparisons *)
  	out_string(if 1<2 then "1 < 2" else "1 >= 2" fi);
  	out_string("\n");
  	out_string(if 1<=1 then "1 <= 1" else "1 > 1" fi);
  	out_string("\n");
  	out_string(if 1<=2 then "1 <= 2" else "1 > 2" fi);
  	out_string("\n");

  	(* testing arithmetic *)
  	out_string("4 + 2 = ");
  	out_int(4+2);
  	out_string("\n");
  	out_string("4 - 2 = ");
  	out_int(4-2);
  	out_string("\n");
  	out_string("4 * 2 = ");
  	out_int(4*2);
  	out_string("\n");
  	out_string("4 / 2 = ");
  	out_int(4/2);
  	out_string("\n");

  	(* testing neg *)
  	out_string("~1 = ");
  	out_int(~1);
  	out_string("\n");

  	(* testing not *)
  	out_string("not true = ");
  	out_int(if not true then 1 else 0 fi);
  	out_string("\n");
	out_string("not false = ");
  	out_int(if not false then 1 else 0 fi);
  	out_string("\n");


  	(* testing isvoid *)
  	out_string("isvoid 1 = ");
  	out_int(if (isvoid 1) then 1 else 0 fi);
  	out_string("\n");

  	(* testing block *)
  	out_string("block 1; 2; evaluates to ");
  	out_int({1; 2;});
  	out_string("\n");

  	(* testing static dispatch *)
  	out_string("\"abcdef\"@String.substr(2,3) = ");
  	out_string("abcdef"@String.substr(2,3));
  	out_string("\n");

  	(* testing while loop *)
  	(* for checkpoint we only test a loop that should never run *)
  	while false loop out_string("loop: this should not be printed\n") pool;
    out_string("\n");

    (new BB).testmany();
    (new BB).testcase(new A);
    (new BB).testat(new C);
    (new BB).testargumentorder(1, 2);
    -- (new BB).testcasevoid(new A);
    -- (new BB).testcasenomatch(1);
    -- (new BB).testdispatchvoid();
  }};
};

