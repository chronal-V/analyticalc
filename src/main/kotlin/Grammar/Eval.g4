grammar Eval;

function: 'f(' VAR ')=' expression
        ;

expression: '(' expression ')'
        | expression '^' expression
        | expression ('*' | '/') expression
        | expression ('+' | '-') expression
        | VAR
        | NUM
        ;

NUM: [0-9]+
        ;
      
VAR: [a-z]
        ;

WS: [ \r\n\t] + -> skip
        ;