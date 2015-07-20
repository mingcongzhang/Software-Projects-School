datatype term = V of string | L of string * term | A of term * term

fun show(t:term):string =
        case t of
                V(st) => st
        |       L(st, tm) => "L" ^ st ^ "." ^show(tm)
        |       A(tm1, tm2) => "(" ^ show(tm1) ^ " " ^ show(tm2) ^ ")";

fun check_var(x:string, y:string, []) = x=y
|       check_var(x:string, y:string, (v1, v2)::t) =
                if x = v1 andalso y = v2 then true
                else if x <> v1 andalso y <> v2 then check_var(x,y,t)
                else false;

fun alpha2(V(tm1), V(tm2), env) = check_var(tm1, tm2, env)
|       alpha2(L(x1, t1), L(x2, t2), env) = alpha2(t1, t2, (x1, x2)::env)
|       alpha2(A(t1, t2), A(t3, t4), env) = alpha2(t1, t3, env) andalso alpha2(t2, t4, env)
|       alpha2(t1:term, t2:term, env) = false;

fun alpha(tm1, tm2) = (alpha2(tm1, tm2, []));
