
(*  Example cool program testing as many aspects of the code generator
    as possible.
 *)

class Main inherits IO {
  main():Object {{
  	out_string("\n");
  	(* testing = *)
  	out_string(if 1+1=2 then "1 + 1 = 2" else "1 + 1 not = 2" fi);
  	out_string("\n");
  	(* testing < *)
  	out_string(if 1<2 then "1 < 2" else "1 >= 2" fi);
  	out_string("\n");
  	(* testing <= *)
  }};
};

