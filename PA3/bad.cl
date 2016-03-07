
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

    testmany() : SELF_TYPE {
        (* Test error handling in let *)
        (let temp : Int, temp2, temp3 : Int, voidobj : Object in
        {
            (* Test error handling in block *)
            temp <- 1 + while;
            temp <- 1 + 1;
        }
    )};
};

(* error:  a is not a type identifier *)
Class C inherits a {
};

(* error:  keyword inherits is misspelled *)
Class D inherts A {
};

(* error:  closing brace is missing *)
Class E inherits A {
;

(* Test cannot continue to H because E never terminated properly *)
Class H inherits A {
};

