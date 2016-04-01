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

Class BB__ inherits C {

    testC : C <- new C;

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

    testlet(): Int {
        let x : Int in 1 + 2
    };

    testmany() : SELF_TYPE {

        (* Test let with and without initialization*)
        (let position : Int <- 0 in
        (let num : Int <- 2 in
        (let temp : Int, temp2 : Int, temp3 : Int, voidobj : Object in
            {
                while position < num loop
                    {
                        position <- position + 1;
                    }
                pool;

                position <- 0;
                (* Test while loop *)
                while position < num loop
                    {
                        position <- position + 1;
                        temp <- position;
                    }
                pool;
                (* Test arithmetic *)
                temp <- 10 + 5 - 5 * 6 / 2;

                (* Test isvoid *)
                voidobj <- 1;
                isvoid voidobj;

                (* Test comparison *)
                1 <= 2;
                1 < 2;
                1 = 2;
                new A = new C;

                (* Test if *)
                if 1 < 2 then
                    temp2 <- 3
                else
                    temp2 <- 4
                fi;

                (* Test block *)
                if 1 < 2 then {
                    temp3 <- 3;
                } else {
                    temp3 <- 4;
                } fi;
                self;
            }
        ) ) )
    };
};




Class Main {
	main():C {
	  (new C).init("moo")
	};
};
