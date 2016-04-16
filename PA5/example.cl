
(*  Example cool program testing as many aspects of the code generator
    as possible.
 *)

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
  	while false loop out_string("loop: this should not be printed") pool;
  }};
};

