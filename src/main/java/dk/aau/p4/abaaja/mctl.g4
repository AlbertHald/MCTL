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
    : Struct ID structBlock
    ;

structBlock
    : LCURL variableDeclaration (COMMA variableDeclaration)* (COMMA)? RCURL
    ;

id
    : ID DOT id
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
    : To ID LPAR (formalParameters)? RPAR COLON returnType block
    ;

if
    : ifLiteral block (Else ifLiteral block)* (Else block)?
    ;

ifLiteral
    : If LPAR expression RPAR
    ;

repeat
    : Repeat LPAR expression RPAR block
    ;

assignment
    : id ASSIGN expression
    | id PLUS PLUS
    ;

invoke
    : ID LPAR (actualParameters)? RPAR
    | (ID | STRING) DOT (Add|IndexesOf|SubString|SubList) LPAR actualParameters RPAR
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

expression
    : invoke
    | LPAR expression RPAR
    | (NOT|MINUS|PLUS) expression
    | LPAR variableType RPAR expression
    | boolean
    | NUMBER
    | id
    | STRING
    | expression (MULTIPLY|DIVIDE|MODULO) expression
    | expression (PLUS|MINUS) expression
    | expression (LESS|LESSEQUAL|GREATER|GREATEREQUAL) expression
    | expression (EQUAL|NOTEQUAL) expression
    | expression And expression
    | expression Or expression
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
    | ID
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
Add: 'add';
IndexesOf: 'indexesOf';
SubString: 'subString';
SubList: 'subList';
And: 'and';
Or: 'or';
Stop: 'stop';
Return: 'return';
To: 'to';
Variable: 'variable';
If: 'if';
Else: 'else';
Repeat: 'repeat';

ID: [a-zA-Z_] [a-zA-Z0-9_]*;
NUMBER: [0-9]+('.'[0-9]*)?;

