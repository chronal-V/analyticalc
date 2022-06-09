grammar Eval;


function: 'f(' VAR ')=' expression
        ;


expression: '(' expression ')' #OpParan
        | expression '^' expression # OpPower
        | expression MUL expression # OpMul
        | expression ('+' | '-') expression # OpAdd
        | VAR #Var
        | NUM #Num
        ;

MUL: '*' | '/';

ADD: '+' | '-';

POW: '^';

PARANL: '(';

PARANR: ')';

NUM: [0-9]+
        ;
      
VAR: [a-z]
        ;

WS: [ \r\n\t] + -> skip
        ;