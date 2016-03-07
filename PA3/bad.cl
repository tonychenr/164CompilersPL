
(*
 *  execute "coolc bad.cl" to see the error messages that the coolc parser
 *  generates
 *
 *  execute "./myparser bad.cl" to see the error messages that your parser
 *  generates
 *)

(* no error *)
class A {
};

(* error:  b is not a type identifier *)
Class b inherits A {
};

(* Test parser restarts at next class definition, F has no error *)
Class F inherits A {
};

Class G inherits F {

    (* Test error handling of features *)
    test1 : Int;
    test2 fdsa;
    test3 : Int <- 1;
    test4 fdsa;

    testRecoverFeatures(): SELF_TYPE {
        let x : Int in 1 + 2;
    };

    testmany() : SELF_TYPE {
        (* Test error handling in let *)
        (let temp : Int, temp2, temp3 : Int, voidobj : Object in
        {
            (* Test error handling in block *)
            temp <- 1 + while;
            temp <- 1 + 1;
            temp <- 1 + while;
        }
    )};

        (* Test if parsing stops if there is an error at end of let binding *)
    -- testmany2() : SELF_TYPE {
    --     (* Test error handling in let *)
    --     (let temp : Int, temp3 : Int, voidobj : Object, temp2 in
    --     {
    --         (* Test error handling in block *)
    --         temp <- 1 + while;
    --         temp <- 1 + 1;
    --     }
    -- )};
};

(* error:  a is not a type identifier *)
Class C inherits a {
};

(* error:  keyword inherits is misspelled *)
Class D inherts A {
};

(* error:  unterminated class cannot recover *)
Class E inherits A {
;

(* Test cannot continue to H because E never terminated properly *)
Class H inherits A {
};

