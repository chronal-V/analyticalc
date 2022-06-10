grammar Calc;


function: 'f(' VAR ')=' expr
        ;


expr: PARANL inner=expr PARANR # OpParan
        | base=expr POW exponent=expr # OpPow
        | left=expr MUL right=expr # OpMul
        | left=expr variable=VAR # OpImplicitMul
        | left=expr ADD right=expr # OpAdd
        | variable=VAR # Variable
        | number=NUM # Number
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