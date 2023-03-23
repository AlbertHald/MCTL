grammar mctl;

mctl: (line)*;


block
    : LCURL (line)* RCURL
    ;

line
    : declaration
    | statement
    | COMMENT
    | SEMI
    ;

declaration
    : variableDeclaration SEMI
    | function
    | structDeclaration
    ;

variableDeclaration
    : Variable ID COLON variableType
    ;

structDeclaration
    : Struct STRUCTID structBlock
    ;

structBlock
    : LCURL variableDeclaration (COMMA variableDeclaration)* (COMMA)? RCURL
    ;

id
    : ID (DOT id)?
    | ID (LSQR expression RSQR)*
    ;

statement
    : if
    | repeat
    | assignment SEMI
    | invoke SEMI
    | Stop SEMI
    | return SEMI
    ;

return
    : Return expression
    ;

function
    :  To ID LPAR (formalParameters)? RPAR COLON returnType block
    ;

if
    : If LPAR expression RPAR block (else)?
    ;

else
    : Else if
    | Else block
    ;

repeat
    : Repeat LPAR expression RPAR block
    ;

assignment
    : id ASSIGN expression
    ;

invoke
    : ID LPAR actualParameters RPAR
    ;

formalParameters
    : (formalParameter COMMA)* formalParameter (COMMA)?
    ;

formalParameter
    : ID COLON variableType
    ;

actualParameters
    : (expression COMMA)* expression (COMMA)?
    ;




invokeExpression
    : ID LPAR actualParameters RPAR
    |
    ;

unaryExpression
    : (NOT|MINUS|PLUS) castExpression
    | invokeExpression
    ;

castExpression
    : unaryExpression
    | LPAR variableType RPAR castExpression
    | boolean
    | NUMBER
    | id
    | STRING
    ;

multiplicativeExpression
    : castExpression ((MULTIPLY|DIVIDE|MODULO) castExpression)*
    ;

additiveExpression
    : multiplicativeExpression ((PLUS|MINUS) multiplicativeExpression)*
    ;

relationalExpression
    : additiveExpression ((LESS|LESSEQUAL|GREATER|GREATEREQUAL) additiveExpression)*
    ;

equalityExpression
    : relationalExpression ((EQUAL|NOTEQUAL) relationalExpression)*
    ;

logicalAndExpression
    : equalityExpression (And equalityExpression)*
    ;

logicalOrExpression
    : logicalAndExpression (Or logicalAndExpression)*
    ;

expression
    : LPAR expression RPAR
    | logicalOrExpression
    ;

returnType
    : variableType
    | Nothing
    ;

variableType
    : baseVariableType (LSQR RSQR)*
    ;

baseVariableType
    : Boolean
    | String
    | Number
    | STRUCTID
    ;

boolean
    : (True|False)
    ;



WS: [ \n\t\r]+ -> skip;
COMMENT: '#{' .*? '}' -> skip;

EQUAL: '==';
NOTEQUAL: '!=';
LESS: '<';
LESSEQUAL: '<=';
GREATER: '>';
GREATEREQUAL: '>=';

DOT:'.';
COMMA:',';
MULTIPLY: '*';
DIVIDE:'/';
MODULO:'%';
MINUS: '-';
PLUS: '+';
ASSIGN: '=';
LPAR: '(';
RPAR: ')';
LSQR: '[';
RSQR: ']';
LCURL: '{';
RCURL: '}';
STRING: ('"' .*? '"'|'\'' .*? '\'');
COLON: ':';
SEMI: ';';
NOT: '!';

Nothing: 'NOTHING';
String: 'STRING';
Number: 'NUMBER';
Boolean: 'BOOLEAN';
Struct: 'struct';
True: 'true';
False: 'false';
And: 'and';
Or: 'or';
Stop: 'stop';
Return: 'return';
To: 'to';
Variable: 'variable';
If: 'if';
Else: 'else';
Repeat: 'repeat';

STRUCTID: [A-Z_] [A-Z0-9_]*;
ID: [a-zA-Z_] [a-zA-Z0-9_]*;
NUMBER: [0-9]+('.'[0-9]*)?;

