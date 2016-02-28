class A {
};

Class C inherits A {
    (* Test feature attr *)
    testattr : String;
    testattrassign : Int <- 1;
   
    (* Test feature method *)
    init(map : String) : SELF_TYPE {
        {
            test_assign <- map;
            (* Test expr-> ID *)
            test_assign;
            self;
        }
    };

    (* Test expr@... *)
    testatmethod(num : Int) : A {
        {
            num <- 2;
            new A;
        }
    };
};

Class BB__ inherits C {

    testC : C <- new C;
    testattr : String;
    testattrassign : Int <- 1;

    (* Test expr@... *)
    testatmethod(num : Int) : C {
        {
            num <- 3;
            new C;
        }
    };

    (* Test expr@... *)
    testat(var : C) : SELF_TYPE {
        {
            var@C.testatmethod(1);
            self;
        }
    };

    (* Test expr -> case *)
    testcase(var : A) : SELF_TYPE {
      case var of
        a : A => out_string("Class type is now A\n");
        o : Object => out_string("Oooops\n");
      esac
    };

    testmany() : SELF_TYPE {

        (* Test let nest with 1 or more expressions in declaration *)
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
                (* Test multiple expressions in while *)
                while position < num loop
                    {
                        position <- position + 1;
                        temp <- position;
                    }
                pool;
                (* Test let ambiguity *)
                temp <- 10 + 5 - 5 * 6 / 2;

                (* Test isvoid *)
                voidobj <- void;
                isvoid voidobj;

                (* Test comparison *)
                1 <= 2;
                1 < 2;
                1 = 2;

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
